package org.geosdi.geoplatform.stax.reader.demo;

import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGoogleGeocodingStaxReader extends AbstractStaxStreamReader<StringBuilder> {

    /**
     * @param theXmlStreamBuilder
     */
    GPGoogleGeocodingStaxReader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder) {
        super(theXmlStreamBuilder);
    }

    /**
     * @param object
     * @return {@link StringBuilder}
     * @throws Exception
     */
    @Override
    public StringBuilder read(Object object) throws Exception {
        XMLStreamReader reader = super.acquireReader(object);
        StringBuilder builder = new StringBuilder();
        try {
            while (reader.hasNext()) {
                int evenType = reader.getEventType();
                if (evenType == XMLEvent.CHARACTERS) {
                    builder.append(reader.getText());
                }
                reader.next();
            }
            return builder;
        } finally {
            super.dispose();
        }
    }
}
