/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.PrintResources;
import org.geosdi.geoplatform.gui.client.form.binding.GPComboBoxFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapCommentFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.PrintTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean.GPPrintEnumBean;
import org.geosdi.geoplatform.gui.client.model.Scale;
import org.geosdi.geoplatform.gui.client.utility.LayerComparable;
import org.geosdi.geoplatform.gui.client.utility.PrintUtility;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPDynamicFormBinding;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.gwtopenmaps.openlayers.client.LonLat;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GPPrintWidget extends GPDynamicFormBinding<GPPrintBean> {

    private ListStore<DPI> storeDPI;
    private ComboBox<DPI> comboDPI;
    private TextField<String> title;
    private TextField<String> mapTitle;
    private TextArea comments;
    private Button print;
    private Button cancel;
    private TreePanel tree;
    private double scale;
    private List<GPLayerBean> layerList;

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
        addButtons();
    }

    @Override
    public void initSize() {
        super.setHeading("GeoPlatform Print Widget");
        setSize(400, 450);
    }

    @Override
    public void initSizeFormPanel() {
        formPanel.setHeaderVisible(false);
        formPanel.setSize(400, 450);
    }

    @Override
    public void execute() {
        if (formPanel.isValid()) {

            double lat = GPApplicationMap.getInstance().getApplicationMap().getMap().getCenter().lat();
            double lon = GPApplicationMap.getInstance().getApplicationMap().getMap().getCenter().lon();


            LonLat center = new LonLat(lon, lat);
            center.transform(GPApplicationMap.getInstance().getApplicationMap().getMap().getProjection(), "EPSG:4326");

            Double scaleDouble = new Double(GPApplicationMap.getInstance().getApplicationMap().getMap().getScale());

            String layers = "{\"title\":\"" + title.getValue() + "\",\"pages\":[{\"center\":["
                    + center.lon() + ","
                    + center.lat()
                    + "],\"scale\":" + Scale.searchValue(scaleDouble)
                    + ",\"rotation\":0,\"mapTitle\":\"" + mapTitle.getValue()
                    + "\",\"comment\":\"" + comments.getValue() + "\"}],\"layers\":[";

            layerList = buildLayerList(tree.getCheckedSelection());

            Collections.sort(layerList, new LayerComparable());

            for (int i = 0; i < layerList.size(); i++) {
                if (layerList.get(i) instanceof GPLayerBean) {
                    GPLayerBean layer = (GPLayerBean) layerList.get(i);
                    layers = layers.concat(buildLayersOrderList(layer));
                }
            }

            layers = layers.concat("],\"layout\":\"A3 portrait\",\"srs\":\"EPSG:4326\",\"dpi\":"
                    + comboDPI.getValue().getDpi() + ",\"units\":\"degrees\"}");

            String url = GWT.getHostPageBaseURL() + GWT.getModuleName() + "/pdf/print.pdf?spec="
                    + URL.encode(layers);

            System.out.println(URL.decode(url));

            Window.open(url, "_blank", "");

            this.hide();
        }

    }

    public String buildLayersOrderList(GPLayerBean layer) {
        String start = "{\"layers\":[";
        String apice = "\"";
        String layerList = "";
        String baseURL = "],\"baseURL\":\"" + layer.getDataSource();
        String format = "\",\"format\":\"" + "image/png";
        String end = "\",\"type\":\"WMS\"},";
        layerList = layerList.concat(start);
        layerList = layerList.concat(apice + layer.getName() + apice + ",");
        layerList = layerList.substring(0, layerList.length() - 1);

        return layerList.concat(baseURL + format + end);
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new PrintTitleFieldBinding(title,
                GPPrintEnumBean.GPPRINT_TITLE.toString()));
        this.formBinding.addFieldBinding(new MapTitleFieldBinding(mapTitle,
                GPPrintEnumBean.GPPRINT_MAP_TITLE.toString()));
        this.formBinding.addFieldBinding(new MapCommentFieldBinding(comments,
                GPPrintEnumBean.GPPRINT_COMMENTS.toString()));
        this.formBinding.addFieldBinding(new GPComboBoxFieldBinding(comboDPI,
                GPPrintEnumBean.GPPRINT_DPI.toString()));
    }

    @Override
    public void reset() {
        this.entity.reset();
        this.formBinding.unbind();
        this.title.reset();
        this.mapTitle.reset();
        this.comments.reset();
        this.comboDPI.reset();
        this.comboDPI.clearSelections();
    }

    private void addEditPrintSettings() {
        fieldSet = new FieldSet();
        fieldSet.setHeading("Edit Print Settings");
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        title = new TextField<String>();
        title.setAllowBlank(false);
        title.setName(GPPrintEnumBean.GPPRINT_TITLE.toString());
        title.setFieldLabel("Title");

        fieldSet.add(title);

        mapTitle = new TextField<String>();
        mapTitle.setName(GPPrintEnumBean.GPPRINT_MAP_TITLE.toString());
        mapTitle.setFieldLabel("Map Title");

        fieldSet.add(mapTitle);

        comments = new TextArea();
        comments.setName(GPPrintEnumBean.GPPRINT_COMMENTS.toString());
        comments.setPreventScrollbars(true);
        comments.setFieldLabel("Comments");

        comments.setSize(150, 150);
        fieldSet.add(comments);

        this.formPanel.add(fieldSet);
    }

    private void addComboDPI() {
        fieldSet = new FieldSet();
        fieldSet.setHeading("DPI");
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);

        this.storeDPI = new ListStore<DPI>();
        this.storeDPI.add(PrintUtility.getDPI());

        this.comboDPI = new ComboBox<DPI>();
        this.comboDPI.setFieldLabel("Select DPI");
        this.comboDPI.setEmptyText("Choose DPI....");
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

    private void addButtons() {
        formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        print = new Button("Print", PrintResources.ICONS.print(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        this.formPanel.addButton(print);

        this.cancel = new Button("Cancel", BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }
                });

        this.formPanel.addButton(cancel);
    }

    private List<GPLayerBean> buildLayerList(List checkedSelection) {
        layerList = new ArrayList<GPLayerBean>();
        for (int i = 0; i < checkedSelection.size(); i++) {
            if (checkedSelection.get(i) instanceof GPLayerBean) {
                layerList.add((GPLayerBean) checkedSelection.get(i));
            }
        }
        return layerList;
    }
}
