/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.search.geoshape;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.elasticsearch.common.geo.builders.ShapeBuilder;
import org.elasticsearch.index.query.GeoShapeQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoShapeQuerySearch extends IBooleanSearch {

    /**
     * @return {@link GPShapeRelation}
     */
    GPShapeRelation getShapeRelation();

    /**
     * @return {@link ShapeBuilder}
     */
    ShapeBuilder getShapeBuilder();

    /**
     *
     */
    enum GPShapeRelation {
        INTERSECTS, DISJOINT, WITHIN, CONTAINS;
    }

    /**
     *
     */
    @Immutable
    class GPGeoShapeQuerySearch extends AbstractBooleanSearch implements IGPGeoShapeQuerySearch {

        private final GPShapeRelation shapeRelation;
        private final ShapeBuilder shapeBuilder;

        /**
         * @param theField
         * @param theType
         * @param theShapeRelation
         * @param theShapeBuilder
         */
        public GPGeoShapeQuerySearch(String theField, BooleanQueryType theType, GPShapeRelation theShapeRelation, ShapeBuilder theShapeBuilder) {
            super(theField, theType);
            checkArgument((theShapeRelation != null), "The Parameter ShapeRelations must not be null.");
            checkArgument((theShapeBuilder != null), "The Parameter ShapeBuilder must not be null.");
            this.shapeRelation = theShapeRelation;
            this.shapeBuilder = theShapeBuilder;
        }

        /**
         * @return {@link GPShapeRelation}
         */
        @Override
        public GPShapeRelation getShapeRelation() {
            return this.shapeRelation;
        }

        /**
         * @return {@link ShapeBuilder}
         */
        @Override
        public ShapeBuilder getShapeBuilder() {
            return this.shapeBuilder;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            try {
                return this.buildGeoShapeQueryBuilder();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }

        /**
         * @return {@link GeoShapeQueryBuilder}
         */
        protected final GeoShapeQueryBuilder buildGeoShapeQueryBuilder() throws IOException {
            switch (this.shapeRelation) {
                case INTERSECTS:
                    return QueryBuilders.geoIntersectionQuery(this.field, this.shapeBuilder);
                case DISJOINT:
                    return QueryBuilders.geoDisjointQuery(this.field, this.shapeBuilder);
                case WITHIN:
                    return QueryBuilders.geoWithinQuery(this.field, this.shapeBuilder);
                case CONTAINS:
                    return QueryBuilders.geoShapeQuery(this.field, getShapeBuilder());
            }
            return null;
        }
    }
}