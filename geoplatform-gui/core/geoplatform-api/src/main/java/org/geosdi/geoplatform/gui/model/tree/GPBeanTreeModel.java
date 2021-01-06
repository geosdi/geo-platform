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
package org.geosdi.geoplatform.gui.model.tree;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import org.geosdi.geoplatform.gui.model.UUIDGenerator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public abstract class GPBeanTreeModel extends BaseTreeModel implements IGPNode {

    private static final long serialVersionUID = 6717337287606067389L;
    //
    private String UUID = UUIDGenerator.uuid();
    private Long id;
    private String label;
    private int zIndex;
    private boolean checked = false;

    protected GPBeanTreeModel() {
    }

    /**
     * Constructor for a layer (raster and vector set a different label)
     *
     * @param id
     * @param zIndex
     * @param checked
     */
    protected GPBeanTreeModel(Long id, int zIndex, boolean checked) {
        this.id = id;
        this.zIndex = zIndex;
        this.checked = checked;
    }

    /**
     * Constructor for a folder
     *
     * @param id
     * @param label
     * @param zIndex
     * @param checked
     */
    protected GPBeanTreeModel(Long id, String label, int zIndex, boolean checked) {
        this.id = id;
        this.label = label;
        this.zIndex = zIndex;
        this.checked = checked;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the zIndex
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @param UUID the UUID to set
     */
    protected void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "id = " + id
                + ", label = " + label
                + ", zIndex = " + zIndex
                + ", checked = " + checked;
    }
}
