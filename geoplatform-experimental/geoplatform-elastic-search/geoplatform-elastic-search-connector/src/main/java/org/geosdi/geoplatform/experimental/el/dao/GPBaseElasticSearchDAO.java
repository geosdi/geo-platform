package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.client.Client;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.configurator.GPIndexConfigurator;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.concurrent.Executor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseElasticSearchDAO<D extends Document> implements GPElasticSearchDAO.GPElasticSearchBaseDAO<D> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GPIndexCreator indexCreator;
    protected GPBaseMapper<D> mapper;
    protected Client elastichSearchClient;
    @Resource(name = "elasticSearchExecutor")
    protected Executor elasticSearchExecutor;

    /**
     * @param document
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeDocumentAsString(D document) throws Exception {
        Preconditions.checkNotNull(document, "The Document must not be null.");
        return this.mapper.writeAsString(document);
    }

    /**
     * @param documentAsString
     * @return {@link D}
     * @throws Exception
     */
    @Override
    public D readDocument(String documentAsString) throws Exception {
        Preconditions.checkArgument(((documentAsString != null) && !(documentAsString.isEmpty())),
                "The String to Wrap must not be null or Empty");
        return mapper.read(documentAsString);
    }

    /**
     * @param thePath
     * @return {}
     * @throws Exception
     */
    @Override
    public D readDocument(Path thePath) {
        Preconditions.checkArgument((thePath != null) && (thePath.toFile().exists()), "The Parameter thePath must " +
                "not be null and the File must exist.");
        try {
            return this.mapper.read(thePath.toFile());
        } catch (Exception ex) {
            logger.error("@@@@@@@@@@@@@@@@@@@@@@@@@Error read path : {} - Exception : {}\n", thePath, ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @return The Index Name
     */
    @Override
    public final String getIndexName() {
        return this.indexCreator.getIndexSettings().getIndexName();
    }

    /**
     * @return The Index Type
     */
    @Override
    public final String getIndexType() {
        return this.indexCreator.getIndexSettings().getIndexType();
    }

    /**
     * <p>
     * Remember Index Creation is called by
     * {@link GPIndexConfigurator#configure()}
     * </p>
     *
     * @throws Exception
     */
    protected final void createIndex() throws Exception {
        this.indexCreator.createIndex();
    }

    /**
     * <p>
     * Dangerous. If called all Data will be dropped
     * </p>
     *
     * @throws Exception
     */
    protected final void deleteIndex() throws Exception {
        this.indexCreator.deleteIndex();
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean existIndex() throws Exception {
        return this.indexCreator.existIndex();
    }

    /**
     * @param theIndexCreator
     */
    @Override
    public <IC extends GPIndexCreator> void setIndexCreator(IC theIndexCreator) {
        this.indexCreator = theIndexCreator;
    }

    /**
     * @return {@link Client}
     * @throws Exception
     */
    @Override
    public final Client client() throws Exception {
        Preconditions.checkNotNull(this.elastichSearchClient, "The Client is null. Check your Configuration.");
        return this.elastichSearchClient;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.mapper, "The Mapper must not be null.");
        Preconditions.checkNotNull(this.indexCreator, "The Index Creator must not be null.");
        this.elastichSearchClient = this.indexCreator.client();
        Preconditions.checkNotNull(this.elastichSearchClient, "The ElasticSearch Client must not be null.");
        Preconditions.checkNotNull(this.elasticSearchExecutor, "The ElasticSearchExecutor must not be null");
    }
}
