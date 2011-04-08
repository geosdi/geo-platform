/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model.visitor;

import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientVectorInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 *
 * @author -
 */
public class VisitorModelConverter implements IVisitor {

    private VisitorModelConverter() {
    }
    private FolderTreeNode folder;

    public VisitorModelConverter(FolderTreeNode folder) {
        this.folder = folder;
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        folder.add((FolderTreeNode)folder);
    }

    @Override
    public void visitVector(GPVectorBean vector) {
        folder.add(new VectorTreeNode((ClientVectorInfo) vector));
    }

    @Override
    public void visitRaster(GPRasterBean raster) {
        folder.add(new RasterTreeNode((ClientRasterInfo) raster));
    }
}
