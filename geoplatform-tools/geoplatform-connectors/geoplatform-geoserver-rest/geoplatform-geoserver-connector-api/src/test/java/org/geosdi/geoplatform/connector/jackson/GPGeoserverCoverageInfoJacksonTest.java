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
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.format.GPGeoserverSupportedFormat;
import org.geosdi.geoplatform.connector.geoserver.model.format.IGPGeoserverSupportedFormat;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverRequestSRS;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation.GPCoverageInterpolationMethod;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation.IGPCoverageInterpolationMethod;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store.GPGeoserverCoverageStoreInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy.FORCE_DECLARED;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPCoverageGridJacksonTest.toCoverageGrid;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverKeywordJacksonTest.toKeyword;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverMetadataParamJacksonTest.toMapParams;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverNativeBoundingBoxJacksonTest.toNativeBoundingBox;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverResponseSRSJacksonTest.toResponseSRS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverCoverageInfoJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverCoverageInfoJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverCoverageInfoAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_COVERAGE_INFO :\n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverCoverageInfoJacksonTest::toCoverageInfo));
    }

    @Test
    public void b_unmarshallGPGeoserverCoverageInfoFromXmlFileTest() throws Exception {
        GPGeoserverCoverageInfo coverageInfo = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "CoverageTypeInfo")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverCoverageInfo.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_COVERAGE_INFO : {}\n", coverageInfo);
    }

    @Test
    public void c_marshallGPGeoserverCoverageInfoAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_COVERAGE_INFO :\n{}\n", jacksonSupport.writeAsString(GPGeoserverCoverageInfoJacksonTest::toCoverageInfo));
    }

    @Test
    public void d_unmarshallGPGeoserverCoverageInfoFromJsonFileTest() throws Exception {
        GPGeoserverCoverageInfo coverageInfo = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "CoverageTypeInfo")
                        .collect(joining(separator, "", ".json"))), GPGeoserverCoverageInfo.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_COVERAGE_INFO : {}\n", coverageInfo);
    }

    /**
     * @return {@link GPGeoserverCoverageInfo}
     */
    public static GPGeoserverCoverageInfo toCoverageInfo() {
        GPGeoserverCoverageInfo coverageInfo = new GPGeoserverCoverageInfo();
        coverageInfo.setAbstractText("Digital elevation model for the Spearfish region. nsfdem is a Tagged Image File Format with Geographic information");
        coverageInfo.setDefaultInterpolationMethod("nearest neighbor");
        coverageInfo.setEnabled(TRUE);
        coverageInfo.setTitle("Spearfish elevation");
        coverageInfo.setGrid(toCoverageGrid());
        IGPCoverageInterpolationMethod interpolationMethod = new GPCoverageInterpolationMethod();
        interpolationMethod.setValues(iterate(0, n -> n + 1)
                .limit(15)
                .boxed()
                .map(String::valueOf)
                .collect(toList()));
        coverageInfo.setInterpolationMethod(interpolationMethod);
        GPGeoserverLatLonBoundingBox latLonBoundingBox = new GPGeoserverLatLonBoundingBox();
        latLonBoundingBox.setMinx(-74.0118315772888);
        latLonBoundingBox.setMaxx(-74.00857344353275);
        latLonBoundingBox.setMiny(40.70754683896324);
        latLonBoundingBox.setMaxy(40.711945649065406);
        latLonBoundingBox.setCrs("EPSG:4326");
        coverageInfo.setLatLonBoundingBox(latLonBoundingBox);
        coverageInfo.setNativeBoundingBox(toNativeBoundingBox());
        coverageInfo.setKeywords(toKeyword());
        coverageInfo.setMetadata(toMapParams());
        coverageInfo.setNativeCRS(new GPGeoserverCRS("PROJCS[\\\"NAD27 / UTM zone 13N\\\", \\n  GEOGCS[\\\"NAD27\\\", \\n   " +
                " DATUM[\\\"North American Datum 1927\\\", \\n      " +
                "SPHEROID[\\\"Clarke 1866\\\", 6378206.4, 294.9786982138982, AUTHORITY[\\\"EPSG\\\",\\\"7008\\\"]], \\n   " +
                "   TOWGS84[2.478, 149.752, 197.726, 0.526, -0.498, 0.501, 0.685], \\n    " +
                "  AUTHORITY[\\\"EPSG\\\",\\\"6267\\\"]], \\n    " +
                "PRIMEM[\\\"Greenwich\\\", 0.0, AUTHORITY[\\\"EPSG\\\",\\\"8901\\\"]], \\n   " +
                " UNIT[\\\"degree\\\", 0.017453292519943295], \\n    AXIS[\\\"Geodetic longitude\\\", EAST], \\n   " +
                " AXIS[\\\"Geodetic latitude\\\", NORTH], \\n    AUTHORITY[\\\"EPSG\\\",\\\"4267\\\"]], \\n " +
                " PROJECTION[\\\"Transverse_Mercator\\\"], \\n  PARAMETER[\\\"central_meridian\\\", -105.0], \\n" +
                "  PARAMETER[\\\"latitude_of_origin\\\", 0.0], \\n  PARAMETER[\\\"scale_factor\\\", 0.9996], \\n " +
                " PARAMETER[\\\"false_easting\\\", 500000.0], \\n  PARAMETER[\\\"false_northing\\\", 0.0], \\n " +
                " UNIT[\\\"m\\\", 1.0], \\n  AXIS[\\\"Easting\\\", EAST], \\n  AXIS[\\\"Northing\\\", NORTH], \\n " +
                " AUTHORITY[\\\"EPSG\\\",\\\"26713\\\"]]", "projected"));
        coverageInfo.setNativeFormat("GeoTIFF");
        coverageInfo.setNativeName("sfdem");
        coverageInfo.setSrs("EPSG:26713");
        coverageInfo.setResponseSRS(toResponseSRS());
        GPGeoserverRequestSRS requestSRS = new GPGeoserverRequestSRS();
        requestSRS.setValues(iterate(0, n -> n + 1)
                .limit(15)
                .boxed()
                .map(String::valueOf)
                .collect(toList()));
        coverageInfo.setRequestSRS(requestSRS);
        GPGeoserverCoverageStoreInfo coverageStoreInfo = new GPGeoserverCoverageStoreInfo();
        coverageStoreInfo.setHref("http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json");
        coverageStoreInfo.setName("sf:sfdem");
        coverageInfo.setStore(coverageStoreInfo);
        IGPGeoserverSupportedFormat supportedFormat = new GPGeoserverSupportedFormat();
        supportedFormat.setFormats(of("ARCGRID", "IMAGEMOSAIC", "GTOPO30", "GEOTIFF", "GIF", "PNG", "JPEG", "TIFF")
                .collect(toList()));
        coverageInfo.setSupportedFormats(supportedFormat);
        coverageInfo.setPolicy(FORCE_DECLARED);
        return coverageInfo;
    }
}