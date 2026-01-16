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
package org.geosdi.geoplatform.geoserver.layers;

import com.google.common.collect.Lists;
import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.IGPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured.GPGeoserverFeatureTypes;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayerStyle;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayerType;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverStyle;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPGeoserverStyle;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverCreateFeatureTypeRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadFeatureTypeWithUrlRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadWorkspaceLayerRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.junit.Assert.assertTrue;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorLayersTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorLayersTest.class);

    @Test
    public void a_existLayer() throws Exception {
        assertTrue("####################", this.restReader.existsLayer("tiger", "poi", FALSE) ==
                this.geoserverConnectorStore.loadLayerRequest().withName("poi").exist());
    }

    @Test
    public void b_getLayer() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("poi");
        GeoserverLoadLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadLayerRequest().withName("poi");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################RESOURCE_URL_REST_READER {}\n", restLayer.getResourceUrl());
        logger.info("##########################RESOURCE_URL_GEOSERVER_CONNECTOR {}\n", geoserverLayer.getLayerResource().getHref());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        List<String> styleNames = geoserverLoadLayerRequest.getResponse().getLayerStyle().getStyles().stream().map(s -> s.getName()).collect(Collectors.toList());
        assertTrue("###################STYLES_LENGTH", styleNames.size() == restLayer.getStyles().getNames().size());
        if(restLayer.getStyles() != null) {
            for (String styleName : restLayer.getStyles().getNames()) {
                logger.info("#######################STYLE_NAME: {}\n", styleName);
                assertTrue("###################STYLE NAME:",  styleNames.contains(styleName));
            }
        }
    }

    @Test
    public void c_getLayerWithWorkspace() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("tiger","poi");
        GeoserverLoadWorkspaceLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withLayerName("poi").withWorkspaceName("tiger");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################STYLES_REST_READER {}\n", restLayer.getStyles().getNames());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        List<String> styleNames = geoserverLoadLayerRequest.getResponse().getLayerStyle().getStyles().stream().map(s -> s.getName()).collect(Collectors.toList());
        logger.info("#######################GEOSERVER_STYLE_NAMES: {}\n", styleNames);
        assertTrue("###################STYLES_LENGTH", styleNames.size() == restLayer.getStyles().getNames().size());
        if(restLayer.getStyles() != null) {
            for (String styleName : restLayer.getStyles().getNames()) {
                logger.info("#######################STYLE_NAME: {}\n", styleName);
                assertTrue("###################STYLE NAME:",  styleNames.contains(styleName));
            }
        }
    }

    @Test
    public void d_getLayerWithWorkspace() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("nurc","mosaic");
        GeoserverLoadWorkspaceLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withLayerName("mosaic").withWorkspaceName("nurc");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################RESOURCE_URL_REST_READER {}\n", restLayer.getResourceUrl());
        logger.info("##########################RESOURCE_URL_GEOSERVER_CONNECTOR {}\n", geoserverLayer.getLayerResource().getHref());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        logger.info("#######################REST_READER_DEFAULT_STYLE: {}\n", restLayer.getDefaultStyle());
        assertTrue("###################STYLE NAME:",   restLayer.getDefaultStyle().equals(geoserverLayer.getDefaultStyle().getName()));
    }

    @Test
    public void e_getUniqueValues() throws Exception {
//        RESTServiceUniqueValues uniqueValues = this.restReader.uniqueValues("comuni2001", "maschi");
//        logger.info("####################UNIQUE_VALUES: {}\n", uniqueValues.getNames());
    }

    @Test
    public void f_existLayerInWorkspace() throws Exception {
        GeoserverLoadWorkspaceLayerRequest geoserverLoadWorkspaceLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withQuietOnNotFound(FALSE)
                .withLayerName("sfdem")
                .withWorkspaceName("sf");
        Boolean result = geoserverLoadWorkspaceLayerRequest.exist();
        logger.info("################{}\n", result);
        GeoserverLayer geoserverLayer = geoserverLoadWorkspaceLayerRequest.getResponse();
        logger.info("################{}\n", geoserverLayer);

        assertTrue("####################", geoserverLayer.getLayerType() == GeoserverLayerType.Raster);
        assertTrue("####################", this.restReader.existsLayer("tiger", "poi", FALSE) == result);
        assertTrue("####################", this.restReader.existsLayer("tigerr", "poi", FALSE) ==
                this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                        .withQuietOnNotFound(FALSE)
                        .withLayerName("poi").withWorkspaceName("tigerr").exist());
    }

    @Test
    public void g_readLayerWithUrl() throws Exception {
        GeoserverLayer geoserverLayer = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withQuietOnNotFound(FALSE)
                .withLayerName("poi")
                .withWorkspaceName("tiger").getResponse();
        logger.info("#################LAYER: {}\n", geoserverLayer);
        GeoserverLoadFeatureTypeWithUrlRequest geoserverLoadFeatureTypeWithUrlRequest = this.geoserverConnectorStore.loadFeatureTypeWithUrl().
                withUrl(geoserverLayer.getLayerResource().getHref());
        GPGeoserverFeatureTypeInfo gpGeoserverFeatureTypeInfo = geoserverLoadFeatureTypeWithUrlRequest.getResponse();
        logger.info("####################FEATURES: {}\n", gpGeoserverFeatureTypeInfo);
        geoserverLayer = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withQuietOnNotFound(FALSE)
                .withLayerName("sfdem")
                .withWorkspaceName("sf").getResponse();
        logger.info("#################LAYER: {}\n", geoserverLayer);
        GPGeoserverCoverageInfo coverageInfo = this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl(geoserverLayer.getLayerResource().getHref()).getResponse();
        logger.info("########################COVERAGES : {}\n", coverageInfo);
        logger.info("########################DIMENSIONS : {}\n", coverageInfo.getDimensions().getCoverageDimension());
        RESTLayer restLayer = this.restReader.getLayer("sf", "sfdem");
        RESTCoverage restCoverage = this.restReader.getCoverage(restLayer);
        logger.info("############{}\n",restCoverage.getEncodedDimensionsInfoList());
    }

    @Ignore(value = "Layer poi_vito may be not present")
    @Test
    public void h_deleteLayer() throws Exception {
        logger.info("##################DELETE_LAYER {}\n", this.geoserverConnectorStore.deleteLayerRequest().withLayerName("poi_vito").getResponse());
        Assert.assertFalse("####################",
                this.geoserverConnectorStore.loadLayerRequest().withName("poi_vito").exist());
    }

    @Ignore(value = "Layer poi_vito may be not present")
    @Test
    public void i_updateLayer() throws Exception {
        GeoserverLayer geoserverLayer = this.geoserverConnectorStore.loadWorkspaceLayerRequest().withWorkspaceName("tiger")
                .withLayerName("poi_vito")
                .getResponse();
        logger.info("##################LAYER {}\n",geoserverLayer);
        GeoserverLayerStyle layerStyle = geoserverLayer.getLayerStyle() != null ? geoserverLayer.getLayerStyle() : new GeoserverLayerStyle();
        List<IGPGeoserverStyle> styles = layerStyle.getStyles() != null ? geoserverLayer.getLayerStyle().getStyles() : Lists.newArrayList();
        GPGeoserverStyle gpGeoserverStyle = new GPGeoserverStyle();
        gpGeoserverStyle.setName("burg");
        styles.add(gpGeoserverStyle);
        layerStyle.setStyles(styles);
        geoserverLayer.setLayerStyle(layerStyle);
        logger.info("##################RESPONSE {}\n", this.geoserverConnectorStore.updateLayerRequest()
                .withWorkspaceName("tiger")
                .withLayerBody(geoserverLayer)
                .withLayerName("poi_vito").getResponse());

        logger.info("##################UPDATED_LAYER {}\n",this.geoserverConnectorStore.loadWorkspaceLayerRequest().withWorkspaceName("tiger")
                .withLayerName("poi_vito")
                .getResponse());
    }

    @Ignore(value = "Layer poi_vito may be not present")
    @Test
    public void l_deleteLayerWorkspace() throws Exception {
        logger.info("##################DELETE_LAYER {}\n", this.geoserverConnectorStore.deleteLayerWorkspaceRequest()
                .withWorkspaceName("sf")
                .withLayerName("admin_shp_comuni").getResponse());
        Assert.assertFalse("####################",
                this.geoserverConnectorStore.loadLayerRequest().withName("admin_shp_comuni").exist());
    }

    @Test
    public void m_getFeatureType() throws Exception {
        GeoserverLoadWorkspaceLayerRequest geoserverLoadWorkspaceLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withQuietOnNotFound(FALSE)
                .withLayerName("poi")
                .withWorkspaceName("tiger");
        Boolean result = geoserverLoadWorkspaceLayerRequest.exist();
        logger.info("################{}\n", result);
        GeoserverLayer geoserverLayer = geoserverLoadWorkspaceLayerRequest.getResponse();
        logger.info("################{}\n", geoserverLayer);
        GPGeoserverFeatureTypeInfo gpGeoserverFeatureTypeInfo = this.geoserverConnectorStore.loadFeatureTypeWithUrl().
                withUrl(geoserverLayer.getLayerResource().getHref()).getResponse();
        RESTLayer restLayer = this.restReader.getLayer("tiger", "poi");
        RESTFeatureType featureType = this.restReader.getFeatureType(restLayer);
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeBoundingBox().getMaxx() ==  featureType.getNativeBoundingBox().getMaxX());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeBoundingBox().getMaxy() ==  featureType.getNativeBoundingBox().getMaxY());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeBoundingBox().getMinx() ==  featureType.getNativeBoundingBox().getMinX());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeBoundingBox().getMiny() ==  featureType.getNativeBoundingBox().getMinY());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeBoundingBox().getCrs().equals(featureType.getNativeBoundingBox().getCRS()));
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getMaxx() ==  featureType.getLatLonBoundingBox().getMaxX());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getMaxy() ==  featureType.getLatLonBoundingBox().getMaxY());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getMinx() ==  featureType.getLatLonBoundingBox().getMinX());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getMiny() ==  featureType.getLatLonBoundingBox().getMinY());
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getCrs().equals(featureType.getLatLonBoundingBox().getCRS()));
        assertTrue("#################", gpGeoserverFeatureTypeInfo.getNativeName().equals(featureType.getNativeName()));
        stream(gpGeoserverFeatureTypeInfo.getAttributes().getValues().spliterator(), FALSE).forEach( c-> c.getName());
        stream(featureType.getAttributes().spliterator(), FALSE)
                .forEach(c-> System.out.println(c.getName()));
    }

    @Test
    public void n_loadFeaturesTypes() throws Exception {
        logger.info("##############{}\n", ((GPGeoserverFeatureTypes)this.geoserverConnectorStore.loadWorkspaceFeatureTypesRequest()
                .withWorkspace("sf")
                .withFeatureTypeCategory(GPGeoserverFeatureTypeCategory.configured).getResponse().toFeatureType()).getFeatureTypes()
        .stream().map(f -> f.getName()).collect(toList()));

        logger.info("############{}\n", stream(this.restReader.getFeatureTypes("sf").spliterator(), FALSE)
                .map(nameLinkElem -> nameLinkElem.getName())
                .collect(toList()));
    }

    @Ignore
    @Test
    public void o_publishDBLayer() throws Exception {
        GeoserverCreateFeatureTypeRequest createFeatureTypeRequest = this.geoserverConnectorStore.createFeatureTypeRequest();
        createFeatureTypeRequest.withWorkspace("sf").withStore("store_vito");
        IGPGeoserverFeatureTypeInfo featureTypeBody = new GPGeoserverFeatureTypeInfo();
        featureTypeBody.setNativeCRS("GEOGCS[&quot;WGS 84&quot;, \n" +
                "  DATUM[&quot;World Geodetic System 1984&quot;, \n" +
                "    SPHEROID[&quot;WGS 84&quot;, 6378137.0, 298.257223563, AUTHORITY[&quot;EPSG&quot;,&quot;7030&quot;]], \n" +
                "    AUTHORITY[&quot;EPSG&quot;,&quot;6326&quot;]], \n" +
                "  PRIMEM[&quot;Greenwich&quot;, 0.0, AUTHORITY[&quot;EPSG&quot;,&quot;8901&quot;]], \n" +
                "  UNIT[&quot;degree&quot;, 0.017453292519943295], \n" +
                "  AXIS[&quot;Geodetic longitude&quot;, EAST], \n" +
                "  AXIS[&quot;Geodetic latitude&quot;, NORTH], \n" +
                "  AUTHORITY[&quot;EPSG&quot;,&quot;4326&quot;]]");
        featureTypeBody.setSrs("EPSG:4326");
        featureTypeBody.setEnabled(TRUE);
        featureTypeBody.setTitle("layer_test");
        featureTypeBody.setName("test");
        GPGeoserverNativeBoundingBox nativeBoundingBox = new GPGeoserverNativeBoundingBox();
        nativeBoundingBox.setMinx(-74.0118315772888);
        nativeBoundingBox.setMaxx(-74.00153046439813);
        nativeBoundingBox.setMiny(40.70754683896324);
        nativeBoundingBox.setMaxy(40.719885123828675);
        nativeBoundingBox.setCrs("EPSG:4326");
        featureTypeBody.setNativeBoundingBox(nativeBoundingBox);
        GPGeoserverLatLonBoundingBox latLonBoundingBox = new GPGeoserverLatLonBoundingBox();
        latLonBoundingBox.setMinx(-74.0118315772888);
        latLonBoundingBox.setMaxx(-74.00857344353275);
        latLonBoundingBox.setMiny(40.70754683896324);
        latLonBoundingBox.setMaxy(40.711945649065406);
        latLonBoundingBox.setCrs("EPSG:4326");
        featureTypeBody.setLatLonBoundingBox(latLonBoundingBox);
        IGPFeatureTypeAttribute featureTypeAttribute = new GPFeatureTypeAttribute();
        featureTypeAttribute.setName("the_geom");
        featureTypeAttribute.setBinding("org.locationtech.jts.geom.Point");
        featureTypeAttribute.setNillable(TRUE);
        featureTypeAttribute.setMinOccurs(0);
        featureTypeAttribute.setMinOccurs(1);
        IGPFeatureTypeAttributes featureTypeAttributes = new GPFeatureTypeAttributes();
        featureTypeAttributes.setValues(asList(featureTypeAttribute));
        featureTypeBody.setAttributes(featureTypeAttributes);
        createFeatureTypeRequest.withFeatureTypeBody(featureTypeBody);
        logger.info("################{}\n", createFeatureTypeRequest.getResponse());
    }

    @Test
    public void p_getLayers() throws Exception {
        logger.info("#############{}\n", this.geoserverConnectorStore.loadLayersRequest().getResponse().getLayers().size());
        logger.info("#############{}\n", this.restReader.getLayers().size());
    }
}