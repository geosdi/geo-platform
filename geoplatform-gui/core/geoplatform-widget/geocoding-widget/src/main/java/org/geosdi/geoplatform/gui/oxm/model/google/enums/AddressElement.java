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
package org.geosdi.geoplatform.gui.oxm.model.google.enums;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class AddressElement {

    public enum EnumAddressElement {

        STREET_ADDRESS("street_address"), // indicates a precise street address. 
        ROUTE("route"), // indicates a named route (such as "US 101").
        INTERSECTION("intersection"), // indicates a major intersection, usually of two major roads.
        POLITICAL("political"), // indicates a political entity. Usually, this type indicates a polygon of some civil administration.
        COUNTRY("country"), // indicates the national political entity, and is typically the highest order type returned by the Geocoder.
        ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"), //indicates a first-order civil entity below the country level. Within the United States, these administrative levels are states. Not all nations exhibit these administrative levels.
        ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"), // indicates a second-order civil entity below the country level. Within the United States, these administrative levels are counties. Not all nations exhibit these administrative levels.
        ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"), // indicates a third-order civil entity below the country level. This type indicates a minor civil division. Not all nations exhibit these administrative levels.
        COLLOQUIAL_AREA("colloquial_area"), // indicates a commonly-used alternative name for the entity.
        LOCALITY("locality"), // indicates an incorporated city or town political entity.
        SUBLOCALITY("sublocality"), // indicates an first-order civil entity below a locality
        NEIGHBORHOOD("neighborhood"), // indicates a named neighborhood
        PREMISE("premise"), // indicates a named location, usually a building or collection of buildings with a common name
        SUBPREMISE("subpremise"), // indicates a first-order entity below a named location, usually a singular building within a collection of buildings with a common name
        POSTAL_CODE("postal_code"), // indicates a postal code as used to address postal mail within the country.
        NATURAL_FEATURE("natural_feature"), // indicates a prominent natural feature.
        AIRPORT("airport"), // indicates an airport.
        PARK("park"), // indicates a named park.
        POINT_OF_INTEREST("point_of_interest"), // indicates a named point of interest. Typically, these "POI"s are prominent local entities that don't easily fit in another category such as "Empire State Building" or "Statue of Liberty."
        POST_BOX("post_box"), // indicates a specific postal box.
        STREET_NUMBER("street_number"), // indicates the precise street number.
        FLOOR("floor"), // indicates the floor of a building address.
        ROOM("room"); // indicates the room of a building address.

        private String value;

        EnumAddressElement(String value) {
            this.value = value;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }
}
