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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Transaction;

/**
 *
 * @author Ayberk
 *
 */
@ManagedBean
@RequestScoped
public class MenuController extends Controller {

    private boolean renderedAdmin = false;
    private boolean renderedTeacher = false;
    private boolean renderedStudent = true;

    private Users currentUser;
    private String currentUserName = "";
    
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
        currentUser= whoAmI();
//        hasPermission(currentUser, Users.TYPE_STUDENT);
//        currentUserName = currentUser.getName() +  " " + currentUser.getSurname();
    }

    public Users whoAmI() {
        Map<String, Object> sessionMap = null;
        Users temp = null;
        try{
        sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        temp = (Users) sessionMap.get(CURRENT_USER);
        }catch(Exception e){
            System.out.println(e+"\nwhoAmI() null pointer");
        }
        if(temp == null) return null;
        switch (temp.getType()) {
            case Users.TYPE_ADMIN:
                setRenderedForAdmin();
                break;
            case Users.TYPE_TEACHER:
                setRenderedForTeacher();
                break;
            case Users.TYPE_STUDENT:
                setRenderedForStudent();
                break;
            default:
                break;
        }
        System.out.println("WhoAmI User toString(): " + temp);

        return temp;

    }

    public void logout() {

        Transaction tx = getSession().beginTransaction();

        System.out.println("logout girdi");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        Users targetUser;
        targetUser = (Users) sessionMap.get(Controller.CURRENT_USER);
        sessionMap.remove(Controller.CURRENT_USER);
        sessionMap.clear();
        getSession().save(new Logs(Logs.USER_LOGOUT, "logined out", targetUser, new Date()));
        tx.commit();
        
        System.out.println("logout basarili1");

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();

        try {
            context.getExternalContext().redirect(PAGE_LOGIN);
            System.out.println("Yonlendirme basarili");
        } catch (IOException ex) {
            System.out.println("Cikis Yapilamadi.");
        }
    }

    public void setRenderedForAdmin() {

        renderedAdmin = true;
        renderedTeacher = true;
        renderedStudent = true;

    }

    public void setRenderedForTeacher() {

        renderedAdmin = false;
        renderedTeacher = true;
        renderedStudent = true;

    }

    public void setRenderedForStudent() {
        renderedAdmin = false;
        renderedTeacher = false;
        renderedStudent = true;

    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

 
    
    public boolean isRenderedAdmin() {
        return renderedAdmin;
    }

    public void setRenderedAdmin(boolean renderedAdmin) {
        this.renderedAdmin = renderedAdmin;
    }

    public boolean isRenderedTeacher() {
        return renderedTeacher;
    }

    public void setRenderedTeacher(boolean renderedTeacher) {
        this.renderedTeacher = renderedTeacher;
    }

    public boolean isRenderedStudent() {
        return renderedStudent;
    }

    public void setRenderedStudent(boolean renderedStudent) {
        this.renderedStudent = renderedStudent;
    }

}
