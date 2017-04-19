package org.geosdi.geoplatform.stax.reader.supplier;

import org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder;

import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XMLStreamReaderSupplier implements Supplier<XMLStreamReader> {

    private static final XmlStreamReaderBuilder xmlStreamBuilder = XmlStreamReaderBuilder.newInstance();
    //
    private final InputStream stream;
    private final Object o;

    public XMLStreamReaderSupplier(InputStream theStream, Object theObject) {
        this.stream = theStream;
        this.o = theObject;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public XMLStreamReader get() {
        try {
            return (this.stream != null) ? xmlStreamBuilder.build(stream) : xmlStreamBuilder.build(o);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
