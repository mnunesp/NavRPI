package com.example.navrpi;

import java.util.HashMap;
import java.util.LinkedList;

// Class is used to encapsulate MapNode for use in routing
public class RoutingMapNode extends MapNode {

    private HashMap<RoutingMapNode, Integer> adjacentNodes = new HashMap<>();
    private LinkedList<RoutingMapNode> shortestPath = new LinkedList<>();

    RoutingMapNode(String i) {
        super(i);
    }

    RoutingMapNode(int xi, int yi, int fl, String bld, String type) {
        super(xi ,yi, fl, bld, type);

    }

    public HashMap<RoutingMapNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(RoutingMapNode node, int distance) {
        adjacentNodes.put(node, distance);
    }

    public LinkedList<RoutingMapNode> getShortestPath() {
        if (!shortestPath.contains(this)) {
            shortestPath.add(this);
        }
        return shortestPath;
    }
    public void setShortestPath(LinkedList<RoutingMapNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass()!= this.getClass()) {
            return false;
        }

        MapNode n = (MapNode) obj;

        return (super.getId().equals(n.getId()));

    }

}
