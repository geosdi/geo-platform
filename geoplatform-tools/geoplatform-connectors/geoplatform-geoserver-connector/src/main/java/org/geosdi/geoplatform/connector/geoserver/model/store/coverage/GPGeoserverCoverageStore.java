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
package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;

import javax.xml.bind.annotation.*;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@AllArgsConstructor
@ToString
@XmlRootElement(name = "coverageStore")
@XmlAccessorType(FIELD)
@XmlType(propOrder = {"name", "description", "type", "workspace", "enabled", "_default", "url", "coverages"})
public class GPGeoserverCoverageStore implements IGPGeoserverCoverageStore {

    private static final long serialVersionUID = -6970625901177655105L;
    //
    private String name;
    private String description;
    private GPCoverageStoreType type;
    private boolean enabled;
    private IGPGeoserverWorkspace workspace;
    @JsonProperty(value = "_default")
    private boolean isDefault;
    private String url;
    private String coverages;

    protected GPGeoserverCoverageStore() {
    }

    /**
     * @param theName
     * @param theDescription
     * @param theType
     * @param theEnabled
     * @param theWorkspace
     * @param theDefault
     * @param theUrl
     * @param theCoverages
     * @return
     */
    @JsonCreator
    protected static IGPGeoserverCoverageStore creator(@JsonProperty(value = "name") String theName,
            @JsonProperty(value = "description") String theDescription, @JsonProperty(value = "type") GPCoverageStoreType theType,
            @JsonProperty(value = "enabled") boolean theEnabled, @JsonProperty(value = "workspace") IGPGeoserverWorkspace theWorkspace,
            @JsonProperty(value = "_default") boolean theDefault, @JsonProperty(value = "url") String theUrl,
            @JsonProperty(value = "coverages") String theCoverages) {
        return new GPGeoserverCoverageStore(theName, theDescription, theType, theEnabled, theWorkspace, theDefault, theUrl, theCoverages);
    }
}