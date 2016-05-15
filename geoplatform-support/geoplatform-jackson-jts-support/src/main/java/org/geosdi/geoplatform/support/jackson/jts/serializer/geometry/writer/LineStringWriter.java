package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.LineString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LineStringWriter extends AbstractLineWriter<LineString> {

    /**
     * @return {@link Class<LineString>}
     */
    @Override
    public Class<LineString> getKey() {
        return LineString.class;
    }
}
