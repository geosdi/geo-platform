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

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.MultiFieldPageBuilder;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.SortablePage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Pagina con più gruppi di ricerche: ogni gruppo è una query bool, messa nella bool esterna secondo il suo tipo.
 * Serve per esempio per (A e B) oppure (C e D).
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCoupleMultiFieldSearch extends MultiFieldPageBuilder {

    /**
     * Implementazione di {@link GPCoupleMultiFieldSearch}.
     */
    @Getter
    @ToString(callSuper = true)
    @Immutable
    class CoupleMultiFieldSearch extends SortablePage implements GPCoupleMultiFieldSearch {

        private final List<GPCoupleMultiFieldPageBuilder> multiFieldPageBuilders;

        /**
         * @param theMultiFieldPageBuilders i gruppi, almeno uno
         */
        public CoupleMultiFieldSearch(@Nonnull(when = NEVER) List<GPCoupleMultiFieldPageBuilder> theMultiFieldPageBuilders) {
            this(null, null, 0, 0, theMultiFieldPageBuilders);
        }

        /**
         * @param theFrom                   il primo documento, da zero
         * @param theSize                   quanti documenti; zero vuol dire nessuna paginazione
         * @param theMultiFieldPageBuilders i gruppi, almeno uno
         */
        public CoupleMultiFieldSearch(int theFrom, int theSize, @Nonnull(when = NEVER) List<GPCoupleMultiFieldPageBuilder> theMultiFieldPageBuilders) {
            this(null, null, theFrom, theSize, theMultiFieldPageBuilders);
        }

        /**
         * @param theField                  il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder              il verso; null vuol dire nessun ordinamento
         * @param theFrom                   il primo documento, da zero
         * @param theSize                   quanti documenti; zero vuol dire nessuna paginazione
         * @param theMultiFieldPageBuilders i gruppi, almeno uno non null
         */
        public CoupleMultiFieldSearch(@Nullable String theField, @Nullable SortOrder theSortOrder, int theFrom, int theSize,
                @Nonnull(when = NEVER) List<GPCoupleMultiFieldPageBuilder> theMultiFieldPageBuilders) {
            super(theField, theSortOrder, theFrom, theSize);
            checkArgument((theMultiFieldPageBuilders != null) && !(theMultiFieldPageBuilders.isEmpty()),
                    "Il parametro multiFieldPageBuilders non deve essere null né vuoto.");
            this.multiFieldPageBuilders = theMultiFieldPageBuilders.stream().filter(Objects::nonNull).toList();
            checkArgument(!this.multiFieldPageBuilders.isEmpty(), "Il parametro multiFieldPageBuilders deve contenere almeno un gruppo non null.");
        }

        /**
         * @return {@link BoolQuery}
         */
        @Override
        public BoolQuery toBoolQuery() {
            BoolQuery.Builder builder = new BoolQuery.Builder();
            this.multiFieldPageBuilders.forEach(group -> MultiFieldPageBuilder.addClause(builder, group.getType(), group.toBoolQuery()._toQuery()));
            return builder.build();
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query toQuery() {
            return this.toBoolQuery()._toQuery();
        }
    }
}
