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
package org.geosdi.geoplatform.gui.server.service.converter;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.temporal.dimension.GPTemporalDimension;
import org.geosdi.geoplatform.core.model.temporal.extent.GPTemporalExtent;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoFolder;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.*;
import org.geosdi.geoplatform.gui.model.logo.GPAttributionLogoURLBean;
import org.geosdi.geoplatform.gui.model.temporal.dimension.GPTemporalDimensionBean;
import org.geosdi.geoplatform.gui.model.temporal.extent.GPTemporalExtentBean;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.collection.TreeFolderElements;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toCollection;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.LAYER_TIME_DELIMITER;
import static org.geosdi.geoplatform.gui.shared.GPLayerType.WMS;

/**
 * Simple Class to convert Web-Services beans model in DTO beans Client.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "dtoLayerConverter")
public class DTOLayerConverter implements GPDTOLayerConverter {

    /**
     * @param folders
     * @return {@link ArrayList<GPFolderClientInfo>}
     */
    public ArrayList<GPFolderClientInfo> convertOnlyFolders(Collection<FolderDTO> folders) {
        return ((folders != null) ? folders.stream()
                .filter(Objects::nonNull)
                .map(this::convertFolderElement)
                .collect(toCollection(ArrayList::new)) : newArrayList());
    }

    /**
     * @param folderElements
     * @return {@link ArrayList<IGPFolderElements>}
     * @throws Exception
     */
    public ArrayList<IGPFolderElements> convertFolderElements(@Nonnull(when = NEVER) TreeFolderElements folderElements) throws Exception{
        checkArgument(folderElements != null, "The Parameter folderElements must not be null.");
        return folderElements.stream()
                .filter(Objects::nonNull)
                .map(this::convertElement)
                .collect(toCollection(ArrayList::new));
    }

    /**
     * @param memento
     * @return {@link GPFolder}
     * @throws Exception
     */
    public GPFolder convertMementoFolder(@Nonnull(when = NEVER) MementoFolder memento) throws Exception {
        checkArgument(memento != null, "The Parameter memento must not be null.");
        GPFolder gpFolder = new GPFolder();
        gpFolder.setId(memento.getIdBaseElement());
        gpFolder.setName(memento.getFolderName());
        gpFolder.setNumberOfDescendants(memento.getNumberOfDescendants());
        gpFolder.setPosition(memento.getzIndex());
        /*
         * TODO: Once implemented shared function you must set this property
         * gpFolder.setShared(true);
         */
        return gpFolder;
    }

    /**
     * @param clientProject
     * @return {@link GPProject}
     * @throws Exception
     */
    public GPProject convertToGProject(@Nonnull(when = NEVER) GPClientProject clientProject) throws Exception {
        checkArgument(clientProject != null, "The Parameter clientProject must not be null.");
        GPProject project = new GPProject();
        project.setName(clientProject.getName());
        project.setShared(clientProject.isProjectShared());
        project.setDescription(clientProject.getDescription());
        project.setInternalPublic(clientProject.isInternalPublic());
        project.setExternalPublic(clientProject.isExternalPublic());
        project.setImagePath(clientProject.getPathImage());
        return project;
    }

    /**
     * @param accountID
     * @param project
     * @return {@link AccountProjectPropertiesDTO}
     * @throws Exception
     */
    public AccountProjectPropertiesDTO convertToAccountProjectPropertiesDTO(@Nonnull(when = NEVER) Long accountID, @Nonnull(when = NEVER) GPClientProject project) throws Exception {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        checkArgument(project != null, "The Parameter project must not be null.");
        AccountProjectPropertiesDTO dto = new AccountProjectPropertiesDTO();
        dto.setAccountID(accountID);
        dto.setProjectID(project.getId());
        dto.setProjectName(project.getName());
        dto.setProjectVersion(project.getVersion());
        dto.setDefaultProject(project.isDefaultProject());
        dto.setShared(project.isProjectShared());
        dto.setInternalPublic(project.isInternalPublic());
        dto.setExternalPublic(project.isExternalPublic());
        dto.setProjectDescription(project.getDescription());
        dto.setPathImage(project.getPathImage());
        return dto;
    }

    /**
     * @param projectDTO
     * @return {@link GPClientProject}
     * @throws Exception
     */
    public GPClientProject convertToGPClientProject(@Nonnull(when = NEVER) ProjectDTO projectDTO) throws Exception {
        checkArgument(projectDTO != null, "The Parameter projectDTO must not be null.");
        GPClientProject clientProject = new GPClientProject();
        clientProject.setId(projectDTO.getId());
        clientProject.setName(projectDTO.getName());
        clientProject.setVersion(projectDTO.getVersion());
        clientProject.setNumberOfElements(projectDTO.getNumberOfElements());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        clientProject.setCreationDate(dateFormatter.format(projectDTO.getCreationDate()));
        clientProject.setDefaultProject(projectDTO.isDefaultProject());
        clientProject.setProjectShared(projectDTO.isShared());
        clientProject.setDescription(projectDTO.getDescription());
        clientProject.setPathImage(projectDTO.getImagePath());
        clientProject.setInternalPublic(projectDTO.isInternalPublic());
        clientProject.setExternalPublic(projectDTO.isExternalPublic());
        ShortAccountDTO owner = projectDTO.getOwner();
        if (owner != null && owner instanceof UserDTO) {
            clientProject.setOwner(this.convertToGPSimpleUser((UserDTO) owner));
        }
        clientProject.setRootFolders(this.convertOnlyFolders(projectDTO.getRootFolders()));
        return clientProject;
    }

    /**
     * @param projectDTO
     * @param imageURL
     * @return {@link GPClientProject}
     * @throws Exception
     */
    public GPClientProject convertToGPCLientProject(@Nonnull(when = NEVER) ProjectDTO projectDTO, @Nullable String imageURL) throws Exception {
        GPClientProject clientProject = this.convertToGPClientProject(projectDTO);
        clientProject.setImage(imageURL);
        return clientProject;
    }

    /**
     * @param shortAccountList
     * @return {@link List<GPSimpleUser>}
     * @throws Exception
     */
    public List<GPSimpleUser> convertToGPSimpleUser(@Nonnull(when = NEVER) List<ShortAccountDTO> shortAccountList) throws Exception {
        checkArgument(shortAccountList != null, "The Parameter shortAccountList must not be null.");
        return shortAccountList.stream()
                .filter(Objects::nonNull)
                .filter(v -> v instanceof UserDTO)
                .map(v -> (UserDTO) v)
                .map(this::convertToGPSimpleUser)
                .collect(Collectors.toList());
    }

    /**
     * @param element
     * @return {@link IGPFolderElements}
     */
    private IGPFolderElements convertElement(IElementDTO element) {
        IGPFolderElements folderElement = null;
        if (element instanceof RasterLayerDTO) {
            folderElement = this.convertRasterElement((RasterLayerDTO) element);
        } else if (element instanceof VectorLayerDTO) {
            folderElement = this.convertVectorElement((VectorLayerDTO) element);
        } else if (element instanceof FolderDTO) {
            folderElement = this.convertFolderElement((FolderDTO) element);
        }
        return folderElement;
    }

    /**
     * @param rasterDTO
     * @return {@link ClientRasterInfo}
     */
    private ClientRasterInfo convertRasterElement(RasterLayerDTO rasterDTO) {
        ClientRasterInfo raster = new ClientRasterInfo();
        this.convertToLayerElementFromLayerDTO(raster, rasterDTO);
        raster.setLayerType(WMS);
        raster.setOpacity(rasterDTO.getOpacity());
        raster.setMaxScale(rasterDTO.getMaxScale());
        raster.setMinScale(rasterDTO.getMinScale());
        if(rasterDTO.isTemporalLayer()) {
            GPTemporalDimension dimension = rasterDTO.getTemporalLayer().getDimension();
            if (dimension != null) {
                GPTemporalDimensionBean dimensionBean = new GPTemporalDimensionBean();
                dimensionBean.setName(dimension.getName());
                dimensionBean.setUnits(dimension.getUnits());
                raster.setDimension(dimensionBean);
            }
            GPTemporalExtent extent = rasterDTO.getTemporalLayer().getExtent();
            if (extent != null) {
                GPTemporalExtentBean extentBean = new GPTemporalExtentBean();
                extentBean.setName(extent.getName());
                extentBean.setDefaultExtent(extent.getDefaultExtent());
                extentBean.setValue(extent.getValue());
                extentBean.setRange(!extent.getValue().contains("/P"));
                raster.setExtent(extentBean);
                if (rasterDTO.getTimeFilter() != null)
                    convertTimeLayerDTO(raster, rasterDTO);
            }
        }
        if(rasterDTO.isSetAttribution()) {
            GPAttributionLogoURLBean logoURLBean = new GPAttributionLogoURLBean();
            logoURLBean.setFormat(rasterDTO.getLayerAttribution().getLogoUrl().getFormat());
            logoURLBean.setWidth(rasterDTO.getLayerAttribution().getLogoUrl().getWidth());
            logoURLBean.setHeight(rasterDTO.getLayerAttribution().getLogoUrl().getHeight());
            logoURLBean.setOnlineResource(rasterDTO.getLayerAttribution().getLogoUrl().getOnlineResource());
            raster.setLogoURLBean(logoURLBean);
        }

        ArrayList<GPStyleStringBeanModel> styles = Lists.<GPStyleStringBeanModel>newArrayList();
        GPStyleStringBeanModel style;
        for (String styleString : rasterDTO.getStyleList()) {
            style = new GPStyleStringBeanModel();
            style.setStyleString(styleString);
            styles.add(style);
        }
        raster.setStyles(styles);
        return raster;
    }

    /**
     * @param raster
     * @param layerDTO
     */
    private void convertTimeLayerDTO(ClientRasterInfo raster, RasterLayerDTO layerDTO) {
        raster.setTimeFilter(layerDTO.getTimeFilter());
        if (raster.getExtent().isRange()) {
            List<String> dimensionList = Lists.<String>newArrayList(raster.getExtent().getValue().split(","));
            String[] timeFilterSplitted = layerDTO.getTimeFilter().split("/");
            int startDimensionPosition = Integer.parseInt(timeFilterSplitted[0]);

            String variableTimeFilter = dimensionList.get(dimensionList.size() - startDimensionPosition - 1);
            if (timeFilterSplitted.length > 1) {
                int endDimensionPosition = Integer.parseInt(timeFilterSplitted[1]);
                variableTimeFilter += "/" + dimensionList.get(dimensionList.size() - endDimensionPosition - 1);
            }
            raster.setVariableTimeFilter(variableTimeFilter);
            String layerAlias;
            if (layerDTO.getAlias() != null && layerDTO.getAlias().indexOf(LAYER_TIME_DELIMITER) != -1) {
                layerAlias = layerDTO.getAlias().substring(0, layerDTO.getAlias().indexOf(LAYER_TIME_DELIMITER));
            } else {
                layerAlias = layerDTO.getTitle();
            }
            raster.setAlias(layerAlias + LAYER_TIME_DELIMITER + raster.getVariableTimeFilter() + "]");
        }
        else {
            raster.setAlias(layerDTO.getAlias());
        }
    }

    /**
     * @param vectorDTO
     * @return {@link ClientVectorInfo}
     */
    private ClientVectorInfo convertVectorElement(VectorLayerDTO vectorDTO) {
        ClientVectorInfo vector = new ClientVectorInfo();
        this.convertToLayerElementFromLayerDTO(vector, vectorDTO);
        this.setVectorLayerType(vector, vectorDTO.getLayerType());
        vector.setFeatureType(vectorDTO.getName());
        return vector;
    }

    /**
     * @param layer
     * @param layerDTO
     */
    private void convertToLayerElementFromLayerDTO(GPLayerClientInfo layer, ShortLayerDTO layerDTO) {
        layer.setAbstractText(layerDTO.getAbstractText());
        layer.setBbox(this.convertBbox(layerDTO.getBbox()));
        layer.setChecked(layerDTO.isChecked());
        layer.setCrs(layerDTO.getSrs());
        layer.setDataSource(layerDTO.getUrlServer());
        layer.setId(layerDTO.getId());
        layer.setLayerName(layerDTO.getName());
        layer.setTitle(layerDTO.getTitle());
        layer.setAlias(layerDTO.getAlias());
        layer.setCqlFilter(layerDTO.getCqlFilter());
        layer.setSingleTileRequest(layerDTO.isSingleTileRequest());
    }

    /**
     * @param folderDTO
     * @return {@link GPFolderClientInfo}
     */
    private GPFolderClientInfo convertFolderElement(FolderDTO folderDTO) {
        GPFolderClientInfo folder = new GPFolderClientInfo();
        folder.setLabel(folderDTO.getName());
        folder.setId(folderDTO.getId());
        // folder.setzIndex(folderDTO.getPosition());
        folder.setNumberOfDescendants(folderDTO.getNumberOfDescendants());
        folder.setChecked(folderDTO.isChecked());
        folder.setExpanded(folderDTO.isExpanded());
        folder.setFolderElements(this.convertFolderElements(folderDTO.getElementList()));
        return folder;
    }

    /**
     * @param gpBbox
     * @return {@link BBoxClientInfo}
     */
    private BBoxClientInfo convertBbox(GPBBox gpBbox) {
        return new BBoxClientInfo(gpBbox.getMinX(), gpBbox.getMinY(), gpBbox.getMaxX(), gpBbox.getMaxY());
    }

    /**
     * @param vector
     * @param layerType
     */
    private void setVectorLayerType(ClientVectorInfo vector, GPLayerType layerType) {
        switch (layerType) {
            case POINT:
                vector.setLayerType(GPLayerType.POINT);
                break;
            case LINESTRING:
                vector.setLayerType(GPLayerType.LINESTRING);
                break;
            case POLYGON:
                vector.setLayerType(GPLayerType.POLYGON);
                break;
            case MULTIPOINT:
                vector.setLayerType(GPLayerType.MULTIPOINT);
                break;
            case MULTILINESTRING:
                vector.setLayerType(GPLayerType.MULTILINESTRING);
                break;
            case MULTIPOLYGON:
                vector.setLayerType(GPLayerType.MULTIPOLYGON);
                break;
            default:
                System.out.println("### No Layer Type ###");
        }
    }

    /**
     * @param folderElements
     * @return {@link List<IGPFolderElements>}
     */
    private List<IGPFolderElements> convertFolderElements(List<AbstractElementDTO> folderElements) {
        return ((folderElements != null) ? folderElements.stream()
                .filter(Objects::nonNull)
                .map(this::convertElement)
                .collect(Collectors.toList()) : Lists.newArrayList());
    }

    /**
     * @param userDTO
     * @return {@link GPSimpleUser}
     */
    private GPSimpleUser convertToGPSimpleUser(UserDTO userDTO) {
        GPSimpleUser user = new GPSimpleUser();
        user.setId(userDTO.getId());
        user.setOrganization(userDTO.getOrganization());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmailAddress());
        return user;
    }
}