/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.command;

import org.geosdi.geoplatform.gui.client.model.AbstractRecord;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.command.api.GPCommandRequest;
import org.geosdi.geoplatform.gui.command.api.GPCommandResponse;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.responce.AbstractRecordDTO;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseCSWCommand<Request extends GPCommandRequest, Response extends GPCommandResponse> implements GPCommand<Request, Response>, InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformCSWService geoPlatformCSWClient;

    /**
     * @param geoPlatformCSWClient the geoPlatformCSWService to set
     */
    @Autowired
    public void setGeoPlatformCSWClient(@Qualifier("geoPlatformCSWClient") GeoPlatformCSWService geoPlatformCSWClient) {
        this.geoPlatformCSWClient = geoPlatformCSWClient;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(this.geoPlatformCSWClient != null, "The Parameter geoPlatformCSWClient must not be null.");
    }

    /**
     * @param serverDTO
     * @return {@link GPCSWServerBeanModel}
     */
    protected static GPCSWServerBeanModel convertServerDTO(ServerCSWDTO serverDTO) {
        GPCSWServerBeanModel server = new GPCSWServerBeanModel();
        server.setId(serverDTO.getId());
        server.setUrlServer(serverDTO.getServerUrl());
        server.setTitle(serverDTO.getTitle());
        server.setAlias(serverDTO.getAlias());
        return server;
    }

    /**
     * @param record
     * @param recordDTO
     * @param <R>
     * @return {@link R}
     */
    protected static <R extends AbstractRecord> R convertRecordDTO(R record, AbstractRecordDTO recordDTO) {
        record.setIdentifier(recordDTO.getIdentifier());
        record.setIdCatalog(recordDTO.getIdCatalog());
        record.setTitle(recordDTO.getTitle());
        record.setCatalogURL(recordDTO.getCatalogURL());
        record.setType(recordDTO.getType());
        record.setAbstractText(recordDTO.getAbstractText());
        record.setSubjects(recordDTO.getSubjects());
        return record;
    }

    /**
     * @param summaryRecordDTO
     * @return {@link SummaryRecord}
     */
    protected static SummaryRecord convertSummaryRecordDTO(SummaryRecordDTO summaryRecordDTO) {
        return convertRecordDTO(new SummaryRecord(), summaryRecordDTO);
    }

    /**
     * @param fullRecordDTO
     * @return {@link FullRecord}
     */
    protected static FullRecord convertFullRecordDTO(FullRecordDTO fullRecordDTO) {
        FullRecord fullRecord = convertRecordDTO(new FullRecord(), fullRecordDTO);
        fullRecord.setBBox(convertBBoxDTO(fullRecordDTO.getBBox()));
        fullRecord.setCrs(fullRecordDTO.getCrs());
        fullRecord.setUriMap(fullRecordDTO.getUriMap());
        return fullRecord;
    }

    /**
     * @param bBox
     * @return {@link BBoxClientInfo}
     */
    protected static BBoxClientInfo convertBBoxDTO(BBox bBox) {
        if (bBox == null) {
            return null;
        }
        BBoxClientInfo bBoxClient = new BBoxClientInfo();
        bBoxClient.setLowerLeftX(bBox.getMinX());
        bBoxClient.setLowerLeftY(bBox.getMinY());
        bBoxClient.setUpperRightX(bBox.getMaxX());
        bBoxClient.setUpperRightY(bBox.getMaxY());
        return bBoxClient;
    }
}