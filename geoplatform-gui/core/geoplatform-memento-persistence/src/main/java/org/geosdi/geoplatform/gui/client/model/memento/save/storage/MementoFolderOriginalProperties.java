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
package org.geosdi.geoplatform.gui.client.model.memento.save.storage;

import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoFolderOriginalProperties extends AbstractMementoOriginalProperties<AbstractFolderTreeNode> {

    private static final long serialVersionUID = 4900306329179765474L;
    private boolean expanded;

    public MementoFolderOriginalProperties() {
    }

    public MementoFolderOriginalProperties(ISave saveAction) {
        super(saveAction);
    }

    @Override
    public void convertMementoToWs() {
        super.convertMementoToWs();
        super.setName(super.getRefBaseElement().getLabel());
        super.setChecked(super.getRefBaseElement().isChecked());
        this.setExpanded(super.getRefBaseElement().isExpanded());
    }

    @Override
    public boolean isChanged() {
        boolean condition = Boolean.FALSE;
        if (!this.getName().equals(super.getRefBaseElement().getLabel())
                || super.isChecked() != super.getRefBaseElement().isChecked()
                || this.isExpanded() != super.getRefBaseElement().isExpanded()) {
            condition = Boolean.TRUE;
        }
        return condition;
    }

    @Override
    public void copyOriginalProperties(GPBeanTreeModel bean) {
        if (bean instanceof AbstractFolderTreeNode) {
            AbstractFolderTreeNode folder = (AbstractFolderTreeNode) bean;
            super.setName(folder.getLabel());
            super.setChecked(folder.isChecked());
            this.setExpanded(folder.isExpanded());
            super.setRefBaseElement(folder);
        } else {
            throw new IllegalArgumentException("The method copyOriginalProperties "
                    + "in MementoFolderOriginalProperties class accepts only AbstractFolderTreeNode instances");
        }
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
