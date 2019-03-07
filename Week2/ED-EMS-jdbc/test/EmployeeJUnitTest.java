/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ed.ems.jdbc.EmsEmployee;
import ed.ems.jdbc.MyEmsEmployeeDB;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.MyHash;

/**
 *
 * @author iOSDev
 */
public class EmployeeJUnitTest {
    
    public EmployeeJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testCreateEmployee() {
        String memo1 = "123456";
        String pwd1 = MyHash.getSHA256HashedString(memo1);
        EmsEmployee emsEmployee1 = new EmsEmployee("000006", "Edmonds New", pwd1,
                "elau@swin.edu.au", "9876543210", "Swinburne EN510a", "What is my name?",
                "Edmonds", "123456", "12345678", 50000, "ED-EMS-ADMIN", true, memo1);
        MyEmsEmployeeDB dao = new MyEmsEmployeeDB();
        dao.createRecord(emsEmployee1);
        EmsEmployee test1 = dao.getRecord("000006");
        // Check if it has already created
        assertEquals(test1.getEmpid(), emsEmployee1.getEmpid());
     }
     
     @Test 
     public void testUpdateEmployee() {
        String memo1 = "123456";
        String pwd1 = MyHash.getSHA256HashedString(memo1);
        EmsEmployee emsEmployee1 = new EmsEmployee("000004", "Update New", pwd1,
                "elau@swin.edu.au", "9876543210", "Swinburne EN510a", "What is my name?",
                "Edmonds", "123456", "12345678", 50000, "ED-EMS-ADMIN", true, memo1);
        MyEmsEmployeeDB dao = new MyEmsEmployeeDB();
        dao.updateRecord(emsEmployee1);
        EmsEmployee test1 = dao.getRecord("000004");
        // Check if it has already update
        assertEquals(test1.getName(), "Update New");
     }
     
     @Test 
     public void testDeleteEmployee() {
        MyEmsEmployeeDB dao = new MyEmsEmployeeDB();
        dao.deleteRecord("000006");
        EmsEmployee test1 = dao.getRecord("000006");
        // Check if it has already deleted
         assertNull(test1);
     }
     
     @Test 
     public void testGetEmployeeById() {
        MyEmsEmployeeDB dao = new MyEmsEmployeeDB();
        EmsEmployee test1 = dao.getRecord("000001");
        // Check if it has already deleted
         assertNotNull(test1);
     }
     
     @Test
     public void testGetAllEmployee() {
         MyEmsEmployeeDB dao = new MyEmsEmployeeDB();
         Set set1 = dao.getAll();
         assertEquals(set1.size(), 6);
     }
}
