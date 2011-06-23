/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class Scale {

        private static List<Integer> scales;

        static {
                scales = new ArrayList<Integer>();
                scales.add(200);
                scales.add(2000);
                scales.add(5000);
                scales.add(10000);
                scales.add(17000);
                scales.add(20000);
                scales.add(25000);
                scales.add(34000);
                scales.add(50000);
                scales.add(100000);
                scales.add(200000);
                scales.add(273000);
                scales.add(500000);
                scales.add(1000000);
                scales.add(2000000);
                scales.add(4000000);
                scales.add(6000000);
                scales.add(8000000);
                scales.add(10000000);
                scales.add(12000000);
                scales.add(14000000);
                scales.add(16000000);
                scales.add(18000000);
                scales.add(20000000);
        }

        public static double searchValue(double scale) {
                int j = 0;
                for (int i = 0; i < scales.size(); i++) {
                        j = i + 1;
                        if (j < scales.size()) {
                                if ((scales.get(i) <= scale) && (scale <= scales.get(j))) {
                                        double diffMax = scales.get(j) - scale;
                                        double diffMins = scale - scales.get(i);
                                        if (diffMax <= diffMins)
                                                return scales.get(j);
                                        else if (diffMins <= diffMax)
                                                return scales.get(i);
                                }
                        } else
                                return scales.get(scales.size() - 1);
                }

                return 50000;
        }

}


