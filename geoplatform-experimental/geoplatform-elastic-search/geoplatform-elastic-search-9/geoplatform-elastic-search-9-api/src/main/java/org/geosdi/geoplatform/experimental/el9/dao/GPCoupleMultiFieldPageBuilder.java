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
package org.geosdi.geoplatform.experimental.el9.dao;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Gruppo di ricerche ({@link MultiFieldsSearch}) da mettere come una clausola sola dentro una
 * {@link GPCoupleMultiFieldSearch}, nella parte della bool indicata dal tipo.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCoupleMultiFieldPageBuilder {

    /**
     * @return {@link BoolQuery} la query bool del gruppo
     */
    BoolQuery toBoolQuery();

    /**
     * @return {@link BooleanQueryType} la parte della bool esterna in cui va il gruppo
     */
    BooleanQueryType getType();

    /**
     * @param theMultiFieldPageBuilder il gruppo di ricerche
     * @param theType                  la parte della bool esterna
     * @return {@link GPCoupleMultiFieldPageBuilder}
     */
    static GPCoupleMultiFieldPageBuilder of(@Nonnull(when = NEVER) MultiFieldsSearch theMultiFieldPageBuilder,
            @Nonnull(when = NEVER) BooleanQueryType theType) {
        checkArgument(theMultiFieldPageBuilder != null, "Il parametro multiFieldPageBuilder non deve essere null.");
        checkArgument(theType != null, "Il parametro type non deve essere null.");
        return new CoupleMultiFieldPageBuilder(theMultiFieldPageBuilder, theType);
    }

    /**
     * Implementazione di {@link GPCoupleMultiFieldPageBuilder}.
     */
    @ToString
    @Immutable
    class CoupleMultiFieldPageBuilder implements GPCoupleMultiFieldPageBuilder {

        private final MultiFieldsSearch multiFieldPageBuilder;
        @Getter
        private final BooleanQueryType type;

        /**
         * @param theMultiFieldPageBuilder il gruppo di ricerche
         * @param theType                  la parte della bool esterna
         */
        CoupleMultiFieldPageBuilder(MultiFieldsSearch theMultiFieldPageBuilder, BooleanQueryType theType) {
            this.multiFieldPageBuilder = theMultiFieldPageBuilder;
            this.type = theType;
        }

        /**
         * @return {@link BoolQuery}
         */
        @Override
        public BoolQuery toBoolQuery() {
            return this.multiFieldPageBuilder.toBoolQuery();
        }
    }
}
