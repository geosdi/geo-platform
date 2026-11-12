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
package org.geosdi.geoplatform.experimental.el9.dao;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.store.IPageMapStore;
import org.geosdi.geoplatform.experimental.el.dao.store.IPageStore;
import org.geosdi.geoplatform.experimental.el.dao.store.PageStore;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType;
import org.geosdi.geoplatform.experimental.el9.search.date.IGPDateQuerySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static javax.annotation.meta.When.NEVER;

/**
 * Ricerche a pagine sui documenti di un indice di ElasticSearch 9.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableElasticSearchDAO<D extends Document> {

    /**
     * @param page la pagina, con la query
     * @param <P>  il tipo della pagina
     * @return {@link IPageResult} i documenti della pagina e il totale
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page) throws Exception;

    /**
     * @param page         la pagina, con la query
     * @param thePredicate il filtro applicato ai documenti della pagina, dopo la ricerca
     * @param <P>          il tipo della pagina
     * @return {@link IPageResult} i documenti che passano il filtro e il totale della ricerca
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) PredicateCondition<D> thePredicate) throws Exception;

    /**
     * @param page    la pagina, con la query
     * @param subType il sottotipo da tenere
     * @param <P>     il tipo della pagina
     * @param <V>     il sottotipo
     * @return {@link IPageResult} solo i documenti del sottotipo, con il totale della ricerca
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page, V extends D> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> subType) throws Exception;

    /**
     * @param page   la pagina, con la query
     * @param classe la classe in cui leggere i documenti
     * @param <P>    il tipo della pagina
     * @param <V>    il tipo letto
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page          la pagina, con la query
     * @param includeFields i campi del sorgente da restituire; null vuol dire tutti
     * @param excludeFields i campi del sorgente da togliere; null vuol dire nessuno
     * @param <P>           il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @param page          la pagina, con la query
     * @param includeFields i campi del sorgente da restituire; null vuol dire tutti
     * @param excludeFields i campi del sorgente da togliere; null vuol dire nessuno
     * @param classe        la classe in cui leggere i documenti
     * @param <P>           il tipo della pagina
     * @param <V>           il tipo letto
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page, V extends Document> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields,
            @Nullable String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page          la pagina, con la query
     * @param includeFields i campi del sorgente da restituire; null vuol dire tutti
     * @param excludeFields i campi del sorgente da togliere; null vuol dire nessuno
     * @param classe        la classe in cui leggere i sorgenti, anche diversa da un {@link Document}
     * @param <P>           il tipo della pagina
     * @param <V>           il tipo letto
     * @return {@link PageStore}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page, V> PageStore<V> findAndMappingWithClass(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields,
            @Nullable String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page         la pagina, con la query
     * @param includeField il campo del sorgente da restituire; null vuol dire tutti
     * @param excludeField il campo del sorgente da togliere; null vuol dire nessuno
     * @param <P>          il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nullable String includeField, @Nullable String excludeField) throws Exception;

    /**
     * @param page         la pagina, con la query
     * @param aggregations le aggregazioni, per nome
     * @param <P>          il tipo della pagina
     * @return {@link SearchResponse} la risposta completa, con le aggregazioni e i sorgenti come {@link JsonData}
     * @throws Exception se la ricerca fallisce
     */
    <P extends Page> SearchResponse<JsonData> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Map<String, Aggregation> aggregations) throws Exception;

    /**
     * @param page la pagina: conta solo la sua query
     * @param <P>  il tipo della pagina
     * @return {@link Long} il numero dei documenti che soddisfano la query
     * @throws Exception se il conteggio fallisce
     */
    <P extends Page> Long count(@Nonnull(when = NEVER) P page) throws Exception;

    /**
     * I documenti di una pagina e il totale della ricerca.
     *
     * @param <D> il tipo dei documenti
     */
    interface IPageResult<D> extends Serializable {

        /**
         * @return {@link Long} il numero dei documenti che soddisfano la ricerca
         */
        Long getTotal();

        /**
         * @return {@link List} i documenti della pagina
         */
        List<D> getResults();

        /**
         * @param <D> il tipo dei documenti
         * @return {@link IPageResult} una pagina vuota
         */
        static <D> IPageResult<D> empty() {
            return new PageResult<>(0L, List.of());
        }

        /**
         * @return {@link IPageStore}
         */
        @JsonIgnore
        default IPageStore<D> toStore() {
            return new PageStore<>(getTotal(), getResults());
        }

        /**
         * @param theFunction la conversione di ogni documento
         * @param <T>         il tipo convertito
         * @return {@link IPageStore} i documenti convertiti, senza i null
         */
        @JsonIgnore
        default <T> IPageStore<T> toSubStore(@Nonnull(when = NEVER) Function<D, T> theFunction) {
            checkArgument(theFunction != null, "Il parametro function non deve essere null.");
            return new PageStore<>(getTotal(), getResults().stream()
                    .filter(Objects::nonNull)
                    .map(theFunction)
                    .toList());
        }

        /**
         * @param theCheck la funzione che raggruppa i documenti
         * @param <K>      il tipo delle chiavi
         * @return {@link IPageMapStore}
         * @throws Exception se il raggruppamento fallisce
         */
        @JsonIgnore
        default <K> IPageMapStore<K, D> toMapStore(@Nonnull(when = NEVER) GPElasticSearchCheck<Map<K, List<D>>, IPageResult<D>, Exception> theCheck) throws Exception {
            checkArgument(theCheck != null, "Il parametro check non deve essere null.");
            return IPageMapStore.of(theCheck.apply(this));
        }
    }

    /**
     * Applica a una ricerca la paginazione, l'ordinamento e la query.
     */
    interface PageBuilder {

        /**
         * @param builder il builder della ricerca
         * @return {@link SearchRequest.Builder} lo stesso builder
         * @throws Exception se la query non si costruisce
         */
        SearchRequest.Builder buildPage(@Nonnull(when = NEVER) SearchRequest.Builder builder) throws Exception;

        /**
         * @return {@link Query} la query della pagina, null se la pagina non ne ha (cioè tutti i documenti)
         */
        @Nullable
        Query toQuery();
    }

    /**
     * Pagina con più ricerche combinate in una query bool.
     */
    interface MultiFieldPageBuilder extends PageBuilder {

        /**
         * @return {@link BoolQuery} una query bool nuova a ogni chiamata
         */
        BoolQuery toBoolQuery();

        /**
         * @return {@link String} la query bool in JSON, per i log e i test
         */
        default String printQueryAsJson() {
            return JsonpUtils.toString(this.toBoolQuery()._toQuery());
        }

        /**
         * @param builder il builder della query bool
         * @param type    la parte della query bool
         * @param query   la clausola
         */
        static void addClause(@Nonnull(when = NEVER) BoolQuery.Builder builder, @Nonnull(when = NEVER) BooleanQueryType type,
                @Nonnull(when = NEVER) Query query) {
            checkArgument(builder != null, "Il parametro builder non deve essere null.");
            checkArgument(type != null, "Il parametro type non deve essere null.");
            checkArgument(query != null, "Il parametro query non deve essere null.");
            switch (type) {
                case SHOULD -> builder.should(query);
                case MUST -> builder.must(query);
                case MUST_NOT -> builder.mustNot(query);
                case FILTER -> builder.filter(query);
            }
        }
    }

    /**
     * Pagina senza query: con size zero non pagina e ElasticSearch restituisce i primi 10 documenti.
     */
    @Getter
    @ToString
    @Immutable
    class Page implements PageBuilder {

        protected static final Logger logger = LoggerFactory.getLogger(Page.class);
        //
        private final int from;
        private final int size;

        /**
         * @param theFrom il primo documento, da zero; i negativi valgono come positivi
         * @param theSize quanti documenti; zero vuol dire nessuna paginazione
         */
        public Page(int theFrom, int theSize) {
            this.from = Math.abs(theFrom);
            this.size = Math.abs(theSize);
        }

        /**
         * @param builder il builder della ricerca
         * @return {@link SearchRequest.Builder} lo stesso builder
         * @throws Exception se la query non si costruisce
         */
        @Override
        public SearchRequest.Builder buildPage(@Nonnull(when = NEVER) SearchRequest.Builder builder) throws Exception {
            checkArgument(builder != null, "Il parametro builder non deve essere null.");
            if (this.size > 0) {
                builder.from(this.from).size(this.size);
            }
            this.sort(builder);
            Query query = this.toQuery();
            if (query != null) {
                logger.trace("##################{} - query: {}\n", getClass().getSimpleName(), query);
                builder.query(query);
            }
            return builder;
        }

        /**
         * @return {@link Query} null: la pagina base non ha query
         */
        @Nullable
        @Override
        public Query toQuery() {
            return null;
        }

        /**
         * Aggiunge l'ordinamento; la pagina base non ordina.
         *
         * @param builder il builder della ricerca
         */
        protected void sort(@Nonnull(when = NEVER) SearchRequest.Builder builder) {
        }
    }

    /**
     * Pagina ordinata su un campo.
     */
    @Getter
    @ToString(callSuper = true)
    @Immutable
    class SortablePage extends Page {

        private final String field;
        private final SortOrder sortOrder;

        /**
         * Pagina ordinata senza paginazione: ElasticSearch restituisce i primi 10 documenti.
         *
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         */
        public SortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder) {
            this(theField, theSortOrder, 0, 0);
        }

        /**
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         * @param theFrom      il primo documento, da zero
         * @param theSize      quanti documenti; zero vuol dire nessuna paginazione
         */
        public SortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder, int theFrom, int theSize) {
            super(theFrom, theSize);
            this.field = theField;
            this.sortOrder = theSortOrder;
        }

        /**
         * @param builder il builder della ricerca
         */
        @Override
        protected void sort(@Nonnull(when = NEVER) SearchRequest.Builder builder) {
            if ((this.field != null) && !(this.field.trim().isEmpty()) && (this.sortOrder != null)) {
                builder.sort(s -> s.field(f -> f.field(this.field).order(this.sortOrder)));
            }
        }
    }

    /**
     * Pagina ordinata con una query già costruita.
     */
    @Getter
    @ToString(callSuper = true)
    @Immutable
    class QueriableSortablePage extends SortablePage {

        private final Query query;

        /**
         * @param theQuery la query; null vuol dire tutti i documenti
         */
        public QueriableSortablePage(@Nullable Query theQuery) {
            this(0, 0, theQuery);
        }

        /**
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         * @param theQuery     la query; null vuol dire tutti i documenti
         */
        public QueriableSortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder, @Nullable Query theQuery) {
            this(theField, theSortOrder, 0, 0, theQuery);
        }

        /**
         * @param theFrom  il primo documento, da zero
         * @param theSize  quanti documenti; zero vuol dire nessuna paginazione
         * @param theQuery la query; null vuol dire tutti i documenti
         */
        public QueriableSortablePage(int theFrom, int theSize, @Nullable Query theQuery) {
            this(null, null, theFrom, theSize, theQuery);
        }

        /**
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         * @param theFrom      il primo documento, da zero
         * @param theSize      quanti documenti; zero vuol dire nessuna paginazione
         * @param theQuery     la query; null vuol dire tutti i documenti
         */
        public QueriableSortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder, int theFrom, int theSize,
                @Nullable Query theQuery) {
            super(theField, theSortOrder, theFrom, theSize);
            this.query = theQuery;
        }

        /**
         * @return {@link Query}
         */
        @Nullable
        @Override
        public Query toQuery() {
            return this.query;
        }
    }

    /**
     * Pagina ordinata con più ricerche, combinate in una query bool secondo il tipo di ciascuna.
     */
    @Getter
    @ToString(callSuper = true)
    @Immutable
    class MultiFieldsSearch extends SortablePage implements MultiFieldPageBuilder {

        private final List<IBooleanSearch> queryList;

        /**
         * @param theQueryList le ricerche; nessuna vuol dire tutti i documenti
         */
        public MultiFieldsSearch(@Nullable IBooleanSearch... theQueryList) {
            this(null, null, 0, 0, theQueryList);
        }

        /**
         * @param theFrom      il primo documento, da zero
         * @param theSize      quanti documenti; zero vuol dire nessuna paginazione
         * @param theQueryList le ricerche; nessuna vuol dire tutti i documenti
         */
        public MultiFieldsSearch(int theFrom, int theSize, @Nullable IBooleanSearch... theQueryList) {
            this(null, null, theFrom, theSize, theQueryList);
        }

        /**
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         * @param theQueryList le ricerche; nessuna vuol dire tutti i documenti
         */
        public MultiFieldsSearch(@Nullable String theField, @Nullable SortOrder theSortOrder, @Nullable IBooleanSearch... theQueryList) {
            this(theField, theSortOrder, 0, 0, theQueryList);
        }

        /**
         * @param theField     il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder il verso; null vuol dire nessun ordinamento
         * @param theFrom      il primo documento, da zero
         * @param theSize      quanti documenti; zero vuol dire nessuna paginazione
         * @param theQueryList le ricerche; nessuna vuol dire tutti i documenti
         */
        public MultiFieldsSearch(@Nullable String theField, @Nullable SortOrder theSortOrder, int theFrom, int theSize,
                @Nullable IBooleanSearch... theQueryList) {
            super(theField, theSortOrder, theFrom, theSize);
            this.queryList = ((theQueryList != null) ? stream(theQueryList).filter(Objects::nonNull).toList() : List.of());
        }

        /**
         * @return {@link BoolQuery}
         */
        @Override
        public BoolQuery toBoolQuery() {
            BoolQuery.Builder builder = new BoolQuery.Builder();
            this.queryList.forEach(search -> MultiFieldPageBuilder.addClause(builder, search.getType(), search.buildQuery()));
            return builder.build();
        }

        /**
         * @return {@link Query} la query bool, null se non ci sono ricerche
         */
        @Nullable
        @Override
        public Query toQuery() {
            return (this.queryList.isEmpty() ? null : this.toBoolQuery()._toQuery());
        }
    }

    /**
     * Pagina ordinata con più ricerche per data; ogni ricerca va nella parte della bool indicata dal suo tipo.
     */
    @Getter
    @ToString(callSuper = true)
    @Immutable
    class DateRangeSortablePage extends MultiFieldsSearch {

        /**
         * @param theDateQuerySearch le ricerche per data
         */
        public DateRangeSortablePage(@Nullable IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, 0, 0, theDateQuerySearch);
        }

        /**
         * @param theFrom            il primo documento, da zero
         * @param theSize            quanti documenti; zero vuol dire nessuna paginazione
         * @param theDateQuerySearch le ricerche per data
         */
        public DateRangeSortablePage(int theFrom, int theSize, @Nullable IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, theFrom, theSize, theDateQuerySearch);
        }

        /**
         * @param theField           il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder       il verso; null vuol dire nessun ordinamento
         * @param theDateQuerySearch le ricerche per data
         */
        public DateRangeSortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder, @Nullable IGPDateQuerySearch... theDateQuerySearch) {
            this(theField, theSortOrder, 0, 0, theDateQuerySearch);
        }

        /**
         * @param theField           il campo dell'ordinamento; null vuol dire nessun ordinamento
         * @param theSortOrder       il verso; null vuol dire nessun ordinamento
         * @param theFrom            il primo documento, da zero
         * @param theSize            quanti documenti; zero vuol dire nessuna paginazione
         * @param theDateQuerySearch le ricerche per data
         */
        public DateRangeSortablePage(@Nullable String theField, @Nullable SortOrder theSortOrder, int theFrom, int theSize,
                @Nullable IGPDateQuerySearch... theDateQuerySearch) {
            super(theField, theSortOrder, theFrom, theSize, theDateQuerySearch);
        }
    }
}
