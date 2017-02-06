package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator;

import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button.WFSUndoRedoButton;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSUndoRedoMediator extends WFSUndoRedoMediatorHandler {

    /**
     * @param button
     * @param <UndoRedo>
     * @return {@link WFSUndoRedoMediator}
     */
    <UndoRedo extends WFSUndoRedoButton> WFSUndoRedoMediator bind(UndoRedo button);

    void undo();

    void redo();
}
