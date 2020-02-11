package com.grapholib;

public class Application {
    public static void main(String[] args) {
        Graph<String> directedGraph = Graphs.getDirectedGraph();
        directedGraph.addVertex("Mayami");
        directedGraph.addVertex("Saint-Petersburg");
        directedGraph.addVertex("Moscow");
        directedGraph.addVertex("New York");
        directedGraph.addVertex("Riga");
        directedGraph.addVertex("London");
        directedGraph.addEdge("London", "Mayami");
        directedGraph.addEdge("Mayami", "Saint-Petersburg");
        directedGraph.addEdge("Saint-Petersburg", "Moscow");
        directedGraph.addEdge("New York", "Riga");
        directedGraph.addEdge("New York", "Saint-Petersburg");
        directedGraph.addEdge("Riga", "Moscow");

        System.out.println("City graph");
        System.out.println(directedGraph);
        System.out.println();
        System.out.println("Path from Mayami to Moscow?");
        System.out.println(directedGraph.getPath("Mayami", "Moscow"));
        System.out.println();
        System.out.println("Path from Moscow to Moscow?");
        System.out.println(directedGraph.getPath("Moscow", "Moscow"));
        System.out.println();
        System.out.println("Path from Moscow to Mayami?");
        System.out.println(directedGraph.getPath("Moscow", "Mayami"));
        System.out.println();
        System.out.println("Path from New York to Moscow?");
        System.out.println(directedGraph.getPath("New York", "Moscow"));
        System.out.println();


        Person barsik = new Person("Bars The Cat", 3);
        Person murzik = new Person("Murz The Cat", 4);
        Person snegok = new Person("Snegok The Cat", 7);
        Person riska = new Person("Riska The Kitty", 5);

        Graph<Person> undirectedGraph = Graphs.getUndirectedGraph();
        undirectedGraph.addVertex(barsik);
        undirectedGraph.addVertex(murzik);
        undirectedGraph.addVertex(snegok);
        undirectedGraph.addVertex(riska);

        undirectedGraph.addEdge(barsik, murzik);
        undirectedGraph.addEdge(barsik, snegok);
        undirectedGraph.addEdge(barsik, riska);
        undirectedGraph.addEdge(murzik, riska);

        System.out.println("Person graph");
        System.out.println(undirectedGraph);
        System.out.println();
        System.out.println("Path from snegok to barsik before riska-prudence edge");
        System.out.println(undirectedGraph.getPath(snegok, barsik));
        System.out.println();
        undirectedGraph.addEdge(riska, murzik);
        System.out.println("Path from snegok to barsik after riska-prudence edge");
        System.out.println(undirectedGraph.getPath(snegok, barsik));
    }

}