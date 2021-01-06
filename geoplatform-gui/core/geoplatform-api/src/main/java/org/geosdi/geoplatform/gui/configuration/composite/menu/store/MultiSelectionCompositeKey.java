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

import org.geosdi.geoplatform.gui.configuration.composite.menu.GPTreeMenuType;
import org.geosdi.geoplatform.gui.configuration.composite.menu.MultiSelectionElementType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiSelectionCompositeKey extends StoreCompositeKey {

    private static final long serialVersionUID = -1258878031936700822L;
    //
    private MultiSelectionElementType multiSelectionType;

    /**
     * <p>Only for Serialization Purpose</p>
     */
    public MultiSelectionCompositeKey() {
        this(null);
    }

    /**
     * @param theMultiSelectionType
     */
    public MultiSelectionCompositeKey(MultiSelectionElementType theMultiSelectionType) {
        super(GPTreeMenuType.ADVANCED);
        this.multiSelectionType = theMultiSelectionType;
    }

    /**
     * @return the multiSelectionType
     */
    public MultiSelectionElementType getMultiSelectionType() {
        return multiSelectionType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.multiSelectionType != null
                ? this.multiSelectionType.hashCode() : 0);
        hash = 59 * hash + (this.treeMenuType != null
                ? this.treeMenuType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MultiSelectionCompositeKey other = (MultiSelectionCompositeKey) obj;
        if (this.multiSelectionType != other.multiSelectionType) {
            return false;
        }
        if (this.treeMenuType != other.treeMenuType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MultiSelectionCompositeKey{ " + super.toString()
                + "multiSelectionType = " + multiSelectionType + '}';
    }

}
