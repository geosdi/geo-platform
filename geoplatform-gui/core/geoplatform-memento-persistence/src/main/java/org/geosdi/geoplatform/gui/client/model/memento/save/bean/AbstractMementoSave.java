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
package org.geosdi.geoplatform.gui.client.model.memento.save.bean;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.observable.Observable;
import org.geosdi.geoplatform.gui.observable.Observer;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class AbstractMementoSave<T extends GPBeanTreeModel> implements IMemento<ISave>, Serializable, Observer {

    private static final long serialVersionUID = 3466894382316001150L;
    //
    private transient ISave saveAction;
    private transient T refBaseElement;
    private Long idBaseElement;
    private transient Map<AbstractFolderTreeNode, Integer> descendantMap = Maps.<AbstractFolderTreeNode, Integer>newHashMap();
    private Map<Long, Integer> wsDescendantMap = Maps.<Long, Integer>newHashMap();

    public AbstractMementoSave() {
    }

    public AbstractMementoSave(ISave saveAction) {
        this.saveAction = saveAction;
    }

    public void convertMementoToWs() {
//        System.out.println("Converting abstract memento to WS");
        AbstractFolderTreeNode folderTmp;
        for (Iterator<AbstractFolderTreeNode> it = this.descendantMap.keySet().iterator(); it.hasNext();) {
            folderTmp = it.next();
            wsDescendantMap.put(folderTmp.getId(), this.descendantMap.get(folderTmp));
        }
        if (this.refBaseElement != null) {
            this.idBaseElement = this.refBaseElement.getId();
        }
    }

    @Override
    public ISave getAction() {
        return this.saveAction;
    }

    @Override
    public void setAction(ISave action) {
        this.saveAction = action;
    }

    public void addFolderDescendantChanged(AbstractFolderTreeNode folder, int zIndex) {
        this.descendantMap.put(folder, zIndex);
    }

    public Map<AbstractFolderTreeNode, Integer> getDescendantMap() {
        return descendantMap;
    }

    public void setDescendantMap(Map<AbstractFolderTreeNode, Integer> descendantMap) {
        this.descendantMap = descendantMap;
    }

    /**
     * @return the wsDescendantMap
     */
    public Map<Long, Integer> getWsDescendantMap() {
        return wsDescendantMap;
    }

    /**
     * @param wsDescendantMap the wsDescendantMap to set
     */
    public void setWsDescendantMap(Map<Long, Integer> wsDescendantMap) {
        this.wsDescendantMap = wsDescendantMap;
    }

    /**
     * @return the idBaseElement
     */
    public Long getIdBaseElement() {
        return idBaseElement;
    }

    /**
     * @param idBaseElement the idBaseElement to set
     */
    public void setIdBaseElement(Long idBaseElement) {
        this.idBaseElement = idBaseElement;
    }

    /**
     * @return the refBaseElement
     */
    public T getRefBaseElement() {
        return refBaseElement;
    }

    /**
     * @param refBaseElement the refBaseElement to set
     */
    public void setRefBaseElement(T refBaseElement) {
        this.refBaseElement = refBaseElement;
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("Memento received observable notify");
        this.idBaseElement = ((Long) arg);
    }

    @Override
    public String toString() {
        return "AbstractMementoSave{"
                + "saveAction=" + saveAction
                + ", refBaseElement=" + refBaseElement
                + ", idBaseElement=" + idBaseElement
                + ", descendantMap=" + descendantMap
                + ", wsDescendantMap=" + wsDescendantMap + '}';
    }
}