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
package org.geosdi.geoplatform.xml.wfs.v110;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "PropertyValueType", propOrder = {"propertyValue"})
public class PropertyValueType implements PropertyValue {

    @XmlMixed
    @XmlAnyElement(lax = true)
    private List<Object> propertyValue;

    public PropertyValueType() {
    }

    public PropertyValueType(Object value) {
        this.propertyValue = Arrays.asList(value);
    }

    /**
     * @return the propertyValue
     */
    @Override
    public Object getPropertyValue() {
        if (propertyValue.size() == 1) {
            return (propertyValue.get(0) instanceof JAXBElement)
                    ? ((JAXBElement) propertyValue.get(0)).getValue()
                    : propertyValue.get(0);
        }
        return propertyValue;
    }

    /**
     * @param propertyValue the propertyValue to set
     */
    @Override
    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = Arrays.asList(propertyValue);
    }

    @Override
    public String toString() {
        return "PropertyValueType{ " + "value = " + getPropertyValue() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.getPropertyValue() != null
                ? this.getPropertyValue().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PropertyValueType other = (PropertyValueType) obj;
        if (this.getPropertyValue() != other.getPropertyValue() && (this.getPropertyValue() == null || !this.propertyValue.equals(
                other.propertyValue))) {
            return false;
        }
        return true;
    }

}
