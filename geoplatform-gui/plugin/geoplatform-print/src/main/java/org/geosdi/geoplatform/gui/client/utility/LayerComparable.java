/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.utility;

import java.util.Comparator;
import org.geosdi.geoplatform.gui.model.GPLayerBean;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class LayerComparable implements Comparator<GPLayerBean> {

    @Override
    public int compare(GPLayerBean t, GPLayerBean t1) {
        if (t.getzIndex() <  t1.getzIndex()) {
            return -1;
        } else if (t.getzIndex() >  t1.getzIndex()) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
