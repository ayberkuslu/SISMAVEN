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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ayberk
 */
public class Controller implements Serializable {

    public static final String CURRENT_USER = "mevcut_kullanici";
    public static final String SELECTED_USER = "selectedUser";
    public static final String SELECTED_COURSE = "selectedCourse";
    public static final String SELECTED_COURSE_TAKER_AS_CLASSES = "selectedCourseTakerAsClasses";
    public static final String SELECTED_COURSE_TAKER_AS_USERS = "selectedCourseTakerAsUsers";
    public static final int RUN_TIME_PROPERTY = 1;
    public static final boolean OPEN_ADD_DROP = true;
    public static final boolean CLOSED_ADD_DROP = false;

    public static final String PAGE_LOGIN = "http://localhost:8080/mavenproject1";
    public static final String PAGE_ERROR = "http://localhost:8080/mavenproject1/faces/error.xhtml";
    public static final String PAGE_HOME = "http://localhost:8080/mavenproject1/faces/homePage.xhtml";

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

        System.out.println("Controllers.Controller.destroy()");
        session.close();
    }

    @PostConstruct
    public void init() {
        System.out.println("Controllers.Controller.init()");
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public boolean hasPermission(Users currentUser, int pageType) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (currentUser == null) {
            try {
                System.out.println("Kullanici yok !");
                context.getExternalContext().redirect(PAGE_LOGIN);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        System.out.println("User Type : " + currentUser.getType() + " Page Type : " + pageType);

        int userType = currentUser.getType();
        if (userType == Users.TYPE_ADMIN) {

            return true;
        }
        if (userType == Users.TYPE_TEACHER && pageType == Users.TYPE_STUDENT) {

            return true;
        }

        if (userType != pageType) {
            try {
                context.getExternalContext().redirect(PAGE_ERROR);
                return false;
            } catch (IOException ex) {
            }

        }
        return true;
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

    public void redirectErrorPage(FacesContext context) {

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

}
