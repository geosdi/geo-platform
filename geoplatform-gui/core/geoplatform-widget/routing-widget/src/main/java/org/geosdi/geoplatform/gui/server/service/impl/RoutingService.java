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
package org.geosdi.geoplatform.gui.server.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.geosdi.geoplatform.gui.client.model.Directions;
import org.geosdi.geoplatform.gui.client.model.RoutingBean;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.service.IRoutingService;
import org.geosdi.geoplatform.gui.server.service.RoutingServiceParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
@Component("routingService")
public class RoutingService implements IRoutingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private RoutingServiceParameters serviceParameter;
    
    private URL url;
    private HttpURLConnection conn;
    private XPath xpath;

    /**
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.server.service.IRoutingService#findDirections(double, double, double, double)
     */
    @Override
    public RoutingBean findDirections(double xStart, double yStart,
            double xStop, double yStop) throws GeoPlatformException {
        RoutingBean tracking = new RoutingBean();

        try {
            url = new URL(serviceParameter.getServiceDataSource()
                    + serviceParameter.getFirstRegex() + xStart + "," + yStart
                    + serviceParameter.getFinalRegex() + xStop + "," + yStop
                    + serviceParameter.getMethod());

            conn = (HttpURLConnection) url.openConnection();

            Document trackingResultDocument = null;
            // open the connection and get results as InputSource.
            conn.connect();
            InputSource geocoderResultInputSource = new InputSource(
                    conn.getInputStream());

            // read result and parse into XML Document
            trackingResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);

            // prepare XPath
            xpath = XPathFactory.newInstance().newXPath();

            // extract the result
            NodeList resultNodeList = null;

            resultNodeList = (NodeList) xpath.evaluate(
                    "/result/route/complete_line", trackingResultDocument,
                    XPathConstants.NODESET);

            // save first the completeline string
            tracking.setCompleteLine(resultNodeList.item(0).getTextContent());

            resultNodeList = (NodeList) xpath.evaluate(
                    "/result/route/total_length", trackingResultDocument,
                    XPathConstants.NODESET);

            tracking.setTotalLength(resultNodeList.item(0).getTextContent());

            resultNodeList = (NodeList) xpath.evaluate(
                    "/result/route/total_estimated_time",
                    trackingResultDocument, XPathConstants.NODESET);
            tracking.setTotaEstimatedTime(resultNodeList.item(0).getTextContent());

            resultNodeList = (NodeList) xpath.evaluate(
                    "/result/route/directions/step", trackingResultDocument,
                    XPathConstants.NODESET);

            for (int i = 0; i < resultNodeList.getLength(); ++i) {
                Directions direction = new Directions();
                direction.setRoute(resultNodeList.item(i).getChildNodes().item(0).getTextContent());
                if (resultNodeList.item(i).getChildNodes().getLength() > 1) {
                    direction.setWkt(resultNodeList.item(i).getChildNodes().item(1).getTextContent());
                }
                tracking.addDirection(direction);
            }

        } catch (MalformedURLException e) {
            logger.error("Error :", e);
            throw new GeoPlatformException(e);
        } catch (IOException e) {
            logger.error("Error :", e);
            throw new GeoPlatformException(e);
        } catch (SAXException e) {
            logger.error("Error :", e);
            throw new GeoPlatformException(e);
        } catch (ParserConfigurationException e) {
            logger.error("Error :", e);
            throw new GeoPlatformException(e);
        } catch (XPathExpressionException e) {
            logger.error("Error :", e);
            throw new GeoPlatformException(e);
        } finally {
            conn.disconnect();
        }

        return tracking;
    }
}
