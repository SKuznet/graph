package com.grapholib;

import java.util.*;

public class UndirectedEdge<V> implements Edge<V> {
    private final Set<V> vertice;

    public UndirectedEdge(V v1, V v2) {
        Set<V> set = new HashSet<V>();
        set.add(v1);
        set.add(v2);
        this.vertice = Collections.unmodifiableSet(set);
    }

    @Override
    public boolean connects(V someSide) {
        return vertice.contains(someSide);
    }

    @Override
    public Optional<V> travel(V startPoint) {
        if (!connects(startPoint)) {
            throw new IllegalArgumentException("Vertex not in this edge");
        }
        for (V vertex : vertice) {
            if (!Objects.equals(vertex, startPoint)) {
                return Optional.of(vertex);
            }
        }
        return Optional.of(startPoint);
    }

    @Override
    public String toString() {
        return "UndirectedEdge{" +
                "vertice=" + vertice +
                '}';
    }
}
