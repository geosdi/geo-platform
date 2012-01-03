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
package org.geosdi.geoplatform.wpsconnector;

import com.vividsolutions.jts.geom.Geometry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.Assert;
import net.opengis.wps10.ProcessBriefType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.geotools.GML;
import org.geotools.GML.Version;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.collection.AbstractFeatureVisitor;
import org.geotools.gml2.bindings.GML2EncodingUtils;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.NullProgressListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext-TEST.xml"})
public class GeoPlatformWPSConnectorTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GeoPlatformWPSConnector gpWPSConnector;
    //
    private HttpPost post;
    private HttpClient httpclient;

    @Test
    public void test() {
        Assert.assertNotNull(gpWPSConnector);

        logger.info("Number of Process ********************** " + gpWPSConnector.getWPSCapabilities().size());

        for (ProcessBriefType pbfT : gpWPSConnector.getWPSCapabilities()) {
            logger.info("Process Abstract : ****************" + pbfT.getTitle().getValue());
            logger.info("Process Identifier : ***********" + pbfT.getIdentifier().getValue());
        }
    }

    @Test
    public void testProcessDoubleAddition() {
        try {
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
            xml.append("<wps:Execute version=\"1.0.0\" service=\"WPS\" xmlns:xsi=");
            xml.append("\"http://www.w3.org/2001/XMLSchema-instance\" ");
            xml.append("xmlns=\"http://www.opengis.net/wps/1.0.0\" xmlns:wfs=");
            xml.append("\"http://www.opengis.net/wfs\" xmlns:wps=\"");
            xml.append("http://www.opengis.net/wps/1.0.0\" xmlns:ows=\"");
            xml.append("http://www.opengis.net/ows/1.1\" xmlns:gml=\"");
            xml.append("http://www.opengis.net/gml\" xmlns:ogc=\"");
            xml.append("http://www.opengis.net/ogc\" xmlns:wcs=\"");
            xml.append("http://www.opengis.net/wcs/1.1.1\" xmlns:xlink=\"");
            xml.append("http://www.w3.org/1999/xlink\" xsi:schemaLocation=\"");
            xml.append("http://www.opengis.net/wps/1.0.0 http://");
            xml.append("schemas.opengis.net/wps/1.0.0/wpsAll.xsd\"> ");
            xml.append("  <ows:Identifier>gt:DoubleAddition</ows:Identifier> ");
            xml.append("  <wps:DataInputs> ");
            xml.append("   <wps:Input> ");
            xml.append("    <ows:Identifier>input_a</ows:Identifier> ");
            xml.append("   <wps:Data> ");
            xml.append("    <wps:LiteralData>4</wps:LiteralData>");
            xml.append(" </wps:Data>");
            xml.append(" </wps:Input>");
            xml.append(" <wps:Input>");
            xml.append("    <ows:Identifier>input_b</ows:Identifier>");
            xml.append("   <wps:Data>");
            xml.append("     <wps:LiteralData>4</wps:LiteralData>");
            xml.append("   </wps:Data>");
            xml.append(" </wps:Input>");
            xml.append(" </wps:DataInputs>");
            xml.append(" <wps:ResponseForm>");
            xml.append("   <wps:RawDataOutput>");
            xml.append("     <ows:Identifier>result</ows:Identifier>");
            xml.append("   </wps:RawDataOutput>");
            xml.append("  </wps:ResponseForm>");
            xml.append("</wps:Execute>");

            StringEntity se = new StringEntity(xml.toString(), "UTF-8");
            se.setContentType("application/atom+xml");

            post.setEntity(se);

            HttpResponse response;
            response = httpclient.execute(post);

            logger.info("Response Status : " + response.getStatusLine() + "\n");

            InputStream is = response.getEntity().getContent();

            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }

            logger.info("RESULT DOUBLE ADDITION @@@@@@@@@@@@@@@@@@@@@@@@ " + writer.toString());

        } catch (UnsupportedEncodingException ex) {
            logger.error("Error " + ex);
        } catch (IOException es) {
            logger.error("IOException : " + es);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    @Test
    public void testProcessFilterByField() {
        try {
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
            xml.append("<wps:Execute version=\"1.0.0\" service=\"WPS\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
            xml.append(" xmlns=\"http://www.opengis.net/wps/1.0.0\" xmlns:wfs=\"http://www.opengis.net/wfs\" ");
            xml.append("xmlns:wps=\"http://www.opengis.net/wps/1.0.0\" xmlns:ows=\"http://www.opengis.net/ows/1.1\"");
            xml.append(" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:ogc=\"http://www.opengis.net/ogc\" ");
            xml.append("xmlns:wcs=\"http://www.opengis.net/wcs/1.1.1\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");
            xml.append("xsi:schemaLocation=\"http://www.opengis.net/wps/1.0.0 http://schemas.opengis.net/wps/1.0.0/wpsAll.xsd\"> ");
            xml.append("  <ows:Identifier>sitdpc:FilterByField</ows:Identifier> ");
            xml.append("  <wps:DataInputs> ");
            xml.append("    <wps:Input> ");
            xml.append("      <ows:Identifier>source</ows:Identifier> ");
            xml.append("      <wps:Reference mimeType=\"text/xml; subtype=wfs-collection/1.0\" xlink:href=\"http://geoserver/wfs\" method=\"POST\"> ");
            xml.append("        <wps:Body> ");
            xml.append("          <wfs:GetFeature service=\"WFS\" version=\"1.0.0\" outputFormat=\"GML2\"> ");
            xml.append("            <wfs:Query typeName=\"sitdpc:comuni2001\"/> ");
            xml.append("          </wfs:GetFeature> ");
            xml.append("        </wps:Body> ");
            xml.append("      </wps:Reference> ");
            xml.append("    </wps:Input> ");
            xml.append("    <wps:Input> ");
            xml.append("      <ows:Identifier>field</ows:Identifier> ");
            xml.append("      <wps:Data> ");
            xml.append("        <wps:LiteralData>comune</wps:LiteralData> ");
            xml.append("      </wps:Data> ");
            xml.append("    </wps:Input> ");
            xml.append("    <wps:Input> ");
            xml.append("      <ows:Identifier>value</ows:Identifier> ");
            xml.append("      <wps:Data> ");
            xml.append("        <wps:LiteralData>pignola</wps:LiteralData> ");
            xml.append("      </wps:Data> ");
            xml.append("    </wps:Input> ");
            xml.append("  </wps:DataInputs> ");
            xml.append("  <wps:ResponseForm> ");
            xml.append("    <wps:RawDataOutput mimeType=\"text/xml; subtype=wfs-collection/1.0\"> ");
            xml.append("      <ows:Identifier>result</ows:Identifier> ");
            xml.append("    </wps:RawDataOutput> ");
            xml.append("  </wps:ResponseForm> ");
            xml.append(" </wps:Execute>");

            StringEntity se = new StringEntity(xml.toString(), "UTF-8");
            se.setContentType("application/atom+xml");

            post.setEntity(se);

            HttpResponse response;
            response = httpclient.execute(post);

            logger.info("Response Status : " + response.getStatusLine() + "\n");

            InputStream is = response.getEntity().getContent();

            GML gml = new GML(Version.GML3);
            gml.setCoordinateReferenceSystem(DefaultGeographicCRS.WGS84);

            SimpleFeatureCollection fc = gml.decodeFeatureCollection(is);

            fc.accepts(new AbstractFeatureVisitor() {

                @Override
                public void visit(Feature feature) {
                    SimpleFeature f = (SimpleFeature) feature;
                    CoordinateReferenceSystem coord = GML2EncodingUtils.getCRS((Geometry) f.getDefaultGeometry());

                    logger.info("CRS for Feature =  " + coord.getCoordinateSystem().getIdentifiers());

                }
            }, new NullProgressListener());

        } catch (UnsupportedEncodingException ex) {
            logger.error("Error " + ex);
        } catch (IOException es) {
            logger.error("IOException : " + es);
        } catch (ParserConfigurationException ex) {
            logger.error("ParserConfigurationException : " + ex);
        } catch (SAXException ex) {
            logger.error("SAXException : " + ex);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    @PostConstruct
    public void init() {
        String url = gpWPSConnector.getUrl().toString();
        logger.info("\n*** URL = " + url);
        this.post = new HttpPost(url);
        this.httpclient = new DefaultHttpClient();
    }
}
