package business.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.representations.users.question.Questions;

public class UserTO implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String id;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    private String username;

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String surname;

    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    private String taxcode;

    
    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    private String email;

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
private String password;

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private List<Questions> questions;

    
    
    public List<Questions> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<Questions>();
        }
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
    
    public void addQuestions(Questions q) {
        this.questions.add(q);
    }

}
