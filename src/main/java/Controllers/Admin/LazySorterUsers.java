/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Models.Users;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp_user
 */
public class LazySorterUsers implements Comparator<Users> {

    private final String sortField;

    private final SortOrder sortOrder;

    public LazySorterUsers(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Users user1, Users user2) {
        try {
            int value = user1.getUserId() - user2.getUserId();
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException("for User compare @");
        }
    }
}
