package org.geosdi.geoplatform.gui.client.puregwt.wfs.event;

import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediatorHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DisableUndoRedoSupportEvent extends WFSUndoRedoMediatorHandler.WFSUndoRedoEvent {

    @Override
    protected void dispatch(WFSUndoRedoMediatorHandler wfsUndoRedoMediatorHandler) {
        wfsUndoRedoMediatorHandler.disableUndoRedoSupport();
    }
}
