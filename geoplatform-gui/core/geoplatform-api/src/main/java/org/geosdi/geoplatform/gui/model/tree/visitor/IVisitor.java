package org.geosdi.geoplatform.gui.model.tree.visitor;

import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;

public interface IVisitor {
	
	void visitRoot(AbstractRootTreeNode root);
	
	void visitFolder(AbstractFolderTreeNode folder);

        void visitVector(GPVectorBean vector);

        void visitRaster(GPRasterBean raster);

}
