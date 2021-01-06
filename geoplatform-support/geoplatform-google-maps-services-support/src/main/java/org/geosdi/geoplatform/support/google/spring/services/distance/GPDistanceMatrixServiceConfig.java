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
package org.geosdi.geoplatform.support.google.spring.services.distance;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPDistanceMatrixServiceConfig {

    /**
     * @param gpGeoApiContext
     * @return {@link GPDistanceMatrixService}
     */
    @Bean
    public GPDistanceMatrixService gpDistanceMatrixService(@Qualifier(value = "gpGeoApiContext") GeoApiContext gpGeoApiContext) {
        return new BasicDistanceMatrixService(gpGeoApiContext);
    }

    /**
     *
     */
    static class BasicDistanceMatrixService implements GPDistanceMatrixService {

        private final GeoApiContext geoApiContext;

        /**
         * @param theGeoApiContext
         */
        BasicDistanceMatrixService(@Nonnull(when = NEVER) GeoApiContext theGeoApiContext) {
            checkArgument(theGeoApiContext != null, "The Parameter geoApiContext must not be null.");
            this.geoApiContext = theGeoApiContext;
        }

        /**
         * @return {@link DistanceMatrixApiRequest}
         */
        @Override
        public DistanceMatrixApiRequest newRequest() {
            return DistanceMatrixApi.newRequest(this.geoApiContext);
        }

        /**
         * @param origins
         * @param destinations
         * @return {@link DistanceMatrixApiRequest}
         */
        @Override
        public DistanceMatrixApiRequest getDistanceMatrix(String[] origins, String[] destinations) {
            return DistanceMatrixApi.getDistanceMatrix(this.geoApiContext, origins, destinations);
        }

        /**
         * <p>Calulate Distance between to Points</p>
         *
         * @param lat1
         * @param lon1
         * @param lat2
         * @param lon2
         * @param unit
         * @return {@link Double}
         */
        @Override
        public Double distance(double lat1, double lon1, double lat2, double lon2, Unit unit) {
            double theta = lon1 - lon2;
            double distance = sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta));
            distance = Math.acos(distance);
            distance = rad2deg(distance);
            distance = distance * 60 * 1.1515;
            switch (unit) {
                case K:
                    distance = distance * 1.609344;
                    break;
                case N:
                    distance = distance * 0.8684;
                    break;
            }
            return distance;
        }

        // This function converts decimal degrees to radians
        private static double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
        }

        // This function converts radians to decimal degrees
        private static double rad2deg(double rad) {
            return (rad * 180 / Math.PI);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "   geoApiContext = " + geoApiContext +
                    '}';
        }
    }
}