package Models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-25T09:26:03")
@StaticMetamodel(Terms.class)
public class Terms_ { 

    public static volatile SingularAttribute<Terms, Integer> termId;
    public static volatile SingularAttribute<Terms, Date> endDate;
    public static volatile SingularAttribute<Terms, String> termName;
    public static volatile SingularAttribute<Terms, Date> startDate;
    public static volatile SingularAttribute<Terms, Integer> status;

}