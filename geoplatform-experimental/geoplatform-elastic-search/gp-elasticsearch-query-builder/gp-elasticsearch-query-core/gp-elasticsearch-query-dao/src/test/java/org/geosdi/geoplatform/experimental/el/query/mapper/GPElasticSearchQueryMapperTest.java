/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.query.mapper;

import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.param.extra.GPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.extra.IGPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.value.GPElasticSearchQueryParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.value.IGPElasticSearchQueryParamValue;
import org.geosdi.geoplatform.experimental.el.query.task.file.GPElasticSearchQueryTaskFilePrinter;
import org.geosdi.geoplatform.experimental.el.query.task.function.GPElasticSearchQueryExtraParamFunction;
import org.geosdi.geoplatform.experimental.el.query.task.function.GPElasticSearchQueryFunction;
import org.geosdi.geoplatform.experimental.el.query.task.function.GPElasticSearchQueryParamValueFunction;
import org.geosdi.geoplatform.experimental.el.query.task.logger.GPElasticSearchQueryTaskLoggerPrinter;
import org.joda.time.DateTime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static java.util.stream.StreamSupport.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPElasticSearchQueryMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryMapperTest.class);
    //
    private final GPElasticSearchQueryMapper queryMapper = new GPElasticSearchQueryMapper<>(GPElasticSearchQuery.class);

    @Test
    public void a_writeGPElasticSearchQueryAsStringTest() throws Exception {
        logger.info("##########################GP_ELASTIC_SEARCH_QUERY_AS_STRING : \n{}\n",
                this.queryMapper.writeAsString(createMockGPElasticSearchQuery()));
    }

    @Test
    public void b_writeGPElasticSearchQueryAsFileTest() throws Exception {
        this.queryMapper.write(new File(of(new File(".").getCanonicalPath(), "target", "GPElasticSearchQuery")
                .collect(joining(separator, "", ".json"))), createMockGPElasticSearchQuery());
    }

    @Test
    public void c_readGPElasticSearchQueryFromFileTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@Mapper : {}, read from File : {}\n\n",
                queryMapper, queryMapper.read(new ClassPathResource("query/GPElasticSearchQuery.json").getFile()));
    }

    @Test
    public void d_printGPElasticSearchQueryFileTest() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);
        stream(createMockGPElasticSearchQuery(20).spliterator(), FALSE)
                .forEach(query -> new GPElasticSearchQueryTaskFilePrinter(query, this.queryMapper,
                        counter, "GPElasticSearchQuery").start());
        Thread.sleep(500);
        logger.info(":::::::::::::::::::::::::::::PRINTED : {} GPElasticSearchQuery.\n", counter.get());
    }

    @Test
    public void e_printGPElasticSearchQueryLoggerTest() throws Exception {
        AtomicInteger counter = new AtomicInteger(0);
        stream(createMockGPElasticSearchQuery(20).spliterator(), FALSE)
                .forEach(query -> new GPElasticSearchQueryTaskLoggerPrinter(query, this.queryMapper, counter).start());
        Thread.sleep(500);
        logger.info(":::::::::::::::::::::::::::::PRINTED : {} GPElasticSearchQuery.\n", counter.get());
    }

    public static GPElasticSearchQuery createMockGPElasticSearchQuery() {
        return new GPElasticSearchQuery() {

            {
                super.setId(UUID.randomUUID().toString());
                super.setQueryName("QUERY_TEST");
                super.setDescription("DESCRIPTION_TEST");
                super.setCreationDate(DateTime.now());
                super.setQueryTemplate("TEMPLATE_TEST");
                super.setIndexSettings(new GPBaseIndexSettings("INDEX_NAME_TEST", "INDEX_TYPE_TEST"));
                super.setQueryParameters(createQueryParameters(20));
                super.setQueryExtraParameters(createQueryExtraParameters(20));
            }
        };
    }

    /**
     * @param queryNumbers
     * @return {@link Collection<GPElasticSearchQuery>}
     */
    public static Collection<GPElasticSearchQuery> createMockGPElasticSearchQuery(int queryNumbers) {
        checkArgument((queryNumbers > 0), "The Number of Query must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(queryNumbers)
                .boxed()
                .map(new GPElasticSearchQueryFunction())
                .collect(Collectors.toList());
    }

    /**
     * @param queryParametersNumber
     * @return {@link Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue>}
     */
    public static Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> createQueryParameters(int queryParametersNumber) {
        checkArgument((queryParametersNumber > 0), "The Number of Query Parameters must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(queryParametersNumber)
                .boxed()
                .map(new GPElasticSearchQueryParamValueFunction())
                .collect(toMap(GPElasticSearchQueryParamValue::getKeyValue, identity()));
    }

    /**
     * @param queryParametersNumber
     * @return {@link Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue>}
     */
    public static Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> createQueryExtraParameters(int queryParametersNumber) {
        checkArgument((queryParametersNumber > 0), "The Number of Query Parameters must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(queryParametersNumber)
                .boxed()
                .map(new GPElasticSearchQueryExtraParamFunction())
                .collect(toMap(GPElasticSearchQueryExtraParamValue::getKeyValue, identity()));
    }
}