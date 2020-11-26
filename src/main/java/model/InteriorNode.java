package model;

import common.NodeWrapper;
import common.NodeType;
import common.StreamUtils;
import org.graphstream.graph.Element;
import org.graphstream.graph.Node;

import java.util.Optional;
import java.util.stream.Stream;

public class InteriorNode extends NodeBase {
    protected InteriorNode(TetrahedralGraph graph, Node node, String symbol) {
        super(graph, node);
    }

    public Optional<String> getParentId() {
        return StreamUtils.asStream(getNode().getNeighborNodeIterator())
                .map(NodeWrapper::new)
                .filter(x -> x.getNodeType() == NodeType.INTERIOR)
                .filter(x -> x.getLevel() == (getLevel() - 1))
                .map(NodeWrapper::getNode)
                .map(Element::getId)
                .findFirst();
    }

    public Optional<InteriorNode> getParent() {
        return getParentId().map(x -> getGraph().getInteriorNode(x));
    }

    public Optional<String> getChildId() {
        return StreamUtils.asStream(getNode().getNeighborNodeIterator())
                .map(NodeWrapper::new)
                .filter(x -> x.getNodeType() == NodeType.INTERIOR)
                .filter(x -> x.getLevel() == (getLevel() + 1))
                .map(NodeWrapper::getNode)
                .map(Element::getId)
                .findFirst();
    }

    public Optional<InteriorNode> getChild() {
        return getChildId().map(x -> getGraph().getInteriorNode(x));
    }

    public Stream<String> getSiblingsIds() {
        return StreamUtils.asStream(getNode().getNeighborNodeIterator())
                .map(NodeWrapper::new)
                .filter(x -> x.getNodeType() == NodeType.REGULAR)
                .map(NodeWrapper::getNode)
                .map(Element::getId);
    }

    public Stream<GraphNode> getSiblings() {
        return getSiblingsIds().map(x -> getGraph().getGraphNode(x));
    }
}
