package com.example.navrpi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuildingNavigator {

    private String currentBuilding;
    private LinkedList<RoutingMapNode> nodes;

    private HashMap<String, ArrayList<RoutingMapNode>> shortestpaths = new HashMap<>();

    public BuildingNavigator() {

    }


    public BuildingNavigator(String buildingName, LinkedList<RoutingMapNode> inputNodes) {
        currentBuilding = buildingName;
        nodes = inputNodes;
    }

    // Runs Dijkstra's algorithm
    public void Navigate(RoutingMapNode sourceNode) {
        sourceNode.setDistance(0);

        Set<RoutingMapNode> settledNodes = new HashSet<>();
        Set<RoutingMapNode> unsettledNodes = new HashSet<>();
        unsettledNodes.add(sourceNode);

        while (unsettledNodes.size() != 0) {
            RoutingMapNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);


            for (Map.Entry<RoutingMapNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {

                RoutingMapNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }


    }

    // Calculates minimum distance from a destination node to source node
    private void CalculateMinimumDistance(RoutingMapNode evaluationNode, Integer edgeWeigh, RoutingMapNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<RoutingMapNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    // Changes distance of a node if a shorter path is found
    private static RoutingMapNode getLowestDistanceNode(Set<RoutingMapNode> unsettledNodes) {
        RoutingMapNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (RoutingMapNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
