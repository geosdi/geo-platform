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
package org.geosdi.geoplatform.experimental.mongodb.configuration.properties;

import org.geosdi.geoplatform.experimental.mongodb.configuration.auth.MongoAuth;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.util.StringUtils;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPMongoProperties implements MongoBaseProperties {

    private String mongoHost;
    private Integer mongoPort;
    private String mongoDatabaseName;
    private MongoAuth mongoAuth;
    private UserCredentials userCredentials;

    @Override
    public void setMongoHost(String theMongoHost) {
        this.mongoHost = theMongoHost;
    }

    @Override
    public void setMongoPort(Integer theMongoPort) {
        this.mongoPort = theMongoPort;
    }

    @Override
    public void setMongoAuth(MongoAuth theMongoAut) {
        this.mongoAuth = theMongoAut;
    }

    @Override
    public String getMongoHost() {
        return this.mongoHost;
    }

    @Override
    public Integer getMongoPort() {
        return this.mongoPort;
    }

    @Override
    public void setMongoDatabaseName(String theMongoDatabaseName) {
        this.mongoDatabaseName = theMongoDatabaseName;
    }

    @Override
    public String getMongoDatabaseName() {
        return this.mongoDatabaseName;
    }

    @Override
    public MongoAuth getMongoAuth() {
        return this.mongoAuth;
    }

    @Override
    public UserCredentials getUserCredential() {
        return this.userCredentials = ((this.mongoAuth != null)
                && (this.mongoAuth.isMongoAuthEnabled()))
                ? new UserCredentials(mongoAuth.getMongoUserName(),
                        mongoAuth.getMongoPassword()) : null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!StringUtils.hasText(mongoDatabaseName)) {
            this.mongoDatabaseName = MongoPropertiesEnum.MONGO_DBNAME.mongoProp();
        }

        if (!StringUtils.hasText(mongoHost)) {
            this.mongoHost = MongoPropertiesEnum.MONGO_HOST.mongoProp();
        }

        if ((this.mongoPort == null) || (this.mongoPort <= 0)) {
            this.mongoPort = MongoPropertiesEnum.MONGO_PORT.mongoProp();
        }
    }

}
