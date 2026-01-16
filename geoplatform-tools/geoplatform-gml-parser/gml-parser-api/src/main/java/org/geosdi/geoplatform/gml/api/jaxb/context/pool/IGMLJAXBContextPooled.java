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
package org.geosdi.geoplatform.gml.api.jaxb.context.pool;

import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGMLJAXBContextPooled {

    // <editor-fold defaultstate="collapsed" desc="Unmarshaller Section">
    // ==========================================================================
    // === Unmarshaller Section
    // ==========================================================================
    /**
     * @param f
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) File f) throws Exception;

    /**
     * @param is
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) InputStream is) throws Exception;

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Reader reader) throws Exception;

    /**
     * @param url
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) URL url) throws Exception;

    /**
     * @param source
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) InputSource source) throws Exception;

    /**
     * @param source
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Source source) throws Exception;

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) XMLStreamReader reader) throws Exception;

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) XMLEventReader reader) throws Exception;

    /**
     * @param node
     * @return {@link T}
     * @throws Exception
     */
    <T extends Geometry> T unmarshal(@Nonnull(when = NEVER) Node node) throws Exception;

    /**
     * @param node
     * @param declaredType
     * @return {@link JAXBElement <T>}
     * @throws Exception
     */
    <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) Node node, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception;

    /**
     * @param source
     * @param declaredType
     * @return {@link JAXBElement<T>}
     * @throws Exception
     */
    <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) Source source, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception;

    /**
     * @param reader
     * @param declaredType
     * @return {@link JAXBElement<T>}
     * @throws Exception
     */
    <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) XMLStreamReader reader, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception;

    /**
     * @param reader
     * @param declaredType
     * @return {@link JAXBElement<T>}
     * @throws Exception
     */
    <T extends Geometry> JAXBElement<T> unmarshal(@Nonnull(when = NEVER) XMLEventReader reader, @Nonnull(when = NEVER) Class<T> declaredType) throws Exception;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Marshaller Section">
    // ==========================================================================
    // === Marshaller Section
    // ==========================================================================

    /**
     * @param jaxbElement
     * @param result
     * @throws Exception
     */
    void marshal(Object jaxbElement, Result result) throws Exception;

    /**
     * @param jaxbElement
     * @param os
     * @throws Exception
     */
    void marshal(Object jaxbElement, OutputStream os) throws Exception;

    /**
     * @param jaxbElement
     * @param output
     * @throws Exception
     */
    void marshal(Object jaxbElement, File output) throws Exception;

    /**
     * @param jaxbElement
     * @param writer
     * @throws Exception
     */
    void marshal(Object jaxbElement, Writer writer) throws Exception;

    /**
     * @param jaxbElement
     * @param handler
     * @throws Exception
     */
    void marshal(Object jaxbElement, ContentHandler handler) throws Exception;

    /**
     * @param jaxbElement
     * @param node
     * @throws Exception
     */
    void marshal(Object jaxbElement, Node node) throws Exception;

    /**
     * @param jaxbElement
     * @param writer
     * @throws Exception
     */
    void marshal(Object jaxbElement, XMLStreamWriter writer) throws Exception;

    /**
     * @param jaxbElement
     * @param writer
     * @throws Exception
     */
    void marshal(Object jaxbElement, XMLEventWriter writer) throws Exception;
    // </editor-fold>
}