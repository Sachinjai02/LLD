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

        employeeController.addEmployeeRelation("Geeta", "Bibek");
        employeeController.addEmployeeRelation("AstroYogi", "Geeta");
        employeeController.addEmployeeRelation("Geeta", "Cartoonist");
        employeeController.addEmployeeRelation("Frivolous", "Indigo");
        employeeController.addEmployeeRelation("Joker", "Yogi");
        employeeController.addEmployeeRelation("AstroYogi", "Frivolous");
        employeeController.addEmployeeRelation("Twister", "Mango");
        employeeController.addEmployeeRelation("Dreadful", "KrorePati");
        employeeController.addEmployeeRelation("Yogi", "Zombie");
        employeeController.addEmployeeRelation("Yogi", "Patriot");
        employeeController.addEmployeeRelation("Patriot", "Twister");
        employeeController.addEmployeeRelation("AstroYogi", "Hikari");
        employeeController.addEmployeeRelation("Hikari", "Dreadful");
        employeeController.addEmployeeRelation("Dreadful", "Patriot");

        employeeController.display();
    }
}
