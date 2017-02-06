package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;

import static com.google.gwt.user.client.ui.AbstractImagePrototype.create;
import static org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.WFSResourcesButtonBar.INSTANCE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UndoButton extends WFSUndoRedoButton {

    public UndoButton(WFSUndoRedoMediator theWFSUndoRedoManagerSupport) {
        super(theWFSUndoRedoManagerSupport, "WFS_UNDO_BUTTON");
        super.setIcon(create(INSTANCE.wfsUndo()));
        super.setToolTip("Undo");
        super.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                wfsUndoRedoManagerSupport.undo();
            }
        });
    }
}
