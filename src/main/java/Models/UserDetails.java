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
 * @author hp_user
 */
@Entity
@Table(name = "user_details")
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
    @NamedQuery(name = "UserDetails.findByDetailId", query = "SELECT u FROM UserDetails u WHERE u.detailId = :detailId"),
    @NamedQuery(name = "UserDetails.findByPhone", query = "SELECT u FROM UserDetails u WHERE u.phone = :phone"),
    @NamedQuery(name = "UserDetails.findByBirthday", query = "SELECT u FROM UserDetails u WHERE u.birthday = :birthday"),
    @NamedQuery(name = "UserDetails.findByGender", query = "SELECT u FROM UserDetails u WHERE u.gender = :gender"),
    @NamedQuery(name = "UserDetails.findByCurrentGpa", query = "SELECT u FROM UserDetails u WHERE u.currentGpa = :currentGpa"),
    @NamedQuery(name = "UserDetails.findByGraduate", query = "SELECT u FROM UserDetails u WHERE u.graduate = :graduate"),
    @NamedQuery(name = "UserDetails.findByMaster", query = "SELECT u FROM UserDetails u WHERE u.master = :master"),
    @NamedQuery(name = "UserDetails.findByEmergencyPhone", query = "SELECT u FROM UserDetails u WHERE u.emergencyPhone = :emergencyPhone"),
    @NamedQuery(name = "UserDetails.findBySecretQuestion", query = "SELECT u FROM UserDetails u WHERE u.secretQuestion = :secretQuestion"),
    @NamedQuery(name = "UserDetails.findBySecretAnswer", query = "SELECT u FROM UserDetails u WHERE u.secretAnswer = :secretAnswer"),
    @NamedQuery(name = "UserDetails.findByRegisterDate", query = "SELECT u FROM UserDetails u WHERE u.registerDate = :registerDate")})
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DETAIL_ID")
    private Integer detailId;
    @Basic(optional = false)
    @Column(name = "PHONE")
    private String phone;
    @Lob
    @Column(name = "ADRESS")
    private String adress;
    @Basic(optional = false)
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private String gender;
  
    @Column(name = "CURRENT_GPA")
    private Double currentGpa;
    @Column(name = "GRADUATE")
    private String graduate;
    @Column(name = "MASTER")
    private String master;
    @Column(name = "EMERGENCY_PHONE")
    private String emergencyPhone;
    @Basic(optional = false)
    @Column(name = "SECRET_QUESTION")
    private String secretQuestion;
    @Basic(optional = false)
    @Column(name = "SECRET_ANSWER")
    private String secretAnswer;
    @Basic(optional = false)
    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public UserDetails() {
    }

    public UserDetails(Integer detailId) {
        this.detailId = detailId;
    }

    public UserDetails(Integer detailId, String phone, Date birthday, String gender, String secretQuestion, String secretAnswer, Date registerDate) {
        this.detailId = detailId;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.registerDate = registerDate;
    }

    public UserDetails(String phone, String adress, Date birthday, String gender, String graduate, String master, String emergencyPhone, String secretQuestion, String secretAnswer, Date registerDate, Users userId) {
        this.phone = phone;
        this.adress = adress;
        this.birthday = birthday;
        this.gender = gender;
        this.graduate = graduate;
        this.master = master;
        this.emergencyPhone = emergencyPhone;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.registerDate = registerDate;
        this.userId = userId;
    }
    
    

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getCurrentGpa() {
        return currentGpa;
    }

    public void setCurrentGpa(Double currentGpa) {
        this.currentGpa = currentGpa;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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
        hash += (detailId != null ? detailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.detailId == null && other.detailId != null) || (this.detailId != null && !this.detailId.equals(other.detailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.UserDetails[ detailId=" + detailId + " ]";
    }
    
}
