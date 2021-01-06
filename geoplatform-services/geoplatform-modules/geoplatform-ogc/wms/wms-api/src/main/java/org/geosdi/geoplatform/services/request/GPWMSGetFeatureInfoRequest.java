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
package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

import static org.geosdi.geoplatform.services.request.WMSGetFeatureInfoResponseFormat.GEOJSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlAccessorType
public class GPWMSGetFeatureInfoRequest extends GPWMSRequest {

    private static final long serialVersionUID = 5591882782946299016L;
    //
    @NotBlank(message = "{wms_crs_not_blank}")
    @NotNull(message = "{wms_crs_not_null}")
    private String crs;
    @NotBlank(message = "{wms_width_not_blank}")
    @NotNull(message = "{wms_width_not_null}")
    private String width;
    @NotBlank(message = "{wms_height_not_blank}")
    @NotNull(message = "{wms_height_not_null}")
    private String height;
    @NotNull(message = "{wms_bbox_not_null}")
    @Valid
    private GPWMSGetFeatureInfoBoundingBox boundingBox;
    @NotNull(message = "{wms_point_not_null}")
    @Valid
    private GPWMSGetFeatureInfoPoint point;
    @NotNull(message = "{wms_get_feature_info_elements_not_null}")
    private List<@Valid GPWMSGetFeatureInfoElement> wmsFeatureInfoElements;
    private WMSGetFeatureInfoResponseFormat format = GEOJSON;
}