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
package org.geosdi.geoplatform.gui.client.model.google;

import org.geosdi.geoplatform.gui.client.model.*;
import org.geosdi.geoplatform.gui.oxm.model.google.GPGoogleAddress;
import org.geosdi.geoplatform.gui.oxm.model.google.GPGoogleResult;
import org.geosdi.geoplatform.gui.oxm.model.google.enums.AddressElement;
import org.geosdi.geoplatform.gui.oxm.model.google.enums.ResponseStatus;

/**
 * @author giuseppe
 * 
 */
public class GoogleGeocodeBean extends GeocodingBean {

    /**
     *
     */
    private static final long serialVersionUID = 7021908143959354644L;
    //
    private String street_address = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String route = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String intersection = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String political = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String googleCountry = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String administrative_area_level_1 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String administrative_area_level_2 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String administrative_area_level_3 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String colloquial_area = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String locality = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String sublocality = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String neighborhood = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String premise = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String subpremise = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String postal_code = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String natural_feature = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String airport = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String park = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String point_of_interest = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    //
    // Optional elements
    private String post_box = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String street_number = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String floor = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String room = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();

    public GoogleGeocodeBean() {
    }

    public GoogleGeocodeBean(String description) {
        super.setDescription(description);
    }

    public GoogleGeocodeBean(GPGoogleResult result) {
        super.setDescription(result.getCompleteDescription());
        super.setLat(result.getGeometry().getLocation().getLat());
        super.setLon(result.getGeometry().getLocation().getLon());

        for (GPGoogleAddress address : result.getAddressList()) {
            setStreet_address(address);
            setRoute(address);
            setIntersection(address);
            setPolitical(address);
            setGoogleCountry(address);
            setAdministrative_area_level_1(address);
            setAdministrative_area_level_2(address);
            setAdministrative_area_level_3(address);
            setColloquial_area(address);
            setLocality(address);
            setSublocality(address);
            setNeighborhood(address);
            setPremise(address);
            setSubpremise(address);
            setPostal_code(address);
            setNatural_feature(address);
            setAirport(address);
            setPark(address);
            setPoint_of_interest(address);
            setPost_box(address);
            setStreet_number(address);
            setFloor(address);
            setRoom(address);
        }
        
        checkForUnvaluedProperties();
    }

    private void checkForUnvaluedProperties() {
        // Assign 'route' value to 'street_address'
        if ((this.street_address.equals(ResponseStatus.EnumResponseStatus.NO_DATA.getValue())) && 
            (!this.route.equals(ResponseStatus.EnumResponseStatus.NO_DATA.getValue()))) {
            setStreet_address(this.route);
        }
        
        // Assign 'locality' value to 'administrative_area_level_3'
        if ((this.administrative_area_level_3.equals(ResponseStatus.EnumResponseStatus.NO_DATA.getValue())) && 
            (!this.locality.equals(ResponseStatus.EnumResponseStatus.NO_DATA.getValue()))) {
            setAdministrative_area_level_3(this.locality);
        }
    }

    /**
     * @return the street_address
     */
    public String getStreet_address() {
        return street_address;
    }

    /**
     * @param street_address the street_address to set
     */
    public void setStreet_address(String street_address) {
        this.street_address = street_address;
        super.setAddress(street_address);
    }

