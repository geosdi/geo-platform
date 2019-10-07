package org.geosdi.geoplatform.stax.reader.builder;

import org.geosdi.geoplatform.stax.reader.builder.xmlreaderchain.XmlStreamReaderHandler;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseXmlStreamReaderBuilder<Factory extends XMLInputFactory> implements GPXmlStreamReaderBuilder {

    protected static final XmlStreamReaderHandler readerHandler = new XmlStreamReaderHandler();
    private final Factory factory;

    /**
     * @param theFactory
     */
    BaseXmlStreamReaderBuilder(@Nonnull(when = NEVER) Factory theFactory) {
        checkArgument(theFactory != null, "The Parameter factory must not be null.");
        this.factory = theFactory;
    }

    /**
     * @param stream
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    @Override
    public XMLStreamReader build(@Nonnull(when = NEVER) InputStream stream) throws XMLStreamException {
        checkArgument(stream != null, "The Parameter inputStream must not be null.");
        return factory.createXMLStreamReader(stream);
    }

    /**
     * <p>Build XmlStreamReader from generic Object using a special chain see {@link org.geosdi.geoplatform.stax.reader.builder.xmlreaderchain.AbstractReaderBuildHandler}</p>
     *
     * @param o
     * @return {@link XMLStreamReader}
     * @throws XMLStreamException
     */
    @Override
    public XMLStreamReader build(@Nonnull(when = NEVER) Object o) throws XMLStreamException {
        return readerHandler.buildXmlReader(o, factory);
    }

    /**
     * @param xmlStreamReader
     * @return {@link XMLEventReader}
     * @throws Exception
     */
    @Override
    public XMLEventReader build(@Nonnull(when = NEVER) XMLStreamReader xmlStreamReader) throws Exception {
        checkArgument(xmlStreamReader != null, "The Parameter xmlStreamReader must not be null.");
        return factory.createXMLEventReader(xmlStreamReader);
    }
}