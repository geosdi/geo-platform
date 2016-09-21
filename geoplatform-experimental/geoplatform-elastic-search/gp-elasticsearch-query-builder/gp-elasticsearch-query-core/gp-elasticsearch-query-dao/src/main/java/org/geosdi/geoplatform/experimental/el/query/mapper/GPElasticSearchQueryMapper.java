package org.geosdi.geoplatform.experimental.el.query.mapper;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRITE_DATES_AS_TIMESTAMPS_DISABLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@Component(value = "gpElasticSearchQueryMapper")
public class GPElasticSearchQueryMapper extends GPBaseMapper<GPElasticSearchQuery> {

    public GPElasticSearchQueryMapper() {
        super(GPElasticSearchQuery.class, new GPJacksonSupport()
                .registerModule(new JodaModule())
                .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
                .configure(GPJsonIncludeFeature.NON_NULL));
    }

    /**
     * @return {@link String} Mapper Name
     */
    @Override
    public String getMapperName() {
        return "GeoPlatform ElasticSearch Query Mapper";
    }

    @Override
    public String toString() {
        return this.getMapperName();
    }
}
