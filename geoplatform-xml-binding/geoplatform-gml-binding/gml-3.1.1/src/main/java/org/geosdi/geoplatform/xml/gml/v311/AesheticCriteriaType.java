/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.xml.gml.v311;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AesheticCriteriaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AesheticCriteriaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MIN_CROSSINGS"/>
 *     &lt;enumeration value="MIN_AREA"/>
 *     &lt;enumeration value="MIN_BENDS"/>
 *     &lt;enumeration value="MAX_BENDS"/>
 *     &lt;enumeration value="UNIFORM_BENDS"/>
 *     &lt;enumeration value="MIN_SLOPES"/>
 *     &lt;enumeration value="MIN_EDGE_LENGTH"/>
 *     &lt;enumeration value="MAX_EDGE_LENGTH"/>
 *     &lt;enumeration value="UNIFORM_EDGE_LENGTH"/>
 *     &lt;enumeration value="MAX_ANGULAR_RESOLUTION"/>
 *     &lt;enumeration value="MIN_ASPECT_RATIO"/>
 *     &lt;enumeration value="MAX_SYMMETRIES"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AesheticCriteriaType")
@XmlEnum
public enum AesheticCriteriaType {

    @XmlEnumValue("MIN_CROSSINGS")
    MIN___CROSSINGS("MIN_CROSSINGS"),
    @XmlEnumValue("MIN_AREA")
    MIN___AREA("MIN_AREA"),
    @XmlEnumValue("MIN_BENDS")
    MIN___BENDS("MIN_BENDS"),
    @XmlEnumValue("MAX_BENDS")
    MAX___BENDS("MAX_BENDS"),
    @XmlEnumValue("UNIFORM_BENDS")
    UNIFORM___BENDS("UNIFORM_BENDS"),
    @XmlEnumValue("MIN_SLOPES")
    MIN___SLOPES("MIN_SLOPES"),
    @XmlEnumValue("MIN_EDGE_LENGTH")
    MIN___EDGE___LENGTH("MIN_EDGE_LENGTH"),
    @XmlEnumValue("MAX_EDGE_LENGTH")
    MAX___EDGE___LENGTH("MAX_EDGE_LENGTH"),
    @XmlEnumValue("UNIFORM_EDGE_LENGTH")
    UNIFORM___EDGE___LENGTH("UNIFORM_EDGE_LENGTH"),
    @XmlEnumValue("MAX_ANGULAR_RESOLUTION")
    MAX___ANGULAR___RESOLUTION("MAX_ANGULAR_RESOLUTION"),
    @XmlEnumValue("MIN_ASPECT_RATIO")
    MIN___ASPECT___RATIO("MIN_ASPECT_RATIO"),
    @XmlEnumValue("MAX_SYMMETRIES")
    MAX___SYMMETRIES("MAX_SYMMETRIES");
    private final String value;

    AesheticCriteriaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AesheticCriteriaType fromValue(String v) {
        for (AesheticCriteriaType c : AesheticCriteriaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        return "AesheticCriteriaType{ " + "value = " + value + '}';
    }
}
