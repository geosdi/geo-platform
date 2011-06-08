/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.form;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import org.geosdi.geoplatform.gui.client.model.DPI;
import org.geosdi.geoplatform.gui.client.model.GPPrintBean;
import org.geosdi.geoplatform.gui.client.model.PrintTemplate;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GPPrintWidget extends Window {

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

    public GPPrintWidget(boolean lazy) {
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
        super.setSize(600, 500);
        super.setHeading("Print Visible Layer");
        setResizable(false);
        
        setLayout(new FitLayout());
        setModal(true);
        setCollapsible(true);
        setPlain(true);
        
        System.out.println(".... print windows");
    }
    
    @Override
    public void show() {
        if (!isInitialized()) {
            this.init();
        }
        super.show();
    }

    private void initComponents() {
        
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
