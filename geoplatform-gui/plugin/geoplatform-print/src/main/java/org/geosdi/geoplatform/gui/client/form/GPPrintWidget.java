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
package org.geosdi.geoplatform.gui.client.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.RootPanel;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.PrintResources;
import org.geosdi.geoplatform.gui.client.form.binding.GPComboBoxFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapCommentFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.PrintTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.i18n.PrintTemplateConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean.GPPrintEnumBean;
import org.geosdi.geoplatform.gui.client.model.PrintTemplate;
import org.geosdi.geoplatform.gui.client.model.Scale;
import org.geosdi.geoplatform.gui.client.utility.LayerComparable;
import org.geosdi.geoplatform.gui.client.utility.PrintUtility;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPDynamicFormBinding;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextArea;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.control.DragFeature;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.client.i18n.PrintModuleConstants.INSTANCE;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GPPrintWidget extends GPDynamicFormBinding<GPPrintBean> {

    public final static String PRINT_VECTOR_NAME = "VectorPrintExtent";
    private final static Logger logger = Logger.getLogger("GPPrintWidget");
    private ListStore<DPI> storeDPI;
    private ListStore<PrintTemplate> storeTemplate;
    private ListStore<Scale> storeScale;
    private ComboBox<DPI> comboDPI;
    private ComboBox<PrintTemplate> comboTemplate;
    private ComboBox<Scale> comboScale;
    private CheckBox checkPrintBaseMap;
    private GPSecureStringTextField title;
    private GPSecureStringTextField mapTitle;
    private GPSecureStringTextArea comments;
    private Button print;
    private Button cancel;
    private TreePanel tree;
    private List<GPLayerBean> layerList;
    private double sizeFactor = .5;
    private boolean rotation = true;
    private DragFeature dragPrintArea;
    private Vector printExtent;

    public GPPrintWidget() {
        super();
        this.entity = new GPPrintBean();
    }

    public GPPrintWidget(TreePanel theTree) {
        this.entity = new GPPrintBean();
        this.tree = theTree;
    }

    @Override
    public void addComponentToForm() {
        addEditPrintSettings();
        addComboDPI();
        addComboTemplate();
        addScaleCombo();
        addCheckPrintBaseMap();
        addButtons();
    }

    @Override
    public void initSize() {
        super.setHeadingHtml(INSTANCE.GPPrintWidget_headingText());
        super.setPosition(RootPanel.get().getOffsetWidth() - 400 - 6, 55);
        setSize(400, 650);
    }

    @Override
    public void initSizeFormPanel() {
        formPanel.setHeaderVisible(false);
        formPanel.setSize(400, 560);
        super.setModal(false);
    }

    @Override
    public void execute() {
        if (formPanel.isValid()) {
            layerList = buildLayerList();
            if (layerList.isEmpty()) {
                GeoPlatformMessage.alertMessage(INSTANCE.GPPrintWidget_warningMessageLayersEmptyListText(),
                        INSTANCE.GPPrintWidget_warningMessageLayersEmptyListText());
                return;
            }

            logger.log(Level.FINEST, "Execute ......");
            // Center on correct ViewPort
            LonLat center = printExtent.getDataExtent().getCenterLonLat();
            if (GPApplicationMap.getInstance().getApplicationMap().getMap().
                    getProjection().equals(
                    GPCoordinateReferenceSystem.WGS_84.getCode())) {
                center.transform(GPCoordinateReferenceSystem.WGS_84.getCode(),
                        GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
            }

            String specJson = "";

            specJson = "{\"layout\":\"" + comboTemplate.getValue().getTemplate() + "\""
                    + ",\"srs\":\"EPSG:3857\",\"units\": \"m\",\"geodetic\":true,\"outputFilename\":\"gp-map\", \"outputFormat\":\"pdf\",";

//            String layers = "{\"title\":\"" + title.getValue() + "\",\"pages\":[{\"center\":["
//                    + center.lon() + ","
//                    + center.lat()
//                    + "],\"scale\":" + getCurrentScale()
//                    + ",\"rotation\":0,\"mapTitle\":\"" + mapTitle.getValue()
//                    + "\",\"comment\":\"" + comments.getValue() + "\"}],\"layers\":[";
            specJson = specJson.concat(buildBaseLayerJson());

            String pagesJson = "\"pages\": ["
                    + "{"
                    + "\"center\": [" + center.lon() + "," + center.lat() + "],"
                    + "\"scale\": " + getCurrentScale() + ","
                    + "\"dpi\": " + comboDPI.getValue().getDpi() + ","
                    + "\"mapTitle\": \"" + mapTitle.getValue() + "\","
                    + "\"title\": \"" + title.getValue() + "\","
                    + "\"comment\": \"" + comments.getValue() + "\""
                    + "}"
                    + "],\n";

            String legendJson = "\"legends\": [";
            String legendLayers = buildLegendLayerJson();
            String legendEnd = "]}";

            specJson = specJson.concat(
                    pagesJson + legendJson + legendLayers + legendEnd);

            String url = GWT.getHostPageBaseURL() + GWT.getModuleName() + "/pdf/create.json";

            RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

            String jsonData = "spec=" + specJson;

            builder.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            try {
                Info.display(INSTANCE.printText(),
                        INSTANCE.GPPrintWidget_infoStartPringBodyText());
                logger.log(Level.INFO, jsonData);
                Request response = builder.sendRequest(jsonData,
                        new RequestCallback() {

                            @Override
                            public void onError(Request request, Throwable exception) {
                                Window.alert(exception.getLocalizedMessage());
                            }

                            @Override
                            public void onResponseReceived(Request request,
                                    Response response) {
                                Info.display(INSTANCE.printText(),
                                        INSTANCE.GPPrintWidget_infoFinishPrintBodyText());
                                String downloadURL = response.getText().substring(11,
                                        response.getText().indexOf("printout") + 8);

                                Window.open(downloadURL, "_blank", "");

                            }
                        });
            } catch (RequestException ex) {
                Logger.getLogger(GPPrintWidget.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            this.hide();
        }

    }

    public String buildLegendLayerJson() {
        String jsonLegendLayer = "";

        layerList = buildLayerList();
        if (!layerList.isEmpty()) {

            Collections.sort(layerList, new LayerComparable());

            String firstLegend = "{\n"
                    + "\"name\": \"" + layerList.get(0).getName() + "\",\n"
                    + "\"classes\": ["
                    + "{"
                    + "\"name\": \"" + "\",\n"
                    + "\"icons\": ["
                    + "\"" + getLegendUrl(layerList.get(0)) + "\""
                    //+ "\"" + "http%3A%2F%2Fdpc.geosdi.org%2Fgeoserver%2Fwms%3FREQUEST%3DGetLegendGraphic%26VERSION%3D1.0.0%26FORMAT%3Dimage%2Fpng%26LAYER%3DPiano_Calabria%3Apga%26scale%3D5000%26service%3DWMS" + "\""
                    + "]\n"
                    + "}"
                    + "]\n"
                    + "}";

            String legendListJson = "";
            for (int i = 1; i < layerList.size(); i++) {
                if (layerList.get(i) instanceof GPLayerBean) {
                    GPLayerBean layer = (GPLayerBean) layerList.get(i);
                    legendListJson = legendListJson.concat(buildLegendOrderList(
                            layer));
                }
            }
            return jsonLegendLayer.concat(firstLegend + legendListJson);
        }

        return jsonLegendLayer;
    }

    private String buildLegendOrderList(GPLayerBean layer) {
        String legend = ",{\n"
                + "            \"name\": \"" + layer.getName() + "\",\n"
                + "            \"classes\": ["
                + "{"
                + "\"name\": \"" + "\",\n"
                + "            \"icons\": ["
                + "\"" + getLegendUrl(layer) + "\""
                //+ "\"" + "http%3A%2F%2Fdpc.geosdi.org%2Fgeoserver%2Fwms%3FREQUEST%3DGetLegendGraphic%26VERSION%3D1.0.0%26FORMAT%3Dimage%2Fpng%26LAYER%3DPiano_Calabria%3Ascenario_crolli%26scale%3D5000%26service%3DWMS" + "\""
                + "]\n"
                + "}"
                + "]\n"
                + "}";
        return legend;

    }

    private String getLegendUrl(GPLayerBean layer) {
        String dataSource = layer.getDataSource();

        if (dataSource.contains("gwc/service/wms")) {
            dataSource = dataSource.replaceAll("gwc/service/wms", "wms");
        } else if (!(dataSource.startsWith("http://ows"))
                && (dataSource.contains("/ows"))) {
            dataSource = dataSource.replaceAll("/ows", "/wms");
        } else {
            dataSource = dataSource.replaceAll("/wfs", "/wms");
        }

        String dataSourceT = dataSource;

        String style = this.getStyleFromLayer(layer);

        String imageURL = URL.encodeQueryString(
                dataSourceT + "?REQUEST=GetLegendGraphic"
                        + "&VERSION=1.0.0&FORMAT=image/png&LAYER="
                        + URL.encode(layer.getName())
                        + "&STYLE=" + style
                        + "&scale=5000&service=WMS");

        return imageURL;

    }

    public String buildBaseLayerJson() {
        String json = "";
        String start = "\"layers\":[{";
        String baseURL = "\"baseURL\": \"http://tile.openstreetmap.org/\",";
        String opacity = "\"opacity\": 1,";
        String type = "\"type\":\"Osm\",";
        String maxExtent = "\"maxExtent\": ["
                + "-20037508.3392,"
                + "-20037508.3392,"
                + "20037508.3392,"
                + "20037508.3392"
                + "],";
        String tileSize = "\"tileSize\": ["
                + "256,"
                + "256"
                + "],";

        String resolutions = "\"resolutions\": ["
                + "156543.0339,78271.51695,39135.758475,19567.8792375,9783.93961875,4891.969809375,2445.9849046875,1222.99245234375,611.496226171875,305.7481130859375,152.87405654296876,76.43702827148438,38.21851413574219,19.109257067871095,9.554628533935547,4.777314266967774,2.388657133483887,1.1943285667419434,0.5971642833709717"
                + "],";
        String extentions = "\"extension\": \"png\"}";

        layerList = buildLayerList();

        Collections.sort(layerList, new LayerComparable());

        String layerListJson = "";
        for (GPLayerBean layerBean : layerList) {
            layerListJson = layerListJson.concat(buildLayersOrderList(layerBean));
        }

        String end = "],";

        if (checkPrintBaseMap.getValue()) {
            return json.concat(start + baseURL + opacity + type + maxExtent + tileSize + resolutions + extentions + layerListJson + end);
        } else {
            return json.concat(start.replace("{", "") + layerListJson.substring(1, layerListJson.length()) + end);

        }
    }

    private String getStyleFromLayer(GPLayerBean layer) {
        String style = "";
        if (layer.getStyles() != null && !layer.getStyles().isEmpty()) {
            style = URL.encode(layer.getStyles().get(0).getStyleString());
        }
        return style;
    }

    public String buildLayersOrderList(GPLayerBean layer) {
        String style = this.getStyleFromLayer(layer);

        String layerJson = ",{\n"
                + "            \"baseURL\": \"" + layer.getDataSource() + "\",\n"
                + "            \"opacity\": 1,\n"
                + "            \"singleTile\": false,\n"
                + "            \"type\": \"WMS\",\n"
                + "            \"layers\": [\n"
                + "                \"" + layer.getName() + "\"\n"
                + "            ],\n"
                + "            \"format\": \"image/png\",\n"
                + "            \"styles\": [\n"
                + "                \"" + style + "\"\n"
                + "            ],\n"
                + "            \"customParams\": {\n"
                + "                \"TRANSPARENT\": \"TRUE\"\n"
                + "            }\n"
                + "        }";

        return layerJson;
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(
                new PrintTitleFieldBinding(title,
                        GPPrintEnumBean.GPPRINT_TITLE.toString()));
        this.formBinding.addFieldBinding(
                new MapTitleFieldBinding(mapTitle,
                        GPPrintEnumBean.GPPRINT_MAP_TITLE.toString()));
        this.formBinding.addFieldBinding(
                new MapCommentFieldBinding(comments,
                        GPPrintEnumBean.GPPRINT_COMMENTS.toString()));
        this.formBinding.addFieldBinding(
                new GPComboBoxFieldBinding(comboDPI,
                        GPPrintEnumBean.GPPRINT_DPI.toString()));
    }

    @Override
    public void reset() {
        logger.log(Level.FINEST, "Reset window ...");
        this.entity.reset();
        this.formBinding.unbind();
        this.title.reset();
        this.mapTitle.reset();
        this.comments.reset();

        if (printExtent != null && dragPrintArea != null) {
            PrintUtility.disableDragPrintArea(GPApplicationMap.getInstance().
                            getApplicationMap().getMap(),
                    dragPrintArea);
        }

        if (GPApplicationMap.getInstance().getApplicationMap().getMap().
                getLayerByName(PRINT_VECTOR_NAME) != null) {

//            GPApplicationMap.getInstance().getApplicationMap().getMap().
//                    removeControl(dragPrintArea);
//            PrintUtility.disableDragPrintArea(GPApplicationMap.getInstance().
//                    getApplicationMap().getMap(), dragPrintArea);
//            GPApplicationMap.getInstance().getApplicationMap().getMap().
//                    removeLayer(GPApplicationMap.getInstance().
//                    getApplicationMap().getMap().getLayerByName(
//                    "VectorPrintExtent"));
            //*****************
            GPApplicationMap.getInstance().
                    getApplicationMap().getMap().getLayerByName(
                    PRINT_VECTOR_NAME).setOpacity(0f);
//            GPApplicationMap.getInstance().getApplicationMap().getMap().removeLayer(printExtent);
            //*****************+
            //GPApplicationMap.getInstance().getApplicationMap().getMap().getLayerByName("VectorPrintExtent").destroy(true);
        }

    }

    private void addEditPrintSettings() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(INSTANCE.GPPrintWidget_editFieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        title = new GPSecureStringTextField();
        title.setAllowBlank(false);
        title.setName(GPPrintEnumBean.GPPRINT_TITLE.toString());
        title.setFieldLabel(INSTANCE.GPPrintWidget_titleLabelText());

        fieldSet.add(title);

        mapTitle = new GPSecureStringTextField();
        mapTitle.setName(GPPrintEnumBean.GPPRINT_MAP_TITLE.toString());
        mapTitle.setFieldLabel(INSTANCE.GPPrintWidget_mapTitleLabelText());

        fieldSet.add(mapTitle);

        comments = new GPSecureStringTextArea();
        comments.setName(GPPrintEnumBean.GPPRINT_COMMENTS.toString());
        comments.setPreventScrollbars(true);
        comments.setFieldLabel(INSTANCE.GPPrintWidget_commentsLabelText());

        comments.setSize(150, 150);
        fieldSet.add(comments);

        this.formPanel.add(fieldSet);
    }

    private void addComboDPI() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(INSTANCE.GPPrintWidget_DPIFieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        this.storeDPI = new ListStore<DPI>();
        this.storeDPI.add(PrintUtility.getDPI());

        this.comboDPI = new ComboBox<DPI>();
        this.comboDPI.setFieldLabel(INSTANCE.GPPrintWidget_comboDPIFieldLabelText());
        this.comboDPI.setEmptyText(INSTANCE.GPPrintWidget_comboDPIEmptyText());
        this.comboDPI.setDisplayField(DPI.EnumDPI.DPI.getValue());
        this.comboDPI.setEditable(false);
        this.comboDPI.setAllowBlank(false);
        this.comboDPI.setForceSelection(true);
        this.comboDPI.setTypeAhead(true);
        this.comboDPI.setTriggerAction(TriggerAction.ALL);

        this.comboDPI.setStore(this.storeDPI);

        fieldSet.add(this.comboDPI);

        super.formPanel.add(fieldSet);
    }

    private void addComboTemplate() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(INSTANCE.GPPrintWidget_templateFieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        this.storeTemplate = new ListStore<PrintTemplate>();
        this.storeTemplate.add(PrintUtility.getTemplate());

        this.comboTemplate = new ComboBox<PrintTemplate>();
        this.comboTemplate.setFieldLabel(
                INSTANCE.GPPrintWidget_comboTemplateFieldLabelText());
//        this.comboTemplate.setEmptyText("Choose Template....");
        this.comboTemplate.setValue(new PrintTemplate(
                PrintTemplateConstants.INSTANCE.A4_Portrait()));

        this.comboTemplate.setDisplayField(
                PrintTemplate.PrintEnumTemplate.TEMPLATE.getValue());
        this.comboTemplate.setEditable(false);
        this.comboTemplate.setAllowBlank(false);
        this.comboTemplate.setForceSelection(true);
        this.comboTemplate.setTypeAhead(true);
        this.comboTemplate.setTriggerAction(TriggerAction.ALL);

        this.comboTemplate.setStore(this.storeTemplate);

        fieldSet.add(this.comboTemplate);

        super.formPanel.add(fieldSet);

        comboTemplate.addSelectionChangedListener(
                new SelectionChangedListener<PrintTemplate>() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent<PrintTemplate> se) {
                        if (se != null) {
                            if (GPApplicationMap.getInstance().getApplicationMap().
                                    getMap().getLayerByName(PRINT_VECTOR_NAME) != null) {

                                printExtent.removeAllFeatures();

                                String scaleString = comboScale.getValue().getScale();
                                String scaleStringRight = scaleString.substring(
                                        scaleString.indexOf(":") + 1);
                                String scaleStringWithoutDot = scaleStringRight.
                                        replaceAll("\\.", "");
                                float scale = Float.parseFloat(scaleStringWithoutDot);
                                updateRotationAndSizeForPrint(se.getSelectedItem().
                                        getTemplate());

                                printExtent.addFeature(PrintUtility.updateRectangle(
                                        GPApplicationMap.getInstance().
                                                getApplicationMap().getMap().getCenter(), scale,
                                        GPApplicationMap.getInstance().
                                                getApplicationMap().getMap(), sizeFactor,
                                        rotation));
                            }

                        }
                    }
                });
    }

    private void addScaleCombo() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(INSTANCE.GPPrintWidget_scaleFieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        this.storeScale = new ListStore<Scale>();
        this.storeScale.add(PrintUtility.getScale());

        this.comboScale = new ComboBox<Scale>();
        this.comboScale.setFieldLabel(INSTANCE.
                GPPrintWidget_comboScaleFieldLabelText());
//        this.comboScale.setEmptyText("Choose Scale....");
        this.comboScale.setValue(new Scale("1:4.000.000"));
        this.comboScale.setDisplayField(
                Scale.ScaleEnum.SCALE.getValue());
        this.comboScale.setEditable(false);
        this.comboScale.setAllowBlank(false);
        this.comboScale.setForceSelection(true);
        this.comboScale.setTypeAhead(true);
        this.comboScale.setTriggerAction(TriggerAction.ALL);

        this.comboScale.setStore(this.storeScale);

        fieldSet.add(this.comboScale);

        super.formPanel.add(fieldSet);

        comboScale.addSelectionChangedListener(
                new SelectionChangedListener<Scale>() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent<Scale> se) {
                        if (se != null) {
                            if (GPApplicationMap.getInstance().getApplicationMap().
                                    getMap().getLayerByName(PRINT_VECTOR_NAME) != null) {
                                printExtent.removeAllFeatures();

                                String scaleString = se.getSelectedItem().getScale();
                                String scaleStringRight = scaleString.substring(
                                        scaleString.indexOf(":") + 1);
                                String scaleStringWithoutDot = scaleStringRight.
                                        replaceAll("\\.", "");
                                float scale = Float.parseFloat(scaleStringWithoutDot);

                                printExtent.addFeature(PrintUtility.updateRectangle(
                                        GPApplicationMap.getInstance().
                                                getApplicationMap().getMap().getCenter(), scale,
                                        GPApplicationMap.getInstance().
                                                getApplicationMap().getMap(), sizeFactor,
                                        rotation));
                            }

                        }
                    }
                });

    }

    private void addCheckPrintBaseMap() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(INSTANCE.
                GPPrintWidget_checkPrintFieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);
        this.checkPrintBaseMap = new CheckBox();
        this.checkPrintBaseMap.setFieldLabel(INSTANCE.
                GPPrintWidget_checkBoxPrintBaseMapFieldLabelText());
        this.checkPrintBaseMap.setToolTip(INSTANCE.
                GPPrintWidget_checkBoxPrintBaseMapTooltipText());
        fieldSet.add(this.checkPrintBaseMap);
        super.formPanel.add(fieldSet);
    }

    private void addButtons() {
        formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        print = new Button(ButtonsConstants.INSTANCE.printText(),
                AbstractImagePrototype.create(PrintResources.ICONS.print()),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        this.formPanel.addButton(print);

        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                        reset();
                    }
                });

        this.formPanel.addButton(cancel);
    }

    private List<GPLayerBean> getVisibleLayersOnTree(List<ModelData> layers,
            List<GPLayerBean> visibleLayers) {
        for (Iterator<ModelData> it = layers.iterator(); it.hasNext(); ) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            if (element instanceof AbstractFolderTreeNode && element.isChecked()
                    && element.getChildCount() != 0) {
                this.
                        getVisibleLayersOnTree(element.getChildren(),
                                visibleLayers);
            } else if (element.isChecked() && element instanceof GPLayerTreeModel) {
                visibleLayers.add((GPLayerBean) element);
            }
        }
        return visibleLayers;
    }

    public List<GPLayerBean> buildLayerList() {
        layerList = Lists.<GPLayerBean>newArrayList();
        AbstractRootTreeNode root = (AbstractRootTreeNode) this.tree.getStore().
                getRootItems().get(
                0);
        assert (root != null) : "VisitorDisplayHide on getVisibleLayers(): Impossible to retrieve root element";
        return this.getVisibleLayersOnTree(root.getChildren(), layerList);
    }

    @Override
    public void showForm() {
        this.createVectorPrintLayer();
        super.showForm();
        this.comboScale.setValue(new Scale("1:4.000.000"));
        this.comboDPI.setValue(new DPI("192"));
        updateRotationAndSizeForPrint(comboTemplate.getValue().getTemplate());
    }

    private void createVectorPrintLayer() {
        if (GPApplicationMap.getInstance().getApplicationMap().getMap().
                getLayerByName(PRINT_VECTOR_NAME) == null) {
            printExtent = PrintUtility.createRectangle(GPApplicationMap.
                            getInstance().getApplicationMap().getMap().getCenter(), 4000000,
                    GPApplicationMap.getInstance().getApplicationMap().getMap(),
                    sizeFactor, rotation);
            Layer layer = Layer.narrowToLayer(printExtent.getJSObject());
            GPApplicationMap.getInstance().
                    getApplicationMap().getMap().addLayer(layer);
            dragPrintArea = PrintUtility.enableDragPrintArea(GPApplicationMap.
                    getInstance().getApplicationMap().getMap(), printExtent);
        } else {
//            /* Use existing layer */
            GPApplicationMap.getInstance().
                    getApplicationMap().getMap().getLayerByName(
                    PRINT_VECTOR_NAME).setOpacity(1f);
            dragPrintArea = PrintUtility.enableDragPrintArea(GPApplicationMap.
                    getInstance().getApplicationMap().getMap(), printExtent);
        }
    }

    private void updateRotationAndSizeForPrint(String template) {

        if (template.contains(PrintTemplateConstants.INSTANCE.A4_Landscape())) {
            sizeFactor = .5;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A4_Portrait())) {
            sizeFactor = .5;
            rotation = true;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A3_Landscape())) {
            sizeFactor = 1;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A3_Portrait())) {
            sizeFactor = 1;
            rotation = true;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A2_Landscape())) {
            sizeFactor = 2;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A2_Portrait())) {
            sizeFactor = 2;
            rotation = true;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A1_Landscape())) {
            sizeFactor = 3;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A1_Portrait())) {
            sizeFactor = 3;
            rotation = true;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A0_Landscape())) {
            sizeFactor = 4;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A0_Portrait())) {
            sizeFactor = 4;
            rotation = true;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A0P_Landscape())) {
            sizeFactor = 5;
            rotation = false;
        } else if (template.contains(PrintTemplateConstants.INSTANCE.A0P_Portrait())) {
            sizeFactor = 5;
            rotation = true;
        }
    }

    private float getCurrentScale() {
        String scaleString = comboScale.getValue().getScale();
        String scaleStringRight = scaleString.substring(
                scaleString.indexOf(":") + 1);
        String scaleStringWithoutDot = scaleStringRight.
                replaceAll("\\.", "");
        return Float.parseFloat(scaleStringWithoutDot);
    }
}
