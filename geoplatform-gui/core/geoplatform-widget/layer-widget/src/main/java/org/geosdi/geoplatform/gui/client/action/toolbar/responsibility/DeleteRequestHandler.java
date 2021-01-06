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
package org.geosdi.geoplatform.gui.client.action.toolbar.responsibility;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveRemove;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDeleteElement;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class DeleteRequestHandler implements ISave<MementoSaveRemove> {

    protected TreePanel tree;
    private VisitorDeleteElement deleteVisitor = new VisitorDeleteElement();
    private VisitorDisplayHide visitorDispalyHide;
    private DeleteRequestHandler successor;

    public DeleteRequestHandler(TreePanel theTree) {
        this.tree = theTree;
        this.visitorDispalyHide = new VisitorDisplayHide(tree);
    }

    public void setSuperiorRequestHandler(DeleteRequestHandler theSuccessor) {
        this.successor = theSuccessor;
    }

    public void deleteRequest(GPBeanTreeModel model) {
        forwardDeleteRequest(model);
    }

    protected void forwardDeleteRequest(GPBeanTreeModel model) {
        if (successor != null) {
            successor.deleteRequest(model);
        }
    }

    protected void delete(GPBeanTreeModel element) {
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        this.visitorDispalyHide.removeVisibleLayers(element);
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        IMemento<ISave> precedingMemento;
        precedingMemento = mementoSave.peekLast();
        boolean isAllowedNewMemento = true;
        mementoSave.cleanOperationsRefToDeletedElement(element);
        MementoSaveRemove mementoSaveRemove = null;
        GPClientProject clientProject = (GPClientProject) Registry.get(UserSessionEnum.CURRENT_PROJECT_ON_TREE.name());
        if (!clientProject.isProjectShared() && precedingMemento != null && precedingMemento instanceof MementoSaveAddedFolder
                && ((MementoSaveAddedFolder) precedingMemento).getAddedFolder().getRefBaseElement().equals(element)) {
            mementoSave.remove(precedingMemento);
            isAllowedNewMemento = false;
        } else {
            mementoSaveRemove = new MementoSaveRemove(this);
            mementoSaveRemove.setRefBaseElement(element);
        }
        this.deleteVisitor.deleteElement(element, parent, parent.indexOf(element));
        this.tree.getStore().remove(element);
        if (isAllowedNewMemento) {
            mementoSaveRemove.setDescendantMap(this.deleteVisitor.getFolderDescendantMap());
            mementoSave.add(mementoSaveRemove);
        }
        displayMessage();
    }

    public abstract void processRequest(GPBeanTreeModel model);

    public abstract void displayMessage();
}
