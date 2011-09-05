/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.util.UtilityLayerModule;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.form.WmsUrlStatus.EnumWmsUrlStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.server.gwt.LayerRemoteImpl;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class AddRasterFromUrlWidget extends GPTreeFormWidget<RasterTreeNode>
        implements ISave<MementoSaveAddedLayers> {

    private TreePanel<GPBeanTreeModel> tree;
    private TextField<String> urlText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
    //
    private Map<String, String> fieldMap = new HashMap<String, String>(); // TODO <GetMap, String> --> booolean GetMap.valid(String field)
    private String suggestion = "";

    /**
     *@param theTree 
     * 
     */
    public AddRasterFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(true);
        this.tree = theTree;
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(this, theTree);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeading("WMS GetMap URL");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.urlText = new TextField<String>();
        this.urlText.setFieldLabel("URL");

        this.AddListenerToUrlText();

        this.fieldSet.add(this.urlText);

        this.formPanel.add(this.fieldSet);

        this.saveStatus = new WmsUrlStatus();
        this.saveStatus.setAutoWidth(true);

        this.formPanel.getButtonBar().add(this.saveStatus);

        this.formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.save = new Button("Add", LayerResources.ICONS.addRasterLayer(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        save.setEnabled(false);

        this.formPanel.addButton(save);

        this.cancel = new Button("Cancel", BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                    }
                });

        this.formPanel.addButton(cancel);

        setFocusWidget(this.urlText);
    }

    private void AddListenerToUrlText() {
        this.urlText.addListener(Events.OnChange, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
                    verifyUrl();
                } else {
                    save.disable();
                    setStatus(EnumWmsUrlStatus.STATUS_NO_CHECKED.getValue(),
                            EnumWmsUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
//                            suggestion);
                }
            }
        });

        this.urlText.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (urlText.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (checkUrl()) {
                        verifyUrl();
                    } else {
                        setStatus(EnumWmsUrlStatus.STATUS_NO_CHECKED.getValue(),
                                EnumWmsUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
//                            suggestion);
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && checkUrl()) {
                    verifyUrlForEnterKey();
                }
            }
        });
    }

    @Override
    public void initSize() {
        setHeading("Add WMS from GetMap direck URL");
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(280, 120);
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy("Adding WMS from URL");
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
//        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";    

        this.retrieveDataFromQueryString(); // Set the fieldMap

        List<GPBeanTreeModel> rasterList = this.createRasterList();

        this.getTree().getStore().insert(parentDestination, rasterList, 0, true);

        this.addVisitor.insertLayerElements(rasterList, parentDestination);

        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
        mementoSaveLayer.setAddedLayers(MementoBuilder.generateMementoLayerList(rasterList));
        mementoSaveLayer.setDescendantMap(this.addVisitor.getFolderDescendantMap());
        GPLayerSaveCache.getInstance().add(mementoSaveLayer);

        clearComponents();
        LayoutManager.getInstance().getStatusMap().setStatus(
                "Added WMS on tree succesfully.",
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void reset() {
        this.save.disable();
        this.urlText.clear();
        this.saveStatus.clearStatus("");
        setFocusWidget(this.urlText);
    }

    public void showForm() {
        if (!isInitialized()) {
            super.init();
        }
        this.expander.checkNodeState();
    }

    private void clearComponents() {
        super.hide();
    }

    /**
     * @return the tree
     */
    public TreePanel<GPBeanTreeModel> getTree() {
        return tree;
    }

    @Override
    public void executeSave(final MementoSaveAddedLayers memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();

        LayerRemote.Util.getInstance().saveAddedLayersAndTreeModifications(memento,
                new AsyncCallback<ArrayList<Long>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught.getCause() instanceof GPSessionTimeout) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                        } else {
                            LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                            setStatus(EnumSaveStatus.STATUS_SAVE_ERROR.getValue(),
                                    EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR.getValue());
                            GeoPlatformMessage.errorMessage("Save WMS Error",
                                    "Problems on saving the new tree state after WMS creation");
                        }
                    }

                    @Override
                    public void onSuccess(ArrayList<Long> result) {
                        GPLayerSaveCache.getInstance().remove(memento);
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                "WMS saved successfully.",
                                EnumSearchStatus.STATUS_SEARCH.toString());
                        //Warning: What happens when I delete a raster before save it???
//                        memento.getRefBaseElement().setId(result.get(0)); // TODO
//                        LayerHandlerManager.fireEvent(peekCacheEvent);                     
                        List<AbstractMementoLayer> listMementoLayers = memento.getAddedLayers();
                        for (int i = 0; i < listMementoLayers.size(); i++) {
                            listMementoLayers.get(i).getRefBaseElement().setId(result.get(i));
                        }
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }
                });
    }

    private void retrieveDataFromQueryString() {
        String url = this.urlText.getValue();
        String queryString = url.substring(url.indexOf("?") + 1);
//        System.out.println("*** Query String: " + queryString + "\n");

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int ind = pair.indexOf("=");
            System.out.println("# " + pair);

            String field = pair.substring(0, ind).trim();
            String fieldValue = pair.substring(ind + 1).trim();

            fieldMap.put(field, fieldValue);
        }
    }

    private boolean checkUrl() {
        String url = this.urlText.getValue();
        if (url == null) {
            System.out.println("URL is NULL");
            return false;
        }
//        url = url.replaceAll("[ ]+", ""); // Delete all space character
//        UtilityLayerModule.replace(url, "[ ]+", ""); // Delete all space character

        if (!url.startsWith("http://")) {
            suggestion = "URL must be start with \"http://\"";
            return false;
        }
//        if (!url.contains("/wms?")) {
//            suggestion = "URL must contain \"/wms?\"";
//            return false;
//        }
        // TODO Check &field=fieldValue

        // Required field in query string
        if (UtilityLayerModule.match(url, GetMap.REQUEST
                + "[ ]*=[ ]*GetMap").length() == 0) {
            suggestion = "Query String must have \"" + GetMap.REQUEST + "=GetMap\"";
            return false;
        }
        if (UtilityLayerModule.match(url, GetMap.VERSION
                + "[ ]*=[ ]*1\\.(0\\.0|1\\.0|1\\.1)").length() == 0) {
            suggestion = "Query String must have \"" + GetMap.VERSION + "=1.0.0, 1.1.0, or 1.1.1\"";
            return false;
        }

        suggestion = "WMS URL is Syntactically Correct";
        return true;
    }

    private void verifyUrl() {
        LayerRemoteImpl.Util.getInstance().checkUrl(this.urlText.getValue(), new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                save.disable();
                GeoPlatformMessage.errorMessage("Error checking URL",
                        "An error occurred while making the requested connection.\n"
                        + "Verify network connections and try again."
                        + "\nIf the problem persists contact your system administrator.");
                LayoutManager.getInstance().getStatusMap().setStatus(
                        "Error checking the WMS URL.",
                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                System.out.println("Error checking the WMS URL: " + caught.toString()
                        + " data: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    save.enable();
                    setStatus(EnumWmsUrlStatus.STATUS_CHECKED.getValue(),
                            EnumWmsUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
                } else {
                    save.disable();
                    setStatus(EnumWmsUrlStatus.STATUS_CHECK_ERROR.getValue(),
                            EnumWmsUrlStatus.STATUS_MESSAGE_CHECK_ERROR.getValue());
                }
            }
        });
    }

    private void verifyUrlForEnterKey() {
        LayerRemoteImpl.Util.getInstance().checkUrl(this.urlText.getValue(), new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                save.disable();
                GeoPlatformMessage.errorMessage("Error checking URL",
                        "An error occurred while making the requested connection.\n"
                        + "Verify network connections and try again."
                        + "\nIf the problem persists contact your system administrator.");
                LayoutManager.getInstance().getStatusMap().setStatus(
                        "Error checking the WMS URL.",
                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                System.out.println("Error checking the WMS URL: " + caught.toString()
                        + " data: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    save.enable();
                    setStatus(EnumWmsUrlStatus.STATUS_CHECKED.getValue(),
                            EnumWmsUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
                    execute(); // Diff
                } else {
                    save.disable();
                    setStatus(EnumWmsUrlStatus.STATUS_CHECK_ERROR.getValue(),
                            EnumWmsUrlStatus.STATUS_MESSAGE_CHECK_ERROR.getValue());
                }
            }
        });
    }

    private List<GPBeanTreeModel> createRasterList() {
        List<GPBeanTreeModel> rasterList = new ArrayList<GPBeanTreeModel>();

        String layersValue = fieldMap.get(GetMap.LAYERS.toString()).trim();
        if (layersValue.contains(",")) { // More than one raster
            String[] rasters = layersValue.split(",");
            for (String raster : rasters) {
                rasterList.add(this.mapRaster(raster.trim()));
            }
        } else { // A single raster
            rasterList.add(this.mapRaster(layersValue));
        }

        return rasterList;
    }

    private RasterTreeNode mapRaster(String rasterString) {
        RasterTreeNode raster = new RasterTreeNode();

        raster.setName(rasterString);
        raster.setTitle(this.mapTitle(rasterString));
        raster.setLabel(raster.getTitle());
        raster.setLayerType(GPLayerType.RASTER);
        raster.setDataSource(this.mapDataSource());
        raster.setCrs(fieldMap.get(GetMap.SRS.toString()));
        raster.setBbox(this.mapBbox());
        raster.setStyles(this.mapStyles());

        System.out.println("\n*** Raster to ADD:\n" + raster + "\n***");
        return raster;
    }

    private String mapTitle(String rasterString) {
        if (rasterString.indexOf(":") != -1) { // workspace:title
            return rasterString.substring(rasterString.indexOf(":") + 1);
        }
        return rasterString;
    }

    private String mapDataSource() {
        String url = this.urlText.getValue();
        return url.substring(0, url.indexOf("?"));
    }

    private BboxClientInfo mapBbox() {
        String[] coordinates = fieldMap.get(GetMap.BBOX.toString()).split(",");

        BboxClientInfo bbox = new BboxClientInfo();
        bbox.setLowerLeftX(Double.parseDouble(coordinates[0].trim()));
        bbox.setLowerLeftY(Double.parseDouble(coordinates[1].trim()));
        bbox.setUpperRightX(Double.parseDouble(coordinates[2].trim()));
        bbox.setUpperRightY(Double.parseDouble(coordinates[3].trim()));
        return bbox;
    }

    private List<String> mapStyles() {
        List<String> styleList = new ArrayList<String>();

        String stylesValue = fieldMap.get(GetMap.STYLES);
        if (stylesValue != null && stylesValue.length() > 0) {
            if (stylesValue.contains(",")) { // More than one style
                String[] styles = stylesValue.trim().split(",");
                styleList.addAll(Arrays.asList(styles));
            } else { // A single style
                styleList.add(stylesValue);
            }
        }

        return styleList;
    }
}
