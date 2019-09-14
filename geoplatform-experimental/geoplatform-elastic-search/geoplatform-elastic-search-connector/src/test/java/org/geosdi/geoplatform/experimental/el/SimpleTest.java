package org.geosdi.geoplatform.experimental.el;

import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el.search.bool.BooleanAllMatchSearch;
import org.geosdi.geoplatform.experimental.el.search.geodistance.IGPGeodistanceQuerySearch.GPGeodistanceQuerySearch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.MUST;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void test() throws Exception {
        GPGeodistanceQuerySearch geodistanceQuerySearch = new GPGeodistanceQuerySearch("location", new GeoPoint(40, 15), 34d, DistanceUnit.METERS, null);
        MultiFieldsSearch multiFieldsSearch = new MultiFieldsSearch(new BooleanAllMatchSearch(MUST), geodistanceQuerySearch);
        multiFieldsSearch.printQueryAsJson();
        logger.info("{}\n", multiFieldsSearch.printQueryAsJson());
    }
}