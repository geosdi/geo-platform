package org.geosdi.geoplatform.gui.client.config.provider.button;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button.UndoButton;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSUndoButtonProvider implements Provider<Button> {

    private final WFSUndoRedoMediator wfsUndoRedoManagerSupport;

    @Inject
    public WFSUndoButtonProvider(WFSUndoRedoMediator theWFSUndoRedoManagerSupport) {
        this.wfsUndoRedoManagerSupport = theWFSUndoRedoManagerSupport;
    }

    @Override
    public Button get() {
        return new UndoButton(this.wfsUndoRedoManagerSupport);
    }
}
