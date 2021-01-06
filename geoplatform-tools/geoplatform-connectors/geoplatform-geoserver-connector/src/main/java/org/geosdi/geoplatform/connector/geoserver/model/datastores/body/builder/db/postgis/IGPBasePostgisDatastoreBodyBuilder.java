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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.GPDatastoreBodyBaseDatabaseBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.GPGeoserverConnectionPostGISValues.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPBasePostgisDatastoreBodyBuilder<Builder extends IGPBasePostgisDatastoreBodyBuilder<?>> extends GPDatastoreBodyBaseDatabaseBuilder<Builder> {

    /**
     * <p>The Schema to use. Default value is : public.</p>
     *
     * @param theSchema
     * @return {@link Builder}
     */
    Builder withSchema(@Nullable String theSchema);

    /**
     * <p>Perform only primary filter on bbox.</p>
     *
     * @param theLooseBbox
     * @return {@link Builder}
     */
    Builder withLooseBbox(@Nullable Boolean theLooseBbox);

    /**
     * <p>Use the spatial index information to quickly get an estimate of the data bounds.</p>
     *
     * @param theEstimatedExtends
     * @return {@link Builder}
     */
    Builder withEstimatedExtends(@Nullable Boolean theEstimatedExtends);

    /**
     * <p>Use prepared statements.</p>
     *
     * @param thePreparedStatements
     * @return {@link Builder}
     */
    Builder withPreparedStatements(@Nullable Boolean thePreparedStatements);

    /**
     * <p>Set to true to have a set of filter functions be translated directly in SQL. Due to differences in the type
     * systems the result might not be the same as evaluating them in memory, including the SQL failing with errors
     * while the in memory version works fine. However this allows to push more of the filter into the database,
     * increasing performance.the postgis table.
     * </p>
     *
     * @param theEncodeFunctions
     * @return {@link Builder}
     */
    Builder withEncodeFunctions(@Nullable Boolean theEncodeFunctions);

    /**
     * <p>Support on the fly geometry simplification</p>
     *
     * @param theSupportOnTheFlyGeometrySemplification
     * @return {@link Builder}
     */
    Builder withSupportOnTheFlyGeometrySemplification(@Nullable Boolean theSupportOnTheFlyGeometrySemplification);

    abstract class GPBasePostgisDatastoreBodyBuilder<Builder extends IGPBasePostgisDatastoreBodyBuilder<?>> extends DatastoreBodyBaseDatabaseBuilder<Builder> implements IGPBasePostgisDatastoreBodyBuilder<Builder> {

        /**
         * @param theType
         */
        protected GPBasePostgisDatastoreBodyBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            super(theType);
        }

        /**
         * <p>The Schema to use. Default value is : public.</p>
         *
         * @param theSchema
         * @return {@link Builder}
         */
        @Override
        public Builder withSchema(@Nullable String theSchema) {
            this.connectionParameters.compute(SCHEMA.getConnectionKey(), (k, v) -> ((theSchema != null) && !(theSchema.trim().isEmpty())) ? theSchema : v);
            return self();
        }

        /**
         * <p>Perform only primary filter on bbox.</p>
         *
         * @param theLooseBbox
         * @return {@link Builder}
         */
        @Override
        public Builder withLooseBbox(@Nullable Boolean theLooseBbox) {
            this.connectionParameters.compute(LOOSE_BBOX.getConnectionKey(), (k, v) -> (theLooseBbox != null) ? theLooseBbox.toString() : v);
            return self();
        }

        /**
         * <p>Use the spatial index information to quickly get an estimate of the data bounds.</p>
         *
         * @param theEstimatedExtends
         * @return {@link Builder}
         */
        @Override
        public Builder withEstimatedExtends(@Nullable Boolean theEstimatedExtends) {
            this.connectionParameters.compute(ESTIMATED_EXTENDS.getConnectionKey(), (k, v) -> (theEstimatedExtends != null) ? theEstimatedExtends.toString() : v);
            return self();
        }

        /**
         * <p>Use prepared statements.</p>
         *
         * @param thePreparedStatements
         * @return {@link Builder}
         */
        @Override
        public Builder withPreparedStatements(@Nullable Boolean thePreparedStatements) {
            this.connectionParameters.compute(PREPARED_STATEMENTS.getConnectionKey(), (k, v) -> (thePreparedStatements != null) ? thePreparedStatements.toString() : v);
            return self();
        }

        /**
         * <p>Set to true to have a set of filter functions be translated directly in SQL. Due to differences in the type
         * systems the result might not be the same as evaluating them in memory, including the SQL failing with errors
         * while the in memory version works fine. However this allows to push more of the filter into the database,
         * increasing performance.the postgis table.
         * </p>
         *
         * @param theEncodeFunctions
         * @return {@link Builder}
         */
        @Override
        public Builder withEncodeFunctions(@Nullable Boolean theEncodeFunctions) {
            this.connectionParameters.compute(ENCODE_FUNCTIONS.getConnectionKey(), (k, v) -> (theEncodeFunctions != null) ? theEncodeFunctions.toString() : v);
            return self();
        }

        /**
         * <p>Support on the fly geometry simplification</p>
         *
         * @param theSupportOnTheFlyGeometrySemplification
         * @return {@link Builder}
         */
        @Override
        public Builder withSupportOnTheFlyGeometrySemplification(@Nullable Boolean theSupportOnTheFlyGeometrySemplification) {
            this.connectionParameters.compute(SUPPORT_ON_THE_FLY_GEOMETRY_SEMPLIFICATION.getConnectionKey(), (k, v) -> (theSupportOnTheFlyGeometrySemplification != null) ? theSupportOnTheFlyGeometrySemplification.toString() : v);
            return self();
        }
    }
}