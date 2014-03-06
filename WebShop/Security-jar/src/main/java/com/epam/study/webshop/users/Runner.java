package com.epam.study.webshop.users;

/**
 * Created by Andrey Yun on 16.02.14.
 */
public class Runner {
/*    public static void main(String[] args) throws LoginOrEmailNotUniqueException {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Users").createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery(Role.ROLE_BY_NAME);
        query.setParameter("role_name","admin");
        Role role = (Role) query.getSingleResult();
        User user = new User();
        user.setFirstName("Ivan");
        user.setMiddleName("Ivanovich");
        user.setLastName("Ivanov");
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR,1988);
        calendar.set(Calendar.MONTH,2);
        calendar.set(Calendar.DAY_OF_MONTH,10);
        java.util.Date date = calendar.getTime();
        user.setDateOfBirth(date);
        user.setEmail("aaa@aa.aa");
        user.setLogin("Ivan");
        user.setPassword(PasswordEncoder.sha2Encode("123"));
        user.setTelephone("5556688");
        user.setSecretQuestion("???");
        user.setSecretAnswer("answer");
        user.getRoles().add(role);
        Query checkExistence = entityManager.createNamedQuery(User.FIND_BY_LOGIN_OR_EMAIL);
        checkExistence.setParameter("login",user.getLogin());
        checkExistence.setParameter("email",user.getEmail());
        List<User> notUniqueUser = checkExistence.getResultList();
        if (notUniqueUser != null && notUniqueUser.size() != 0) {
            throw new LoginOrEmailNotUniqueException("aaa!");
        }
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }*/

}
