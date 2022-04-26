package com.tlc.crm;

import com.tlc.crm.crud.api.EmployeeManager;
import com.tlc.crm.crud.model.Employee;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER=new Scanner(System.in);
    private static final EmployeeManager EMPLOYEE_MANAGER = EmployeeManager.getInstance();

    public static void start() {

        String userChoice;
        do {
            System.out.println("EmployeeManagement: \na.CREATE \nb.UPDATE \nc.DELETE \nd.SELECT");
            final String choice = SCANNER.next();

            if ("a".equalsIgnoreCase(choice)) {
                Main.create();
            } else if ("b".equalsIgnoreCase(choice)) {
                Main.update();
            } else if ("c".equalsIgnoreCase(choice)) {
                Main.delete();
            } else if ("d".equalsIgnoreCase(choice)) {
                Main.select();
            }

            System.out.println("Do you need to Continue?(Yes,No)");
            userChoice = SCANNER.next();
        } while ("yes".equalsIgnoreCase(userChoice));
    }

    private static void create() {
        System.out.println("enter the id");
        final Long id = SCANNER.nextLong();
        System.out.println("enter the name");
        final String name = SCANNER.next();
        System.out.println("enter the age");
        final String age = SCANNER.next();

        final Employee employee = new Employee(id, name, age);

         EMPLOYEE_MANAGER.create(employee);
    }

    private static void update() {
        final Long id = SCANNER.nextLong();
        final String name = SCANNER.next();
        final String age = SCANNER.next();

        final Employee employee = new Employee(id, name, age);

        EMPLOYEE_MANAGER.update(employee);
    }

    private static void delete() {
        Long id = SCANNER.nextLong();
        Employee employee = new Employee();

        employee.setId(id);
        EMPLOYEE_MANAGER.delete(employee);
    }

    private static void select() {
        Long id = SCANNER.nextLong();

        System.out.println(EMPLOYEE_MANAGER.get(id));
    }
}
