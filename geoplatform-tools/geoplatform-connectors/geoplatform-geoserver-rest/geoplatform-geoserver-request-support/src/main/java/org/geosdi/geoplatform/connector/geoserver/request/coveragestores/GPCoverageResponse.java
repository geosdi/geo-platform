/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

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