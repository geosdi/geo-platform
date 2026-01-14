/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.dao.scroll;

import net.jcip.annotations.Immutable;
import org.elasticsearch.core.TimeValue;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el.dao.scroll.GPScrollElasticSearchCallback.DEFAULT_SCROOL_CALBACK;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
non-sealed class ScrollElasticSearchConfig<P extends GPPageableElasticSearchDAO.Page, D extends Document, R extends Object, E extends Exception, C extends GPElasticSearchCheck<R, List<D>, E>> implements GPScrollElasticSearchConfig<P, D, R, E, C> {

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