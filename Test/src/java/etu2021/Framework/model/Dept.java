/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.model;

import etu2021.Framework.annotation.Scope;
import etu2021.Framework.annotation.Url;
import etu2021.Framework.loadview.ModelView;

/**
 *
 * @author johary
 */
@Scope(isSingleton=false)
public class Dept {
    int iddep;
    String dep;

    public int getIddep() {
        return iddep;
    }

    public void setIddep(int iddep) {
        this.iddep = iddep;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
    
      @Url(lien="Dept_all")
    public ModelView getallDept(){
        String cle="Departement";
        String jsp="Depts.jsp";
        String nom="Dept A";
        ModelView rep=new ModelView();
        rep.addItem(cle, nom);
        rep.setUrl(jsp);
        return rep;
    
    }
}
