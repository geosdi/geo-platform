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
package org.geosdi.geoplatform.services;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType.Attribute;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import java.awt.Color;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import org.slf4j.LoggerFactory;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.FillImpl;
import org.geotools.styling.PolygonSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.SLD;
import org.geotools.styling.SLDParser;
import org.geotools.styling.SLDTransformer;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.filter.FilterFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath*:applicationContext.xml"})
public class SLDTest {

    private static final Logger logger = LoggerFactory.getLogger(SLDTest.class);
    //
    private StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory();
    private FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();
    private StyleBuilder styleBuilder = new StyleBuilder();
    //
    @Autowired
    private GeoServerRESTReader restReader;
    //
    @Autowired
    private GeoServerRESTPublisher restPublisher;

    @Test
    public void testDescribeFeatureType() {
        try {
            RESTLayer restLayer = this.restReader.getLayer("topp:states");
            for (Attribute att : this.restReader.getFeatureType(restLayer).
                    getAttributes()) {
                System.out.println(
                        "Stampa attributo: " + att.getBinding() + " - " + att.
                        getName());
            }
        } catch (Exception e) {
            logger.error("Error on performing SLD Test: " + e + " maybe the "
                    + "Geoserver is unavailable?");
        }
    }

    @Test
    public void testSLD() {
        String sldBody = this.restReader.getSLD("population");
        logger.info("##################### Style: {} ", sldBody);
        String xmlToPublish = null;
        try {
            Reader reader = new StringReader(sldBody);
            Style style = this.createFromSLD(reader);
            style.setName("Frank");
            for (FeatureTypeStyle fStyle : style.featureTypeStyles()) {
                for (Rule rule : fStyle.rules()) {
                    for (Symbolizer sym : rule.symbolizers()) {
                        for (String qwerty : sym.getOptions().keySet()) {
                            logger.info("################ Qwerty: {} ", qwerty);
                        }
                        StringBuilder stringa = new StringBuilder();

                        logger.info("@@@@@@@@@@@@@@@@@@@@@ Stringa "
                                + "generata: {} ", stringa);
                    }
                }
                PolygonSymbolizer pointSymbolizer = SLD.polySymbolizer(fStyle);
                Fill expres = pointSymbolizer.getFill();

                Fill fill = new FillImpl(filterFactory);
                fill.setColor(styleBuilder.colorExpression(Color.yellow));
                fill.setOpacity(styleBuilder.literalExpression(0.1));
                pointSymbolizer.setFill(fill);

                SLDTransformer styleTransform = new SLDTransformer();
                xmlToPublish = styleTransform.transform(style);
                logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Modifica "
                        + "apportata: {} ", xmlToPublish);

                logger.info("######################## Expression: {} ", expres.
                        getOpacity());
            }

            restPublisher.publishStyle(xmlToPublish);
        } catch (Exception nep) {
            logger.error("Error on performing SLD Test: " + nep + " maybe the "
                    + "Geoserver is unavailable?");
        }
    }

    /**
     * Figure out if a valid SLD file is available.
     */
    private File toSLDFile(File file) {
        String path = file.getAbsolutePath();
        String base = path.substring(0, path.length() - 4);
        String newPath = base + ".sld";
        File sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        newPath = base + ".SLD";
        sld = new File(newPath);
        if (sld.exists()) {
            return sld;
        }
        return null;
    }

    /**
     * Create a Style object from a definition in a SLD document
     */
    private Style createFromSLD(Reader sld) {
        try {
            SLDParser stylereader = new SLDParser(styleFactory, sld);
            Style[] style = stylereader.readXML();
            return style[0];

        } catch (Exception e) {
            logger.error("################## Exception on creating "
                    + "sld: {} ", e.toString());
        }
        return null;
    }

    private String removeXmlStringNamespaceAndPreamble(String xmlString) {
        return xmlString.replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */.
                replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */
    }

}
