package org.geosdi.geoplatform.gui.client.puregwt.wfs.handler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.DisableUndoRedoSupportEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediatorHandler.WFSUndoRedoEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;

import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediatorHandler.TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WFSUndoRedoMediatorHandlerSupport {

    private static final Logger logger = Logger.getLogger("WFSUndoRedoMediatorHandlerSupport");
    //
    private static final GPEventBus bus = new GPEventBusImpl();
    public static final DisableUndoRedoSupportEvent DISABLE_UNDO_REDO_SUPPORT_EVENT = new DisableUndoRedoSupportEvent();

    @Inject
    public WFSUndoRedoMediatorHandlerSupport(WFSUndoRedoMediator wfsUndoRedoMediator) {
        logger.info("###################################WFSUndoRedoMediatorHandlerSupport " + wfsUndoRedoMediator);
        addUndoRedoMediatorHandler(wfsUndoRedoMediator);
    }

    protected HandlerRegistration addUndoRedoMediatorHandler(WFSUndoRedoMediator wfsUndoRedoMediator) {
        return bus.addHandler(TYPE, wfsUndoRedoMediator);
    }

    public static void fireUndoRedoEvent(WFSUndoRedoEvent event) {
        logger.info("###########################fireUndoRedoEvent");
        bus.fireEvent(event);
    }
}
