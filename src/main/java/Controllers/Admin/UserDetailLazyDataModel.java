/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Models.UserDetails;
import java.util.List;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hp_user
 */
public class UserDetailLazyDataModel extends LazyDataModel<UserDetails> {

    private List<UserDetails> datasource;

    public UserDetailLazyDataModel(List<UserDetails> datasource) {
        this.datasource = datasource;
    }

}
