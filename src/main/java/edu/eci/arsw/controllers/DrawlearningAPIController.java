package edu.eci.arsw.controllers;
import com.google.gson.Gson;
import edu.eci.arsw.model.Clue;
import edu.eci.arsw.model.User;
import edu.eci.arsw.persistence.DrawlearningPersistenceException;
import edu.eci.arsw.services.DrawlearningServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;
@RestController
@RequestMapping(value="/drawlearning")
public class DrawlearningAPIController {
    @Autowired
    DrawlearningServices dls = null;

    @RequestMapping(path = "/OrganizerName/OrganizerName" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrganizerName() {
        User Organizer = null;
        try {
            Organizer = dls.getOrganizerName();
        } catch (Exception e) {
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(Organizer), HttpStatus.ACCEPTED);

    }

    @RequestMapping(path = "/{name}" , method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setWinner(@PathVariable String name) {
        try {
            dls.setWinner(name);
        } catch (Exception e) {
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(path= "/clean", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteParticipants() {
        try {
            dls.deleteParticipants();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(path = "/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBlueprintByAuthorName(@PathVariable String name) {
        User user = null;
        try {
            user = dls.getUser(name);
        } catch (DrawlearningPersistenceException e) {
            return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(user), HttpStatus.ACCEPTED);

    }



    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postUser(@RequestBody User user){
        try {
            dls.addNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(DrawlearningAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al adicionar un nuevo usuario",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers() {
        try {
            Gson gson = new Gson();
            return new ResponseEntity<>(gson.toJson(dls.getAllUsers()), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(DrawlearningAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error al obtener Participantes", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/Clue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClue(@RequestBody Clue Clue){
        try {
            dls.addNewClue(Clue);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex){
            Logger.getLogger(DrawlearningAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No se ha podido crear la pista", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/TakeClue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> TakeClue() {
        try {
            Gson gson = new Gson();
            return new ResponseEntity<>(gson.toJson(dls.TakeClue()), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(DrawlearningAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error al buscar a los Participants", HttpStatus.NOT_FOUND);
        }
    }
}
