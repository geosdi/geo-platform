//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per BagType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="BagType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}member" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}members" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BagType", propOrder = {
    "member",
    "members"
})
public class BagType
    extends AbstractGMLType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<AssociationRoleType> member;
    protected ArrayAssociationType members;

    /**
     * Gets the value of the member property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the member property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociationRoleType }
     * 
     * 
     */
    public List<AssociationRoleType> getMember() {
        if (member == null) {
            member = new ArrayList<AssociationRoleType>();
        }
        return this.member;
    }

    public boolean isSetMember() {
        return ((this.member!= null)&&(!this.member.isEmpty()));
    }

    public void unsetMember() {
        this.member = null;
    }

    /**
     * Recupera il valore della proprietà members.
     * 
     * @return
     *     possible object is
     *     {@link ArrayAssociationType }
     *     
     */
    public ArrayAssociationType getMembers() {
        return members;
    }

    /**
     * Imposta il valore della proprietà members.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayAssociationType }
     *     
     */
    public void setMembers(ArrayAssociationType value) {
        this.members = value;
    }

    public boolean isSetMembers() {
        return (this.members!= null);
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
            List<AssociationRoleType> theMember;
            theMember = this.getMember();
            strategy.appendField(locator, this, "member", buffer, theMember);
        }
        {
            ArrayAssociationType theMembers;
            theMembers = this.getMembers();
            strategy.appendField(locator, this, "members", buffer, theMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BagType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final BagType that = ((BagType) object);
        {
            List<AssociationRoleType> lhsMember;
            lhsMember = this.getMember();
            List<AssociationRoleType> rhsMember;
            rhsMember = that.getMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "member", lhsMember), LocatorUtils.property(thatLocator, "member", rhsMember), lhsMember, rhsMember)) {
                return false;
            }
        }
        {
            ArrayAssociationType lhsMembers;
            lhsMembers = this.getMembers();
            ArrayAssociationType rhsMembers;
            rhsMembers = that.getMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "members", lhsMembers), LocatorUtils.property(thatLocator, "members", rhsMembers), lhsMembers, rhsMembers)) {
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
            List<AssociationRoleType> theMember;
            theMember = this.getMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "member", theMember), currentHashCode, theMember);
        }
        {
            ArrayAssociationType theMembers;
            theMembers = this.getMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "members", theMembers), currentHashCode, theMembers);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof BagType) {
            final BagType copy = ((BagType) draftCopy);
            if (this.isSetMember()) {
                List<AssociationRoleType> sourceMember;
                sourceMember = this.getMember();
                @SuppressWarnings("unchecked")
                List<AssociationRoleType> copyMember = ((List<AssociationRoleType> ) strategy.copy(LocatorUtils.property(locator, "member", sourceMember), sourceMember));
                copy.unsetMember();
                List<AssociationRoleType> uniqueMemberl = copy.getMember();
                uniqueMemberl.addAll(copyMember);
            } else {
                copy.unsetMember();
            }
            if (this.isSetMembers()) {
                ArrayAssociationType sourceMembers;
                sourceMembers = this.getMembers();
                ArrayAssociationType copyMembers = ((ArrayAssociationType) strategy.copy(LocatorUtils.property(locator, "members", sourceMembers), sourceMembers));
                copy.setMembers(copyMembers);
            } else {
                copy.members = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new BagType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof BagType) {
            final BagType target = this;
            final BagType leftObject = ((BagType) left);
            final BagType rightObject = ((BagType) right);
            {
                List<AssociationRoleType> lhsMember;
                lhsMember = leftObject.getMember();
                List<AssociationRoleType> rhsMember;
                rhsMember = rightObject.getMember();
                target.unsetMember();
                List<AssociationRoleType> uniqueMemberl = target.getMember();
                uniqueMemberl.addAll(((List<AssociationRoleType> ) strategy.merge(LocatorUtils.property(leftLocator, "member", lhsMember), LocatorUtils.property(rightLocator, "member", rhsMember), lhsMember, rhsMember)));
            }
            {
                ArrayAssociationType lhsMembers;
                lhsMembers = leftObject.getMembers();
                ArrayAssociationType rhsMembers;
                rhsMembers = rightObject.getMembers();
                target.setMembers(((ArrayAssociationType) strategy.merge(LocatorUtils.property(leftLocator, "members", lhsMembers), LocatorUtils.property(rightLocator, "members", rhsMembers), lhsMembers, rhsMembers)));
            }
        }
    }

    public void setMember(List<AssociationRoleType> value) {
        List<AssociationRoleType> draftl = this.getMember();
        draftl.addAll(value);
    }

    public BagType withMember(AssociationRoleType... values) {
        if (values!= null) {
            for (AssociationRoleType value: values) {
                getMember().add(value);
            }
        }
        return this;
    }

    public BagType withMember(Collection<AssociationRoleType> values) {
        if (values!= null) {
            getMember().addAll(values);
        }
        return this;
    }

    public BagType withMembers(ArrayAssociationType value) {
        setMembers(value);
        return this;
    }

    public BagType withMember(List<AssociationRoleType> value) {
        setMember(value);
        return this;
    }

    @Override
    public BagType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public BagType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public BagType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public BagType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public BagType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public BagType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public BagType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public BagType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public BagType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public BagType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
