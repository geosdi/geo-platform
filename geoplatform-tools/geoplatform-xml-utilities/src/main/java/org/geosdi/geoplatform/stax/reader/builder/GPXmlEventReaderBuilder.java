package org.geosdi.geoplatform.stax.reader.builder;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPXmlEventReaderBuilder {

    /**
     * @param xmlStreamReader
     * @return {@link XMLEventReader}
     * @throws Exception
     */
    XMLEventReader build(@Nonnull(when = NEVER) XMLStreamReader xmlStreamReader) throws Exception;
}