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
package org.geosdi.geoplatform.gui.client.widget.form;

import org.geosdi.geoplatform.gui.regex.GetMap;
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
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveOperations;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.form.WmsUrlStatus.EnumWmsUrlStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.server.gwt.LayerRemoteImpl;
import org.geosdi.geoplatform.gui.regex.GPRegEx;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class LoadWmsGetMapFromUrlWidget extends GPTreeFormWidget<RasterTreeNode>
        implements ISave<MementoSaveAddedLayers> {

    private TreePanel<GPBeanTreeModel> tree;
    private TextField<String> urlText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    //
    private Map<String, String> fieldMap = new HashMap<String, String>(); // TODO <GetMap, String> --> booolean GetMap.valid(String field)
    private String urlEncoding = "";
    private String suggestion = "";

    /**
     * @param theTree
     *
     */
    public LoadWmsGetMapFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(true);
        this.tree = theTree;
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(theTree, this);
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

        this.addListenerToUrlText();

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

    private void addListenerToUrlText() {
        this.urlText.addListener(Events.OnPaste, new Listener() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
                    verifyUrl(false);
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
                        verifyUrl(false);
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
                    verifyUrl(true);
                }
            }
        });
    }

    @Override
    public void initSize() {
        setHeading("Add WMS from GetMap direct URL");
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
        mementoSaveLayer.setAddedLayers(MementoSaveBuilder.generateMementoLayerList(rasterList));
        mementoSaveLayer.setDescendantMap(this.addVisitor.getFolderDescendantMap());
        IMementoSave mementoSave = LayerModuleInjector.MainInjector.getInstance().getMementoSave();
        mementoSave.add(mementoSaveLayer);

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
        reset();
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
        MementoSaveOperations.mementoSaveAddedLayer(memento,
                "WMS saved successfully.", "Problems on saving the new tree state after WMS creation");
    }

    private void retrieveDataFromQueryString() {
        String queryString = urlEncoding.substring(urlEncoding.indexOf("?") + 1);
//        System.out.println("*** Query String: " + queryString + "\n");

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int ind = pair.indexOf("=");
            System.out.println("# " + pair);

            String field = pair.substring(0, ind);
            String fieldValue = pair.substring(ind + 1);

            fieldMap.put(field.toUpperCase(), fieldValue);
        }
    }

    // TODO Check
    // Check &field=fieldValue
    // Check required fields in query string
    private boolean checkUrl() {
        boolean check = false;

        String url = this.urlText.getValue();
//        System.out.println("*** URL:\n" + url + "\n");        
        if (url == null) { // If it is added the listener on Events.OnPaste, sometimes url is NULL
            System.out.println("URL is NULL");
            return false;
        }

        url = url.replaceAll("[ ]+", ""); // Delete all spaces
//        System.out.println("*** URL clean:\n" + url + "\n");

        this.urlEncoding = URL.decodeQueryString(url); // Encoding into ASCII
//        System.out.println("*** URL encoding:\n" + urlEncoding + "\n");

        this.urlEncoding = urlEncoding.replaceAll("%26", "&");
        this.urlEncoding = urlEncoding.replaceAll("%3d", "=");
//        System.out.println("*** URL re-encoding:\n" + urlEncoding + "\n");

        if (!urlEncoding.startsWith("http://")) {
            suggestion = "URL must be start with \"http://\"";
//        } else if (!url.contains("/wms?")) { // TODO DEL ?
//            suggestion = "URL must contain \"/wms?\"";
        } else if (!GPRegEx.RE_REQUEST.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.REQUEST + "=GetMap\"";
        } else if (!GPRegEx.RE_VERSION.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.VERSION + "=1.0.0, 1.1.0, or 1.1.1\"";
        } else if (!GPRegEx.RE_LAYERS.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.LAYERS + "=value[,value,...]\"";
        } else if (!GPRegEx.RE_SRS.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.SRS + "=EPSG:id_code\"";
        } else if (!GPRegEx.RE_BBOX.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.BBOX + "=minx,miny,maxx,maxy\"";
        } else if (!GPRegEx.RE_WIDTH.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.WIDTH + "=output_width\"";
        } else if (!GPRegEx.RE_HEIGHT.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.HEIGHT + "=output_height\"";
        } else if (!GPRegEx.RE_FORMAT.test(urlEncoding)) {
            suggestion = "Query String must have \"" + GetMap.FORMAT + "=image/(png|gif|jpeg)\"";
        } else {
            suggestion = "WMS URL is Syntactically Correct";
            check = true;
        }
        System.out.println("*** Suggestion = " + suggestion);

        return check;
    }

    private void verifyUrl(final boolean runExecute) {
        LayerRemoteImpl.Util.getInstance().checkWmsGetMapUrl(this.urlEncoding, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                save.disable();
                GeoPlatformMessage.errorMessage("Error checking URL",
                        "An error occurred while making the requested connection.\n"
                        + "Verify network connections and try again.\n"
                        + "If the problem persists contact your system administrator.");
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
                    if (runExecute) { // Iff the enter key is pressed
                        execute();
                    }
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

        String layersValue = fieldMap.get(GetMap.LAYERS.toString());
        if (layersValue.contains(",")) { // More than one raster
            String[] rasters = layersValue.split(",");
            for (String raster : rasters) {
                rasterList.add(this.mapRaster(raster));
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

//        System.out.println("\n*** Raster to ADD:\n" + raster + "\n***");
        return raster;
    }

    private String mapTitle(String rasterString) {
        if (rasterString.indexOf(":") != -1) { // workspace:name
            return rasterString.substring(rasterString.indexOf(":") + 1);
        }
        return rasterString;
    }

    private String mapDataSource() {
        return urlEncoding.substring(0, urlEncoding.indexOf("?"));
    }

    private BBoxClientInfo mapBbox() {
        String[] coordinates = fieldMap.get(GetMap.BBOX.toString()).split(",");

        BBoxClientInfo bbox = new BBoxClientInfo();
        bbox.setLowerLeftX(Double.parseDouble(coordinates[0]));
        bbox.setLowerLeftY(Double.parseDouble(coordinates[1]));
        bbox.setUpperRightX(Double.parseDouble(coordinates[2]));
        bbox.setUpperRightY(Double.parseDouble(coordinates[3]));
        return bbox;
    }

    private ArrayList<GPStyleStringBeanModel> mapStyles() {
        ArrayList<GPStyleStringBeanModel> styleList = new ArrayList<GPStyleStringBeanModel>();

        String stylesValue = fieldMap.get(GetMap.STYLES.toString());
        if (stylesValue != null && stylesValue.length() > 0) {
            String[] styles = stylesValue.split(",");
            for (String string : styles) {
//                System.out.println("### Style:" + string);
                GPStyleStringBeanModel style = new GPStyleStringBeanModel();
                style.setStyleString(string);
                styleList.add(style);
            }
        }

        return styleList;
    }
}