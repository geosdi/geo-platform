package org.geosdi.geoplatform.connector.wms;

import org.geotools.referencing.CRS;
import org.junit.Test;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleTest {

    @Test
    public void simpleTest() throws Exception {
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:32632", Boolean.TRUE);
        System.out.println("################## " + sourceCRS);
    }
}
