package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo.WFSUndoRedoEditSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSUndoRedoMediatorHandler extends EventHandler {

    GwtEvent.Type<WFSUndoRedoMediatorHandler> TYPE = new GwtEvent.Type<WFSUndoRedoMediatorHandler>();

    /**
     * @param undoRedo
     * @param <UndoRedo>
     */
    <UndoRedo extends WFSUndoRedoEditSupport> void enableUndoRedoSupport(UndoRedo undoRedo);

    void disableUndoRedoSupport();

    abstract class WFSUndoRedoEvent extends GwtEvent<WFSUndoRedoMediatorHandler> {

        @Override
        public Type<WFSUndoRedoMediatorHandler> getAssociatedType() {
            return TYPE;
        }
    }
}
