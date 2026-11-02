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
 * <p>Classe Java per ImageDatumType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ImageDatumType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractDatumType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}pixelInCell"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageDatumType", propOrder = {
    "pixelInCell"
})
public class ImageDatumType
    extends AbstractDatumType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:pixelInCell is a specification of the way an image grid is associated with the image data attributes. The required codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     */
    @XmlElement(required = true)
    protected CodeWithAuthorityType pixelInCell;

    /**
     * gml:pixelInCell is a specification of the way an image grid is associated with the image data attributes. The required codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getPixelInCell() {
        return pixelInCell;
    }

    /**
     * Imposta il valore della proprietà pixelInCell.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     * @see #getPixelInCell()
     */
    public void setPixelInCell(CodeWithAuthorityType value) {
        this.pixelInCell = value;
    }

    public boolean isSetPixelInCell() {
        return (this.pixelInCell!= null);
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
            CodeWithAuthorityType thePixelInCell;
            thePixelInCell = this.getPixelInCell();
            strategy.appendField(locator, this, "pixelInCell", buffer, thePixelInCell, this.isSetPixelInCell());
        }
        return buffer;
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
        final ImageDatumType that = ((ImageDatumType) object);
        {
            CodeWithAuthorityType lhsPixelInCell;
            lhsPixelInCell = this.getPixelInCell();
            CodeWithAuthorityType rhsPixelInCell;
            rhsPixelInCell = that.getPixelInCell();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pixelInCell", lhsPixelInCell), LocatorUtils.property(thatLocator, "pixelInCell", rhsPixelInCell), lhsPixelInCell, rhsPixelInCell, this.isSetPixelInCell(), that.isSetPixelInCell())) {
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
            CodeWithAuthorityType thePixelInCell;
            thePixelInCell = this.getPixelInCell();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pixelInCell", thePixelInCell), currentHashCode, thePixelInCell, this.isSetPixelInCell());
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
        if (draftCopy instanceof ImageDatumType) {
            final ImageDatumType copy = ((ImageDatumType) draftCopy);
            {
                java.lang.Boolean pixelInCellShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPixelInCell());
                if (pixelInCellShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType sourcePixelInCell;
                    sourcePixelInCell = this.getPixelInCell();
                    CodeWithAuthorityType copyPixelInCell = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "pixelInCell", sourcePixelInCell), sourcePixelInCell, this.isSetPixelInCell()));
                    copy.setPixelInCell(copyPixelInCell);
                } else {
                    if (pixelInCellShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pixelInCell = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ImageDatumType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ImageDatumType) {
            final ImageDatumType target = this;
            final ImageDatumType leftObject = ((ImageDatumType) left);
            final ImageDatumType rightObject = ((ImageDatumType) right);
            {
                java.lang.Boolean pixelInCellShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPixelInCell(), rightObject.isSetPixelInCell());
                if (pixelInCellShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType lhsPixelInCell;
                    lhsPixelInCell = leftObject.getPixelInCell();
                    CodeWithAuthorityType rhsPixelInCell;
                    rhsPixelInCell = rightObject.getPixelInCell();
                    CodeWithAuthorityType mergedPixelInCell = ((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "pixelInCell", lhsPixelInCell), LocatorUtils.property(rightLocator, "pixelInCell", rhsPixelInCell), lhsPixelInCell, rhsPixelInCell, leftObject.isSetPixelInCell(), rightObject.isSetPixelInCell()));
                    target.setPixelInCell(mergedPixelInCell);
                } else {
                    if (pixelInCellShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pixelInCell = null;
                    }
                }
            }
        }
    }

}
