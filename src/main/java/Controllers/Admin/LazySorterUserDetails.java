/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Models.UserDetails;
import Models.Users;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp_user
 */
public class LazySorterUserDetails implements Comparator<UserDetails> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorterUserDetails(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(UserDetails userDetail1, UserDetails userDetail2) {
        try {
            Object value1 = UserDetails.class.getField(this.sortField).get(userDetail1);
            Object value2 = UserDetails.class.getField(this.sortField).get(userDetail2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException("for UserDetail @");
        }
    }
        public int compare(Users user1, Users user2) {
        try {
            Object value1 = Users.class.getField(this.sortField).get(user1);
            Object value2 = Users.class.getField(this.sortField).get(user2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException("for User @");
        }
    }
}