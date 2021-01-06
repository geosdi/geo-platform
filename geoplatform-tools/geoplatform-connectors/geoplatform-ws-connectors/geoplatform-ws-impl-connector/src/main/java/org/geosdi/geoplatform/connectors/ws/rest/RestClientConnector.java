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
package org.geosdi.geoplatform.connectors.ws.rest;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.geosdi.geoplatform.connectors.ws.GPAbstractWSClientConnector;
import org.geosdi.geoplatform.support.cxf.rs.provider.configurator.GPRestProviderType;
import org.geosdi.geoplatform.support.cxf.rs.provider.factory.GPRestProviderFactory;
import org.geosdi.geoplatform.support.cxf.rs.provider.jettyson.GPJSONProvider;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

/**
 * <p>
 * Abstract class that represents the template for the implementation of all
 * clients REST ws. The parameter E is the generic endpoints </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class RestClientConnector<E> extends GPAbstractWSClientConnector<E> {

    private GPRestProviderType providerType;
    private Object basicRestProvider;

    public RestClientConnector(Class<E> theServiceClass) {
        super(theServiceClass);
    }

    @Override
    protected void create() {
        if (serviceClass == null) {
            throw new IllegalArgumentException(
                    "The Parameter Service Class can't be null.");
        }

        if (getAddress() == null) {
            throw new IllegalArgumentException(
                    "The Parameter Address can't be null.");
        }

        this.basicRestProvider = GPRestProviderFactory.createProvider(providerType);

        if (this.basicRestProvider == null) {
            throw new IllegalArgumentException("The Provider cannot be null.");
        }

        if ((this.basicRestProvider instanceof GPJSONProvider)
                && ((getExtraClasses() != null) && (getExtraClasses().length > 0))) {
            ((GPJSONProvider) this.basicRestProvider).setExtraClass(
                    getExtraClasses());
        }

        this.endpointService = JAXRSClientFactory.create(getAddress(),
                serviceClass, Arrays.<Object> asList(this.basicRestProvider), Boolean.TRUE);
    }

    /**
     * @param providerType the providerType to set
     */
    @Value("configurator{cxf_rest_provider_type:@null}")
    public void setProviderType(GPRestProviderType providerType) {
        this.providerType = providerType;
    }

    protected abstract Class<?>[] getExtraClasses();

}
