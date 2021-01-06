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
package org.geosdi.geoplatform.connector.wfs.response;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Francesco Izzi <francesco.izzi@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
//@XmlRootElement(name = "LayerSchemaDTO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"typeName", "targetNamespace", "scope",
        "geometry", "attributes"})
public class LayerSchemaDTO implements Serializable {

    private static final long serialVersionUID = -1361873282045310490L;
    /**
     * Name of feature type
     */
    private String typeName;
    /**
     * Feature namespace
     */
    private String targetNamespace;
    /**
     * URL of the server serving the feature
     */
    private String scope;
    /**
     * Geometry attribute
     */
    private GeometryAttributeDTO geometry;
    /**
     * Non-spatial attributes
     */
    @XmlElementWrapper(name = "attributes")
    @XmlElement(name = "attribute")
    private List<AttributeDTO> attributes;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public GeometryAttributeDTO getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryAttributeDTO geometry) {
        this.geometry = geometry;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public List<AttributeDTO> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public List<AttributeDTO> getAttributesCopy() {
        List<AttributeDTO> copyAttributes = new ArrayList<AttributeDTO>();
        for (AttributeDTO attributeDTO : attributes) {
            copyAttributes.add(attributeDTO.copy());
        }

        return copyAttributes;
    }

    public List<String> getAttributeNames() {
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }
        List<String> attributeNames = new ArrayList<String>(attributes.size());
        for (AttributeDTO attribute : attributes) {
            attributeNames.add(attribute.getName());
        }
        return attributeNames;
    }

    @Override
    public String toString() {
        return "LayerSchemaDTO {"
                + " typeName = " + typeName
                + ", targetNamespace = " + targetNamespace
                + ", scope = " + scope
                + ", geometry = " + geometry
                + ", attributes = " + attributes + '}';
    }

}
