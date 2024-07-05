package org.geosdi.geoplatform.experimental.el.dao.scroll;

import net.jcip.annotations.Immutable;
import org.elasticsearch.core.TimeValue;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el.dao.scroll.GPScrollElasticSearchCallback.DEFAULT_SCROOL_CALBACK;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
class ScrollElasticSearchConfig<P extends GPPageableElasticSearchDAO.Page, D extends Document, R extends Object, E extends Exception, C extends GPElasticSearchCheck<R, Set<D>, E>> implements GPScrollElasticSearchConfig<P, D, R, E, C> {

    private final P page;
    private final TimeValue timeValue;
    private final Integer size;
    private final C checkFunction;
    private final GPScrollElasticSearchCallback scrollElasticSearchCallback;

    /**
     * @param thePage
     * @param theTimeValue
     * @param theSize
     * @param theCheckFunction
     * @param theScroolElasticSearchCallback
     */
    ScrollElasticSearchConfig(@Nonnull(when = NEVER) P thePage, @Nonnull(when = NEVER) TimeValue theTimeValue,
            @Nonnull(when = NEVER) Integer theSize, @Nonnull(when = NEVER) C theCheckFunction,
            @Nullable GPScrollElasticSearchCallback theScroolElasticSearchCallback) {
        checkArgument(thePage != null, "The Parameter page must not be null.");
        checkArgument(theTimeValue != null, "The Parameter timeValue must not be null.");
        checkArgument((theSize != null) && (theSize > 0), "The Parameter size must not be null and must be a positive number.");
        checkArgument(theCheckFunction != null, "The Parameter checkFunction must not be null.");
        this.page = thePage;
        this.timeValue = theTimeValue;
        this.size = theSize;
        this.checkFunction = theCheckFunction;
        this.scrollElasticSearchCallback = (theScroolElasticSearchCallback != null) ? theScroolElasticSearchCallback : DEFAULT_SCROOL_CALBACK;
    }

    /**
     * @return {@link P}
     */
    @Override
    public P toPage() {
        return this.page;
    }

    /**
     * @return {@link TimeValue}
     */
    @Override
    public TimeValue toTimeValue() {
        return this.timeValue;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer toSize() {
        return this.size;
    }

    /**
     * @return {@link C}
     */
    @Override
    public C toCheckFunction() {
        return this.checkFunction;
    }

    /**
     * @return {@link GPScrollElasticSearchCallback}
     */
    @Override
    public GPScrollElasticSearchCallback toScroolElasticSearchCallback() {
        return this.scrollElasticSearchCallback;
    }
}