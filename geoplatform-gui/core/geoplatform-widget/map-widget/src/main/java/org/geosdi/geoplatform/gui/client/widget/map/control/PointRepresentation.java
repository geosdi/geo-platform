/**
 * ------------------------------------------------------------------ --
 * Copyright <Creation_date>-<Last modification date> SELEX Sistemi -- Integrati
 * S.p.A. all rights reserved. -- This software is the property of SELEX Sistemi
 * Integrati S.p.A. -- and can not be reproduced, used to prepare Derivative
 * Works of, -- publicly displayed, publicly performed, sublicensed, and it --
 * cannot be distributed as the Work itself and such Derivative -- Works in
 * Source or Object form except under a license agreement -- granted by SELEX
 * Sistemi Integrati S.p.A.
 * ------------------------------------------------------------------
 *
 * @file GeoPortalAOE.java
 *
 * @author giuseppe
 * @date 12/nov/2010
 * @brief
 * @date Date ; Name ; Description of modification
 */
package org.geosdi.geoplatform.gui.client.widget.map.control;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PointRepresentation extends GeoPlatformBeanModel {

    private static final long serialVersionUID = 4796399385877182227L;

    public enum PointRepresentationKeyValue {

        EPSG_CODE, X_COORDINATE, Y_COORDINATE;
    }

    public String getEpsgCode() {
        return super.get(PointRepresentationKeyValue.EPSG_CODE.toString());
    }

    public void setEpsgCode(String epsgCode) {
        super.set(PointRepresentationKeyValue.EPSG_CODE.toString(), epsgCode);
    }

    public double getXCoordinate() {
        return (Double) super.get(
                PointRepresentationKeyValue.X_COORDINATE.toString());
    }

    public void setXCoordinate(double x) {
        super.set(PointRepresentationKeyValue.X_COORDINATE.toString(), x);
    }

    public double getYCoordinate() {
        return (Double) super.get(
                PointRepresentationKeyValue.Y_COORDINATE.toString());
    }

    public void setYCoordinate(double y) {
        super.set(PointRepresentationKeyValue.Y_COORDINATE.toString(), y);
    }
}
