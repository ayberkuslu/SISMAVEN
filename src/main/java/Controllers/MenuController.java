/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Users;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ApplicationScoped
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
        Users temp = (Users) sessionMap.get("kullanici"); 
         System.out.println("WhoAmI User toString(): "+temp);
        
        return temp;

    }
    
}
