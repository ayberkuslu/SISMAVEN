/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import Controllers.Controller;
import static Controllers.Controller.CURRENT_USER;
import static Controllers.Controller.RUN_TIME_PROPERTY;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.hibernate.Criteria;
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
    private Classes selectedClassesForDeroll;

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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "THERE IS NO OPEN TERM", ""));
        } else {

            Criteria myCriteriaCourses = getSession().createCriteria(Courses.class);
            myCriteriaCourses.add(Restrictions.eq("status", Courses.COURSE_ACTIVE));
            openCourses = myCriteriaCourses.list();

            System.out.println("Open Courses First Size : " + openCourses.size());

            Criteria myCriteria = getSession().createCriteria(Classes.class);
            myCriteria.createAlias("userId", "user");
            myCriteria.createAlias("courseId", "course");
            myCriteria.add(Restrictions.eq("user.userId", currentUser.getUserId()));
            myCriteria.add(Restrictions.eq("course.status", Courses.COURSE_ACTIVE));
            currentUserClasses = myCriteria.list();

            System.out.println("CurrentUserClasses Size : " + currentUserClasses.size());

            List<Courses> courseToBeRemovedFromAvaliableList = new ArrayList(); // already taken courses should be removed
            for (Classes classes : currentUserClasses) {
                for (Courses courses : openCourses) {
                    if (classes.getCourseId().getCourseId().intValue() == courses.getCourseId().intValue()) {
                        courseToBeRemovedFromAvaliableList.add(courses);
                    }
                }
            }

            for (Courses course : courseToBeRemovedFromAvaliableList) {
                openCourses.remove(course);
            }
            System.out.println("Open Courses Last Size : " + openCourses.size());

        }

        if (!properties.getIsOpenAddDrop()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADD-DROP IS CLOSE", "ERROR"));
        }

        currentUser = (Users) sessionMap.get(CURRENT_USER);
        if (currentUser.getType() != Users.TYPE_STUDENT) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NOT STUDENT ACCOUNT !", "ERROR"));
        }

        tx.commit();
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("EnrollCourse destroy()");
//        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
//                getExternalContext().getSessionMap();

        getSession().close();
    }

    public void enrollCourse() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedCourseForEnroll == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO COURSE SELECTED", ""));
            return;
        }

        try {
            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

            System.out.println("Open Drop : " + properties.getIsOpenAddDrop());

            if (!properties.getIsOpenAddDrop()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADD-DROP IS CLOSE", "WAIT"));
                tx.commit();
                return;
            }

            if (selectedCourseForEnroll.getCurrentSize() >= selectedCourseForEnroll.getMaxSize()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL!CLASS IS FULL", ""));
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
            context.addMessage(null, new FacesMessage("SUCCESSFULLY ENROLLED :" + selectedCourseForEnroll.getCourseName()));
            selectedCourseForEnroll = null;
            tx.commit();
        } catch (HibernateException e) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FAIL! ERROR WHILE ENROLLING", ""));

            tx.rollback();
            System.out.println(e);
        }

    }

    public void derollCourse() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedClassesForDeroll == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NO COURSE SELECTED", ""));
            return;
        }

        try {
            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

            System.out.println("Open Drop : " + properties.getIsOpenAddDrop());

            if (!properties.getIsOpenAddDrop()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL!ADD-DROP IS CLOSE", ""));
                tx.commit();
                return;
            }

            Courses selectedCourseForDeroll = selectedClassesForDeroll.getCourseId();

            selectedCourseForDeroll.decrementCurrentSize(); // courseCurrentSize--;
            getSession().update(selectedCourseForDeroll);
            getSession().delete(selectedClassesForDeroll);
            openCourses.add(selectedCourseForDeroll);
            currentUserClasses.remove(selectedClassesForDeroll);
            tx.commit();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"SUCCESSFULLY DEROLLED" + selectedClassesForDeroll.getCourseId().getCourseName(),"success"));

            selectedClassesForDeroll = null;

        } catch (HibernateException e) {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL! ERROR WHILE DEROLLING", ""));

            tx.rollback();
            System.out.println(e);
        }

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

    public Classes getSelectedClassesForDeroll() {
        return selectedClassesForDeroll;
    }

    public void setSelectedClassesForDeroll(Classes selectedClassesForDeroll) {
        this.selectedClassesForDeroll = selectedClassesForDeroll;
    }

    public List<Classes> getCurrentUserClasses() {
        return currentUserClasses;
    }

    public void setCurrentUserClasses(List<Classes> currentUserClasses) {
        this.currentUserClasses = currentUserClasses;
    }

}
