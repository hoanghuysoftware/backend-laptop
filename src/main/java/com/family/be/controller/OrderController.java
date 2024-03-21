package com.family.be.controller;

import com.family.be.dto.request.OrderRequest;
import com.family.be.dto.response.ResponseMessage;
import com.family.be.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAll(){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("Api get all order successfully !")
                .createAt(new Date())
                .data(orderService.getAllOrder())
                .build(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetByCustomer(@PathVariable Long id){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("Api get a order by customer ID successfully !")
                .createAt(new Date())
                .data(orderService.getOrderInfoByCustomer(id))
                .build(), HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateNewOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("APi create new order successfully !")
                .createAt(new Date())
                .data(orderService.createNewOrder(orderRequest))
                .build(), HttpStatus.CREATED);
    }
}
