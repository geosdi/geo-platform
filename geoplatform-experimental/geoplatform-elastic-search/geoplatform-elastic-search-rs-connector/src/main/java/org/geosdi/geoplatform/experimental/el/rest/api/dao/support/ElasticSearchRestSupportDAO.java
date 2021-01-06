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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.support;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.search.SearchHit;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.index.settings.GPElasticSearchRestIndexSettings;
import org.geosdi.geoplatform.experimental.el.rest.api.mapper.GPElasticSearchRestMapper;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestSupportDAO<D extends Document> implements GPElasticSearchRestSupportDAO<D> {

    protected static final String EMPTY_JSON = "{}";
    //
    private final GPElasticSearchCheck<GPElasticSearchRestIndexSettings, Class<D>, Exception> settingsCheck;
    private GPElasticSearchRestIndexSettings settings;
    protected final GPElasticSearchRestMapper<D> elasticSearchRestMapper;

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestSupportDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        this.elasticSearchRestMapper = new GPElasticSearchRestMapper<D>(theEntityClass, theJacksonSupport) {

            private String mapperName;

            @Override
            public String getMapperName() {
                try {
                    return this.mapperName = ((this.mapperName != null) ? this.mapperName :
                            this.mapperName(ElasticSearchRestSupportDAO.this.getClass().getSimpleName(), this::createMapperName));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new IllegalStateException(ex);
                }
            }
        };
        this.settingsCheck = GPElasticSearchRestIndexSettings::of;
    }

    /**
     * @return {@link GPElasticSearchRestIndexSettings}
     */
    @Override
    public GPElasticSearchRestIndexSettings getSettings() throws Exception {
        return this.settings = ((this.settings != null) ? this.settings : this.settingsCheck.apply(this.elasticSearchRestMapper.getEntityClass()));
    }

    /**
     * @return {@link GPElasticSearchRestMapper<D>}
     */
    @Override
    public GPElasticSearchRestMapper<D> mapper() {
        return this.elasticSearchRestMapper;
    }

    /**
     * @return {@link String}
     */
    protected final String getJsonRootName() {
        return this.elasticSearchRestMapper.getJsonRootName();
    }

    /**
     * @param documentAsString
     * @return {@link D}
     * @throws Exception
     */
    protected D readDocument(@Nonnull(when = NEVER) String documentAsString) throws Exception {
        checkArgument(((documentAsString != null) && !(documentAsString.trim().isEmpty()) && !(documentAsString.equalsIgnoreCase(EMPTY_JSON))), "The String to Wrap must not be null or Empty");
        return this.elasticSearchRestMapper.read(documentAsString);
    }

    /**
     * @param searchHit
     * @return {@link D}
     */
    protected D readDocument(@Nonnull(when = NEVER) SearchHit searchHit) {
        try {
            checkNotNull(searchHit, "The SearchHit must not be null.");
            D document = readDocument(searchHit.getSourceAsString());
            if (!document.isIdSetted()) {
                document.setId(searchHit.getId());
            }
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
    protected <V extends Document> V readDocument(@Nonnull(when = NEVER) SearchHit searchHit, @Nonnull(when = NEVER) Class<V> classe) {
        try {
            checkNotNull(searchHit, "The SearchHit must not be null.");
            checkNotNull(classe, "The Parameter classe must not be null.");
            V subClass = this.readDocument(searchHit.getSourceAsString(), classe);
            if (!subClass.isIdSetted()) {
                subClass.setId(searchHit.getId());
            }
            return subClass;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param documentAsString
     * @param classe
     * @return {@link V}
     * @throws Exception
     */
    protected <V extends Document> V readDocument(@Nonnull(when = NEVER) String documentAsString, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(((documentAsString != null) && !(documentAsString.trim().isEmpty()) && !(documentAsString.equalsIgnoreCase(EMPTY_JSON))), "The String to Wrap must not be null or Empty");
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return this.elasticSearchRestMapper.read(documentAsString, classe);
    }

    /**
     * @param document
     * @return {@link String}
     * @throws Exception
     */
    protected String writeDocumentAsString(@Nonnull(when = NEVER) D document) throws Exception {
        checkNotNull(document, "The Document must not be null.");
        return this.elasticSearchRestMapper.writeAsString(document);
    }

    /**
     * @param response
     * @return {@link D}
     */
    protected D readGetResponse(@Nonnull(when = NEVER) GetResponse response) {
        try {
            checkNotNull(response, "The GetResponse must not be null.");
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
}