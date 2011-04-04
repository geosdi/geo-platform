package org.geosdi.geoplatform.gui.model.tree;

import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public abstract class AbstractFolderTreeNode extends GPBeanTreeModel implements
		IFolderTreeNode {

	@Override
	public abstract boolean isChecked();

	@Override
	public abstract boolean isChildChecked(GPLayerTreeModel child);

	@Override
	public abstract AbstractImagePrototype getIcon();

	@Override
	public void accept(IVisitor visitor) {
		visitor.visitFolder(this);
	}

}
