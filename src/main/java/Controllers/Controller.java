/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ApplicationScoped
public class Controller implements Serializable{

//    private HibernateUtil helper;
    private Session session;
    private Users currentUser;
    private UserDetails currentUserDetail;

    /**
     * Creates a new instance of Controller
     */
    public Controller() {
    }

    @PreDestroy
    public void destroy() {
        session.close();
    }

    @PostConstruct
    public void init() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public UserDetails getCurrentUserDetail() {
        return currentUserDetail;
    }

    public void setCurrentUserDetail(UserDetails currentUserDetail) {
        this.currentUserDetail = currentUserDetail;
    }

    public Session getSession() {

        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Users getUserById(int userId) {

        try {
            Transaction transaction = session.beginTransaction();
            try {
                Users temp = (Users) getSession().get(Users.class, userId);
                return temp;

            } catch (Exception ex) {
                transaction.rollback();
                getSession().close();
                throw ex;
            }

        } finally {
            getSession().getTransaction().commit();
        }

//        return null;
    }

    public Object getObject(Class type, Serializable srzbl) {
        getSession().beginTransaction();
        Object o = getSession().get(type, srzbl);
        getSession().getTransaction().commit();

        return o;
    }

    public UserDetails getUserDetailsById(int userId) {
        try {

            getSession().beginTransaction();
            List userDetailsList = getSession().createCriteria(UserDetails.class).add(Restrictions.eq("userId.userId", userId)).list();
            getSession().getTransaction().commit();
            if (userDetailsList.size() != 1) {
                return null;
            }

            UserDetails temp = (UserDetails) userDetailsList.get(0);
            return temp;

        } catch (HibernateException e) {

            System.out.println(e + "\nExcepiton while loading UserDetail of " + userId);
            getSession().getTransaction().rollback();

        } finally {

        }

        return null;
    }

    public static String sha256(String base) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void insertObject(Object o) {
        try {
            getSession().beginTransaction();
            getSession().save(o);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            System.out.println(e + "\n InsertObjectException while inserting :" + o.toString());
        }
    }

    public void logout() {
        System.out.println("logout girdi");

        insertObject(new Logs(Logs.USER_LOGOUT, "logined out", getCurrentUser(), new Date()));
        currentUser = new Users();
        currentUserDetail = new UserDetails();

        System.out.println("logout basarili1");

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            System.out.println("logout basarili2");

            context.getExternalContext().redirect("loginPage.xhtml");
            System.out.println("Yonlendirme basarili");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
