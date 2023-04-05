package edu.eci.arsw.services;
import edu.eci.arsw.model.Clue;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.model.User;
import edu.eci.arsw.persistence.DrawlearningPersistence;
import edu.eci.arsw.persistence.DrawlearningPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class DrawlearningServices {
    @Autowired
    DrawlearningPersistence dp = null;

    public void addNewUser(User user){
        dp.saveUser(user);
    }

    public Set<User> getAllUsers(){
        return dp.getAllUsers();
    }

    public User getUser(String name) throws DrawlearningPersistenceException {
        return dp.getUser(name);
    }
    public User getOrganizerName(){
        return dp.getOrganizerName();
    }

    public User getWinner(){
        return dp.getWinner();
    }

    public void setWinner(String name){
        dp.setWinner(name);
    }

    public void deleteParticipants(){dp.deleteParticipants();}

    public void addNewClue(Clue Clue) throws DrawlearningPersistenceException{
        dp.saveClue(Clue);
    }

    public String TakeClue(){
        return dp.TakeClue();
    }

    public ArrayList<Point> getPointsByUser(String name){
        return dp.getPointsByUser(name);
    }

    public void addPointToUser(User user){
        dp.addPointToUser(user);
    }

    public void deleteAllPointsUser(String name){
        dp.deleteAllPointsUser(name);
    }

    
}
