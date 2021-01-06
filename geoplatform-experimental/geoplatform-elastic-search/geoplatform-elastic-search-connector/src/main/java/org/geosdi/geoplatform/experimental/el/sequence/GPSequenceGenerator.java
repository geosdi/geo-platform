/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.sequence;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.VersionType;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPElasticSearchMapper;
import org.geosdi.geoplatform.experimental.el.api.model.sequence.GPSequence;
import org.geosdi.geoplatform.experimental.el.api.model.sequence.IGPSequence;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.slf4j.Logger;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.elasticsearch.common.xcontent.XContentType.JSON;

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
                .setSource(mapper.getDefaultMapper().writeValueAsString(this.gpSequence), JSON)
                .setVersionType(VersionType.EXTERNAL)
                .setVersion((isSetInitialValue() ? getInitialValue() : -1l))
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
                .setDoc(mapper.getDefaultMapper().writeValueAsString(this.gpSequence), JSON)
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
        checkNotNull(elastichSearchClient, "The ElasticSearch Client must not be null.");
        checkNotNull(logger, "The GeoPlatform Log must not be null.");
        checkArgument((getSequenceID() != null) && !(getSequenceID().isEmpty()),
                "The SequenceID must not be null or an Empty String.");
        this.gpSequence = new GPSequence(getSequenceID());
        if (existSequence()) {
            GetResponse response = this.elastichSearchClient
                    .prepareGet()
                    .setIndex(BaseSequenceSettings.GP_SEQUENCE.getSequenceName())
                    .setType(BaseSequenceSettings.GP_SEQUENCE.getSequenceType())
                    .setId(this.gpSequence.getSequenceId()).execute().actionGet();
            logger.debug(":::::::::::::::::::::::::::::::::VERSION_FOUND : {}\n", response.getVersion());
            this.gpSequence.setVersion(response.getVersion());
        }
    }
}
