package th.ac.mahidol.ict.controller;

import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.mahidol.ict.service.SciencePlanService;

import java.util.List;

@RestController
@RequestMapping("/sciencePlans")
public class ScienceController {

    @Autowired
    SciencePlanService scienceService;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<SciencePlan>> gettAllSciencePlans(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(scienceService.getSciencePlans(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<SciencePlan> getSciencePlanById(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        return new ResponseEntity<>(scienceService.getSciencePlanById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/telescopeLoc")
    public ResponseEntity<List<SciencePlan.TELESCOPELOC>> getAllTelescopeLocations(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(scienceService.getTelescopeLocations(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/starSystem")
    public ResponseEntity<List<StarSystem.CONSTELLATIONS>> getAllStarSystems(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(scienceService.getStarSystems(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<List<SciencePlan>> getSciencePlanByQuery(@RequestHeader(value = "Authorization") String token, @RequestParam("query") String query) {
        return new ResponseEntity<>(scienceService.searchSciencePlans(query), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/category")
    public ResponseEntity<List<SciencePlan>> getSciencePlanByStatus(@RequestHeader(value = "Authorization") String token, @RequestParam("status") SciencePlan.STATUS status) {
        return new ResponseEntity<>(scienceService.findSciencePlansByStatus(status), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createSciencePlan(@RequestHeader(value = "Authorization") String token, @RequestBody SciencePlan sciencePlan){
        scienceService.createSciencePlan(sciencePlan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/edit")
    public ResponseEntity<HttpStatus> editSciencePlan(@RequestHeader(value = "Authorization") String token, @RequestBody SciencePlan sciencePlan){
        boolean res = scienceService.editSciencePlanByID(sciencePlan.getPlanNo(), sciencePlan);
        if(res){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteSciencePlanById(@RequestHeader(value = "Authorization") String token, @RequestParam("id") int id){
        boolean res = scienceService.deleteSciencePlanByID(id);
        if(res){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}