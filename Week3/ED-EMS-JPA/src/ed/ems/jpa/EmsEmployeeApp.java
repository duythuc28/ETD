/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jpa;

import java.math.BigDecimal;
import util.MyHash;

/**
 *
 * @author iOSDev
 */
public class EmsEmployeeApp {

    private MyEmsEmployeeDB mydb;

    public EmsEmployeeApp() {
        mydb = new MyEmsEmployeeDB();
    }

    public static void main(String[] args) {
        EmsEmployeeApp client = new EmsEmployeeApp();
// assuming inputs from keyboard or any GUI
        String memo1 = "678901";
        String pwd1 = MyHash.getSHA256HashedString(memo1);
        EmsEmployeeDTO emsEmployeeDTO = new EmsEmployeeDTO("000006", "Carol Danvers",
                pwd1, "cdanvers@swin.edu.au", "7654321098", "Swinburne EN515a",
                "What is my name?", "Carol", "321456", "98701234", new BigDecimal(140000.0),
                "ED-EMS-ADMIN", true, memo1);
        boolean result = client.createRecord(emsEmployeeDTO);
        client.showCreateResult(result, emsEmployeeDTO);
// assuming inputs from keyboard or any GUI
        String memo2 = "123456";
        String pwd2 = MyHash.getSHA256HashedString(memo2);
        EmsEmployeeDTO emsEmployeeDTO2 = new EmsEmployeeDTO("000001", "Man Lau",
                pwd2, "elau@swin.edu.au", "9876543210", "Swinburne EN510a",
                "What is my name?", "Man", "123456", "12345678", new BigDecimal(50000.0),
                "ED-EMS-ADMIN", true, memo2);
        result = client.createRecord(emsEmployeeDTO2);
        client.showCreateResult(result, emsEmployeeDTO2);
    }

    /**
     * show the result (successful or not) of creating a record in the database
     *
     * @param result
     * @param emsEmployeeDTO
     */
    public void showCreateResult(boolean result, EmsEmployeeDTO emsEmployeeDTO) {
        if (result) {
            System.out.println("Record with primary key " + emsEmployeeDTO.getEmpid()
                    + " has been created in the database table.");
        } else {
            System.out.println("Record with primary key " + emsEmployeeDTO.getEmpid()
                    + " could not be created in the database table!");
        }
    }

    /**
     * create an EMS employee record via MyEmsEmployeeDB.createRecord()
     *
     * @param emsEmployeeDTO
     * @return true if create successful; false otherwise
     */
    public boolean createRecord(EmsEmployeeDTO emsEmployeeDTO) {
        return mydb.createRecord(emsEmployeeDTO);
    }
}
