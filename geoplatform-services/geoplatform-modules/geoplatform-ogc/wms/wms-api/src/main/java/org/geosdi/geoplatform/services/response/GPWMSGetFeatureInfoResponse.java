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
package org.geosdi.geoplatform.services.response;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoResponse extends Serializable {

    /**
     * @return {@link List<Object>}
     */
    List<Object> getFeatures();

    /**
     * @param theFeature
     */
    void addFeature(@Nonnull(when = NEVER) Object theFeature);

    interface GPWMSGetFeatureInfoObjectResponse extends Serializable {

        /**
         * @return {@link Object}
         */
        Object getResponse();

        /**
         * @return {@link String}
         */
        String getLayerName();

        /**
         * @param theResponse
         * @param theLayerName
         * @return {@link GPWMSGetFeatureInfoObjectResponse}
         */
        static GPWMSGetFeatureInfoObjectResponse toResponse(Object theResponse, String theLayerName) {
            return new WMSGetFeatureInfoObjectResponse(theResponse, theLayerName);
        }

        @ToString
        @Getter
        @Immutable
        @XmlAccessorType(FIELD)
        class WMSGetFeatureInfoObjectResponse implements GPWMSGetFeatureInfoObjectResponse {

            private static final long serialVersionUID = -985006208177418887L;
            //
            private final Object response;
            private final String layerName;

            /**
             * @param theResponse
             * @param theLayerName
             */
            WMSGetFeatureInfoObjectResponse(Object theResponse, String theLayerName) {
                this.response = theResponse;
                this.layerName = theLayerName;
            }
        }
    }
}