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
package org.geosdi.geoplatform.experimental.el;

import org.elasticsearch.common.geo.GeoPoint;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el.search.bool.BooleanAllMatchSearch;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el.search.geodistance.IGPGeodistanceQuerySearch.GPGeodistanceQuerySearch;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.common.unit.DistanceUnit.METERS;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.MUST;
import static org.joda.time.DateTime.now;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPElasticSearchQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryTest.class);

    @Test
    public void a_geoDistanceQueryTest() throws Exception {
        GPGeodistanceQuerySearch geodistanceQuerySearch = new GPGeodistanceQuerySearch("location", new GeoPoint(40, 15), 34d, METERS, null);
        MultiFieldsSearch multiFieldsSearch = new MultiFieldsSearch(new BooleanAllMatchSearch(), geodistanceQuerySearch);
        multiFieldsSearch.printQueryAsJson();
        logger.info("###################GEO_DISTANCE_QUERY : \n{}\n", multiFieldsSearch.printQueryAsJson());
    }

    @Test
    public void b_geoDistanceAndDateQueryTest() throws Exception {
        GPGeodistanceQuerySearch geodistanceQuerySearch = new GPGeodistanceQuerySearch("location", new GeoPoint(40, 15), 34d, METERS, null);
        MultiFieldsSearch multiFieldsSearch = new MultiFieldsSearch(new GPDateQuerySearch("date", MUST,
                now().minusDays(4), now()), geodistanceQuerySearch);
        multiFieldsSearch.printQueryAsJson();
        logger.info("@@@@@@@@@@@@@@@@@@GEO_DISTANCE_DATE_QUERY : \n{}\n", multiFieldsSearch.printQueryAsJson());
    }
}