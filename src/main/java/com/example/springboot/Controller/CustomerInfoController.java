package com.example.springboot.Controller;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.CustomerInfo;
import com.example.springboot.Repository.CustomerInfoRepository;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Service.CustomerInfoService;
import com.example.springboot.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerInfoRepository customerInfoRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customertableinfo")
    public String listOfTheCustomerInfo(Model model) {
        List<CustomerInfo> listOfTheCustomerInfo = customerInfoRepository.findAll();
        model.addAttribute("customerinfos", customerInfoService.getAllCustomerInfo());
        return "/Customer/tableCustomerInfo";
    }

    @GetMapping("/addcustomerinfo/{customerinfo_id}")
    public String createCustomerInfoForm(Model model) {

        //Crate model attribute to bind from data
        CustomerInfo customerInfo = new CustomerInfo();
        model.addAttribute("customerinfo", customerInfo);
        return "Customer/addcustomerinfo";
    }

    @PostMapping("/saveCustomerInfo")
    public String saveCustomerInfo(@ModelAttribute("customerinfo") CustomerInfo customerInfo) {

        //Save customer to database
        customerInfoService.saveCustomerInfo(customerInfo);
        /*Customer customer = customerService.getCustomerById(customerInfo.getCustomerInfo_id());
        customer.setCustomer_id(customer.getCustomer_id());
        customer.getCustomerInfos().add(customerInfo);
        customerRepository.save(customer);*/
        return "redirect:/customertableinfo";
    }


    @GetMapping("/showTheCustomer/{customerinfo_id}")
    public String showTheCustomer(@PathVariable(value = "customerinfo_id") Long customerinfo_id, Model model) {

        //Get customer from the service
        CustomerInfo customerInfo = (CustomerInfo) customerInfoService.getCustomerInfoById(customerinfo_id);

        //Set customer as a model attribute to pre-populate the form
        model.addAttribute("customerinfo", customerInfo);
        return "/Customer/showTheCustomer";
    }

    @GetMapping("/updateCustomerInfoForm/{customerinfo_id}")
    public String updateCustomerInfoForm(@PathVariable(value = "customerinfo_id") Long customerinfo_id, Model model) {

        CustomerInfo customerInfo = (CustomerInfo) customerInfoService.getCustomerInfoById(customerinfo_id);

        model.addAttribute("customerinfo", customerInfo);
        return "/Customer/updatecustomerinfo";
    }

    @PostMapping("/updateCustomerInfo/{customerinfo_id}")
    public String updateCustomerInfo(@PathVariable(value = "customerinfo_id") Long customerinfo_id,
                                     @ModelAttribute("customerinfo") CustomerInfo customerInfo) {
        CustomerInfo existingCustomerInfo = (CustomerInfo) customerInfoService.getCustomerInfoById(customerinfo_id);
        existingCustomerInfo.setHeight(customerInfo.getHeight());
        existingCustomerInfo.setAge(customerInfo.getAge());
        existingCustomerInfo.setWeight(customerInfo.getWeight());
        existingCustomerInfo.setMuscleMass(customerInfo.getMuscleMass());
        existingCustomerInfo.setBodyFatMass(customerInfo.getBodyFatMass());
        existingCustomerInfo.setFat(customerInfo.getFat());
        customerInfoService.updateCustomerInfo(existingCustomerInfo);
        return "redirect:/customertableinfo";
    }

    /*@GetMapping("/deleteCustomerInfo/customerinfo_id}")
    public String deleteCustomerInfo(@PathVariable(value = "customerinfo_id") Long customerinfo_id){
        customerInfoService.deleteCustomerInfoById(customerinfo_id);
        return "redirect:/customertableinfo";
    }*/
}
