/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Giris;

import Controllers.Controller;
import Controllers.HibernateUtil;
import Models.*;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@ViewScoped
public class ForgetPasswordBean extends Controller {

    private String userId;
    private String userAnswer = "";
    private String userQuestion;
    private String userPassword1 = "";
    private String userPassword2 = "";
    private boolean rendered = false;

    /**
     * Creates a new instance of ForgetPasswordController
     */
    public ForgetPasswordBean() {
    }

    @PreDestroy
    @Override
    public void destroy() {
        getSession().close();
    }

    @PostConstruct
    @Override
    public void init() {
        setSession(HibernateUtil.getSessionFactory().openSession());
    }

    public void findSecretQuestion() {
        System.out.println("User ID: " + userId);

        String kullaniciYokMesaj = "User could NOT find!";
        if (userId == null || userId.equals("")) {
            userQuestion = kullaniciYokMesaj;
            rendered = false;

            return;
        }
        UserDetails temp = getUserDetailsById(Integer.parseInt(userId));
        if (temp != null) {
            userQuestion = temp.getSecretQuestion();
            rendered = true;
        } else {
            userQuestion = kullaniciYokMesaj;
            rendered = false;

        }
    }

    private boolean checkUserAnswer(UserDetails temp) {
        if (userAnswer.equals("")) {
            return false;
        }
        if (temp.getSecretAnswer().equalsIgnoreCase(sha256(userAnswer))) {
            return true;
        }

        return false;
    }

    public void changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("changePassword() : userId : " + userId);

        if (userId == null || userId.equals("")) {
            userQuestion = "User could NOT find!";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error with finding User", ""));
            return;
        }

        UserDetails targetUserDetail = getUserDetailsById(Integer.parseInt(userId));

        if (targetUserDetail == null
                || checkUserAnswer(targetUserDetail) == false) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error with User Question", ""));

            return;
        }
        if ( userPassword1.equals("") == true || userPassword1.equals(userPassword2) == false) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords does not match !", ""));
            return;
        }
        try {
            getSession().beginTransaction();
            Users user = (Users) getSession().load(Users.class, Integer.parseInt(userId));

            user.setPassword(sha256(userPassword1));
            getSession().update(user);
            getSession().save(new Logs(Logs.USER_RESET_PASSWORD, "password changed", user, new Date()));
            getSession().getTransaction().commit();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password is not changed", ""));
            getSession().getTransaction().rollback();

        }
        context.addMessage(null, new FacesMessage("Password succesfully changed !"));
        System.out.println("PASSWORD CHANGED FOR USER : " + userId );
        System.out.println("Yeni sifre hashlenmemis : " + userPassword1);

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getUserPassword1() {
        return userPassword1;
    }

    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

    public String getUserPassword2() {
        return userPassword2;
    }

    public void setUserPassword2(String userPassword2) {
        this.userPassword2 = userPassword2;
    }

    public boolean isValidUser() {

        return true;
    }

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

}
