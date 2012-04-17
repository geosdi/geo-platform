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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * A discrete coverage consists of a domain set, range set and optionally a coverage function. The domain set consists of either geometry or temporal objects, finite in number. The range set is comprised of a finite number of attribute values each of which is associated to every direct position within any single spatiotemporal object in the domain. In other words, the range values are constant on each spatiotemporal object in the domain. This coverage function maps each element from the coverage domain to an element in its range. This definition conforms to ISO 19123.
 * 
 * <p>Java class for AbstractDiscreteCoverageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractDiscreteCoverageType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCoverageType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}coverageFunction" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDiscreteCoverageType", propOrder = {
    "coverageFunction"
})
@XmlSeeAlso({
    MultiSolidCoverageType.class,
    GridCoverageType.class,
    RectifiedGridCoverageType.class,
    MultiCurveCoverageType.class,
    MultiPointCoverageType.class,
    MultiSurfaceCoverageType.class
})
public abstract class AbstractDiscreteCoverageType
        extends AbstractCoverageType {

    protected CoverageFunctionType coverageFunction;

    public AbstractDiscreteCoverageType() {
    }

    /**
     * Gets the value of the coverageFunction property.
     * 
     * @return
     *     possible object is
     *     {@link CoverageFunctionType }
     *     
     */
    public CoverageFunctionType getCoverageFunction() {
        return coverageFunction;
    }

    /**
     * Sets the value of the coverageFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoverageFunctionType }
     *     
     */
    public void setCoverageFunction(CoverageFunctionType value) {
        this.coverageFunction = value;
    }

    @Override
    public String toString() {
        return "AbstractDiscreteCoverageType{" + "coverageFunction = "
                + coverageFunction + '}';
    }
}
