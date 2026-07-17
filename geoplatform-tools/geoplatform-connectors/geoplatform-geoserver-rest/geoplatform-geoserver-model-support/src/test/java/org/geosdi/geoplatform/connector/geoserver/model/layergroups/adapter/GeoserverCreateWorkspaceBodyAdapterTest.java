/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.model.layergroups.adapter;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GeoserverCreateWorkspaceBody;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline tests for {@link GeoserverCreateWorkspaceBodyAdapter}, the JAXB adapter mapping the public
 * {@link GPGeoserverCreateWorkspaceBody} to the package-private {@code AdaptedGeoserverCreateWorkspaceBody}
 * wire type. Lives in the same package to reach the package-private adapted type.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverCreateWorkspaceBodyAdapterTest {

    private static GeoserverCreateWorkspaceBodyAdapter adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new GeoserverCreateWorkspaceBodyAdapter();
    }

    @Test
    public void a_marshalCopiesWorkspaceNameTest() throws Exception {
        AdaptedGeoserverCreateWorkspaceBody adapted = adapter.marshal(new GeoserverCreateWorkspaceBody("topp"));
        assertEquals("topp", adapted.getWorkspaceName());
    }

    @Test
    public void b_unmarshalBuildsWorkspaceBodyTest() throws Exception {
        AdaptedGeoserverCreateWorkspaceBody adapted = new AdaptedGeoserverCreateWorkspaceBody();
        adapted.setWorkspaceName("sf");
        GPGeoserverCreateWorkspaceBody body = adapter.unmarshal(adapted);
        assertEquals("sf", body.getWorkspaceName());
    }

    @Test
    public void c_roundTripPreservesWorkspaceNameTest() throws Exception {
        GPGeoserverCreateWorkspaceBody body = adapter.unmarshal(adapter.marshal(new GeoserverCreateWorkspaceBody("cite")));
        assertEquals("cite", body.getWorkspaceName());
    }

    @Test
    public void d_unmarshalBlankNameThrowsTest() throws Exception {
        // GeoserverCreateWorkspaceBody rejects a null/blank name, so unmarshal must propagate it.
        AdaptedGeoserverCreateWorkspaceBody adapted = new AdaptedGeoserverCreateWorkspaceBody();
        adapted.setWorkspaceName("   ");
        try {
            adapter.unmarshal(adapted);
            fail("Expected unmarshal of a blank workspace name to be rejected");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }
}