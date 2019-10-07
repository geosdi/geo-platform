package org.geosdi.geoplatform.stax.reader.demo;


import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderStax2Builder.woodstoxConfigureForConvenience;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GoogleGeocodingWoodstoxReader extends GPGoogleGeocodingStaxReader {

    public GoogleGeocodingWoodstoxReader() {
        super(woodstoxConfigureForConvenience());
    }
}