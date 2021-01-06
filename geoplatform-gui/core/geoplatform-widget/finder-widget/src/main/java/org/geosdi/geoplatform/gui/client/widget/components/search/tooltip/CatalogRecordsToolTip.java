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
package org.geosdi.geoplatform.gui.client.widget.components.search.tooltip;

import com.extjs.gxt.ui.client.core.Template;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.tips.ToolTip;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import java.util.Arrays;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.model.FullRecord;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogRecordsToolTip extends ToolTip
        implements GPCatalogRecordsToolTip {

    public CatalogRecordsToolTip() {
        super();
        this.configureToolTipConfig();
    }

    @Override
    public void initTarget(Component target) {
        if (listener == null) {
            listener = new Listener<ComponentEvent>() {

                @Override
                public void handleEvent(ComponentEvent be) {
                    EventType type = be.getType();
                    if (type == Events.Hide || type == Events.Clear
                            || type == Events.Detach) {
                        hide();
                    }
                }
            };
        }
        super.initTarget(target);
    }

    @Override
    public void bindRecords(List<FullRecord> records) {
        if (super.isVisible()) {
            super.hide();
        }
        if (!records.isEmpty()) {
            this.toolTipConfig.setTemplate(new Template(this.buildToolTipMessage(
                    records)));
            super.show();
        }
    }

    @Override
    public void bindRecord(FullRecord record) {
        if (super.isVisible()) {
            super.hide();
        }

        this.toolTipConfig.setTemplate(new Template(this.buildToolTipMessage(
                Arrays.asList(record))));
        super.show();
    }

    @Override
    public void onHideToolTip() {
        super.hide();
    }

    /**
     * Create an html String from Metadata List which can't be added on 
     * GPLayerTreeWidget
     * 
     * @param recods
     * @return {@link String} html String
     */
    protected String buildToolTipMessage(List<FullRecord> recods) {
        StringBuilder builder = new StringBuilder();
        builder.append("<br/> ");
        builder.append("<p> ");
        builder.append(CatalogFinderConstants.INSTANCE.CatalogRecordsToolTip_buildTooltipHeaderText());
        builder.append(": </p>");
        builder.append("<ul>");
        for (FullRecord record : recods) {
            builder.append("<li>");
            builder.append(record.getTitle());
            builder.append("</li>");
        }
        builder.append("</ul>");
        builder.append("<br/>");
        builder.append(CatalogFinderConstants.INSTANCE.CatalogRecordsToolTip_metadataNotAddedText());
        builder.append("<br/>");
        builder.append(CatalogFinderConstants.INSTANCE.CatalogRecordsToolTip_metadataNotGetMapProtocolText());

        return builder.toString();
    }

    private void configureToolTipConfig() {
        ToolTipConfig tipConfig = new ToolTipConfig();
        tipConfig.setTitle(CatalogFinderConstants.INSTANCE.CatalogRecordsToolTip_tooltipTitleText());
        tipConfig.setAnchor("letf");
        tipConfig.setCloseable(true);
        tipConfig.setMaxWidth(400);
        tipConfig.setAutoHide(false);
        super.update(tipConfig);
    }
}
