/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.geoserver.acl.services;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GPGeoserverRule;
import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GPGeoserverRules;
import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GeoserverRule;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(NAME_ASCENDING)
public class GeoserverConnectorAclServicesTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorAclServicesTest.class);
    //

    @Test
    public void a_loadAclServices() throws Exception {
        logger.info("################### {}\n", this.geoserverConnectorStore.loadAclServices().getResponse());
    }

    @Test
    public void b_createAclServices() throws Exception {
        GPGeoserverRules gpGeoserverRules = new GPGeoserverRules();
        GeoserverRule gpGeoserverAclLayers = new GPGeoserverRule();
        gpGeoserverAclLayers.setResource("gwc.*");
        gpGeoserverAclLayers.setValue("*");
        GeoserverRule gpGeoserverAclLayers2 = new GPGeoserverRule();
        gpGeoserverAclLayers2.setResource("WCS.*");
        gpGeoserverAclLayers2.setValue("*");
        gpGeoserverRules.setRules(Lists.newArrayList(gpGeoserverAclLayers, gpGeoserverAclLayers2));
        logger.info("################### {}\n",
                this.geoserverConnectorStore.createAclServices().withBody(gpGeoserverRules).getResponse());
    }

    @Test
    public void c_updateAclServices() throws Exception {
        GPGeoserverRules gpGeoserverRules = new GPGeoserverRules();
        GeoserverRule gpGeoserverAclLayers = new GPGeoserverRule();
        gpGeoserverAclLayers.setResource("gwc.*");
        gpGeoserverAclLayers.setValue("ROLE_ANONYMOUS");
        GeoserverRule gpGeoserverAclLayers2 = new GPGeoserverRule();
        gpGeoserverAclLayers2.setResource("WCS.*");
        gpGeoserverAclLayers2.setValue("ROLE_ANONYMOUS");
        gpGeoserverRules.setRules(Lists.newArrayList(gpGeoserverAclLayers, gpGeoserverAclLayers2));
        logger.info("################### {}\n",
                this.geoserverConnectorStore.updateAclServices().withBody(gpGeoserverRules).getResponseAsString());
    }

    @Test
    public void d_deleteAclServices() throws Exception {
        logger.info("################### {}\n",
                this.geoserverConnectorStore.deleteAclServicesByRule().withResource("gwc.*").getResponse());
        logger.info("################### {}\n",
                this.geoserverConnectorStore.deleteAclServicesByRule().withResource("WCS.*").getResponse());
    }
}