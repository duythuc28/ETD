/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jdbc;

import java.util.Iterator;
import java.util.Set;
import util.IOUtility;
import util.MyHash;

/**
 *
 * @author iOSDev
 */
public class UIConsoleManager {
    private MyEmsEmployeeDB employeeDB;
    public UIConsoleManager() {
        employeeDB = new MyEmsEmployeeDB();
    }
    
    private void addNewEmployee() {
        String empID = IOUtility.getString("Add employee ID:");
        String name = IOUtility.getString("Add employee name:");
        String memo = IOUtility.getString("Add employee password:");
        String hashPassword = MyHash.getSHA256HashedString(memo);
        String email = IOUtility.getString("Add employee email:");
        String phone = IOUtility.getString("Add employee phone:");
        String address = IOUtility.getString("Add employee address:");
        String secqn = IOUtility.getString("Add secrect question:");
        String secans = IOUtility.getString("Add secrect answer:");
        String bsbid = IOUtility.getString("Add BSB number:");
        String accid = IOUtility.getString("Add account number:");
        double salary = IOUtility.getDouble("Add Salary:");
        IOUtility.println("Select app group:");
        IOUtility.println("1. ED-EMS-ADMIN");
	IOUtility.println("2. ED-EMS-USERS");
        int appGroupIndex = IOUtility.getInt("Enter app group number:");
        String appgroup = appGroupIndex == 1 ? "ED-EMS-ADMIN" : "ED-EMS-USERS";
        boolean active = IOUtility.getInt("1. Active \n 2.Inactive\nSelect number:") == 1;
        EmsEmployee employee = new EmsEmployee(empID, name, hashPassword, email, phone, address, secqn, secans, bsbid, accid, salary, appgroup, active, memo);
        if (employeeDB.createRecord(employee)) {
            IOUtility.println("Create new employee successfully");
        } else {
            IOUtility.println("Create new employee failed");
        }
    }
    
    private void updateEmployee() {
        Set employees = employeeDB.getAll();
        for (Iterator it = employees.iterator(); it.hasNext();) {
            EmsEmployee employee = (EmsEmployee) it.next();
            IOUtility.println(employee.toString());
        }
        String empID = IOUtility.getString("Select employee ID to update:");
        EmsEmployee findEmployee = employeeDB.getRecord(empID);
        if (findEmployee != null) {
        String name = IOUtility.getString("Update employee name:");
        findEmployee.setName(name);
        String memo = IOUtility.getString("Update employee password:");
        findEmployee.setMemo(memo);
        String hashPassword = MyHash.getSHA256HashedString(memo);
        findEmployee.setPassword(hashPassword);
        String email = IOUtility.getString("Update employee email:");
        findEmployee.setEmail(email);
        String phone = IOUtility.getString("Update employee phone:");
        findEmployee.setPhone(phone);
        String address = IOUtility.getString("Update employee address:");
        findEmployee.setAddress(address);
        String secqn = IOUtility.getString("Update secrect question:");
        findEmployee.setSecqn(secqn);
        String secans = IOUtility.getString("Update secrect answer:");
        findEmployee.setSecans(secans);
        String bsbid = IOUtility.getString("Update BSB number:");
        findEmployee.setBsbid(bsbid);
        String accid = IOUtility.getString("Update account number:");
        findEmployee.setAccid(accid);
        double salary = IOUtility.getDouble("Update Salary:");
        findEmployee.setSalary(salary);
        IOUtility.println("Select app group:");
        IOUtility.println("1. ED-EMS-ADMIN");
	IOUtility.println("2. ED-EMS-USERS");
        int appGroupIndex = IOUtility.getInt("Update app group number:");
        String appgroup = appGroupIndex == 1 ? "ED-EMS-ADMIN" : "ED-EMS-USERS";
        findEmployee.setAppgroup(appgroup);
        boolean active = IOUtility.getInt("1. Active \n2. Inactive\nSelect number:") == 1;
        findEmployee.setActive(active);
        if (employeeDB.updateRecord(findEmployee)) {
            IOUtility.println("Update employee successfully");
        } else {
            IOUtility.println("Update employee failed");
        }
        
        } else {
            IOUtility.println("Incorrect employee ID");
        }
    }
    
    private void removeEmployee() {
        Set employees = employeeDB.getAll();
        for (Iterator it = employees.iterator(); it.hasNext();) {
            EmsEmployee employee = (EmsEmployee) it.next();
            IOUtility.println(employee.toString());
        }
//        IOUtility.println("Select employee ID to update:");
        String empID = IOUtility.getString("Select employee ID to remove:");
        EmsEmployee findEmployee = employeeDB.getRecord(empID);
        if (findEmployee != null) {
            employeeDB.deleteRecord(empID);
            IOUtility.println("Remove employee successfully");
        } else {
            IOUtility.println("Incorrect employee ID");
        }
    }
    
    /**
	 * Display the user interface menu
	 * 
	 * @return int returns the option user has picked
	 */
	private int menuIndex() {
		IOUtility.println("=================================================");
		IOUtility.println("|                 MENU SELECTION                |");
		IOUtility.println("=================================================");
		IOUtility.println("1. Add new employee");
		IOUtility.println("2. Update employee");
		IOUtility.println("3. Remove employee ");
		IOUtility.println("4. Exit");
		return IOUtility.getInt("Please select your number:");
	}
        
        /**
	 * Calls the appropriate UI method based on user selection.
	 */
	public void showMenu() {
		int selectiionIndex = 0;
		do {
			selectiionIndex = menuIndex();
			switch (selectiionIndex)
			{
			case 1:
				// add employee
				addNewEmployee();
				break;
			case 2: 
				// update employee
				updateEmployee();
				break;
			case 3: 
				// remove employee
				removeEmployee();
				break;
			case 4: 
				// .. remove participant
				IOUtility.println("Exit program.");
				break;
			default:
				IOUtility.println("Choice must be a value between 1 and 4.");
				break;
			}   
		} while (selectiionIndex != 4);
	}
}
