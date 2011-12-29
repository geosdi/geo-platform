/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform.gui.server.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.exception.GPCatalogException;
import org.geosdi.geoplatform.gui.client.model.FinderBean.MetadataOnlineResourceBean;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.server.service.IFinderService;

import java.util.ArrayList;


import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FinderBean;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO.GPCatalogMetadataOnlineResource;
import org.geosdi.geoplatform.services.GPCatalogFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
@Service("finderService")
public class FinderService implements IFinderService {

    @Autowired
    private GPCatalogFinderService gpCatalogFinderService;

    @Override
    public ArrayList<FinderBean> searchPublicMetadata(String searchString)
            throws GPCatalogException {
        List<GPCatalogMetadataDTO> gpCatalogMetadataDTOList = this.gpCatalogFinderService.searchPublicMetadata(searchString);

        ArrayList<FinderBean> foundedBeans = new ArrayList<FinderBean>();

        for (GPCatalogMetadataDTO gpCatalogMetadataDTO : gpCatalogMetadataDTOList) {
            FinderBean finderBean = convertGPCatalogMetadataDTOToFinderBean(gpCatalogMetadataDTO);
            foundedBeans.add(finderBean);
        }
        return foundedBeans;
    }

    @Override
    public ArrayList<FinderBean> searchPrivateMetadata(String username, String password, String searchString) throws GPCatalogException {
        List<GPCatalogMetadataDTO> gpCatalogMetadataDTOList = this.gpCatalogFinderService.searchPrivateMetadataWithCredentials(username, password, searchString);

        ArrayList<FinderBean> foundedBeans = new ArrayList<FinderBean>();

        for (GPCatalogMetadataDTO gpCatalogMetadataDTO : gpCatalogMetadataDTOList) {
            FinderBean finderBean = convertGPCatalogMetadataDTOToFinderBean(gpCatalogMetadataDTO);
            foundedBeans.add(finderBean);
        }
        return foundedBeans;
    }

    private FinderBean convertGPCatalogMetadataDTOToFinderBean(GPCatalogMetadataDTO gpCatalogMetadataDTO) {
        FinderBean finderBean = new FinderBean();
        finderBean.setAbstractValue(gpCatalogMetadataDTO.getAbstractValue());
        finderBean.setKeywordValues(gpCatalogMetadataDTO.getKeywordValues());
        finderBean.setTitle(gpCatalogMetadataDTO.getTitle());
        finderBean.setUuid(gpCatalogMetadataDTO.getUuid());
        finderBean.setBbox(convertGPBBoxToBboxClientInfo(gpCatalogMetadataDTO.getBbox()));
        finderBean.setMetadataOnlineResourceBeanList(convertLists(finderBean, gpCatalogMetadataDTO.getGpCatalogMetadataOnlineResourceList()));

        return finderBean;
    }

    private BboxClientInfo convertGPBBoxToBboxClientInfo(GPBBox bbox) {
        BboxClientInfo bboxClientInfo = new BboxClientInfo();
        bboxClientInfo.setLowerLeftX(bbox.getMinX());
        bboxClientInfo.setLowerLeftY(bbox.getMinY());
        bboxClientInfo.setUpperRightX(bbox.getMaxX());
        bboxClientInfo.setUpperRightY(bbox.getMaxY());

        return bboxClientInfo;
    }

    private List<MetadataOnlineResourceBean> convertLists(FinderBean finderBean, List<GPCatalogMetadataOnlineResource> gpCatalogMetadataOnlineResourceList) {
        List<MetadataOnlineResourceBean> metadataOnlineResourceBeanList = new ArrayList<MetadataOnlineResourceBean>();

        for (GPCatalogMetadataOnlineResource gpCatalogMetadataOnlineResource : gpCatalogMetadataOnlineResourceList) {
            MetadataOnlineResourceBean metadataOnlineResourceBean = finderBean.makeInstance();
            metadataOnlineResourceBean.setDescription(gpCatalogMetadataOnlineResource.getDescription());
            metadataOnlineResourceBean.setName(gpCatalogMetadataOnlineResource.getName());
            metadataOnlineResourceBean.setProtocol(gpCatalogMetadataOnlineResource.getProtocol());
            metadataOnlineResourceBean.setURL(gpCatalogMetadataOnlineResource.getURL());

            metadataOnlineResourceBeanList.add(metadataOnlineResourceBean);
        }

        return metadataOnlineResourceBeanList;
    }
}
