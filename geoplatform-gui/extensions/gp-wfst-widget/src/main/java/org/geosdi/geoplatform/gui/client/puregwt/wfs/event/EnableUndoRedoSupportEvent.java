package org.geosdi.geoplatform.gui.client.puregwt.wfs.event;

import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediatorHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo.WFSUndoRedoEditSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EnableUndoRedoSupportEvent extends WFSUndoRedoMediatorHandler.WFSUndoRedoEvent {

    private final WFSUndoRedoEditSupport wfsUndoRedoEditSupport;

    public EnableUndoRedoSupportEvent(WFSUndoRedoEditSupport theWFSUndoRedoEditSupport) {
        this.wfsUndoRedoEditSupport = theWFSUndoRedoEditSupport;
    }

    @Override
    protected void dispatch(WFSUndoRedoMediatorHandler wfsUndoRedoMediatorHandler) {
        wfsUndoRedoMediatorHandler.enableUndoRedoSupport(this.wfsUndoRedoEditSupport);
    }
}
