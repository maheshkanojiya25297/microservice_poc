package com.implementation.multiDb.connectivity.multiple.Db.Connectivity;

import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.exceptions.ResourceNotFoundException;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.user.User;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.userInfo.UserInfo;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.repositories.user.UserRepository;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.repositories.userInfo.UserInfoRepository;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.postgres.model.Product;
import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.postgres.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@RestController
public class MultipleDbController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    private Logger logger = LoggerFactory.getLogger(MultipleDbController.class);

    @PostMapping("/users/")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userRepository.save(user);
        logger.info("addUser: user: {} " + user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PostMapping("/products/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product product1 = productRepository.save(product);
        logger.info("addProduct : product1: {} " + product1);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }


    @PostMapping("/infousers/")
    public ResponseEntity<UserInfo> addUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo outputUserInfo = userInfoRepository.save(userInfo);
        logger.info("addUserInfo : outputUserInfo: {} " + outputUserInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(outputUserInfo);
    }


    @GetMapping("/users/")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = userRepository.findAll();
        logger.info("getAllUser: user: {} " + user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/products/")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> product = productRepository.findAll();
        logger.info("getAllProduct : product: {} " + product);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/infousers/")
    public ResponseEntity<List<UserInfo>> addAllUserInfo() {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        logger.info("getAllUserInfo : userInfos: {} " + userInfos);
        return ResponseEntity.ok(userInfos);
    }


    @GetMapping("/infousers/{id}")
    public ResponseEntity<Optional<UserInfo>> getEachUserInfo(@PathVariable("id") Integer id) throws RuntimeException, ResourceNotFoundException {
        Optional<UserInfo> userInfo = ofNullable(userInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" UserInfo with this id doesn't exist in system!")));
        logger.info("getEachUserInfo : userInfo: {} " + userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getEachUser(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Optional<User> user = ofNullable(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with this ID doesn't exists in system !")));
        logger.info("getEachUser : user: {} " + user);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getEachProduct(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Optional<Product> product = ofNullable(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with this ID doesn't exists in system!")));
        logger.info("getEachProduct : product: {} " + product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

}
