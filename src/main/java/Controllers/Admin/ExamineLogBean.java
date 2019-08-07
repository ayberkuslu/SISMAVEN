/*
 * Copyright (C) 2019 Ayberk.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package Controllers.Admin;

import Controllers.Controller;

import Controllers.HibernateUtil;
import Models.Logs;
import Models.Users;
import Util.LogsLazyDataModel;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@ViewScoped
public class ExamineLogBean extends Controller {

    Users currentUser;
    private LazyDataModel<Logs> logs;

    private Date firstDate;
    private Date lastDate;

    /**
     * Creates a new instance of ExamineLogBean
     */
    public ExamineLogBean() {
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("ExamineLog init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        currentUser = (Users) sessionMap.get(CURRENT_USER);

        if (hasPermission(currentUser, Users.TYPE_ADMIN) == false) {
            return;
        }

        loadData();
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("ExamineLog destroy()");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap();
        sessionMap.remove(SELECTED_USER);
        getSession().close();
    }

    public void loadData() {
        System.out.println("loadData()");
        Transaction tx = getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Logs.class);
        System.out.println(firstDate);
        if (firstDate != null) {
            System.out.println("firstDate != null");

            if (lastDate == null) {
                System.out.println("lastDate == null");
                criteria.add(Restrictions.between("date", firstDate, new Date()));

            } else {
                lastDate.setHours(23);
                lastDate.setMinutes(59);
                lastDate.setSeconds(59);

                System.out.println("lastDate:" + lastDate);

                criteria.add(Restrictions.between("date", firstDate, lastDate));
                System.out.println("lastDate != null");

            }
        } else if (firstDate == null && lastDate != null) {
                            System.out.println("firstDate == null && lastDate != null");

            lastDate.setHours(23);
            lastDate.setMinutes(59);
            lastDate.setSeconds(59);
            criteria.add(Restrictions.le("date", lastDate));

        }

        List<Logs> list = criteria.list();
        logs = new LogsLazyDataModel(list);
        System.out.println("row Count" + logs.getRowCount());
        tx.commit();
    }


    public LazyDataModel<Logs> getLogs() {
        return logs;
    }

    public void setLogs(LazyDataModel<Logs> logs) {
        this.logs = logs;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

}
