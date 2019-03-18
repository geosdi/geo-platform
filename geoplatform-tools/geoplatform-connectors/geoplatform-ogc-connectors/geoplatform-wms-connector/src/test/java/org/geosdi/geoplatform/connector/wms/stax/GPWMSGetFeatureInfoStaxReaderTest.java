package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSGetFeatureInfoStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoStaxReaderTest.class);
    //
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    private static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();
    private static File file;
    private static File file1;
    private static File file2;
    private static File file3;
    private static File file4;
    private static File file5;
    private static File file6;
    private static File file7;
    private static File file8;
    private static File file9;
    private static File file10;
    private static File file11;
    private static File file12;
    private static File file13;
    private static File file14;
    private static File file15;
    private static File file16;
    private static File file17;
    private static File file18;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("geoserver-Vigneti-GetFeatureInfo.xml"));
        file1 = new File(basePath.concat("geoserver-GetFeatureInfo.xml"));
        file2 = new File(basePath.concat("geoserver-GetFeatureInfo1.xml"));
        file3 = new File(basePath.concat("geoserver-GetFeatureInfo-Point.xml"));
        file4 = new File(basePath.concat("geoserver-GetFeatureInfo-MultiLineString.xml"));
        file5 = new File(basePath.concat("spearfish-GetFeatureInfo.xml"));
        file6 = new File(basePath.concat("tasmaniaRoads-GetFeatureInfo.xml"));
        file7 = new File(basePath.concat("tasmaniaStates-GetFeatureInfo.xml"));
        file8 = new File(basePath.concat("tiger_ny-GetFeatureInfo.xml"));
        file9 = new File(basePath.concat("sfdem-GetFeatureInfo.xml"));
        file10 = new File(basePath.concat("nurcAPk50095-GetFeatureInfo.xml"));
        file11 = new File(basePath.concat("nurcArcSample-GetFeatureInfo.xml"));
        file12 = new File(basePath.concat("comuni-GetFeatureInfo.xml"));
        file13 = new File(basePath.concat("parchiNaturali-GetFeatureInfo.xml"));
        file14 = new File(basePath.concat("retiRiserve-GetFeatureInfo.xml"));
        file15 = new File(basePath.concat("linee-GetFeatureInfo.xml"));
        file16 = new File(basePath.concat("azioniPunto-GetFeatureInfo.xml"));
        file17 = new File(basePath.concat("comuniBasilicata-GetFeatureInfo.xml"));
        file18 = new File(basePath.concat("corine-GetFeatureInfo.xml"));
    }

    @Test
    public void a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VIGNETI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file)));
    }

    @Test
    public void b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file1)));
    }

    @Test
    public void c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STATES_1 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file2)));
    }

    @Test
    public void d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ADMIN_TEMPO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file3)));
    }

    @Test
    public void e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TIGER_ROADS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file4)));
    }

    @Test
    public void f_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_SPEARFISH : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file5)));
    }

    @Test
    public void g_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TASMANIA_ROADS : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file6)));
    }

    @Test
    public void h_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TASMANIA_STATES : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file7)));
    }

    @Test
    public void i_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_TYGER_NY : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file8)));
    }

    @Test
    public void l_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_SF_DEM : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file9)));
    }

    @Test
    public void m_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_NURC_APk50095 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file10)));
    }

    @Test
    public void n_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_NURC_ARC_SAMPLE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file11)));
    }

    @Test
    public void o_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_COMUNI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file12)));
    }

    @Test
    public void p_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PARCHI_NATURALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file13)));
    }

    @Test
    public void q_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_RETI_RISERVE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file14)));
    }

    @Test
    public void r_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_LINEE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file15)));
    }

    @Test
    public void s_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AZIONI_PUNTO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file16)));
    }

    @Test
    public void t_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_COMUNI_BASILICATA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file17)));
    }

    @Test
    public void u_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CORINE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxReader.read(file18)));
    }
}