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
package org.geosdi.geoplatform.connector.wms;

import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoTheoriesTest {

    protected static String dirFiles;

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
                "AereeUrbaneValoreStorico.xml", "PNSRS_Valanghe.xml", "AreeAmmassamento.xml",
                "Ferrovie.xml", "MonumentiBizantini.xml", "ReteGas.xml", "AziendeSanitarie.xml",
                "ABR_CaveAttive.xml", "AreeUrbaneValoreStorico.xml", "BaciniIdrogeografici.xml",
                "geoserver-building_resonance_level.xml", "admin_vigneti_catastali.xml", "VulcanoCampiIstat.xml",
                 "rsdi_alt_600_a_700.xml", "rsdi_fiumi_basilicata.xml", "rsdi_sentieri_app_lucano.xml",
                "dtsew_campania_20150324_20201122.xml", "dtsup_campania_20150324_20201122.xml"};
    }
}