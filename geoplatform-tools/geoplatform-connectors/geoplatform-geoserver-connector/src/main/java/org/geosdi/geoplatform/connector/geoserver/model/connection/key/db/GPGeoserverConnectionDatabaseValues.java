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
package org.geosdi.geoplatform.connector.geoserver.model.connection.key.db;

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
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.user;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionDatabaseValues implements IGPGeoserverConnectionKey {

    PRIMARY_KEY_METADATA_TABLE(of("Primary key metadata table", "The optional table containing primary key structure and sequence associations. Can be expressed as ‘schema.name’ or just ‘name’", user, "String", FALSE, null)),
    CALLBACK_FACTORY(of("Callback factory", "Name of JDBCReaderCallbackFactory to enable on the data store", user, "String", FALSE, null)),
    EVICTION_TESTS_FOR_RUN(of("Evictor tests per run", "Number of connections checked by the idle connection evictor for each of its runs (defaults to 3)", user, "Integer", FALSE, 3)),
    BATCH_INSERT_SIZE(of("Batch insert size", "Number of records inserted in the same batch (default, 1). For optimal performance, set to 100.", user, "Integer", FALSE, 1)),
    FETCH_SIZE(of("fetch size", "Number of records read with each iteraction with the dbms", user, "Integer", FALSE, 1000)),
    CONNECTION_TIMEOUT(of("Connection timeout", "Number of seconds the connection pool will wait before timing out attempting to get a new connection (default, 20 seconds)", user, "Integer", FALSE, 20)),
    NAMESPACE(of("namespace", "Namespace prefix", user, "String", FALSE, null)),
    MAX_CONNECTIONS(of("max connections", "Maximum number of open connections", user, "Integer", FALSE, 10)),
    TEST_WITH_IDLE(of("Test while idle", "Periodically test the connections are still valid also while idle in the pool", user, "Boolean", FALSE, TRUE)),
    MAX_CONNECTION_IDLE_TIME(of("Max connection idle time", "Number of seconds a connection needs to stay idle for the evictor to consider closing it", user, "Integer", FALSE, 300)),
    SESSION_STARTUP_SQL(of("Session startup SQL", "SQL statement executed when the connection is grabbed from the pool", user, "String", FALSE, null)),
    VALIDATE_CONNECTIONS(of("validate connections", "Check connection is alive before using it", user, "Boolean", FALSE, TRUE)),
    PASSWD(of("passwd", "Password used to login", user, "String", FALSE, null)),
    EXPOSE_PRIMARY_KEYS(of("Expose primary keys", "Expose primary key columns as attributes of the feature type", user, "Boolean", FALSE, FALSE)),
    MIN_CONNECTIONS(of("min connections", "Minimum number of pooled connection", user, "Integer", FALSE, 1)),
    EVICTOR_RUN_PERIODICY(of("Evictor run periodicity", "Number of seconds between idle object evitor runs (default, 300 seconds)", user, "Integer", FALSE, 300)),
    SESSION_CLOSEUP_SQL(of("Session close-up SQL", "SQL statement executed when the connection is released to the pool", user, "String", FALSE, null));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionDatabaseValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
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
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> requiredValues() {
        return stream(GPGeoserverConnectionDatabaseValues.values())
                .map(value -> (IGPGeoserverConnectionKey) value)
                .filter(IGPGeoserverConnectionKey::isRequired)
                .collect(toList());
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        return stream(GPGeoserverConnectionDatabaseValues.values())
                .map(value -> (IGPGeoserverConnectionKey) value)
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
    }
}