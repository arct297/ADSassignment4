import java.util.*;

public class Vertex<VertexDataType> {
    private final VertexDataType data;
    private final Map<Vertex<VertexDataType>, Double> adjacentVertices;

    public Vertex(VertexDataType vertexName) {
        this.data = vertexName;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<VertexDataType> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public VertexDataType getData() {
        return this.data;
    }

    public boolean hasConnectionTo(Vertex<VertexDataType> destination) {
        return adjacentVertices.containsKey(destination);
    }

    public int amountOfEdges() {
        return adjacentVertices.size();
    }

    public List<VertexDataType> getAdjacencyList() {
        List<VertexDataType> vertices = new LinkedList<>();
        for (Vertex<VertexDataType> v : adjacentVertices.keySet()) {
            vertices.add(v.getData());
        }
        return vertices;
    }

    public double getDistance(Vertex<VertexDataType> target) {
        Double distance = adjacentVertices.get(target);
        if (distance == null) {
            throw new RuntimeException("Edge does not exist");
        }
        return distance;
    }
}
