package Models;

import Models.Courses;
import Models.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-28T12:05:54")
@StaticMetamodel(Classes.class)
public class Classes_ { 

    public static volatile SingularAttribute<Classes, Integer> classesId;
    public static volatile SingularAttribute<Classes, Integer> grade;
    public static volatile SingularAttribute<Classes, Courses> courseId;
    public static volatile SingularAttribute<Classes, Users> userId;

}