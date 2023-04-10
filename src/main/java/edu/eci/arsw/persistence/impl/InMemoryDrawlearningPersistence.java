package edu.eci.arsw.persistence.impl;
import edu.eci.arsw.model.Clue;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.model.User;
import edu.eci.arsw.persistence.DrawlearningPersistence;
import edu.eci.arsw.persistence.DrawlearningPersistenceException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class InMemoryDrawlearningPersistence implements DrawlearningPersistence{
    private final Map<String,User> Participants = new ConcurrentHashMap<>();

    private User OrganizerName = null;
    private Clue newClue = new Clue();

    public InMemoryDrawlearningPersistence(){
    }

    @Override
    public User getUser(String name) throws DrawlearningPersistenceException{
        Set<String> keys = Participants.keySet();
        if(!Participants.containsKey(name)){
            throw new DrawlearningPersistenceException(DrawlearningPersistenceException.NO_USER);
        }
        return Participants.get(name);
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        Set<String> keys = Participants.keySet();

        for (String name: keys){
            users.add(Participants.get(name));
        }
        return users;
    }

    @Override
    public ArrayList<Point> getPointsByUser(String name) {
        return Participants.get(name).getPoints();
    }
    @Override
    public User getOrganizerName() {
        return OrganizerName;
    }

    @Override
    public User getWinner() {
        Set<User> users = new HashSet<>();
        Set<String> keys = Participants.keySet();
        User Winner = null;

        for (String name: keys){
            if(Participants.get(name).isWinner()){
                Winner = Participants.get(name);
            }
        }
        return Winner;
    }


    @Override
    public void addPointToUser(User user) {
        Participants.get(user.getName()).addPoint(user.getPoints().get(0));
    }

    @Override
    public void deleteAllPointsUser(String name) {
        Participants.get(name).deletePoints();
    }

    @Override
    public void saveUser(User user) {
        if(!Participants.containsKey(user.getName())){
            if(user.getName().contains("Organizer")){
                OrganizerName = user;
            }else{
                Participants.put(user.getName(), user);
            }

        }
    }
    @Override
    public void saveClue(Clue Clue) throws DrawlearningPersistenceException{
        newClue = new Clue(Clue.getPista(), Clue.getTake());
    }

    @Override
    public void setWinner(String name) {
        Participants.get(name).setWinner(true);
    }

    @Override
    public void deleteParticipants() {
        Participants.clear();
    }


    @Override
    public String TakeClue(){
        synchronized (newClue){

            if (!newClue.getTake()){
                newClue.setTake(true);
                return newClue.getPista();
            }else {
                return "La pista no esta disponible!";
            }
        }
    }
}
