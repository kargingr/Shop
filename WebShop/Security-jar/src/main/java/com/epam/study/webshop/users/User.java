package com.epam.study.webshop.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrey Yun on 16.02.14.
 */

@Entity
@Table(name = "SHOP.USERS")
@NamedQueries(
        {
                @NamedQuery(name = User.FIND_BY_LOGIN_PASS,
                        query = "from User u where u.login = :login and u.password = :pass"),
                @NamedQuery(name = User.FIND_BY_LOGIN_OR_EMAIL,
                        query = "from User u where u.login = :login or u.email = :email"),
                @NamedQuery(name = User.FIND_BY_LOGIN_AND_EMAIL,
                        query = "from User u where u.login = :login and u.email = :email"),
                @NamedQuery(name = User.FIND_BY_LOGIN_AND_ANSWER,
                        query = "from User u where u.login = :login and u.secretAnswer = :answer")
        }
)
public class User implements Serializable {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private String telephone;
    private String email;
    private String login;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private Set<Role> roles = new HashSet<Role>();

    public static final String FIND_BY_LOGIN_PASS = "login";
    public static final String FIND_BY_LOGIN_OR_EMAIL = "checkIfExists";
    public static final String FIND_BY_LOGIN_AND_EMAIL = "findToShowSecretQuestion";
    public static final String FIND_BY_LOGIN_AND_ANSWER = "findToRestore";

    public User() {
    }

    public User(int id, String firstName, String middleName, String lastName,
                Date dateOfBirth, String telephone, String email, String login,
                String password, String secretQuestion, String secretAnswer, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.roles = roles;
    }

    @Id
    @Column(name = "id", unique = true)
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "SHOP.USERS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "tel")
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "secret_quest", nullable = false)
    public String getSecretQuestion() {
        return secretQuestion;
    }
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    @Column(name = "secret_answer", nullable = false)
    public String getSecretAnswer() {
        return secretAnswer;
    }
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "pass")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE},
                targetEntity = Role.class)
    @JoinTable(name = "SHOP.USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!dateOfBirth.equals(user.dateOfBirth)) return false;
        if (!email.equals(user.email)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        if (middleName != null ? !middleName.equals(user.middleName) : user.middleName != null) return false;
        if (!password.equals(user.password)) return false;
        if (!roles.equals(user.roles)) return false;
        if (!secretAnswer.equals(user.secretAnswer)) return false;
        if (!secretQuestion.equals(user.secretQuestion)) return false;
        if (!telephone.equals(user.telephone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + telephone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + secretQuestion.hashCode();
        result = 31 * result + secretAnswer.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }
}
