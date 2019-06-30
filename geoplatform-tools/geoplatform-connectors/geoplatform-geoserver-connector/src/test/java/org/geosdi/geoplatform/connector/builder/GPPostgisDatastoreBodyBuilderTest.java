package org.geosdi.geoplatform.connector.builder;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPPostgisDatastoreBodyBuilder.GPPostgisDatastoreBodyBuilder.postgisDatastoreBodyBuilder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPPostgisDatastoreBodyBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPostgisDatastoreBodyBuilderTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_buildDatastoreBodyPostGisTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisDatastoreBodyBuilder()
                .withName("DATASTORE_TEST")
                .withUser("postgres")
                .withPassword("&&postgres!!")
                .withDatabase("db_test")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test
    public void b_buildDatastoreBodyPostGisTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisDatastoreBodyBuilder()
                .withName("DATASTORE_TEST_1")
                .withUser("postgres")
                .withPassword("&&postgres!!")
                .withHost("192.168.1.12")
                .withDatabase("db_test_1")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test
    public void c_buildDatastoreBodyPostGisTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisDatastoreBodyBuilder()
                .withName("DATASTORE_TEST_1")
                .withUser("postgres")
                .withPassword("&&postgres!!")
                .withHost(null)
                .withDatabase("db_test_1")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test
    public void d_buildDatastoreBodyPostGisTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisDatastoreBodyBuilder()
                .withName("DATASTORE_TEST_2")
                .withUser("postgres")
                .withPassword("&&postgres!!")
                .withHost("         ")
                .withDatabase("db_test_2")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_buildDatastoreBodyPostGisTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisDatastoreBodyBuilder()
                .withName("DATASTORE_TEST_2")
                .withPassword("&&postgres!!")
                .withHost("         ")
                .withDatabase("db_test_2")
                .build();
    }
}