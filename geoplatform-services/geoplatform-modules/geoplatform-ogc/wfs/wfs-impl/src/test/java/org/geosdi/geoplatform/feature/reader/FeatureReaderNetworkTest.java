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
package org.geosdi.geoplatform.feature.reader;

import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureReaderNetworkTest {

    private final static Logger logger = LoggerFactory.getLogger(FeatureReaderNetworkTest.class);
    //
    private static final String DESCRIBE_FEATURE_PATH = "http://150.145.141.92/geoserver/wfs?servicewfs&version=1.1.0&request=DescribeFeatureType&typeName=";
    private static final FeatureSchemaReader featureReaderXSD = new GPFeatureSchemaReader();

    @Test
    public void readRestrictedAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("sf:restricted"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("#######################HERE THE SCHEMA FOR : sf:restricted : \n{}\n", layerSchema);
    }

    @Test
    public void readTigerRoadsAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("tiger:tiger_roads"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("########################HERE THE SCHEMA FOR : tiger:tiger_roads : \n{}\n", layerSchema);
    }

    @Test
    public void readStatesAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("topp:states"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("######################HERE THE SCHEMA FOR : topp:states : \n{}\n", layerSchema);
    }

    @Test
    public void readSFStreamsAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("sf:streams"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("######################HERE THE SCHEMA FOR : sf:streams : \n{}\n", layerSchema);
    }

    @Test
    public void readSFArchsitesAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("sf:archsites"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("######################HERE THE SCHEMA FOR : sf:archsites : \n{}\n", layerSchema);
    }

    @Test
    public void readToppTasmaniaRoadsAllNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("topp:tasmania_roads"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("######################HERE THE SCHEMA FOR : topp:tasmania_roads : \n{}\n", layerSchema);
    }

    @Test
    public void readToppTasmaniaWaterBodiesNet() throws Exception {
        List<LayerSchemaDTO> schemas = featureReaderXSD.read(new URL(DESCRIBE_FEATURE_PATH.concat("topp:tasmania_water_bodies"))
                .openStream());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        LayerSchemaDTO layerSchema = schemas.get(0);
        logger.debug("######################HERE THE SCHEMA FOR : topp:tasmania_water_bodies : \n{}\n", layerSchema);
    }
}
