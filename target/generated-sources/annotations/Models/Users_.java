package Models;

import Models.Classes;
import Models.Courses;
import Models.Logs;
import Models.UserDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-26T14:09:48")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Classes> classesCollection;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Logs> logsCollection;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile CollectionAttribute<Users, Courses> coursesCollection;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile CollectionAttribute<Users, UserDetails> userDetailsCollection;
    public static volatile SingularAttribute<Users, String> tckno;
    public static volatile SingularAttribute<Users, Integer> type;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Boolean> status;

}