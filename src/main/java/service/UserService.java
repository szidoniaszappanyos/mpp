package service;

import model.Users;
import repository.jdbcRepositoryService.UserJdbcRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UserService {
    private UserJdbcRepository initUserJdbcRepository(){
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        UserJdbcRepository userJdbcRepository = new UserJdbcRepository(serverProps);
        return userJdbcRepository;
    }

    public String login(String userName, String password){
        UserJdbcRepository userJdbcRepository = initUserJdbcRepository();
        Iterable<Users> users = userJdbcRepository.findAll();
        System.out.println(users.toString());
        for(Users user : users){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return "Successful login";
            }
            else if(user.getUserName().equals(userName) && !user.getPassword().equals(password)){
                return "Incorrect Password";
            }
        }

        return "Incorrect Username";
    }
}
