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
package org.geosdi.geoplatform.response;

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
    private Boolean cached;
    private String cqlFilter;
    private String timeFilter;
    private boolean singleTileRequest;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">

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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the urlServer
     */
    public String getUrlServer() {
        return urlServer;
    }

    /**
     * @param urlServer the urlServer to set
     */
    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }

    /**
     * @return the srs
     */
    public String getSrs() {
        return srs;
    }

    /**
     * @param srs the srs to set
     */
    public void setSrs(String srs) {
        this.srs = srs;
    }

    public String getCqlFilter() {
        return cqlFilter;
    }

    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    public String getTimeFilter() {
        return timeFilter;
    }

    public void setTimeFilter(String timeFilter) {
        this.timeFilter = timeFilter;
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * @return the layerType
     */
    public GPLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType the layerType to set
     */
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
    }

    /**
     * @return the bbox
     */
    public GPBBox getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    public void setBbox(GPBBox bbox) {
        this.bbox = bbox;
    }

    /**
     * @return the cached
     */
    public Boolean isCached() {
        return cached;
    }

    /**
     * @param cached the cached to set
     */
    public void setCached(Boolean cached) {
        this.cached = cached;
    }

    public boolean isSingleTileRequest() {
        return singleTileRequest;
    }

    public void setSingleTileRequest(boolean singleTileRequest) {
        this.singleTileRequest = singleTileRequest;
    }
    //</editor-fold>

    /**
     * (non-Javadoc)
     *
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString()
                + ", title = " + title
                + ", alias = " + alias
                + ", urlServer = " + urlServer
                + ", srs = " + srs
                + ", abstractText = " + abstractText
                + ", cqlFilter = " + cqlFilter
                + ", timeFilter = " + timeFilter
                + ", layerType = " + layerType
                + ", bbox = " + bbox
                + ", cached = " + cached
                + ", singleTileRequest = " + singleTileRequest;
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
        if (layerDTO.isCached() != null) {
            layer.setCached(layerDTO.isCached());
        }
    }
}
