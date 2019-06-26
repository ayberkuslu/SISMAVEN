package Models;

import Models.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-26T14:09:47")
@StaticMetamodel(UserDetails.class)
public class UserDetails_ { 

    public static volatile SingularAttribute<UserDetails, Date> birthday;
    public static volatile SingularAttribute<UserDetails, String> secretQuestion;
    public static volatile SingularAttribute<UserDetails, String> gender;
    public static volatile SingularAttribute<UserDetails, Double> currentGpa;
    public static volatile SingularAttribute<UserDetails, Integer> detailId;
    public static volatile SingularAttribute<UserDetails, String> adress;
    public static volatile SingularAttribute<UserDetails, Users> userId;
    public static volatile SingularAttribute<UserDetails, String> secretAnswer;
    public static volatile SingularAttribute<UserDetails, String> master;
    public static volatile SingularAttribute<UserDetails, String> phone;
    public static volatile SingularAttribute<UserDetails, String> graduate;
    public static volatile SingularAttribute<UserDetails, String> emergencyPhone;
    public static volatile SingularAttribute<UserDetails, Date> registerDate;

}