/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.model;

import java.util.List;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class Scale extends GeoPlatformBeanModel {

    public enum ScaleEnum {

        SCALE("SCALE");
        private String scale;

        ScaleEnum(String scale) {
            this.scale = scale;
        }

        public String getValue() {
            return this.scale;
        }
    }

    public Scale(String scale) {
        setScale(scale);
    }

    public String getScale() {
        return get(Scale.ScaleEnum.SCALE.getValue());
    }

    /**
     * @param scale the template to set
     */
    public void setScale(String scale) {
        set(Scale.ScaleEnum.SCALE.getValue(), scale);
    }

    @Override
    public String toString() {
        return "Scale{" + getScale() + '}';
    }
    private static List<Integer> scales;

//    static {
//        scales = new ArrayList<Integer>();
//        scales.add(200);
//        scales.add(2000);
//        scales.add(5000);
//        scales.add(10000);
//        scales.add(17000);
//        scales.add(20000);
//        scales.add(25000);
//        scales.add(34000);
//        scales.add(50000);
//        scales.add(100000);
//        scales.add(200000);
//        scales.add(273000);
//        scales.add(500000);
//        scales.add(1000000);
//        scales.add(2000000);
//        scales.add(4000000);
//        scales.add(6000000);
//        scales.add(8000000);
//        scales.add(10000000);
//        scales.add(12000000);
//        scales.add(14000000);
//        scales.add(16000000);
//        scales.add(18000000);
//        scales.add(20000000);
//    }
    public static double searchValue(double scale) {
        int j = 0;
        for (int i = 0; i < scales.size(); i++) {
            j = i + 1;
            if (j < scales.size()) {
                if ((scales.get(i) <= scale) && (scale <= scales.get(j))) {
                    double diffMax = scales.get(j) - scale;
                    double diffMins = scale - scales.get(i);
                    if (diffMax <= diffMins) {
                        return scales.get(j);
                    } else if (diffMins <= diffMax) {
                        return scales.get(i);
                    }
                }
            } else {
                return scales.get(scales.size() - 1);
            }
        }

        return 50000;
    }
}
