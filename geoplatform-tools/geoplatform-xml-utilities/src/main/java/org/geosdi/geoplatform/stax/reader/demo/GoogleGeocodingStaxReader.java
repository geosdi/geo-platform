package org.geosdi.geoplatform.stax.reader.demo;

import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder.jdkDefaultInstance;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GoogleGeocodingStaxReader extends GPGoogleGeocodingStaxReader {

    public GoogleGeocodingStaxReader() {
        super(jdkDefaultInstance());
    }
}