package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractJTSLineStringAdapter<L extends LineString, V extends com.vividsolutions.jts.geom.LineString>
        extends JTSGeometryAdapter<L, V> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    AbstractJTSLineStringAdapter(L theLocationtechGeometry, V theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }
}