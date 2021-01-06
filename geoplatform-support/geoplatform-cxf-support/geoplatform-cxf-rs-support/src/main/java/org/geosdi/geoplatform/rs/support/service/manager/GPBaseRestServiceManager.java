/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    protected ApplicationContext appContext;
    protected List<?> serviceBeans;
    protected AtomicBoolean configured = new AtomicBoolean(FALSE);
    protected final String restServiceManagerName;
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
     * @return {@link List<?>}
     */
    @Override
    public List<?> getServiceBeans() {
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
            logger.debug("#############################{}_REST_RESOURCES : {}\n", this.restServiceManagerName.toUpperCase(), this.serviceBeans);
        } else {
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}_REST_RESOURCES already configured.", this.restServiceManagerName.toUpperCase());
        }
    }

    /**
     * @param value
     * @return {@link Integer}
     */
    protected int internalOrder(@Nullable Object value) {
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

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        logger.trace("#############################Called {}#dispose.", this.restServiceManagerName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {\n" +
                " name = " + this.restServiceManagerName + "\n" +
                ", serviceBeans = " + serviceBeans + "\n" +
                "}\n";
    }
}