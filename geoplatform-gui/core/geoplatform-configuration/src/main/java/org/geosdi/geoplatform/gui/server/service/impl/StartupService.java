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
package org.geosdi.geoplatform.gui.server.service.impl;

import org.geosdi.geoplatform.gui.global.CopyrightInfo;
import org.geosdi.geoplatform.gui.global.IGeoPlatformGlobal;
import org.geosdi.geoplatform.gui.server.service.IStartupService;
import org.geosdi.geoplatform.gui.spring.GeoPlatformContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author giuseppe
 */
@Service(value = "startupService")
public class StartupService implements IStartupService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private IGeoPlatformGlobal geoPlatformGlobal;

    @PostConstruct
    public void init() {
        logger.info("------------------------------> INIT STARTUP-GEO-PLATFORM SERVICE");
        logger.info(this.geoPlatformGlobal.getGeoPlatformInfo().getVersion().getName() + " - Version : {}\n", this.geoPlatformGlobal.getGeoPlatformInfo().getVersion().getVersion());
        for (CopyrightInfo info : this.geoPlatformGlobal.getGeoPlatformInfo().getCopyrightInfo()) {
            logger.info(info.toString());
        }
    }

    @PreDestroy
    public void destroy() {
        logger.info("-------------------> DESTROY STARTUP-GEO-PLATFORM SERVICE");
    }

    @Override
    public IGeoPlatformGlobal initGeoPlatformConfiguration() {
        return geoPlatformGlobal;
    }

    @Override
    @Deprecated
    public IGeoPlatformGlobal initGPConfigurationForBeanName(String beanName) {
        checkArgument(GeoPlatformContextUtil.getInstance().getBean(beanName) != null, "The Bean with name : " + beanName + " is null.");
        return (IGeoPlatformGlobal) GeoPlatformContextUtil.getInstance().getBean(beanName);
    }
}