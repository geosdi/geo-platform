package org.geosdi.geoplatform.gui.model.tree.grid;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel.GPLayerKeyValue;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

import java.util.ArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPRasterTreeGridBeanModel extends GPTreeGridBeanModel<GPRasterBean> implements GPRasterBean {

    private String UUID;
    private Long id;
    private String label;
    private int zIndex;
    private boolean checked = false;
    private String title;
    private String name;
    private String alias;
    private String abstractText;
    private String dataSource;
    private String crs;
    private BBoxClientInfo bbox;
    private GPLayerType layerType;
    private ArrayList<GPStyleStringBeanModel> styles = Lists.<GPStyleStringBeanModel>newArrayList();
    private String cqlFilter;
    private String timeFilter;
    private String variableTimeFilter;
    private float opacity = 1.0f;
    private Float maxScale;
    private Float minScale;
    private boolean singleTileRequest;

    /**
     * @param theModel
     */
    public GPRasterTreeGridBeanModel(GPRasterBean theModel) {
        super(theModel);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the zIndex
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @param UUID the UUID to set
     */
    protected void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    @Override
    public final void setTitle(String title) {
        this.title = title;
        super.set(GPLayerKeyValue.TITLE.toString(), this.title);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStyles(ArrayList<GPStyleStringBeanModel> styles) {
        this.styles = styles;
    }

    @Override
    public ArrayList<GPStyleStringBeanModel> getStyles() {
        return this.styles;
    }

    /**
     * @return the alias
     */
    @Override
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    @Override
    public final void setAlias(String alias) {
        this.alias = alias;
        super.set(GPLayerKeyValue.ALIAS.toString(), this.alias);
    }

    /**
     * @return the abstractText
     */
    @Override
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    @Override
    public final void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
        super.set(GPLayerKeyValue.ABSTRACT.toString(),
                this.abstractText != null ? this.abstractText : "");
    }

    /**
     * @return the dataSource
     */
    @Override
    public String getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    @Override
    public final void setDataSource(String dataSource) {
        this.dataSource = dataSource;
        super.set(GPLayerKeyValue.SERVER.toString(), this.dataSource);
    }

    /**
     * @return the crs
     */
    @Override
    public String getCrs() {
        return crs;
    }

    /**
     * @param crs the crs to set
     */
    @Override
    public void setCrs(String crs) {
        this.crs = crs;
    }

    /**
     * @return the bbox
     */
    @Override
    public BBoxClientInfo getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    @Override
    public void setBbox(BBoxClientInfo bbox) {
        this.bbox = bbox;
    }

    /**
     * @return the layerType
     */
    @Override
    public GPLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType the layerType to set
     */
    @Override
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
    }

    @Override
    public String getCqlFilter() {
        return cqlFilter;
    }

    @Override
    public String getVariableTimeFilter() {
        return variableTimeFilter;
    }

    @Override
    public void setVariableTimeFilter(String variableTimeFilter) {
        this.variableTimeFilter = variableTimeFilter;
    }

    @Override
    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    @Override
    public String getTimeFilter() {
        return this.timeFilter;
    }

    @Override
    public void setTimeFilter(String timeFilter) {
        this.timeFilter = timeFilter;
    }

    @Override
    public boolean isSingleTileRequest() {
        return singleTileRequest;
    }

    @Override
    public final void setSingleTileRequest(boolean singleTileRequest) {
        this.singleTileRequest = singleTileRequest;
    }

    /**
     * @return the opacity
     */
    @Override
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    @Override
    public final void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the maxScale
     */
    @Override
    public Float getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    @Override
    public final void setMaxScale(Float maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * @return the minScale
     */
    @Override
    public Float getMinScale() {
        return minScale;
    }

    /**
     * @param minScale the minScale to set
     */
    @Override
    public final void setMinScale(Float minScale) {
        this.minScale = minScale;
    }
}