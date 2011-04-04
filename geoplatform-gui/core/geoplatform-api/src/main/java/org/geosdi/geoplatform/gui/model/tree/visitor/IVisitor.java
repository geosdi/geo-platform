package org.geosdi.geoplatform.gui.model.tree.visitor;

import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;

public interface IVisitor {
	
	void visitRoot(AbstractRootTreeNode root);
	
	void visitFolder(AbstractFolderTreeNode folder);
	
	void visitLeaf(GPLayerBean leaf);

}
