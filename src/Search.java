import java.util.*;

public class Search<VertexDataType> {
    protected Set<VertexDataType> marked;
    protected Map<VertexDataType, VertexDataType> edgeTo;
    protected final VertexDataType source;

    public Search(VertexDataType source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(VertexDataType v) {
        return marked.contains(v);
    }

    public Iterable<VertexDataType> pathTo(VertexDataType v) {
        if (!hasPathTo(v)) return null;

        LinkedList<VertexDataType> ls = new LinkedList<>();
        for (VertexDataType i = v; i != source; i = edgeTo.get(i)) {
            ls.push(i); // inverted adding
        }

        ls.push(source);

        return ls;
    }
}

