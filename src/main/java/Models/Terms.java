/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp_user
 */
@Entity
@Table(name = "terms")
@NamedQueries({
    @NamedQuery(name = "Terms.findAll", query = "SELECT t FROM Terms t"),
    @NamedQuery(name = "Terms.findByTermId", query = "SELECT t FROM Terms t WHERE t.termId = :termId"),
    @NamedQuery(name = "Terms.findByStartDate", query = "SELECT t FROM Terms t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Terms.findByEndDate", query = "SELECT t FROM Terms t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Terms.findByTermName", query = "SELECT t FROM Terms t WHERE t.termName = :termName"),
    @NamedQuery(name = "Terms.findByStatus", query = "SELECT t FROM Terms t WHERE t.status = :status")})
public class Terms implements Serializable {

    @OneToMany(mappedBy = "currentTerm", fetch = FetchType.EAGER)
    private Collection<RuntimeProperties> runtimePropertiesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<Courses> coursesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TERM_ID")
    private Integer termId;
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "TERM_NAME")
    private String termName;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private int status;
    
    public static final int OPEN = 1;
    public static final int CLOSE = 0;
    

    public Terms() {
    }

    public Terms(Integer termId) {
        this.termId = termId;
    }

    public Terms(Integer termId, Date startDate, String termName, int status) {
        this.termId = termId;
        this.startDate = startDate;
        this.termName = termName;
        this.status = status;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getAktif(){
        if(this.status == 1) return "AKTIF";
        else return "PASIF";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (termId != null ? termId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terms)) {
            return false;
        }
        Terms other = (Terms) object;
        if ((this.termId == null && other.termId != null) || (this.termId != null && !this.termId.equals(other.termId))) {
            return false;
        }
        return other.termId.intValue() == this.termId.intValue();
    }

    @Override
    public String toString() {
        return this.termName + "[" + this.termId.toString() +"]";
    }

    @XmlTransient
    public Collection<Courses> getCoursesCollection() {
        return coursesCollection;
    }

    public void setCoursesCollection(Collection<Courses> coursesCollection) {
        this.coursesCollection = coursesCollection;
    }



    @XmlTransient
    public Collection<RuntimeProperties> getRuntimePropertiesCollection() {
        return runtimePropertiesCollection;
    }

    public void setRuntimePropertiesCollection(Collection<RuntimeProperties> runtimePropertiesCollection) {
        this.runtimePropertiesCollection = runtimePropertiesCollection;
    }
    
}
