/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.geosdi.geoplatform.jaxb.pool.GPJAXBContextBuilderPool;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBXmlMapper extends XmlMapper {

    private static final GPJAXBContextBuilderPool GP_JAXB_CONTEXT_BUILDER_POOL = GPJAXBContextBuilderPool.jaxbContextBuilderPool();

    /**
     * Method to deserialize JSON content from given file into given Java type.
     *
     * @param src
     * @param valueType
     * @throws IOException          if a low-level I/O problem (unexpected end-of-input,
     *                              network error) occurs (passed through as-is without additional wrapping -- note
     *                              that this is one case where {@link com.fasterxml.jackson.databind.DeserializationFeature#WRAP_EXCEPTIONS}
     *                              does NOT result in wrapping of exception even if enabled)
     * @throws JsonParseException   if underlying input contains invalid content
     *                              of type {@link com.fasterxml.jackson.core.JsonParser} supports (JSON for default case)
     * @throws JsonMappingException if the input JSON structure does not match structure
     *                              expected for result type (or has other mismatch issues)
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) File src, @Nonnull(when = NEVER) Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return GP_JAXB_CONTEXT_BUILDER_POOL.unmarshal(src, valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) Reader src, @Nonnull(when = NEVER) Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return GP_JAXB_CONTEXT_BUILDER_POOL.unmarshal(src, valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) String src, @Nonnull(when = NEVER) Class<T> valueType) throws JsonParseException, JsonMappingException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return GP_JAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader(src), valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) InputStream src, @Nonnull(when = NEVER) Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return GP_JAXB_CONTEXT_BUILDER_POOL.unmarshal(src, valueType);
    }

    /**
     * @param src
     * @param valueType
     * @param <T>
     * @return {@link T}
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     */
    @Override
    public <T> T readValue(@Nonnull(when = NEVER) URL src, @Nonnull(when = NEVER) Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        checkArgument(src != null, "The Parameter src must not be null.");
        checkArgument(valueType != null, "The Parameter valueType must not be null.");
        return GP_JAXB_CONTEXT_BUILDER_POOL.unmarshal(src, valueType);
    }

    /**
     * Method that can be used to serialize any Java value as
     * JSON output, using Writer provided.
     * <p>
     * Note: method does not close the underlying stream explicitly
     * here; however, {@link com.fasterxml.jackson.core.JsonFactory} this mapper uses may choose
     * to close the stream depending on its settings (by default,
     * it will try to close it when {@link com.fasterxml.jackson.core.JsonGenerator} we construct
     * is closed).
     *
     * @param w
     * @param value
     */
    @Override
    public void writeValue(@Nonnull(when = NEVER) Writer w, @Nonnull(when = NEVER) Object value) throws IOException, JsonGenerationException, JsonMappingException {
        checkArgument(w != null, "The Parameter w must not be null.");
        checkArgument(value != null, "The Parameter value must not be null.");
        try {
            GP_JAXB_CONTEXT_BUILDER_POOL.marshal(value, w);
        } catch (JAXBException exception) {
            exception.printStackTrace();
            throw new IOException(exception);
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
    public void writeValue(@Nonnull(when = NEVER) File resultFile, @Nonnull(when = NEVER) Object value) throws IOException, JsonGenerationException, JsonMappingException {
        checkArgument(resultFile != null, "The Parameter resultFile must not be null.");
        checkArgument(value != null, "The Parameter value must not be null.");
        try {
            GP_JAXB_CONTEXT_BUILDER_POOL.marshal(value, resultFile);
        } catch (JAXBException exception) {
            exception.printStackTrace();
            throw new IOException(exception);
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
    public String writeValueAsString(@Nonnull(when = NEVER) Object value) throws JsonProcessingException {
        checkArgument(value != null, "The Parameter value must not be null.");
        Writer writer = new StringWriter();
        try {
            GP_JAXB_CONTEXT_BUILDER_POOL.marshal(value, writer);
            return writer.toString();
        } catch (JAXBException exception) {
            exception.printStackTrace();
            throw new IllegalStateException(exception);
        }
    }
}