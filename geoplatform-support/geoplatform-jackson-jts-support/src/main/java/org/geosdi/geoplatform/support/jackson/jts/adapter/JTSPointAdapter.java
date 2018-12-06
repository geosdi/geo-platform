package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.Point;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.POINT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPointAdapter extends JTSGeometryAdapter<Point, com.vividsolutions.jts.geom.Point> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSPointAdapter(Point theLocationtechGeometry, com.vividsolutions.jts.geom.Point theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return POINT.getType();
    }

    /**
     * @param theLocationtechGeometry
     * @return {@link JTSPointAdapter}
     */
    protected static JTSPointAdapter adapt(@Nonnull(when = When.NEVER) Point theLocationtechGeometry) {
        checkArgument(theLocationtechGeometry != null, "The Parameter locationtechGeometry must not be null");
        return new JTSPointAdapter(theLocationtechGeometry, null);
    }

    /**
     * @param theVividisolutionsGeometry
     * @return {@link JTSPointAdapter }
     */
    protected static JTSPointAdapter adapt(@Nonnull(when = When.NEVER) com.vividsolutions.jts.geom.Point theVividisolutionsGeometry) {
        checkArgument(theVividisolutionsGeometry != null, "The Parameter vividisolutionsGeometry must not be null");
        return new JTSPointAdapter(null, theVividisolutionsGeometry);
    }
}