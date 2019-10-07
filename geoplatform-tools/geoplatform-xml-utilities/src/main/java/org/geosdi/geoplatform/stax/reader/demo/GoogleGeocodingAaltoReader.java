package org.geosdi.geoplatform.stax.reader.demo;

import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderStax2Builder.aaltoConfigureForSpeed;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GoogleGeocodingAaltoReader extends GPGoogleGeocodingStaxReader {

    public GoogleGeocodingAaltoReader() {
        super(aaltoConfigureForSpeed());
    }
}