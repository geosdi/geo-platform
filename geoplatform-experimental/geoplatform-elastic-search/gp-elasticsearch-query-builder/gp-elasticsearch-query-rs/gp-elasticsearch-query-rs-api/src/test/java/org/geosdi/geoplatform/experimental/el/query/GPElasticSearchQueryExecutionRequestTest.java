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
package org.geosdi.geoplatform.experimental.el.query;


import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.geosdi.geoplatform.experimental.el.query.mapper.GPElasticSearchQueryMapperTest;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.experimental.el.query.rest.request.IGPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryExecutorStore;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRITE_DATES_AS_TIMESTAMPS_DISABLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryExecutionRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryExecutionRequestTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport()
            .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
            .registerModule(new JodaModule())
            .configure(GPJsonIncludeFeature.NON_NULL);

    @Test
    public void writeGPElasticSearchQueryExecutionRequestAsStringTest() throws Exception {
        logger.info("#####################################\n\n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(createMockGPElasticSearchQuery()));
    }

    @Test
    public void readGPElasticSearchQueryExecutionRequestFromStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", jacksonSupport
                .getDefaultMapper().readValue("{\n" +
                        "  \"GPElasticSearchQueryExecutionRequest\" : {\n" +
                        "    \"queryID\" : \"fe9001ca-800f-41e6-8457-bfa90a43881a\",\n" +
                        "    \"queryTemplateParameters\" : {\n" +
                        "      \"key1\" : 1,\n" +
                        "      \"key2\" : \"Key_2\",\n" +
                        "      \"key3\" : \"2016-09-29T13:38:04.291+0000\",\n" +
                        "      \"key4\" : 3.4\n" +
                        "    }\n" +
                        "  }\n" +
                        "}", GPElasticSearchQueryExecutionRequest.class));
    }

    @Test
    public void writeGPElasticSearchQueryExecutorStoreAsStringTest() throws Exception {
        GPElasticSearchQueryExecutorStore<GPElasticSearchQuery> queryExecutorStore = new GPElasticSearchQueryExecutorStore<>();
        queryExecutorStore.setTotal(10l);
        queryExecutorStore.setExecutionResults(GPElasticSearchQueryMapperTest.createMockGPElasticSearchQuery(10));
        logger.info("#####################################GP_ELASTICSEARCH_QUERY_STORE : \n\n{}\n\n",
                jacksonSupport.getDefaultMapper().writeValueAsString(queryExecutorStore));
    }

    @Test
    public void writeGPElasticSearchQueryExecutorStoreAsFileTest() throws Exception {
        GPElasticSearchQueryExecutorStore<GPElasticSearchQuery> queryExecutorStore = new GPElasticSearchQueryExecutorStore<>();
        queryExecutorStore.setTotal(10l);
        queryExecutorStore.setExecutionResults(GPElasticSearchQueryMapperTest.createMockGPElasticSearchQuery(10));
        jacksonSupport.getDefaultMapper().writeValue(new File("./target/GPElasticSearchQueryExecutorStore.json"),
                queryExecutorStore);
    }

    public static IGPElasticSearchQueryExecutionRequest createMockGPElasticSearchQuery() {
        return new GPElasticSearchQueryExecutionRequest() {

            {
                super.setQueryID(UUID.randomUUID().toString());
                super.setQueryTemplateParameters(new HashMap<String, Object>() {

                    {
                        super.put("key1", new Integer(1));
                        super.put("key2", "Key_2");
                        super.put("key3", new Date());
                        super.put("key4", new Double(3.4));
                    }
                });
            }
        };
    }
}
