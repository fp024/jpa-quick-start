package org.fp024.jpaquick.shopping.web.controller.customer;

import lombok.extern.slf4j.Slf4j;
import org.fp024.jpaquick.shopping.biz.domain.Address;
import org.fp024.jpaquick.shopping.biz.domain.Customer;
import org.fp024.jpaquick.shopping.biz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 회원 등록 화면으로 이동
    @GetMapping("/customer/new")
    public String insertCustomer() {
        return "customer/insertCustomer";
    }

    // 회원 등록 기능처리
    @PostMapping("/customer/new")
    public String insertCustomer(Customer customer) {
        customerService.insertCustomer(customer);
        return "redirect:/";
    }

    // 회원 목록 조회 기능 처리
    @GetMapping("/getCustomerList")
    public String getCustomerList(Model model) {
        model.addAttribute("customerList", customerService.getCustomerList());
        return "customer/getCustomerList";
    }

}
