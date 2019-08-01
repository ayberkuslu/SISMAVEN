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
    
    private final String DOMAIN_TAG = "http://localhost:8080/";
    private final String CONTEXT_PATH = "mavenproject1/";
    
    private final String PROJECT_PATH = DOMAIN_TAG + CONTEXT_PATH;
    
    
    

    public final String PAGE_LOGIN =  PROJECT_PATH;
    public final String PAGE_FORGET_PASSWORD = PROJECT_PATH + "forgetPassword.xhtml";
    public final String PAGE_HOME = PROJECT_PATH + "homePage.xhtml";
    
    public final String PAGE_ADMIN_EXAMINE_USER = PROJECT_PATH + "admin/adminKullaniciGetir.xhtml";
    public final String PAGE_ADMIN_INSERT = PROJECT_PATH + "admin/adminKullaniciEkle.xhtml";
    public final String PAGE_ADMIN_YONETIM = PROJECT_PATH + "admin/adminYonetimIslemleri.xhtml";
    public final String PAGE_ADMIN_EXAMINE_LOG = PROJECT_PATH + "admin/adminExamineLog.xhtml";

    public final String PAGE_TEACHER_COURSE_MANAGE = PROJECT_PATH + "teacher/teacherCourseManage.xhtml";
    public final String PAGE_TEACHER_COURSES = PROJECT_PATH + "teacher/teacherCourses.xhtml";
   
    public final String PAGE_STUDENT_ENROLL = PROJECT_PATH + "student/studentEnroll.xhtml";
    
    public final String PAGE_ERROR = PROJECT_PATH + "error.xhtml";

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

    public String getPAGE_LOGIN() {
        return PAGE_LOGIN;
    }

    public String getPAGE_FORGET_PASSWORD() {
        return PAGE_FORGET_PASSWORD;
    }

    public String getPAGE_HOME() {
        return PAGE_HOME;
    }

    public String getPAGE_ADMIN_EXAMINE_USER() {
        return PAGE_ADMIN_EXAMINE_USER;
    }

    public String getPAGE_ADMIN_INSERT() {
        return PAGE_ADMIN_INSERT;
    }

    public String getPAGE_ADMIN_YONETIM() {
        return PAGE_ADMIN_YONETIM;
    }

    public String getPAGE_ADMIN_EXAMINE_LOG() {
        return PAGE_ADMIN_EXAMINE_LOG;
    }

    public String getPAGE_TEACHER_COURSE_MANAGE() {
        return PAGE_TEACHER_COURSE_MANAGE;
    }

    public String getPAGE_TEACHER_COURSES() {
        return PAGE_TEACHER_COURSES;
    }

    public String getPAGE_STUDENT_ENROLL() {
        return PAGE_STUDENT_ENROLL;
    }

    public String getPAGE_ERROR() {
        return PAGE_ERROR;
    }

    
    
    
    
}
