package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button;

import com.extjs.gxt.ui.client.widget.button.Button;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WFSUndoRedoButton extends Button {

    protected final WFSUndoRedoMediator wfsUndoRedoManagerSupport;

    /**
     * Creates a new button.
     */
    protected WFSUndoRedoButton(WFSUndoRedoMediator theWFSUndoRedoManagerSupport, String theID) {
        super.setId(theID);
        this.wfsUndoRedoManagerSupport = theWFSUndoRedoManagerSupport.bind(this);
        super.setEnabled(Boolean.FALSE);
    }
}
