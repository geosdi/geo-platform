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
package org.geosdi.geoplatform.experimental.el.dao;

import lombok.Getter;
import lombok.ToString;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCoupleMultiFieldPageBuilder extends GPPageableElasticSearchDAO.PageBuilder {

    /**
     * @return {@link BoolQueryBuilder}
     */
    BoolQueryBuilder boolQueryBuilder();

    /**
     * @return {@link BooleanQueryType}
     */
    BooleanQueryType getType();

    /**
     * @param theMultiFieldPageBuilder
     * @param theType
     * @return {@link GPCoupleMultiFieldPageBuilder}
     */
    static GPCoupleMultiFieldPageBuilder of(@Nonnull(when = NEVER) MultiFieldsSearch theMultiFieldPageBuilder, @Nonnull(when = NEVER) BooleanQueryType theType) {
        checkArgument(theMultiFieldPageBuilder != null, "The Parameter multiFieldPageBuilder must not be null.");
        checkArgument(theType != null, "The Parameter type must not be null");
        return new CoupleMultiFieldPageBuilder(theMultiFieldPageBuilder, theType);
    }

    @ToString
    class CoupleMultiFieldPageBuilder implements GPCoupleMultiFieldPageBuilder {

        private final MultiFieldsSearch multiFieldPageBuilder;
        @Getter
        private final BooleanQueryType type;

        /**
         * @param theMultiFieldPageBuilder
         * @param theType
         */
        CoupleMultiFieldPageBuilder(MultiFieldsSearch theMultiFieldPageBuilder, BooleanQueryType theType) {
            this.multiFieldPageBuilder = theMultiFieldPageBuilder;
            this.type = theType;
        }

        /**
         * @param builder
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return this.multiFieldPageBuilder.buildPage(builder);
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return this.multiFieldPageBuilder.buildPage(builder);
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.multiFieldPageBuilder.boolQueryBuilder();
        }
    }
}