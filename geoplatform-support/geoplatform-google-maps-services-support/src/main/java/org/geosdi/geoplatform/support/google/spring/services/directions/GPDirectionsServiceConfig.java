/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.google.spring.services.directions;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPDirectionsServiceConfig {

    /**
     * @param gpGeoApiContext
     * @return {@link GPDirectionsService}
     */
    @Bean
    public GPDirectionsService gpDirectionsService(@Qualifier(value = "gpGeoApiContext") GeoApiContext gpGeoApiContext) {
        return new BasicDirectionsService(gpGeoApiContext);
    }

    static class BasicDirectionsService implements GPDirectionsService {

        private final GeoApiContext geoApiContext;

        /**
         * @param theGeoApiContext
         */
        BasicDirectionsService(@Nonnull(when = NEVER) GeoApiContext theGeoApiContext) {
            checkArgument(theGeoApiContext != null, "The Parameter geoApiContext must not be null.");
            this.geoApiContext = theGeoApiContext;
        }

        /**
         * @return {@link DirectionsApiRequest}
         */
        @Override
        public DirectionsApiRequest newRequest() {
            return DirectionsApi.newRequest(geoApiContext);
        }

        /**
         * @param origin
         * @param destination
         * @return {@link DirectionsApiRequest}
         */
        @Override
        public DirectionsApiRequest getDirections(String origin, String destination) {
            return DirectionsApi.getDirections(geoApiContext, origin, destination);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + "geoApiContext = " + geoApiContext + '}';
        }
    }
}