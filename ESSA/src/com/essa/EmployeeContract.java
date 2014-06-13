/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.essa;

/**
 *
 * @author zi05
 */
public enum EmployeeContract {

    WORKER(1), SERVANT(2);
    private int contract; //category base on salary type

    private EmployeeContract(int contract) {
        this.contract = contract;
    }

    public int getContract() {
        return contract;
    }

    public void setContract (int contract ) {
        this.contract  = contract ;
    }
}
