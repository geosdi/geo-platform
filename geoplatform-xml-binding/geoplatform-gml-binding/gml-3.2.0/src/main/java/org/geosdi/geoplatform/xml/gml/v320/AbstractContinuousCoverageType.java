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
 * <p>Classe Java per AbstractContinuousCoverageType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractContinuousCoverageType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCoverageType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}coverageFunction" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractContinuousCoverageType", propOrder = {
    "coverageFunction"
})
public abstract class AbstractContinuousCoverageType
    extends AbstractCoverageType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The gml:coverageFunction property describes the mapping function from the domain to the range of the coverage.
     * The value of the CoverageFunction is one of gml:CoverageMappingRule and gml:GridFunction.
     * If the gml:coverageFunction property is omitted for a gridded coverage (including rectified gridded coverages) the gml:startPoint is assumed to be the value of the gml:low property in the gml:Grid geometry, and the gml:sequenceRule is assumed to be linear and the gml:axisOrder property is assumed to be “+1 +2”.
     * 
     */
    protected CoverageFunctionType coverageFunction;

    /**
     * The gml:coverageFunction property describes the mapping function from the domain to the range of the coverage.
     * The value of the CoverageFunction is one of gml:CoverageMappingRule and gml:GridFunction.
     * If the gml:coverageFunction property is omitted for a gridded coverage (including rectified gridded coverages) the gml:startPoint is assumed to be the value of the gml:low property in the gml:Grid geometry, and the gml:sequenceRule is assumed to be linear and the gml:axisOrder property is assumed to be “+1 +2”.
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
     * Imposta il valore della proprietà coverageFunction.
     * 
     * @param value
     *     allowed object is
     *     {@link CoverageFunctionType }
     *     
     * @see #getCoverageFunction()
     */
    public void setCoverageFunction(CoverageFunctionType value) {
        this.coverageFunction = value;
    }

    public boolean isSetCoverageFunction() {
        return (this.coverageFunction!= null);
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
            CoverageFunctionType theCoverageFunction;
            theCoverageFunction = this.getCoverageFunction();
            strategy.appendField(locator, this, "coverageFunction", buffer, theCoverageFunction, this.isSetCoverageFunction());
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
        final AbstractContinuousCoverageType that = ((AbstractContinuousCoverageType) object);
        {
            CoverageFunctionType lhsCoverageFunction;
            lhsCoverageFunction = this.getCoverageFunction();
            CoverageFunctionType rhsCoverageFunction;
            rhsCoverageFunction = that.getCoverageFunction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coverageFunction", lhsCoverageFunction), LocatorUtils.property(thatLocator, "coverageFunction", rhsCoverageFunction), lhsCoverageFunction, rhsCoverageFunction, this.isSetCoverageFunction(), that.isSetCoverageFunction())) {
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
            CoverageFunctionType theCoverageFunction;
            theCoverageFunction = this.getCoverageFunction();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coverageFunction", theCoverageFunction), currentHashCode, theCoverageFunction, this.isSetCoverageFunction());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractContinuousCoverageType) {
            final AbstractContinuousCoverageType copy = ((AbstractContinuousCoverageType) target);
            {
                java.lang.Boolean coverageFunctionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoverageFunction());
                if (coverageFunctionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CoverageFunctionType sourceCoverageFunction;
                    sourceCoverageFunction = this.getCoverageFunction();
                    CoverageFunctionType copyCoverageFunction = ((CoverageFunctionType) strategy.copy(LocatorUtils.property(locator, "coverageFunction", sourceCoverageFunction), sourceCoverageFunction, this.isSetCoverageFunction()));
                    copy.setCoverageFunction(copyCoverageFunction);
                } else {
                    if (coverageFunctionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coverageFunction = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractContinuousCoverageType) {
            final AbstractContinuousCoverageType target = this;
            final AbstractContinuousCoverageType leftObject = ((AbstractContinuousCoverageType) left);
            final AbstractContinuousCoverageType rightObject = ((AbstractContinuousCoverageType) right);
            {
                java.lang.Boolean coverageFunctionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoverageFunction(), rightObject.isSetCoverageFunction());
                if (coverageFunctionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CoverageFunctionType lhsCoverageFunction;
                    lhsCoverageFunction = leftObject.getCoverageFunction();
                    CoverageFunctionType rhsCoverageFunction;
                    rhsCoverageFunction = rightObject.getCoverageFunction();
                    CoverageFunctionType mergedCoverageFunction = ((CoverageFunctionType) strategy.merge(LocatorUtils.property(leftLocator, "coverageFunction", lhsCoverageFunction), LocatorUtils.property(rightLocator, "coverageFunction", rhsCoverageFunction), lhsCoverageFunction, rhsCoverageFunction, leftObject.isSetCoverageFunction(), rightObject.isSetCoverageFunction()));
                    target.setCoverageFunction(mergedCoverageFunction);
                } else {
                    if (coverageFunctionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coverageFunction = null;
                    }
                }
            }
        }
    }

}
