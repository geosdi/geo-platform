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
package org.geosdi.geoplatform.gui.configuration.map.client.layer;

import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitorClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GPFolderClientInfo implements Serializable, Comparable<IGPFolderElements>, IGPFolderElements, IGPParentElement {

    /**
     *
     */
    private static final long serialVersionUID = 8769227953810545929L;
    private String label;
    private int zIndex;
    private List<IGPFolderElements> folderElements;
    private IGPParentElement parent;
    private int numberOfDescendants;
    private boolean checked;
    private Long id;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfDescendants() {
        return numberOfDescendants;
    }

    public void setNumberOfDescendants(int numberOfChildrens) {
        this.numberOfDescendants = numberOfChildrens;
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
     * @return the elements
     */
    public List<IGPFolderElements> getFolderElements() {
        return folderElements == null ? new ArrayList<IGPFolderElements>() : folderElements;
    }

    /**
     * @param elements the elements to set
     */
    public void setFolderElements(List<IGPFolderElements> folderElements) {
        Collections.sort(folderElements);
        this.folderElements = folderElements;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GPFolderClientInfo{" + "label=" + label + ", zIndex=" + zIndex + ", folderElements=" + folderElements + ", parent=" + parent + ", numberOfDescendants=" + numberOfDescendants + ", checked=" + checked + ", id=" + id + ", expanded=" + expanded + '}';
    }

    @Override
    public int compareTo(IGPFolderElements o) {
        return o.getzIndex() - getzIndex();
    }

    /**
     * @return the zIndex
     */
    @Override
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public void accept(IVisitorClient visitor) {
        visitor.visitFolder(this);
    }

    /**
     * @return the parent
     */
    public IGPParentElement getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(IGPParentElement parent) {
        this.parent = parent;
    }
}
