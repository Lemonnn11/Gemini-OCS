package th.ac.mahidol.ict.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.mahidol.ict.model.Astronomer;
import th.ac.mahidol.ict.model.ScienceObserver;
import th.ac.mahidol.ict.model.User;
import th.ac.mahidol.ict.repository.MyOCSRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceimpl implements UserService{

    @Autowired
    private MyOCSRepository ocs;

    @Override
    public List<User> getUsers() {
        return ocs.getAllUsers();
    }

    @Override
    public List<Astronomer> getAstronomers() {
        return ocs.getAllAstronomers();
    }

    @Override
    public List<ScienceObserver> getScienceObservers() {
        return ocs.getAllScienceObservers();
    }
}
