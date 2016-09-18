package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.config;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class QueryColleagueRegistryConfig {

    @Bean
    public static Map<GPBaseIndexCreator.GPIndexSettings, GPElasticSearchQueryColleague> elasticSearchQueryColleagueRegistry(@Nonnull List<GPElasticSearchQueryColleague> queryColleagues)
            throws Exception {
        Preconditions.checkArgument((queryColleagues != null) && !(queryColleagues.isEmpty()), "Attention " +
                "in classpath there are no instance of ", GPElasticSearchQueryColleague.class.getSimpleName());
        return queryColleagues.stream()
                .filter(queryColleague -> queryColleague != null)
                .collect(Collectors.toMap(GPElasticSearchQueryColleague::getQueryColleagueKey, Function.identity()));
    }
}
