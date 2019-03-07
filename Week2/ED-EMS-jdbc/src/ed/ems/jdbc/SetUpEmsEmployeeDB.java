/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jdbc;

import java.util.ArrayList;
import util.MyHash;

/**
 *
 * @author iOSDev
 */
public class SetUpEmsEmployeeDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyEmsEmployeeDB myEmsEmployeeDB = new MyEmsEmployeeDB();
        /*
* drop table first for a clean start
* may cause error if table does not exist
         */
        myEmsEmployeeDB.dropEmsEmployeeTable();
        myEmsEmployeeDB.createEmsEmployeeTable();
//        ArrayList<EmsEmployee> empList = prepareEmsEmployeeData();
//        myEmsEmployeeDB.addRecords(empList);

//        UIConsoleManager ui = new UIConsoleManager();
//        ui.showMenu();
    }

    public static ArrayList<EmsEmployee> prepareEmsEmployeeData() {
        ArrayList<EmsEmployee> empList = new ArrayList<>();
        String memo1 = "123456";
        String pwd1 = MyHash.getSHA256HashedString(memo1);
        EmsEmployee emsEmployee1 = new EmsEmployee("000001", "Edmonds Lau", pwd1,
                "elau@swin.edu.au", "9876543210", "Swinburne EN510a", "What is my name?",
                "Edmonds", "123456", "12345678", 50000, "ED-EMS-ADMIN", true, memo1);
        String memo2 = "234567";
        String pwd2 = MyHash.getSHA256HashedString(memo2);
        EmsEmployee emsEmployee2 = new EmsEmployee("000002", "James T. Kirk", pwd2,
                "jkirk@swin.edu.au", "8765432109", "Swinburne EN511a", "What is my name?",
                "James", "234567", "98765432", 90000, "ED-EMS-ADMIN", true, memo2);
        String memo3 = "345678";
        String pwd3 = MyHash.getSHA256HashedString(memo3);
        EmsEmployee emsEmployee3 = new EmsEmployee("000003", "Sheldon Cooper", pwd3,
                "scooper@swin.edu.au", "7654321098", "Swinburne EN512a", "What is my last name?",
                "Cooper", "345678", "56789012", 60000, "ED-EMS-USERS", true, memo3);
        String memo4 = "456789";
        String pwd4 = MyHash.getSHA256HashedString(memo4);
        EmsEmployee emsEmployee4 = new EmsEmployee("000004", "Harry Potter", pwd4,
                "hpotter@swin.edu.au", "6543210987", "Swinburne EN513a", "What is my last name?",
                "Potter", "012345", "67890123", 70000, "ED-EMS-USERS", true, memo4);
        String memo5 = "567890";
        String pwd5 = MyHash.getSHA256HashedString(memo5);
        EmsEmployee emsEmployee5 = new EmsEmployee("000005", "Clark Kent", pwd5,
                "ckent@swin.edu.au", "5432109876", "Swinburne EN514a", "What is my last name?",
                "Kent", "123456", "78901234", 80000, "ED-EMS-USERS", true, memo5);
        empList.add(emsEmployee1);
        empList.add(emsEmployee2);
        empList.add(emsEmployee3);
        empList.add(emsEmployee4);
        empList.add(emsEmployee5);
        return empList;
    }
}
