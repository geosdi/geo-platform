/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.converter;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoRaster;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoVector;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.response.RasterPropertiesDTO;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.springframework.stereotype.Component;

/**
 * Simple Class to convert Web-Services beans model in DTO beans Client.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "dtoMementoConverter")
public class DTOMementoConverter {

    public List<GPLayer> convertMementoLayers(List<AbstractMementoLayer> addedLayers) {
        List<GPLayer> layersList = Lists.<GPLayer>newArrayList();
        GPFolder folder = new GPFolder();
        for (AbstractMementoLayer memento : addedLayers) {
            GPLayer layer = null;
            if (memento instanceof MementoRaster) {
//                MementoRaster mementoRaster = (MementoRaster) memento;
                layer = new GPRasterLayer();
                layer.setLayerType(GPLayerType.WMS);
                ((GPRasterLayer) layer).setStyles(((MementoRaster) memento).getStyles());
                // layer.setLayerInfo();???
            } else if (memento instanceof MementoVector) {
//                MementoVector mementoVector = (MementoVector) memento;
                layer = new GPVectorLayer();
                // layer.setGeometry()???
            }
            this.convertToLayerElementFromMementoLayer(layer, memento);
            folder.setId(memento.getIdFolderParent());
            layer.setFolder(folder);
            layersList.add(layer);
        }
        return layersList;
    }

    private void convertToLayerElementFromMementoLayer(GPLayer layer, AbstractMementoLayer memento) {
        layer.setAbstractText(memento.getAbstractText());
        layer.setBbox(new GPBBox(memento.getLowerLeftX(), memento.getLowerLeftY(),
                memento.getUpperRightX(), memento.getUpperRightY()));
        layer.setSrs(memento.getSrs());
        layer.setUrlServer(memento.getDataSource());
        layer.setId(memento.getIdBaseElement());
        layer.setName(memento.getLayerName());
        layer.setTitle(memento.getTitle());
        layer.setPosition(memento.getzIndex());
//        layer.setChecked(memento.isChecked());
//        layer.setAlias(memento.getAlias());
//        layer.setShared(mementoLayer.isShared());
    }

    public GPWebServiceMapData convertDescendantMap(Map descendantMap) {
        GPWebServiceMapData wsMap = new GPWebServiceMapData();
        wsMap.setDescendantsMap(descendantMap);
        System.out.println("Size descendants map: " + descendantMap.size());
        return wsMap;
    }

    public RasterPropertiesDTO convertMementoProperties(MementoLayerOriginalProperties memento) {
        RasterPropertiesDTO dto = new RasterPropertiesDTO();
        dto.setAlias(memento.getName());
        dto.setChecked(memento.isChecked());
        dto.setId(memento.getIdBaseElement());
        dto.setOpacity(memento.getOpacity());
        dto.setCqlFilter(memento.getCqlFilter());
        dto.setTimeFilter(memento.getTimeFilter());
        dto.setMaxScale(memento.getMaxScale());
        dto.setMinScale(memento.getMinScale());
        dto.setSingleTileRequest(memento.isSingleTileRequest());
        List<String> styleList = Lists.<String>newArrayList();
        if (memento.getStyleList() != null) {
            for (GPStyleStringBeanModel beanModel : memento.getStyleList()) {
                styleList.add(beanModel.getStyleString());
            }
        }
        dto.setStyleList(styleList);
        return dto;
    }
}
