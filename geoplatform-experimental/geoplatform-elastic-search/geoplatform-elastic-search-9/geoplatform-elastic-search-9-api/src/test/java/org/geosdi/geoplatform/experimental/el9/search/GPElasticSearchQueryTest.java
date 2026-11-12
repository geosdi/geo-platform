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
package org.geosdi.geoplatform.experimental.el9.search;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.json.JsonpUtils;
import org.geosdi.geoplatform.experimental.el9.dao.GPCoupleMultiFieldPageBuilder;
import org.geosdi.geoplatform.experimental.el9.dao.GPCoupleMultiFieldSearch.CoupleMultiFieldSearch;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.Page;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanAllMatchSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanExactSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanFieldExistsSearch;
import org.geosdi.geoplatform.experimental.el9.search.bool.BooleanMultiMatchSearch;
import org.geosdi.geoplatform.experimental.el9.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geobbox.IGPGeoBoundingBoxQuerySearch.GPGeoBoundingBoxQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geodistance.IGPGeodistanceQuerySearch.GPGeodistanceQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.geopolygon.IGPGeoPolygonQuerySearch.GPGeoPolygonQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.match.GPMatchQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.range.IGPNumberRangeQuerySearch.GPNumberRangeQuerySearch;
import org.geosdi.geoplatform.experimental.el9.search.string.GPQueryStringSearch;
import org.geosdi.geoplatform.experimental.el9.search.term.GPTermSearch;
import org.geosdi.geoplatform.experimental.el9.search.term.GPTermsSearch;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static co.elastic.clients.elasticsearch._types.DistanceUnit.Kilometers;
import static co.elastic.clients.elasticsearch._types.SortOrder.Asc;
import static co.elastic.clients.elasticsearch._types.query_dsl.Operator.And;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.FILTER;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.MUST;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.MUST_NOT;
import static org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch.BooleanQueryType.SHOULD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Controlla il JSON delle query costruite dalle ricerche e dalle pagine, senza ElasticSearch.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPElasticSearchQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryTest.class);

    /**
     * Le ricerche testuali e sui termini.
     */
    @Test
    void textAndTermQueriesTest() {
        assertJson(new BooleanAllMatchSearch().buildQuery(), "{\"match_all\":{}}");
        assertJson(new GPTermSearch("place.code", "PZ", MUST).buildQuery(), "{\"term\":{\"place.code\":{\"value\":\"PZ\"}}}");
        assertJson(new GPTermSearch("place.population", 66000L, MUST).buildQuery(), "{\"term\":{\"place.population\":{\"value\":66000}}}");
        assertJson(new GPTermsSearch("place.code", MUST, "PZ", "MT").buildQuery(), "{\"terms\":{\"place.code\":[\"PZ\",\"MT\"]}}");
        assertJson(new GPMatchQuerySearch("place.name", "Potenza", MUST, null).buildQuery(),
                "{\"match\":{\"place.name\":{\"operator\":\"or\",\"query\":\"Potenza\"}}}");
        assertJson(new BooleanExactSearch("place.name", "Potenza città", MUST, And).buildQuery(),
                "{\"match\":{\"place.name\":{\"operator\":\"and\",\"query\":\"Potenza città\"}}}");
        assertJson(new BooleanFieldExistsSearch("place.location", MUST).buildQuery(), "{\"exists\":{\"field\":\"place.location\"}}");
        assertJson(new BooleanMultiMatchSearch("Basilicata", MUST, And, new String[]{"place.name", " ", "place.description"}).buildQuery(),
                "{\"multi_match\":{\"fields\":[\"place.name\",\"place.description\"],\"operator\":\"and\",\"query\":\"Basilicata\"}}");
        assertJson(new GPQueryStringSearch("place.name", "*enza", MUST).buildQuery(),
                "{\"query_string\":{\"allow_leading_wildcard\":true,\"default_operator\":\"and\",\"fields\":[\"place.name\"],\"query\":\"*enza\"}}");
    }

    /**
     * Gli intervalli di numeri e di date: i long restano esatti e le date tengono lo scostamento dall'UTC.
     */
    @Test
    void rangeQueriesTest() {
        assertJson(new GPNumberRangeQuerySearch("place.population", FILTER, 9007199254740993L, 10).buildQuery(),
                "{\"range\":{\"place.population\":{\"gte\":10,\"lte\":9007199254740993}}}");
        ZonedDateTime from = ZonedDateTime.of(2026, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1));
        assertJson(new GPDateQuerySearch("place.foundation", MUST, from, from.plusDays(1)).buildQuery(),
                "{\"range\":{\"place.foundation\":{\"gte\":\"2026-01-01T00:00:00+01:00\",\"lte\":\"2026-01-02T00:00:00+01:00\"}}}");
        assertThrows(IllegalArgumentException.class, () -> new GPDateQuerySearch("place.foundation", MUST, from, from));
        assertThrows(IllegalArgumentException.class, () -> new GPNumberRangeQuerySearch("place.population", MUST, 10, 20));
    }

    /**
     * Le ricerche geografiche: il poligono diventa una geo_shape in WKT con l'anello chiuso.
     */
    @Test
    void geoQueriesTest() {
        assertJson(new GPGeoBoundingBoxQuerySearch("place.location", new Envelope(15.0, 17.0, 40.0, 41.0)).buildQuery(),
                "{\"geo_bounding_box\":{\"place.location\":{\"top_left\":{\"lat\":41.0,\"lon\":15.0},\"bottom_right\":{\"lat\":40.0,\"lon\":17.0}}}}");
        assertJson(new GPGeodistanceQuerySearch("place.location", new Coordinate(15.8, 40.64), 50, Kilometers, null).buildQuery(),
                "{\"geo_distance\":{\"place.location\":{\"lat\":40.64,\"lon\":15.8},\"distance\":\"50.0km\",\"distance_type\":\"arc\"}}");
        assertJson(new GPGeoPolygonQuerySearch("place.location", List.of(new Coordinate(15, 40), new Coordinate(17, 40),
                        new Coordinate(17, 41), new Coordinate(15, 41))).buildQuery(),
                "{\"geo_shape\":{\"place.location\":{\"shape\":\"POLYGON ((15 40, 17 40, 17 41, 15 41, 15 40))\",\"relation\":\"intersects\"}}}");
    }

    /**
     * Le pagine: paginazione, ordinamento e query bool con le ricerche nella parte giusta.
     *
     * @throws Exception se la pagina non si costruisce
     */
    @Test
    void pagesTest() throws Exception {
        SearchRequest emptyPage = new Page(0, 0).buildPage(new SearchRequest.Builder().index("places")).build();
        assertNull(emptyPage.from());
        assertNull(emptyPage.size());
        assertNull(emptyPage.query());
        assertNull(new MultiFieldsSearch().toQuery());
        MultiFieldsSearch search = new MultiFieldsSearch("place.population", Asc, 5, 10,
                new GPTermSearch("place.code", "PZ", MUST), new GPTermSearch("place.code", "MT", SHOULD),
                new GPTermSearch("place.code", "BA", MUST_NOT), new BooleanFieldExistsSearch("place.location", FILTER));
        SearchRequest request = search.buildPage(new SearchRequest.Builder().index("places")).build();
        assertEquals(5, request.from());
        assertEquals(10, request.size());
        assertJson(request, "{\"from\":5,\"query\":{\"bool\":{\"filter\":[{\"exists\":{\"field\":\"place.location\"}}],"
                + "\"must\":[{\"term\":{\"place.code\":{\"value\":\"PZ\"}}}],\"must_not\":[{\"term\":{\"place.code\":{\"value\":\"BA\"}}}],"
                + "\"should\":[{\"term\":{\"place.code\":{\"value\":\"MT\"}}}]}},\"size\":10,\"sort\":[{\"place.population\":{\"order\":\"asc\"}}]}");
        // La stessa pagina costruisce sempre una query nuova: i builder del client si usano una volta sola.
        assertEquals(search.printQueryAsJson(), search.printQueryAsJson());
    }

    /**
     * Due gruppi di ricerche in OR: (codice PZ e popolazione da 10) oppure (codice MT).
     */
    @Test
    void coupleMultiFieldSearchTest() {
        CoupleMultiFieldSearch search = new CoupleMultiFieldSearch(List.of(
                GPCoupleMultiFieldPageBuilder.of(new MultiFieldsSearch(new GPTermSearch("place.code", "PZ", MUST),
                        new GPNumberRangeQuerySearch("place.population", MUST, 100, 10)), SHOULD),
                GPCoupleMultiFieldPageBuilder.of(new MultiFieldsSearch(new GPTermSearch("place.code", "MT", MUST)), SHOULD)));
        assertEquals("Query: {\"bool\":{\"should\":[{\"bool\":{\"must\":[{\"term\":{\"place.code\":{\"value\":\"PZ\"}}},"
                + "{\"range\":{\"place.population\":{\"gte\":10,\"lte\":100}}}]}},"
                + "{\"bool\":{\"must\":[{\"term\":{\"place.code\":{\"value\":\"MT\"}}}]}}]}}", search.printQueryAsJson());
    }

    /**
     * @param theQuery    la query
     * @param theExpected il JSON atteso
     */
    private static void assertJson(Query theQuery, String theExpected) {
        String json = JsonpUtils.toString(theQuery);
        logger.debug("#################Query: {}", json);
        assertEquals("Query: " + theExpected, json);
    }

    /**
     * @param theRequest  la ricerca
     * @param theExpected il JSON atteso del corpo
     */
    private static void assertJson(SearchRequest theRequest, String theExpected) {
        String json = JsonpUtils.toString(theRequest);
        logger.debug("#################Search: {}", json);
        assertTrue(json.endsWith(theExpected), () -> "JSON diverso da quello atteso: " + json);
    }
}
