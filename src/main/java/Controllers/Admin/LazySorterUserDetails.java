/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Models.UserDetails;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp_user
 */
public class LazySorterUserDetails implements Comparator<UserDetails> {
 
    private final String sortField;
  
    private final SortOrder sortOrder;
     
    public LazySorterUserDetails(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @Override
    public int compare(UserDetails userDetail1, UserDetails userDetail2) {
        try {
            Object value1 = UserDetails.class.getField(this.sortField).get(userDetail1);
            Object value2 = UserDetails.class.getField(this.sortField).get(userDetail2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("for UserDetail @");
        }
    }
}