/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.server.command;

import org.geosdi.geoplatform.gui.command.api.GPCommandRequest;
import org.geosdi.geoplatform.gui.command.api.GPCommandResponse;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseCSWCommand<Request extends GPCommandRequest, Response extends GPCommandResponse> implements GPCommand<Request, Response>, InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformCSWService geoPlatformCSWClient;

    /**
     * @param geoPlatformCSWClient the geoPlatformCSWService to set
     */
    @Autowired
    public void setGeoPlatformCSWClient(@Qualifier("geoPlatformCSWClient") GeoPlatformCSWService geoPlatformCSWClient) {
        this.geoPlatformCSWClient = geoPlatformCSWClient;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(this.geoPlatformCSWClient != null, "The Parameter geoPlatformCSWClient must not be null.");
    }

    /**
     * @param serverDTO
     * @return {@link GPCSWServerBeanModel}
     */
    protected static GPCSWServerBeanModel convertServerDTO(ServerCSWDTO serverDTO) {
        GPCSWServerBeanModel server = new GPCSWServerBeanModel();
        server.setId(serverDTO.getId());
        server.setUrlServer(serverDTO.getServerUrl());
        server.setTitle(serverDTO.getTitle());
        server.setAlias(serverDTO.getAlias());
        return server;
    }
}