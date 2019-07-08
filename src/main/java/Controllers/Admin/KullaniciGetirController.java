/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.*;
import Models.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hp_user
 */

@ManagedBean
@ViewScoped
public class KullaniciGetirController extends Controller {

    private LazyDataModel<Users> users;
    private LazyDataModel<UserDetails> userDetails;
    private LazyDataModel<Users> filteredUsers;
    private LazyDataModel<UserDetails> filteredUserDetails;
    private List<Classes> currentUserClasses;

    private Users selectedUser;
    private UserDetails selectedUserDetails;

    /**
     * Creates a new instance of KullaniciGetirController
     */
    public KullaniciGetirController() {
    }

//    @PostConstruct
//    public void initial(){
//        userDetails = new UserDetailLazyDataModel();
//    }
    @PostConstruct
    public void init() {
        System.out.println("KullaniciGetir init()");

        setSession(HibernateUtil.getSessionFactory().openSession());
        getSession().beginTransaction();
//        List<UserDetails> list = getSession().createCriteria(UserDetails.class).createAlias("userId","userId").add(Restrictions.eq("userId.type", Users.TYPE_STUDENT)).list();
//                userDetails = new UserDetailLazyDataModel(list);

        loadData();
//        userDetails = new UserDetailLazyDataModel<UserDetails>(getSession().createCriteria(UserDetails.class).createAlias("userId","userId").add(Restrictions.eq("userId.type", Users.TYPE_STUDENT)).list());
//        userDetails = (LazyDataModel<UserDetails>) getSession().createCriteria(UserDetails.class).createAlias("userId","userId").add(Restrictions.eq("userId.type", 2)).list();
//        users=(LazyDataModel<Users>) getSession().createCriteria(Users.class).list();
//        System.out.println("\n detail size : " );
    }

    @PreDestroy
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
        return selectedUserDetails;
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

    public void setUserDetails(LazyDataModel<UserDetails> userDetails) {
        this.userDetails = userDetails;
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

    public void onRowSelect() {
        FacesMessage msg = new FacesMessage("User Selected " + selectedUser.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

}
