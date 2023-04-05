package edu.eci.arsw.persistence;
import edu.eci.arsw.model.Clue;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

public interface DrawlearningPersistence {
    public void saveUser(User user);
    public User getUser(String name) throws DrawlearningPersistenceException;
    public Set<User> getAllUsers();
    public ArrayList<Point> getPointsByUser(String name);
    public void addPointToUser(User user);
    public  User getOrganizerName();
    public User getWinner();
    public void setWinner(String name);
    public void saveClue(Clue Clue) throws DrawlearningPersistenceException;
    public String TakeClue();
    public void deleteParticipants();
    public void deleteAllPointsUser(String name);
    
}
