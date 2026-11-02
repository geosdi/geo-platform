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
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * <p>Classe Java per LinearRingType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LinearRingType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractRingType">
 *       <sequence>
 *         <choice>
 *           <choice maxOccurs="unbounded" minOccurs="4">
 *             <element ref="{http://www.opengis.net/gml}pos"/>
 *             <element ref="{http://www.opengis.net/gml}pointProperty"/>
 *             <element ref="{http://www.opengis.net/gml}pointRep"/>
 *           </choice>
 *           <element ref="{http://www.opengis.net/gml}posList"/>
 *           <element ref="{http://www.opengis.net/gml}coordinates"/>
 *         </choice>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinearRingType", propOrder = {
    "posOrPointPropertyOrPointRep",
    "posList",
    "coordinates"
})
public class LinearRingType
    extends AbstractRingType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "pos", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pointProperty", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pointRep", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> posOrPointPropertyOrPointRep;
    protected DirectPositionListType posList;
    protected CoordinatesType coordinates;

    /**
     * Gets the value of the posOrPointPropertyOrPointRep property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the posOrPointPropertyOrPointRep property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPosOrPointPropertyOrPointRep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DirectPositionType }{@code >}
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the posOrPointPropertyOrPointRep property.
     */
    public List<JAXBElement<?>> getPosOrPointPropertyOrPointRep() {
        if (posOrPointPropertyOrPointRep == null) {
            posOrPointPropertyOrPointRep = new ArrayList<>();
        }
        return this.posOrPointPropertyOrPointRep;
    }

    public boolean isSetPosOrPointPropertyOrPointRep() {
        return ((this.posOrPointPropertyOrPointRep!= null)&&(!this.posOrPointPropertyOrPointRep.isEmpty()));
    }

    public void unsetPosOrPointPropertyOrPointRep() {
        this.posOrPointPropertyOrPointRep = null;
    }

    /**
     * Recupera il valore della proprietà posList.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionListType }
     *     
     */
    public DirectPositionListType getPosList() {
        return posList;
    }

    /**
     * Imposta il valore della proprietà posList.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionListType }
     *     
     */
    public void setPosList(DirectPositionListType value) {
        this.posList = value;
    }

    public boolean isSetPosList() {
        return (this.posList!= null);
    }

    /**
     * Recupera il valore della proprietà coordinates.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *     
     */
    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    /**
     * Imposta il valore della proprietà coordinates.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *     
     */
    public void setCoordinates(CoordinatesType value) {
        this.coordinates = value;
    }

    public boolean isSetCoordinates() {
        return (this.coordinates!= null);
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            List<JAXBElement<?>> thePosOrPointPropertyOrPointRep;
            thePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            strategy.appendField(locator, this, "posOrPointPropertyOrPointRep", buffer, thePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            strategy.appendField(locator, this, "posList", buffer, thePosList, this.isSetPosList());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            strategy.appendField(locator, this, "coordinates", buffer, theCoordinates, this.isSetCoordinates());
        }
        return buffer;
    }

    public void setPosOrPointPropertyOrPointRep(List<JAXBElement<?>> value) {
        this.posOrPointPropertyOrPointRep = null;
        if (value!= null) {
            List<JAXBElement<?>> draftl = this.getPosOrPointPropertyOrPointRep();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final LinearRingType that = ((LinearRingType) object);
        {
            List<JAXBElement<?>> lhsPosOrPointPropertyOrPointRep;
            lhsPosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            List<JAXBElement<?>> rhsPosOrPointPropertyOrPointRep;
            rhsPosOrPointPropertyOrPointRep = (that.isSetPosOrPointPropertyOrPointRep()?that.getPosOrPointPropertyOrPointRep():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "posOrPointPropertyOrPointRep", lhsPosOrPointPropertyOrPointRep), LocatorUtils.property(thatLocator, "posOrPointPropertyOrPointRep", rhsPosOrPointPropertyOrPointRep), lhsPosOrPointPropertyOrPointRep, rhsPosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep(), that.isSetPosOrPointPropertyOrPointRep())) {
                return false;
            }
        }
        {
            DirectPositionListType lhsPosList;
            lhsPosList = this.getPosList();
            DirectPositionListType rhsPosList;
            rhsPosList = that.getPosList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "posList", lhsPosList), LocatorUtils.property(thatLocator, "posList", rhsPosList), lhsPosList, rhsPosList, this.isSetPosList(), that.isSetPosList())) {
                return false;
            }
        }
        {
            CoordinatesType lhsCoordinates;
            lhsCoordinates = this.getCoordinates();
            CoordinatesType rhsCoordinates;
            rhsCoordinates = that.getCoordinates();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinates", lhsCoordinates), LocatorUtils.property(thatLocator, "coordinates", rhsCoordinates), lhsCoordinates, rhsCoordinates, this.isSetCoordinates(), that.isSetCoordinates())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<JAXBElement<?>> thePosOrPointPropertyOrPointRep;
            thePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posOrPointPropertyOrPointRep", thePosOrPointPropertyOrPointRep), currentHashCode, thePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList, this.isSetPosList());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinates", theCoordinates), currentHashCode, theCoordinates, this.isSetCoordinates());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof LinearRingType) {
            final LinearRingType copy = ((LinearRingType) draftCopy);
            {
                java.lang.Boolean posOrPointPropertyOrPointRepShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosOrPointPropertyOrPointRep());
                if (posOrPointPropertyOrPointRepShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> sourcePosOrPointPropertyOrPointRep;
                    sourcePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<?>> copyPosOrPointPropertyOrPointRep = ((List<JAXBElement<?>> ) strategy.copy(LocatorUtils.property(locator, "posOrPointPropertyOrPointRep", sourcePosOrPointPropertyOrPointRep), sourcePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep()));
                    copy.setPosOrPointPropertyOrPointRep(copyPosOrPointPropertyOrPointRep);
                } else {
                    if (posOrPointPropertyOrPointRepShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPosOrPointPropertyOrPointRep();
                    }
                }
            }
            {
                java.lang.Boolean posListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosList());
                if (posListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionListType sourcePosList;
                    sourcePosList = this.getPosList();
                    DirectPositionListType copyPosList = ((DirectPositionListType) strategy.copy(LocatorUtils.property(locator, "posList", sourcePosList), sourcePosList, this.isSetPosList()));
                    copy.setPosList(copyPosList);
                } else {
                    if (posListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.posList = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinatesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoordinates());
                if (coordinatesShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType sourceCoordinates;
                    sourceCoordinates = this.getCoordinates();
                    CoordinatesType copyCoordinates = ((CoordinatesType) strategy.copy(LocatorUtils.property(locator, "coordinates", sourceCoordinates), sourceCoordinates, this.isSetCoordinates()));
                    copy.setCoordinates(copyCoordinates);
                } else {
                    if (coordinatesShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coordinates = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new LinearRingType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof LinearRingType) {
            final LinearRingType target = this;
            final LinearRingType leftObject = ((LinearRingType) left);
            final LinearRingType rightObject = ((LinearRingType) right);
            {
                java.lang.Boolean posOrPointPropertyOrPointRepShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosOrPointPropertyOrPointRep(), rightObject.isSetPosOrPointPropertyOrPointRep());
                if (posOrPointPropertyOrPointRepShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> lhsPosOrPointPropertyOrPointRep;
                    lhsPosOrPointPropertyOrPointRep = (leftObject.isSetPosOrPointPropertyOrPointRep()?leftObject.getPosOrPointPropertyOrPointRep():null);
                    List<JAXBElement<?>> rhsPosOrPointPropertyOrPointRep;
                    rhsPosOrPointPropertyOrPointRep = (rightObject.isSetPosOrPointPropertyOrPointRep()?rightObject.getPosOrPointPropertyOrPointRep():null);
                    List<JAXBElement<?>> mergedPosOrPointPropertyOrPointRep = ((List<JAXBElement<?>> ) strategy.merge(LocatorUtils.property(leftLocator, "posOrPointPropertyOrPointRep", lhsPosOrPointPropertyOrPointRep), LocatorUtils.property(rightLocator, "posOrPointPropertyOrPointRep", rhsPosOrPointPropertyOrPointRep), lhsPosOrPointPropertyOrPointRep, rhsPosOrPointPropertyOrPointRep, leftObject.isSetPosOrPointPropertyOrPointRep(), rightObject.isSetPosOrPointPropertyOrPointRep()));
                    target.setPosOrPointPropertyOrPointRep(mergedPosOrPointPropertyOrPointRep);
                } else {
                    if (posOrPointPropertyOrPointRepShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPosOrPointPropertyOrPointRep();
                    }
                }
            }
            {
                java.lang.Boolean posListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosList(), rightObject.isSetPosList());
                if (posListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionListType lhsPosList;
                    lhsPosList = leftObject.getPosList();
                    DirectPositionListType rhsPosList;
                    rhsPosList = rightObject.getPosList();
                    DirectPositionListType mergedPosList = ((DirectPositionListType) strategy.merge(LocatorUtils.property(leftLocator, "posList", lhsPosList), LocatorUtils.property(rightLocator, "posList", rhsPosList), lhsPosList, rhsPosList, leftObject.isSetPosList(), rightObject.isSetPosList()));
                    target.setPosList(mergedPosList);
                } else {
                    if (posListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.posList = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinatesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoordinates(), rightObject.isSetCoordinates());
                if (coordinatesShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType lhsCoordinates;
                    lhsCoordinates = leftObject.getCoordinates();
                    CoordinatesType rhsCoordinates;
                    rhsCoordinates = rightObject.getCoordinates();
                    CoordinatesType mergedCoordinates = ((CoordinatesType) strategy.merge(LocatorUtils.property(leftLocator, "coordinates", lhsCoordinates), LocatorUtils.property(rightLocator, "coordinates", rhsCoordinates), lhsCoordinates, rhsCoordinates, leftObject.isSetCoordinates(), rightObject.isSetCoordinates()));
                    target.setCoordinates(mergedCoordinates);
                } else {
                    if (coordinatesShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coordinates = null;
                    }
                }
            }
        }
    }

}
