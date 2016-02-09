package org.geosdi.geoplatform.experimental.el.sequence;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPElasticSearchMapper;
import org.geosdi.geoplatform.experimental.el.api.model.sequence.GPSequence;
import org.geosdi.geoplatform.experimental.el.api.model.sequence.IGPSequence;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.slf4j.Logger;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPSequenceGenerator implements IGPSequenceGenerator {

    private static final JacksonSupport mapper = GPElasticSearchMapper.DEFAULT_MAPPER;
    //
    @GeoPlatformLog
    protected Logger logger;
    //
    @Resource(name = "elastichSearchClient")
    private Client elastichSearchClient;
    private IGPSequence gpSequence;

    /**
     * @throws Exception
     */
    @Override
    public synchronized void deleteSequence() throws Exception {
        if (existSequence()) {
            this.elastichSearchClient.admin().indices().prepareDelete(
                    BaseSequenceSettings.GP_SEQUENCE.getSequenceName()).get();
            logger.debug("#########################SEQUENCE_NAME : {} - SEQUENCE_TYPE : {} deleted.\n",
                    BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                    BaseSequenceSettings.GP_SEQUENCE.getSequenceType());
        } else {
            logger.debug("@@@@@@@@@@@@@@@@@@@@SEQUENCE_NAME : {} - SEQUENCE_TYPE : {} doesn't exist.\n",
                    BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                    BaseSequenceSettings.GP_SEQUENCE.getSequenceType());
        }
    }

    /**
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public synchronized Long nextID() throws Exception {
        return (existSequence() ? updateSequence() : createSequence());
    }

    /**
     * @return {@link Long}
     * @throws Exception
     */
    Long createSequence() throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@SEQUENCE_NAME : {} - SEQUENCE_TYPE : {} doesn't "
                        + "exist, so i will create it.\n", BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                BaseSequenceSettings.GP_SEQUENCE.getSequenceType());
        this.elastichSearchClient.admin().indices()
                .prepareCreate(BaseSequenceSettings.GP_SEQUENCE.getSequenceName()).get();
        IndexResponse response = this.elastichSearchClient
                .prepareIndex(BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                        BaseSequenceSettings.GP_SEQUENCE.getSequenceType(), this.gpSequence.getSequenceId())
                .setSource(mapper.getDefaultMapper().writeValueAsString(this.gpSequence))
                .get();
        this.gpSequence.setVersion(response.getVersion());
        return response.getVersion();
    }

    /**
     * @return {@link Long}
     * @throws Exception
     */
    Long updateSequence() throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@SEQUENCE_NAME : {} - SEQUENCE_TYPE : {} already "
                        + "exist, so i will update it.\n", BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                BaseSequenceSettings.GP_SEQUENCE.getSequenceType());
        UpdateResponse updateResponse = this.elastichSearchClient
                .prepareUpdate(BaseSequenceSettings.GP_SEQUENCE.getSequenceName(),
                        BaseSequenceSettings.GP_SEQUENCE.getSequenceType(),
                        this.gpSequence.getSequenceId())
                .setDoc(mapper.getDefaultMapper().writeValueAsString(this.gpSequence))
                .get();
        this.gpSequence.setVersion(updateResponse.getVersion());
        return updateResponse.getVersion();
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    Boolean existSequence() throws Exception {
        return this.elastichSearchClient
                .admin().indices()
                .prepareExists(BaseSequenceSettings.GP_SEQUENCE.getSequenceName())
                .get().isExists();
    }

    /**
     * @return {@link IGPSequence}
     */
    @Override
    public IGPSequence getSequenceModel() {
        return this.gpSequence;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(elastichSearchClient, "The ElasticSearch "
                + "Client must not be null.");
        Preconditions.checkNotNull(logger, "The GeoPlatform Log must not be "
                + "null.");
        Preconditions.checkArgument((getSequenceID() != null) && !(getSequenceID().isEmpty()),
                "The SequenceID must not be null or an Empty String.");
        this.gpSequence = new GPSequence(getSequenceID());
    }
}
