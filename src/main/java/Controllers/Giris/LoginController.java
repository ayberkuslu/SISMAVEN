/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Giris;

import Controllers.Controller;
import Controllers.HibernateUtil;
import Models.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Ayberk
 */
@ViewScoped
@ManagedBean
public class LoginController extends Controller {

    private Integer userId;
    private String userPassword;
    private boolean authorized = false;
    Transaction tx ;

    @PreDestroy
    @Override
    public void destroy() {

        System.out.println("Controllers.Giris.LoginController.destroy()");
        getSession().close();
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("Controllers.Giris.LoginController.init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
    }

    public void tryLogin() {

        try {
            this.authorized = isCorrectUser();
            goNextPage();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isCorrectUser() {

        try {

            tx = getSession().beginTransaction();
            Users targetUser = (Users) getSession().get(Users.class , userId);
            
            FacesContext context = FacesContext.getCurrentInstance();

            if (targetUser.getStatus() == false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"User is not an active user.\nPlease contact with the School Admin.",""));
                return false;
            }

            if (targetUser.getPassword().equals(sha256(userPassword))) {
                Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap();

                sessionMap.put(CURRENT_USER, targetUser);
                if(targetUser.getType() == Users.TYPE_ADMIN){
                    
                }

                setCurrentUser(targetUser);
                getSession().save(new Logs(Logs.USER_LOGIN, "succesfully logined", targetUser, new Date()));

                tx.commit();
                return true;
            }
            context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));

        } catch (HibernateException e) {
            tx.rollback();
            System.out.println(e);
            System.out.println("<<HiberNateException \nAranan ID : " + userId + "\n Aranan sifre : " + userPassword
                    + "\n Aranan hash : " + sha256(userPassword));

        }
        return false;
    }

    private void goNextPage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
//
//        String truePage = "faces/homePage.xhtml";
//
//        String falsePage = "index";
        if (authorized == true) {
            System.out.println("Giris Basarili. Kullanici : " + userId + "\n");                      
            context.getExternalContext().redirect(PAGE_HOME);

            return;
        }

        System.out.println("Giris Basarisiz. Kullanici : " + userId + "\n");

    }

    public Integer getUserId() {

        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

}
