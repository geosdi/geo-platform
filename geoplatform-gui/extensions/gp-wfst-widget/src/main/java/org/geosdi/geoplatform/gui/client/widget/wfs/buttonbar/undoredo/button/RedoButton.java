package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;

import java.util.logging.Logger;

import static com.google.gwt.user.client.ui.AbstractImagePrototype.create;
import static org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.WFSResourcesButtonBar.INSTANCE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class RedoButton extends WFSUndoRedoButton {

    private static final Logger logger = Logger.getLogger("RedoButton");

    public RedoButton(WFSUndoRedoMediator theWFSUndoRedoManagerSupport) {
        super(theWFSUndoRedoManagerSupport, "WFS_REDO_BUTTON");
        super.setIcon(create(INSTANCE.wfsRedo()));
        super.setToolTip("Redo");
        super.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                wfsUndoRedoManagerSupport.redo();
            }
        });
    }
}
