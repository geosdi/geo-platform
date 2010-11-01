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
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.server.service.IGeocodingService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author giuseppe
 * 
 */
@Service("geocodingService")
public class GeocodingService implements IGeocodingService {

	// URL prefix to the geocoder
	private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

	private URL url;
	private HttpURLConnection conn;
	private XPath xpath;
	private ArrayList<GeocodingBean> beans;

	@PostConstruct
	public void init() {
		// prepare XPath
		xpath = XPathFactory.newInstance().newXPath();
	}

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

		url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address="
				+ URLEncoder.encode(address, "UTF-8") + "&sensor=false");

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

		for (int i = 0; i < resultNodeList.getLength(); ++i) {
			GeocodingBean bean = new GeocodingBean();
			bean.setDescription(resultNodeList.item(i).getTextContent());
			int resultID = i + 1;
			getGeometryLocation(geocoderResultDocument,
					"/GeocodeResponse/result[" + resultID
							+ "]/geometry/location/*", bean);
			beans.add(bean);
		}

		return beans;
	}

	/**
	 * 
	 * @param geocoderResultDocument
	 * @param xpathQuery
	 * @param bean
	 */
	private void getGeometryLocation(Document geocoderResultDocument,
			String xpathQuery, GeocodingBean bean) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList resultNodeListLocation = null;
		try {
			resultNodeListLocation = (NodeList) xpath.evaluate(xpathQuery,
					geocoderResultDocument, XPathConstants.NODESET);

			float lat = Float.NaN;
			float lng = Float.NaN;
			for (int i = 0; i < resultNodeListLocation.getLength(); ++i) {
				Node node = resultNodeListLocation.item(i);
				if ("lat".equals(node.getNodeName()))
					lat = Float.parseFloat(node.getTextContent());
				if ("lng".equals(node.getNodeName()))
					lng = Float.parseFloat(node.getTextContent());
			}
			bean.setLat(lat);
			bean.setLon(lng);

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
