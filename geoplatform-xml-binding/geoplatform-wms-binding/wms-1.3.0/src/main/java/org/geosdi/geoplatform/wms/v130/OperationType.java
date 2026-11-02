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
// Generato il: 2026.11.02 alle 02:51:45 PM CET
//

package org.geosdi.geoplatform.wms.v130;

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
 * For each operation offered by the server, list the available output
 *         formats and the online resource.
 * 
 * <p>Classe Java per OperationType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OperationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/wms}Format" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/wms}DCPType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationType", propOrder = {
    "format",
    "dcpType"
})
public class OperationType implements ToString
{

    /**
     * A container for listing an available format's MIME type.
     * 
     */
    @XmlElement(name = "Format", required = true)
    protected List<String> format;
    /**
     * Available Distributed Computing Platforms (DCPs) are listed here.
     *         At present, only HTTP is defined.
     * 
     */
    @XmlElement(name = "DCPType", required = true)
    protected List<DCPType> dcpType;

    /**
     * A container for listing an available format's MIME type.
     * 
     * Gets the value of the format property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the format property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the format property.
     */
    public List<String> getFormat() {
        if (format == null) {
            format = new ArrayList<>();
        }
        return this.format;
    }

    /**
     * Available Distributed Computing Platforms (DCPs) are listed here.
     *         At present, only HTTP is defined.
     * 
     * Gets the value of the dcpType property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dcpType property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDCPType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DCPType }
     * </p>
     * 
     * 
     * @return
     *     The value of the dcpType property.
     */
    public List<DCPType> getDCPType() {
        if (dcpType == null) {
            dcpType = new ArrayList<>();
        }
        return this.dcpType;
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
        {
            List<String> theFormat;
            theFormat = (((this.format!= null)&&(!this.format.isEmpty()))?this.getFormat():null);
            strategy.appendField(locator, this, "format", buffer, theFormat, ((this.format!= null)&&(!this.format.isEmpty())));
        }
        {
            List<DCPType> theDCPType;
            theDCPType = (((this.dcpType!= null)&&(!this.dcpType.isEmpty()))?this.getDCPType():null);
            strategy.appendField(locator, this, "dcpType", buffer, theDCPType, ((this.dcpType!= null)&&(!this.dcpType.isEmpty())));
        }
        return buffer;
    }

    public void setFormat(List<String> value) {
        this.format = null;
        if (value!= null) {
            List<String> draftl = this.getFormat();
            draftl.addAll(value);
        }
    }

    public void setDCPType(List<DCPType> value) {
        this.dcpType = null;
        if (value!= null) {
            List<DCPType> draftl = this.getDCPType();
            draftl.addAll(value);
        }
    }

}
