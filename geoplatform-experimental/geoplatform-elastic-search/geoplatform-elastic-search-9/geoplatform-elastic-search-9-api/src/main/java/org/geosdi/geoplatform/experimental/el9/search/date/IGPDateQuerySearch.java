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
package org.geosdi.geoplatform.experimental.el9.search.date;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;
import java.time.ZonedDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static javax.annotation.meta.When.NEVER;

/**
 * Ricerca dei documenti con una data compresa tra due estremi, inclusi (query range sulle date).
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPDateQuerySearch extends IBooleanSearch {

    /**
     * @return {@link ZonedDateTime} l'estremo superiore, incluso
     */
    ZonedDateTime getDateTo();

    /**
     * @return {@link ZonedDateTime} l'estremo inferiore, incluso
     */
    ZonedDateTime getDateFrom();

    /**
     * Le date vanno a ElasticSearch in ISO 8601 con lo scostamento dall'UTC, come le scriveva il modulo per
     * ElasticSearch 7 con Joda-Time, così il fuso orario non si perde.
     */
    @Getter
    @Immutable
    class GPDateQuerySearch extends AbstractBooleanSearch implements IGPDateQuerySearch {

        private final ZonedDateTime dateTo;
        private final ZonedDateTime dateFrom;

        /**
         * @param theField            il campo con la data
         * @param theBooleanQueryType la parte della query bool
         * @param theDateFrom         l'estremo inferiore, incluso
         * @param theDateTo           l'estremo superiore, incluso, dopo theDateFrom
         */
        public GPDateQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theBooleanQueryType,
                @Nonnull(when = NEVER) ZonedDateTime theDateFrom, @Nonnull(when = NEVER) ZonedDateTime theDateTo) {
            super(theField, theBooleanQueryType);
            checkArgument(theDateFrom != null, "Il parametro dateFrom non deve essere null.");
            checkArgument(theDateTo != null, "Il parametro dateTo non deve essere null.");
            checkArgument(theDateFrom.isBefore(theDateTo), "Il parametro dateFrom deve essere prima di dateTo.");
            this.dateFrom = theDateFrom;
            this.dateTo = theDateTo;
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            return Query.of(q -> q.range(r -> r.date(d -> d.field(this.field)
                    .gte(ISO_OFFSET_DATE_TIME.format(this.dateFrom))
                    .lte(ISO_OFFSET_DATE_TIME.format(this.dateTo)))));
        }
    }
}
