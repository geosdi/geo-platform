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
package org.geosdi.geoplatform.core.delegate;

import org.geosdi.geoplatform.core.delegate.api.account.AccountDelegate;
import org.geosdi.geoplatform.core.delegate.api.acl.AclDelegate;
import org.geosdi.geoplatform.core.delegate.api.folder.FolderDelegate;
import org.geosdi.geoplatform.core.delegate.api.layer.LayerDelegate;
import org.geosdi.geoplatform.core.delegate.api.message.MessageDelegate;
import org.geosdi.geoplatform.core.delegate.api.organization.OrganizationDelegate;
import org.geosdi.geoplatform.core.delegate.api.project.ProjectDelegate;
import org.geosdi.geoplatform.core.delegate.api.server.ServerDelegate;
import org.geosdi.geoplatform.core.delegate.api.viewport.ViewportDelegate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class CoreDelegateSpringTest {

    @Autowired
    private AccountDelegate gpAccountDelegate;
    @Autowired
    private AclDelegate gpAclDelegate;
    @Autowired
    private FolderDelegate gpFolderDelegate;
    @Autowired
    private LayerDelegate gpLayerDelegate;
    @Autowired
    private MessageDelegate gpMessageDelegate;
    @Autowired
    private OrganizationDelegate gpOrganizationDelegate;
    @Autowired
    private ProjectDelegate gpProjectDelegate;
    @Autowired
    private ServerDelegate gpServerDelegate;
    @Autowired
    private ViewportDelegate gpViewportDelegate;

    @Test
    public void coreDelegateComponentsTest() {
        Assert.assertNotNull(gpAccountDelegate);
        Assert.assertNotNull(gpAclDelegate);
        Assert.assertNotNull(gpFolderDelegate);
        Assert.assertNotNull(gpLayerDelegate);
        Assert.assertNotNull(gpMessageDelegate);
        Assert.assertNotNull(gpOrganizationDelegate);
        Assert.assertNotNull(gpProjectDelegate);
        Assert.assertNotNull(gpServerDelegate);
        Assert.assertNotNull(gpViewportDelegate);
    }

}
