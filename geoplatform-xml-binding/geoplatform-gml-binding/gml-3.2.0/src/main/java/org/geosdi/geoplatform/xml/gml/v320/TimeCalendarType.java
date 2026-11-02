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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeCalendarType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeCalendarType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       <sequence>
 *         <element name="referenceFrame" type="{http://www.opengis.net/gml}TimeCalendarEraPropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeCalendarType", propOrder = {
    "referenceFrame"
})
public class TimeCalendarType
    extends TimeReferenceSystemType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected List<TimeCalendarEraPropertyType> referenceFrame;

    /**
     * Gets the value of the referenceFrame property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the referenceFrame property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getReferenceFrame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeCalendarEraPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the referenceFrame property.
     */
    public List<TimeCalendarEraPropertyType> getReferenceFrame() {
        if (referenceFrame == null) {
            referenceFrame = new ArrayList<>();
        }
        return this.referenceFrame;
    }

    public boolean isSetReferenceFrame() {
        return ((this.referenceFrame!= null)&&(!this.referenceFrame.isEmpty()));
    }

    public void unsetReferenceFrame() {
        this.referenceFrame = null;
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
            List<TimeCalendarEraPropertyType> theReferenceFrame;
            theReferenceFrame = (this.isSetReferenceFrame()?this.getReferenceFrame():null);
            strategy.appendField(locator, this, "referenceFrame", buffer, theReferenceFrame, this.isSetReferenceFrame());
        }
        return buffer;
    }

    public void setReferenceFrame(List<TimeCalendarEraPropertyType> value) {
        this.referenceFrame = null;
        if (value!= null) {
            List<TimeCalendarEraPropertyType> draftl = this.getReferenceFrame();
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
        final TimeCalendarType that = ((TimeCalendarType) object);
        {
            List<TimeCalendarEraPropertyType> lhsReferenceFrame;
            lhsReferenceFrame = (this.isSetReferenceFrame()?this.getReferenceFrame():null);
            List<TimeCalendarEraPropertyType> rhsReferenceFrame;
            rhsReferenceFrame = (that.isSetReferenceFrame()?that.getReferenceFrame():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceFrame", lhsReferenceFrame), LocatorUtils.property(thatLocator, "referenceFrame", rhsReferenceFrame), lhsReferenceFrame, rhsReferenceFrame, this.isSetReferenceFrame(), that.isSetReferenceFrame())) {
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
            List<TimeCalendarEraPropertyType> theReferenceFrame;
            theReferenceFrame = (this.isSetReferenceFrame()?this.getReferenceFrame():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "referenceFrame", theReferenceFrame), currentHashCode, theReferenceFrame, this.isSetReferenceFrame());
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
        if (draftCopy instanceof TimeCalendarType) {
            final TimeCalendarType copy = ((TimeCalendarType) draftCopy);
            {
                java.lang.Boolean referenceFrameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReferenceFrame());
                if (referenceFrameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeCalendarEraPropertyType> sourceReferenceFrame;
                    sourceReferenceFrame = (this.isSetReferenceFrame()?this.getReferenceFrame():null);
                    @SuppressWarnings("unchecked")
                    List<TimeCalendarEraPropertyType> copyReferenceFrame = ((List<TimeCalendarEraPropertyType> ) strategy.copy(LocatorUtils.property(locator, "referenceFrame", sourceReferenceFrame), sourceReferenceFrame, this.isSetReferenceFrame()));
                    copy.setReferenceFrame(copyReferenceFrame);
                } else {
                    if (referenceFrameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetReferenceFrame();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeCalendarType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeCalendarType) {
            final TimeCalendarType target = this;
            final TimeCalendarType leftObject = ((TimeCalendarType) left);
            final TimeCalendarType rightObject = ((TimeCalendarType) right);
            {
                java.lang.Boolean referenceFrameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReferenceFrame(), rightObject.isSetReferenceFrame());
                if (referenceFrameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeCalendarEraPropertyType> lhsReferenceFrame;
                    lhsReferenceFrame = (leftObject.isSetReferenceFrame()?leftObject.getReferenceFrame():null);
                    List<TimeCalendarEraPropertyType> rhsReferenceFrame;
                    rhsReferenceFrame = (rightObject.isSetReferenceFrame()?rightObject.getReferenceFrame():null);
                    List<TimeCalendarEraPropertyType> mergedReferenceFrame = ((List<TimeCalendarEraPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "referenceFrame", lhsReferenceFrame), LocatorUtils.property(rightLocator, "referenceFrame", rhsReferenceFrame), lhsReferenceFrame, rhsReferenceFrame, leftObject.isSetReferenceFrame(), rightObject.isSetReferenceFrame()));
                    target.setReferenceFrame(mergedReferenceFrame);
                } else {
                    if (referenceFrameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetReferenceFrame();
                    }
                }
            }
        }
    }

}
