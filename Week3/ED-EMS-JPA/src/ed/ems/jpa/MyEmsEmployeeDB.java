/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jpa;

import entity.EmsEmployee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author iOSDev
 */
public class MyEmsEmployeeDB {

    private EntityManager em = null;

    public MyEmsEmployeeDB() {
// using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ED-EMS-entityPU");
// create an entity manage object to manage all DB synchronization
        em = emf.createEntityManager();
    }

    /**
     * @return the entity manager object to manage the DB table (sync)
     */
    public EntityManager getEntityManger() {
        return em;
    }

    /**
     * find an EMS employee using employee id (empid)
     *
     * @param empid
     * @return an EmsEmployee object if found, null if not
     */
    public EmsEmployee findEmsEmployee(String empid) {
// only works when empid is the primary key
        return em.find(EmsEmployee.class, empid);
    }

    /**
     * create an EMS employee record in the database if there is no such
     * employee
     *
     * @param emsEmployee
     * @return true if an employee record has been successfully created; false
     * otherwise
     * @throws Exception
     */
    public boolean createEmsEmployee(EmsEmployee emsEmployee) throws Exception {
        try {
            if (findEmsEmployee(emsEmployee.getEmpid()) != null) {
// emsEmployee exists already
                return false;
            }
// emsEmployee does not exist in database
            em.getTransaction().begin();
// persist an emsEmployee object in entity manager
            em.persist(emsEmployee);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * create an EMS employee record via this.createEmsEmployee()
     *
     * @param emsEmployeeDTO
     * @return true if create successful; false otherwise
     */
    public boolean createRecord(EmsEmployeeDTO emsEmployeeDTO) {
// convert a DTO to a DAO for ORM purposes
        EmsEmployee emsEmployee = this.emsEmployeeDTO2DAO(emsEmployeeDTO);
        boolean result = false;
        try {
            result = this.createEmsEmployee(emsEmployee);
        } catch (Exception ex) {
        }
        return result;
    }

    /**
     * convert an EmsEmployeeDTO object to an EmsEmployee (DAO) object
     *
     * @param emsEmployeeDTO
     * @return the relevant EmsEmployee object
     */
    private EmsEmployee emsEmployeeDTO2DAO(EmsEmployeeDTO emsEmployeeDTO) {
        EmsEmployee emsEmployee = new EmsEmployee();
        emsEmployee.setEmpid(emsEmployeeDTO.getEmpid());
        emsEmployee.setName(emsEmployeeDTO.getName());
        emsEmployee.setPassword(emsEmployeeDTO.getPassword());
        emsEmployee.setEmail(emsEmployeeDTO.getEmail());
        emsEmployee.setPhone(emsEmployeeDTO.getPhone());
        emsEmployee.setAddress(emsEmployeeDTO.getAddress());
        emsEmployee.setSecqn(emsEmployeeDTO.getSecqn());
        emsEmployee.setSecans(emsEmployeeDTO.getSecans());
        emsEmployee.setBsbid(emsEmployeeDTO.getBsbid());
        emsEmployee.setAccid(emsEmployeeDTO.getAccid());
        emsEmployee.setSalary(emsEmployeeDTO.getSalary());
        emsEmployee.setAppgroup(emsEmployeeDTO.getAppgroup());
        emsEmployee.setActive(emsEmployeeDTO.getActive());
        emsEmployee.setMemo(emsEmployeeDTO.getMemo());
        return emsEmployee;
    }
}
