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
package org.geosdi.geoplatform.gui;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.Assert;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.server.service.IReverseGeocoding;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

/**
 * @author giuseppe
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext-TEST.xml" })
public class ReverseGeocoderTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IReverseGeocoding reverseGeoding;

	@Test
	public void test() {
		GeocodingBean bean = null;
		try {
			bean = this.reverseGeoding.findLocation(40.6372425, 15.8022214);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			logger.error("XPathExpressionException " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException " + e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("SAXException " + e);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("ParserConfigurationException " + e);
		}

		Assert.assertNotNull(bean);

		logger.info("Locations ***************** " + bean.getDescription());

	}

}
