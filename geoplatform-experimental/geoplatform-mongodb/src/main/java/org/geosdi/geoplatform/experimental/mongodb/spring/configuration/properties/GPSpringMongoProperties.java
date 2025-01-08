/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.mongodb.spring.configuration.properties;

import com.mongodb.MongoCredential;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.mongodb.configuration.auth.MongoAuth;
import org.geosdi.geoplatform.experimental.mongodb.configuration.properties.MongoProperties;
import org.geosdi.geoplatform.experimental.mongodb.spring.annotation.GPMongoProp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.inject.Named;

import static com.google.common.base.Preconditions.checkArgument;
import static com.mongodb.MongoCredential.createCredential;
import static org.geosdi.geoplatform.experimental.mongodb.configuration.properties.MongoPropertiesEnum.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@GPMongoProp
@Named(value = "gpSpringMongoProp")
class GPSpringMongoProperties implements MongoProperties {

    private static final long serialVersionUID = 2164578284848305794L;
    //
    private static final String MONGO_HOST_VALUE = "gpMongoConfigurator{gp.mongo.host:@null}";
    private static final String MONGO_PORT_VALUE = "gpMongoConfigurator{gp.mongo.port:@null}";
    private static final String MONGO_DATABASE_NAME_VALUE = "gpMongoConfigurator{gp.mongo.dbname:@null}";
    //
    @Value(MONGO_HOST_VALUE)
    private String mongoHost;
    @Value(MONGO_PORT_VALUE)
    private Integer mongoPort;
    @Value(MONGO_DATABASE_NAME_VALUE)
    private String mongoDatabaseName;
    @Resource(name = "gpSpringMongoAuth")
    private MongoAuth mongoAuth;
    private MongoCredential userCredentials;

    @Override
    public String getMongoHost() {
        return this.mongoHost = (StringUtils.hasText(this.mongoHost)) ? this.mongoHost : (String) MONGO_HOST.mongoProp();
    }

    @Override
    public Integer getMongoPort() {
        return this.mongoPort = (this.mongoPort != null) ? this.mongoPort : (Integer) MONGO_PORT.mongoProp();
    }

    @Override
    public String getMongoDatabaseName() {
        return this.mongoDatabaseName = (StringUtils.hasText(this.mongoDatabaseName)) ? this.mongoDatabaseName : (String) MONGO_DBNAME.mongoProp();
    }

    @Override
    public MongoAuth getMongoAuth() {
        return this.mongoAuth;
    }

    @Override
    public MongoCredential getUserCredential() {
        return this.userCredentials;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     * @throws Exception in the event of misconfiguration (such as failure to set an
     * essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(!this.mongoHost.equals(MONGO_HOST_VALUE), "The Parameter mongoHost must not be " + MONGO_HOST_VALUE);
        checkArgument(!this.mongoDatabaseName.equals(MONGO_DATABASE_NAME_VALUE), "The Parameter mongoDatabaseName must not be " + MONGO_DATABASE_NAME_VALUE);
        checkArgument(this.mongoAuth != null, "The Parameter mongoAuth must not be null.");
        this.userCredentials = this.mongoAuth.isMongoAuthEnabled() ?
                createCredential(mongoAuth.getMongoUserName(), this.mongoDatabaseName,
                        mongoAuth.getMongoPassword().toCharArray()) : null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{ "
                + "mongoHost = " + getMongoHost()
                + ", mongoPort = " + getMongoPort()
                + ", mongoDatabaseName = " + getMongoDatabaseName()
                + ", userCredentials = " + userCredentials + '}';
    }
}