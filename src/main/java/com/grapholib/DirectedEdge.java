package com.grapholib;

import java.util.Objects;
import java.util.Optional;


public class DirectedEdge<T> implements Edge<T> {
    private final T from;
    private final T to;

    public DirectedEdge(T from, T to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean connects(T someSide) {
        return Objects.equals(from, someSide) || Objects.equals(to, someSide);
    }

    @Override
    public Optional<T> travel(T startPoint) {
        if (!connects(startPoint)) {
            throw new IllegalArgumentException("Vertex not in this edge");
        }
        if (Objects.equals(from, startPoint)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
