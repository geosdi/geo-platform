package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name", "type", "enabled", "__default", "dateCreated", "dateModified", "url"})
@XmlRootElement(name = "coverageStore")
@Getter
@ToString
public class GPCoverageResponse implements Serializable {

    private static final long serialVersionUID = -4161438867237643692L;
    //
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "enabled")
    private Boolean enabled;
    @XmlElement(name = "__default")
    private String __default;
    @XmlElement(name = "dateCreated")
    private String dateCreated;
    @XmlElement(name = "dateModified")
    private String dateModified;
    @XmlElement(name = "url")
    private String url;

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
}