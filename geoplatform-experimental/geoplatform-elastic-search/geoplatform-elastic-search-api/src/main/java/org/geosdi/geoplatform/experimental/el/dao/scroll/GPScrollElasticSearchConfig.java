package org.geosdi.geoplatform.experimental.el.dao.scroll;

import org.elasticsearch.core.TimeValue;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.Page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPScrollElasticSearchConfig<P extends Page, D extends Document, R extends Object, E extends Exception, C extends GPElasticSearchCheck<R, Set<D>, E>> {

    /**
     * @return {@link P}
     */
    P toPage();

    /**
     * @return {@link TimeValue}
     */
    TimeValue toTimeValue();

    /**
     * @return {@link Integer}
     */
    Integer toSize();

    /**
     * @return {@link C}
     */
    C toCheckFunction();

    /**
     * @return {@link GPScrollElasticSearchCallback}
     */
    GPScrollElasticSearchCallback toScroolElasticSearchCallback();

    /**
     * @param thePage
     * @param theTimeValue
     * @param theSize
     * @param theCheckFunction
     * @param theScroolElasticSearchCallback
     * @param <P>
     * @param <D>
     * @param <R>
     * @param <E>
     * @param <C>
     * @return {@link GPScrollElasticSearchConfig}
     * @throws Exception
     */
    static <P extends Page, D extends Document, R extends Object, E extends Exception, C extends GPElasticSearchCheck<R, Set<D>, E>> GPScrollElasticSearchConfig<P, D, R, E, C> toScrollConfig(@Nonnull(when = NEVER) P thePage,
            @Nonnull(when = NEVER) TimeValue theTimeValue, @Nonnull(when = NEVER) Integer theSize, @Nonnull(when = NEVER) C theCheckFunction,
            @Nullable GPScrollElasticSearchCallback theScroolElasticSearchCallback) throws Exception {
        return new ScrollElasticSearchConfig<>(thePage, theTimeValue, theSize, theCheckFunction, theScroolElasticSearchCallback);
    }
}