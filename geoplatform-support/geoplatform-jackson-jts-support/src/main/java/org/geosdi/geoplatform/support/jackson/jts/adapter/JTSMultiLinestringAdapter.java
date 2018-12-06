package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.MULTILINESTRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSMultiLinestringAdapter extends JTSBaseGeometryCollectionAdapter<MultiLineString, com.vividsolutions.jts.geom.MultiLineString> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSMultiLinestringAdapter(MultiLineString theLocationtechGeometry, com.vividsolutions.jts.geom.MultiLineString theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return MULTILINESTRING.getType();
    }

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    @Override
    public GPJTSGeometryAdapter getGeometryN(int n) {
        return ((this.locationtechGeometry != null) ? JTSLineStringAdapter.adapt((LineString) this.locationtechGeometry.getGeometryN(n))
                : JTSLineStringAdapter.adapt((com.vividsolutions.jts.geom.LineString) this.vividisolutionsGeometry.getGeometryN(n)));
    }

    /**
     * @param theMultiLineString
     * @return {@link JTSMultiLinestringAdapter}
     */
    protected static JTSMultiLinestringAdapter adapt(@Nonnull(when = NEVER) MultiLineString theMultiLineString) {
        checkArgument(theMultiLineString != null, "The Parameter multiLineString must not be null.");
        return new JTSMultiLinestringAdapter(theMultiLineString, null);
    }

    /**
     * @param theMultiLineString
     * @return {@link JTSMultiLinestringAdapter}
     */
    protected static JTSMultiLinestringAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.MultiLineString theMultiLineString) {
        checkArgument(theMultiLineString != null, "The Parameter multiLineString must not be null.");
        return new JTSMultiLinestringAdapter(null, theMultiLineString);
    }
}