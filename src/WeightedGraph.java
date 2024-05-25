import java.util.*;

public class WeightedGraph<VertexDataType> {
    private final boolean undirected;
    private final Map<VertexDataType, Vertex<VertexDataType>> graphVerticesMap = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(VertexDataType vertexName) {
        if (hasVertex(vertexName))
            return;

        graphVerticesMap.put(vertexName, new Vertex<>(vertexName));
    }

    //    public void addEdge(VertexDataType source, VertexDataType dest, double weight) {
//        if (!hasVertex(source))
//            addVertex(source);
//
//        if (!hasVertex(dest))
//            addVertex(dest);
//
//        if (hasEdge(source, dest)
//                || source.equals(dest))
//            return; // reject parallels & self-loops
//
//        graphVerticesMap.get(source).addAdjacentVertex(new Vertex<>(dest), weight);
//
//        if (undirected)
//            graphVerticesMap.get(dest).addAdjacentVertex(new Vertex<>(source), weight);
//    }
    public void addEdge(VertexDataType source, VertexDataType dest, double weight) {
        Vertex<VertexDataType> sourceVertex;
        Vertex<VertexDataType> destVertex;

        // Проверяем, существует ли вершина source. Если нет, создаем её.
        if (hasVertex(source)) {
            sourceVertex = graphVerticesMap.get(source);
        } else {
            sourceVertex = new Vertex<>(source);
            graphVerticesMap.put(source, sourceVertex);
        }

        if (hasVertex(dest)) {
            destVertex = graphVerticesMap.get(dest);
        } else {
            destVertex = new Vertex<>(dest);
            graphVerticesMap.put(dest, destVertex);
        }

        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (undirected) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }



    public int getVerticesCount() {
        return graphVerticesMap.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (VertexDataType v : graphVerticesMap.keySet()) {
            count += graphVerticesMap.get(v).amountOfEdges();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(VertexDataType v) {
        return graphVerticesMap.containsKey(v);
    }

    public boolean hasEdge(VertexDataType source, VertexDataType dest) {
        if (!hasVertex(source)) return false;

        return graphVerticesMap.get(source).hasConnectionTo(new Vertex<>(dest));
    }

    public List<VertexDataType> adjacencyList(VertexDataType v) {
        if (!hasVertex(v)) return null;

        return graphVerticesMap.get(v).getAdjacencyList();
    }


    public Vertex<VertexDataType> getVertex(VertexDataType data) {
        return graphVerticesMap.get(data);
    }
    public double getDistance(Vertex<VertexDataType> source, Vertex<VertexDataType> target) {
        return source.getDistance(target);
    }

}
