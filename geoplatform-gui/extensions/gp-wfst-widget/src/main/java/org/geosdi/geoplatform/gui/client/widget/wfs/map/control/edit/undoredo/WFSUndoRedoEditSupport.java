package org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.undoredo;

import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSEdit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSUndoRedoEditSupport extends WFSEdit {

    void redo();

    void undo();
}
