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
package org.geosdi.geoplatform.gui.client.model.yahoo;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.GPYahooResult;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.enums.ResponseStatus;

/**
*
* @author Michele Santomauro - CNR IMAA geoSDI Group
* @email michele.santomauro@geosdi.org
*/
public class YahooGeocodeBean extends GeocodingBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3142960730005278335L;
	//
	// See http://developer.yahoo.com/geo/placefinder/guide/responses.html for details
    private String quality = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private float latitude = 0.0F;
    private float longitude = 0.0F;
    private String offsetlat = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String offsetlon = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String radius = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String name = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String line1 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String line2 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String line3 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String line4 = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String house = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String street = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String xstreet = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String unittype = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String unit = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String postal = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String neighborhood = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String yahooCity = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String county = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String state = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String yahooCountry = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String countrycode = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String statecode = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String countycode = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String uzip = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String hash = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String woeid = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();
    private String woetype = ResponseStatus.EnumResponseStatus.NO_DATA.getValue();

    public YahooGeocodeBean() {
    }

    public YahooGeocodeBean(String description) {
        super.setDescription(description);
    }

    public YahooGeocodeBean(GPYahooResult result) {
    	super.setDescription(result.getLine1() + " " + result.getLine2() + " " +
                             result.getLine3() + " " + result.getLine4());
        super.setLat(result.getLatitude());
        super.setLon(result.getLongitude());
        //
        super.setPostalCode(result.getPostal());
        super.setCountry(result.getState());
        super.setDistrict(result.getCounty());
        super.setCity(result.getCity());
        super.setAddress(result.getStreet());
        

        this.quality = result.getQuality();
        this.latitude = result.getLatitude();
        this.longitude = result.getLongitude();
        this.offsetlat = result.getOffsetlat();
        this.offsetlon = result.getOffsetlon();
        this.radius = result.getRadius();
        this.name = result.getName();
        this.line1 = result.getLine1();
        this.line2 = result.getLine2();
        this.line3 = result.getLine3();
        this.line4 = result.getLine4();
        this.house = result.getHouse();
        this.street = result.getStreet();
        this.xstreet = result.getXstreet();
        this.unittype = result.getUnittype();
        this.unit = result.getUnit();
        this.postal = result.getPostal();
        this.neighborhood = result.getNeighborhood();
        this.yahooCity = result.getCity();
        this.county = result.getCounty();
        this.state = result.getState();
        this.yahooCountry = result.getCountry();
        this.countrycode = result.getCountrycode();
        this.statecode = result.getStatecode();
        this.countycode = result.getCountrycode();
        this.uzip = result.getUnit();
        this.hash = result.getHash();
        this.woeid = result.getWoeid();
        this.woetype = result.getWoetype();
    }
    
	/**
	 * @return the quality
	 */
	public String getQuality() {
		return quality;
	}
	
	/**
	 * @param quality the quality to set
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @return the offsetlat
	 */
	public String getOffsetlat() {
		return offsetlat;
	}
	
	/**
	 * @param offsetlat the offsetlat to set
	 */
	public void setOffsetlat(String offsetlat) {
		this.offsetlat = offsetlat;
	}
	
	/**
	 * @return the offsetlon
	 */
	public String getOffsetlon() {
		return offsetlon;
	}
	
	/**
	 * @param offsetlon the offsetlon to set
	 */
	public void setOffsetlon(String offsetlon) {
		this.offsetlon = offsetlon;
	}
	
	/**
	 * @return the radius
	 */
	public String getRadius() {
		return radius;
	}
	
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the line1
	 */
	public String getLine1() {
		return line1;
	}
	
	/**
	 * @param line1 the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	
	/**
	 * @return the line2
	 */
	public String getLine2() {
		return line2;
	}
	
	/**
	 * @param line2 the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	/**
	 * @return the line3
	 */
	public String getLine3() {
		return line3;
	}
	
	/**
	 * @param line3 the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	
	/**
	 * @return the line4
	 */
	public String getLine4() {
		return line4;
	}
	
	/**
	 * @param line4 the line4 to set
	 */
	public void setLine4(String line4) {
		this.line4 = line4;
	}
	
	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}
	
	/**
	 * @param house the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * @return the xstreet
	 */
	public String getXstreet() {
		return xstreet;
	}
	
	/**
	 * @param xstreet the xstreet to set
	 */
	public void setXstreet(String xstreet) {
		this.xstreet = xstreet;
	}
	
	/**
	 * @return the unittype
	 */
	public String getUnittype() {
		return unittype;
	}
	
	/**
	 * @param unittype the unittype to set
	 */
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}
	
	/**
	 * @param postal the postal to set
	 */
	public void setPostal(String postal) {
		this.postal = postal;
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
	 * @return the city
	 */
	public String getYahooCity() {
		return yahooCity;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setYahooCity(String city) {
		this.yahooCity = city;
	}
	
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the country
	 */
	public String getYahooCountry() {
		return yahooCountry;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setYahooCountry(String country) {
		this.yahooCountry = country;
	}
	
	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}
	
	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	/**
	 * @return the statecode
	 */
	public String getStatecode() {
		return statecode;
	}
	
	/**
	 * @param statecode the statecode to set
	 */
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	
	/**
	 * @return the countycode
	 */
	public String getCountycode() {
		return countycode;
	}
	
	/**
	 * @param countycode the countycode to set
	 */
	public void setCountycode(String countycode) {
		this.countycode = countycode;
	}
	
	/**
	 * @return the uzip
	 */
	public String getUzip() {
		return uzip;
	}
	
	/**
	 * @param uzip the uzip to set
	 */
	public void setUzip(String uzip) {
		this.uzip = uzip;
	}
	
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	
	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	/**
	 * @return the woeid
	 */
	public String getWoeid() {
		return woeid;
	}
	
	/**
	 * @param woeid the woeid to set
	 */
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}
	
	/**
	 * @return the woetype
	 */
	public String getWoetype() {
		return woetype;
	}
	
	/**
	 * @param woetype the woetype to set
	 */
	public void setWoetype(String woetype) {
		this.woetype = woetype;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPYahooResult [quality=" + quality + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", offsetlat=" + offsetlat
				+ ", offsetlon=" + offsetlon + ", radius=" + radius + ", name="
				+ name + ", line1=" + line1 + ", line2=" + line2 + ", line3="
				+ line3 + ", line4=" + line4 + ", house=" + house + ", street="
				+ street + ", xstreet=" + xstreet + ", unittype=" + unittype
				+ ", unit=" + unit + ", postal=" + postal + ", neighborhood="
				+ neighborhood + ", city=" + yahooCity + ", county=" + county
				+ ", state=" + state + ", country=" + yahooCountry
				+ ", countrycode=" + countrycode + ", statecode=" + statecode
				+ ", countycode=" + countycode + ", uzip=" + uzip + ", hash="
				+ hash + ", woeid=" + woeid + ", woetype=" + woetype + "]";
	}
}
