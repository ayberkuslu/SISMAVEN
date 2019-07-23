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
import java.util.List;
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

    private List<Courses> openCourses;
    private Courses selectedCourseForEnroll;

    private List<Classes> currentUserClasses;
    private Courses selectedCourseForDeroll;

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
        currentUser = (Users) sessionMap.get(CURRENT_USER);
        RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

        currentTerm = properties.getCurrentTerm();

        if (currentTerm == null) {
            openCourses = new ArrayList();
            context.addMessage(null, new FacesMessage("THERE IS NO OPEN TERM"));
        } else {

            openCourses = getSession().createCriteria(Courses.class)
                    .add(Restrictions.eq("status", Courses.COURSE_ACTIVE)).list(); // TODO : ZATEN ALINAN DERSLER CIKARILMALI
//            currentUserClasses = new ArrayList(currentUser.getClassesCollection());
            currentUserClasses = getSession().createCriteria(Classes.class)
                    .add(Restrictions.eq("userId.userId", currentUser.getUserId() ))
//                    .add(Restrictions.eq("courseId.termId.termId", currentTerm.getTermId() ))
                    .list();
            
            


            List<Courses> courseToBeRemovedFromAvaliableList = new ArrayList();
            for (Classes classes : currentUserClasses) {
                for (Courses courses : openCourses) {
                    if (classes.getCourseId().getCourseId() == courses.getCourseId()) {
                        courseToBeRemovedFromAvaliableList.add(courses);
                    }
                }
            }

            for (Courses course : courseToBeRemovedFromAvaliableList) {
                openCourses.remove(course);
            }

        }

        if (!properties.getIsOpenAddDrop()) {
            context.addMessage(null, new FacesMessage("ADD-DROP IS CLOSE"));
        }

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

        getSession().close();
    }

    public void enrollCourse() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

            System.out.println("Open Drop : " + properties.getIsOpenAddDrop());

            if (!properties.getIsOpenAddDrop()) {
                context.addMessage(null, new FacesMessage("FAIL!ADD-DROP IS CLOSE"));
                tx.commit();
                return;
            }
//            if(currentUser.){
//                
//            }
            if (selectedCourseForEnroll.getCurrentSize() >= selectedCourseForEnroll.getMaxSize()) {
                context.addMessage(null, new FacesMessage("FAIL!CLASS IS FULL"));
                tx.commit();
                return;

            }
            Classes newClass = new Classes();
            newClass.setCourseId(selectedCourseForEnroll);
            newClass.setUserId(currentUser);
            selectedCourseForEnroll.incrementCurrentSize(); // courseCurrentSize++;
            getSession().update(selectedCourseForEnroll);
            getSession().save(newClass);
            openCourses.remove(selectedCourseForEnroll);
            currentUserClasses.add(newClass);
            tx.commit();
        } catch (HibernateException e) {

            context.addMessage(null, new FacesMessage("FAIL! ERROR WHILE ENROLLING"));

            tx.rollback();
            System.out.println(e);
        }
    }

    public void derollClass() {

    }

    public List<Courses> getOpenCourses() {
        return openCourses;
    }

    public void setOpenCourses(List<Courses> openCourses) {
        this.openCourses = openCourses;
    }

    public Courses getSelectedCourseForEnroll() {
        return selectedCourseForEnroll;
    }

    public void setSelectedCourseForEnroll(Courses selectedCourseForEnroll) {
        this.selectedCourseForEnroll = selectedCourseForEnroll;
    }

    public List<Classes> getCurrentUserClasses() {
        return currentUserClasses;
    }

    public void setCurrentUserClasses(List<Classes> currentUserClasses) {
        this.currentUserClasses = currentUserClasses;
    }
    
    

}
