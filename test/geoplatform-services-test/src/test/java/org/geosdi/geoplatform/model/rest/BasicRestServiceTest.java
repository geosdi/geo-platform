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
package org.geosdi.geoplatform.model.rest;

import java.util.ArrayList;
import java.util.Date;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.model.ServiceTest;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath*:applicationContext.xml"})
@TestExecutionListeners(value = {RSListenerBasicServices.class})
@ActiveProfiles(profiles = {"dev"})
abstract class BasicRestServiceTest extends ServiceTest {

    protected static final String organizationNameRSTest = "geoSDI_rs_test";
    // Users
    protected static final String usernameTest = "user_test_rs";
    protected static final String passwordTest = usernameTest;
    protected static final String emailTest = usernameTest + "@" + domainNameTest;
    protected GPUser userTest;
    // Projects
    protected GPProject projectTest;
    protected long idProjectTest = -1;
    // Folders
    protected static final String nameRootFolderA = "rootFolderA";
    protected static final String nameRootFolderB = "rootFolderB";
    protected GPFolder rootFolderA;
    protected GPFolder rootFolderB;
    protected long idRootFolderA = -1;
    protected long idRootFolderB = -1;
    protected long idAccountProject = -1;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Insert User
        idUserTest = this.createAndInsertUser(usernameTest, organizationTest,
                GPRole.USER);
        userTest = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        // Insert Project
        idProjectTest = this.createAndInsertProject("project_test_rs", false, 2,
                new Date(System.currentTimeMillis()));
        projectTest = gpWSClient.getProjectDetail(idProjectTest);
        // Insert the Account as the owner of Project
        this.idAccountProject = this.createAndInsertAccountProject(userTest,
                projectTest, BasePermission.ADMINISTRATION);

        // Create root folders for the user
        idRootFolderA = this.createAndInsertFolder(nameRootFolderA, projectTest,
                2, null);
        rootFolderA = gpWSClient.getFolderDetail(idRootFolderA);

        idRootFolderB = this.createAndInsertFolder(nameRootFolderB, projectTest,
                1, null);
        rootFolderB = gpWSClient.getFolderDetail(idRootFolderB);

        // Set the list of keywords (for raster layer)
        layerInfoKeywords = new ArrayList<>();
        layerInfoKeywords.add("keyword_test_rs");
    }

    @Override
    protected void setUpOrganization() throws IllegalParameterFault {
        organizationTest = new GPOrganization(organizationNameRSTest);
        organizationTest.setId(gpWSClient.insertOrganization(organizationTest));
    }

    @Override
    public void tearDown() {
        try {
            if (idProjectTest != -1) {
                Assert.assertEquals(Boolean.TRUE, gpWSClient.deleteProject(
                        idProjectTest));
            }
        } catch (Exception ex) {
            logger.error(
                    "\n@@@@@@@@@@@@@@@@@ERROR@@@@@@@@@@@@@@@@@@@@@@@@ " + ex);
        }

        super.tearDown();
    }

}
