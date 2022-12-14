package com.study.strategy;

import java.util.*;

public class EmployeeHierarchyGraphBasedStrategy implements EmployeeHierarchyManagementStrategy {

    private Map<String, List<String>> employeeGraph;
    private LinkedHashSet<String> orderedTopLevelManagers;
    private Set<String> subordinates;

    public EmployeeHierarchyGraphBasedStrategy() {
        this.employeeGraph = new HashMap<>();
        this.orderedTopLevelManagers = new LinkedHashSet<>();
        this.subordinates = new HashSet<>();
    }
    @Override
    public void addEmployeeRelation(String manager, String reportee) {
        if(orderedTopLevelManagers.contains(reportee)) {
            orderedTopLevelManagers.remove(reportee);
        }
        subordinates.add(reportee);
        if( ! subordinates.contains(manager)) {
            orderedTopLevelManagers.add(manager);
        }
        List<String> reportees = employeeGraph.getOrDefault(manager, new ArrayList<>());
        if(reportees.size() == 0) {
            employeeGraph.put(manager, reportees);
        }
        reportees.add(reportee);
    }

    @Override
    public void display() {
        orderedTopLevelManagers.forEach(
                manager -> {
                    displayHierarchy(manager, employeeGraph, new HashMap<>(), 0);
                }
        );
    }

    private void displayHierarchy(String manager, Map<String, List<String>> employeeGraph, Map<String, Boolean> visited, int distance) {
        if(visited.getOrDefault(manager, false)) {
            return;
        }
        for(int i=0;i<distance;++i) {
            System.out.print(" ");
        }
        System.out.println(manager);
        visited.put(manager, true);

        if(employeeGraph.get(manager) != null) {
            employeeGraph.get(manager).forEach(reportee ->
                    displayHierarchy(reportee, employeeGraph, visited, distance + manager.length() + 4)
            );
        }

    }


}
