/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.api.jaxb.context.geojson;

import org.geojson.GeoJsonObject;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGMLGeoJsonUnmarshaller {

    /**
     * @param file
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) File file) throws Exception;

    /**
     * @param is
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) InputStream is) throws Exception;

    /**
     * @param reader
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) Reader reader) throws Exception;

    /**
     * @param url
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) URL url) throws Exception;

    /**
     * @param source
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) InputSource source) throws Exception;

    /**
     * @param source
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) Source source) throws Exception;

    /**
     * @param reader
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLStreamReader reader) throws Exception;

    /**
     * @param reader
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLEventReader reader) throws Exception;

    /**
     * @param node
     * @param <G>
     * @return {@link G}
     * @throws Exception
     */
    <G extends GeoJsonObject> G unmarshalAsGeoJson(@Nonnull(when = NEVER) Node node) throws Exception;

    /**
     *
     * @param node
     * @param declaredType
     * @param <G>
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) Node node, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception;

    /**
     * @param source
     * @param declaredType
     * @param <G>
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) Source source, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception;

    /**
     * @param reader
     * @param declaredType
     * @param <G>
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLStreamReader reader, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception;

    /**
     * @param reader
     * @param declaredType
     * @param <G>
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLEventReader reader, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception;
}