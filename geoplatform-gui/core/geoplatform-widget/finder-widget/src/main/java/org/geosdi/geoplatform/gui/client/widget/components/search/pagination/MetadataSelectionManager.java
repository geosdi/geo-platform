/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.widget.components.search.pagination;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.puregwt.event.EnableAddOnTreeButtonEvent;
import org.geosdi.geoplatform.gui.client.puregwt.event.EnableWMSGetCapabilitiesButtonEvent;
import org.geosdi.geoplatform.gui.client.puregwt.event.wms.CatalogWMSEventHandler;
import org.geosdi.geoplatform.gui.client.widget.components.search.tree.CatalogTreeLayerWidgetSupport;
import org.geosdi.geoplatform.gui.client.widget.components.search.tooltip.CatalogRecordsToolTip;
import org.geosdi.geoplatform.gui.client.widget.components.search.tooltip.GPCatalogRecordsToolTip;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * <p>
 * MetadataSelectionManager class Manage All Operations for Metadata Selection:
 * <ul> <li> Operation to activate {@link CatalogTreeLayerWidgetSupport} button
 * to add Metadata on GPLayerTreeWidget </li> <li> Operation to manage Metadata
 * which can't be added as WMS Resource on Tree </li> <li> Operation to clear
 * {@link List<FullRecord>} recordsExcluded </li> </ul> </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MetadataSelectionManager implements CatalogMetadataSelectionManager {

    private final GPEventBus bus;
    private final GPCatalogRecordsToolTip recordsToolTip;
    private final EnableWMSGetCapabilitiesButtonEvent loadWMSGetCapabilitiesEvent = new EnableWMSGetCapabilitiesButtonEvent();
    private final EnableAddOnTreeButtonEvent event = new EnableAddOnTreeButtonEvent();
    private final List<FullRecord> recordsExcluded = Lists.<FullRecord>newArrayList();

    public MetadataSelectionManager(GPEventBus theBus,
            GPCatalogRecordsToolTip theRecordsToolTip) {
        this.bus = theBus;
        this.recordsToolTip = theRecordsToolTip;
    }

    @Override
    public void clearRecordsExcludedList() {
        this.recordsExcluded.clear();
    }

    @Override
    public void addRecordExcluded(FullRecord record) {
        if (!this.recordsExcluded.contains(record)) {
            this.recordsExcluded.add(record);
        }
    }

    @Override
    public List<FullRecord> getRecordsExcluded() {
        return Lists.<FullRecord>newArrayList(recordsExcluded);
    }

    @Override
    public boolean isRecordsExcludedSet() {
        return !(this.recordsExcluded.isEmpty());
    }

    @Override
    public void bindContainer(final RecordsContainer container) {
        container.addListener(Events.Render, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                recordsToolTip.initTarget(container.getWidget());
            }

        });
    }

    @Override
    public void fireCatalogRecordToolTip(FullRecord record) {
        this.recordsToolTip.bindRecord(record);
        this.fireCatalogTreeLayerHandler(record.isForWMSGetMapRequest());
        this.enableLoadWMSGetCapabilities(record.isForWMSGetCapabilities());
    }

    @Override
    public void manageSelectionChanged(SelectionChangedEvent<FullRecord> se) {
        clearRecordsExcludedList();
        this.fireCatalogTreeLayerHandler((!(se.getSelection().isEmpty())
                && (se.getSelectedItem().isForWMSGetMapRequest())));
        this.fireLoadWMSGetCapabilities((!(se.getSelection().isEmpty())
                && (se.getSelectedItem().isForWMSGetCapabilities())),
                se.getSelectedItem());
        this.fireCatalogRecordsToolTip(se.getSelection().isEmpty());
    }

    protected void enableLoadWMSGetCapabilities(boolean enable) {
        this.loadWMSGetCapabilitiesEvent.setEnable(enable);
        bus.fireEvent(this.loadWMSGetCapabilitiesEvent);
    }

    /**
     * <p>
     * Activate Button to load WMS GetCapabilities.</p>
     *
     * @param enable
     * @param fullRecord
     */
    protected void fireLoadWMSGetCapabilities(boolean enable,
            FullRecord fullRecord) {
        this.enableLoadWMSGetCapabilities(enable);
        if (enable) {
            bus.fireEvent(new CatalogWMSEventHandler(fullRecord));
        }
    }

    /**
     * <p>
     * Activate Button to add Metadata on GPTreeLayerWidget.</p>
     *
     * @param enable
     */
    protected void fireCatalogTreeLayerHandler(boolean enable) {
        this.event.setEnable(enable);
        bus.fireEvent(event);
    }

    /**
     * Activate {@link CatalogRecordsToolTip} ToolTip
     *
     * @param enable
     */
    protected void fireCatalogRecordsToolTip(boolean enable) {
        if (enable) {
            this.recordsToolTip.onHideToolTip();
        } else {
            this.recordsToolTip.bindRecords(this.getRecordsExcluded());
        }
    }

}
