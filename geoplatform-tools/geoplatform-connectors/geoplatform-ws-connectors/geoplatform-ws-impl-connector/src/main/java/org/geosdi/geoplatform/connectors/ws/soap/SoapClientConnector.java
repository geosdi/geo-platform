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
package org.geosdi.geoplatform.connectors.ws.soap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.geosdi.geoplatform.connectors.ws.GPAbstractWSClientConnector;
import org.geosdi.geoplatform.support.cxf.client.ClientInterceptorStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Abstract class that represents the template for the implementation of all
 * clients SOAP ws. The parameter E is the generic endpoints </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class SoapClientConnector<E> extends GPAbstractWSClientConnector<E> {

    @Autowired(required = false)
    private ClientInterceptorStrategyFactory clientInterceptorStrategyFactory;

    public SoapClientConnector(Class<E> theServiceClass) {
        super(theServiceClass);
    }

    @Override
    protected void create() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        if (clientInterceptorStrategyFactory != null) {
            factory.getInInterceptors().add(
                    this.clientInterceptorStrategyFactory.getLoggingInInterceptor());
            factory.getInInterceptors().add(
                    this.clientInterceptorStrategyFactory.getSecurityInInterceptor());

            factory.getOutInterceptors().add(
                    this.clientInterceptorStrategyFactory.getLoggingOutInterceptor());
            factory.getOutInterceptors().add(
                    this.clientInterceptorStrategyFactory.getSecurityOutInterceptor());
        }

        if (serviceClass == null) {
            throw new IllegalArgumentException(
                    "The Parameter Service Class can't be null.");
        }

        factory.setServiceClass(this.serviceClass);

        if (getAddress() == null) {
            throw new IllegalArgumentException(
                    "The Parameter Address can't be null.");
        }

        factory.setAddress(getAddress());
        this.endpointService = (E) factory.create();
    }

}
