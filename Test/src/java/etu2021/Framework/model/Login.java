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
    String name;
    String mdp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    @Url(lien="connect")
    public ModelView Connected(){
        ModelView v=new ModelView();
        String jsp="redirection.jsp";
        v.setUrl(jsp);
        v.addAuthenf("isConnected", true);
        v.addAuthenf("Profil", this.name);
        return v;
    }
}
