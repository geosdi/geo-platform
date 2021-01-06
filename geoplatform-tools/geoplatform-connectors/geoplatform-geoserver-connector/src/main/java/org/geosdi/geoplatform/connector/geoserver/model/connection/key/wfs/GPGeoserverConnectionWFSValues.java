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
package org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.*;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSAxisOrder.COMPLIANT;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSFilterCompliance.LOW;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.WFSStrategy.auto;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionWFSValues implements IGPGeoserverConnectionKey {

    PROTOCOL(of("Protocol", "Sets a preference for the HTTP protocol to use when requesting WFS functionality. Set this value to Boolean.TRUE for POST, Boolean.FALSE for GET or NULL for AUTO", user, "Boolean", FALSE, null)),
    WFS_GET_CAPABILITIES_URL(of("WFS GetCapabilities URL", "Represents a URL to the getCapabilities document or a server instance.", user, "URL", TRUE, null)),
    BUFFER_SIZE(of("Buffer Size", "This allows the user to specify a buffer size in features. This param has a default value of 10 features.", user, "Integer", FALSE, 10)),
    FILTER_COMPLIANCE(of("Filter compliance", "Level of compliance to WFS specification (0-low,1-medium,2-high)", user, "Integer", FALSE, LOW)),
    ENTITY_RESOLVER(of("EntityResolver", "Sets the entity resolver used to expand XML entities", program, "EntityResolver", FALSE, "org.geotools.xml.PreventLocalEntityResolver")),
    TIME_OUT(of("Time-out", "This allows the user to specify a timeout in milliseconds. This param has a default value of 3000ms.", user, "Integer", FALSE, 3000)),
    GML_COMPLIANCE_LEVEL(of("GmlComplianceLevel", "Optional OGC GML compliance level required.", user, "Integer", FALSE, 0)),
    LENIENT(of("Lenient", "Indicates that datastore should do its best to create features from the provided data even if it does not accurately match the schema. Errors will be logged but the parsing will continue if this is true. Default is false", user, "Boolean", FALSE, FALSE)),
    PASSWORD(of("Password", "This allows the user to specify a username. This param should not be used without the USERNAME param.", user, "String", FALSE, null)),
    USE_DEFAULT_SRS(of("Use Default SRS", "Use always the declared DefaultSRS for requests and reproject locally if necessary", advanced, "Boolean", FALSE, FALSE)),
    NAMESPACE(of("Namespace", "Override the original WFS type name namespaces", advanced, "String", FALSE, null)),
    USERNAME(of("Username", "This allows the user to specify a username. This param should not be used without the PASSWORD param.", user, "String", FALSE, null)),
    AXIS_ORDER_FILTER(of("Axis Order Filter", "Indicates axis order used by the remote WFS server for filters. It applies only to WFS 1.x.0 servers. Default is the same as AXIS_ORDER", advanced, "String", FALSE, COMPLIANT)),
    GML_COMPATIBLE_TYPE_NAMES(of("GmlCompatibleTypeNames", "Use Gml Compatible TypeNames (replace : by _).", user, "Boolean", FALSE, FALSE)),
    MAXIMUM_FEATURES(of("Maximum features", "Positive integer used as a hard limit for the amount of Features to retrieve for each FeatureType. A value of zero or not providing this parameter means no limit.", user, "Integer", FALSE, 0)),
    AXIS_ORDER(of("Axis Order", "Indicates axis order used by the remote WFS server in result coordinates. It applies only to WFS 1.x.0 servers. Default is Compliant", advanced, "String", FALSE, COMPLIANT)),
    WFS_STRATEGY(of("WFS Strategy", "Override wfs stragegy with either cubwerx, ionic, mapserver, geoserver, strict, nonstrict or arcgis strategy.", user, "String", FALSE, auto)),
    TRY_GZIP(of("Try GZIP", "Indicates that datastore should use gzip to transfer data if the server supports it. Default is true", user, "Boolean", FALSE, TRUE)),
    ENCODING(of("Encoding", "This allows the user to specify the character encoding of the XML-Requests sent to the Server. Defaults to UTF-8", user, "String", FALSE, "UTF-8")),
    OUTPUT_FORMAT(of("Outputformat", "This allows the user to specify an outputFormat, different from the default one.", advanced, "String", FALSE, null));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionWFSValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        this.key = theKey;
    }


    /**
     * @return {@link String}
     */
    @Override
    public String getConnectionKey() {
        return this.key.getConnectionKey();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getConnectionDescription() {
        return this.key.getConnectionDescription();
    }

    /**
     * @return {@link GPGeoserverConnectionKeyLevel}
     */
    @Override
    public GPGeoserverConnectionKeyLevel getLevel() {
        return this.key.getLevel();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getType() {
        return this.key.getType();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isRequired() {
        return this.key.isRequired();
    }

    /**
     * @return {@link Object}
     */
    @Override
    public Object getDefaultValue() {
        return this.key.getDefaultValue();
    }
    /**
     * @return {@link List <IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> requiredValues() {
        return stream(GPGeoserverConnectionWFSValues.values())
                .filter(IGPGeoserverConnectionKey::isRequired)
                .collect(toList());
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        return stream(GPGeoserverConnectionWFSValues.values())
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
    }
}