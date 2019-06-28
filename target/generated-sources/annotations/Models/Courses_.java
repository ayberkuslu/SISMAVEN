package Models;

import Models.Classes;
import Models.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-28T12:05:54")
@StaticMetamodel(Courses.class)
public class Courses_ { 

    public static volatile SingularAttribute<Courses, Integer> termId;
    public static volatile CollectionAttribute<Courses, Classes> classesCollection;
    public static volatile SingularAttribute<Courses, Integer> courseName;
    public static volatile SingularAttribute<Courses, Integer> maxSize;
    public static volatile SingularAttribute<Courses, Integer> courseId;
    public static volatile SingularAttribute<Courses, Users> userId;
    public static volatile SingularAttribute<Courses, Integer> status;
    public static volatile SingularAttribute<Courses, Integer> currentSize;

}