package org.geosdi.geoplatform.experimental.el.rest.api.dao.async;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.find.GPElasticSearchRestFindDAO;

import javax.annotation.Nonnull;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestAsyncDAO<D extends Document> extends GPElasticSearchRestFindDAO<D> {

    /**
     * @param document
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable persistAsync(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param document
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param document
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) D document, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception;

    /**
     * @param theID
     * @param theProperties
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception;

    /**
     * @param theID
     * @param theProperties
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception;

    /**
     * @param theID
     * @param theXcontetBuilder
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder) throws Exception;

    /**
     * @param theID
     * @param theXcontetBuilder
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param <R>
     * @param <V>
     * @return {@link Cancellable}
     * @throws Exception
     */
    <R extends UpdateByQueryRequest, V extends Object> Cancellable updateByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param theActionListener
     * @param <R>
     * @param <V>
     * @return {@link Cancellable}
     * @throws Exception
     */
    <R extends UpdateByQueryRequest, V extends Object> Cancellable updateByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck, @Nonnull(when = NEVER) ActionListener<BulkByScrollResponse> theActionListener) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param <R>
     * @param <V>
     * @return {@link Cancellable}
     * @throws Exception
     */
    <R extends DeleteByQueryRequest, V extends Object> Cancellable deleteByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param theActionListener
     * @param <R>
     * @param <V>
     * @return {@link Cancellable}
     * @throws Exception
     */
    <R extends DeleteByQueryRequest, V extends Object> Cancellable deleteByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck, @Nonnull(when = NEVER) ActionListener<BulkByScrollResponse> theActionListener) throws Exception;

    /**
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    Cancellable countAsync(@Nonnull(when = NEVER) ActionListener<CountResponse> theListener) throws Exception;
}