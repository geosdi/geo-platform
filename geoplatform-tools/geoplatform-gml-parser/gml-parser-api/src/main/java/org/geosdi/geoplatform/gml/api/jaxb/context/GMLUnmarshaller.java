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
package org.geosdi.geoplatform.gml.api.jaxb.context;

import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GMLUnmarshaller {

    /**
     * @param f
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(File f) throws JAXBException, ParserException;

    /**
     * @param is
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(InputStream is) throws JAXBException, ParserException;

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(Reader reader) throws JAXBException, ParserException;

    /**
     * @param url
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(URL url) throws JAXBException, ParserException;

    /**
     * @param source
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(InputSource source) throws JAXBException, ParserException;

    /**
     * @param source
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T unmarshal(Source source) throws Exception;

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T unmarshal(XMLStreamReader reader) throws Exception;

    /**
     * @param reader
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T unmarshal(XMLEventReader reader) throws Exception;

    /**
     * @param node
     * @param <T>
     * @return {@link T}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> T unmarshal(Node node) throws JAXBException, ParserException;

    /**
     * @param node
     * @param declaredType
     * @param <T>
     * @return {@link JAXBElement<T>}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> JAXBElement<T> unmarshal(Node node, Class<T> declaredType) throws JAXBException, ParserException;

    /**
     * @param source
     * @param declaredType
     * @param <T>
     * @return {@link JAXBElement<T>}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> JAXBElement<T> unmarshal(Source source, Class<T> declaredType) throws JAXBException, ParserException;

    /**
     * @param reader
     * @param declaredType
     * @param <T>
     * @return {@link JAXBElement<T>}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> JAXBElement<T> unmarshal(XMLStreamReader reader, Class<T> declaredType) throws JAXBException, ParserException;

    /**
     * @param reader
     * @param declaredType
     * @param <T>
     * @return {@link JAXBElement<T>}
     * @throws JAXBException
     * @throws ParserException
     */
    <T> JAXBElement<T> unmarshal(XMLEventReader reader, Class<T> declaredType) throws JAXBException, ParserException;

    /**
     * @throws Exception
     */
    void dispose() throws Exception;
}
