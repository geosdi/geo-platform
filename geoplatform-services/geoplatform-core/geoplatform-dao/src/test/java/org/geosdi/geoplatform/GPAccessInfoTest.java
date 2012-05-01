/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.geosdi.geoplatform.core.dao.GSAccountDAO;
import org.geosdi.geoplatform.core.dao.GSResourceDAO;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GSAccount;
import org.geosdi.geoplatform.core.model.GSResource;
import org.geosdi.geoplatform.core.model.enums.GrantType;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPAccessInfoTest extends BaseDAOTest {

    private final String gsAccountUserName = "gsuser";
    @Autowired
    protected GSAccountDAO gsAccountDAO;
    @Autowired
    protected GSResourceDAO gsResourceDAO;

    @Test
    public void insertGPAccessInfoTest() {
        this.removeAllGSAccounts();
        GSAccount gsAccount = this.generateGSAccount(this.gsAccountUserName);
        GSResource resource = this.generateResource(gsAccount);
        GPUser gpUser = this.insertUser(this.gsAccountUserName, GPRole.ADMIN, GPRole.USER);
        GPProject gpUserProject = this.createProject("gp_user_project", true, 0,
                                               new Date(System.currentTimeMillis()));
        projectDAO.persist(gpUserProject);
        gpUser.setDefaultProjectID(gpUserProject.getId());
        gpUser.setGsAccount(gsAccount);
        this.gsAccountDAO.persist(gsAccount);
        this.gsResourceDAO.persist(resource);
        accountDAO.merge(gpUser);
    }

    private GSAccount generateGSAccount(String userName) {
        GSAccount account = new GSAccount();
        account.setGsuser(userName);
        account.setAuthkey(UUID.randomUUID().toString());
        return account;
    }

    private GSResource generateResource(GSAccount account) {
        GSResource resource = new GSResource();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        WKTReader reader = new WKTReader(geometryFactory);
        MultiPolygon multiPolygon = null;
        try {
            //"District of Columbia" restrictions
            multiPolygon = (MultiPolygon) reader.read(
                    "MULTIPOLYGON(((-77.008232 38.966557,-76.911209 38.889988,-77.045448 38.78812,-77.035248 38.813915,-77.045189 38.829365,-77.040405 38.838413,-77.039078 38.862431,-77.067886 38.886101,-77.078949 38.9156,-77.122627 38.93206,-77.042389 38.993431,-77.008232 38.966557)))");
        } catch (ParseException ex) {
            logger.error("Error to generate multipolygon: " + ex);
        }
        resource.setArea(multiPolygon);
        resource.setGrant(GrantType.ALLOW);
        resource.setWorkspace("topp");
        resource.setLayerName("states");
        resource.setGsAccount(account);
        return resource;
    }

    private void removeAllGSAccounts() {
        List<GSAccount> accountList = gsAccountDAO.findAll();
        for (GSAccount account : accountList) {
            logger.trace("\n*** GSAccount to REMOVE:\n{}\n***", account);
            boolean removed = gsAccountDAO.remove(account);
            Assert.assertTrue("Old GSAccount NOT removed", removed);
        }
    }
}
