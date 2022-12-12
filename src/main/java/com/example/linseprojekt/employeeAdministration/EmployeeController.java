package com.example.linseprojekt.employeeAdministration;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.employeeAdministration.model.Employee;
import com.example.linseprojekt.employeeAdministration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//alle controller klasser kører pt med samme type metoder(9. december) - se contactsController for beskrivelse af metoder
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestParam("employeeName") String employeeName){
        String msg = "";
        Employee employee = new Employee(employeeName);
        employeeService.save(employee);
        if(employeeService.save(employee)!=null){
            msg = "Medarbejder "+ employee.getEmployeeName() +" er oprettet.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else{
            msg = "Fejl i oprettelsen af medarbejder "+ employee.getEmployeeName();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") Long employeeId){
        String msg = "";
        Optional<Employee> employee = employeeService.findById(employeeId);
        if(employee.isPresent()){
            employeeService.deleteById(employeeId);
            msg = "Medarbejder "+ employee.get().getEmployeeName()+". Medarbejder ID: "+employeeId+ " er slettet.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg = "Medarbejder med medarbejder ID "+ employeeId+" kunne ikke findes.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

}
