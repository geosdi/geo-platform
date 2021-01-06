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

import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.LayerRefreshTimeConstants;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LayerRefreshTimeValue extends GeoPlatformBeanModel {

    private static final long serialVersionUID = -1598149409078479846L;
    public final static List<LayerRefreshTimeValue> refreshTimeList =
            Lists.<LayerRefreshTimeValue>newArrayList();
//    public final static String REFRESH_TIME_KEY_LABEL = "refreshTimeKeyLabel";
    public final static String REFRESH_TIME_KEY = "refreshTimeKey";
    public final static int NO_REFRESH_VALUE = -1;
    public final static int unscribeLayerNotification = 0;

    static {
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.NO_REFRESH));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.FIVE_SECONDS));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.TEN_SECONDS));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.TWENTY_SECONDS));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.THIRTY_SECONDS));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.FORTY_FIVE_SECONDS));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.ONE_MINUTE));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.FIVE_MINUTES));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.FIFTEEN_MINUTES));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.THIRTY_MINUTES));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.ONE_HOUR));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.THREE_HOUR));
        refreshTimeList.add(new LayerRefreshTimeValue(LayerRefreshTimeEnum.SIX_HOUR));
    }

    public LayerRefreshTimeValue(LayerRefreshTimeEnum layerRefreshTime) {
        this.setLayerRefreshTime(layerRefreshTime);
    }

    public static List<LayerRefreshTimeValue> getLayerRefreshTimeList() {
        return refreshTimeList;
    }

    public final void setLayerRefreshTime(LayerRefreshTimeEnum layerRefreshTime) {
        super.set(REFRESH_TIME_KEY, layerRefreshTime);
    }

    public enum LayerRefreshTimeEnum {

        NO_REFRESH(LayerRefreshTimeConstants.INSTANCE.NO_REFRESH(), NO_REFRESH_VALUE),
        FIVE_SECONDS(LayerRefreshTimeConstants.INSTANCE.FIVE_SECONDS(), 5),
        TEN_SECONDS(LayerRefreshTimeConstants.INSTANCE.TEN_SECONDS(), 10),
        TWENTY_SECONDS(LayerRefreshTimeConstants.INSTANCE.TWENTY_SECONDS(), 20),
        THIRTY_SECONDS(LayerRefreshTimeConstants.INSTANCE.THIRTY_SECONDS(), 30),
        FORTY_FIVE_SECONDS(LayerRefreshTimeConstants.INSTANCE.FORTY_FIVE_SECONDS(), 45),
        ONE_MINUTE(LayerRefreshTimeConstants.INSTANCE.ONE_MINUTE(), 60),
        FIVE_MINUTES(LayerRefreshTimeConstants.INSTANCE.FIVE_MINUTES(), 300),
        FIFTEEN_MINUTES(LayerRefreshTimeConstants.INSTANCE.FIFTEEN_MINUTES(), 900),
        THIRTY_MINUTES(LayerRefreshTimeConstants.INSTANCE.THIRTY_MINUTES(), 1800),
        ONE_HOUR(LayerRefreshTimeConstants.INSTANCE.ONE_HOUR(), 3600),
        THREE_HOUR(LayerRefreshTimeConstants.INSTANCE.THREE_HOURS(), 10800),
        SIX_HOUR(LayerRefreshTimeConstants.INSTANCE.SIX_HOURS(), 21600);
        private String label;
        private int value;

        private LayerRefreshTimeEnum(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return this.label;
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }
}
