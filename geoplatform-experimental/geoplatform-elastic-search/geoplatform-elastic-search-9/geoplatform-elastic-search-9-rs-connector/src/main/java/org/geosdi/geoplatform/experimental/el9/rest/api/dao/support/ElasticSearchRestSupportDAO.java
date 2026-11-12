/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.support;

import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpMapper;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.support.GPElasticSearchRestSupportDAO;
import org.geosdi.geoplatform.experimental.el.rest.api.index.settings.GPElasticSearchRestIndexSettings;
import org.geosdi.geoplatform.experimental.el.rest.api.mapper.GPElasticSearchRestMapper;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Base dei DAO per ElasticSearch 9: il mapper dei documenti, le impostazioni dell'indice e la conversione tra i
 * documenti e il JSON che passa per il client.
 * <p>I documenti si scrivono e si leggono con il mapper di geoplatform-jackson-support configurato dal DAO (con il
 * suo nome radice, se c'è), come nel connettore per ElasticSearch 7: al client arriva il JSON già fatto, in un
 * {@link JsonData}. Così i documenti già salvati con la 7 si leggono uguali.</p>
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestSupportDAO<D extends Document> implements GPElasticSearchRestSupportDAO<D> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final GPElasticSearchCheck<GPElasticSearchRestIndexSettings, Class<D>, Exception> settingsCheck;
    private GPElasticSearchRestIndexSettings settings;
    protected final GPElasticSearchRestMapper<D> elasticSearchRestMapper;

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default di geoplatform-jackson-support
     */
    protected ElasticSearchRestSupportDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        checkArgument(theEntityClass != null, "Il parametro entityClass non deve essere null.");
        this.elasticSearchRestMapper = new GPElasticSearchRestMapper<D>(theEntityClass, theJacksonSupport) {

            private String mapperName;

            /**
             * @return {@link String}
             */
            @Override
            public String getMapperName() {
                try {
                    return this.mapperName = ((this.mapperName != null) ? this.mapperName :
                            this.mapperName(ElasticSearchRestSupportDAO.this.getClass().getSimpleName(), this::createMapperName));
                } catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }
        };
        this.settingsCheck = GPElasticSearchRestIndexSettings::of;
    }

    /**
     * @param <Settings> il tipo delle impostazioni
     * @return {@link Settings} le impostazioni dell'indice, lette dall'annotazione del documento
     * @throws Exception se le impostazioni non si leggono
     */
    @Override
    public <Settings extends GPElasticSearchRestIndexSettings> Settings getSettings() throws Exception {
        return (Settings) (this.settings = ((this.settings != null) ? this.settings : this.settingsCheck.apply(this.elasticSearchRestMapper.getEntityClass())));
    }

    /**
     * @return {@link GPElasticSearchRestMapper}
     */
    @Override
    public GPElasticSearchRestMapper<D> mapper() {
        return this.elasticSearchRestMapper;
    }

    /**
     * @return {@link String} il prefisso dei campi del documento ({@code NomeRadice.}), vuoto senza nome radice
     */
    public final String getJsonRootName() {
        return this.elasticSearchRestMapper.getJsonRootName();
    }

    /**
     * @return {@link JsonpMapper} il mapper del client, per convertire i {@link JsonData}
     */
    protected abstract JsonpMapper jsonpMapper();

    /**
     * @param theDocument il documento
     * @return {@link JsonData} il JSON del documento scritto dal mapper del DAO
     * @throws Exception se il documento non si scrive
     */
    protected JsonData writeDocument(@Nonnull(when = NEVER) Object theDocument) throws Exception {
        checkArgument(theDocument != null, "Il parametro document non deve essere null.");
        return JsonData.fromJson(this.elasticSearchRestMapper.writeValue(theDocument));
    }

    /**
     * @param theSource il sorgente restituito da ElasticSearch
     * @return {@link String} il JSON del sorgente, null se il sorgente non c'è
     */
    protected String toJson(@Nullable JsonData theSource) {
        return ((theSource != null) ? theSource.toJson(this.jsonpMapper()).toString() : null);
    }

    /**
     * Legge un documento; se il JSON non ha l'id, usa quello di ElasticSearch.
     *
     * @param theID     l'id del documento in ElasticSearch
     * @param theSource il sorgente
     * @return {@link D} il documento, null se il sorgente non c'è o non si legge
     */
    protected D readDocument(@Nullable String theID, @Nullable JsonData theSource) {
        return this.readDocument(theID, theSource, this.elasticSearchRestMapper.getEntityClass());
    }

    /**
     * Legge un documento in una classe scelta; se il JSON non ha l'id, usa quello di ElasticSearch.
     *
     * @param theID     l'id del documento in ElasticSearch
     * @param theSource il sorgente
     * @param theClass  la classe in cui leggere
     * @param <V>       il tipo letto
     * @return {@link V} il documento, null se il sorgente non c'è o non si legge
     */
    protected <V extends Document> V readDocument(@Nullable String theID, @Nullable JsonData theSource, @Nonnull(when = NEVER) Class<V> theClass) {
        checkArgument(theClass != null, "Il parametro class non deve essere null.");
        V document = this.read(theSource, theClass);
        if ((document != null) && !(document.isIdSetted())) {
            document.setId(theID);
        }
        return document;
    }

    /**
     * @param theSource il sorgente
     * @param theClass  la classe in cui leggere, anche diversa da un {@link Document}
     * @param <V>       il tipo letto
     * @return {@link V} il valore, null se il sorgente non c'è o non si legge
     */
    protected <V> V read(@Nullable JsonData theSource, @Nonnull(when = NEVER) Class<V> theClass) {
        checkArgument(theClass != null, "Il parametro class non deve essere null.");
        String json = this.toJson(theSource);
        if (json == null) {
            return null;
        }
        try {
            return this.elasticSearchRestMapper.read(json, theClass);
        } catch (Exception ex) {
            logger.error("@@@@@@@@@@@@@@@@@@@@@@@@{} - documento non leggibile come {}: {}", getClass().getSimpleName(), theClass.getSimpleName(), ex.toString());
            return null;
        }
    }
}
