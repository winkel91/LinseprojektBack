package com.example.linseprojekt.customerAdministration;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.customerAdministration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Blob;
import java.util.Optional;

//alle controller klasser kører pt med samme type metoder(9. december) - se contactsController for beskrivelse af metoder
@RestController
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestParam("customerName") String customerName,
                                                  @RequestParam("customerCPR") String customerCPR,
                                                  @RequestParam("customerAddress") String customerAddress,
                                                  @RequestParam("customerPhoneNumber")String customerPhoneNumber){
        Customer customer = new Customer(customerName, customerCPR, customerAddress, customerPhoneNumber);
customerService.save(customer);
String msg = "";
        if(customerService.save(customer)!=null)  {
            msg="Oprettet kunde: " + customer.getCustomerName();
        }else {
            msg="fejl i oprettelsen af " + customer.getCustomerName();
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/editCustomerInfo")
    public ResponseEntity<Customer> editCustomer(@RequestParam("customerIdNumber")Long customerIdNumber,
                                               @RequestParam("customerName") String customerName,
                                               @RequestParam("customerCPR") String customerCPR,
                                               @RequestParam("customerAddress") String customerAddress,
                                               @RequestParam("customerPhoneNumber")String customerPhoneNumber){
        Customer newCustomer = new Customer(customerName,customerCPR,customerAddress,customerPhoneNumber);
        Optional<Customer> oldCustomer = customerService.findById(customerIdNumber);
        if (oldCustomer.isPresent()){
            newCustomer.setCustomerId(customerIdNumber);
            customerService.save(newCustomer);
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(newCustomer, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam("customerIdNumber") Long customerIdNumber){
        String msg = "";
        Optional<Customer> customer = customerService.findById(customerIdNumber);
        if(customer.isPresent()){
            customerService.deleteById(customerIdNumber);
            msg = "Kunde med kundenummer "+ customerIdNumber+" er slettet.";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            msg = "Kunde med kundenummer "+ customerIdNumber+" kunne ikke findes.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/addPhoto")
    public ResponseEntity<String> addPhoto(@RequestParam("eyePhoto") File eyephoto,
                                           @RequestParam("eyeComment") String eyeComment,
                                           @RequestParam("customerId") Long customerId){
        Customer customer = customerService.findById(customerId).get();
        customer.setEyePicture(eyephoto);
        customer.setEyeComment(eyeComment);
        customerService.save(customer);
        return new ResponseEntity<>("Billedet er gemt", HttpStatus.OK);
    }

    @GetMapping("/GetPhoto")
    public ResponseEntity<File> getPhoto(@RequestParam("customerId") Long customerId){
        Customer customer = customerService.findById(customerId).get();
        File photo = customer.getEyePicture();
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }
}
