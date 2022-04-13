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
package org.geosdi.geoplatform.connector.wms.stax.theories;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxGml2Reader;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GPWMSFeatureStoreTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSFeatureStoreTheoriesTest.class);
    //
    private static String dirFiles;
    private static final GPWMSGetFeatureInfoStaxGml2Reader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxGml2Reader();

    @BeforeClass
    public static void buildDirFiles() throws Exception {
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"geoserver-Vigneti-GetFeatureInfo.xml", "geoserver-GetFeatureInfo.xml", "geoserver-GetFeatureInfo1.xml",
                "geoserver-GetFeatureInfo-Point.xml", "geoserver-GetFeatureInfo-MultiLineString.xml",
                "spearfish-GetFeatureInfo.xml", "tasmaniaRoads-GetFeatureInfo.xml", "tasmaniaStates-GetFeatureInfo.xml",
                "tiger_ny-GetFeatureInfo.xml", "sfdem-GetFeatureInfo.xml", "nurcAPk50095-GetFeatureInfo.xml",
                "nurcArcSample-GetFeatureInfo.xml", "comuni-GetFeatureInfo.xml", "parchiNaturali-GetFeatureInfo.xml",
                "retiRiserve-GetFeatureInfo.xml", "linee-GetFeatureInfo.xml", "azioniPunto-GetFeatureInfo.xml",
                "comuniBasilicata-GetFeatureInfo.xml", "corine-GetFeatureInfo.xml", "airports.xml",
                "geologia.xml", "livelloEdifici.xml", "volumetria.xml", "livelloEdifici1.xml", "masw.xml",
                "CF_zonepianificazione_mappeinterattive.xml", "PianoCampiFlegrei.xml", "ABR_Comuni.xml",
                "AereeUrbaneValoreStorico.xml", "PNSRS_Valanghe.xml"};
    }

    /**
     * @param fileName
     * @throws Exception
     */
    @Theory
    public void wmsGetFeatureInfoStaxFeatureReaderTest(String fileName) throws Exception {
        checkArgument((fileName != null) && !(fileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        File file = new File(dirFiles.concat(fileName));
        logger.info("#######################FEATURE_STORE : \n{}\n for File : {}\n", wmsGetFeatureInfoStaxReader.readAsStore(file), fileName);
    }
}