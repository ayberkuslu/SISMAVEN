/*
 * Copyright (C) 2019 hp_user.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package Controllers;



import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ayberk
 */
@ManagedBean
@RequestScoped
public class ErrorPageController {

    /**
     * Creates a new instance of ErrorPageController
     */
    public ErrorPageController() {
    }
    
    public static void goHomePage(){
        System.out.println("goHomePage()");
        
        
        FacesContext context = FacesContext.getCurrentInstance();

        
        try {
            context.getExternalContext().redirect(Controller.PAGE_HOME);
        } catch (IOException ex) {
            System.out.println(ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", ""));
        }
          
    
    }
    
}
