/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author hp_user
 */
@Named(value = "sideMenuController")
@RequestScoped
public class SideMenuController {

    /**
     * Creates a new instance of SideMenuController
     */
    public SideMenuController() {
    }

    public void goInsertUserPage() {
        FacesContext context = null;
        try {
             context = FacesContext.getCurrentInstance();
            String nextPage = "faces/admin/adminKullaniciEkle.xhtml";
            
            context.getExternalContext().redirect(nextPage);
        } catch (IOException ex) {
            System.out.println("goInsertUserPage() try catch fails");
                    context.addMessage(null, new FacesMessage("ERROR"));

            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
