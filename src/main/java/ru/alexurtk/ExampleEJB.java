package ru.alexurtk;

import org.apache.commons.lang3.StringUtils;
import ru.alexurtk.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alex on 11.04.2018.
 */

@Stateless
public class ExampleEJB {
    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public boolean checkPassword(String login, String password){
        if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            return false;
        }

        UserEntity userEntity = entityManager.find(UserEntity.class, login);
        if(userEntity == null){
            return false;
        }

        if(password.equals(userEntity.getPassword())){
            return true;
        }

        return false;
    }

    public boolean createUser(String login, String password){
        if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            return false;
        }

        UserEntity userEntity = entityManager.find(UserEntity.class, login);
        if(userEntity != null){
            return false;
        }

        userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        entityManager.persist(userEntity);

        return true;
    }

    public List<UserEntity> getAllUsers(){
        Query query = entityManager.createQuery("select entity from UserEntity entity");
        return query.getResultList();
    }
}
