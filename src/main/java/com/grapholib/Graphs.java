package com.grapholib;


public class Graphs {

    private Graphs() {
    }

    public static <V> Graph<V> getUndirectedGraph() {
        return new Graph<>(UndirectedEdge::new);
    }

    public static <V> Graph<V> getDirectedGraph() {
        return new Graph<>(DirectedEdge::new);
    }
}
