/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;

import com.extjs.gxt.ui.client.data.BeanModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class PrintTemplate extends BeanModel {

    public enum PrintEnumTemplate {

        TEMPLATE("PRINT_TEMPLATE");
        private String printTemplate;

        PrintEnumTemplate(String printTemplate) {
            this.printTemplate = printTemplate;
        }

        public String getValue() {
            return this.printTemplate;
        }
    }
    /**
     * 
     */
    private static final long serialVersionUID = 7794981335181739804L;

    public PrintTemplate(String template) {
        setTemplate(template);
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return get(PrintEnumTemplate.TEMPLATE.getValue());
    }

    /**
     * @param template
     *            the template to set
     */
    public void setTemplate(String template) {
        set(PrintEnumTemplate.TEMPLATE.getValue(), template);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrintTemplate [toString()=" + getTemplate() + "]";
    }
}
