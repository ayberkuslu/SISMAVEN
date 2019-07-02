/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.*;
import Models.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hp_user
 */
@Named(value = "kullaniciGetirController")
@Dependent
@ManagedBean
@RequestScoped
public class KullaniciGetirController extends Controller  {

    private LazyDataModel<Users> users;
    private LazyDataModel<UserDetails> userDetails;
    private Users selectedUser;
    private UserDetails selectedUserDetails;

    /**
     * Creates a new instance of KullaniciGetirController
     */
    public KullaniciGetirController() {
    }
    
    @PostConstruct
    public void loadData() {
        setSession(HibernateUtil.getSessionFactory().openSession());
        getSession().beginTransaction();
//        userDetails = new UserDetailLazyDataModel<UserDetails>(getSession().createCriteria(UserDetails.class).createAlias("userId","userId").add(Restrictions.eq("userId.type", Users.TYPE_STUDENT)).list());
//        userDetails = (LazyDataModel<UserDetails>) getSession().createCriteria(UserDetails.class).createAlias("userId","userId").add(Restrictions.eq("userId.type", 2)).list();
//        users=(LazyDataModel<Users>) getSession().createCriteria(Users.class).list();
//        System.out.println("\n detail size : " + userDetails.);
        getSession().close();
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
    
    
    
}
