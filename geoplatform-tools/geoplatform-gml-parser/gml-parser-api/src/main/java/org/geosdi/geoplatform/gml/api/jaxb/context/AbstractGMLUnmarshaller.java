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

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.jaxb.context.geojson.GMLGeoJsonUnmarshaller;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLUnmarshaller extends GMLGeoJsonUnmarshaller implements GMLUnmarshaller {

    protected final Unmarshaller unmarshaller;

    /**
     * @param theUnmarshaller
     */
    protected AbstractGMLUnmarshaller(@Nonnull(when = NEVER) Unmarshaller theUnmarshaller) {
        checkArgument(theUnmarshaller != null, "The Parameter unmarshaller must not be null.");
        this.unmarshaller = theUnmarshaller;
    }

    /**
     * @param file
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) File file) throws Exception {
        checkArgument(file != null && !file.isDirectory() && file.exists(), "The Parameter file must not be null, must not be a directory and must exists.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(file));
    }

    /**
     * @param is
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) InputStream is) throws Exception {
        checkArgument(is != null, "The Parameter InputStream must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(is));
    }

    /**
     * @param reader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) Reader reader) throws Exception {
        checkArgument(reader != null, "The Parameter reader must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(reader));
    }

    /**
     * @param url
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) URL url) throws Exception {
        checkArgument(url != null, "The Parameter url must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(url));
    }

    /**
     * @param source
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) InputSource source) throws Exception {
        checkArgument(source != null, "The Parameter inputSource must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(source));
    }

    /**
     * @param source
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) Source source) throws Exception {
        checkArgument(source != null, "The Parameter source must not be null");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(source));
    }

    /**
     * @param reader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLStreamReader reader) throws Exception {
        checkArgument(reader != null, "The Parameter xmlStreamReader must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(reader));
    }

    /**
     * @param reader
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLEventReader reader) throws Exception {
        checkArgument(reader != null, "The Parameter xmlEventReader must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(reader));
    }

    /**
     * @param node
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject unmarshalAsGeoJson(@Nonnull(when = NEVER) Node node) throws Exception {
        checkArgument(node != null, "The Parameter node must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(node));
    }

    /**
     * @param node
     * @param declaredType
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    @Override
    public <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) Node node, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception {
        checkArgument(node != null, "The Parameter node must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(node), declaredType);
    }

    /**
     * @param source
     * @param declaredType
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    @Override
    public <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) Source source, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception {
        checkArgument(source != null, "The Parameter source must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(source), declaredType);
    }

    /**
     * @param reader
     * @param declaredType
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    @Override
    public <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLStreamReader reader, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception {
        checkArgument(reader != null, "The Parameter xmlStreamReader must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(reader), declaredType);
    }

    /**
     * @param reader
     * @param declaredType
     * @return {@link JAXBElement<G>}
     * @throws Exception
     */
    @Override
    public <G extends GeoJsonObject> JAXBElement<G> unmarshalAsGeoJson(@Nonnull(when = NEVER) XMLEventReader reader, @Nonnull(when = NEVER) Class<G> declaredType) throws Exception {
        checkArgument(reader != null, "The Parameter xmlEventReader must not be null.");
        checkArgument(declaredType != null, "The Parameter declaredType must not be null.");
        return super.parseElementAsGeoJson(this.unmarshaller.unmarshal(reader), declaredType);
    }

    /**
     * @param element
     * @return {@link Geometry}
     * @throws ParserException
     */
    protected Geometry parseElement(Object element) throws ParserException {
        Object value = JAXBIntrospector.getValue(element);
        if (value instanceof PropertyType) {
            return sextanteParser.parseGeometry((PropertyType) value);
        } else if (value instanceof AbstractGeometry) {
            return sextanteParser.parseGeometry((AbstractGeometry) value);
        }
        throw new ParserException("The Object must be an instance of PropertyType or AbstractGeometry Class.");
    }

    /**
     * @param element
     * @param type
     * @param <T>
     * @return {@link JAXBElement<T>}
     * @throws ParserException
     */
    protected <T extends Geometry> JAXBElement<T> parseElement(Object element, Class<T> type) throws ParserException {
        checkNotNull(element, "The Object Element must not be null.");
        if (element instanceof JAXBElement) {
            Geometry geom = parseElement(element);
            if (type.isAssignableFrom(geom.getClass())) {
                T value = (T) geom;
                return new JAXBElement(((JAXBElement<?>) element).getName(), type, value);
            } else {
                throw new ParserException("Geometry class " + geom.getClass().getName() + " not match " + type.getName());
            }
        } else {
            throw new ParserException("The Object element is not an instance of JAXBElement class");
        }
    }
}