/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.widget.map.util;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class EPSGTemplate extends GeoPlatformBeanModel {

    public enum EPSGEnum {

        EPSG("EPSG"), EPSG_DESCRIPTION("EPSG_DESCRIPTION");
        private String epsg;
        private String epsgDescription;

        EPSGEnum(String epsg) {
            this.epsg = epsg;
        }

        public String getValue() {
            return this.epsg;
        }
    }
    /**
     *
     */
    private static final long serialVersionUID = 7794981335181739804L;

    public EPSGTemplate(String epsg, String epsgDescription) {
        setEpsg(epsg, epsgDescription);
    }

    /**
     * @return the template
     */
    public String getEpsg() {
        return get(EPSGEnum.EPSG.getValue());
    }

    /**
     * @param template the template to set
     */
    public void setEpsg(String epsg, String epsgDescription) {
        set(EPSGEnum.EPSG.getValue(), epsg);
        set(EPSGEnum.EPSG_DESCRIPTION.getValue(), epsgDescription);
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EPSGTemplate [toString()=" + getEpsg() + "]";
    }
}
