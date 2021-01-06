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
package org.geosdi.geoplatform.connector.wms.unmarshall;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.wms.v111.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.Exception;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder.newInstance;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class SimpleUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUnmarshallerTest.class);
    //
    private static final GPJAXBContextBuilder jaxbContextBuilder = newInstance();

    @Test
    public void a_simpleTest() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        File file = new File(basePath.concat("test.xml"));
        SimpleBean simpleBean = jaxbContextBuilder.unmarshal(file, SimpleBean.class);
        logger.info("#######################SimpleBean : {}\n", simpleBean);
    }

    @Test
    public void b_simpleTest() throws Exception {
        Layer layer = jaxbContextBuilder.unmarshal(new StringReader("<Layer queryable=\"1\" opaque=\"0\">\n"
                + "        <Name>admin:eventi_time</Name>\n"
                + "        <Title>eventi_time</Title>\n"
                + "        <Abstract/>\n"
                + "        <KeywordList>\n"
                + "          <Keyword>features</Keyword>\n"
                + "          <Keyword>admin_shp_cnt_eventi_time</Keyword>\n"
                + "        </KeywordList>\n"
                + "        <SRS>EPSG:4326</SRS>\n"
                + "        <LatLonBoundingBox minx=\"5.98701953887939\" miny=\"35.3546295166016\" maxx=\"24.1629810333252\" maxy=\"48.1553726196289\"/>\n"
                + "        <BoundingBox SRS=\"EPSG:4326\" minx=\"5.98701953887939\" miny=\"35.3546295166016\" maxx=\"24.1629810333252\" maxy=\"48.1553726196289\"/>\n"
                + "        <Dimension name=\"time\" units=\"ISO8601\"/>\n"
                + "        <Extent name=\"time\" default=\"1985-04-20T22:00:00Z\">1985-01-01T23:00:00.000Z,1985-01-02T23:00:00.000Z,1985-01-03T23:00:00.000Z,1985-01-04T23:00:00.000Z," +
                "1985-01-05T23:00:00.000Z,1985-01-06T23:00:00.000Z,1985-01-07T23:00:00.000Z,1985-01-08T23:00:00.000Z,1985-01-09T23:00:00.000Z," +
                "1985-01-10T23:00:00.000Z,1985-01-11T23:00:00.000Z,1985-01-12T23:00:00.000Z,1985-01-13T23:00:00.000Z," +
                "1985-01-14T23:00:00.000Z,1985-01-15T23:00:00.000Z,1985-01-16T23:00:00.000Z,1985-01-17T23:00:00.000Z,1985-01-18T23:00:00.000Z" +
                ",1985-01-19T23:00:00.000Z,1985-01-20T23:00:00.000Z,1985-01-21T23:00:00.000Z,1985-01-22T23:00:00.000Z,1985-01-24T23:00:00.000Z," +
                "1985-01-26T23:00:00.000Z,1985-01-27T23:00:00.000Z,1985-01-30T23:00:00.000Z,1985-01-31T23:00:00.000Z,1985-02-01T23:00:00.000Z," +
                "1985-02-02T23:00:00.000Z,1985-02-03T23:00:00.000Z,1985-02-04T23:00:00.000Z,1985-02-05T23:00:00.000Z,1985-02-06T23:00:00.000Z," +
                "1985-02-07T23:00:00.000Z,1985-02-08T23:00:00.000Z,1985-02-09T23:00:00.000Z,1985-02-10T23:00:00.000Z,1985-02-11T23:00:00.000Z," +
                "1985-02-12T23:00:00.000Z,1985-02-13T23:00:00.000Z,1985-02-14T23:00:00.000Z,1985-02-15T23:00:00.000Z,1985-02-16T23:00:00.000Z," +
                "1985-02-17T23:00:00.000Z,1985-02-18T23:00:00.000Z,1985-02-19T23:00:00.000Z,1985-02-20T23:00:00.000Z,1985-02-21T23:00:00.000Z," +
                "1985-02-22T23:00:00.000Z,1985-02-23T23:00:00.000Z,1985-02-24T23:00:00.000Z,1985-02-25T23:00:00.000Z,1985-02-26T23:00:00.000Z," +
                "1985-02-27T23:00:00.000Z,1985-02-28T23:00:00.000Z,1985-03-01T23:00:00.000Z,1985-03-02T23:00:00.000Z,1985-03-03T23:00:00.000Z," +
                "1985-03-04T23:00:00.000Z,1985-03-05T23:00:00.000Z,1985-03-06T23:00:00.000Z,1985-03-07T23:00:00.000Z,1985-03-08T23:00:00.000Z," +
                "1985-03-09T23:00:00.000Z,1985-03-11T23:00:00.000Z,1985-03-12T23:00:00.000Z,1985-03-13T23:00:00.000Z,1985-03-14T23:00:00.000Z," +
                "1985-03-15T23:00:00.000Z,1985-03-16T23:00:00.000Z,1985-03-17T23:00:00.000Z,1985-03-19T23:00:00.000Z,1985-03-20T23:00:00.000Z," +
                "1985-03-21T23:00:00.000Z,1985-03-22T23:00:00.000Z,1985-03-23T23:00:00.000Z,1985-03-24T23:00:00.000Z,1985-03-25T23:00:00.000Z," +
                "1985-03-26T23:00:00.000Z,1985-03-27T23:00:00.000Z,1985-03-28T23:00:00.000Z,1985-03-29T23:00:00.000Z,1985-03-30T23:00:00.000Z," +
                "1985-03-31T22:00:00.000Z,1985-04-01T22:00:00.000Z,1985-04-02T22:00:00.000Z,1985-04-03T22:00:00.000Z,1985-04-04T22:00:00.000Z," +
                "1985-04-05T22:00:00.000Z,1985-04-06T22:00:00.000Z,1985-04-07T22:00:00.000Z,1985-04-08T22:00:00.000Z,1985-04-09T22:00:00.000Z," +
                "1985-04-10T22:00:00.000Z,1985-04-11T22:00:00.000Z,1985-04-12T22:00:00.000Z,1985-04-13T22:00:00.000Z,1985-04-14T22:00:00.000Z," +
                "1985-04-15T22:00:00.000Z,1985-04-16T22:00:00.000Z,1985-04-17T22:00:00.000Z,1985-04-18T22:00:00.000Z,1985-04-19T22:00:00.000Z," +
                "1985-04-20T22:00:00.000Z</Extent>\n"
                + "        <Style>\n"
                + "          <Name>point</Name>\n"
                + "          <Title>Default Point</Title>\n"
                + "          <Abstract>A sample style that draws a point</Abstract>\n"
                + "          <LegendURL width=\"20\" height=\"20\">\n"
                + "            <Format>image/png</Format>\n"
                + "            <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?request=GetLegendGraphic&amp;format=image%2Fpng&amp;width=20&amp;height=20&amp;layer=admin%3Aeventi_time\"/>\n"
                + "          </LegendURL>\n"
                + "        </Style>\n"
                + "      </Layer>"), Layer.class);
        logger.info("#####################Layer : {}\n", layer.getExtent());
        List<String> values = Arrays.asList(layer.getExtent().get(0).getvalue().split(","));
        assertTrue(values.size() == 104);
    }

    @Test
    public void c_simpleTest() throws Exception {
        ContactInformation contactInformation = jaxbContextBuilder.unmarshal(new StringReader("<ContactInformation>\n"
                + "      <ContactPersonPrimary>\n"
                + "        <ContactPerson>CNR - geoSDI</ContactPerson>\n"
                + "        <ContactOrganization>CNR - geoSDI</ContactOrganization>\n"
                + "      </ContactPersonPrimary>\n"
                + "      <ContactPosition>CNR - geoSDI</ContactPosition>\n"
                + "      <ContactAddress>\n"
                + "        <AddressType>Work</AddressType>\n"
                + "        <Address>C.da Santa Loja</Address>\n"
                + "        <City>Tito</City>\n"
                + "        <StateOrProvince/>\n"
                + "        <PostCode>85050</PostCode>\n"
                + "        <Country>Italia</Country>\n"
                + "      </ContactAddress>\n"
                + "      <ContactVoiceTelephone/>\n"
                + "      <ContactFacsimileTelephone/>\n"
                + "      <ContactElectronicMailAddress>core@geosdi.org</ContactElectronicMailAddress>\n"
                + "    </ContactInformation>"), ContactInformation.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@CONTACT_INFORMATION : {}\n", contactInformation);
    }

    @Test
    public void d_simpleTest() throws Exception {
        KeywordList keywordList = jaxbContextBuilder.unmarshal(new StringReader("<KeywordList>\n"
                + "      <Keyword>WFS</Keyword>\n"
                + "      <Keyword>WMS</Keyword>\n"
                + "      <Keyword>GEOSERVER</Keyword>\n"
                + "    </KeywordList>"), KeywordList.class);
        logger.info("#######################KEYWORD_LIST : {}\n", keywordList);
    }

    @Test
    public void e_simpleTest() throws Exception {
        GetMap getMap = jaxbContextBuilder.unmarshal(new StringReader("<GetMap>\n"
                + "        <Format>image/png</Format>\n"
                + "        <Format>application/atom xml</Format>\n"
                + "        <Format>application/atom+xml</Format>\n"
                + "        <Format>application/json;type=utfgrid</Format>\n"
                + "        <Format>application/openlayers</Format>\n"
                + "        <Format>application/pdf</Format>\n"
                + "        <Format>application/rss xml</Format>\n"
                + "        <Format>application/rss+xml</Format>\n"
                + "        <Format>application/vnd.google-earth.kml</Format>\n"
                + "        <Format>application/vnd.google-earth.kml xml</Format>\n"
                + "        <Format>application/vnd.google-earth.kml+xml</Format>\n"
                + "        <Format>application/vnd.google-earth.kml+xml;mode=networklink</Format>\n"
                + "        <Format>application/vnd.google-earth.kmz</Format>\n"
                + "        <Format>application/vnd.google-earth.kmz xml</Format>\n"
                + "        <Format>application/vnd.google-earth.kmz+xml</Format>\n"
                + "        <Format>application/vnd.google-earth.kmz;mode=networklink</Format>\n"
                + "        <Format>atom</Format>\n"
                + "        <Format>image/geotiff</Format>\n"
                + "        <Format>image/geotiff8</Format>\n"
                + "        <Format>image/gif</Format>\n"
                + "        <Format>image/gif;subtype=animated</Format>\n"
                + "        <Format>image/jpeg</Format>\n"
                + "        <Format>image/png8</Format>\n"
                + "        <Format>image/png; mode=8bit</Format>\n"
                + "        <Format>image/svg</Format>\n"
                + "        <Format>image/svg xml</Format>\n"
                + "        <Format>image/svg+xml</Format>\n"
                + "        <Format>image/tiff</Format>\n"
                + "        <Format>image/tiff8</Format>\n"
                + "        <Format>image/vnd.jpeg-png</Format>\n"
                + "        <Format>kml</Format>\n"
                + "        <Format>kmz</Format>\n"
                + "        <Format>openlayers</Format>\n"
                + "        <Format>rss</Format>\n"
                + "        <Format>text/html; subtype=openlayers</Format>\n"
                + "        <Format>utfgrid</Format>\n"
                + "        <DCPType>\n"
                + "          <HTTP>\n"
                + "            <Get>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Get>\n"
                + "          </HTTP>\n"
                + "        </DCPType>\n"
                + "      </GetMap>"), GetMap.class);
        logger.info("###############################GET_MAP : {}\n", getMap);
    }

    @Test
    public void f_simpleTest() throws Exception {
        GetFeatureInfo getFeatureInfo = jaxbContextBuilder.unmarshal(new StringReader("<GetFeatureInfo>\n"
                + "        <Format>text/plain</Format>\n"
                + "        <Format>application/vnd.ogc.gml</Format>\n"
                + "        <Format>text/xml</Format>\n"
                + "        <Format>application/vnd.ogc.gml/3.1.1</Format>\n"
                + "        <Format>text/xml; subtype=gml/3.1.1</Format>\n"
                + "        <Format>text/html</Format>\n"
                + "        <Format>application/json</Format>\n"
                + "        <DCPType>\n"
                + "          <HTTP>\n"
                + "            <Get>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Get>\n"
                + "            <Post>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Post>\n"
                + "          </HTTP>\n"
                + "        </DCPType>\n"
                + "      </GetFeatureInfo>"), GetFeatureInfo.class);
        logger.info("#############################GET_FEATURE_INFO : {}\n", getFeatureInfo);
    }

    @Test
    public void g_simpleTest() throws Exception {
        DescribeLayer describeLayer = jaxbContextBuilder.unmarshal(new StringReader("<DescribeLayer>\n"
                + "        <Format>application/vnd.ogc.wms_xml</Format>\n"
                + "        <DCPType>\n"
                + "          <HTTP>\n"
                + "            <Get>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Get>\n"
                + "          </HTTP>\n"
                + "        </DCPType>\n"
                + "      </DescribeLayer>"), DescribeLayer.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@DESCRIBE_LAYER : {}\n", describeLayer);
    }

    @Test
    public void h_simpleTest() throws Exception {
        GetLegendGraphic getLegendGraphic = jaxbContextBuilder.unmarshal(new StringReader("<GetLegendGraphic>\n"
                + "        <Format>image/png</Format>\n"
                + "        <Format>image/jpeg</Format>\n"
                + "        <Format>image/gif</Format>\n"
                + "        <DCPType>\n"
                + "          <HTTP>\n"
                + "            <Get>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Get>\n"
                + "          </HTTP>\n"
                + "        </DCPType>\n"
                + "      </GetLegendGraphic>"), GetLegendGraphic.class);
        logger.info("########################GET_LEGEND_GRAPHIC : {}\n", getLegendGraphic);
    }

    @Test
    public void i_simpleTest() throws Exception {
        GetStyles getStyles = jaxbContextBuilder.unmarshal(new StringReader("<GetStyles>\n"
                + "        <Format>application/vnd.ogc.sld+xml</Format>\n"
                + "        <DCPType>\n"
                + "          <HTTP>\n"
                + "            <Get>\n"
                + "              <OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:type=\"simple\" xlink:href=\"https://prosit.geosdi.org/geoserver/wms?SERVICE=WMS&amp;\"/>\n"
                + "            </Get>\n"
                + "          </HTTP>\n"
                + "        </DCPType>\n"
                + "      </GetStyles>"), GetStyles.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GET_STYLES : {}\n", getStyles);
    }

    @Test
    public void m_dateTimeTest() throws Exception {
        LocalDate nowDate = LocalDate.now().minusDays(7);
        Instant instant = nowDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date res = Date.from(instant);
        logger.info("{}\n", res);
    }

    @Getter
    @Setter
    @ToString
    @XmlRootElement(name = "root")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class SimpleBean implements Serializable {

        private static final long serialVersionUID = -6760414143134662079L;
        //
        private String name;
        @XmlElement(name = "sur.name")
        private String surname;
    }
}