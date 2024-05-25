import java.util.*;

public class MyGraph<VertexDataType> {
    private final boolean undirected;
    private final Map<VertexDataType, List<VertexDataType>> map = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(VertexDataType v) {
        if (hasVertex(v))
            return;

        map.put(v, new LinkedList<>());
    }

    public void addEdge(VertexDataType source, VertexDataType dest) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops

        map.get(source).add(dest);

        if (undirected)
            map.get(dest).add(source);
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (VertexDataType v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(VertexDataType v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(VertexDataType source, VertexDataType dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).contains(dest);
    }

    public List<VertexDataType> adjacencyList(VertexDataType v) {
        if (!hasVertex(v)) return null;

        return map.get(v);
    }
}

