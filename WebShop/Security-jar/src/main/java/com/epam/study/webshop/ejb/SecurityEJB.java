package com.epam.study.webshop.ejb;

import com.epam.study.webshop.exception.*;
import com.epam.study.webshop.users.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import com.epam.study.webshop.modeljpa.*;

/**
 * Created by Andrey Yun on 22.02.14.
 */

@Stateless
public class SecurityEJB implements SecurityLocal
{
    @EJB
    private IModelJPALocal  jpaStore;
    //@PersistenceContext(unitName = "Users")
    private EntityManager entityManager = jpaStore.getEntityManager();

    @Override
    public User checkLoginPassword(String login, String passwordHash) throws LoginPasswordIncorrectException {
        Query query = entityManager.createNamedQuery(User.FIND_BY_LOGIN_PASS);
        query.setParameter("login",login);
        query.setParameter("pass",passwordHash);
        User res = null;
        try {
            res = (User) query.getSingleResult();
        } catch (NoResultException e) {
            throw new LoginPasswordIncorrectException("Login / password incorrect!");
        }
        return res;
    }

    @Override
    public User register(User user) throws LoginOrEmailNotUniqueException {
        Query checkExistence = entityManager.createNamedQuery(User.FIND_BY_LOGIN_OR_EMAIL);
        checkExistence.setParameter("login",user.getLogin());
        checkExistence.setParameter("email",user.getEmail());
        List<User> notUniqueUser = checkExistence.getResultList();
        if (notUniqueUser != null && notUniqueUser.size() != 0) {
            throw new LoginOrEmailNotUniqueException("The login or email specified is not unique");
        } else {
            Query queryRole = entityManager.createNamedQuery(Role.ROLE_BY_NAME);
            queryRole.setParameter("role_name","user");
            Role role = (Role) queryRole.getSingleResult();
            user.getRoles().add(role);
            entityManager.persist(user);
            return user;
       }
    }

    @Override
    public String showSecretQuestion(String login, String email) throws NoLoginEmailCorrespondenceException {
        Query query = entityManager.createNamedQuery(User.FIND_BY_LOGIN_AND_EMAIL);
        query.setParameter("login", login);
        query.setParameter("email", email);
        String res = null;
        try {
            User user = (User) query.getSingleResult();
            res = user.getSecretQuestion();
        } catch (NoResultException e) {
            throw new NoLoginEmailCorrespondenceException("The data entered is invalid!");
        }
        return res;
    }

    @Override
    public boolean changePassword(String login, String answer, String newPasswordHash)
            throws IncorrectSecretAnswerException {
        Query query = entityManager.createNamedQuery(User.FIND_BY_LOGIN_AND_ANSWER);
        query.setParameter("login", login);
        query.setParameter("answer", answer);
        User user = null;
        try {
            user = (User) query.getSingleResult();
            user.setPassword(newPasswordHash);
            entityManager.merge(user);
        } catch (NoResultException e) {
            throw new IncorrectSecretAnswerException("Error occurred while changing the password!");
        }
        return true;
    }

}
