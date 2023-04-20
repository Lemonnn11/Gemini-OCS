package th.ac.mahidol.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.ScienceObserver;
import th.ac.mahidol.ict.model.User;
import th.ac.mahidol.ict.service.SciencePlanService;
import th.ac.mahidol.ict.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<User>> gettAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/allAstronomer")
    public ResponseEntity<List<Astronomer>> getAllAstronomers(){
        return new ResponseEntity<>(userService.getAstronomers(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/allScienceObserver")
    public ResponseEntity<List<ScienceObserver>> getAllSciencePlans(){
        return new ResponseEntity<>(userService.getScienceObservers(), HttpStatus.OK);
    }

}
