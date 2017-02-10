package org.geosdi.geoplatform.gui.client.widget.grid.pagination;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPagingToolBar extends PagingToolBar {

    public GPPagingToolBar(int pageSize) {
        super(pageSize);
    }

    @Override
    public void enable() {
        super.enable();
        this.first.enable();
        this.prev.enable();
        this.next.enable();
        this.last.enable();
        this.refresh.enable();
    }

    /**
     * Clears the current toolbar text.
     */
    @Override
    public void clear() {
        super.clear();
        this.first.disable();
        this.prev.disable();
        this.next.disable();
        this.last.disable();
        this.refresh.disable();
        this.refresh.setIcon(getImages().getRefresh());
    }
}
