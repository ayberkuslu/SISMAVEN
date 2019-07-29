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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ayberk
 */
@Entity
@Table(name = "runtime_properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RuntimeProperties.findAll", query = "SELECT r FROM RuntimeProperties r"),
    @NamedQuery(name = "RuntimeProperties.findByRunTimeId", query = "SELECT r FROM RuntimeProperties r WHERE r.runTimeId = :runTimeId"),
    @NamedQuery(name = "RuntimeProperties.findByIsOpenAddDrop", query = "SELECT r FROM RuntimeProperties r WHERE r.isOpenAddDrop = :isOpenAddDrop")})
public class RuntimeProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RUN_TIME_ID")
    private Integer runTimeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_OPEN_ADD_DROP")
    private boolean isOpenAddDrop;
    @JoinColumn(name = "CURRENT_TERM", referencedColumnName = "TERM_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Terms currentTerm;

    public RuntimeProperties() {
    }

    public RuntimeProperties(Integer runTimeId) {
        this.runTimeId = runTimeId;
    }

    public RuntimeProperties(Integer runTimeId, boolean isOpenAddDrop) {
        this.runTimeId = runTimeId;
        this.isOpenAddDrop = isOpenAddDrop;
    }

    public Integer getRunTimeId() {
        return runTimeId;
    }

    public void setRunTimeId(Integer runTimeId) {
        this.runTimeId = runTimeId;
    }

    public boolean getIsOpenAddDrop() {
        return isOpenAddDrop;
    }

    public void setIsOpenAddDrop(boolean isOpenAddDrop) {
        this.isOpenAddDrop = isOpenAddDrop;
    }

    public Terms getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(Terms currentTerm) {
        this.currentTerm = currentTerm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (runTimeId != null ? runTimeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuntimeProperties)) {
            return false;
        }
        RuntimeProperties other = (RuntimeProperties) object;
        if ((this.runTimeId == null && other.runTimeId != null) || (this.runTimeId != null && !this.runTimeId.equals(other.runTimeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Runtime[ CurrentTerm" + currentTerm.getTermName()+ " ] Add Drop : " + isOpenAddDrop;
    }
    
}
