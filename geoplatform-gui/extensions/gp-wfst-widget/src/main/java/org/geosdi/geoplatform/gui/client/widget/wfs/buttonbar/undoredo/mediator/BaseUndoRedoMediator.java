package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button.WFSUndoRedoButton;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo.WFSUndoRedoEditSupport;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseUndoRedoMediator implements WFSUndoRedoMediator {

    private static final Logger logger = Logger.getLogger("BaseUndoRedoMediator");
    //
    private final Map<String, WFSUndoRedoButton> undoRedoButtonRepo = Maps.newHashMap();
    private WFSUndoRedoEditSupport wfsUndoRedoEditSupport;

    /**
     * @param button
     * @return {@link WFSUndoRedoMediator}
     */
    @Override
    public <UndoRedo extends WFSUndoRedoButton> WFSUndoRedoMediator bind(UndoRedo button) {
        this.undoRedoButtonRepo.put(button.getItemId(), button);
        return this;
    }

    @Override
    public void undo() {
        if (this.wfsUndoRedoEditSupport != null)
            this.wfsUndoRedoEditSupport.undo();
    }

    @Override
    public void redo() {
        if (this.wfsUndoRedoEditSupport != null)
            this.wfsUndoRedoEditSupport.redo();
    }

    /**
     * @param undoRedo
     */
    @Override
    public <UndoRedo extends WFSUndoRedoEditSupport> void enableUndoRedoSupport(UndoRedo undoRedo) {
        logger.log(Level.FINE, "#################################EXECUTE enableUndoRedoSupport");
        this.wfsUndoRedoEditSupport = undoRedo;
        for (Map.Entry<String, WFSUndoRedoButton> entry : this.undoRedoButtonRepo.entrySet()) {
            entry.getValue().setEnabled(Boolean.TRUE);
        }
    }

    @Override
    public void disableUndoRedoSupport() {
        for (Map.Entry<String, WFSUndoRedoButton> entry : this.undoRedoButtonRepo.entrySet()) {
            entry.getValue().setEnabled(Boolean.FALSE);
        }
    }
}
