package org.geosdi.geoplatform.gui.model.tree;

import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public abstract class AbstractRootTreeNode extends GPBeanTreeModel {

	@Override
	public abstract AbstractImagePrototype getIcon();

	@Override
	public void accept(IVisitor visitor) {
		visitor.visitRoot(this);
	}

}
