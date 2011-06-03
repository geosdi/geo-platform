/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.model.memento;

import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.observable.Observable;
import org.geosdi.geoplatform.gui.observable.Observer;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoFolder extends AbstractMementoSave implements Observer {

    private static final long serialVersionUID = -2323528396954124089L;
    private transient FolderTreeNode refFolder;
    private String folderName;
    private int zIndex;
    private long idElement;
    private long idParent;
    private transient FolderTreeNode refParent;
    private boolean isChecked;
    private int numberOfDescendants;

    public MementoFolder() {
    }

    public MementoFolder(ISave saveAction) {
        super(saveAction);
    }

    public void convertParentWS() {
        if (this.getRefParent() != null) {
            this.idParent = this.refParent.getId();
        }
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getNumberOfDescendants() {
        return numberOfDescendants;
    }

    public void setNumberOfDescendants(int numberOfDescendants) {
        this.numberOfDescendants = numberOfDescendants;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public FolderTreeNode getRefFolder() {
        return refFolder;
    }

    public void setRefFolder(FolderTreeNode refFolder) {
        this.refFolder = refFolder;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("MementoFolder received observable notify");
        this.idElement = ((Long) arg);
    }

    /**
     * @return the idElement
     */
    public long getIdElement() {
        return idElement;
    }

    /**
     * @param idElement the idElement to set
     */
    public void setIdElement(long idElement) {
        this.idElement = idElement;
    }

    /**
     * @return the refParent
     */
    public FolderTreeNode getRefParent() {
        return refParent;
    }

    /**
     * @param refParent the refParent to set
     */
    public void setRefParent(FolderTreeNode refParent) {
        this.refParent = refParent;
    }

    /**
     * @return the idParent
     */
    public long getIdParent() {
        return idParent;
    }

    /**
     * @param idParent the idParent to set
     */
    public void setIdParent(long idParent) {
        this.idParent = idParent;
    }
}
