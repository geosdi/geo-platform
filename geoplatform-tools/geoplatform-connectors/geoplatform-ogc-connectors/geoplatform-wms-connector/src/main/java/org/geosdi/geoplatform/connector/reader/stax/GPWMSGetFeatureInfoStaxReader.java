package org.geosdi.geoplatform.connector.reader.stax;

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
public class GPWMSGetFeatureInfoStaxReader extends WMSGetFeatureInfoStaxReader {

    /**
     * @param object
     * @return {@link FeatureCollection}
     * @throws Exception
     */
    @Override
    public FeatureCollection read(@Nonnull(when = NEVER) Object object) throws Exception {
        XMLStreamReader reader = this.acquireReader(object);
        FeatureCollection featureCollection = new FeatureCollection();
        while (reader.hasNext()) {
            int evenType = reader.getEventType();
            if (evenType == XMLEvent.START_ELEMENT) {
                if (super.isTagName("wfs", "FeatureCollection")) {
                    this.loadTypeNames();
                } else if (super.isTagName("gml", "featureMember")) {
                    Feature feature = new Feature();
                    this.readFeatures(feature);
                    featureCollection.add(feature);
                }
            }
            reader.next();
        }
        this.dispose();
        return featureCollection;
    }
}