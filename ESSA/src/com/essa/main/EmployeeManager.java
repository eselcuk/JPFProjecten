/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essa.main;

import com.essa.client.Company;
import com.essa.EmployeeContract;
import com.essa.util.FileAndObjectOIStream;
import com.essa.staff.Employee;
import com.essa.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author zi05
 */
public class EmployeeManager {

    private static FileAndObjectOIStream fileAndObjectOIStreamClass;
    private static String fileName;

    public static void main(String[] args) throws ParseException {
        //fileAndObjectOIStreamClass.fileObjectInput();
        SortedSet<Employee> employees = new TreeSet<>();
        fileName = "employee.ser";
        addEmployee(employees);
        //updateEmployee();
        //deleteEmployee();
        listEmployee();

        //fileAndObjectOIStreamClass.fileObjectOutput(employees, fileName );
        //fileObjectOutput(employees);
        //fileAndObjectOIStreamClass.fileObjectInput(fileName);
        //fileAndObjectOIStreamClass.fileObjectInput();
    }

    private static void addEmployee(Set<Employee> employees) throws ParseException {
        employees.add(new Employee("74041446122", "Ekber", "Selcuk", "Male", new Date(11, 4, 1974), "Istanbul", "Hermesdijkstraat 17", 1,
                BigDecimal.valueOf(20.00), EmployeeContract.SERVANT, new Company("CFC", "CFC BVBA", "Weg naar As 264", 1, "BE"), 100F));
        employees.add(new Employee("74041446123", "Selcuk", "Ekber", "Male", new Date(14, 4, 1974), "Istanbul", "Hermesdijkstraat 17", 1,
                BigDecimal.valueOf(20.00), EmployeeContract.WORKER, new Company("CFC", "Compact BVBA", "Weg naar As 264", 1, "BE"), 100F));
        employees.add(new Employee("74041446126", "Selcuk", "Ekber", "Male", new Date(14, 4, 1974), "Istanbul", "Hermesdijkstraat 17", 1,
                BigDecimal.valueOf(20.00), EmployeeContract.WORKER, new Company("CFC", "Compact BVBA", "Weg naar As 264", 1, "BE"), 100F));
        employees.add(new Employee("74041446124", "Dirx", "Belig", "Male", new Date(14, 4, 1974), "Istanbul", "Hermesdijkstraat 17", 1,
                BigDecimal.valueOf(20.00), EmployeeContract.SERVANT, new Company("CFC", "Compact BVBA", "Weg naar As 264", 1, "BE"), 100F));
        employees.add(new Employee("74041446125", "Dirx", "Belig", "Male", new Date(14, 4, 1974), "Istanbul", "Hermesdijkstraat 17", 1,
                BigDecimal.valueOf(20.00), EmployeeContract.WORKER, new Company("CFC", "Compact BVBA", "Weg naar As 264", 1, "BE"), 100F));

        fileAndObjectOIStreamClass.fileObjectOutput(employees, fileName);
    }

    private static void listEmployee() {
        fileAndObjectOIStreamClass.fileObjectInput(fileName);
    }

}
