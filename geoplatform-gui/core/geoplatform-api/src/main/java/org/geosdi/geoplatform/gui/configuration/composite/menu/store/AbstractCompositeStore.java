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
package org.geosdi.geoplatform.gui.configuration.composite.menu.store;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.GPMenuGenericTool;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractCompositeStore implements GPMenuCompositeStore {

    private static final long serialVersionUID = -7607092275910880131L;
    //
    protected Map<? extends StoreCompositeKey, List<? extends GPMenuGenericTool>> clientTools;

    public AbstractCompositeStore() {
    }

    @Override
    public void setClientTools(
            Map<? extends StoreCompositeKey, List<? extends GPMenuGenericTool>> theClientTools) {
        this.clientTools = theClientTools;
    }

    @Override
    public Map<? extends StoreCompositeKey, List<? extends GPMenuGenericTool>> getClientTools() {
        return this.clientTools;
    }

    @Override
    public void init() {
        Preconditions.checkNotNull(clientTools, "The Client Tools must not "
                + "be null.");

        new CompositeStoreSorter() {

            @Override
            public void sort() {
                for (Map.Entry<? extends StoreCompositeKey, List<? extends GPMenuGenericTool>> baseEntry : clientTools.entrySet()) {
                    List<? extends GPMenuGenericTool> list = baseEntry.getValue();

                    Collections.sort(list);
                }
            }

        }.sort();
    }

    @Override
    public String toString() {
        return "AbstractCompositeStore{ " + "clientTools = " + clientTools + '}';
    }

}
