import java.util.*;

public class DijkstraSearch<VertexDataType> extends Search<VertexDataType> {
    private final Set<VertexDataType> unsettledNodes;
    private final Map<VertexDataType, Double> distances;
    private final WeightedGraph<VertexDataType> graph;

    public DijkstraSearch(WeightedGraph<VertexDataType> graph, VertexDataType source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;

        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            VertexDataType currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (VertexDataType neighbor : graph.getVertex(currentNode).getAdjacencyList()) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor);

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode); // inverted adding
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private double getDistance(VertexDataType node, VertexDataType target) {
        Vertex<VertexDataType> nodeVertex = graph.getVertex(node);
        Vertex<VertexDataType> targetVertex = graph.getVertex(target);
        return nodeVertex.getDistance(targetVertex);
    }

    private VertexDataType getVertexWithMinimumWeight(Set<VertexDataType> vertices) {
        VertexDataType minimum = null;
        for (VertexDataType vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;

                continue;
            }

            if (getShortestDistance(vertex) < getShortestDistance(minimum))
                minimum = vertex;
        }

        return minimum;
    }

    private double getShortestDistance(VertexDataType destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
