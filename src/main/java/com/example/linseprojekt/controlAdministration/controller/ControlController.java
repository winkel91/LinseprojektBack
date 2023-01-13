package com.example.linseprojekt.controlAdministration.controller;

import com.example.linseprojekt.controlAdministration.service.ControlService;
import com.example.linseprojekt.controlAdministration.model.Control;
import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.customerAdministration.service.CustomerService;
import com.example.linseprojekt.employeeAdministration.model.Employee;
import com.example.linseprojekt.employeeAdministration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

//alle controller klasser kører pt med samme type metoder(9. december) - se contactsController for beskrivelse af metoder
@RestController
public class ControlController {

    private ControlService controlService;
    private CustomerService customerService;
    private EmployeeService employeeService;

    @Autowired
    public ControlController(ControlService controlService, CustomerService customerService, EmployeeService employeeService){
        this.controlService = controlService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/createControl")
    public ResponseEntity<Control> createControl(@RequestParam("customerId") Long customerId,
                                                @RequestParam("employeeId") Long employeeId,
                                                @RequestParam("date") String date,
                                                @RequestParam("time") String time){
        String msg = "";
        Control control = new Control();
        if(customerService.findById(customerId).isPresent()){
            Customer customer = customerService.findById(customerId).get();
            control.setCustomer(customer);
        } else {
            msg = "Kunden kunne ikke findes.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(employeeService.findById(employeeId).isPresent()){
            Employee employee = employeeService.findById(employeeId).get();
            control.setEmployee(employee);
        } else {
            msg = "Medarbejderen kunne ikke findes.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        control.setTime(time);
        control.setDate(date);
        controlService.save(control);
        msg = "Kontrol er gemt.";
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/editControl")
    public ResponseEntity<String> editControl(@RequestParam("controlId") Long controlId,
                                              @RequestParam("employeeId") Long employeeId,
                                              @RequestParam("date") String date,
                                               @RequestParam("time") String time) {
        String msg = "";
        Customer customer = controlService.findById(controlId).get().getCustomer();
        Employee employee = employeeService.findById(employeeId).get();
        Control newControl = new Control(customer, employee, date, time);
        Optional<Control> oldControl = controlService.findById(controlId);
        if (oldControl.isPresent()) {
            newControl.setControlId(controlId);
            controlService.save(newControl);
            msg = "Kontrol opdateret.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg = "Der skete en fejl.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteControl")
    public ResponseEntity<String> deleteControl(@RequestParam("controlId") Long controlId){
        String msg = "";
        if(controlService.findById(controlId).isPresent()){
            controlService.deleteById(controlId);
            msg = "Kontrol slettet.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg = "Kontrol kunne ikke findes.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllControls")
    public ResponseEntity<Set<Control>> getAllControls(){
        return new ResponseEntity<>(controlService.findAll(),HttpStatus.OK);
    }

}
