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
package org.geosdi.geoplatform.connector.geoserver.model;

import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayerType;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalogMode;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.gml.GMLVersion;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType;
import org.geosdi.geoplatform.connector.geoserver.model.store.coverage.GPCoverageStoreType;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline tests for the model enums that follow the {@code @JsonValue} getter + {@code @JsonCreator}
 * {@code forXxx(String)} pattern. Representative sample (the same shape repeats across ~28 enums):
 * it verifies the Jackson serialization round-trip through the production mapper — guarding the
 * {@code com.fasterxml.jackson.annotation} annotations against the Jackson 3 databind — and the
 * case-insensitive, null-safe factory contract.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverModelEnumTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverModelEnumTest.class);
    private static JsonMapper mapper;

    @BeforeClass
    public static void setUp() {
        // Enums are always serialized nested as a field value, never as a root value, so root
        // wrap/unwrap (enabled on the production mapper) never applies to them; disable it to
        // exercise the raw @JsonValue / @JsonCreator contract.
        mapper = new GPJacksonSupport().getDefaultMapper().rebuild()
                .disable(SerializationFeature.WRAP_ROOT_VALUE)
                .disable(DeserializationFeature.UNWRAP_ROOT_VALUE)
                .build();
    }

    @Test
    public void a_coverageStoreTypeJsonRoundTripTest() throws Exception {
        String json = mapper.writeValueAsString(GPCoverageStoreType.GEOTIFF);
        logger.info("################COVERAGE_STORE_TYPE_JSON : {}\n", json);
        assertEquals("\"GeoTIFF\"", json);
        assertEquals(GPCoverageStoreType.GEOTIFF, mapper.readValue(json, GPCoverageStoreType.class));
    }

    @Test
    public void b_coverageStoreTypeForTypeContractTest() throws Exception {
        assertSame(GPCoverageStoreType.GEOTIFF, GPCoverageStoreType.forType("geotiff"));
        assertSame(GPCoverageStoreType.IMAGEMOSAIC, GPCoverageStoreType.forType("ImageMosaic"));
        assertNull(GPCoverageStoreType.forType(null));
        assertNull(GPCoverageStoreType.forType("   "));
        assertNull(GPCoverageStoreType.forType("does-not-exist"));
    }

    @Test
    public void c_storeInfoTypeJsonRoundTripTest() throws Exception {
        assertEquals("\"coverageStore\"", mapper.writeValueAsString(GeoserverStoreInfoType.COVERAGE));
        assertEquals("\"dataStore\"", mapper.writeValueAsString(GeoserverStoreInfoType.FEATURE));
        assertEquals(GeoserverStoreInfoType.FEATURE, mapper.readValue("\"dataStore\"", GeoserverStoreInfoType.class));
        assertSame(GeoserverStoreInfoType.FEATURE, GeoserverStoreInfoType.forType("DATASTORE"));
    }

    @Test
    public void d_layerTypeJsonRoundTripTest() throws Exception {
        assertEquals("\"RASTER\"", mapper.writeValueAsString(GeoserverLayerType.Raster));
        assertEquals(GeoserverLayerType.Vector, mapper.readValue("\"VECTOR\"", GeoserverLayerType.class));
        assertSame(GeoserverLayerType.Vector, GeoserverLayerType.forType("vector"));
        assertNull(GeoserverLayerType.forType("nope"));
    }

    @Test
    public void e_catalogModeJsonRoundTripTest() throws Exception {
        assertEquals("\"CHALLENGE\"", mapper.writeValueAsString(GPGeoserverCatalogMode.CHALLENGE));
        assertEquals(GPGeoserverCatalogMode.MIXED, mapper.readValue("\"MIXED\"", GPGeoserverCatalogMode.class));
        assertSame(GPGeoserverCatalogMode.HIDE, GPGeoserverCatalogMode.forMode("hide"));
        assertNull(GPGeoserverCatalogMode.forMode(""));
    }

    @Test
    public void f_gmlVersionSerializesEnumNameNotHumanReadableTest() throws Exception {
        // GMLVersion is the odd one out: its @JsonValue is toString() == name() ("V_10"), while the
        // human-readable form ("1.0.0") is a separate accessor and is NOT what gets serialized.
        assertEquals("\"V_10\"", mapper.writeValueAsString(GMLVersion.V_10));
        assertEquals("1.0.0", GMLVersion.V_10.toHumanReadble());
        assertEquals(GMLVersion.V_20, mapper.readValue("\"V_20\"", GMLVersion.class));
        assertSame(GMLVersion.V_11, GMLVersion.forVersion("v_11"));
        assertNull(GMLVersion.forVersion("1.0.0"));
    }
}
