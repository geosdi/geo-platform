package org.geosdi.geoplatform.support.wfs.feature.reader.geojson;

import net.jcip.annotations.ThreadSafe;
import org.geojson.Feature;
import org.geojson.FeatureCollection;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWFSGetFeatureGeoJsonStaxReader extends WFSBaseGetFeatureGeoJsonStaxReader {

    /**
     * @param object
     * @return {@link FeatureCollection}
     * @throws Exception
     */
    @Override
    public FeatureCollection read(@Nonnull(when = NEVER) Object object) throws Exception {
        XMLStreamReader reader = this.acquireReader(object);
        FeatureCollection featureCollection = new FeatureCollection();
        try {
            while (reader.hasNext()) {
                int evenType = reader.getEventType();
                if (evenType == XMLEvent.START_ELEMENT) {
                    if (super.isTagName(WFS_PREFIX, FEATURE_COLLECTION_LOCAL_NAME)) {
                        this.loadTypeNames();
                    } else if (super.isTagName(GML_PREFIX, FEATURE_MEMBERS_LOCAL_NAME)) {
                        Feature feature = new Feature();
                        this.readFeatures(feature);
                        featureCollection.add(feature);
                    } else {
                        Feature feature = new Feature();
                        this.readFeatureID(feature);
                        featureCollection.add(feature);
                    }
                }
                reader.next();
            }
            return featureCollection;
        } finally {
            this.dispose();
        }
    }
}