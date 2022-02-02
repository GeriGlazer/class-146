package drones;

import drones.DB.DBmanager;
import drones.DB.DBtools;
import drones.beans.Customer;
import drones.beans.Drones;
import drones.dao.CustomerDao;
import drones.dao.DroneDao;
import drones.dbdao.Customer_mysql;
import drones.dbdao.Drone_mysql;
import drones.jobs.RepairScanner;


import java.util.*;

public class MyDroneLab {
    public static void main(String[] args) {
//        boolean allOK = DBtools.runQuery(DBmanager.CREATE_DB);
//        System.out.println("command run successfully: "+allOK);

//        boolean allOK = DBtools.runQuery(DBmanager.DROP_DB);
//        System.out.println("command run successfully: "+allOK);

        boolean allOK = DBtools.runQuery(DBmanager.CREATE_CUSTOMER_TABLE);
        System.out.println("command run successfully: "+allOK);

        allOK = DBtools.runQuery(DBmanager.CREATE_DRONES_TABLE);
        System.out.println("command run successfully: "+allOK);

        //Customer customer = new Customer(7,"Yoav Hacmon....","050-724-4372","Karnei Shomron",true,true);

        CustomerDao dbCustomer = new Customer_mysql();
        //dbCustomer.addCustomer(customer);
        //dbCustomer.updateCustomer(customer);
        //dbCustomer.deleteCustomer(6);



        //Customer talBechor = dbCustomer.getCustomerById(100);
        //System.out.println(talBechor==null?"User not found":talBechor);
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,"TLV");
        List<Customer> allCustomers = dbCustomer.getCustomers(DBmanager.GET_ALL_CITY,values);
        allCustomers.forEach(System.out::println);


        //running thread...
        Drone_mysql dronesDBdao = new Drone_mysql();
        Set<Drones> drones = dronesDBdao.getAllDrones();

        //RUNNABLE !!!!!!
        RepairScanner droneScanner = new RepairScanner(drones);
        //THREAD !!!!!
        Thread theScanner = new Thread(droneScanner);
        //START THE B****
        theScanner.start();

    }
}
















