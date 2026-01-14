/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.geoserver.styles;

import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.GPJacksonJAXBXmlSupport;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.JacksonJAXBXmlSupport;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertTrue;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GeoserverConnectorStylesTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorStylesTest.class);
    //
    JacksonJAXBXmlSupport JACKSON_JAXB_XML_SUPPORT = new GPJacksonJAXBXmlSupport();

    @Test
    public void a_existStyle() throws Exception {
        assertTrue("####################",  restReader.existsStyle("burg", TRUE) ==
                this.geoserverConnectorStore.loadStyleRequest().withStyleName("burg").withQuietOnNotFound(TRUE).exist());
        assertTrue("####################",  restReader.existsStyle("burga", TRUE) ==
                this.geoserverConnectorStore.loadStyleRequest().withStyleName("burga").withQuietOnNotFound(TRUE).exist());
    }

    //@Ignore
    @Test
    public void b_publishStyle() throws Exception {
        StyledLayerDescriptor styledLayerDescriptor = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources", "default_polygon_test.sld")
                        .collect(joining(separator))), StyledLayerDescriptor.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_STYLE_SLD : {}", styledLayerDescriptor);
        logger.info("################{}\n", this.geoserverConnectorStore.createStyleSLDV100Request()
                .withStyleBody(styledLayerDescriptor)
                .withRaw(TRUE)
                .withStyleName("Default_Polygon_Test").getResponseAsString());
    }

   // @Ignore
    @Test
    public void c_publishStyle() throws Exception {
        logger.info("################{}\n", this.geoserverConnectorStore.createStyleSLDV100Request()
                                .withStyleName("default_polygon_test")
                //.withRaw(TRUE)
                .withStringStyleBody("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + "<StyledLayerDescriptor version=\"1.0.0\"\n" + "    xsi:schemaLocation=\"http://www.opengis.net/sld StyledLayerDescriptor.xsd\"\n" + "    xmlns=\"http://www.opengis.net/sld\"\n" + "    xmlns:ogc=\"http://www.opengis.net/ogc\"\n" + "    xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + "    <!-- a named layer is the basic building block of an sld document -->\n" + "\n" + "  <NamedLayer>\n" + "    <Name>Default Polygon Test</Name>\n" + "    <UserStyle>\n" + "        <!-- they have names, titles and abstracts -->\n" + "\n" + "      <Title>A boring default style</Title>\n" + "      <Abstract>A sample style that just prints out a transparent red interior with a red outline</Abstract>\n" + "      <!-- FeatureTypeStyles describe how to render different features -->\n" + "      <!-- a feature type for polygons -->\n" + "\n" + "      <FeatureTypeStyle>\n" + "        <!--FeatureTypeName>Feature</FeatureTypeName-->\n" + "        <Rule>\n" + "          <Name>Rule 1</Name>\n" + "          <Title>RedFill RedOutline</Title>\n" + "          <Abstract>50% transparent red fill with a red outline 1 pixel in width</Abstract>\n" + "\n" + "          <!-- like a linesymbolizer but with a fill too -->\n" + "          <PolygonSymbolizer>\n" + "            <Fill>\n" + "              <CssParameter name=\"fill\">#AAAAAA</CssParameter>\n" + "            </Fill>\n" + "            <Stroke>\n" + "              <CssParameter name=\"stroke\">#000000</CssParameter>\n" + "              <CssParameter name=\"stroke-width\">1</CssParameter>\n" + "            </Stroke>\n" + "          </PolygonSymbolizer>\n" + "        </Rule>\n" + "\n" + "        </FeatureTypeStyle>\n" + "    </UserStyle>\n" + "  </NamedLayer>\n" + "</StyledLayerDescriptor>")
                .getResponse());
    }

    //@Ignore
    @Test
    public void d_publishStyleFromFile() throws Exception {
        File file = new File(of("src", "test", "resources", "default_polygon_test.sld").collect(joining(separator)));
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        logger.info("####################{}\n", this.geoserverConnectorStore.createStyleWithFileSLDRequest()
                .withStyleName("default_polygon_test")
                .withRaw(TRUE)
                .withStyleBody(file).getResponse());
        logger.info("###########{}\n", this.geoserverConnectorStore.loadStyleRequest().withStyleName("default_polygon_test").withQuietOnNotFound(TRUE).exist());
    }

    @Ignore
    @Test
    public void e_updateStyleFromFile() throws Exception {
        File file = new File(of("src", "test", "resources", "default_polygon_test_update.sld").collect(joining(separator)));
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        logger.info("####################{}\n", this.geoserverConnectorStore.updateStyleWithFileSLDRequest()
                .withStyleName("default_polygon_test")
                .withRaw(TRUE)
                .withStyleBody(file).getResponse());
        logger.info("###########{}\n", this.geoserverConnectorStore.loadStyleRequest().withStyleName("default_polygon_test").withQuietOnNotFound(TRUE).exist());
    }

    @Test
    public void  f_updateStyle() throws Exception {
        logger.info("###############{}\n", this.geoserverConnectorStore.updateStyleSLDV100Request()
                .withStyleName("default_polygon_test")
                .withRaw(TRUE)
                .withStringStyleBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<StyledLayerDescriptor version=\"1.0.0\" xmlns=\"http://www.opengis.net/sld\" xmlns:ns2=\"http://www.opengis.net/ogc\" xmlns:ns4=\"http://www.w3.org/1999/xlink\" xmlns:ns3=\"http://www.opengis.net/gml\">\n"
                        + "    <NamedLayer>\n"
                        + "        <Name>Default Polygon Test</Name>\n"
                        + "        <UserStyle>\n"
                        + "            <Title>A boring default style</Title>\n"
                        + "            <Abstract>A sample style that just prints out a transparent red interior with a red outline</Abstract>\n"
                        + "            <FeatureTypeStyle>\n"
                        + "                <Rule>\n"
                        + "                    <Name>Rule 1</Name>\n"
                        + "                    <Title>RedFill RedOutline</Title>\n"
                        + "                    <Abstract>50% transparent red fill with a red outline 1 pixel in width</Abstract>\n"
                        + "                    <PolygonSymbolizer>\n"
                        + "                        <Fill>\n"
                        + "                            <CssParameter name=\"fill\">#AAAAAA</CssParameter>\n"
                        + "                        </Fill>\n"
                        + "                        <Stroke>\n"
                        + "                            <CssParameter name=\"stroke\">#F00707</CssParameter>\n"
                        + "                            <CssParameter name=\"stroke-width\">1</CssParameter>\n"
                        + "                        </Stroke>\n"
                        + "                    </PolygonSymbolizer>\n"
                        + "                </Rule>\n"
                        + "            </FeatureTypeStyle>\n"
                        + "        </UserStyle>\n"
                        + "    </NamedLayer>\n"
                        + "</StyledLayerDescriptor>").getResponse());
    }

    @Test
    public void loadStyles() throws Exception {
        logger.info("############{}\n", this.geoserverConnectorStore.loadStylesRequest().getResponse());
        List<String> geoserverStyles = this.restReader.getStyles().getNames();

        geoserverStyles.stream().forEach(style -> {
            logger.info("#######{}\n", style);
        });

    }
}