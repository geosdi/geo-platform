/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class DPI extends GeoPlatformBeanModel {

    public enum EnumDPI {

        DPI("dpi");
        private String dpi;

        private EnumDPI(String dpi) {
            this.dpi = dpi;
        }

        public String getValue() {
            return this.dpi;
        }
    }

    public DPI(String dpi) {
        setDpi(dpi);
    }

    /**
     * @return the dpi
     */
    public String getDpi() {
        return get(EnumDPI.DPI.getValue());
    }

    /**
     * @param dpi
     *            the dpi to set
     */
    public void setDpi(String dpi) {
        set(EnumDPI.DPI.getValue(), dpi);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DPI [toString()=" + getDpi() + "]";
    }
}
