package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;

import java.nio.file.Path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ElasticSearchDAO<D extends Document> extends GPPageableElasticSearchDAO<D> {

    /**
     * @param <Mapper>
     * @param theMapper
     */
    <Mapper extends GPBaseMapper<D>> void setMapper(Mapper theMapper);

    /**
     * @return {@link String}
     */
    String getIndexName();

    /**
     * @return {@link String}
     */
    String getIndexType();

    /**
     * @return {@link String}
     * @throws Exception
     */
    String getJsonRootName() throws Exception;

    /**
     * @param document
     * @return {@link String}
     * @throws Exception
     */
    String writeDocumentAsString(D document) throws Exception;

    /**
     * @param documentAsString
     * @return {@link D}
     * @throws Exception
     */
    D readDocument(String documentAsString) throws Exception;

    /**
     * @param searchHit
     * @return {@link D}
     * @throws Exception
     */
    default D readDocument(SearchHit searchHit) {
        try {
            Preconditions.checkNotNull(searchHit, "The SearchHit must not be null.");
            D document = readDocument(searchHit.getSourceAsString());
            if (!document.isIdSetted())
                document.setId(searchHit.getId());
            return document;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param thePath
     * @return {}
     * @throws Exception
     */
    D readDocument(Path thePath);

    /**
     * @param response
     * @return {@link D}
     */
    default D readGetResponse(GetResponse response) {
        try {
            Preconditions.checkNotNull(response, "The GetResponse must not be null.");
            D document = readDocument(response.getSourceAsString());
            if (!document.isIdSetted()) {
                document.setId(response.getId());
            }
            return document;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param document
     * @return {@link IndexRequestBuilder}
     */
    default IndexRequestBuilder prepareIndexRequestBuilder(D document) {
        try {
            return (document.isIdSetted() ? client()
                    .prepareIndex(getIndexName(), getIndexType(), document.getId())
                    .setSource(writeDocumentAsString(document)) : client().prepareIndex(getIndexName(), getIndexType())
                    .setSource(writeDocumentAsString(document)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param <IC>
     * @param theIndexCreator
     */
    <IC extends GPIndexCreator> void setIndexCreator(IC theIndexCreator);

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    Boolean existIndex() throws Exception;

    /**
     * <p>Returns the Elastic Search Client</p>
     *
     * @return {@link Client}
     * @throws Exception
     */
    Client client() throws Exception;
}
