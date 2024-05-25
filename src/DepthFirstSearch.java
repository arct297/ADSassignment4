

public class DepthFirstSearch<VertexDataType> extends Search<VertexDataType> {
    public DepthFirstSearch(MyGraph<VertexDataType> graph, VertexDataType source) {
        super(source);

        dfs(graph, source);
    }

    private void dfs(MyGraph<VertexDataType> graph, VertexDataType current) {
        marked.add(current);

        for (VertexDataType v : graph.adjacencyList(current)) {
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                dfs(graph, v);
            }
        }
    }
}

