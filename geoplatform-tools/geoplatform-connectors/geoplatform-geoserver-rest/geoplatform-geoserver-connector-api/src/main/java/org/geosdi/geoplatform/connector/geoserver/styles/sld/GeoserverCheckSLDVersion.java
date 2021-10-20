package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

import static java.lang.Boolean.FALSE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverCheckSLDVersion {

    /**
     * @param fileSLD
     * @return {@link Boolean}
     */
    default Boolean checkSLD10Version(File fileSLD) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileSLD);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//@version='1.0.0'");
            return (Boolean) expr.evaluate(doc, XPathConstants.BOOLEAN);
        } catch (Exception e) {
            e.printStackTrace();
            return FALSE;
        }
    }
}