package com.study.factories;

import com.study.strategy.EmployeeHierarchyGraphBasedStrategy;
import com.study.strategy.EmployeeHierarchyManagementStrategy;

public class EmployeeHierarchyManagementFactory {

    public static EmployeeHierarchyManagementStrategy getEmployeeHierarchyManagementStrategy(String type) {
        if(type.equals("graph")) {
            return new EmployeeHierarchyGraphBasedStrategy();
        }
        return null;
    }
}
