package edu.wgu.d288.controllers;

import edu.wgu.d288.services.CheckoutService;
import edu.wgu.d288.services.Purchase;
import edu.wgu.d288.services.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("http://localhost:8080/api/checkout")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})

        public class CheckoutController {

        @Autowired
        private CheckoutService checkoutService;

        @PostMapping("/purchase")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse response = checkoutService.placeOrder(purchase);
        return ResponseEntity.ok(response);
        }
        }