    /**
     * @param street_address the street_address to set
     */
    private void setStreet_address(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.STREET_ADDRESS.getValue())) {
            this.street_address = address.getLongName();
            super.setAddress(address.getLongName());
        }
    }

    /**
     * @return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * @param route the route to set
     */
    private void setRoute(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.ROUTE.getValue())) {
            this.route = address.getLongName();
        }
    }

    /**
     * @return the intersection
     */
    public String getIntersection() {
        return intersection;
    }

    /**
     * @param intersection the intersection to set
     */
    public void setIntersection(String intersection) {
        this.intersection = intersection;
    }

    /**
     * @param intersection the intersection to set
     */
    private void setIntersection(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.INTERSECTION.getValue())) {
            this.intersection = address.getLongName();
        }
    }

    /**
     * @return the political
     */
    public String getPolitical() {
        return political;
    }

    /**
     * @param political the political to set
     */
    public void setPolitical(String political) {
        this.political = political;
    }

    /**
     * @param political the political to set
     */
    private void setPolitical(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.POLITICAL.getValue())) {
            this.political = address.getLongName();
        }
    }

    /**
     * @return the country
     */
    public String getGoogleCountry() {
        return googleCountry;
    }

    /**
     * @param country the country to set
     */
    public void setGoogleCountry(String country) {
        this.googleCountry = country;
    }

    /**
     * @param country the country to set
     */
    private void setGoogleCountry(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.COUNTRY.getValue())) {
            this.googleCountry = address.getLongName();
        }
    }

    /**
     * @return the administrative_area_level_1
     */
    public String getAdministrative_area_level_1() {
        return administrative_area_level_1;
    }

    /**
     * @param administrative_area_level_1 the administrative_area_level_1 to set
     */
    public void setAdministrative_area_level_1(String administrative_area_level_1) {
        this.administrative_area_level_1 = administrative_area_level_1;
        super.setCountry(administrative_area_level_1);
    }

    /**
     * @param administrative_area_level_1 the administrative_area_level_1 to set
     */
    private void setAdministrative_area_level_1(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.ADMINISTRATIVE_AREA_LEVEL_1.getValue())) {
            this.administrative_area_level_1 = address.getLongName();
            super.setCountry(address.getLongName());
        }
    }

    /**
     * @return the administrative_area_level_2
     */
    public String getAdministrative_area_level_2() {
        return administrative_area_level_2;
    }

    /**
     * @param administrative_area_level_2 the administrative_area_level_2 to set
     */
    public void setAdministrative_area_level_2(String administrative_area_level_2) {
        this.administrative_area_level_2 = administrative_area_level_2;
        super.setDistrict(administrative_area_level_2);
    }

    /**
     * @param administrative_area_level_2 the administrative_area_level_2 to set
     */
    private void setAdministrative_area_level_2(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.ADMINISTRATIVE_AREA_LEVEL_2.getValue())) {
            this.administrative_area_level_2 = address.getLongName();
            super.setDistrict(address.getLongName());
        }
    }

    /**
     * @return the administrative_area_level_3
     */
    public String getAdministrative_area_level_3() {
        return administrative_area_level_3;
    }

    /**
     * @param administrative_area_level_3 the administrative_area_level_3 to set
     */
    public void setAdministrative_area_level_3(String administrative_area_level_3) {
        this.administrative_area_level_3 = administrative_area_level_3;
        super.setCity(administrative_area_level_3);
    }

    /**
     * @param administrative_area_level_3 the administrative_area_level_3 to set
     */
    private void setAdministrative_area_level_3(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.ADMINISTRATIVE_AREA_LEVEL_3.getValue())) {
            this.administrative_area_level_3 = address.getLongName();
            super.setCity(address.getLongName());
        }
    }

    /**
     * @return the colloquial_area
     */
    public String getColloquial_area() {
        return colloquial_area;
    }

    /**
     * @param colloquial_area the colloquial_area to set
     */
    public void setColloquial_area(String colloquial_area) {
        this.colloquial_area = colloquial_area;
    }

    /**
     * @param colloquial_area the colloquial_area to set
     */
    private void setColloquial_area(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.COLLOQUIAL_AREA.getValue())) {
            this.colloquial_area = address.getLongName();
        }
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @param locality the locality to set
     */
    private void setLocality(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.LOCALITY.getValue())) {
            this.locality = address.getLongName();
        }
    }

    /**
     * @return the sublocality
     */
    public String getSublocality() {
        return sublocality;
    }

    /**
     * @param sublocality the sublocality to set
     */
    public void setSublocality(String sublocality) {
        this.sublocality = sublocality;
    }

    /**
     * @param sublocality the sublocality to set
     */
    private void setSublocality(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.SUBLOCALITY.getValue())) {
            this.sublocality = address.getLongName();
        }
    }

    /**
     * @return the neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    private void setNeighborhood(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.NEIGHBORHOOD.getValue())) {
            this.neighborhood = address.getLongName();
        }
    }

    /**
     * @return the premise
     */
    public String getPremise() {
        return premise;
    }

    /**
     * @param premise the premise to set
     */
    public void setPremise(String premise) {
        this.premise = premise;
    }

    /**
     * @param premise the premise to set
     */
    private void setPremise(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.PREMISE.getValue())) {
            this.premise = address.getLongName();
        }
    }

    /**
     * @return the subpremise
     */
    public String getSubpremise() {
        return subpremise;
    }

    /**
     * @param subpremise the subpremise to set
     */
    public void setSubpremise(String subpremise) {
        this.subpremise = subpremise;
    }

    /**
     * @param subpremise the subpremise to set
     */
    private void setSubpremise(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.SUBPREMISE.getValue())) {
            this.subpremise = address.getLongName();
        }
    }

    /**
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    private void setPostal_code(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.POSTAL_CODE.getValue())) {
            this.postal_code = address.getLongName();
            super.setPostalCode(address.getLongName());
        }
    }

    /**
     * @return the natural_feature
     */
    public String getNatural_feature() {
        return natural_feature;
    }

    /**
     * @param natural_feature the natural_feature to set
     */
    public void setNatural_feature(String natural_feature) {
        this.natural_feature = natural_feature;
    }

    /**
     * @param natural_feature the natural_feature to set
     */
    private void setNatural_feature(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.NATURAL_FEATURE.getValue())) {
            this.natural_feature = address.getLongName();
        }
    }

    /**
     * @return the airport
     */
    public String getAirport() {
        return airport;
    }

    /**
     * @param airport the airport to set
     */
    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     * @param airport the airport to set
     */
    private void setAirport(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.AIRPORT.getValue())) {
            this.airport = address.getLongName();
        }
    }

    /**
     * @return the park
     */
    public String getPark() {
        return park;
    }

    /**
     * @param park the park to set
     */
    public void setPark(String park) {
        this.park = park;
    }

    /**
     * @param park the park to set
     */
    private void setPark(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.PARK.getValue())) {
            this.park = address.getLongName();
        }
    }

    /**
     * @return the point_of_interest
     */
    public String getPoint_of_interest() {
        return point_of_interest;
    }

    /**
     * @param point_of_interest the point_of_interest to set
     */
    public void setPoint_of_interest(String point_of_interest) {
        this.point_of_interest = point_of_interest;
    }

    /**
     * @param point_of_interest the point_of_interest to set
     */
    private void setPoint_of_interest(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.POINT_OF_INTEREST.getValue())) {
            this.point_of_interest = address.getLongName();
        }
    }

    /**
     * @return the post_box
     */
    public String getPost_box() {
        return post_box;
    }

    /**
     * @param post_box the post_box to set
     */
    public void setPost_box(String post_box) {
        this.post_box = post_box;
    }

    /**
     * @param post_box the post_box to set
     */
    private void setPost_box(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.POST_BOX.getValue())) {
            this.post_box = address.getLongName();
        }
    }

    /**
     * @return the street_number
     */
    public String getStreet_number() {
        return street_number;
    }

    /**
     * @param street_number the street_number to set
     */
    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    /**
     * @param street_number the street_number to set
     */
    private void setStreet_number(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.STREET_NUMBER.getValue())) {
            this.street_number = address.getLongName();
            super.setStreetNumber(address.getLongName());
        }
    }

    /**
     * @return the floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @param floor the floor to set
     */
    private void setFloor(GPGoogleAddress address) {
        if (address.getTypesList().get(0).equals(AddressElement.EnumAddressElement.FLOOR.getValue())) {
            this.floor = address.getLongName();
        }
    }

    /**
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @param room the room to set
     */
    private void setRoom(GPGoogleAddress address) {
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GoogleGeocodingBean{" + "street_address=" + street_address
                + ", route=" + route + ", intersection=" + intersection
                + ", political=" + political + ", country=" + googleCountry
                + ", administrative_area_level_1=" + administrative_area_level_1
                + ", administrative_area_level_2=" + administrative_area_level_2
                + ", administrative_area_level_3=" + administrative_area_level_3
                + ", colloquial_area=" + colloquial_area + ", locality=" + locality
                + ", sublocality=" + sublocality + ", neighborhood=" + neighborhood
                + ", premise=" + premise + ", subpremise=" + subpremise
                + ", postal_code=" + postal_code + ", natural_feature=" + natural_feature
                + ", airport=" + airport + ", park=" + park
                + ", point_of_interest=" + point_of_interest + ", post_box=" + post_box
                + ", street_number=" + street_number + ", floor=" + floor + ", room=" + room + '}';
    }
}
