package org.fp024.jpaquick.shopping.web.controller.customer;

import org.fp024.jpaquick.shopping.biz.domain.Order;
import org.fp024.jpaquick.shopping.biz.domain.OrderStatus;
import org.fp024.jpaquick.shopping.biz.service.CustomerService;
import org.fp024.jpaquick.shopping.biz.service.OrderService;
import org.fp024.jpaquick.shopping.biz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class OrderController {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // 주문 등록 화면으로 이동
    @GetMapping("/order/new")
    public String insertOrder(Model model) {
        model.addAttribute("customerList", customerService.getCustomerList());
        model.addAttribute("productList",productService.getProductList());
        return "order/insertOrder";
    }

    // 주문 등록 기능 처리
    @PostMapping("/order/new")
    public String insertOrder(Long customerId, Long productId, int count) {
        orderService.insertOrder(customerId, productId, count);
        return "redirect:/getOrderList";
    }

    // 주문 목록 검색 기능 처리
    @RequestMapping("/getOrderList")
    public String getOrderList(@ModelAttribute("searchInfo") Order order, Model model) {
        List<Order> orderList = orderService.getOrderList(order);
        model.addAttribute("orderList", orderList);
        return "order/getOrderList";
    }

    // 주문 취소 처리
    @GetMapping("/order/{orderId}/orderCancel")
    public String orderCancel(@PathVariable("orderId") Long orderId) {
        orderService.orderCancel(orderId);
        return "redirect:/getOrderList";
    }


    @ModelAttribute("orderStatusList")
    public List<OrderStatus> orderStatusList() {
        return Arrays.asList(OrderStatus.values());
    }

}
