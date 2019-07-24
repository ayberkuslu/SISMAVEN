/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Teacher;

import Controllers.Controller;
import Controllers.HibernateUtil;
import Models.Classes;
import Models.Courses;
import Models.RuntimeProperties;
import Models.Terms;
import Models.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class CourseManageController extends Controller implements Serializable {

    private Users currentUser;
    Transaction tx;

    private List<Courses> activeCoursesList;
    private List<Classes> selectedCourseClassesList;
    private Courses selectedCourse;
    private Classes selectedCourseTakerAsClasses;
    private Users selectedCourseTakerAsUsers;
    private Terms currentTerm;

    private boolean renderedGradeUpdate = false;

    /**
     * Creates a new instance of CourseManageController
     */
    public CourseManageController() {
    }

    @PostConstruct
    @Override
    public void init() {
        resetValues();
        System.out.println("CourseManage init()");
        FacesContext context = FacesContext.getCurrentInstance();

        setSession(HibernateUtil.getSessionFactory().openSession());
        tx = getSession().beginTransaction();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

        System.out.println("Add Drop : "+ properties.getIsOpenAddDrop());
        currentTerm = properties.getCurrentTerm();

        currentUser = (Users) sessionMap.get(CURRENT_USER);
        if (currentUser.getType() != Users.TYPE_TEACHER) {
            System.out.println("Not teacher");
        }

        if (currentTerm == null) {
            activeCoursesList = new ArrayList<>();
            context.addMessage(null, new FacesMessage("There is no active Term !!!!"));

        } else {
            activeCoursesList = getSession().createCriteria(Courses.class)
                    .add(Restrictions.eq("userId.userId", currentUser.getUserId()))
                    .add(Restrictions.eq("termId.termId", currentTerm.getTermId())).list();
        }

        tx.commit();
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("CourseManage destroy()");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        sessionMap.remove(SELECTED_COURSE);
        sessionMap.remove(SELECTED_COURSE_TAKER_AS_CLASSES);
        sessionMap.remove(SELECTED_COURSE_TAKER_AS_USERS);

        getSession().close();
    }
    public void updateStudentGrades() {
        try {
            tx = getSession().beginTransaction();
            System.out.println("updateStudentGrades()");
            int midTermExam = 0 ;
            if(selectedCourseTakerAsClasses.getVizeNot() != null)
            midTermExam= selectedCourseTakerAsClasses.getVizeNot();
            int finalExam = 0 ;
            if(selectedCourseTakerAsClasses.getFinalNot() !=null)
            finalExam= selectedCourseTakerAsClasses.getFinalNot();
            
            String grade = letterGrade(midTermExam,finalExam);
            selectedCourseTakerAsClasses.setGrade(grade);
            getSession().update(selectedCourseTakerAsClasses);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fail!Student grades NOT updated!"));

            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student grades updated!"));
    }
    public void onCourseRowSelect(SelectEvent event) {

        tx = getSession().beginTransaction();

        System.out.println("Course Name " + ((Courses) event.getObject()).getCourseName());

        selectedCourse = (Courses) event.getObject();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        sessionMap.remove(SELECTED_COURSE_TAKER_AS_CLASSES);
        sessionMap.remove(SELECTED_COURSE_TAKER_AS_USERS);

        selectedCourseTakerAsClasses = null;
        selectedCourseTakerAsUsers = null;

        sessionMap.put(SELECTED_COURSE, selectedCourse);
        currentUser = (Users) sessionMap.get(CURRENT_USER);

        selectedCourseClassesList = new ArrayList<>(selectedCourse.getClassesCollection());
        renderedGradeUpdate = false;

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Course Selected " + selectedCourse.getCourseName()));
        tx.commit();
    }

    public void onCourseTakerRowSelect(SelectEvent event) {
        tx = getSession().beginTransaction();

        System.out.println("CourseTaker Name " + ((Classes) event.getObject()).getUserId().getName());

        selectedCourseTakerAsClasses = ((Classes) event.getObject());
        selectedCourseTakerAsUsers = ((Users) selectedCourseTakerAsClasses.getUserId());
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        sessionMap.put(SELECTED_COURSE_TAKER_AS_CLASSES, selectedCourseTakerAsClasses);
        sessionMap.put(SELECTED_COURSE_TAKER_AS_USERS, selectedCourseTakerAsUsers);
        renderedGradeUpdate = true;
        FacesMessage msg = new FacesMessage("CourseTaker Selected " + selectedCourseTakerAsUsers.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        tx.commit();
    }

    public void resetValues() {
        selectedCourseClassesList = null;
        selectedCourse = null;
        selectedCourseTakerAsClasses = null;
        selectedCourseTakerAsUsers = null;
        renderedGradeUpdate = false;
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        sessionMap.remove(SELECTED_COURSE);

        sessionMap.remove(SELECTED_COURSE_TAKER_AS_CLASSES);
        sessionMap.remove(SELECTED_COURSE_TAKER_AS_USERS);

    }

    public String letterGrade(int midTerm , int finalExam ) {
        double termGrade = (0.4 * midTerm + 0.6 * finalExam);
        if (termGrade >= 89.5) {
            return "AA";
        } else if (termGrade >= 84.5) {
            return "BA";
        } else if (termGrade >= 79.5) {
            return "BB";
        } else if (termGrade >= 74.5) {
            return "CB";
        } else if (termGrade >= 69.5) {
            return "CC";
        } else if (termGrade >= 64.5) {
            return "DC"; 
        } else if (termGrade >= 59.5) {
            return "DD";
        }

        return "FF";
    }

    public List<Courses> getActiveCoursesList() {
        return activeCoursesList;
    }

    public void setActiveCoursesList(List<Courses> activeCoursesList) {
        this.activeCoursesList = activeCoursesList;
    }

    public Courses getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Courses selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public Classes getSelectedCourseTakerAsClasses() {
        return selectedCourseTakerAsClasses;
    }

    public void setSelectedCourseTakerAsClasses(Classes selectedCourseTakerAsClasses) {
        this.selectedCourseTakerAsClasses = selectedCourseTakerAsClasses;
    }

    public List<Classes> getSelectedClassesList() {
        return selectedCourseClassesList;
    }

    public void setSelectedClassesList(List<Classes> selectedCourseClasses) {
        this.selectedCourseClassesList = selectedCourseClasses;
    }

    public boolean isRenderedGradeUpdate() {
        return renderedGradeUpdate;
    }

    public void setRenderedGradeUpdate(boolean renderedGradeUpdate) {
        this.renderedGradeUpdate = renderedGradeUpdate;
    }

}
