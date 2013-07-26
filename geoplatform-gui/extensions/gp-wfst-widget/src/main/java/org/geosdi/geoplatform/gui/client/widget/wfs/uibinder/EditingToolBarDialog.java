/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.wfs.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 *
 */
public class EditingToolBarDialog extends Composite {

    interface Binder extends UiBinder<Widget, EditingToolBarDialog> {
    }
    private static final Binder binder = GWT.create(Binder.class);
    @UiField
    ToggleButton info;
    @UiField
    ToggleButton edit;
    @UiField
    ToggleButton reshape;
    @UiField
    ToggleButton rotate;
    @UiField
    ToggleButton drag;
    @UiField
    ToggleButton resize;

    public EditingToolBarDialog() {
        
        TextResource wfstCss = ResourceEditingToolBar.INSTANCE.wfstCss();
        StyleInjector.inject(wfstCss.getText());
        
        initWidget(binder.createAndBindUi(this));
        
        //setting up the icons
        Image infoImage = new Image(ResourceEditingToolBar.INSTANCE.info());
        info.getElement().appendChild(infoImage.getElement());
        
        Image editImage = new Image(ResourceEditingToolBar.INSTANCE.edit());
        edit.getElement().appendChild(editImage.getElement());
        
        Image reshapeImage = new Image(ResourceEditingToolBar.INSTANCE.reshape());
        reshape.getElement().appendChild(reshapeImage.getElement());
        
        Image rotateImage = new Image(ResourceEditingToolBar.INSTANCE.rotate());
        rotate.getElement().appendChild(rotateImage.getElement());
        
        Image dragImage = new Image(ResourceEditingToolBar.INSTANCE.drag());
        drag.getElement().appendChild(dragImage.getElement());
        
        Image resizeImage = new Image(ResourceEditingToolBar.INSTANCE.resize());
        resize.getElement().appendChild(resizeImage.getElement());

        info.setTitle("Info");
        edit.setTitle("Edit");
        reshape.setTitle("Reshape");
        rotate.setTitle("Rotate");
        drag.setTitle("Drag");
        resize.setTitle("Resize");

    }

    @UiHandler("info")
    void handleInfoClick(ClickEvent e) {
        //Window.alert("Info");        
    }
    
    @UiHandler("edit")
    void handleEditClick(ClickEvent e) {
        //Window.alert("Edit");        
    }
    
    @UiHandler("reshape")
    void handleReshapeClick(ClickEvent e) {
        //Window.alert("Reshape");        
    }
    
    @UiHandler("rotate")
    void handleRotateClick(ClickEvent e) {
        //Window.alert("Reshape");        
    }
    
    @UiHandler("drag")
    void handleDragClick(ClickEvent e) {
        //Window.alert("Drag");        
    }
    
    @UiHandler("resize")
    void handleResizeClick(ClickEvent e) {
        //Window.alert("Resize");        
    }
    
}
