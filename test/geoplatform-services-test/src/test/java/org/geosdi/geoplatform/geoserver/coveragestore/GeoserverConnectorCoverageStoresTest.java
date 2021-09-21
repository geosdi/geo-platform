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
package org.geosdi.geoplatform.geoserver.coveragestore;

import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorCoverageStoresTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorCoverageStoresTest.class);

    @Test
    public void a_exsistCoverageStores() throws Exception {
        Assert.assertTrue("####################",  restReader.existsCoveragestore("burg", "test", TRUE) ==
                this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("burg").withStore("test").exsist());
    }

    //@Ignore(value = "Store store_vito may be not present")
    @Test
    public void c_deleteCoverageStore() throws Exception {
        Assert.assertTrue("####################", this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("sf").withStore("store_vito").exsist());
        this.geoserverConnectorStore.deleteCoverageStoreRequest().withCoverageStore("store_vito").withWorkspace("sf").withRecurse(TRUE).getResponse();
        Assert.assertFalse("####################", this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("sf").withStoreName("store_vito").withQuietNotFound(TRUE).exsist());
    }

}