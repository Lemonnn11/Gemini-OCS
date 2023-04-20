package th.ac.mahidol.ict.service;

import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.ScienceObserver;
import th.ac.mahidol.ict.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    List<Astronomer> getAstronomers();

    List<ScienceObserver> getScienceObservers();
}
