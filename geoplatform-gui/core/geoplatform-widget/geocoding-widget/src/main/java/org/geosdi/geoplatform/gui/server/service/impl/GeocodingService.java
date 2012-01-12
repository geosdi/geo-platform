/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.server.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.google.GoogleGeocodingBean;
import org.geosdi.geoplatform.gui.oxm.model.google.GPGoogleGeocode;
import org.geosdi.geoplatform.gui.oxm.model.google.GPGoogleResult;
import org.geosdi.geoplatform.gui.oxm.model.google.enums.ResponseStatus;
import org.geosdi.geoplatform.gui.server.service.IGeocodingService;
import org.geosdi.geoplatform.oxm.GeoPlatformMarshall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

/**
 * @author giuseppe
 * 
 */
@Service("geocodingService")
public class GeocodingService implements
        IGeocodingService {

    // URL prefix to the geocoder
    private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";
    //
    @Autowired
    private GeoPlatformMarshall geocoderJaxbMarshaller;
    //
    private ArrayList<GeocodingBean> beans;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.server.service.IGeocodingService#findLocations
     * (java.lang.String)
     */
    @Override
    public ArrayList<GeocodingBean> findLocations(String address)
            throws IOException, SAXException, ParserConfigurationException,
            XPathExpressionException {
        // TODO Auto-generated method stub
        this.beans = new ArrayList<GeocodingBean>();

        URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address="
                + URLEncoder.encode(address, "UTF-8") + "&sensor=false");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        GPGoogleGeocode oxmBean = (GPGoogleGeocode) this.geocoderJaxbMarshaller.loadFromStream(conn.getInputStream());

        /** TODO : think a way to retrieve all information for zip code, region etc
         *  Change GeocodingBean model adapting it for new properties
         **/
        if (oxmBean.getStatus().equals(ResponseStatus.EnumResponseStatus.STATUS_OK.getValue())) {
            for (GPGoogleResult result : oxmBean.getResultList()) {
                GoogleGeocodingBean bean = new GoogleGeocodingBean(result);
                beans.add(bean);
            }
        }

        return beans;
    }
}
