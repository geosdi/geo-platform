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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.index;

import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.mapping.GPElasticSearchRestMappingDAO;

import java.util.concurrent.CompletableFuture;

/**
 * Gestione dell'indice del DAO.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestIndexDAO<D extends Document> extends GPElasticSearchRestMappingDAO<D> {

    /**
     * Crea l'indice, con il mapping se il documento lo chiede.
     *
     * @return {@link Boolean} true se l'ha creato, false se c'era già
     * @throws Exception se la richiesta fallisce
     */
    Boolean createIndex() throws Exception;

    /**
     * @return {@link CompletableFuture} true se l'ha creato, false se c'era già
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<Boolean> createIndexAsync() throws Exception;

    /**
     * @return {@link Boolean} true se ElasticSearch ha cancellato l'indice
     * @throws Exception se la richiesta fallisce, anche se l'indice non c'è
     */
    Boolean deleteIndex() throws Exception;

    /**
     * @return {@link CompletableFuture} true se ElasticSearch ha cancellato l'indice
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<Boolean> deleteIndexAsync() throws Exception;

    /**
     * @return {@link Boolean} true se l'indice c'è
     * @throws Exception se la richiesta fallisce
     */
    Boolean existIndex() throws Exception;

    /**
     * Rende subito visibili alle ricerche i documenti appena scritti.
     *
     * @throws Exception se la richiesta fallisce
     */
    void refreshIndex() throws Exception;

    /**
     * @return {@link CompletableFuture} completato quando il refresh è fatto
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<Void> refreshIndexAsync() throws Exception;
}
