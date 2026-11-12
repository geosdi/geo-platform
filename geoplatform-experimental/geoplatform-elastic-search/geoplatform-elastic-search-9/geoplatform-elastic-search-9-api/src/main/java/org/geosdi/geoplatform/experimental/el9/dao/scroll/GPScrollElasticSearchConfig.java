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
package org.geosdi.geoplatform.experimental.el9.dao.scroll;

import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.scroll.GPScrollElasticSearchCallback;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.Page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Duration;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * Configurazione di una lettura con lo scroll: la query, quanto resta aperto il contesto tra una richiesta e
 * l'altra, quanti documenti per blocco, cosa fare con ogni blocco e alla fine.
 *
 * @param <P> il tipo della pagina con la query
 * @param <D> il tipo dei documenti
 * @param <R> il tipo restituito dalla funzione su ogni blocco
 * @param <E> l'eccezione della funzione
 * @param <C> il tipo della funzione
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public sealed interface GPScrollElasticSearchConfig<P extends Page, D extends Document, R, E extends Exception, C extends GPElasticSearchCheck<R, List<D>, E>> permits ScrollElasticSearchConfig {

    /**
     * @return {@link P} la pagina con la query; paginazione e ordinamento della pagina non contano
     */
    P toPage();

    /**
     * @return {@link Duration} quanto resta aperto il contesto dello scroll tra una richiesta e l'altra
     */
    Duration toKeepAlive();

    /**
     * @return {@link Integer} quanti documenti per blocco
     */
    Integer toSize();

    /**
     * @return {@link C} la funzione chiamata su ogni blocco
     */
    C toCheckFunction();

    /**
     * @return {@link GPScrollElasticSearchCallback} chiamato alla fine
     */
    GPScrollElasticSearchCallback toScroolElasticSearchCallback();

    /**
     * @param thePage                       la pagina con la query
     * @param theKeepAlive                  quanto resta aperto il contesto, positivo
     * @param theSize                       quanti documenti per blocco, positivo
     * @param theCheckFunction              la funzione chiamata su ogni blocco
     * @param theScroolElasticSearchCallback chiamato alla fine; null vuol dire quello di default, che scrive sul log
     * @param <P>                           il tipo della pagina
     * @param <D>                           il tipo dei documenti
     * @param <R>                           il tipo restituito dalla funzione
     * @param <E>                           l'eccezione della funzione
     * @param <C>                           il tipo della funzione
     * @return {@link GPScrollElasticSearchConfig}
     */
    static <P extends Page, D extends Document, R, E extends Exception, C extends GPElasticSearchCheck<R, List<D>, E>> GPScrollElasticSearchConfig<P, D, R, E, C> toScrollConfig(
            @Nonnull(when = NEVER) P thePage, @Nonnull(when = NEVER) Duration theKeepAlive, @Nonnull(when = NEVER) Integer theSize,
            @Nonnull(when = NEVER) C theCheckFunction, @Nullable GPScrollElasticSearchCallback theScroolElasticSearchCallback) {
        return new ScrollElasticSearchConfig<>(thePage, theKeepAlive, theSize, theCheckFunction, theScroolElasticSearchCallback);
    }
}
