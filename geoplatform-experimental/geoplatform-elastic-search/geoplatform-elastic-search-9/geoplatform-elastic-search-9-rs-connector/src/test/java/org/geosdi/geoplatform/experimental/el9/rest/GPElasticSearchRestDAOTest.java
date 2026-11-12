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
package org.geosdi.geoplatform.experimental.el9.rest;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.UpdateByQueryRequest;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el.dao.store.PageStore;
import org.geosdi.geoplatform.experimental.el9.dao.GPCoupleMultiFieldPageBuilder;
import org.geosdi.geoplatform.experimental.el9.dao.GPCoupleMultiFieldSearch.CoupleMultiFieldSearch;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.DateRangeSortablePage;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.IPageResult;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.Page;
import org.geosdi.geoplatform.experimental.el9.dao.scroll.GPScrollElasticSearchConfig;
import org.geosdi.geoplatform.experimental.el9.rest.api.info.GPElasticSearchRestInfo;
import org.geosdi.geoplatform.experimental.el9.rest.dao.GPPlaceDAO;
import org.geosdi.geoplatform.experimental.el9.rest.model.GPPlace;
import org.geosdi.geoplatform.experimental.el9.rest.model.GPPlaceName;
import org.geosdi.geoplatform.experimental.el9.search.IGPQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanAllMatchSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanFieldExistsSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanMultiMatchSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanPrefixSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el9.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geobbox.IGPGeoBoundingBoxQuerySearch.GPGeoBoundingBoxQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geodistance.IGPGeodistanceQuerySearch.GPGeodistanceQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geopolygon.IGPGeoPolygonQuerySearch.GPGeoPolygonQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geoshape.IGPGeoShapeQuerySearch.GPGeoShapeQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.match.GPMatchQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.phrase.GPPhrasePrefixSearch;
import org.geosdi.geoplatform.experimental.el9.search.phrase.GPPhraseSearch;
import org.geosdi.geoplatform.experimental.el9.search.range.IGPNumberRangeQuerySearch.GPNumberRangeQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.regexp.GPRegexpSearch;
import org.geosdi.geoplatform.experimental.el9.search.string.GPQueryStringSearch;
import org.geosdi.geoplatform.experimental.el9.search.term.GPTermSearch;
import org.geosdi.geoplatform.experimental.el9.search.term.GPTermsSearch;
import org.geosdi.geoplatform.experimental.el9.search.wildcard.GPWildCardSearch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static co.elastic.clients.elasticsearch._types.DistanceUnit.Kilometers;
import static co.elastic.clients.elasticsearch._types.SortOrder.Asc;
import static co.elastic.clients.elasticsearch._types.query_dsl.Operator.And;
import static java.time.Duration.ofMinutes;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.MUST;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.MUST_NOT;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.SHOULD;
import static org.geosdi.geoplatform.experimental.el9.search.geoshape.IGPGeoShapeQuerySearch.GPShapeRelation.DISJOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

