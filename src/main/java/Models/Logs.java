/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ayberk
 */
@Entity
@Table(name = "logs")
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByLogId", query = "SELECT l FROM Logs l WHERE l.logId = :logId"),
    @NamedQuery(name = "Logs.findByEventCode", query = "SELECT l FROM Logs l WHERE l.eventCode = :eventCode")})
public class Logs implements Serializable {

    public static final int USER_LOGIN = 1;
    public static final int USER_LOGOUT = 2;
    public static final int USER_CHANGE_PASSWORD = 3;
    public static final int USER_RESET_PASSWORD = 4;
    public static final int USER_DELETE = 5;
    public static final int USER_ACTIVATE = 6;
    public static final int NEW_TERM_START = 7;
    public static final int TERM_END = 8;
    public static final int ADD_DROP_START = 9;
    public static final int ADD_DROP_END = 10;

    public static final int NEW_COURSE = 11;
    public static final int USER_NEW = 12;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOG_ID")
    private Integer logId;
    @Basic(optional = false)
    @Column(name = "EVENT_CODE")
    private int eventCode;
    @Lob
    @Column(name = "DETAIL")
    private String detail;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Logs() {
    }

    public Logs(Integer logId) {
        this.logId = logId;
    }

    public Logs(Integer logId, int eventCode) {
        this.logId = logId;
        this.eventCode = eventCode;
    }

    public Logs(Integer logId, int eventCode, String detail, Users userId) {
        this.logId = logId;
        this.eventCode = eventCode;
        this.detail = detail;
        this.userId = userId;
    }

    public Logs(int eventCode, String detail, Users userId, Date date) {
        this.eventCode = eventCode;
        this.detail = detail;
        this.userId = userId;
        this.date = date;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Logs[ logId=" + logId + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
