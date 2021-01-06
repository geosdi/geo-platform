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
package org.geosdi.geoplatform.gui.client.model;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProjectKey;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPRootTreeNode extends AbstractRootTreeNode {

    private static final long serialVersionUID = 1765450539495169525L;

    public GPRootTreeNode() {
    }

    public GPRootTreeNode(TreePanel<GPBeanTreeModel> theTree) {
        super.setLabel(LayerModuleConstants.INSTANCE.GPRootTreeNode_labelText());
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        set(GPClientProjectKey.PROJECT_ID.toString(), id);
    }

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return super.get(GPClientProjectKey.PROJECT_ID.toString());
    }

    /**
     * @param projectName
     */
    @Override
    public void setProjectName(String projectName) {
        set(GPClientProjectKey.PROJECT_NAME.toString(), projectName);
    }

    /**
     * @return
     */
    @Override
    public String getProjectName() {
        return super.get(GPClientProjectKey.PROJECT_NAME.toString());
    }

    /**
     * @param projectElements
     */
    @Override
    public void setProjectElements(int projectElements) {
        set(GPClientProjectKey.PROJECT_ELEMENTS.toString(), projectElements);
    }

    /**
     * @return
     */
    @Override
    public int getProjectElements() {
        return super.get(GPClientProjectKey.PROJECT_ELEMENTS.toString(), 0);
    }

    /**
     * @param projectVersion
     */
    @Override
    public void setProjectVersion(int projectVersion) {
        set(GPClientProjectKey.PROJECT_VERSION.toString(), projectVersion);
    }

    /**
     * @return
     */
    @Override
    public int getProjectVersion() {
        return super.get(GPClientProjectKey.PROJECT_VERSION.toString(), 0);
    }

    /**
     * @param projectMessage
     */
    @Override
    public void setProjectMessage(String projectMessage) {
        set(GPClientProjectKey.DEFAULT_PROJECT_KEY_MESSAGE.toString(), projectMessage);
    }

    /**
     * @return
     */
    @Override
    public String getProjectMessage() {
        return super.get(GPClientProjectKey.DEFAULT_PROJECT_KEY_MESSAGE.toString());
    }

    /**
     * @param creationDate
     */
    @Override
    public void setCreationDate(String creationDate) {
        set(GPClientProjectKey.CREATION_DATE.toString(), creationDate);
    }

    /**
     * @return
     */
    @Override
    public String getCreationDate() {
        return super.get(GPClientProjectKey.CREATION_DATE.toString());
    }

    /**
     * @param projectsShared
     */
    @Override
    public void setProjectShared(boolean projectsShared) {
        set(GPClientProjectKey.PROJECT_SHARED.toString(), projectsShared);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isProjectShared() {
        return super.get(GPClientProjectKey.PROJECT_SHARED.toString());
    }

    /**
     * @param theInternalPublic
     */
    @Override
    public void setInternalPublic(boolean theInternalPublic) {
        set(GPClientProjectKey.PROJECT_INTERNAL_PUBLIC.toString(), theInternalPublic);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isInternalPublic() {
        return super.get(GPClientProjectKey.PROJECT_INTERNAL_PUBLIC.toString());
    }

    /**
     * @param theExternalPublic
     */
    @Override
    public void setExternalPublic(boolean theExternalPublic) {
        set(GPClientProjectKey.PROJECT_EXTERNAL_PUBLIC.toString(), theExternalPublic);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isExternalPublic() {
        return super.get(GPClientProjectKey.PROJECT_EXTERNAL_PUBLIC.toString());
    }

    /**
     * @param children
     */
    public void addElements(List<FolderTreeNode> children) {
        VisitorPosition visitor = new VisitorPosition();
        this.accept(visitor);
    }

    /**
     * @param clientFolders
     */
    @Override
    public void modelConverter(List<GPFolderClientInfo> clientFolders) {
        for (GPFolderClientInfo folder : clientFolders) {
            FolderTreeNode folderTreeNode = new FolderTreeNode(folder, FALSE);
            folderTreeNode.setParent(this);
            super.add(folderTreeNode);
        }
        VisitorPosition visitor = new VisitorPosition();
        this.accept(visitor);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel#getIcon()
     */
    @Override
    public AbstractImagePrototype getIcon() {
        return AbstractImagePrototype.create(LayerResources.ICONS.geoPlatform());
    }

    @Override
    public TreeStatusEnum getTreeStatus() {
        return TreeStatusEnum.ROOT_SELECTED;
    }

    @Override
    public String toString() {
        return "GPRootTreeNode{} " + super.toString();
    }
}