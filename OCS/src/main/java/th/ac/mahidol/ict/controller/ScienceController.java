package th.ac.mahidol.ict.controller;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.mahidol.ict.model.MySciencePlan;
import th.ac.mahidol.ict.service.SciencePlanService;
import th.ac.mahidol.ict.utils.ExtractJWT;

import java.util.List;

@RestController
@RequestMapping("/sciencePlans")
public class ScienceController {

    @Autowired
    SciencePlanService scienceService;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<MySciencePlan>> gettAllSciencePlans() {
        return new ResponseEntity<>(scienceService.getSciencePlans(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<MySciencePlan> getSciencePlanById(@PathVariable int id) {
        return new ResponseEntity<>(scienceService.getSciencePlanById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/telescopeLoc")
    public ResponseEntity<List<SciencePlan.TELESCOPELOC>> getAllTelescopeLocations() {
        return new ResponseEntity<>(scienceService.getTelescopeLocations(), HttpStatus.OK);
    }



    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<List<MySciencePlan>> getSciencePlanByQuery(@RequestParam("query") String query) {
        return new ResponseEntity<>(scienceService.searchSciencePlans(query), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/category")
    public ResponseEntity<List<MySciencePlan>> getSciencePlanByStatus(@RequestParam("status") SciencePlan.STATUS status) {
        return new ResponseEntity<>(scienceService.findSciencePlansByStatus(status), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createSciencePlan(@RequestHeader(value = "Authorization") String token, @RequestBody MySciencePlan mySciencePlan) throws Exception{
        String email = ExtractJWT.JWTPayloadExtract(token, "\"sub\"");
        boolean res = scienceService.createSciencePlan(mySciencePlan, email);
        if(res){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @CrossOrigin
    @PutMapping("/edit")
    public ResponseEntity<HttpStatus> editSciencePlan( @RequestBody MySciencePlan mySciencePlan){
        boolean res = scienceService.editSciencePlanByID(mySciencePlan.getPlanNo(), mySciencePlan);
        if(res){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @CrossOrigin
    @PutMapping("/submit")
    public ResponseEntity<String> submitSciencePlan(@RequestBody MySciencePlan mySciencePlan){
        return new ResponseEntity<>(scienceService.submitSciencePlan(mySciencePlan), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteSciencePlanById( @RequestParam("id") int id){
        boolean res = scienceService.deleteSciencePlanByID(id);
        if(res){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}