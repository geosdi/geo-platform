/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.mapper.xml.jaxb;

import org.geosdi.geoplatform.jaxb.IGPJAXBContextBuilder;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.TokenStreamFactory;
import tools.jackson.core.exc.JacksonIOException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseJAXBXmlMapper extends XmlMapper {

    private final IGPJAXBContextBuilder jaxbContextBuilder;

    /**
     * @param theJaxbContextBuilder
     */
    protected GPBaseJAXBXmlMapper(@Nonnull(when = When.NEVER) IGPJAXBContextBuilder theJaxbContextBuilder) {
        checkArgument(theJaxbContextBuilder != null, "The Parameter jaxbContextBuilder must not be null.");
        this.jaxbContextBuilder = theJaxbContextBuilder;
    }

    /**
     * Method to deserialize JSON content from given file into given Java type.
     *
     * @throws JacksonIOException if a low-level I/O problem (unexpected end-of-input,
     *   network error) occurs (passed through as-is without additional wrapping -- note
     *   that this is one case where {@link DeserializationFeature#WRAP_EXCEPTIONS}
     *   does NOT result in wrapping of exception even if enabled)
     * @throws StreamReadException if underlying input contains invalid content
     *    of type {@link JsonParser} supports (JSON for default case)
     * @throws DatabindException if the input JSON structure does not match structure
     *   expected for result type (or has other mismatch issues)
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) File src, @Nonnull(when = NEVER) Class<T> valueType) throws JacksonException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return jaxbContextBuilder.unmarshal(src, valueType);
    }

    /**
     * @param src
     * @param valueType
     * @return {@link <></>}
     * @param <T>
     * @throws JacksonException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) Reader src, @Nonnull(when = NEVER) Class<T> valueType) throws JacksonException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return jaxbContextBuilder.unmarshal(src, valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws JacksonException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) String src, @Nonnull(when = NEVER) Class<T> valueType) throws JacksonException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return jaxbContextBuilder.unmarshal(new StringReader(src), valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws JacksonException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) InputStream src, @Nonnull(when = NEVER) Class<T> valueType) throws JacksonException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return jaxbContextBuilder.unmarshal(src, valueType);
    }

    /**
     * Method that can be used to serialize any Java value as
     * JSON output, using Writer provided.
     *<p>
     * Note: method does not close the underlying stream explicitly
     * here; however, {@link TokenStreamFactory} this mapper uses may choose
     * to close the stream depending on its settings (by default,
     * it will try to close it when {@link JsonGenerator} we construct
     * is closed).
     */
    @Override
    public void writeValue(@Nonnull(when = NEVER) Writer w, @Nonnull(when = NEVER) Object value) throws JacksonException {
        checkArgument(w != null, "The Parameter w must not be null.");
        checkArgument(value != null, "The Parameter value must not be null.");
        try {
            jaxbContextBuilder.marshal(value, w);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    /**
     * Method that can be used to serialize any Java value as
     * JSON output, written to File provided.
     *
     * @param resultFile
     * @param value
     */
    @Override
    public void writeValue(@Nonnull(when = NEVER) File resultFile, @Nonnull(when = NEVER) Object value) throws JacksonException {
        checkArgument(resultFile != null, "The Parameter resultFile must not be null.");
        checkArgument(value != null, "The Parameter value must not be null.");
        try {
            jaxbContextBuilder.marshal(value, resultFile);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    /**
     * Method that can be used to serialize any Java value as
     * a String. Functionally equivalent to calling
     * {@link #writeValue(Writer, Object)} with {@link StringWriter}
     * and constructing String, but more efficient.
     * <p>
     * Note: prior to version 2.1, throws clause included {@link IOException}; 2.1 removed it.
     *
     * @param value
     */
    @Override
    public String writeValueAsString(@Nonnull(when = NEVER) Object value) throws JacksonException {
        checkArgument(value != null, "The Parameter value must not be null.");
        Writer writer = new StringWriter();
        try {
            jaxbContextBuilder.marshal(value, writer);
            return writer.toString();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new IllegalStateException(exception);
        }
    }
}
