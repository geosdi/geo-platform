package org.geosdi.geoplatform.rs.support.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseRestServiceManager implements GPRestServiceManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private ApplicationContext appContext;
    private List<Object> serviceBeans;
    private AtomicBoolean configured = new AtomicBoolean(FALSE);
    private final String restServiceManagerName;
    private final boolean sort;

    /**
     * @param theRestServiceManagerName
     * @param isSort
     */
    protected GPBaseRestServiceManager(@Nonnull(when = NEVER) String theRestServiceManagerName, boolean isSort) {
        checkArgument((theRestServiceManagerName != null) && !(theRestServiceManagerName.trim().isEmpty()), "The Parameter restServiceManagerName must not be null or an empty string.");
        this.restServiceManagerName = theRestServiceManagerName;
        this.sort = isSort;
    }

    /**
     * @return {@link List <Object>}
     */
    @Override
    public List<Object> getServiceBeans() {
        return this.serviceBeans;
    }

    /**
     * @throws Exception
     */
    @PostConstruct
    protected void configureRestServices() throws Exception {
        checkArgument(this.appContext != null, "The Parameter appContext must not be null.");
        if (configured.compareAndSet(FALSE, TRUE)) {
            this.serviceBeans = this.appContext.getBeansWithAnnotation(Path.class)
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(Objects::nonNull)
                    .sorted(comparing(this::internalOrder))
                    .collect(toList());
            logger.debug("#############################{}_REST_RESOURCES : {}\n", this.restServiceManagerName, this.serviceBeans);
        } else {
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}_REST_RESOURCES already configured.", this.restServiceManagerName.toUpperCase());
        }
    }

    /**
     * @param value
     * @return {@link Integer}
     */
    int internalOrder(@Nullable Object value) {
        return ((this.sort) && (value != null)) ? this.order(value) : this.unOrder(value);
    }

    /**
     * @param value
     * @return
     */
    int order(@Nonnull(when = NEVER) Object value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        Order annotation = findAnnotation(value.getClass(), Order.class);
        return (annotation != null) ? annotation.value() : -1;
    }

    /**
     * @param value
     * @return {@link Integer}
     */
    int unOrder(@Nullable Object value) {
        return -1;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(this.appContext != null, "The Parameter appContext must not be null.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
                " name = " + this.restServiceManagerName + "\n" +
                ", serviceBeans = " + serviceBeans + "\n" +
                "}\n";
    }
}