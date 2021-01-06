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
package org.geosdi.geoplatform.support.swagger.spring.configuration;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.configurator.bootstrap.cxf.Rest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Rest
@Immutable
@Component(value = "gpSwaggerConfiguration")
public class GPSwaggerConfiguration implements SwaggerConfiguration {

    @Value(value = "gpSwaggerConfigurator{gp.swagger.resourcePackage:@null}")
    private String resourcePackage;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.api.version:@null}")
    private String version;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.host:@null}")
    private String host;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.basePath:@null}")
    private String basePath;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.title:@null}")
    private String title;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.description:@null}")
    private String description;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.contact:@null}")
    private String contact;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.license:@null}")
    private String license;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.licenseUrl:@null}")
    private String licenseUrl;
    @Value(value = "gpSwaggerConfigurator{gp.swagger.scheme:@null}")
    private String scheme;

    @Override
    public String getResourcePackage() {
        return this.resourcePackage;
    }

    @Override
    public String getVersion() {
        return this.version = (((this.version != null)
                && !(this.version.isEmpty())) ? this.version : "1.0.0");
    }

    /**
     * @return the host
     */
    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public String getBasePath() {
        return this.basePath;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description = (((this.description != null)
                && !(this.description.isEmpty()))
                        ? this.description : "");
    }

    @Override
    public String getContact() {
        return this.contact;
    }

    @Override
    public String getLicense() {
        return this.license = (((this.license != null)
                && !(this.description.isEmpty())) ? this.license : "");
    }

    @Override
    public String getLicenseUrl() {
        return this.licenseUrl = (((this.licenseUrl != null)
                && !(this.licenseUrl.isEmpty()))
                        ? this.licenseUrl : "");
    }

    @Override
    public Boolean isScan() {
        return Boolean.TRUE;
    }

    @Override
    public String[] getSchemes() {
        return ((this.scheme != null) && !(this.scheme.isEmpty())
                ? this.scheme.split(",") : new String[]{"http"});
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument((this.resourcePackage != null)
                && !(this.resourcePackage.isEmpty()), "The Resource Package "
                + "parameter must not be null or an Empty String.");

        Preconditions.checkArgument((this.host != null)
                && !(this.host.isEmpty()), "The Host "
                + "parameter must not be null or an Empty String.");

        Preconditions.checkArgument((this.basePath != null)
                && !(this.basePath.isEmpty()), "The Base Path "
                + "parameter must not be null or an Empty String.");

        Preconditions.checkArgument((this.title != null)
                && !(this.title.isEmpty()), "The Title "
                + "parameter must not be null or an Empty String.");

        Preconditions.checkArgument((this.contact != null)
                && !(this.contact.isEmpty()), "The Contact "
                + "parameter must not be null or an Empty String.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {"
                + "resourcePackage = " + resourcePackage
                + ", version = " + getVersion()
                + ", host = " + host
                + ", basePath = " + basePath
                + ", title = " + title
                + ", description = " + getDescription()
                + ", contact = " + contact
                + ", license = " + getLicense()
                + ", licenseUrl = " + getLicenseUrl()
                + ", schemes = " + Arrays.toString(getSchemes())
                + ", scan = " + isScan() + '}';
    }

}
