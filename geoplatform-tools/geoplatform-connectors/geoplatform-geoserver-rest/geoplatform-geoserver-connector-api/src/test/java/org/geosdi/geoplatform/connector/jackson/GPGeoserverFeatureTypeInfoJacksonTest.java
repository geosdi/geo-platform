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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypesStoreInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy.FORCE_DECLARED;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPFeatureTypeAttributesJacksonTest.toFeatureTypeAttributes;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverDataLinkJacksonTest.toDataLinks;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverKeywordJacksonTest.toKeyword;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverMetadataLinkJacksonTest.toMetadataLinks;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverMetadataParamJacksonTest.toMapParams;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverNativeBoundingBoxJacksonTest.toNativeBoundingBox;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverResponseSRSJacksonTest.toResponseSRS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverFeatureTypeInfoJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverFeatureTypeInfoJacksonTest.class);

    @Test
    public void a_unmarshallGPGeoserverFeatureTypeInfoFromXmlFileTest() throws Exception {
        GPGeoserverFeatureTypeInfo featureTypeInfo = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "FeatureTypeInfo")
                .collect(joining(separator, "", ".xml"))), GPGeoserverFeatureTypeInfo.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_FEATURE_TYPE_INFO : {}\n", featureTypeInfo);
    }

    @Test
    public void b_marshallGPGeoserverFeatureTypeInfoAsXmlStringTest() throws Exception {
        logger.info("######################GP_GEOSERVER_FEATURE_TYPE_INFO : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverFeatureTypeInfoJacksonTest::toFeatureTypeInfo));
    }

    @Test
    public void c_marshallGPGeoserverFeatureTypeInfoAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_FEATURE_TYPE_INFO : \n{}\n", jacksonSupport.writeAsString(GPGeoserverFeatureTypeInfoJacksonTest::toFeatureTypeInfo));
    }

    @Test
    public void d_unmarshallGPGeoserverFeatureTypeInfoFromJsonFileTest() throws Exception {
        GPGeoserverFeatureTypeInfo featureTypeInfo = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "FeatureTypeInfo")
                        .collect(joining(separator, "", ".json"))), GPGeoserverFeatureTypeInfo.class);
        logger.info("#####################GP_GEOSERVER_FEATURE_TYPE_INFO : {}\n", featureTypeInfo);
    }

    @Test
    public void e_unmarshallGPGeoserverFeatureTypeInfoFromXmlFileTest() throws Exception {
        GPGeoserverFeatureTypeInfo featureTypeInfo = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "FeatureTypeInfo1")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverFeatureTypeInfo.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_FEATURE_TYPE_INFO : {}\n", featureTypeInfo);
    }

    /**
     * @return {@link GPGeoserverFeatureTypeInfo}
     */
    public static GPGeoserverFeatureTypeInfo toFeatureTypeInfo() {
        GPGeoserverFeatureTypeInfo featureType = new GPGeoserverFeatureTypeInfo();
        featureType.setNativeCRS("GEOGCS[&quot;WGS 84&quot;, \n" +
                "  DATUM[&quot;World Geodetic System 1984&quot;, \n" +
                "    SPHEROID[&quot;WGS 84&quot;, 6378137.0, 298.257223563, AUTHORITY[&quot;EPSG&quot;,&quot;7030&quot;]], \n" +
                "    AUTHORITY[&quot;EPSG&quot;,&quot;6326&quot;]], \n" +
                "  PRIMEM[&quot;Greenwich&quot;, 0.0, AUTHORITY[&quot;EPSG&quot;,&quot;8901&quot;]], \n" +
                "  UNIT[&quot;degree&quot;, 0.017453292519943295], \n" +
                "  AXIS[&quot;Geodetic longitude&quot;, EAST], \n" +
                "  AXIS[&quot;Geodetic latitude&quot;, NORTH], \n" +
                "  AUTHORITY[&quot;EPSG&quot;,&quot;4326&quot;]]");
        featureType.setSrs("EPSG:4326");
        featureType.setEnabled(TRUE);
        featureType.setTitle("layer_test");
        featureType.setName("test");
        featureType.setAbstractText("ABSTRACT_TEXT");
        featureType.setNativeBoundingBox(toNativeBoundingBox());
        GPGeoserverLatLonBoundingBox latLonBoundingBox = new GPGeoserverLatLonBoundingBox();
        latLonBoundingBox.setMinx(-74.0118315772888);
        latLonBoundingBox.setMaxx(-74.00857344353275);
        latLonBoundingBox.setMiny(40.70754683896324);
        latLonBoundingBox.setMaxy(40.711945649065406);
        latLonBoundingBox.setCrs("EPSG:4326");
        featureType.setLatLonBoundingBox(latLonBoundingBox);
        featureType.setAttributes(toFeatureTypeAttributes(15));
        featureType.setDataLinks(toDataLinks(20));
        featureType.setMetadataLinks(toMetadataLinks(25));
        featureType.setMetadata(toMapParams());
        featureType.setProjectionPolicy(FORCE_DECLARED);
        featureType.setMaxFeatures(40);
        featureType.setKeywords(toKeyword());
        GPGeoserverFeatureTypesStoreInfo featureTypesStoreInfo = new GPGeoserverFeatureTypesStoreInfo();
        featureTypesStoreInfo.setName("tiger:nyc");
        featureTypesStoreInfo.setHref("http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml");
        featureType.setStore(featureTypesStoreInfo);
        featureType.setResponseSRS(toResponseSRS());
        return featureType;
    }
}