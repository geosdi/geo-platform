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
package org.geosdi.geoplatform.experimental.el9.search.range;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Ricerca dei documenti con un numero compreso tra due estremi, inclusi (query range).
 * <p>I nomi vengono dal modulo per ElasticSearch 7: {@code greatherValue} è l'estremo inferiore e
 * {@code lessValue} quello superiore.</p>
 *
 * @param <LESS>     il tipo dell'estremo superiore
 * @param <GREATHER> il tipo dell'estremo inferiore
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPNumberRangeQuerySearch<LESS extends Number, GREATHER extends Number> extends IBooleanSearch {

    /**
     * @return {@link LESS} l'estremo superiore, incluso
     */
    LESS getLessValue();

    /**
     * @return {@link GREATHER} l'estremo inferiore, incluso
     */
    GREATHER getGreatherValue();

    /**
     * Gli estremi vanno a ElasticSearch come numeri JSON così come sono, senza passare da double: un long oltre
     * 2^53 resta esatto.
     */
    @Getter
    @Immutable
    class GPNumberRangeQuerySearch extends AbstractBooleanSearch implements IGPNumberRangeQuerySearch<Number, Number> {

        private final Number lessValue;
        private final Number greatherValue;

        /**
         * @param theField            il campo numerico
         * @param theBooleanQueryType la parte della query bool
         * @param theLessValue        l'estremo superiore, incluso
         * @param theGreatherValue    l'estremo inferiore, incluso, minore di theLessValue
         */
        public GPNumberRangeQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theBooleanQueryType,
                @Nonnull(when = NEVER) Number theLessValue, @Nonnull(when = NEVER) Number theGreatherValue) {
            super(theField, theBooleanQueryType);
            checkArgument(theLessValue != null, "Il parametro lessValue non deve essere null.");
            checkArgument(theGreatherValue != null, "Il parametro greatherValue non deve essere null.");
            checkArgument(theGreatherValue.doubleValue() < theLessValue.doubleValue(), "Il parametro greatherValue deve essere minore di lessValue.");
            this.lessValue = theLessValue;
            this.greatherValue = theGreatherValue;
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            return Query.of(q -> q.range(r -> r.untyped(u -> u.field(this.field)
                    .gte(JsonData.of(this.greatherValue))
                    .lte(JsonData.of(this.lessValue)))));
        }
    }
}
