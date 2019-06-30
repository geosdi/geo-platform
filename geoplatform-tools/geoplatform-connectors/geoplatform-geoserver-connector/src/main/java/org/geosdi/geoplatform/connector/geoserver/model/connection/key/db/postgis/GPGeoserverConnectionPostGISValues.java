package org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.GPGeoserverConnectionDatabaseValues;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.*;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionPostGISValues implements IGPGeoserverConnectionKey {

    PORT(of("port", "Port", user, "Integer", TRUE, 4326)),
    SUPPORT_ON_THE_FLY_GEOMETRY_SEMPLIFICATION(of("Support on the fly geometry simplification", "When enabled, operations such as map rendering will pass a hint that will enable the usage of ST_Simplify", user, "Boolean", FALSE, TRUE)),
    CREATE_DATABASE(of("create database", "Creates the database if it does not exist yet", advanced, "Boolean", FALSE, FALSE)),
    CREATE_DATABASE_PARAMS(of("create database params", "Extra specifications appeneded to the CREATE DATABASE command", advanced, "String", FALSE, null)),
    DBTYPE(of("dbtype", "Type", program, "String", TRUE, "postgis")),
    MAX_OPEN_PREPARED_STATEMENTS(of("Max open prepared statements", "Maximum number of prepared statements kept open and cached for each connection in the pool. Set to 0 to have unbounded caching, to -1 to disable caching", user, "Integer", FALSE, 50)),
    ENCODE_FUNCTIONS(of("encode functions", "Set to true to have a set of filter functions be translated directly in SQL. Due to differences in the type systems the result might not be the same as evaluating them in memory, including the SQL failing with errors while the in memory version works fine. However this allows to push more of the filter into the database, increasing performance.the postgis table.", advanced, "Boolean", FALSE, FALSE)),
    HOST(of("host", "Host", user, "String", TRUE, "localhost")),
    LOOSE_BBOX(of("Loose bbox", "Perform only primary filter on bbox", user, "Boolean", FALSE, TRUE)),
    ESTIMATED_EXTENDS(of("Estimated extends", "Use the spatial index information to quickly get an estimate of the data bounds", user, "Boolean", FALSE, TRUE)),
    DATABASE(of("database", "Database", user, "String", FALSE, null)),
    TEST_WHILE_IDLE(of("Test while idle", "Periodically test the connections are still valid also while idle in the pool", user, "Boolean", FALSE, TRUE)),
    PREPARED_STATEMENTS(of("preparedStatements", "Use prepared statements", user, "Boolean", FALSE, FALSE)),
    SCHEMA(of("schema", "Schema", user, "String", FALSE, "public")),
    USER(of("user", "User name to login as", user, "String", TRUE, null));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionPostGISValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
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
        List<IGPGeoserverConnectionKey> postgisRequiredValues = stream(GPGeoserverConnectionPostGISValues.values())
                .filter(IGPGeoserverConnectionKey::isRequired)
                .collect(Collectors.toList());
        postgisRequiredValues.addAll(GPGeoserverConnectionDatabaseValues.requiredValues());
        return postgisRequiredValues;
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        List<IGPGeoserverConnectionKey> postgisDefaultValues = stream(GPGeoserverConnectionPostGISValues.values())
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
        postgisDefaultValues.addAll(GPGeoserverConnectionDatabaseValues.defaultValues());
        return postgisDefaultValues;
    }
}