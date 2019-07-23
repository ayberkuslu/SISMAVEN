/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author hp_user
 */
@Entity
@Table(name = "courses")
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findByCourseId", query = "SELECT c FROM Courses c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Courses.findByTermId", query = "SELECT c FROM Courses c WHERE c.termId = :termId"),
    @NamedQuery(name = "Courses.findByCourseName", query = "SELECT c FROM Courses c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Courses.findByStatus", query = "SELECT c FROM Courses c WHERE c.status = :status"),
    @NamedQuery(name = "Courses.findByCurrentSize", query = "SELECT c FROM Courses c WHERE c.currentSize = :currentSize"),
    @NamedQuery(name = "Courses.findByMaxSize", query = "SELECT c FROM Courses c WHERE c.maxSize = :maxSize")})
public class Courses implements Serializable {

    public static final int DEFAULT_COURSE_MAX_SIZE = 50;
    public static final int COURSE_ACTIVE = 1;
    public static final int COURSE_PASIVE = 0;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "COURSE_NAME")
    private String courseName;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COURSE_ID")
    private Integer courseId;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private int status;
    @Column(name = "CURRENT_SIZE")
    private Integer currentSize;
    @Column(name = "MAX_SIZE")
    private Integer maxSize;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<Classes> classesCollection;

    @JoinColumn(name = "TERM_ID", referencedColumnName = "TERM_ID")
    @ManyToOne(optional = false)
    private Terms termId;

    public Courses() {
    }

    public Courses(Integer courseId) {
        this.courseId = courseId;
    }

    public Courses(Integer courseId, Terms termId, String courseName, int status) {
        this.courseId = courseId;
        this.termId = termId;
        this.courseName = courseName;
        this.status = status;
    }

    public void incrementCurrentSize() {
        this.currentSize++;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAktif() {
        if (this.status == 1) {
            return "AKTIF";
        } else {
            return "PASIF";
        }
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<Classes> getClassesCollection() {
        return classesCollection;
    }

    public void setClassesCollection(Collection<Classes> classesCollection) {
        this.classesCollection = classesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Courses[ courseId=" + courseId + " ]";
    }

    public Terms getTermId() {
        return termId;
    }

    public void setTermId(Terms termId) {
        this.termId = termId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
