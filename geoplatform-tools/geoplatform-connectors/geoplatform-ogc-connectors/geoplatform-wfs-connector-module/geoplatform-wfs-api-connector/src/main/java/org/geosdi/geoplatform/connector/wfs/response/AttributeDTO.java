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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

/**
 * @author Francesco Izzi <francesco.izzi@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "attribute")
@XmlSeeAlso(value = {GeometryAttributeDTO.class})
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID = 4000281402477022477L;
    //
    private String type;
    private String name;
    private String value;
    private int maxOccurs;
    private int minOccurs;
    private boolean nillable;

    public AttributeDTO() {
    }

    /**
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link String}
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return {@link Integer}
     */
    public int getMaxOccurs() {
        return maxOccurs;
    }

    /**
     * @param maxOccurs
     */
    public void setMaxOccurs(int maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    /**
     * @return {@link Integer}
     */
    public int getMinOccurs() {
        return minOccurs;
    }

    /**
     * @param minOccurs
     */
    public void setMinOccurs(int minOccurs) {
        this.minOccurs = minOccurs;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isNillable() {
        return nillable;
    }

    /**
     * @param nillable
     */
    public void setNillable(boolean nillable) {
        this.nillable = nillable;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isDateType() {
        return (this.type.equalsIgnoreCase("dateTime") || (this.type.equalsIgnoreCase("date")));
    }

    /**
     * @return {@link AttributeDTO}
     */
    public AttributeDTO copy() {
        AttributeDTO copy = new AttributeDTO();
        copy.setName(this.getName());
        copy.setValue(this.getValue());
        copy.setType(this.getType());
        copy.setMaxOccurs(this.getMaxOccurs());
        copy.setMinOccurs(this.getMinOccurs());
        copy.setNillable(this.isNillable());
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AttributeDTO {");
        str.append("name=").append(name);
        str.append(", value=").append(value);
        str.append(", type=").append(type);
        str.append(", maxOccurs=").append(maxOccurs);
        str.append(", minOccurs=").append(minOccurs);
        str.append(", nillable=").append(nillable);
        return str.append("}").toString();
    }
}