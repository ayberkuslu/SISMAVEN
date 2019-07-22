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
import javax.faces.view.ViewScoped;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

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
    private Terms currentTerm;
    
    private LazyDataModel<Users> users;
    private LazyDataModel<Users> filteredUsers;

    /**
     * Creates a new instance of CourseManageController
     */
    public CourseManageController() {
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("CourseManage init()");
        FacesContext context = FacesContext.getCurrentInstance();

        setSession(HibernateUtil.getSessionFactory().openSession());
        tx = getSession().beginTransaction();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

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
        getSession().close();
    }

    public void onRowSelect(SelectEvent event) {

        tx = getSession().beginTransaction();

        System.out.println("Course Name " + ((Courses) event.getObject()).getCourseName());

        selectedCourse = (Courses) event.getObject();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        sessionMap.put(SELECTED_COURSE, selectedCourse);
        currentUser = (Users) sessionMap.get(CURRENT_USER);

//        List<Courses> activeCourses = getSession().createCriteria(Courses.class)
//                .add(Restrictions.eq("userId.userId", currentUser.getUserId())).list();
        selectedCourseClassesList = new ArrayList<>(selectedCourse.getClassesCollection());
        System.out.println("selectedCourseClassesList.size() = " + selectedCourseClassesList.size());

//        for (Classes classes : selectedCourseClassesList) {
//            selectedCourseTakers.add(classes.getUserId());
//        }
//        selectedUserClassesList = classesList();
//        selectedUserCoursesList = coursesList();
        FacesMessage msg = new FacesMessage("Course Selected " + selectedCourse.getCourseName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        tx.commit();
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

    public List<Classes> getSelectedClassesList() {
        return selectedCourseClassesList;
    }

    public void setSelectedClassesList(List<Classes> selectedCourseClasses) {
        this.selectedCourseClassesList = selectedCourseClasses;
    }

}
