package Controllers.Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Controllers.*;
import Models.UserDetails;
import Models.Users;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hp_user
 */
@ManagedBean
@ViewScoped
public class KullaniciEkleController extends Controller{
// user

    private String tckno;
    private String name;
    private String surname;
    private String password;
    private String email;
    private boolean status;
    private String type;

    //userDetail
    private String phone;
    private String adress;
    private Date birthday;
    private String gender = "MALE";
    private Double currentGpa;
    private String graduate;
    private String master;
    private String emergencyPhone;
    private String secretQuestion;
    private String secretAnswer;
    private Date registerDate;
    private Users userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTckno() {
        return tckno;
    }

    public void setTckno(String tckno) {
        this.tckno = tckno;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    /**
     * Creates a new instance of KullaniciEkleController
     */
    public KullaniciEkleController() {
    }

    public void insertNewUser() { // DENENMEDI , TODO DENE
        System.out.println("insertNewUser()");
        FacesContext context = FacesContext.getCurrentInstance();

        int parse = 0;
        try {
            parse = Integer.parseInt(type);

        } catch (NumberFormatException e) {
            context.addMessage(null, new FacesMessage(e.toString() + "\nCan be wrong TYPE."));
        }

//github commit test
        Users user = new Users();

        user.setTckno(tckno);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setStatus(true);
        user.setType(parse);

        UserDetails userDetail = new UserDetails();

        if (gender.toLowerCase().charAt(0) == 'm') {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }

        userDetail.setPhone(phone);
        userDetail.setAdress(adress);
        userDetail.setBirthday(birthday);
        userDetail.setGender(gender.toUpperCase());
        userDetail.setGraduate(graduate);
        userDetail.setMaster(master);
        userDetail.setEmergencyPhone(emergencyPhone);
        userDetail.setSecretQuestion(secretQuestion);
        userDetail.setRegisterDate(new Date());
        userDetail.setUserId(user);

        try {
            insertObject(user);
            insertObject(userDetail);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Kullanici Ekleme BASARİSİZ."));
           getSession().getTransaction().rollback();
            return;
        }
        context.addMessage(null, new FacesMessage(user.getUserId() + " Numarali Kullanici Eklendi."));

    }

}
