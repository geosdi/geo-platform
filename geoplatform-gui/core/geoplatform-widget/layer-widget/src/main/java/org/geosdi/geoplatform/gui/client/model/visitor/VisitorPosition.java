package org.geosdi.geoplatform.gui.client.model.visitor;

import java.util.List;

import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import com.extjs.gxt.ui.client.data.ModelData;

public class VisitorPosition implements IVisitor {

	private int tmpIndex = -1;
	private int numberOfElements = -1;
	private GPBeanTreeModel startPosition;
	private GPBeanTreeModel endPosition;
	private GPBeanTreeModel tmpElement;

	public void fixPosition(GPBeanTreeModel changedElement,
			GPBeanTreeModel parentDestination, int newIndex) {
		GPBeanTreeModel oldParent = (GPBeanTreeModel) changedElement
				.getParent();
		int oldPosition = oldParent.getChildren().indexOf(changedElement);
		if (oldPosition < newIndex) {
			this.startPosition = this.getPrecedingElement(changedElement);
			oldParent.remove(changedElement);
			changedElement.setParent(parentDestination);
			parentDestination.insert(changedElement, newIndex);
			this.endPosition = this.getNextUnvisitedElement(this
					.findDeepestElementInNode(changedElement));
		} else if (oldPosition > newIndex) {
			this.endPosition = this.getNextUnvisitedElement(this
					.findDeepestElementInNode(changedElement));
			oldParent.remove(changedElement);
			changedElement.setParent(parentDestination);
			parentDestination.insert(changedElement, newIndex);
			this.startPosition = this.getPrecedingElement(changedElement);
		} else {
			return;
		}
		this.preorderTraversal();
		System.out.println("Modifica terminata");
	}

	private GPBeanTreeModel getPrecedingElement(GPBeanTreeModel element) {
		GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
		GPBeanTreeModel precedingElement = null;
		if (parent.indexOf(element) - 1 == -1) {
			precedingElement = parent;
		} else if (parent.indexOf(element) - 1 != -1) {
			precedingElement = this
					.findDeepestElementInNode((GPBeanTreeModel) parent
							.getChild(parent.indexOf(element) - 1));
		}
		return precedingElement;
	}

	private GPBeanTreeModel getNextUnvisitedElement(GPBeanTreeModel element) {
		GPBeanTreeModel unvisitedElement = null;
		if (!element.isLeaf()) {
			unvisitedElement = (GPBeanTreeModel) element.getChild(0);
		} else {
			unvisitedElement = this.getFollowingElement(element);
		}
		return unvisitedElement;
	}

	private GPBeanTreeModel getFollowingElement(GPBeanTreeModel element) {
		GPBeanTreeModel followingElement = null;
		GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
		int indexElement = parent.indexOf(element);
		if (parent != null && parent.getChild(indexElement + 1) != null) {
			return (GPBeanTreeModel) parent.getChild(indexElement + 1);
		} else if (parent != null && parent instanceof GPRootTreeNode) {
			return null;
		} else {
			followingElement = this.getFollowingElement(parent);
		}
		return followingElement;
	}

	private GPBeanTreeModel findDeepestElementInNode(GPBeanTreeModel node) {
		GPBeanTreeModel deepestNode = node;
		if (!node.isLeaf()) {
			GPBeanTreeModel tmpNode = (GPBeanTreeModel) node.getChildren().get(
					node.getChildren().size() - 1);
			deepestNode = this.findDeepestElementInNode(tmpNode);
		}
		return deepestNode;
	}

	private void preorderTraversal() {
		assert (this.startPosition != null) : "You need to specify a startPosition before call this method";
		this.tmpIndex = this.startPosition.getzIndex();
		this.tmpElement = this.getNextUnvisitedElement(this.startPosition);
		while (this.tmpElement != null && !this.tmpElement.equals(this.endPosition)) {
			this.tmpElement.accept(this);
			this.tmpElement = this.getNextUnvisitedElement(this.tmpElement);
		}
		this.resetVisit();
	}

	private void resetVisit() {
		this.startPosition = null;
		this.endPosition = null;
		this.tmpIndex = -1;
		this.tmpElement = null;
	}

	@Override
	public void visitRoot(AbstractRootTreeNode root) {
		if(this.numberOfElements != -1 && this.startPosition == null){
			root.setzIndex(this.numberOfElements);
			List<ModelData> childrens = root.getChildren();
			for (int i = 0; i < childrens.size(); i++) {
				((GPBeanTreeModel) childrens.get(i)).accept(this);
			}
		} else if (this.numberOfElements == -1){
			this.numberOfElements = 0;
			this.countNumberOfElements(root);
			this.tmpIndex = this.numberOfElements;
			this.visitRoot(root);
		}
	}

	private void countNumberOfElements(GPBeanTreeModel element) {
		++this.numberOfElements;
		List<ModelData> childrens = element.getChildren();
		for (int i = 0; i < childrens.size(); i++) {
			this.countNumberOfElements((GPBeanTreeModel) childrens.get(i));
		}
	}

	@Override
	public void visitFolder(AbstractFolderTreeNode folder) {
		folder.setzIndex(--this.tmpIndex);
		System.out.println("Visitor Folder set zIndex: " + this.tmpIndex
				+ " to the folder: " + folder.getLabel());
		List<ModelData> childrens = folder.getChildren();
		for (int i = 0; i < childrens.size(); i++) {
			this.tmpElement = (GPBeanTreeModel) childrens.get(i);
			this.tmpElement.accept(this);
		}
	}

	@Override
	public void visitLeaf(GPLayerBean leaf) {
		((GPBeanTreeModel) leaf).setzIndex(--this.tmpIndex);
		System.out.println("Visitor Leaf set zIndex: " + this.tmpIndex
				+ " to the leaf: " + leaf.getLabel());
	}

}
