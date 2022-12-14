package com.study.controllers;

import com.study.services.EmployeeService;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addEmployeeRelation(String manager, String reportee) {
        this.employeeService.addEmployeeRelationship(manager, reportee);
    }

    public void display() {
        this.employeeService.displayHierarchy();
    }
}
