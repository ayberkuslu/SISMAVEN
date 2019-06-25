/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hp_user
 */
@ManagedBean
@RequestScoped
public class Controller {
    
       private HibernateUtil helper;      
       private Session session;
       private Users currentUser;
       private UserDetails currentUserDetail;
       



       

    /**
     * Creates a new instance of Controller
     */
    public Controller() {
    }
    
    
    
    
    @PostConstruct
public void init(){   
    helper = new HibernateUtil();
}     

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public UserDetails getCurrentUserDetail() {
        return currentUserDetail;
    }

    public void setCurrentUserDetail(UserDetails currentUserDetail) {
        this.currentUserDetail = currentUserDetail;
    }


    public HibernateUtil getHelper() {
        return helper;
    }

    public void setHelper(HibernateUtil helper) {
        this.helper = helper;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public Users getUserById(int userId){
         try {           
            setSession(HibernateUtil.getSessionFactory().openSession());
            getSession().beginTransaction(); 
            Users temp = (Users) getSession().get(Users.class, userId);
            getSession().close();
            return temp;
        } catch (Exception e) {
            System.out.println(e);

        }
        
         return null;
    }
    
    public Object getObject(Class type , Serializable srzbl){
         setSession(HibernateUtil.getSessionFactory().openSession());
            getSession().beginTransaction();
            Object o = getSession().get(type, srzbl);
            getSession().close();

            return o ;
    }
    
        public UserDetails getUserDetailsById(int userId){
         try {
             
            setSession(HibernateUtil.getSessionFactory().openSession());
            getSession().beginTransaction(); 
                
List abc = getSession().createCriteria(UserDetails.class).add(Restrictions.eq("userId.userId", userId)).list();
getSession().getTransaction().commit();
    if(abc.size() != 1) return null;
    
      UserDetails temp = (UserDetails)  abc.get(0);
        
            getSession().close();
            return temp;

        } catch (HibernateException e) {
            System.out.println(e);

        }
        
         return null;
    }
            public static String sha256(String base) {
                
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
            
            public void insertObject(Object o){                
            setSession(getHelper().getSessionFactory().openSession());
            getSession().beginTransaction();
            getSession().save(o);
            getSession().getTransaction().commit();
            getSession().close();       
            }
            
            
            
            public void logout(){
                                               System.out.println("logout girdi");

                insertObject(new Logs(Logs.USER_LOGOUT,"logined out",getCurrentUser(),new Date()));
                currentUser = new Users();
                currentUserDetail = new UserDetails();

                               System.out.println("logout basarili1");

                FacesContext context = FacesContext.getCurrentInstance();
                
           try {
                              System.out.println("logout basarili2");

               context.getExternalContext().redirect("loginPage.xhtml");
               System.out.println("logout basarili");
           } catch (IOException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           }

            }
        
    
}
