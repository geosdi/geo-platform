/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 */
//@XmlRootElement
@Getter
@Setter
@ToString(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "position", "shared", "checked", "title", "alias", "urlServer", "srs",
        "abstractText", "layerType", "bbox", "cached", "cqlFilter", "timeFilter", "singleTileRequest"})
@XmlSeeAlso(value = {RasterLayerDTO.class, VectorLayerDTO.class})
public class ShortLayerDTO extends AbstractElementDTO {

    private String title;
    private String alias;
    private String urlServer;
    private String srs;
    private String abstractText;
    private GPLayerType layerType;
    private GPBBox bbox;
    private boolean cached;
    private String cqlFilter;
    private String timeFilter;
    private boolean singleTileRequest;

    /**
     * Default constructor
     */
    public ShortLayerDTO() {
        super();
    }

    /**
     * Constructor with GPLayer as arg
     */
    public ShortLayerDTO(GPLayer layer) {
        super(layer.getId(), layer.getName(), layer.getPosition(), layer.isShared(), layer.isChecked());
        this.title = layer.getTitle();
        this.alias = layer.getAlias();
        this.urlServer = layer.getUrlServer();
        this.srs = layer.getSrs();
        this.abstractText = layer.getAbstractText();
        this.layerType = layer.getLayerType();
        this.bbox = layer.getBbox();
        this.cached = layer.isCached();
        this.cqlFilter = layer.getCqlFilter();
        this.timeFilter = layer.getTimeFilter();
        this.singleTileRequest = layer.isSingleTileRequest();
    }

    /**
     * @param layers
     * @return {@link List<ShortLayerDTO>}
     */
    public static List<ShortLayerDTO> convertToShortLayerDTOList(@Nonnull(when = NEVER) List<GPLayer> layers) {
        checkArgument(layers != null, "The Parameter layers must not be null.");
        return layers.stream()
                .filter(Objects::nonNull)
                .map(ShortLayerDTO::new)
                .collect(toList());
    }

    /**
     * @param project
     * @param parent
     * @param layer
     * @param layerDTO
     */
    protected static void convertToGPLayer(GPProject project, GPFolder parent, GPLayer layer, ShortLayerDTO layerDTO) {
        layer.setProject(project);
        layer.setFolder(parent);
        // Set all properties except "id" and "shared"
        if(layerDTO != null) {
            layer.setName(layerDTO.getName());
            if (layerDTO.getPosition() != null) {
                layer.setPosition(layerDTO.getPosition());
            }
            if (layerDTO.isChecked() != null) {
                layer.setChecked(layerDTO.isChecked());
            }
            // Specific properties of a layer
            layer.setTitle(layerDTO.getTitle());
            layer.setAlias(layerDTO.getAlias());
            layer.setUrlServer(layerDTO.getUrlServer());
            layer.setSrs(layerDTO.getSrs());
            layer.setAbstractText(layerDTO.getAbstractText());
            layer.setLayerType(layerDTO.getLayerType());
            layer.setBbox(layerDTO.getBbox());
            layer.setCqlFilter(layerDTO.getCqlFilter());
            layer.setTimeFilter(layerDTO.getTimeFilter());
            layer.setSingleTileRequest(layerDTO.isSingleTileRequest());
            layer.setCached(layerDTO.isCached());
        }
    }
}
