package org.geosdi.geoplatform.stax.reader.builder;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPXmlStreamReaderBuilder {

    /**
     * @param stream
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    XMLStreamReader build(InputStream stream) throws XMLStreamException;

    /**
     * <p>Build XmlStreamReader from generic Object using a special chain see {@link org.geosdi.geoplatform.stax.reader.builder.xmlreaderchain.AbstractReaderBuildHandler}</p>
     *
     * @param o
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    XMLStreamReader build(Object o) throws XMLStreamException;
}
