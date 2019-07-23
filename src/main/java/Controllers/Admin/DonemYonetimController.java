/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.Controller;
import Controllers.HibernateUtil;
import Models.Courses;
import Models.Logs;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Transaction;

import Models.RuntimeProperties;
import Models.Terms;
import Models.Users;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class DonemYonetimController extends Controller {

    Transaction tx;
    private boolean addDropOpen;
    private String currentTermName;
    private String newTermName;

    /**
     * Creates a new instance of AdminDonemYonetimi
     */
    public DonemYonetimController() {

    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("Admin Yonetim init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
        try {
            tx = getSession().beginTransaction();

            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            addDropOpen = properties.getIsOpenAddDrop();
            if (properties.getCurrentTerm() != null) {
                currentTermName = properties.getCurrentTerm().getTermName();
            } else {
                currentTermName = null;
            }

            tx.commit();
        } catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());
            tx.rollback();
        }
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("Admin Yonetim destroy()");
        getSession().close();
    }

    public void startNewTerm() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        Users currentUser = (Users) sessionMap.get(CURRENT_USER);
        RuntimeProperties properties;
        try {
            tx = getSession().beginTransaction();
            properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            if (properties.getCurrentTerm() != null) {

                context.addMessage(null, new FacesMessage("You need to end current term first!."));
                tx.commit();
                return;
            }
            if (newTermName == null || newTermName.length() < 1) {
                context.addMessage(null, new FacesMessage("Provide a proper Term name."));
                tx.commit();
                return;

            }
            Terms newTerm = new Terms();
            newTerm.setTermName(newTermName.toUpperCase());
            newTerm.setStatus(Terms.OPEN);
            newTerm.setStartDate(new Date());

            properties.setCurrentTerm(newTerm);
            getSession().save(newTerm);
            getSession().save(new Logs(Logs.NEW_TERM_START, "new term started", currentUser, new Date()));
            getSession().update(properties);
            context.addMessage(null, new FacesMessage("New Term " + newTermName + " started!."));

            tx.commit();
            currentTermName = newTermName;
            newTermName = "";

        } catch (HibernateException e) {
            tx.rollback();

        }
    }

    public void endCurrentTerm() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        Users currentUser = (Users) sessionMap.get(CURRENT_USER);
        try {
            tx = getSession().beginTransaction();

            FacesContext context = FacesContext.getCurrentInstance();

            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            Terms currentTerm = properties.getCurrentTerm();
            if (currentTerm == null) {
                context.addMessage(null, new FacesMessage("Term is not open."));
                tx.commit();
                return;
            } else if (properties.getIsOpenAddDrop() == OPEN_ADD_DROP) {
                context.addMessage(null, new FacesMessage("Term can not be finished while ADD-DROP is still open."));

                System.out.println("Already Open!!!!!");
                tx.commit();
                return;
            }

            currentTerm.setEndDate(new Date());
            currentTerm.setStatus(Terms.CLOSE);
            
            List<Courses> currentCourses = getSession().createCriteria(Courses.class)
                    .add(Restrictions.eq("status", Courses.COURSE_ACTIVE)).list();
            for(Courses course : currentCourses){
                course.setStatus(Courses.COURSE_PASIVE);
                getSession().update(course);
            }

            getSession().update(currentTerm);
            properties.setCurrentTerm(null);
            getSession().update(properties);
            getSession().save(new Logs(Logs.TERM_END, "new term ended", currentUser, new Date()));

            context.addMessage(null, new FacesMessage("Term is succesfully ended."));

            currentTermName = null;

            tx.commit();

        } catch (HibernateException e) {
            tx.rollback();
            System.out.println(e.getLocalizedMessage() + "\n EndCurrentTermFailed ");
        }

    }

    public void startAddDrop() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        Users currentUser = (Users) sessionMap.get(CURRENT_USER);
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);

            if (properties.getCurrentTerm() == null) {
                context.addMessage(null, new FacesMessage("There is no current Term"));
                tx.commit();
                System.out.println("There is no current Term!");
                return;
            } else if (properties.getIsOpenAddDrop() == OPEN_ADD_DROP) {
                context.addMessage(null, new FacesMessage("ADD-DROP is Already Open"));

                System.out.println("Already Open!!!!!");
                tx.commit();
                return;
            }

            properties.setIsOpenAddDrop(OPEN_ADD_DROP);
            getSession().save(new Logs(Logs.ADD_DROP_START, "add drop started", currentUser, new Date()));
            getSession().update(properties);
            tx.commit();
            System.out.println("Add drop basladi");
            addDropOpen = true;

        } catch (HibernateException e) {
            System.out.println(e.toString());
            context.addMessage(null, new FacesMessage("ADD-DROP Couldnt opened.!"));

            tx.rollback();

            return;

        }
        context.addMessage(null, new FacesMessage("ADD-DROP Opened!"));

    }

    public void endAddDrop() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        Users currentUser = (Users) sessionMap.get(CURRENT_USER);
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            tx = getSession().beginTransaction();
            RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            if (properties.getCurrentTerm() == null) {
                context.addMessage(null, new FacesMessage("There is no current Term"));
                tx.commit();
                System.out.println("There is no current Term!");
                return;
            } else if (properties.getIsOpenAddDrop() == CLOSED_ADD_DROP) {
                context.addMessage(null, new FacesMessage("ADD-DROP is Already Closed"));
                tx.commit();
                System.out.println("Already closed!!!!!");
                return;
            }
            properties.setIsOpenAddDrop(CLOSED_ADD_DROP);
            getSession().save(new Logs(Logs.ADD_DROP_END, "add drop ended", currentUser, new Date()));
            getSession().update(properties);
            tx.commit();
            addDropOpen = false;
        } catch (HibernateException e) {
            System.out.println(e.toString());
            context.addMessage(null, new FacesMessage("ADD-DROP Couldnt closed.!"));
            tx.rollback();
            return;
        }
        context.addMessage(null, new FacesMessage("ADD-DROP Closed"));

    }

    public boolean isAddDropOpen() {
        return addDropOpen;
    }

    public void setAddDropOpen(boolean addDropOpen) {
        this.addDropOpen = addDropOpen;
    }

    public String getCurrentTermName() {
        return currentTermName;
    }

    public void setCurrentTermName(String currentTermName) {
        this.currentTermName = currentTermName;
    }

    public String getNewTermName() {
        return newTermName;
    }

    public void setNewTermName(String newTermName) {
        this.newTermName = newTermName;
    }
}
