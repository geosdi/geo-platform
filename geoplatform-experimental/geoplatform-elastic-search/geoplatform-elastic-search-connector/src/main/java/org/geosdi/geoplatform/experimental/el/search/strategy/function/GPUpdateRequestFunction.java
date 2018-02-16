package org.geosdi.geoplatform.experimental.el.search.strategy.function;

import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static org.elasticsearch.common.xcontent.XContentType.JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPUpdateRequestFunction implements Function<Document, UpdateRequestBuilder> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final ElasticSearchDAO searchDAO;

    public GPUpdateRequestFunction(ElasticSearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    @Override
    public UpdateRequestBuilder apply(Document document) {
        try {
            return this.searchDAO.client().prepareUpdate(this.searchDAO.getIndexName(), this.searchDAO.getIndexType(), document.getId())
                    .setDoc(this.searchDAO.mapper().writeAsString(document), JSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
