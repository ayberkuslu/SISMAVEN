/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import Controllers.Controller;
import static Controllers.Controller.CURRENT_USER;
import static Controllers.Controller.RUN_TIME_PROPERTY;
import static Controllers.Controller.SELECTED_COURSE;
import static Controllers.Controller.SELECTED_COURSE_TAKER_AS_CLASSES;
import static Controllers.Controller.SELECTED_COURSE_TAKER_AS_USERS;
import Controllers.HibernateUtil;
import Models.Classes;
import Models.Courses;
import Models.RuntimeProperties;
import Models.Terms;
import Models.Users;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class EnrollCourseContoller extends Controller {

    private Users currentUser;
    Transaction tx;
    
    private Terms currentTerm;
    private Courses selectedCourse;

    /**
     * Creates a new instance of EnrollCourseContoller
     */
    public EnrollCourseContoller() {
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("EnrollCourse init()");
        FacesContext context = FacesContext.getCurrentInstance();

        setSession(HibernateUtil.getSessionFactory().openSession());
        tx = getSession().beginTransaction();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

        currentTerm = properties.getCurrentTerm();

        currentUser = (Users) sessionMap.get(CURRENT_USER);
        if (currentUser.getType() != Users.TYPE_STUDENT) {
            System.out.println("Not Student");
        }

       

        tx.commit();
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("EnrollCourse destroy()");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
//        sessionMap.remove(SELECTED_COURSE);
//        sessionMap.remove(SELECTED_COURSE_TAKER_AS_CLASSES);
//        sessionMap.remove(SELECTED_COURSE_TAKER_AS_USERS);

        getSession().close();
    }
    
    public void enrollCourse(){
        
        try{
        tx = getSession().beginTransaction();
        
        Classes newClass = new Classes();
        newClass.setCourseId(selectedCourse);
        newClass.setUserId(currentUser);
        
        getSession().save(newClass);
        tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        
    }
    

}
