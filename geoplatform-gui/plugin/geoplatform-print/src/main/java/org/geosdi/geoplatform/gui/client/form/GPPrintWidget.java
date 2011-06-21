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
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.PrintResources;
import org.geosdi.geoplatform.gui.client.form.binding.GPComboBoxFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapCommentFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.MapTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.form.binding.PrintTitleFieldBinding;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean.GPPrintEnumBean;
import org.geosdi.geoplatform.gui.client.utility.PrintUtility;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPDynamicFormBinding;

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

    public GPPrintWidget() {
        super();
        this.entity = new GPPrintBean();
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
            System.out.println(
                    "TEST ********************** " + this.entity.toString());
        }

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
}
