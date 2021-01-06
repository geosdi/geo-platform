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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Classe Java per AbstractDiscreteCoverageType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
    extends AbstractCoverageType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected CoverageFunctionType coverageFunction;

    /**
     * Recupera il valore della proprietà coverageFunction.
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
     */
    public void setCoverageFunction(CoverageFunctionType value) {
        this.coverageFunction = value;
    }

    public boolean isSetCoverageFunction() {
        return (this.coverageFunction!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            CoverageFunctionType theCoverageFunction;
            theCoverageFunction = this.getCoverageFunction();
            strategy.appendField(locator, this, "coverageFunction", buffer, theCoverageFunction);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractDiscreteCoverageType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractDiscreteCoverageType that = ((AbstractDiscreteCoverageType) object);
        {
            CoverageFunctionType lhsCoverageFunction;
            lhsCoverageFunction = this.getCoverageFunction();
            CoverageFunctionType rhsCoverageFunction;
            rhsCoverageFunction = that.getCoverageFunction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coverageFunction", lhsCoverageFunction), LocatorUtils.property(thatLocator, "coverageFunction", rhsCoverageFunction), lhsCoverageFunction, rhsCoverageFunction)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            CoverageFunctionType theCoverageFunction;
            theCoverageFunction = this.getCoverageFunction();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coverageFunction", theCoverageFunction), currentHashCode, theCoverageFunction);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractDiscreteCoverageType) {
            final AbstractDiscreteCoverageType copy = ((AbstractDiscreteCoverageType) target);
            if (this.isSetCoverageFunction()) {
                CoverageFunctionType sourceCoverageFunction;
                sourceCoverageFunction = this.getCoverageFunction();
                CoverageFunctionType copyCoverageFunction = ((CoverageFunctionType) strategy.copy(LocatorUtils.property(locator, "coverageFunction", sourceCoverageFunction), sourceCoverageFunction));
                copy.setCoverageFunction(copyCoverageFunction);
            } else {
                copy.coverageFunction = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractDiscreteCoverageType) {
            final AbstractDiscreteCoverageType target = this;
            final AbstractDiscreteCoverageType leftObject = ((AbstractDiscreteCoverageType) left);
            final AbstractDiscreteCoverageType rightObject = ((AbstractDiscreteCoverageType) right);
            {
                CoverageFunctionType lhsCoverageFunction;
                lhsCoverageFunction = leftObject.getCoverageFunction();
                CoverageFunctionType rhsCoverageFunction;
                rhsCoverageFunction = rightObject.getCoverageFunction();
                target.setCoverageFunction(((CoverageFunctionType) strategy.merge(LocatorUtils.property(leftLocator, "coverageFunction", lhsCoverageFunction), LocatorUtils.property(rightLocator, "coverageFunction", rhsCoverageFunction), lhsCoverageFunction, rhsCoverageFunction)));
            }
        }
    }

    public AbstractDiscreteCoverageType withCoverageFunction(CoverageFunctionType value) {
        setCoverageFunction(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withDomainSet(JAXBElement<? extends DomainSetType> value) {
        setDomainSet(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withRangeSet(RangeSetType value) {
        setRangeSet(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withBoundedBy(BoundingShapeType value) {
        setBoundedBy(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractDiscreteCoverageType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
