package org.geosdi.geoplatform.connector.server.request;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJAXBConnectorRequest<T> extends GPConnectorRequest<T> {

    /**
     * @param indent
     * @return {@link String}
     * @throws Exception
     */
    default String formatResponseAsString(int indent) throws Exception {
        try {
            InputStream stream = getResponseAsStream();
            if (stream == null)
                throw new IllegalStateException("The InputStream to format must not be null.");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse(new InputSource(stream));
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(Boolean.TRUE);
            format.setIndent(indent);
            Writer writer = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(writer, format);
            serializer.serialize(doc);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    /**
     * @return Marshaller
     * @throws Exception
     */
    Marshaller getMarshaller() throws Exception;

    /**
     * @return Unmarshaller
     * @throws Exception
     */
    Unmarshaller getUnmarshaller() throws Exception;

    /**
     * Show the XML Object created for the Request to send to Server
     *
     * @return Request as a String
     * @throws Exception
     */
    String showRequestAsString() throws Exception;
}
