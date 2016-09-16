package org.geosdi.geoplatform.experimental.el.query.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.dao.AbstractElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el.search.phrase.GPPhrasePrefixSearch;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.MUST;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "gpElasticSearchQueryDAO")
public class GPElasticSearchQueryDAO extends AbstractElasticSearchDAO<GPElasticSearchQuery>
        implements IGPElasticSearchQueryDAO {

    /**
     * @param queryName
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<GPElasticSearchQuery> findByQueryName(String queryName, @Nullable Integer from,
            @Nullable Integer size) throws Exception {
        Preconditions.checkArgument(((queryName != null) && !(queryName.isEmpty())), "The Parameter " +
                "Query Name must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch("gpElasticSearchQuery.queryName",
                queryName, MUST)));
    }

    /**
     * @param queryDescription
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<GPElasticSearchQuery> findByQueryDescription(String queryDescription, @Nullable Integer from,
            @Nullable Integer size) throws Exception {
        Preconditions.checkArgument(((queryDescription != null) && !(queryDescription.isEmpty())), "The Parameter " +
                "Query Description must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch("gpElasticSearchQuery.description",
                queryDescription, MUST)));
    }

    /**
     * @param fromDate
     * @param toDate
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<GPElasticSearchQuery> findByQueryCreationDate(DateTime fromDate, DateTime toDate,
            @Nullable Integer from, @Nullable Integer size) throws Exception {
        Preconditions.checkArgument((fromDate != null), "The Parameter From Must not be null.");
        Preconditions.checkArgument((toDate != null), "The Parameter To Must not be null.");
        Preconditions.checkArgument(fromDate.isBefore(toDate), "The Parameter From must be Before of To");
        return super.find(new MultiFieldsSearch(from, size, new GPDateQuerySearch("gpElasticSearchQuery.description",
                MUST, fromDate, toDate)));
    }

    /**
     * @return {@link List<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public List<GPElasticSearchQuery> findLasts() throws Exception {
        logger.debug("###############Try to find Lasts 10 GPElasticSearchQuery, orderBy " +
                "creationDate #SortOrder.DESC.\n\n");
        return super.find(new QueriableSortablePage("gpElasticSearchQuery.creationDate", SortOrder.DESC,
                matchAllQuery())).getResults();
    }

    /**
     * @param theMapper
     */
    @Resource(name = "gpElasticSearchQueryMapper")
    @Override
    public <Mapper extends GPBaseMapper<GPElasticSearchQuery>> void setMapper(Mapper theMapper) {
        this.mapper = theMapper;
    }

    /**
     * @param theIndexCreator
     */
    @Resource(name = "gpElasticSearchQueryIndexCreator")
    @Override
    public <IC extends GPIndexCreator> void setIndexCreator(IC theIndexCreator) {
        super.setIndexCreator(theIndexCreator);
    }
}
