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
package org.geosdi.geoplatform.gui.server.command.memento.dnd;

import com.google.common.base.Preconditions;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.command.memento.dnd.SaveDragAndDropRequest;
import org.geosdi.geoplatform.gui.client.command.memento.dnd.SaveDragAndDropResponse;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.converter.DTOMementoConverter;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.layer.WSDDLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.response.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy
@Component(value = "command.memento.dnd.SaveDragAndDropCommand")
public class SaveDragAndDropCommand implements
        GPCommand<SaveDragAndDropRequest, SaveDragAndDropResponse> {

    private static final Logger logger = LoggerFactory.getLogger(
            SaveDragAndDropCommand.class);
    //
    @Autowired
    private DTOMementoConverter dtoMementoConverter;
    @Autowired
    private SessionUtility sessionUtility;
    private GeoPlatformService geoPlatformServiceClient;

    @Override
    public SaveDragAndDropResponse execute(SaveDragAndDropRequest request,
            HttpServletRequest httpServletRequest) {

        logger.debug("##################### Executing {} Command", this.
                getClass().getSimpleName());

        MementoSaveDragDrop memento = request.getMemento();

        Preconditions.checkNotNull(memento, "The MementoSaveDragDrop must not "
                + "be null.");

        try {
            this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }

        GPWebServiceMapData map = this.dtoMementoConverter.convertDescendantMap(
                memento.getWsDescendantMap());

        boolean result = false;

        switch (request.getElementType()) {
            case COMPOSITE:
                try {
                    result = this.geoPlatformServiceClient.saveDragAndDropFolderAndTreeModifications(
                            new WSDDFolderAndTreeModifications(
                                    memento.getIdBaseElement(),
                                    memento.getIdNewParent(),
                                    memento.getNewZIndex(), map));
                } catch (ResourceNotFoundFault ex) {
                    logger.error("Failed to save Folder drag&drop : " + ex);
                    throw new GeoPlatformException(ex);
                }
                break;
            case LEAF:
                try {
                    result = this.geoPlatformServiceClient.saveDragAndDropLayerAndTreeModifications(
                            new WSDDLayerAndTreeModificationsRequest(
                                    memento.getIdBaseElement(),
                                    memento.getIdNewParent(),
                                    memento.getNewZIndex(), map));
                } catch (ResourceNotFoundFault ex) {
                    logger.error("Failed to save Layer drag&drop : " + ex);
                    throw new GeoPlatformException(ex);
                } catch (IllegalParameterFault ilg) {
                    logger.error("Error on SaveDragAndDropCommand: " + ilg);
                    throw new GeoPlatformException("Parameter incorrect "
                            + "on saveDragAndDropLayerAndTreeModifications");
                }
                break;
        }

        return new SaveDragAndDropResponse(result);
    }

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }

}
