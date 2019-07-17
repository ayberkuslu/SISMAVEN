/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Controllers.Controller;
import Controllers.HibernateUtil;
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
import javax.faces.application.FacesMessage;
import org.hibernate.HibernateException;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class DonemYonetimController extends Controller {

    Transaction tx;
    private boolean addDropOpen;
    private String currentTermName = "There is no active term!";
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
            }

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        }
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("Admin Yonetim destroy()");
        getSession().close();
    }

    public void endCurrentTerm() {
        tx = getSession().beginTransaction();

        RuntimeProperties properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
        FacesContext context = FacesContext.getCurrentInstance();

        Terms currentTerm = properties.getCurrentTerm();

        if (currentTerm != null) {

            context.addMessage(null, new FacesMessage("Term is not open."));

            tx.commit();

            return;
        }

        currentTerm.setEndDate(new Date());
        currentTerm.setStatus(Terms.CLOSE);

        try {
            tx = getSession().beginTransaction();
            getSession().update(currentTerm);
            properties.setCurrentTerm(null);
            getSession().update(properties);
            tx.commit();
            currentTermName = "There is no active term!";

        } catch (HibernateException e) {
            tx.rollback();
            System.out.println(e.getLocalizedMessage() + "\n EndCurrentTermFailed ");
        }

    }

    public void startNewTerm() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();

        Users currentUser = (Users) sessionMap.get(CURRENT_USER);
        RuntimeProperties properties;
        try {
            tx = getSession().beginTransaction();
            properties = (RuntimeProperties) getSession().get(RuntimeProperties.class, RUN_TIME_PROPERTY);
            Terms newTerm = new Terms();
//             newTermName = "newTermToBeAdded";
            newTerm.setTermName(newTermName);
            newTerm.setStatus(Terms.OPEN);
            newTerm.setStartDate(new Date());

            properties.setCurrentTerm(newTerm);
            getSession().save(newTerm);
            getSession().save(new Logs(Logs.NEW_TERM_START, "new term started", currentUser, new Date()));
            getSession().update(properties);
            tx.commit();
            currentTermName = newTermName;

        } catch (HibernateException e) {
            tx.rollback();

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

            if (properties.getIsOpenAddDrop() == OPEN_ADD_DROP) {
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

            if (properties.getIsOpenAddDrop() == CLOSED_ADD_DROP) {
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

    public String getNewTermName() {
        return newTermName;
    }

    public void setNewTermName(String newTermName) {
        this.newTermName = newTermName;
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

    public void yazdir() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("!!:"+newTermName));

    }

}
