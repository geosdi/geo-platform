package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.locationtech.jts.geom.GeometryCollection;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryCollectionAdapter extends JTSBaseGeometryCollectionAdapter<GeometryCollection, com.vividsolutions.jts.geom.GeometryCollection> {

    /**
     * @param theLocationtechGeometry
     * @param theVividisolutionsGeometry
     */
    JTSGeometryCollectionAdapter(GeometryCollection theLocationtechGeometry, com.vividsolutions.jts.geom.GeometryCollection theVividisolutionsGeometry) {
        super(theLocationtechGeometry, theVividisolutionsGeometry);
    }

    /**
     * @param theGeometryCollection
     * @return {@link JTSGeometryCollectionAdapter}
     */
    protected static JTSGeometryCollectionAdapter adapt(@Nonnull(when = NEVER) GeometryCollection theGeometryCollection) {
        checkArgument(theGeometryCollection != null, "The Parameter geometryCollection must not be null.");
        return new JTSGeometryCollectionAdapter(theGeometryCollection, null);
    }

    /**
     * @param theGeometryCollection
     * @return {@link JTSGeometryCollectionAdapter}
     */
    protected static JTSGeometryCollectionAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.GeometryCollection theGeometryCollection) {
        checkArgument(theGeometryCollection != null, "The Parameter geometryCollection must not be null.");
        return new JTSGeometryCollectionAdapter(null, theGeometryCollection);
    }
}