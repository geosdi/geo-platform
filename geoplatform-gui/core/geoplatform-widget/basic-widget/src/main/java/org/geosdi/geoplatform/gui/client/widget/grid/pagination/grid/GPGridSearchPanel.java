package org.geosdi.geoplatform.gui.client.widget.grid.pagination.grid;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.GeoPlatformSearchPanel;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGridSearchPanel<T extends GeoPlatformBeanModel>
        extends GeoPlatformSearchPanel<Grid<T>, T> {

    public GPGridSearchPanel(boolean lazy) {
        super(lazy);
    }

    public GPGridSearchPanel(boolean lazy, int pageSize) {
        super(lazy, pageSize);
    }

    @Override
    public void initWidget() {
        ColumnModel cm = prepareColumnModel();
        super.widget = new Grid<T>(store, cm);
        super.widget.setBorders(true);

        super.widget.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        super.widget.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (widget.getSelectionModel().getSelection().size() > 0) {
                    selectButton.enable();
                } else {
                    selectButton.disable();
                }
            }
        });

        super.widget.addListener(Events.CellDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                executeSelect();
            }
        });

        setGridProperties();
    }

    public abstract void setGridProperties();

    public abstract ColumnModel prepareColumnModel();

}
