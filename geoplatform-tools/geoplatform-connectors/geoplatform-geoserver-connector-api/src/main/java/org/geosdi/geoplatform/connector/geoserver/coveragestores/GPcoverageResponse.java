package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import lombok.Getter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name", "type", "enabled", "__default", "dateCreated", "dateModified", "url"})
@XmlRootElement(name = "coverageStore")
@Getter
public class GPcoverageResponse implements ToString2 {

    @XmlElement(name = "name")
    protected String name;
    @XmlElement(name = "type")
    protected String type;
    @XmlElement(name = "enabled")
    protected Boolean enabled;
    @XmlElement(name = "__default")
    protected String __default;
    @XmlElement(name = "dateCreated")
    protected String dateCreated;
    @XmlElement(name = "dateModified")
    protected String dateModified;
    @XmlElement(name = "url")
    protected String url;

    public boolean isSetName() {
        return (this.name != null);
    }

    public boolean isSetType() {
        return (this.type != null);
    }

    public boolean isSetEnabled() {
        return (this.enabled != null);
    }

    public boolean isSetDefault() {
        return (this.__default != null);
    }

    public boolean isSetDateCreated() {
        return (this.dateCreated != null);
    }

    public boolean isSetDateModified() {
        return (this.dateModified != null);
    }

    public boolean isSetUrl() {
        return (this.url != null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theType;
            theType = this.getType();
            strategy.appendField(locator, this, "type", buffer, theType, this.isSetType());
        }
        {
            Boolean theEnabled;
            theEnabled = this.getEnabled();
            strategy.appendField(locator, this, "enabled", buffer, theEnabled, this.isSetEnabled());
        }
        {
            String theDefault;
            theDefault = this.get__default();
            strategy.appendField(locator, this, "__default", buffer, theDefault, this.isSetDefault());
        }
        {
            String theDateCreated;
            theDateCreated = this.getDateCreated();
            strategy.appendField(locator, this, "dateCreated", buffer, theDateCreated, this.isSetDateCreated());
        }
        {
            String theDateModified;
            theDateModified = this.getDateModified();
            strategy.appendField(locator, this, "dateModified", buffer, theDateModified, this.isSetDateModified());
        }
        {
            String theUrl;
            theUrl = this.getUrl();
            strategy.appendField(locator, this, "url", buffer, theUrl, this.isSetUrl());
        }
        return buffer;
    }
}