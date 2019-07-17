/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.*;
import Models.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class KullaniciGetirController extends Controller {

    private LazyDataModel<Users> users;
//    private LazyDataModel<UserDetails> userDetails;

    private LazyDataModel<Users> filteredUsers;
//    private LazyDataModel<UserDetails> filteredUserDetails;

    private List<Classes> selectedUserClassesList;
    private List<Courses> selectedUserCoursesList;

    private Users selectedUser;
    private UserDetails selectedUserDetails;

    private String isRenderedClasses = "true";
    private String isRenderedCourses = "true";
    

    /**
     * Creates a new instance of KullaniciGetirController
     */
    public KullaniciGetirController() {
    }

@PostConstruct
@Override
public void init() {
    System.out.println("KullaniciGetir init()");
    setSession(HibernateUtil.getSessionFactory().openSession());
    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    loadData();

    System.out.println("SessionMap Size : " + sessionMap.size());
}

@PreDestroy
@Override
public void destroy() {
    System.out.println("KullaniciGetir destroy()");
    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
            getExternalContext().getSessionMap();
    sessionMap.remove(SELECTED_USER);
    getSession().close();
}

    public List<Courses> CoursesList() {
        Collection collection = selectedUser.getCoursesCollection();

        if (collection == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(collection);
    }

    public List<Classes> ClassesList() {
        Collection collection = selectedUser.getClassesCollection();

        if (collection == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(collection);
    }

    public void loadData() {
        Transaction tx = getSession().beginTransaction();
        List<Users> list = getSession().createCriteria(Users.class).list();
        users = new UserLazyDataModel(list);
        tx.commit();
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserDetails getSelectedUserDetails() {
        return selectedUserDetails;
    }

//    public void setUserDetails(LazyDataModel<UserDetails> userDetails) {
//        this.userDetails = userDetails;
//    }

    public void setSelectedUserDetails(UserDetails selectedUserDetails) {
        this.selectedUserDetails = selectedUserDetails;
    }

    public LazyDataModel<Users> getUsers() {
        return users;
    }

    public void setUsers(LazyDataModel<Users> users) {
        this.users = users;
    }

//    public LazyDataModel<UserDetails> getUserDetails() {
//        return userDetails;
//    }

    public LazyDataModel<Users> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(LazyDataModel<Users> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

//    public LazyDataModel<UserDetails> getFilteredUserDetails() {
//        return filteredUserDetails;
//    }
//
//    public void setFilteredUserDetails(LazyDataModel<UserDetails> filteredUserDetails) {
//        this.filteredUserDetails = filteredUserDetails;
//    }

    public List<Classes> getSelectedUserClassesList() {
        return selectedUserClassesList;
    }

    public void setSelectedUserClassesList(List<Classes> selectedUserClassesList) {
        this.selectedUserClassesList = selectedUserClassesList;
    }

    public List<Courses> getSelectedUserCoursesList() {
        return selectedUserCoursesList;
    }

    public void setSelectedUserCoursesList(List<Courses> selectedUserCoursesList) {
        this.selectedUserCoursesList = selectedUserCoursesList;
    }

//    private Collection getSelectedUserClassesCollection() {
//        if (selectedUser == null) {
//            return null;
//        }
//        selectedUserClassesCollection = selectedUser.getClassesCollection();
//        return selectedUserClassesCollection;
//    }

//    private Collection getSelectedUserCoursesCollection() {
//        if (selectedUser == null) {
//            return null;
//        }
//        selectedUserCoursesCollection = selectedUser.getCoursesCollection();
//        return selectedUserCoursesCollection;
//    }

//    private void setSelectedUserClassesCollection(Collection selectedUserClasses) {
//        this.selectedUserClassesCollection = selectedUserClasses;
//    }

    public String getIsRenderedClasses() {
        return isRenderedClasses;
    }

    public void setIsRenderedClasses(String isRenderedClasses) {
        this.isRenderedClasses = isRenderedClasses;
    }

    public String getIsRenderedCourses() {
        return isRenderedCourses;
    }

    public void setIsRenderedCourses(String isRenderedCourses) {
        this.isRenderedCourses = isRenderedCourses;
    }

    public void onRowSelect(SelectEvent event) {

        System.out.println("Kullanici Name " + ((Users) event.getObject()).getName());

        selectedUser = (Users) event.getObject();
        setSelectedUserDetails(getUserDetailsById(selectedUser.getUserId()));
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        sessionMap.put(SELECTED_USER, selectedUser);

        selectedUserClassesList = ClassesList();
        selectedUserCoursesList = CoursesList();

        FacesMessage msg = new FacesMessage("User Selected " + selectedUser.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        switch (selectedUser.getType()) {
            case Users.TYPE_STUDENT:
                isRenderedClasses = "true";
                isRenderedCourses = "false";
                break;
            case Users.TYPE_TEACHER:
                isRenderedClasses = "false";
                isRenderedCourses = "true";

                break;
            default:
                isRenderedClasses = "false";
                isRenderedCourses = "false";

                break;
        }
        System.out.println("IsRendered : " + isRenderedClasses + "  " + isRenderedCourses);
    }

    public void deleteSelectedUser() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        
        selectedUser = (Users) sessionMap.get(SELECTED_USER);
        System.out.println("Kontrol"+selectedUser);

        if (getSelectedUser() == null) {
            FacesMessage msg = new FacesMessage("User Not Selected!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        getSession().beginTransaction();

        try {
            selectedUser.setStatus(false);
            getSession().save(new Logs(Logs.USER_DELETE, "user made passive", selectedUser, new Date()));
            getSession().update(selectedUser);
            getSession().getTransaction().commit();

        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getLocalizedMessage() + "\nFAILED WHILE DELETING!"));
           System.out.println(e.getLocalizedMessage()+"\n"+e.getMessage());
            return;

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("USER DELETED"));

    }
    
    public void activateSelectedUser() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        
        selectedUser = (Users) sessionMap.get(SELECTED_USER);
        System.out.println("Kontrol"+selectedUser);

        if (getSelectedUser() == null) {
            FacesMessage msg = new FacesMessage("User Not Selected!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        getSession().beginTransaction();

        try {
            selectedUser.setStatus(true);
            getSession().save(new Logs(Logs.USER_ACTIVATE, "user made active", selectedUser, new Date()));
            getSession().update(selectedUser);
            getSession().getTransaction().commit();

        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getLocalizedMessage() + "\nFAILED WHILE ACTIVATING!"));
           System.out.println(e.getLocalizedMessage()+"\n"+e.getMessage());
            return;

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("USER ACTIVATED"));

    }
    
    

}
