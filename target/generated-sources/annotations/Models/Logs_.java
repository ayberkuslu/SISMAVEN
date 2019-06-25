package Models;

import Models.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-25T09:26:05")
@StaticMetamodel(Logs.class)
public class Logs_ { 

    public static volatile SingularAttribute<Logs, Integer> eventCode;
    public static volatile SingularAttribute<Logs, Date> date;
    public static volatile SingularAttribute<Logs, Integer> logId;
    public static volatile SingularAttribute<Logs, String> detail;
    public static volatile SingularAttribute<Logs, Users> userId;

}