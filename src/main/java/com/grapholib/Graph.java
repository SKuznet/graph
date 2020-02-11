package com.grapholib;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class Graph<V> {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final Set<V> verticeSet = new HashSet<>();
    private final List<Edge<V>> edges = new ArrayList<>();

    private final BiFunction<V, V, Edge<V>> edgeFactory;

    Graph(BiFunction<V, V, Edge<V>> edgeFactory) {
        this.edgeFactory = edgeFactory;
    }

    public boolean addVertex(V vertex) {
        writeLock.lock();
        try {
            return verticeSet.add(vertex);
        } finally {
            writeLock.unlock();
        }
    }

    public void addEdge(V from, V to) {
        writeLock.lock();
        try {
            if (!verticeSet.contains(from) || !verticeSet.contains(to)) {
                throw new IllegalArgumentException("Vertices are not in this graph");
            }
            edges.add(edgeFactory.apply(from, to));
        } finally {
            writeLock.unlock();
        }
    }

    public List<Edge<V>> getPath(V from, V to) {
        readLock.lock();
        try {
            if (!verticeSet.contains(from) || !verticeSet.contains(to)) {
                throw new IllegalArgumentException("Vertices are not in this graph");
            }
            Map<V, List<Edge<V>>> lastNewPaths = new HashMap<>();
            lastNewPaths.put(from, List.of());
            Map<V, List<Edge<V>>> reachedVerticesWithPath = new HashMap<>(lastNewPaths);
            while (!lastNewPaths.isEmpty() && !reachedVerticesWithPath.containsKey(to)) {
                Map<V, List<Edge<V>>> newPaths = new HashMap<>();
                for (V lastReachedVertex : lastNewPaths.keySet()) {
                    Map<V, Edge<V>> travels = findPossibleTravels(lastReachedVertex);
                    travels.forEach((v, e) -> {
                        if (!reachedVerticesWithPath.containsKey(v)) {
                            List newFoundPath = new ArrayList<>(reachedVerticesWithPath.get(lastReachedVertex));
                            newFoundPath.add(e);
                            newPaths.put(v, newFoundPath);
                        }
                    });
                }
                lastNewPaths = newPaths;
                reachedVerticesWithPath.putAll(newPaths);
            }
            return reachedVerticesWithPath.get(to);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public String toString() {
        readLock.lock();
        try {
            return "Graph{" +
                    "verticeSet=" + verticeSet +
                    ", edges=" + edges +
                    '}';
        } finally {
            readLock.unlock();
        }
    }

    private Map<V, Edge<V>> findPossibleTravels(V from) {
        return edges.stream()
                .filter(e -> e.connects(from))
                .filter(e -> e.travel(from).isPresent())
                .collect(Collectors.toUnmodifiableMap(e -> e.travel(from).get(), e -> e));
    }
}
