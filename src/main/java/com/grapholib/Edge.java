package com.grapholib;

import java.util.Optional;


public interface Edge<V> {
    boolean connects(V someSide);

    Optional<V> travel(V startPoint);
}
