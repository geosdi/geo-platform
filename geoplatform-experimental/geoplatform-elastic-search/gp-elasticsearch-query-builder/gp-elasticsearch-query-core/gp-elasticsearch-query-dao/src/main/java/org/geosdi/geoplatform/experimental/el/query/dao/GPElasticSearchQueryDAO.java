package org.geosdi.geoplatform.experimental.el.query.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.dao.AbstractElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el.search.phrase.GPPhrasePrefixSearch;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.sort.SortOrder.DESC;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.MUST;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPElasticSearchQueryDAO<Q extends GPElasticSearchQuery> extends AbstractElasticSearchDAO<Q>
        implements IGPElasticSearchQueryDAO<Q> {

    /**
     * @param queryName
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findByQueryName(String queryName, @Nullable Integer from,
            @Nullable Integer size) throws Exception {
        Preconditions.checkArgument(((queryName != null) && !(queryName.isEmpty())), "The Parameter " +
                "Query Name must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch(super.getJsonRootName()
                .concat(".queryName"), queryName, MUST)));
    }

    /**
     * @param queryDescription
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findByQueryDescription(String queryDescription, @Nullable Integer from,
            @Nullable Integer size) throws Exception {
        Preconditions.checkArgument(((queryDescription != null) && !(queryDescription.isEmpty())), "The Parameter " +
                "Query Description must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch(super.getJsonRootName()
                .concat(".description"), queryDescription, MUST)));
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
    public IPageResult<Q> findByQueryCreationDate(DateTime fromDate, DateTime toDate,
            @Nullable Integer from, @Nullable Integer size) throws Exception {
        Preconditions.checkArgument((fromDate != null), "The Parameter From Must not be null.");
        Preconditions.checkArgument((toDate != null), "The Parameter To Must not be null.");
        Preconditions.checkArgument(fromDate.isBefore(toDate), "The Parameter From must be Before of To");
        String field = super.getJsonRootName().concat(".creationDate");
        return super.find(new MultiFieldsSearch(field, SortOrder.DESC, from, size, new GPDateQuerySearch(field,
                MUST, fromDate, toDate)));
    }

    /**
     * @return {@link List<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public List<Q> findLasts() throws Exception {
        logger.debug("###############Try to find Lasts 10 GPElasticSearchQuery, orderBy " +
                "creationDate #SortOrder.DESC.\n\n");
        return super.find(new QueriableSortablePage(super.getJsonRootName().concat(".creationDate"), DESC,
                matchAllQuery())).getResults();
    }
}
