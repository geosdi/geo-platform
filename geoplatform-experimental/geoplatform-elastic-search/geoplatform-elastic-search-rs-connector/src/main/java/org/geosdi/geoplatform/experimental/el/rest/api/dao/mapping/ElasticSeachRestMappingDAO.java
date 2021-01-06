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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.mapping;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.base.ElasticSearchRestBaseDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.client.RequestOptions.DEFAULT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSeachRestMappingDAO<D extends Document> extends ElasticSearchRestBaseDAO<D> implements GPElasticSeachRestMappingDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSeachRestMappingDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theXContentBuilder
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean putMapping(@Nonnull(when = NEVER) XContentBuilder theXContentBuilder) throws Exception {
        return this.putMapping(theXContentBuilder, this::createPutMappingRequest).isAcknowledged();
    }

    /**
     * @param theXContentBuilder
     * @param theActionListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable putMappingAsync(@Nonnull(when = NEVER) XContentBuilder theXContentBuilder, @Nonnull(when = NEVER) ActionListener<AcknowledgedResponse> theActionListener) throws Exception {
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        GPElasticSearchCheck<PutMappingRequest, XContentBuilder, Exception> putMappingCheck = this::createPutMappingRequest;
        return this.elasticSearchRestHighLevelClient.indices().putMappingAsync(putMappingCheck.apply(theXContentBuilder), DEFAULT, theActionListener);
    }

    /**
     * @return {@link GetMappingsResponse}
     * @throws Exception
     */
    @Override
    public GetMappingsResponse getMapping() throws Exception {
        return this.elasticSearchRestHighLevelClient.indices().getMapping(new GetMappingsRequest().indices(getIndexName()), DEFAULT);
    }

    /**
     * @param theActionListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable getMappingAsync(@Nonnull(when = NEVER) ActionListener<GetMappingsResponse> theActionListener) throws Exception {
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        return this.elasticSearchRestHighLevelClient.indices().getMappingAsync(new GetMappingsRequest().indices(this.getIndexName()), DEFAULT, theActionListener);
    }

    /**
     * @param theXContentBuilder
     * @param theCheck
     * @return {@link AcknowledgedResponse}
     * @throws Exception
     */
    protected <Request extends PutMappingRequest, Builder extends XContentBuilder, E extends Exception> AcknowledgedResponse putMapping(@Nonnull(when = NEVER) Builder theXContentBuilder,
            @Nonnull(when = NEVER) GPElasticSearchCheck<Request, Builder, E> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter theCheck must not be null.");
        return this.elasticSearchRestHighLevelClient.indices().putMapping(theCheck.apply(theXContentBuilder), DEFAULT);
    }

    /**
     * <p>
     * This method must be used for manual Put Mapping.
     * </p>
     *
     * @return {@link XContentBuilder}
     * @throws Exception
     * @TODO think a way to have Introspection to build dinamically Put Mapping.
     */
    protected abstract XContentBuilder preparePutMapping() throws Exception;
}