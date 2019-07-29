/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Logs;
import Models.Users;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@RequestScoped
public class MenuController extends Controller {

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
    }

    @PreDestroy
    @Override
    public void destroy() {

        System.out.println("Controllers.MenuController.destroy()");
        getSession().close();
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("Controllers.MenuController.init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
    }

    public Users whoAmI() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        Users temp = (Users) sessionMap.get(CURRENT_USER);
        System.out.println("WhoAmI User toString(): " + temp);

        return temp;

    }

    public String logout() {
        System.out.println("logout girdi");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        Users targetUser;
        targetUser = (Users) sessionMap.get("kullanici");
        sessionMap.remove("kullanici");

        insertObject(new Logs(Logs.USER_LOGOUT, "logined out", targetUser, new Date()));
        System.out.println("logout basarili1");

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();

        try {
            System.out.println("logout basarili2");

            context.getExternalContext().redirect("loginPage.xhtml");
            System.out.println("Yonlendirme basarili");
            return "loginPage.xhtml";
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ABC";
    }

}
