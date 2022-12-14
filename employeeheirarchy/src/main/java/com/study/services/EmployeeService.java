package com.study.services;

import com.study.strategy.EmployeeHierarchyManagementStrategy;


public class EmployeeService {

    private EmployeeHierarchyManagementStrategy employeeHierarchyStrategy;

    public EmployeeService(EmployeeHierarchyManagementStrategy employeeHierarchyStrategy) {
        this.employeeHierarchyStrategy = employeeHierarchyStrategy;
    }


    public void addEmployeeRelationship(String manager, String reportee) {
        this.employeeHierarchyStrategy.addEmployeeRelation(manager, reportee);
    }

    public void displayHierarchy() {
        this.employeeHierarchyStrategy.display();
    }
}
