package com.example.navrpi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BuildingNavigator {

    private String currentBuilding;
    private LinkedList<MapNode> nodes;

    public BuildingNavigator() {

    }

    public BuildingNavigator(String buildingName, LinkedList<MapNode> inputNodes) {
        currentBuilding = buildingName;
        nodes = inputNodes; // TODO: Want to change to query DB
    }

    public ArrayList<Integer> Navigate(MapNode sourceNode) {
        sourceNode.setDistance(0);

        Set<MapNode> settledNodes = new HashSet<>();
        Set<MapNode> unsettledNodes = new HashSet<>();
        unsettledNodes.add(sourceNode);

        while (unsettledNodes.size() != 0) {
            MapNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<MapNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                MapNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        return new ArrayList<>();
    }

    private static void CalculateMinimumDistance(MapNode evaluationNode, Integer edgeWeigh, MapNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<MapNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static MapNode getLowestDistanceNode(Set<MapNode> unsettledNodes) {
        MapNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (MapNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
