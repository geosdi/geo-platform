package org.geosdi.geoplatform.experimental.el.query.task.function;

import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.joda.time.DateTime;

import java.util.UUID;
import java.util.function.Function;

import static org.geosdi.geoplatform.experimental.el.query.mapper.GPElasticSearchQueryMapperTest.createQueryExtraParameters;
import static org.geosdi.geoplatform.experimental.el.query.mapper.GPElasticSearchQueryMapperTest.createQueryParameters;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryFunction implements Function<Integer, GPElasticSearchQuery> {

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public GPElasticSearchQuery apply(Integer integer) {
        return new GPElasticSearchQuery() {

            {
                super.setId(UUID.randomUUID().toString());
                super.setQueryName("QUERY_TEST_" + integer);
                super.setDescription("DESCRIPTION_TEST_" + integer);
                super.setCreationDate(DateTime.now().minusHours(1));
                super.setQueryTemplate("TEMPLATE_TEST_" + integer);
                super.setIndexSettings(new GPBaseIndexSettings("INDEX_NAME_TEST_" + integer,
                        "INDEX_TYPE_TEST_" + integer));
                super.setQueryParameters(createQueryParameters(20));
                super.setQueryExtraParameters(createQueryExtraParameters(20));
            }
        };
    }
}
