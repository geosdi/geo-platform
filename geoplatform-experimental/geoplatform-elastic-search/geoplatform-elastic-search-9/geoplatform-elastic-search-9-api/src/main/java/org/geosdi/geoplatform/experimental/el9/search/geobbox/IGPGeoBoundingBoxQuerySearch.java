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
package org.geosdi.geoplatform.experimental.el9.search.geobbox;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.locationtech.jts.geom.Envelope;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.FILTER;

/**
 * Ricerca dei punti dentro un rettangolo (query geo_bounding_box), sempre in FILTER.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoBoundingBoxQuerySearch extends IBooleanSearch {

    /**
     * @return {@link Envelope} il rettangolo, con x longitudine e y latitudine
     */
    Envelope getEnvelope();

    /**
     * Implementazione di {@link IGPGeoBoundingBoxQuerySearch}.
     */
    @Immutable
    @Getter
    class GPGeoBoundingBoxQuerySearch extends AbstractBooleanSearch implements IGPGeoBoundingBoxQuerySearch {

        private final Envelope envelope;

        /**
         * @param theField    il campo geo_point
         * @param theEnvelope il rettangolo, con x longitudine e y latitudine
         */
        public GPGeoBoundingBoxQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Envelope theEnvelope) {
            super(theField, FILTER);
            checkArgument(theEnvelope != null, "Il parametro envelope non deve essere null.");
            this.envelope = theEnvelope;
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            return Query.of(q -> q.geoBoundingBox(g -> g.field(this.field)
                    .boundingBox(b -> b.tlbr(t -> t
                            .topLeft(l -> l.latlon(ll -> ll.lat(this.envelope.getMaxY()).lon(this.envelope.getMinX())))
                            .bottomRight(l -> l.latlon(ll -> ll.lat(this.envelope.getMinY()).lon(this.envelope.getMaxX())))))));
        }
    }
}