/**
 * Il connettore per ElasticSearch 9 su un ElasticSearch 9.5.6 vero, in Docker, in HTTP con utente e password: la
 * password è cifrata con jasypt nel file delle proprietà, come in produzione.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@SpringJUnitConfig(classes = GPElasticSearchRestTestConfig.class)
@DirtiesContext(classMode = AFTER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class GPElasticSearchRestDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRestDAOTest.class);
    //
    private static ElasticsearchContainer container;
    //
    @Autowired
    private GPPlaceDAO placeDAO;

    /**
     * Avvia ElasticSearch e scrive le proprietà prima che Spring crei il contesto.
     *
     * @throws Exception se il container o le proprietà non si preparano
     */
    @BeforeAll
    static void beforeAll() throws Exception {
        container = GPElasticSearchContainerSupport.startContainer(false);
        GPElasticSearchContainerSupport.writeConnectorProperties("http://" + container.getHttpHostAddress(), null, null);
    }

    /**
     * Ferma ElasticSearch.
     */
    @AfterAll
    static void afterAll() {
        if (container != null) {
            container.stop();
        }
    }

    /**
     * Il cluster risponde con utente e password decifrata, ed è un ElasticSearch 9.
     *
     * @throws Exception se la richiesta fallisce
     */
    @Test
    @Order(1)
    void infoTest() throws Exception {
        assertTrue(this.placeDAO.isUpElasticSearchCluster());
        GPElasticSearchRestInfo info = this.placeDAO.info();
        logger.info("#################ElasticSearch: {}", info);
        assertTrue(info.getVersion().getNumber().startsWith("9."));
    }

    /**
     * Creazione dell'indice con il mapping del DAO.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(2)
    void createIndexTest() throws Exception {
        assertFalse(this.placeDAO.existIndex());
        assertTrue(this.placeDAO.createIndex());
        assertTrue(this.placeDAO.existIndex());
        assertFalse(this.placeDAO.createIndex());
        assertEquals("gp_place_9_test", this.placeDAO.getIndexName());
        assertEquals("place.", this.placeDAO.getJsonRootName());
        String mapping = this.placeDAO.loadMappingAsString();
        logger.info("#################Mapping: {}", mapping);
        assertTrue(mapping.contains("geo_point"));
        Map<String, Object> mappingAsMap = this.placeDAO.loadMappingAsMap();
        assertTrue(mappingAsMap.containsKey("properties"));
    }

    /**
     * Scrittura di un documento con l'id, di uno senza (l'id lo sceglie ElasticSearch) e di tre in bulk.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(3)
    void persistTest() throws Exception {
        GPPlace potenza = this.placeDAO.persist(new GPPlace("potenza", "Potenza", "PZ", "Capoluogo della Basilicata", 66000L,
                year(2000), "40.6401,15.8056"));
        assertEquals("potenza", potenza.getId());
        assertThrows(Exception.class, () -> this.placeDAO.persist(new GPPlace("potenza", "Potenza", "PZ", null, 1L, year(2000), null)));
        GPPlace matera = this.placeDAO.persist(new GPPlace(null, "Matera", "MT", "Città dei Sassi in Basilicata", 60000L,
                year(2001), "40.6664,16.6043"));
        assertNotNull(matera.getId());
        this.placeDAO.persist(List.of(
                new GPPlace("bari", "Bari", "BA", "Capoluogo della Puglia", 316000L, year(2002), "41.1171,16.8719"),
                new GPPlace("melfi", "Melfi", "PZ", "Castello normanno", 21000L, year(2003), "40.9963,15.6519"),
                new GPPlace("napoli", "Napoli", "NA", "Capoluogo della Campania", 909000L, year(2004), "40.8518,14.2681")));
        this.placeDAO.refreshIndex();
        assertEquals(5L, this.placeDAO.count());
        GPPlace found = this.placeDAO.findByID("potenza");
        assertEquals("Potenza", found.getName());
        assertEquals(year(2000), found.getFoundation());
        assertNull(this.placeDAO.findByID("roma"));
        // L'id scelto da ElasticSearch finisce anche nel JSON del documento, dentro il nome radice.
        Thread.sleep(1000);
        this.placeDAO.refreshIndex();
        assertEquals(1L, this.placeDAO.count(new GPTermSearch("place.id", matera.getId(), MUST).buildQuery()));
        assertEquals("Matera", this.placeDAO.findByID(matera.getId()).getName());
    }

    /**
     * Ogni ricerca trova i documenti attesi.
     *
     * @throws Exception se una ricerca fallisce
     */
    @Test
    @Order(4)
    void searchTest() throws Exception {
        assertTotal(5, new BooleanAllMatchSearch());
        assertTotal(2, new GPTermSearch("place.code", "PZ", MUST));
        assertTotal(3, new GPTermsSearch("place.code", MUST, "PZ", "MT"));
        assertTotal(2, new GPMatchQuerySearch("place.description", "Basilicata", MUST, null));
        assertTotal(3, new GPPhraseSearch("place.description", "Capoluogo della", MUST));
        assertTotal(1, new GPPhrasePrefixSearch("place.name", "Mat", MUST));
        assertTotal(2, new BooleanPrefixSearch("place.code", "P", MUST));
        assertTotal(2, new GPWildCardSearch("place.code", "*A", MUST));
        assertTotal(1, new GPRegexpSearch("place.code", "M.", MUST));
        assertTotal(1, new GPQueryStringSearch("place.name", "*enza", MUST));
        assertTotal(1, new BooleanMultiMatchSearch("Puglia", MUST, And, new String[]{"place.name", "place.description"}));
        assertTotal(5, new BooleanFieldExistsSearch("place.location", MUST));
        assertTotal(2, new GPNumberRangeQuerySearch("place.population", MUST, 100000, 50000));
        assertTotal(3, new GPDateQuerySearch("place.foundation", MUST, ZonedDateTime.of(2000, 6, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2)),
                ZonedDateTime.of(2003, 6, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2))));
        Envelope basilicata = new Envelope(15.0, 17.0, 40.0, 41.0);
        assertTotal(3, new GPGeoBoundingBoxQuerySearch("place.location", basilicata));
        assertTotal(3, new GPGeodistanceQuerySearch("place.location", new Coordinate(15.8056, 40.6401), 80, Kilometers, null));
        assertTotal(3, new GPGeoPolygonQuerySearch("place.location", List.of(new Coordinate(15, 40), new Coordinate(17, 40),
                new Coordinate(17, 41), new Coordinate(15, 41))));
        assertTotal(2, new GPGeoShapeQuerySearch("place.location", DISJOINT, new GeometryFactory().toGeometry(basilicata)));
        assertEquals(1L, this.placeDAO.find(new GPTermSearch("place.code", "NA", MUST).buildQuery()).getTotal());
    }

    /**
     * Paginazione, ordinamento, clausole MUST_NOT e SHOULD, gruppi di ricerche e conteggio di una pagina.
     *
     * @throws Exception se una ricerca fallisce
     */
    @Test
    @Order(5)
    void pagesTest() throws Exception {
        IPageResult<GPPlace> firstPage = this.placeDAO.find(new MultiFieldsSearch("place.population", Asc, 0, 2, new BooleanAllMatchSearch()));
        assertEquals(5L, firstPage.getTotal());
        assertEquals(List.of("Melfi", "Matera"), firstPage.getResults().stream().map(GPPlace::getName).toList());
        IPageResult<GPPlace> secondPage = this.placeDAO.find(1, 2, "population", Asc);
        assertEquals(List.of("Matera", "Potenza"), secondPage.getResults().stream().map(GPPlace::getName).toList());
        assertEquals(3L, this.placeDAO.find(new MultiFieldsSearch(new GPTermSearch("place.code", "PZ", MUST_NOT))).getTotal());
        CoupleMultiFieldSearch couple = new CoupleMultiFieldSearch("place.name.keyword", Asc, 0, 10, List.of(
                GPCoupleMultiFieldPageBuilder.of(new MultiFieldsSearch(new GPTermSearch("place.code", "PZ", MUST),
                        new GPNumberRangeQuerySearch("place.population", MUST, 100000, 50000)), SHOULD),
                GPCoupleMultiFieldPageBuilder.of(new MultiFieldsSearch(new GPTermSearch("place.code", "BA", MUST)), SHOULD)));
        assertEquals(List.of("Bari", "Potenza"), this.placeDAO.find(couple).getResults().stream().map(GPPlace::getName).toList());
        assertEquals(2L, this.placeDAO.count(couple));
        DateRangeSortablePage dates = new DateRangeSortablePage("place.foundation", Asc, new GPDateQuerySearch("place.foundation", MUST,
                ZonedDateTime.of(2001, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC), ZonedDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)));
        assertEquals(List.of("Bari", "Melfi", "Napoli"), this.placeDAO.find(dates).getResults().stream().map(GPPlace::getName).toList());
        assertEquals(5, this.placeDAO.findLasts().size());
        assertEquals(2L, this.placeDAO.find(new GPTermSearch("place.code", "PZ", MUST).toPage(),
                place -> place.getPopulation() > 50000).getTotal());
        assertEquals(1, this.placeDAO.find(new GPTermSearch("place.code", "PZ", MUST).toPage(),
                place -> place.getPopulation() > 50000).getResults().size());
    }

    /**
     * Letture con solo alcuni campi e per più id.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(6)
    void fieldsAndIdsTest() throws Exception {
        Page page = new GPTermSearch("place.code", "BA", MUST).toPage();
        GPPlace bari = this.placeDAO.find(page, new String[]{"place.name"}, null).getResults().get(0);
        assertEquals("Bari", bari.getName());
        assertEquals("bari", bari.getId());
        assertNull(bari.getCode());
        PageStore<GPPlaceName> names = this.placeDAO.findAndMappingWithClass(new MultiFieldsSearch("place.name.keyword", Asc,
                new GPTermSearch("place.code", "PZ", MUST)), new String[]{"place.name"}, null, GPPlaceName.class);
        assertEquals(List.of("Melfi", "Potenza"), names.getResults().stream().map(GPPlaceName::getName).toList());
        GPPlace potenza = this.placeDAO.findByID("potenza", null, new String[]{"place.description"});
        assertNull(potenza.getDescription());
        assertEquals(66000L, potenza.getPopulation());
        assertEquals(List.of("Bari"), this.placeDAO.findByIDS(List.of("potenza", "bari", "roma", " "), place -> place.getPopulation() > 100000)
                .stream().map(GPPlace::getName).toList());
        assertEquals(2, this.placeDAO.findByIDS(List.of("potenza", "melfi")).size());
        assertTrue(this.placeDAO.findByIDS(List.of()).isEmpty());
    }

    /**
     * Aggregazione per sigla della provincia.
     *
     * @throws Exception se la ricerca fallisce
     */
    @Test
    @Order(7)
    void aggregationTest() throws Exception {
        SearchResponse<JsonData> response = this.placeDAO.find(new Page(0, 0),
                Map.of("codes", Aggregation.of(a -> a.terms(t -> t.field("place.code")))));
        List<StringTermsBucket> buckets = response.aggregations().get("codes").sterms().buckets().array();
        assertEquals("PZ", buckets.get(0).key().stringValue());
        assertEquals(2L, buckets.get(0).docCount());
        assertEquals(4, buckets.size());
    }

    /**
     * Aggiornamento di un documento, di alcuni campi e di più documenti in bulk.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(8)
    void updateTest() throws Exception {
        GPPlace potenza = this.placeDAO.findByID("potenza");
        potenza.setPopulation(67000L);
        IGPUpdateResponse response = this.placeDAO.update(potenza);
        assertTrue(response.getStatus());
        assertEquals(2L, response.getVersion());
        assertEquals(67000L, this.placeDAO.findByID("potenza").getPopulation());
        this.placeDAO.update("bari", Map.of("place", Map.of("population", 320000)));
        GPPlace bari = this.placeDAO.findByID("bari");
        assertEquals(320000L, bari.getPopulation());
        assertEquals("Capoluogo della Puglia", bari.getDescription());
        GPPlace melfi = this.placeDAO.findByID("melfi");
        melfi.setDescription("Castello normanno e cattedrale");
        bari.setDescription("Capoluogo della Puglia, sul mare");
        this.placeDAO.update(List.of(melfi, bari));
        assertEquals("Castello normanno e cattedrale", this.placeDAO.findByID("melfi").getDescription());
        assertThrows(Exception.class, () -> this.placeDAO.update(List.of(new GPPlace("roma", "Roma", "RM", null, 1L, year(2005), null))));
    }

    /**
     * Lettura di tutti i documenti a blocchi, nel thread del test e con RxJava.
     *
     * @throws Exception se lo scroll fallisce
     */
    @Test
    @Order(9)
    void scrollTest() throws Exception {
        this.placeDAO.refreshIndex();
        List<String> names = new ArrayList<>();
        AtomicInteger blocks = new AtomicInteger();
        CountDownLatch completed = new CountDownLatch(1);
        this.placeDAO.findWithScroll(GPScrollElasticSearchConfig.toScrollConfig(new MultiFieldsSearch(new BooleanAllMatchSearch()),
                ofMinutes(1), 2, documents -> {
                    blocks.incrementAndGet();
                    documents.forEach(document -> names.add(document.getName()));
                    return documents.size();
                }, completed::countDown));
        assertEquals(5, names.size());
        assertEquals(3, blocks.get());
        assertEquals(0, completed.getCount());
        List<String> asyncNames = new ArrayList<>();
        CountDownLatch asyncCompleted = new CountDownLatch(1);
        this.placeDAO.findWithRXScrollAsync(GPScrollElasticSearchConfig.toScrollConfig(new GPTermSearch("place.code", "PZ", MUST).toPage(),
                ofMinutes(1), 1, documents -> {
                    documents.forEach(document -> asyncNames.add(document.getName()));
                    return documents.size();
                }, asyncCompleted::countDown));
        assertTrue(asyncCompleted.await(30, SECONDS));
        assertEquals(2, asyncNames.size());
    }

    /**
     * Le operazioni con il client asincrono.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(10)
    void asyncTest() throws Exception {
        GPPlace lecce = this.placeDAO.persistAsync(new GPPlace("lecce", "Lecce", "LE", "Barocco", 95000L, year(2005), "40.3515,18.1750"))
                .get(30, SECONDS);
        assertEquals("lecce", lecce.getId());
        lecce.setPopulation(96000L);
        assertTrue(this.placeDAO.updateAsync(lecce).get(30, SECONDS).getStatus());
        this.placeDAO.refreshIndexAsync().get(30, SECONDS);
        assertEquals(6L, this.placeDAO.countAsync().get(30, SECONDS));
        assertEquals(96000L, this.placeDAO.findByID("lecce").getPopulation());
    }

    /**
     * Aggiornamento e cancellazione con una query, cancellazione per id e dell'indice.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    @Order(11)
    void deleteTest() throws Exception {
        long updated = this.placeDAO.updateByQuery("PZ", code -> UpdateByQueryRequest.of(u -> u.index(this.placeDAO.getIndexName())
                .query(new GPTermSearch("place.code", code, MUST).buildQuery()).refresh(true)
                .script(s -> s.source(src -> src.scriptString("ctx._source.place.population += 1"))))).updated();
        assertEquals(2L, updated);
        assertEquals(67001L, this.placeDAO.findByID("potenza").getPopulation());
        assertEquals(1L, this.placeDAO.deleteByQuery(new GPTermSearch("place.code", "NA", MUST).buildQuery()).deleted());
        assertTrue(this.placeDAO.delete("melfi"));
        assertThrows(IllegalStateException.class, () -> this.placeDAO.delete("roma"));
        this.placeDAO.refreshIndex();
        assertEquals(4L, this.placeDAO.count());
        assertTrue(this.placeDAO.deleteIndex());
        assertFalse(this.placeDAO.existIndex());
    }

    /**
     * @param theExpected il numero atteso
     * @param theSearch   la ricerca
     * @throws Exception se la ricerca fallisce
     */
    private void assertTotal(long theExpected, IBooleanSearch theSearch) throws Exception {
        assertEquals(theExpected, this.placeDAO.find(theSearch.toPage()).getTotal(), () -> "Totale sbagliato per " + theSearch);
        assertEquals(theExpected, this.placeDAO.count(((IGPQuerySearch) theSearch).buildQuery()), () -> "Conteggio sbagliato per " + theSearch);
    }

    /**
     * @param theYear l'anno
     * @return {@link Instant} il primo gennaio dell'anno, a mezzanotte UTC
     */
    private static Instant year(int theYear) {
        return ZonedDateTime.of(theYear, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC).toInstant();
    }
}
