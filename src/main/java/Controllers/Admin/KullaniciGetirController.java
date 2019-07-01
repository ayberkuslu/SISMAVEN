/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.*;
import Models.*;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hp_user
 */
@Named(value = "kullaniciGetirController")
@Dependent
@ManagedBean
@RequestScoped
public class KullaniciGetirController extends Controller {

    private List users;
    private List userDetails;

    /**
     * Creates a new instance of KullaniciGetirController
     */
    public KullaniciGetirController() {
    }

    public void loadData() {
        setSession(HibernateUtil.getSessionFactory().openSession());
        getSession().beginTransaction();

        userDetails = getSession().createCriteria(UserDetails.class).add(Restrictions.eq(".type", 2)).list();
        users = getSession().createCriteria(Users.class).list();
        System.out.println("User list size : " + users.size()+"\n detail size : " + userDetails.size());
        getSession().close();
    }

}
