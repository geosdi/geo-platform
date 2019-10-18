package org.geosdi.geoplatform.connector.reader.stax;

import net.jcip.annotations.ThreadSafe;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;
import org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import java.util.Map;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder.jdkDefaultInstance;
import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder.jdkWithProp;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPWMSGetFeatureInfoStaxReader extends WMSBaseGetFeatureInfoStaxReader {

    /**
     * Create a {@link GPWMSGetFeatureInfoStaxReader} with {@link XMLInputFactory} with these properties :
     * <p>
     *     <ul>
     *         <li>Enable {@link XMLInputFactory#IS_COALESCING} property.</li>
     *         <li>Enable {@link XMLInputFactory#IS_NAMESPACE_AWARE} property.</li>
     *         <li>Enable "http://java.sun.com/xml/stream/properties/report-cdata-event" property.</li>
     *     </ul>
     * </p>
     */
    public GPWMSGetFeatureInfoStaxReader() {
        super(jdkDefaultInstance());
    }

    /**
     * @param theProp
     */
    public GPWMSGetFeatureInfoStaxReader(@Nonnull(when = NEVER) Map<String, Object> theProp) {
        super(jdkWithProp(theProp));
    }
}