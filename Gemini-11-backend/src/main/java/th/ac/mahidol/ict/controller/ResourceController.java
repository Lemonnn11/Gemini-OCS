package th.ac.mahidol.ict.controller;

import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.service.SciencePlanService;
import th.ac.mahidol.ict.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    SciencePlanService sciencePlanService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/starSystem")
    public ResponseEntity<List<StarSystem.CONSTELLATIONS>> getAllStarSystems() {
        return new ResponseEntity<>(sciencePlanService.getStarSystems(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/allAstronomer")
    public ResponseEntity<List<Astronomer>> getAllAstronomers(){
        return new ResponseEntity<>(userService.getAstronomers(), HttpStatus.OK);
    }

}
