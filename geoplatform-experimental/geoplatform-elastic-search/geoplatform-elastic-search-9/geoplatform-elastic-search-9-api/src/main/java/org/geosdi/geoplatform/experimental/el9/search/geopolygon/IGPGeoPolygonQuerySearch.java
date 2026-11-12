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
package org.geosdi.geoplatform.experimental.el9.search.geopolygon;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el9.search.geoshape.IGPGeoShapeQuerySearch.GPGeoShapeQuerySearch;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.FILTER;
import static org.geosdi.geoplatform.experimental.el9.search.geoshape.IGPGeoShapeQuerySearch.GPShapeRelation.INTERSECTS;

/**
 * Ricerca dei punti dentro un poligono, sempre in FILTER.
 * <p>ElasticSearch 8 ha tolto la query geo_polygon: qui il poligono diventa una query geo_shape con relazione
 * INTERSECTS, che su un campo geo_point trova gli stessi punti.</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoPolygonQuerySearch extends IBooleanSearch {

    /**
     * @return {@link List} i vertici, con x longitudine e y latitudine
     */
    List<Coordinate> getPoints();

    /**
     * Implementazione di {@link IGPGeoPolygonQuerySearch}.
     */
    @Getter
    @Immutable
    class GPGeoPolygonQuerySearch extends AbstractBooleanSearch implements IGPGeoPolygonQuerySearch {

        private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
        //
        private final List<Coordinate> points;

        /**
         * @param theField  il campo geo_point
         * @param thePoints i vertici, almeno tre, con x longitudine e y latitudine; l'anello si chiude da solo
         */
        public GPGeoPolygonQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) List<Coordinate> thePoints) {
            super(theField, FILTER);
            checkArgument((thePoints != null) && (thePoints.size() >= 3), "Il parametro points non deve essere null e deve avere almeno tre vertici.");
            this.points = List.copyOf(thePoints);
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            return new GPGeoShapeQuerySearch(this.field, INTERSECTS, this.toPolygon()).buildQuery();
        }

        /**
         * @return {@link Polygon} il poligono dei vertici, con l'anello chiuso
         */
        private Polygon toPolygon() {
            List<Coordinate> ring = new ArrayList<>(this.points);
            if (!ring.get(0).equals2D(ring.get(ring.size() - 1))) {
                ring.add(ring.get(0));
            }
            return GEOMETRY_FACTORY.createPolygon(ring.toArray(Coordinate[]::new));
        }
    }
}
