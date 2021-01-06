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
package org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher.executor;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.InsertFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.InsertFeatureResponse;
import org.geosdi.geoplatform.gui.client.model.binder.IFeatureIdBinder;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.CloseAttributesWindowEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature.FeatureAttributesWindowBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSEdit;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSInsertFeatureExecutor extends WFSDispatcherExecutor implements
        IWFSInsertFeatureExecutor {

    @Inject
    private InsertFeatureRequest insertFeatureRequest;
    @Inject
    private CloseAttributesWindowEvent closeAttributesWindow;
    @Inject
    private IFeatureIdBinder fidBinder;

    @Inject
    public WFSInsertFeatureExecutor() {
        super(new FeatureStatusBarEvent("WFS Insert Feature....",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_LOADING),
                new FeatureStatusBarEvent("WFS Insert Feature Done.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_OK),
                new FeatureStatusBarEvent("WFS Insert Feature not done.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_NOT_OK),
                new FeatureStatusBarEvent("WFS Insert Feature Failed.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_ERROR));
    }

    @Override
    public void insertFeature(final WFSEdit editorSource,
                              List<AttributeDTO> featureAttributes) {
        progressBar.show();

        setUpRequest(editorSource, featureAttributes);

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<InsertFeatureResponse>() {

                    private static final long serialVersionUID = 6578231272584552338L;

                    {
                        super.setCommandRequest(insertFeatureRequest);
                    }

                    @Override
                    public void onCommandSuccess(InsertFeatureResponse response) {
                        manageInsertFeatureCommandSuccess(response.getResult(),
                                editorSource);
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        manageInsertFeatureCommandFailure(exception,
                                editorSource);
                    }

                });
    }

    final void setUpRequest(WFSEdit editorSource, List<AttributeDTO> attributes) {
        bus.fireEvent(loadingEvent);

        insertFeatureRequest.setServerUrl(
                layerSchemaBinder.getLayerSchemaDTO().getScope());
        insertFeatureRequest.setTypeName(
                layerSchemaBinder.getLayerSchemaDTO().getTypeName());
        insertFeatureRequest.setTargetNamespace(
                layerSchemaBinder.getLayerSchemaDTO().getTargetNamespace());
        attributes.add(this.buildGeometryAttribute(editorSource));
        insertFeatureRequest.setAttributes(attributes);

    }

    final AttributeDTO buildGeometryAttribute(WFSEdit editorSource) {
        GeometryAttributeDTO geom = new GeometryAttributeDTO();
        geom.setName(layerSchemaBinder.getGeometryName());
        geom.setValue(editorSource.buildWktGeometry());
        geom.setSrid(900913);

        return geom;
    }

    final void manageInsertFeatureCommandSuccess(Boolean result,
                                                 WFSEdit editorSource) {
        progressBar.hide();
        if (result) {
            FeatureAttributesWindowBuilder.fireAttributesWindowEvent(
                    closeAttributesWindow);
            super.fireEvents();
            fidBinder.setFID(null);
        } else {
            editorSource.resetWFSEditing();
            bus.fireEvent(statusNotOk);
        }
    }

    final void manageInsertFeatureCommandFailure(Throwable exception,
                                                 WFSEdit editorSource) {
        String errorMessage = "Error on WFS Insert Feature request";
        GeoPlatformMessage.errorMessage(
                "WFS Service Error",
                errorMessage + " - " + exception.getMessage());

        progressBar.hide();
        editorSource.resetWFSEditing();

        bus.fireEvent(failedEvent);
    }

}
