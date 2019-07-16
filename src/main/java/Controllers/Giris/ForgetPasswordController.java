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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hp_user
 */
@ManagedBean
@RequestScoped
public class ForgetPasswordController extends Controller {

    private String userId; //  string geliyor ,int yapilmali
    private String userAnswer;
    private String userQuestion;
    private String userPassword1 = "";
    private String userPassword2 = "";

    /**
     * Creates a new instance of ForgetPasswordController
     */
    public ForgetPasswordController() {
    }
    
    @PreDestroy
    public void destroy() {
        getSession().close();
    }

    @PostConstruct
    public void init() {
        setSession(HibernateUtil.getSessionFactory().openSession());
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

    public void findSecretQuestion() {
        System.out.println("User ID: " + userId);

        String kullaniciYokMesaj = "Kullanici Bulunamadi!";
        if (userId == null || userId.equals("")) {
            userQuestion = kullaniciYokMesaj;

            return;
        }
        UserDetails temp = getUserDetailsById(Integer.parseInt(userId));
        if (temp != null) {
            userQuestion = temp.getSecretQuestion();
        } else {
            userQuestion = kullaniciYokMesaj;
        }
    }

    private boolean checkUserAnswer(UserDetails temp) {
        if (temp.getSecretAnswer().equalsIgnoreCase(sha256(userAnswer))) {
            return true;
        }

        return false;
    }

    public void changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("changePassword() : userId : " + userId);

        if (userId == null || userId.equals("")) {
            userQuestion = "Kullanici Bulunamadi!";
            context.addMessage(null, new FacesMessage("Hata!!"));
            return;
        }

        UserDetails targetUserDetail = getUserDetailsById(Integer.parseInt(userId));
//        Users targetUser = getUserById(Integer.parseInt(userId));
        System.out.println("@@@ HASH@@@");
        System.out.println(sha256(userAnswer));
        System.out.println("@@@ HASH@@@");

        if (targetUserDetail == null
                || checkUserAnswer(targetUserDetail) == false) {
            context.addMessage(null, new FacesMessage("Hata2!!"));

            return;
        }
        if (userPassword1.equals(userPassword2) == false) {
            context.addMessage(null, new FacesMessage("Parolalar uyusmuyor!"));
            return;
        }
        try{
        getSession().beginTransaction();
        Users user = (Users) getSession().load(Users.class, Integer.parseInt(userId));

        user.setPassword(sha256(userPassword1));
        getSession().update(user);
        getSession().save(new Logs(Logs.USER_RESET_PASSWORD, "password changed", user, new Date()));
        getSession().getTransaction().commit();
        }catch(Exception e){
                    context.addMessage(null, new FacesMessage("Sifre DEGISTIRILEMEDI!"));
        getSession().getTransaction().rollback();

            
        }
        context.addMessage(null, new FacesMessage("Sifre Basariyla Degistirildi!"));
        System.out.println("PASSWORD CHANGED FOR USER : " + userId + "\n New Password : " + ((Users) getUserById(Integer.parseInt(userId))).getPassword());
        System.out.println("Yeni sifre hashlenmemis : " + userPassword1);

    }
    public boolean isValidUser(){
        
        return true;
    }

}
