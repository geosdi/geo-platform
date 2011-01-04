/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2010 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.GeocodingKeyValue;
import org.geosdi.geoplatform.gui.server.service.IReverseGeocoding;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author giuseppe
 * 
 */
@Service("reverseGeocoding")
public class ReverseGeocoding extends GPGeocodingService implements
		IReverseGeocoding {

	// URL prefix to the reverse geocoder
	private static final String REVERSE_GEOCODER_PREFIX_FOR_XML = "http://maps.googleapis.com/maps/api/geocode/xml";

	private GeocodingBean bean;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.geosdi.geoplatform.gui.server.service.IReverseGeocoding#findLocations
	 * (double, double)
	 */
	@Override
	public GeocodingBean findLocation(double lat, double lon)
			throws IOException, SAXException, ParserConfigurationException,
			XPathExpressionException {
		// TODO Auto-generated method stub
		this.bean = new GeocodingBean();

		url = new URL(REVERSE_GEOCODER_PREFIX_FOR_XML + "?latlng="
				+ URLEncoder.encode(lat + "," + lon, "UTF-8") + "&sensor=true");

		conn = (HttpURLConnection) url.openConnection();

		Document geocoderResultDocument = null;

		try {
			// open the connection and get results as InputSource.
			conn.connect();
			InputSource geocoderResultInputSource = new InputSource(
					conn.getInputStream());

			// read result and parse into XML Document
			geocoderResultDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(geocoderResultInputSource);
		} finally {
			conn.disconnect();
		}

		// extract the result
		NodeList resultNodeList = null;

		// a) obtain the formatted_address field for every result
		resultNodeList = (NodeList) xpath.evaluate(
				"/GeocodeResponse/result/formatted_address",
				geocoderResultDocument, XPathConstants.NODESET);

		if (resultNodeList.getLength() > 0)
			bean.setDescription(resultNodeList.item(0).getTextContent());
		else
			bean.setDescription(GeocodingKeyValue.ZERO_RESULTS.toString());

		return bean;
	}

}
