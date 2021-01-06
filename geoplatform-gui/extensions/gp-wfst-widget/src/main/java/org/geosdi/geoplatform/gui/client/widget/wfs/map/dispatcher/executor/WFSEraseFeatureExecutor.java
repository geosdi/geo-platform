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

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.EraseFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.EraseFeatureResponse;
import org.geosdi.geoplatform.gui.client.model.binder.IFeatureIdBinder;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.RemoveFeatureDetailEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import javax.inject.Inject;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSEraseFeatureExecutor extends WFSDispatcherExecutor implements
        IWFSEraseFeatureExecutor {

    @Inject
    private EraseFeatureRequest eraseFeatureRequest;
    @Inject
    private IFeatureIdBinder fidBinder;
    @Inject
    private Vector vector;
    private RemoveFeatureDetailEvent removeFeatureDetailEvent;

    @Inject
    public WFSEraseFeatureExecutor() {
        super(new FeatureStatusBarEvent("WFS Erase Feature....",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_LOADING),
                new FeatureStatusBarEvent("WFS Erase Feature Done.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_OK),
                new FeatureStatusBarEvent("WFS Erase Feature not done.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_NOT_OK),
                new FeatureStatusBarEvent("WFS Erase Feature Failed.",
                        FeatureStatusBar.FeatureStatusBarType.STATUS_ERROR));
    }

    @Override
    public void eraseFeature(final VectorFeature feature) {
        if (checkFIDState(feature)) {
            GeoPlatformMessage.alertMessage("WFS-T Service",
                    "The Selected Feature FID is null. Reactivate Get Feature "
                            + "Control and then the Feature can be deleted.");
            return;
        }

        GeoPlatformMessage.confirmMessage("WFS-T Erase Feature", "Are you sure "
                        + "you want to delete the feature?",
                new Listener<MessageBoxEvent>() {

                    @Override
                    public void handleEvent(MessageBoxEvent be) {
                        if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                            doIt(feature);
                        }
                    }

                });

    }

    final void doIt(final VectorFeature feature) {
        progressBar.show();
        setUpRequest(feature);

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<EraseFeatureResponse>() {

                    private static final long serialVersionUID = 5479389376112532915L;

                    {
                        super.setCommandRequest(eraseFeatureRequest);
                    }

                    @Override
                    public void onCommandSuccess(EraseFeatureResponse response) {
                        manageEraseFeatureCommandSuccess(response.getResult(),
                                feature);
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        manageEraseFeatureCommandFailure(exception);
                    }

                });
    }

    final boolean checkFIDState(VectorFeature feature) {
        return (fidBinder.getFID() == null) && (feature.getFID() == null);
    }

    final void setUpRequest(VectorFeature feature) {
        bus.fireEvent(loadingEvent);

        eraseFeatureRequest.setFid((fidBinder.getFID() != null)
                ? fidBinder.getFID() : feature.getFID());
        eraseFeatureRequest.setServerUrl(
                layerSchemaBinder.getLayerSchemaDTO().getScope());
        eraseFeatureRequest.setTypeName(
                layerSchemaBinder.getLayerSchemaDTO().getTypeName());
    }

    final void manageEraseFeatureCommandFailure(Throwable exception) {
        String errorMessage = "Error on WFS Erase Feature request";
        GeoPlatformMessage.errorMessage(
                "WFS Service Error",
                errorMessage + " - " + exception.getMessage());

        progressBar.hide();

        bus.fireEvent(failedEvent);
    }

    final void manageEraseFeatureCommandSuccess(Boolean result,
                                                VectorFeature feature) {
        progressBar.hide();
        if (result) {
            vector.removeFeature(feature);
            super.fireEvents();
            bus.fireEvent(new RemoveFeatureDetailEvent(feature.getFID()));
        } else {
            bus.fireEvent(statusNotOk);
        }
    }

}
