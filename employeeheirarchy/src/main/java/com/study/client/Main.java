package com.study.client;

import com.study.controllers.EmployeeController;
import com.study.factories.EmployeeHierarchyManagementFactory;
import com.study.services.EmployeeService;
import com.study.strategy.EmployeeHierarchyManagementStrategy;

public class Main {
    public static void main(String[] args) {
        String strategyType = "graph";
        EmployeeHierarchyManagementStrategy strategy = EmployeeHierarchyManagementFactory.getEmployeeHierarchyManagementStrategy(strategyType);
        if(strategy == null) {
            throw new RuntimeException("No valid strategy found for type: " + strategyType);
        }
        EmployeeService employeeService = new EmployeeService(strategy);
        EmployeeController employeeController = new EmployeeController(employeeService);

        employeeController.addEmployeeRelation("G", "B");
        employeeController.addEmployeeRelation("A", "G");
        employeeController.addEmployeeRelation("G", "C");
        employeeController.addEmployeeRelation("F", "I");
        employeeController.addEmployeeRelation("J", "Y");
        employeeController.addEmployeeRelation("A", "F");
        employeeController.addEmployeeRelation("T", "M");
        employeeController.addEmployeeRelation("D", "K");
        employeeController.addEmployeeRelation("Y", "Z");
        employeeController.addEmployeeRelation("Y", "P");
        employeeController.addEmployeeRelation("P", "T");
        employeeController.addEmployeeRelation("A", "H");
        employeeController.addEmployeeRelation("H", "D");
        employeeController.addEmployeeRelation("D", "P");

        employeeController.display();
    }
}
