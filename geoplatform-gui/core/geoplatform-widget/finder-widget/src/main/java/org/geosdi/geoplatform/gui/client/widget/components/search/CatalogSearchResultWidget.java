/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.components.search;

import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.SummaryRecordsContainer;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CatalogSearchResultWidget extends LayoutContainer {

    private SummaryRecordsContainer summaryRecordsContainer;
    //
    private TreePanel<GPBeanTreeModel> tree; // TODO Inject via GIN

    @Inject
    public CatalogSearchResultWidget(SummaryRecordsContainer theSummaryRecordsContainer) {
         summaryRecordsContainer = theSummaryRecordsContainer;
        
        super.setStyleAttribute("padding", "10px");
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        Label resultLabel = new Label("Search Result");
        resultLabel.setStyleAttribute("color", "#4169E1");
        resultLabel.setStyleAttribute("font",
                "normal 14px tahoma, arial, helvetica, sans-serif");
        resultLabel.setStyleAttribute("padding-bottom", "5px");

        add(resultLabel);

        add(summaryRecordsContainer);

        if (tree != null) {  // Widgets for add layers to the tree
            Label operationLabel = new Label("Operations with selected");
            operationLabel.setStyleAttribute("color", "#4169E1");
            operationLabel.setStyleAttribute("font",
                    "normal 14px tahoma, arial, helvetica, sans-serif");
            resultLabel.setStyleAttribute("padding-top", "10px");
            resultLabel.setStyleAttribute("padding-bottom", "10px");

            add(operationLabel);

            Button addLayerButton = new Button("Add To Layer Tree");
            add(addLayerButton);
        }
    }
}
