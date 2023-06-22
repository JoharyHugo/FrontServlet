/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.model;

import etu2021.Framework.annotation.Url;
import etu2021.Framework.loadview.ModelView;

/**
 *
 * @author johary
 */
public class Dept {
    String dep;

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
    
      @Url(lien="Dept_all")
    public ModelView getallDept(){
        
        String jsp="Depts.jsp";
        ModelView rep=new ModelView();
        rep.setUrl(jsp);
        return rep;
    
    }
}
