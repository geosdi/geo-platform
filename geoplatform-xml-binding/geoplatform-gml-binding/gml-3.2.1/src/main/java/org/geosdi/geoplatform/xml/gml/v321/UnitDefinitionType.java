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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per UnitDefinitionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="UnitDefinitionType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}DefinitionType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}quantityType" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}quantityTypeReference" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}catalogSymbol" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitDefinitionType", propOrder = {
    "quantityType",
    "quantityTypeReference",
    "catalogSymbol"
})
@XmlSeeAlso({
    BaseUnitType.class,
    DerivedUnitType.class,
    ConventionalUnitType.class
})
public class UnitDefinitionType
    extends DefinitionType
    implements ToString
{

    /**
     * The gml:quantityType property indicates the phenomenon to which the units apply. This element contains an informal description of the phenomenon or type of physical quantity that is measured or observed. When the physical quantity is the result of an observation or measurement, this term is known as observable type or measurand.
     * The use of gml:quantityType for references to remote values is deprecated.
     * 
     */
    protected StringOrRefType quantityType;
    /**
     * The gml:quantityTypeReference property indicates the phenomenon to which the units apply. The content is a reference to a remote value.
     * 
     */
    protected ReferenceType quantityTypeReference;
    /**
     * The catalogSymbol is the preferred lexical symbol used for this unit of measure.
     * The codeSpace attribute in gml:CodeType identifies a namespace for the catalog symbol value, and might reference the external catalog. The string value in gml:CodeType contains the value of a symbol that should be unique within this catalog namespace. This symbol often appears explicitly in the catalog, but it could be a combination of symbols using a specified algebra of units.
     * 
     */
    protected CodeType catalogSymbol;

    /**
     * The gml:quantityType property indicates the phenomenon to which the units apply. This element contains an informal description of the phenomenon or type of physical quantity that is measured or observed. When the physical quantity is the result of an observation or measurement, this term is known as observable type or measurand.
     * The use of gml:quantityType for references to remote values is deprecated.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getQuantityType() {
        return quantityType;
    }

    /**
     * Imposta il valore della proprietà quantityType.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     * @see #getQuantityType()
     */
    public void setQuantityType(StringOrRefType value) {
        this.quantityType = value;
    }

    public boolean isSetQuantityType() {
        return (this.quantityType!= null);
    }

    /**
     * The gml:quantityTypeReference property indicates the phenomenon to which the units apply. The content is a reference to a remote value.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getQuantityTypeReference() {
        return quantityTypeReference;
    }

    /**
     * Imposta il valore della proprietà quantityTypeReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     * @see #getQuantityTypeReference()
     */
    public void setQuantityTypeReference(ReferenceType value) {
        this.quantityTypeReference = value;
    }

    public boolean isSetQuantityTypeReference() {
        return (this.quantityTypeReference!= null);
    }

    /**
     * The catalogSymbol is the preferred lexical symbol used for this unit of measure.
     * The codeSpace attribute in gml:CodeType identifies a namespace for the catalog symbol value, and might reference the external catalog. The string value in gml:CodeType contains the value of a symbol that should be unique within this catalog namespace. This symbol often appears explicitly in the catalog, but it could be a combination of symbols using a specified algebra of units.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getCatalogSymbol() {
        return catalogSymbol;
    }

    /**
     * Imposta il valore della proprietà catalogSymbol.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getCatalogSymbol()
     */
    public void setCatalogSymbol(CodeType value) {
        this.catalogSymbol = value;
    }

    public boolean isSetCatalogSymbol() {
        return (this.catalogSymbol!= null);
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
            StringOrRefType theQuantityType;
            theQuantityType = this.getQuantityType();
            strategy.appendField(locator, this, "quantityType", buffer, theQuantityType, this.isSetQuantityType());
        }
        {
            ReferenceType theQuantityTypeReference;
            theQuantityTypeReference = this.getQuantityTypeReference();
            strategy.appendField(locator, this, "quantityTypeReference", buffer, theQuantityTypeReference, this.isSetQuantityTypeReference());
        }
        {
            CodeType theCatalogSymbol;
            theCatalogSymbol = this.getCatalogSymbol();
            strategy.appendField(locator, this, "catalogSymbol", buffer, theCatalogSymbol, this.isSetCatalogSymbol());
        }
        return buffer;
    }

}
