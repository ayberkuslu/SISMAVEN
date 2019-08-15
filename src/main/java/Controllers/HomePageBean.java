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
package Controllers;

import static Controllers.Controller.CURRENT_USER;
import Models.Users;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Transaction;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@ViewScoped
public class HomePageBean extends Controller {

    private Users currentUser;
    Transaction tx;
    private int userType;

    private final int ADMIN = Users.TYPE_ADMIN;
    private final int TEACHER = Users.TYPE_TEACHER;
    private final int STUDENT = Users.TYPE_STUDENT;

    private DashboardModel modelAdmin;
    private boolean renderedModelAdmin = false;
    private DashboardModel modelTeacher;
    private boolean renderedModelTeacher = false;
    private DashboardModel modelStudent;
    private boolean renderedModelStudent = false;
    
    
    
    private ScheduleModel eventModel;
      

    /**
     * Creates a new instance of HomePageBean
     */
    public HomePageBean() {

    }

    @PreDestroy
    @Override
    public void destroy() {

        System.out.println("Controllers.HomePageBean.destroy()");
        getSession().close();
    }

    @PostConstruct
    @Override
    public void init() {
        System.out.println("Controllers.HomePageBean.init()");
        setSession(HibernateUtil.getSessionFactory().openSession());
        tx = getSession().beginTransaction();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        currentUser = (Users) sessionMap.get(CURRENT_USER);
        userType = currentUser.getType();

        if (hasPermission(currentUser, Users.TYPE_ANYONE) == false) {
            return;
        }

        renderedModelAdmin = false;
        renderedModelTeacher = false;
        renderedModelStudent = false;

        switch (userType) {
            case Users.TYPE_ADMIN:
                setDashboardAdmin();
                renderedModelAdmin = true;

//                setDashboardTeacher();
//                renderedModelTeacher = true;
//
//                setDashboardStudent();
//                renderedModelStudent = true;
                break;
            case Users.TYPE_TEACHER:
                setDashboardTeacher();
                renderedModelTeacher = true;
                break;
            default:
                setDashboardStudent();
                renderedModelStudent = true;
                break;
        }
        
                eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new DefaultScheduleEvent("New Term started", new Date(), new Date()));


    }

    public void goExamineUser() {
        goPage(PAGE_ADMIN_EXAMINE_USER);
    }

    public void goExamineLog() {
        goPage(PAGE_ADMIN_EXAMINE_LOG);
    }

    public void goCreateUser() {
        goPage(PAGE_ADMIN_CREATE_USER);
    }

    public void goSchoolManage() {
        goPage(PAGE_ADMIN_SCHOOL_MANAGE);
    }

    public void goCourseManage() {
        goPage(PAGE_TEACHER_COURSE_MANAGE);
    }

    public void goCreateCourse() {
        goPage(PAGE_TEACHER_CREATE_COURSE);
    }

    public void goEnroll() {
        goPage(PAGE_STUDENT_ENROLL);
    }

    private void goPage(String page) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(page);
        } catch (IOException ex) {
            System.out.println(ex + "\n Exception while redirecting to " + page);
        }
    }

    private void setDashboardAdmin() {
        modelAdmin = new DefaultDashboardModel();
        DashboardColumn userManageColumn = new DefaultDashboardColumn();
        DashboardColumn schoolManageColumn = new DefaultDashboardColumn();
        DashboardColumn logManageColumn = new DefaultDashboardColumn();

        userManageColumn.addWidget("CreateUser");
        userManageColumn.addWidget("ExamineUser");

        schoolManageColumn.addWidget("SchoolManage");

        logManageColumn.addWidget("ExamineLog");

        modelAdmin.addColumn(userManageColumn);
        modelAdmin.addColumn(schoolManageColumn);
        modelAdmin.addColumn(logManageColumn);

    }

    private void setDashboardTeacher() {
        modelTeacher = new DefaultDashboardModel();
        DashboardColumn createCourse = new DefaultDashboardColumn();
        DashboardColumn manageCourse = new DefaultDashboardColumn();

        manageCourse.addWidget("courseManage");
        createCourse.addWidget("createCourse");

        modelTeacher.addColumn(manageCourse);
        modelTeacher.addColumn(createCourse);
    }

    private void setDashboardStudent() {

        modelStudent = new DefaultDashboardModel();
        DashboardColumn enrollCourse = new DefaultDashboardColumn();
        enrollCourse.addWidget("enrollCourse");
        modelStudent.addColumn(enrollCourse);

    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public DashboardModel getModelAdmin() {
        return modelAdmin;
    }

    public DashboardModel getModelTeacher() {
        return modelTeacher;
    }

    public DashboardModel getModelStudent() {
        return modelStudent;
    }

    public int getADMIN() {
        return ADMIN;
    }

    public int getTEACHER() {
        return TEACHER;
    }

    public int getSTUDENT() {
        return STUDENT;
    }

    public boolean isRenderedModelAdmin() {
        return renderedModelAdmin;
    }

    public boolean isRenderedModelTeacher() {
        return renderedModelTeacher;
    }

    public boolean isRenderedModelStudent() {
        return renderedModelStudent;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
    
    

}
