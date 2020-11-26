package common;

import org.graphstream.graph.Node;

public class NodeAttributesAccessor {
    private final Node node;

    public NodeAttributesAccessor(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public int getLevel() {
        return node.getAttribute(Attributes.LEVEL);
    }

    public NodeType getNodeType() {
        if (node.hasAttribute(Attributes.NodeType.INTERIOR)) {
            return NodeType.INTERIOR;
        }

        if (node.hasAttribute(Attributes.NodeType.REGULAR)) {
            return NodeType.REGULAR;
        }

        throw new IllegalStateException("Illegal node type");
    }

    public String getLabel() {
        return node.getAttribute(Attributes.LABEL);
    }
}
