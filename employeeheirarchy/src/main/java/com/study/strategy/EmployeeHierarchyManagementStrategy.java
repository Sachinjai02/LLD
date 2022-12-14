package com.study.strategy;

public interface EmployeeHierarchyManagementStrategy {
    public void addEmployeeRelation(String manager, String reportee);
    public void display();
}
