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
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmx;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per ML_CodeDefinition_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ML_CodeDefinition_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmx}CodeDefinition_Type">
 *       <sequence>
 *         <element name="alternativeExpression" type="{http://www.isotc211.org/2005/gmx}CodeAlternativeExpression_PropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ML_CodeDefinition_Type", propOrder = {
    "alternativeExpression"
})
public class MLCodeDefinitionType
    extends CodeDefinitionType
    implements ToString
{

    @XmlElement(required = true)
    protected List<CodeAlternativeExpressionPropertyType> alternativeExpression;

    /**
     * Gets the value of the alternativeExpression property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the alternativeExpression property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAlternativeExpression().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeAlternativeExpressionPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the alternativeExpression property.
     */
    public List<CodeAlternativeExpressionPropertyType> getAlternativeExpression() {
        if (alternativeExpression == null) {
            alternativeExpression = new ArrayList<>();
        }
        return this.alternativeExpression;
    }

    public boolean isSetAlternativeExpression() {
        return ((this.alternativeExpression!= null)&&(!this.alternativeExpression.isEmpty()));
    }

    public void unsetAlternativeExpression() {
        this.alternativeExpression = null;
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
            List<CodeAlternativeExpressionPropertyType> theAlternativeExpression;
            theAlternativeExpression = (this.isSetAlternativeExpression()?this.getAlternativeExpression():null);
            strategy.appendField(locator, this, "alternativeExpression", buffer, theAlternativeExpression, this.isSetAlternativeExpression());
        }
        return buffer;
    }

    public void setAlternativeExpression(List<CodeAlternativeExpressionPropertyType> value) {
        this.alternativeExpression = null;
        if (value!= null) {
            List<CodeAlternativeExpressionPropertyType> draftl = this.getAlternativeExpression();
            draftl.addAll(value);
        }
    }

}
