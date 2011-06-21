/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean;
import org.geosdi.geoplatform.gui.client.model.PrintTemplate;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class LayerProperties extends Window {

    protected ListStore<PrintTemplate> storeTemplate;
    protected ComboBox<PrintTemplate> comboTemplate;
    protected ListStore<DPI> storeDPI;
    protected ComboBox<DPI> comboDPI;
    protected TextField<String> title;
    protected TextField<String> mapTitle;
    protected TextArea comments;
    protected RadioGroup imageFormat;
    protected Radio png;
    protected Radio jpeg;
    private GPPrintBean print;
    private boolean initialized;
    private TextField<String> layerTitleText;
    private TextField<String> nameTitleText;
    private TextArea description;
    private GPBeanTreeModel model;
    private VerticalPanel vp;
    private FormData formData;

    public LayerProperties(boolean lazy, GPBeanTreeModel theModel) {
        this.model = theModel;
        if (!lazy) {
            init();
        }
    }

    private void init() {
        if (!isInitialized()) {
            initializeWindow();
            initComponents();
            this.initialized = true;
        }
    }

    private void initializeWindow() {
        super.setSize(350, 200);
        super.setHeading("Layer Properties - " + model.getLabel());
        setResizable(true);

        setLayout(new FitLayout());
        setModal(true);
        setCollapsible(true);
        setPlain(true);

    }

    @Override
    public void show() {
        if (!isInitialized()) {
            this.init();
        }
        super.show();
    }

    private void initComponents() {

        formData = new FormData("-20");
        vp = new VerticalPanel();
        vp.setSpacing(0);

        TabPanel panel = new TabPanel();
        panel.setPlain(true);
        panel.setAutoHeight(true);


        TabItem about = new TabItem("About");

        this.layerTitleText = new TextField<String>();
        this.layerTitleText.setFieldLabel("Title");
        this.layerTitleText.setValue(model.getLabel());



        about.add(layerTitleText);



        panel.add(about);


        TabItem display = new TabItem("Display");
        panel.add(display);

        vp.add(panel);
        vp.setAutoWidth(true);
        vp.setAutoHeight(true);


        super.add(vp);

    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
