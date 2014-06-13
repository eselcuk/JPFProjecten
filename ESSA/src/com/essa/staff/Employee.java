/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essa.staff;

import com.essa.client.Company;
import com.essa.EmployeeContract;
import com.essa.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author zi05
 */
public class Employee extends Person {

    //private String workPlaceCompanyCode;
    private BigDecimal salary;
    private EmployeeContract empContractType;
    private Company empWorksAt;
    private float displacement;

    public Employee(String sSNumber, String firstName, String lastName, String gender,
            Date birthDate, String birthPlace, String address, long postcodeCode,
            BigDecimal salary, EmployeeContract empContractType,
            Company empWorksAt, float displacement) throws ParseException {
        super(sSNumber, firstName, lastName, gender, birthDate, birthPlace, address, postcodeCode);
        this.salary = salary;
        this.empContractType = empContractType;
        this.empWorksAt = empWorksAt;
        this.displacement = displacement;
    }

 /*
    @Override
    public int hashCode() {
        return getSSNumber().hashCode();
    }

    
    @Override
    public void setSSNumber(String SSNumber) {
        super.setSSNumber(SSNumber); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        final Employee emp = (Employee) obj;
        return this.getSSNumber().equals(emp.getSSNumber());
    }
*/
    @Override
    public String toString() {
        return super.toString() 
               + "\t" + getSalary() + "\t" + getEmpContractType() + "\t" + getEmpWorksAt() + "\t" + getDisplacement();
    }


    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public EmployeeContract getEmpContractType() {
        return empContractType;
    }

    public void setEmpContractType(EmployeeContract empContractType) {
        this.empContractType = empContractType;
    }

    public Company getEmpWorksAt() {
        return empWorksAt;
    }

    public void setEmpWorksAt(Company empWorksAt) {
        this.empWorksAt = empWorksAt;
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }


}
