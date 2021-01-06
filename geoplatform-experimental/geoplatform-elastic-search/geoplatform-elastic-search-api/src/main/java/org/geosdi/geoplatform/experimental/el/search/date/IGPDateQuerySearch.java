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
package org.geosdi.geoplatform.experimental.el.search.date;

import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPDateQuerySearch extends IBooleanSearch {

    /**
     * @return {@link DateTime}
     */
    DateTime getDateTo();

    /**
     * @return {@link DateTime}
     */
    DateTime getDateFrom();

    @Getter
    @Immutable
    class GPDateQuerySearch extends AbstractBooleanSearch implements IGPDateQuerySearch {

        private final DateTime dateTo;
        private final DateTime dateFrom;

        /**
         * @param theField
         * @param theBooleanQueryType
         * @param theDateFrom
         * @param theDateTo
         */
        public GPDateQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theBooleanQueryType,
                @Nonnull(when = NEVER) DateTime theDateFrom, @Nonnull(when = NEVER) DateTime theDateTo) {
            super(theField, theBooleanQueryType);
            checkArgument((theDateTo != null), "The Parameter DateTo must not be null.");
            checkArgument((theDateFrom != null), "The Parameter DateFrom must not be null.");
            checkArgument((theDateFrom.isBefore(theDateTo)), "The Parameter DateFrom must be after DateTo.");
            this.dateTo = theDateTo;
            this.dateFrom = theDateFrom;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return rangeQuery(field).gte(dateFrom).lte(dateTo);
        }
    }
}