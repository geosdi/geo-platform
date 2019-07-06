package org.geosdi.geoplatform.connector.builder;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.wfs.IGPWFSDatastoreBodyBuilder.GPWFSDatastoreBodyBuilder.wfsDatastoreBodyBuilder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWFSDatastoreBodyBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSDatastoreBodyBuilderTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_buildWFSDatastoreTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = wfsDatastoreBodyBuilder()
                .withName("DATASTORE_TEST")
                .withWFSGetCapabilitiesUrl(new URL("http://150.145.141.92/geoserver/ows?service=wfs&version=1.1.0&request=GetCapabilities"))
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test(expected = IllegalArgumentException.class)
    public void b_buildWFSDatastoreTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = wfsDatastoreBodyBuilder()
                .withName("DATASTORE_TEST_1")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }
}