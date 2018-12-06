package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.LineString;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.adapter.JTSGeometryType.LINESTRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSLineStringAdapter extends AbstractJTSLineStringAdapter<LineString, com.vividsolutions.jts.geom.LineString> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSLineStringAdapter(LineString theLocationtechGeometry, com.vividsolutions.jts.geom.LineString theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getGeometryType() {
        return LINESTRING.getType();
    }

    /**
     * @param theLineString
     * @return {@link JTSLineStringAdapter}
     */
    protected static JTSLineStringAdapter adapt(@Nonnull(when = NEVER) LineString theLineString) {
        checkArgument(theLineString != null, "The Parameter lineString must not be null.");
        return new JTSLineStringAdapter(theLineString, null);
    }

    /**
     * @param theLineString
     * @return {@link JTSLineStringAdapter}
     */
    protected static JTSLineStringAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.LineString theLineString) {
        checkArgument(theLineString != null, "The Parameter lineString must not be null.");
        return new JTSLineStringAdapter(null, theLineString);
    }
}