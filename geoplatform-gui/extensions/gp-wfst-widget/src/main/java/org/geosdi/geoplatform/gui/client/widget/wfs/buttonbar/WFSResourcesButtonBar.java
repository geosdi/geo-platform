package org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSResourcesButtonBar extends ClientBundle {

    WFSResourcesButtonBar INSTANCE = GWT.create(WFSResourcesButtonBar.class);

    @Source("images/wfs-redo.png")
    ImageResource wfsRedo();

    @Source("images/wfs-undo.png")
    ImageResource wfsUndo();
}
