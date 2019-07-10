/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author hp_user
 */
@Entity
@Table(name = "classes")
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c"),
    @NamedQuery(name = "Classes.findByClassesId", query = "SELECT c FROM Classes c WHERE c.classesId = :classesId"),
    @NamedQuery(name = "Classes.findByGrade", query = "SELECT c FROM Classes c WHERE c.grade = :grade")})
public class Classes implements Serializable {
    
    

    @Column(name = "VIZE_NOT")
    private Integer vizeNot;
    @Column(name = "FINAL_NOT")
    private Integer finalNot;

    @Column(name = "GRADE")
    private String grade;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLASSES_ID")
    private Integer classesId;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(optional = false)
    private Courses courseId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Classes() {
    }

    public Classes(Integer classesId) {
        this.classesId = classesId;
    }

    public Classes(Integer classesId, String grade) {
        this.classesId = classesId;
        this.grade = grade;
    }

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }


    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classesId != null ? classesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.classesId == null && other.classesId != null) || (this.classesId != null && !this.classesId.equals(other.classesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Classes[ classesId=" + classesId + " ]";
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getVizeNot() {
        return vizeNot;
    }

    public void setVizeNot(Integer vizeNot) {
        this.vizeNot = vizeNot;
    }

    public Integer getFinalNot() {
        return finalNot;
    }

    public void setFinalNot(Integer finalNot) {
        this.finalNot = finalNot;
    }
    
}
