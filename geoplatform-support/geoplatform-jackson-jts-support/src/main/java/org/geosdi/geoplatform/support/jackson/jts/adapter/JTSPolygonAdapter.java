package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.POLYGON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPolygonAdapter extends JTSGeometryAdapter<Polygon, com.vividsolutions.jts.geom.Polygon> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSPolygonAdapter(Polygon theLocationtechGeometry, com.vividsolutions.jts.geom.Polygon theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return POLYGON.getType();
    }

    /**
     * @return {@link JTSLineStringAdapter}
     */
    public JTSLineStringAdapter getExteriorRing() {
        return ((this.locationtechGeometry != null)
                ? JTSLineStringAdapter.adapt(this.locationtechGeometry.getExteriorRing())
                : JTSLineStringAdapter.adapt(this.vividisolutionsGeometry.getExteriorRing()));
    }

    /**
     * @return {@link Integer}
     */
    public int getNumInteriorRing() {
        return ((this.locationtechGeometry != null)
                ? this.locationtechGeometry.getNumInteriorRing() : this.vividisolutionsGeometry.getNumInteriorRing());
    }

    /**
     * @param n
     * @return {@link JTSLineStringAdapter}
     */
    public JTSLineStringAdapter getInteriorRingN(int n) {
        return ((this.locationtechGeometry != null)
                ? JTSLineStringAdapter.adapt(this.locationtechGeometry.getInteriorRingN(n))
                : JTSLineStringAdapter.adapt(this.vividisolutionsGeometry.getInteriorRingN(n)));
    }

    /**
     * @param thePolygon
     * @return {@link JTSPolygonAdapter}
     */
    protected static JTSPolygonAdapter adapt(@Nonnull(when = When.NEVER) Polygon thePolygon) {
        checkArgument(thePolygon != null, "The Parameter polygon must not be null.");
        return new JTSPolygonAdapter(thePolygon, null);
    }

    /**
     * @param thePolygon
     * @return {@link JTSPolygonAdapter}
     */
    protected static JTSPolygonAdapter adapt(@Nonnull(when = When.NEVER) com.vividsolutions.jts.geom.Polygon thePolygon) {
        checkArgument(thePolygon != null, "The Parameter polygon must not be null.");
        return new JTSPolygonAdapter(null, thePolygon);
    }
}