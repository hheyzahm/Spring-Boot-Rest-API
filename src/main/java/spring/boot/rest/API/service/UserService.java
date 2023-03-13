package spring.boot.rest.API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.rest.API.model.SpringBootUserModel;
import spring.boot.rest.API.model.UserModel;
import spring.boot.rest.API.repository.UserActiveRepository;
import spring.boot.rest.API.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


/*
 * The service component contains business logic.
 */
@Service
public class UserService {

    /*
     * The service component contains business logic.
     */
    /*
     * In Spring, you use @Autowired annotation for instantiating a class object.
     */
    /*
     * The @Repository annotation from the EmployeeRepository class enabled the
     * creation of a bean of this class through the @ComponentScan feature of
     * Spring. This bean is then used in the service class using @Autowired
     * annotation. This is called Dependency Injection in Spring.
     */
    @Autowired
    public UserRepository userRepository;
    @Autowired
    UserActiveRepository userActiveRepository;

    // READ
    /*
     * This function returns the list of all User details in the database.
     */
    public List<UserModel> listAllUser() {
        return userRepository.findAll();
    }

    // CREATE
    /*
     * This method calls the empRepository.save() function, which will The parameter
     * passed to this method is the User model containing all the details to save.
     */
    public void saveUser(UserModel user) {
        userRepository.save(user);
    }

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }


    // GET SINGLE USER FROM DATA WITH USER ID
    public UserModel getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    // DELETE
    /*
     * This function will delete a User record where the id in the table is equal
     * to the id passed.
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);

    }
    /*public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        else return false;

    }*/

    // UPDATE
    public UserModel updateUser(Integer userId, UserModel userDetails) {
        UserModel user = userRepository.findById(userId).get();
        user.setUserName(userDetails.getUserName());
        user.setUserEmail(userDetails.getUserEmail());
        user.setAge(userDetails.getAge());
        user.setGender(userDetails.getGender());
        user.setAddress(userDetails.getAddress());

        return userRepository.save(user);
    }

    //User Active Create

    public SpringBootUserModel createOperation(SpringBootUserModel user) {

        return userActiveRepository.save(user);
    }
    public int count()
    {
        return (int) userActiveRepository.count();
    }
    public List<SpringBootUserModel> readAllOperation() {
        return userActiveRepository.findAll();
    }

    public SpringBootUserModel readUserOperation(Integer id) {
        return userActiveRepository.findById(id).get();
    }

    // update
    public SpringBootUserModel updateOperation(Integer userID, SpringBootUserModel details){
        SpringBootUserModel user =userActiveRepository.findById(userID).get();
        user.setUserFullName(details.getUserFullName());
        user.setUserFatherName(details.getUserFatherName());
        user.setUserEmail(details.getUserEmail());
        user.setGender(details.getGender());
        user.setAge(details.getAge());
        user.setAddress(details.getAddress());
        user.setActive_user(details.isUserActive());
        return userActiveRepository.save(user);
    }
    //delete
    public void deleteOperation(Integer id){userActiveRepository.deleteById(id);
    }
    //active users
    public List<SpringBootUserModel> activeUsersOperation(Boolean check){
        List<SpringBootUserModel> all = readAllOperation();
        List<SpringBootUserModel> active=new ArrayList<SpringBootUserModel>();
        for (SpringBootUserModel user: all
        ) {
            if (user.isUserActive()==check) active.add(user);
        }
        return active;
    }
    public boolean userExistsOperation(Integer id){
        return userActiveRepository.existsById(id);
    }

}
