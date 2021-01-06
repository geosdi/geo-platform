/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.jaxb;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJAXBContextBuilder {

    /**
     * @param path
     * @param type
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T unmarshall(Path path, Class<T> type) throws Exception;

    /**
     * @param file
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(File file, Class<T> type);

    /**
     * @param url
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(URL url, Class<T> type);

    /**
     * @param uri
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(URI uri, Class<T> type);

    /**
     * @param source
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Source source, Class<T> type);

    /**
     * @param xmlStreamReader
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(XMLStreamReader xmlStreamReader, Class<T> type);

    /**
     * @param reader
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Reader reader, Class<T> type);

    /**
     * @param xmlEventReader
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(XMLEventReader xmlEventReader, Class<T> type);

    /**
     * @param node
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(Node node, Class<T> type);

    /**
     * @param inputStream
     * @param type
     * @param <T>
     * @return {@link T}
     */
    <T> T unmarshal(InputStream inputStream, Class<T> type);

    /**
     * @param jaxbObject
     * @param result
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Result result) throws JAXBException;

    /**
     * @param jaxbObject
     * @param file
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, File file) throws JAXBException;

    /**
     * @param jaxbObject
     * @param path
     * @throws Exception
     */
    void marshall(Object jaxbObject, Path path) throws Exception;

    /**
     * @param jaxbObject
     * @param node
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Node node) throws JAXBException;

    /**
     * @param jaxbObject
     * @param stream
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, OutputStream stream) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, Writer writer) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, XMLEventWriter writer) throws JAXBException;

    /**
     * @param jaxbObject
     * @param writer
     * @throws JAXBException
     */
    void marshal(Object jaxbObject, XMLStreamWriter writer) throws JAXBException;
}