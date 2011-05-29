//<editor-fold defaultstate="collapsed" desc="License">
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
//</editor-fold>
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPStyle;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.StyleDTO;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.geosdi.geoplatform.responce.collection.StyleList;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
class LayerSericeImpl {

    final private static Logger LOGGER = Logger.getLogger(LayerSericeImpl.class);
    private GPLayerDAO layerDao;
    private GPStyleDAO styleDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">    
    /**
     * @param layerDao
     *            the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }

    /**
     * @param styleDao
     *            the styleDao to set
     */
    public void setStyleDao(GPStyleDAO styleDao) {
        this.styleDao = styleDao;
    }
    //</editor-fold>

    StyleList getLayerStyles(long layerId) {
        Search searchCriteria = new Search(GPStyle.class);
        searchCriteria.addSortAsc("name");

        Filter layer = Filter.equal("layer.id", layerId);
        searchCriteria.addFilter(layer);

        List<GPStyle> foundStyle = styleDao.search(searchCriteria);
        StyleList list = convertToStyleList(foundStyle);
        return list;
    }
    
    public long insertLayer(GPLayer layer) {
        layerDao.persist(layer);
        return layer.getId();
    }

    public long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPRasterLayer orig = (GPRasterLayer)layerDao.find(layer.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Layer not found", layer.getId());
        }
        
//        orig.setParent(layer.getParent());
        orig.setAbstractText(layer.getAbstractText());
        orig.setBbox(layer.getBbox());
        orig.setFolder(layer.getFolder());
        orig.setLayerInfo(layer.getLayerInfo());
        orig.setLayerType(layer.getLayerType());
        orig.setName(layer.getName());
        orig.setPosition(layer.getPosition());
        orig.setShared(layer.isShared());
        orig.setSrs(layer.getSrs());
        orig.setTitle(layer.getTitle());
        orig.setUrlServer(layer.getUrlServer());

        layerDao.merge(orig);
        return orig.getId();
    }

    public long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPVectorLayer orig = (GPVectorLayer)layerDao.find(layer.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Layer not found", layer.getId());
        }
        
//        orig.setParent(layer.getParent());
        orig.setAbstractText(layer.getAbstractText());
        orig.setBbox(layer.getBbox());
        orig.setFolder(layer.getFolder());
        orig.setGeometry(layer.getGeometry());
        orig.setLayerType(layer.getLayerType());
        orig.setName(layer.getName());
        orig.setPosition(layer.getPosition());
        orig.setShared(layer.isShared());
        orig.setSrs(layer.getSrs());
        orig.setTitle(layer.getTitle());
        orig.setUrlServer(layer.getUrlServer());

        layerDao.merge(orig);
        return orig.getId();
    }

    public boolean deleteLayer(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPLayer layer = layerDao.find(request.getId());
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", request.getId());
        }

        // data on ancillary tables should be deleted by cascading
        return layerDao.remove(layer);
    }

    public GPRasterLayer getRasterLayer(long layerId) throws ResourceNotFoundFault {
        GPRasterLayer layer = (GPRasterLayer)layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer;
    }

    public GPVectorLayer getVectorLayer(long layerId) throws ResourceNotFoundFault {
        GPVectorLayer layer = (GPVectorLayer)layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer;
    }

    public ShortLayerDTO getShortLayer(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return new ShortLayerDTO(layer);
    }

    public LayerList getLayers() {
        List<GPLayer> found = layerDao.findAll();
        return convertToLayerList(found);
    }

    public GPBBox getBBox(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getBbox();
    }

    public GPLayerInfo getLayerInfo(long layerId) throws ResourceNotFoundFault {
        GPRasterLayer layer = (GPRasterLayer)layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getLayerInfo();
    }

//    public Point getGeometry(long layerId) throws ResourceNotFoundFault {
//        GPVectorLayer layer = (GPVectorLayer)layerDao.find(layerId);
//        if (layer == null) {
//            throw new ResourceNotFoundFault("Layer not found", layerId);
//        }
//
//        return layer.getGeometry();
//    }

    public GPLayerType getLayerType(long layerId) throws ResourceNotFoundFault {
        GPLayer layer = layerDao.find(layerId);
        if (layer == null) {
            throw new ResourceNotFoundFault("Layer not found", layerId);
        }

        return layer.getLayerType();
    }

    // TODO Move to StyleList?
    // as constructor: StyleList list = new StyleList(List<GPStyle>);    
    private StyleList convertToStyleList(List<GPStyle> foundStyle) {
        List<StyleDTO> stylesDTO = new ArrayList<StyleDTO>(foundStyle.size());
        for (GPStyle style : foundStyle) {
            stylesDTO.add(new StyleDTO(style));
        }

        StyleList styles = new StyleList();
        styles.setList(stylesDTO);
        return styles;
    }
    
    // TODO Move to LayerList?
    // as constructor: LayerList list = new LayerList(List<GPLayer>);
    private LayerList convertToLayerList(List<GPLayer> layerList) {
        List<ShortLayerDTO> layersDTO = new ArrayList<ShortLayerDTO>(layerList.size());
        for (GPLayer layer : layerList) {
            layersDTO.add(new ShortLayerDTO(layer));
        }

        LayerList layers = new LayerList();
        layers.setList(layersDTO);
        return layers;
    }    
}
