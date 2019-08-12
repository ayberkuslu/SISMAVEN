/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Teacher;

import Controllers.Controller;
import Controllers.HibernateUtil;
import Models.RuntimeProperties;
import Models.Terms;
import Models.Users;
import Models.Courses;
import Models.Logs;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@ViewScoped
public class CreateCourseBean extends Controller {

    private Users currentUser;
    Transaction tx;
    private String newCourseName;
    private double maxCourseSize = Courses.DEFAULT_COURSE_MAX_SIZE;

    /**
     * Creates a new instance of CoursePageController
     */
    public CreateCourseBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("CoursePage init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        currentUser = (Users) sessionMap.get(CURRENT_USER);
        if(hasPermission(currentUser, Users.TYPE_TEACHER) == false){
            return;
        }
        System.out.println("SessionMap Size : " + sessionMap.size());
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("CoursePage destroy()");
        getSession().close();
    }

    public void createNewCourse() {
        FacesContext context = FacesContext.getCurrentInstance();

        Courses newCourse;
        try {

            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            Terms currentTerm = properties.getCurrentTerm();
            if (currentTerm == null) {
                context.addMessage(null, new FacesMessage("There is no current term !"));
                return;
            } else if (newCourseName == null) {
                context.addMessage(null, new FacesMessage("Provide a proper CourseName"));
                return;
            }
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            currentUser = (Users) sessionMap.get(CURRENT_USER);

            newCourse = new Courses();
            newCourse.setCourseName(newCourseName);
            newCourse.setCurrentSize(0);
            newCourse.setMaxSize((int) maxCourseSize);
            newCourse.setStatus(Courses.COURSE_ACTIVE);
            newCourse.setTermId(currentTerm);
            newCourse.setUserId(currentUser);
            getSession().save(newCourse);
            getSession().save(new Logs(Logs.NEW_COURSE, "new course created", currentUser, new Date()));

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            context.addMessage(null, new FacesMessage(e.getLocalizedMessage() + "\n New Course couldnt created!!"));
            return;
        }
        context.addMessage(null, new FacesMessage("Course " + newCourse.getCourseName() + " is created!."));
        System.out.println("Name : " + newCourse.getCourseName()
                + "\n max size : " + newCourse.getMaxSize()
                + "\n Teacher : " + newCourse.getUserId().getName());
        resetValues();
    }

    private void resetValues() {
        maxCourseSize = Courses.DEFAULT_COURSE_MAX_SIZE;
        newCourseName = null;
    }

    public String getNewCourseName() {
        return newCourseName;
    }

    public void setNewCourseName(String newCourseName) {
        this.newCourseName = newCourseName;
    }

    public double getMaxCourseSize() {
        return maxCourseSize;
    }

    public void setMaxCourseSize(double maxCourseSize) {
        this.maxCourseSize = maxCourseSize;
    }

}
