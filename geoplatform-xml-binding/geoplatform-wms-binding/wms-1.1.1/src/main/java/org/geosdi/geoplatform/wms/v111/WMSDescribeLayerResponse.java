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
// Generato il: 2026.11.02 alle 02:14:56 PM CET
//

package org.geosdi.geoplatform.wms.v111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "layerDescription"
})
@XmlRootElement(name = "WMS_DescribeLayerResponse")
public class WMSDescribeLayerResponse implements Serializable, ToString
{

    private static final long serialVersionUID = 1L;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlElement(name = "LayerDescription")
    protected List<LayerDescription> layerDescription;

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the layerDescription property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the layerDescription property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLayerDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LayerDescription }
     * </p>
     * 
     * 
     * @return
     *     The value of the layerDescription property.
     */
    public List<LayerDescription> getLayerDescription() {
        if (layerDescription == null) {
            layerDescription = new ArrayList<>();
        }
        return this.layerDescription;
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
            String theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, (this.version!= null));
        }
        {
            List<LayerDescription> theLayerDescription;
            theLayerDescription = (((this.layerDescription!= null)&&(!this.layerDescription.isEmpty()))?this.getLayerDescription():null);
            strategy.appendField(locator, this, "layerDescription", buffer, theLayerDescription, ((this.layerDescription!= null)&&(!this.layerDescription.isEmpty())));
        }
        return buffer;
    }

    public void setLayerDescription(List<LayerDescription> value) {
        this.layerDescription = null;
        if (value!= null) {
            List<LayerDescription> draftl = this.getLayerDescription();
            draftl.addAll(value);
        }
    }

}
