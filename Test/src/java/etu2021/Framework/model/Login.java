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
public class Login {
    String Name;
    String Mdp;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String mdp) {
        this.Mdp = mdp;
    }
    @Url(lien="Connect")
    public ModelView Connected(){
        ModelView v=new ModelView();
        String jsp="redirection.jsp";
        v.setUrl(jsp);
        v.addAuthenf("isconnected", true);
        v.addAuthenf("profil", this.Name);
          //v.addAuthenf("mdp", this.Mdp);
        return v;
    }
}
