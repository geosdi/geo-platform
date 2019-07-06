package org.geosdi.geoplatform.connector.builder;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.jndi.IGPPostgisJndiDatastoreBuilder.GPPostgisJndiDatastoreBuilder.postgisJndiDatastoreBuilder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPPostgisJndiDatastoreBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPostgisJndiDatastoreBuilderTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_buildDatastorePostgisJndiTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisJndiDatastoreBuilder()
                .withName("DATASTORE_TEST")
                .withJndiReferenceName("java:comp/env/jdbc/mydatabase")
                .build();
        logger.info("###########################GEOSERVER_CREATE_DATASTORE_BODY : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createDatastoreBody));
    }

    @Test(expected = IllegalArgumentException.class)
    public void b_buildDatastorePostgisJndiTest() throws Exception {
        IGPGeoserverCreateDatastoreBody createDatastoreBody = postgisJndiDatastoreBuilder()
                .withName("DATASTORE_TEST")
                .withEnabled(TRUE)
                .withSchema("schema_test")
                .build();
    }
}