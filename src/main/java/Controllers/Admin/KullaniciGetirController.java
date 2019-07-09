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
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ApplicationScoped
public class KullaniciGetirController extends Controller {

    private LazyDataModel<Users> users;
    private LazyDataModel<UserDetails> userDetails;
    private LazyDataModel<Users> filteredUsers;
    private LazyDataModel<UserDetails> filteredUserDetails;
    private List<Classes> currentUserClasses;

    private Users selectedUser;
    private UserDetails selectedUserDetails;
    private Collection<Classes> selectedUserClasses;

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
        getSession().beginTransaction();

        loadData();
        System.out.println("SessionMap Size : " + sessionMap.size());

    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("KullaniciGetir destroy()");

        getSession().close();
    }

    public void loadData() {

        List<Users> list = getSession().createCriteria(Users.class).list();
        users = new UserLazyDataModel(list);

    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserDetails getSelectedUserDetails() {
        if (selectedUserDetails == null
                || !selectedUserDetails.getUserId().getUserId().equals(selectedUser.getUserId())) {
            if (selectedUser == null) {
                return null;
            }
            selectedUserDetails = getUserDetailsById(selectedUser.getUserId());
        }
        return selectedUserDetails;
    }

    public void setUserDetails(LazyDataModel<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public void setSelectedUserDetails(UserDetails selectedUserDetails) {
        this.selectedUserDetails = selectedUserDetails;
    }

    public LazyDataModel<Users> getUsers() {
        return users;
    }

    public void setUsers(LazyDataModel<Users> users) {
        this.users = users;
    }

    public LazyDataModel<UserDetails> getUserDetails() {
        return userDetails;
    }

    public LazyDataModel<Users> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(LazyDataModel<Users> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public LazyDataModel<UserDetails> getFilteredUserDetails() {
        return filteredUserDetails;
    }

    public void setFilteredUserDetails(LazyDataModel<UserDetails> filteredUserDetails) {
        this.filteredUserDetails = filteredUserDetails;
    }

    public List<Classes> getCurrentUserClasses() {
        return currentUserClasses;
    }

    public void setCurrentUserClasses(List<Classes> currentUserClasses) {
        this.currentUserClasses = currentUserClasses;
    }

    public Collection<Classes> getSelectedUserClasses() {
        if(selectedUser == null) return null;
//        if(selectedUserClasses == null){
            selectedUserClasses = selectedUser.getClassesCollection();
//        }
        return selectedUserClasses;
    }
    
    public ArrayList<Classes> ClassesList(){
        if(getSelectedUserClasses() == null) return new ArrayList<Classes>();
        
        return new ArrayList<Classes>(getSelectedUserClasses());
    }

    public void setSelectedUserClasses(Collection selectedUserClasses) {
        this.selectedUserClasses = selectedUserClasses;
    }

    public void onRowSelect() {
        FacesMessage msg = new FacesMessage("User Selected " + selectedUser.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public List<Models.Users> liste() {
        ArrayList<Users> temp = new ArrayList<>();

        temp.add(new Users(1));
        temp.add(new Users(2));
        temp.add(new Users(3));
        temp.add(new Users(4));

        return temp;
    }

}
