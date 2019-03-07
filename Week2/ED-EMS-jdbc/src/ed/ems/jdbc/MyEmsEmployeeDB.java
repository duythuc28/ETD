/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author iOSDev
 */
public class MyEmsEmployeeDB {

    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createEmsEmployeeTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE EMS_EMPLOYEE ( "
                    + "EMPID CHAR(6), "
                    + "NAME VARCHAR(50), "
                    + "PASSWORD CHAR(64), "
                    + "EMAIL VARCHAR(50), "
                    + "PHONE VARCHAR(10), "
                    + "ADDRESS VARCHAR(50), "
                    + "SECQN VARCHAR(60), "
                    + "SECANS VARCHAR(60), "
                    + "BSBID CHAR(6), "
                    + "ACCID VARCHAR(10), "
                    + "SALARY DECIMAL(10,2), "
                    + "APPGROUP VARCHAR(15), "
                    + "ACTIVE BOOLEAN, "
                    + "MEMO VARCHAR(255), "
                    + "CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMPID))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void dropEmsEmployeeTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE EMS_EMPLOYEE");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void addRecords(ArrayList<EmsEmployee> emsEmployees) {
        emsEmployees.forEach((emsEmployee) -> {
            insertEmployee(emsEmployee);
        });
    }

    public EmsEmployee getRecord(String empid) {
        // TODO: get the record by id
        if (empid == null) {
            return null;
        } 
        Connection connection = null;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMS_EMPLOYEE WHERE empid='" + empid + "'");
            if (rs.next()) {
                EmsEmployee employee = extractEmployeeFromResultSet(rs);
                return employee;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Set<EmsEmployee> getAll() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMS_EMPLOYEE");
            Set employees = new HashSet();
            while (rs.next()) {
                EmsEmployee employee = extractEmployeeFromResultSet(rs);
                employees.add(employee);
            }
            return employees;
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return null;
    }

    public boolean createRecord(EmsEmployee emsEmployee) {

        if (emsEmployee != null) {
            // TODO: Going to do the business here
            // Check the record exist in the current database
            EmsEmployee existedEmployee = getRecord(emsEmployee.getEmpid());
            // If the record does not exist in the database
            if (existedEmployee == null) {
                insertEmployee(emsEmployee);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean updateRecord(EmsEmployee emsEmployee) {
        if (emsEmployee != null) {
            Connection cnnct = null;
            PreparedStatement pStmnt = null;
            try {
                cnnct = getConnection();
                String preQueryStatement
                        = "UPDATE EMS_EMPLOYEE SET name=?, password=?, email=?, phone=?, address=?, secqn=?, secans=?, bsbid=?, accid=?, salary=?, appgroup=?, active=?, memo=? WHERE empid=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, emsEmployee.getName());
                pStmnt.setString(2, emsEmployee.getPassword());
                pStmnt.setString(3, emsEmployee.getEmail());
                pStmnt.setString(4, emsEmployee.getPhone());
                pStmnt.setString(5, emsEmployee.getAddress());
                pStmnt.setString(6, emsEmployee.getSecqn());
                pStmnt.setString(7, emsEmployee.getSecans());
                pStmnt.setString(8, emsEmployee.getBsbid());
                pStmnt.setString(9, emsEmployee.getAccid());
                pStmnt.setDouble(10, emsEmployee.getSalary());
                pStmnt.setString(11, emsEmployee.getAppgroup());
                pStmnt.setBoolean(12, emsEmployee.isActive());
                pStmnt.setString(13, emsEmployee.getMemo());
                pStmnt.setString(14, emsEmployee.getEmpid());
                int i = pStmnt.executeUpdate();
                return i == 1;
            } catch (SQLException ex) {
                while (ex != null) {
                    ex.printStackTrace();
                    ex = ex.getNextException();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (pStmnt != null) {
                    try {
                        pStmnt.close();
                    } catch (SQLException e) {
                    }
                }
                if (cnnct != null) {
                    try {
                        cnnct.close();
                    } catch (SQLException sqlEx) {
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteRecord(String empid) {
        if (empid != null) {
            Connection connection = null;
            try {
                connection = getConnection();
                Statement statement = connection.createStatement();

                int i = statement.executeUpdate("DELETE FROM EMS_EMPLOYEE WHERE empid='" + empid + "'");
                return i == 1;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException sqlEx) {

                    }
                }
            }
        }
        return false;
    }

    private void insertEmployee(EmsEmployee emsEmployee) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO EMS_EMPLOYEE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, emsEmployee.getEmpid());
            pStmnt.setString(2, emsEmployee.getName());
            pStmnt.setString(3, emsEmployee.getPassword());
            pStmnt.setString(4, emsEmployee.getEmail());
            pStmnt.setString(5, emsEmployee.getPhone());
            pStmnt.setString(6, emsEmployee.getAddress());
            pStmnt.setString(7, emsEmployee.getSecqn());
            pStmnt.setString(8, emsEmployee.getSecans());
            pStmnt.setString(9, emsEmployee.getBsbid());
            pStmnt.setString(10, emsEmployee.getAccid());
            pStmnt.setDouble(11, emsEmployee.getSalary());
            pStmnt.setString(12, emsEmployee.getAppgroup());
            pStmnt.setBoolean(13, emsEmployee.isActive());
            pStmnt.setString(14, emsEmployee.getMemo());
            pStmnt.executeUpdate();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    private EmsEmployee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        EmsEmployee employee = new EmsEmployee();
        employee.setAccid(rs.getString("accid"));
        employee.setActive(rs.getBoolean("active"));
        employee.setAddress(rs.getString("address"));
        employee.setAppgroup(rs.getString("appgroup"));
        employee.setBsbid(rs.getString("bsbid"));
        employee.setEmail(rs.getString("email"));
        employee.setEmpid(rs.getString("empid"));
        employee.setMemo(rs.getString("memo"));
        employee.setName(rs.getString("name"));
        employee.setPassword(rs.getString("password"));
        employee.setPhone(rs.getString("phone"));
        employee.setSalary(rs.getDouble("salary"));
        employee.setSecans(rs.getString("secans"));
        employee.setSecqn(rs.getString("secqn"));
        return employee;
    }

}
