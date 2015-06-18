//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TopoSurfacePropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TopoSurfacePropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}TopoSurface"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}OwnershipAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopoSurfacePropertyType", propOrder = {
    "topoSurface"
})
public class TopoSurfacePropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "TopoSurface", required = true)
    protected TopoSurfaceType topoSurface;
    @XmlAttribute(name = "owns")
    protected java.lang.Boolean owns;

    /**
     * Recupera il valore della proprietà topoSurface.
     * 
     * @return
     *     possible object is
     *     {@link TopoSurfaceType }
     *     
     */
    public TopoSurfaceType getTopoSurface() {
        return topoSurface;
    }

    /**
     * Imposta il valore della proprietà topoSurface.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoSurfaceType }
     *     
     */
    public void setTopoSurface(TopoSurfaceType value) {
        this.topoSurface = value;
    }

    public boolean isSetTopoSurface() {
        return (this.topoSurface!= null);
    }

    /**
     * Recupera il valore della proprietà owns.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isOwns() {
        if (owns == null) {
            return false;
        } else {
            return owns;
        }
    }

    /**
     * Imposta il valore della proprietà owns.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setOwns(boolean value) {
        this.owns = value;
    }

    public boolean isSetOwns() {
        return (this.owns!= null);
    }

    public void unsetOwns() {
        this.owns = null;
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
        {
            TopoSurfaceType theTopoSurface;
            theTopoSurface = this.getTopoSurface();
            strategy.appendField(locator, this, "topoSurface", buffer, theTopoSurface);
        }
        {
            boolean theOwns;
            theOwns = this.isOwns();
            strategy.appendField(locator, this, "owns", buffer, theOwns);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TopoSurfacePropertyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TopoSurfacePropertyType that = ((TopoSurfacePropertyType) object);
        {
            TopoSurfaceType lhsTopoSurface;
            lhsTopoSurface = this.getTopoSurface();
            TopoSurfaceType rhsTopoSurface;
            rhsTopoSurface = that.getTopoSurface();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "topoSurface", lhsTopoSurface), LocatorUtils.property(thatLocator, "topoSurface", rhsTopoSurface), lhsTopoSurface, rhsTopoSurface)) {
                return false;
            }
        }
        {
            boolean lhsOwns;
            lhsOwns = this.isOwns();
            boolean rhsOwns;
            rhsOwns = that.isOwns();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owns", lhsOwns), LocatorUtils.property(thatLocator, "owns", rhsOwns), lhsOwns, rhsOwns)) {
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
        int currentHashCode = 1;
        {
            TopoSurfaceType theTopoSurface;
            theTopoSurface = this.getTopoSurface();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "topoSurface", theTopoSurface), currentHashCode, theTopoSurface);
        }
        {
            boolean theOwns;
            theOwns = this.isOwns();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owns", theOwns), currentHashCode, theOwns);
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
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof TopoSurfacePropertyType) {
            final TopoSurfacePropertyType copy = ((TopoSurfacePropertyType) draftCopy);
            if (this.isSetTopoSurface()) {
                TopoSurfaceType sourceTopoSurface;
                sourceTopoSurface = this.getTopoSurface();
                TopoSurfaceType copyTopoSurface = ((TopoSurfaceType) strategy.copy(LocatorUtils.property(locator, "topoSurface", sourceTopoSurface), sourceTopoSurface));
                copy.setTopoSurface(copyTopoSurface);
            } else {
                copy.topoSurface = null;
            }
            if (this.isSetOwns()) {
                boolean sourceOwns;
                sourceOwns = this.isOwns();
                boolean copyOwns = strategy.copy(LocatorUtils.property(locator, "owns", sourceOwns), sourceOwns);
                copy.setOwns(copyOwns);
            } else {
                copy.unsetOwns();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TopoSurfacePropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof TopoSurfacePropertyType) {
            final TopoSurfacePropertyType target = this;
            final TopoSurfacePropertyType leftObject = ((TopoSurfacePropertyType) left);
            final TopoSurfacePropertyType rightObject = ((TopoSurfacePropertyType) right);
            {
                TopoSurfaceType lhsTopoSurface;
                lhsTopoSurface = leftObject.getTopoSurface();
                TopoSurfaceType rhsTopoSurface;
                rhsTopoSurface = rightObject.getTopoSurface();
                target.setTopoSurface(((TopoSurfaceType) strategy.merge(LocatorUtils.property(leftLocator, "topoSurface", lhsTopoSurface), LocatorUtils.property(rightLocator, "topoSurface", rhsTopoSurface), lhsTopoSurface, rhsTopoSurface)));
            }
            {
                boolean lhsOwns;
                lhsOwns = leftObject.isOwns();
                boolean rhsOwns;
                rhsOwns = rightObject.isOwns();
                target.setOwns(((boolean) strategy.merge(LocatorUtils.property(leftLocator, "owns", lhsOwns), LocatorUtils.property(rightLocator, "owns", rhsOwns), lhsOwns, rhsOwns)));
            }
        }
    }

    public TopoSurfacePropertyType withTopoSurface(TopoSurfaceType value) {
        setTopoSurface(value);
        return this;
    }

    public TopoSurfacePropertyType withOwns(boolean value) {
        setOwns(value);
        return this;
    }

}
