/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wfs.reader;

import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GetFeatureGeoJsonStaxGml3ReaderTest extends BaseGetFeatureGeoJsonStaxGml3ReaderTest {

    @Test
    public void a_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("extra_rt_webgis_strade_comunali.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void b_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("extra_rt_webgis:Cippi_SR_SP_ettometriche_v1.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void c_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("RTWebGIS:SINS_4069_4365_pr_lu.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void d_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("RTWebGIS:SINS_4068_1454677_punti_tappa_francigena.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void e_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("RTWebGIS:SINS_4068_9363_totem_asl7.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void f_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("extra_rt_webgis:Cippi_km_v2_4.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void g_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("extra_rt_webgis:strade_private.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void h_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("aggregati_strutturali_edificato.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void i_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("mappa_vulnerabilita_edificato.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }

    @Test
    public void l_readFeatureCollectionWithStaxTest() throws Exception {
        File file = new File(dirFiles.concat("pai_frane_II_agg_2015.xml"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))));
    }
}