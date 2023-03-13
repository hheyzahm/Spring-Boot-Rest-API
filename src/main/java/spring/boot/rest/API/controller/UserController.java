package spring.boot.rest.API.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.rest.API.model.SpringBootUserModel;
import spring.boot.rest.API.model.UserModel;
import spring.boot.rest.API.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * @RequestMapping annotation on the class defines a base URL for all the REST APIs created in this controller. This base URL is followed by individual REST endpoints given to each of the controller methods.
 *
 * @RestController on the class is a combination of:
 */
@RestController
@RequestMapping("/api")
public class UserController {

    /*
     * @Controller - tells Spring Boot that this class is a controller.
     *
     * @ResponseBody - indicates that the return value of the methods inside the
     * controller will be returned as the response body for the REST API.
     *
     * EmployeeService is injected as a dependency using @Autowired annotation.
     */
    @Autowired
    UserService userService;

    /*
     *
     * value - is the endpoint. In your case, it’s /employees. Note that the
     * endpoint given in the value field is only “/employees” and not
     * “/API/employees”. Since “/api” is common for all the endpoints, this was
     * added as the base URL in the @RequestMapping annotation on the class.
     *
     * method - this is the HTTP method type represented by an enum. For the create
     * employee endpoint, the HTTP method is POST. Hence, you’ll add
     * RequestMethod.POST as its value.
     *
     * @RequestBody annotation is used to map the request body of the endpoint to
     * the method parameter. emp will contain the request JSON passed to this
     * endpoint.
     */
    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public UserModel createUser(@RequestBody UserModel user) {

        return userService.createUser(user);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<UserModel> readAllUser() {

        return userService.listAllUser();
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Integer id) {
        try {
            UserModel user = userService.getUser(id);
            return new ResponseEntity<UserModel>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/putUser/{empId}", method = RequestMethod.PUT)
    public UserModel updateUser(@PathVariable(value = "empId") Integer id, @RequestBody UserModel user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserModel user, @PathVariable Integer id) {

        ResponseEntity<?> responseEntity = null;
        try {
            UserModel userData = userService.getUser(id);
            if (userService.userRepository.existsById(id)) {
                userService.updateUser(id, user);
                responseEntity = new ResponseEntity<String>("User Successfully Updated", HttpStatus.OK);
            }

        } catch (NoSuchElementException e) {
            responseEntity = new ResponseEntity<String>("Unable to update user because there's no user exist in database springbootDB against userId = " + id, HttpStatus.NOT_FOUND);

            //return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;  /*try {
            // User existUser = userService.getUser(id);
            user.setUserID(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    }

    @RequestMapping(value = "/deleteUser/{empId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployees(@PathVariable(value = "empId") Integer id) {
        ResponseEntity<?> responseEntity = null;
        try {
            UserModel user = userService.getUser(id);
            if (userService.userRepository.existsById(id)) {
                userService.deleteUser(id);
                responseEntity = new ResponseEntity<String>("User Successfully Deleted", HttpStatus.OK);
            }

        } catch (NoSuchElementException e) {
            responseEntity = new ResponseEntity<String>("Unable to delete user because there's no user exists in database springbootDB against userId = " + id, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SpringBootUserModel createOperation(@RequestBody SpringBootUserModel user) {
        /* int next = (int) userService.count() + 1;
        user.setUserID(next);
        SpringBootUserModel u = new SpringBootUserModel(
                next, user.getUserFullName(), user.getUserFatherName(), user.getUserEmail(),
                user.getGender(), user.getAge(), user.getAddress(), user.isUserActive());*/

        return userService.createOperation(user);
    }

    @RequestMapping(value = "/readAll", method = RequestMethod.GET)
    public List<SpringBootUserModel> readAllOperation() {

        return userService.readAllOperation();
    }

    @RequestMapping(value = "/readActive/{check}", method = RequestMethod.GET)
    public List<SpringBootUserModel> readActiveAllOperation(@PathVariable(value = "check") Boolean check) {
        return userService.activeUsersOperation(check);
    }

    @RequestMapping(value = "/readUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> readUserOperation(@PathVariable(value = "userId") @RequestBody Integer userID) {
        Boolean userExists = userService.userExistsOperation(userID);
        if (userExists) {
            SpringBootUserModel user = userService.readUserOperation(userID);
            return new ResponseEntity<SpringBootUserModel>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to fetch user data because there's no user exist in database springbootDB against userId = " + userID, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updateOperation/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOperation(@PathVariable(value = "userId") Integer userID, @RequestBody SpringBootUserModel details) {
        Boolean userExists = userService.userExistsOperation(userID);
        if (userExists) {
            //SpringBootUser springBootUser = springBootUserService.readUser(userID);
            return new ResponseEntity<SpringBootUserModel>(userService.updateOperation(userID, details), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to update user because there's no user exist in database springbootDB against userId = " + userID, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deleteOperation/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOperation(@PathVariable(value = "userId") @RequestBody Integer userID) {
        Boolean userExists = userService.userExistsOperation(userID);
        if (userExists) {
            userService.deleteOperation(userID);
            return new ResponseEntity<String>("User Successfully Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unable to delete user because there's no user exist in database springbootDB against userId = " + userID, HttpStatus.NOT_FOUND);
        }
    }


}
