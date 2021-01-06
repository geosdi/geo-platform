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
package org.geosdi.geoplatform.response.collection;

import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.response.FolderDTO;
import org.geosdi.geoplatform.response.ShortLayerDTO;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TreeFolderElementsStore implements ITreeFolderElementsStore {

    private static final long serialVersionUID = -8985769926116698122L;
    //
    @XmlElementWrapper(name = "folderElements")
    @XmlElement(name = "folderElement")
    private TreeFolderElements folderElements = new TreeFolderElements();

    public TreeFolderElementsStore() {
    }

    /**
     * @return the folderElements
     */
    @Override
    public TreeFolderElements getFolderElements() {
        return folderElements;
    }

    /**
     * @param folderElements the folderElements to set
     */
    public void setFolderElements(TreeFolderElements folderElements) {
        this.folderElements = folderElements;
    }

    @Override
    public void addFolderCollection(List<FolderDTO> folders) {
        this.folderElements.addFolderCollection(folders);
    }

    @Override
    public void addLayerCollection(Collection<GPLayer> layerList) {
        this.folderElements.addLayerCollection(layerList);
    }

    @Override
    public void addLayerCollection(List<ShortLayerDTO> layers) {
        this.folderElements.addLayerCollection(layers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "folderElements = "
                + folderElements + '}';
    }
}
