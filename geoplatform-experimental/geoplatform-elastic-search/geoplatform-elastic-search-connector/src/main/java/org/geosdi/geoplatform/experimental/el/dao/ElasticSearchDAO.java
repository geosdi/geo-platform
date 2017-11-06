/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

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
     * @param <IS>
     * @return {@link GPIndexSettings}
     */
    <IS extends GPIndexSettings> IS getIndexSettings();

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
     * @param documentAsString
     * @param classe
     * @param <V>
     * @return {@link V}
     * @throws Exception
     */
    <V extends Document> V readDocument(String documentAsString, Class<V> classe) throws Exception;

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
     * @param searchHit
     * @param classe
     * @param <V>
     * @return {@link V}
     */
    default <V extends Document> V readDocument(SearchHit searchHit, Class<V> classe) {
        try {
            Preconditions.checkNotNull(searchHit, "The SearchHit must not be null.");
            Preconditions.checkNotNull(classe, "The Parameter classe must not be null.");
            V subClass = this.readDocument(searchHit.getSourceAsString(), classe);
            if (!subClass.isIdSetted())
                subClass.setId(searchHit.getId());
            return subClass;
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

    /**
     * @param <E>
     * @return {@link E}
     * @throws Exception
     */
    <E extends ExecutorService> E executor() throws Exception;
}
