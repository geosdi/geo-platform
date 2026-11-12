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
package org.geosdi.geoplatform.experimental.el9.search.geoshape;

import co.elastic.clients.elasticsearch._types.GeoShapeRelation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.FILTER;

/**
 * Ricerca per relazione spaziale con una geometria (query geo_shape), sempre in FILTER. Vale sia per i campi
 * geo_shape sia per i geo_point.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoShapeQuerySearch extends IBooleanSearch {

    /**
     * @return {@link GPShapeRelation}
     */
    GPShapeRelation getShapeRelation();

    /**
     * @return {@link Geometry} la geometria, con x longitudine e y latitudine
     */
    Geometry getShape();

    /**
     * Le relazioni spaziali di ElasticSearch.
     */
    enum GPShapeRelation {
        INTERSECTS(GeoShapeRelation.Intersects),
        DISJOINT(GeoShapeRelation.Disjoint),
        WITHIN(GeoShapeRelation.Within),
        CONTAINS(GeoShapeRelation.Contains);

        private final GeoShapeRelation relation;

        /**
         * @param theRelation la relazione del client
         */
        GPShapeRelation(GeoShapeRelation theRelation) {
            this.relation = theRelation;
        }

        /**
         * @return {@link GeoShapeRelation} la relazione del client
         */
        public GeoShapeRelation toRelation() {
            return this.relation;
        }
    }

    /**
     * La geometria va a ElasticSearch in WKT, che ElasticSearch accetta come il GeoJSON: così non serve una
     * libreria GeoJSON.
     */
    @Getter
    @Immutable
    class GPGeoShapeQuerySearch extends AbstractBooleanSearch implements IGPGeoShapeQuerySearch {

        private final GPShapeRelation shapeRelation;
        private final Geometry shape;

        /**
         * @param theField         il campo geo_shape o geo_point
         * @param theShapeRelation la relazione spaziale
         * @param theShape         la geometria, con x longitudine e y latitudine
         */
        public GPGeoShapeQuerySearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) GPShapeRelation theShapeRelation,
                @Nonnull(when = NEVER) Geometry theShape) {
            super(theField, FILTER);
            checkArgument(theShapeRelation != null, "Il parametro shapeRelation non deve essere null.");
            checkArgument((theShape != null) && !(theShape.isEmpty()), "Il parametro shape non deve essere null né vuoto.");
            this.shapeRelation = theShapeRelation;
            this.shape = theShape;
        }

        /**
         * @return {@link Query}
         */
        @Override
        public Query buildQuery() {
            String wkt = new WKTWriter().write(this.shape);
            return Query.of(q -> q.geoShape(g -> g.field(this.field)
                    .shape(s -> s.shape(JsonData.of(wkt)).relation(this.shapeRelation.toRelation()))));
        }
    }
}
