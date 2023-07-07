/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.model;

import etu2021.Framework.annotation.Auth;
import etu2021.Framework.annotation.Scope;
import etu2021.Framework.annotation.Session;
import etu2021.Framework.annotation.Url;

import etu2021.Framework.loadview.ModelView;
import etu2021.Framework.upload.FileUploads;
import java.util.Date;

/**
 *
 * @author johary
 */
@Scope(isSingleton=true)
public class Emp {
    int id;
    String nom;
    Date embauche;
    FileUploads fichier;
    
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
   
    
    public String getnom() {
        return nom;
    }

    public void setnom(String Nom) {
        this.nom = Nom;
    }

    public Date getembauche() {
        return embauche;
    }

    public void setembauche(Date embauche) {
        this.embauche = embauche;
    }

    public FileUploads getfichier() {
        return fichier;
    }

    public void setfichier(FileUploads fichier) {
        this.fichier = fichier;
    }
    
    
    @Auth(authentification="")
    @Url(lien="Emp_all")
    public ModelView getallEmp(){
       String cle="Employer";
      this.nom="Johary";
      this.id=1;
       String jsp="Emps.jsp";
        ModelView rep=new ModelView();
       rep.addItem(cle, nom);
        rep.setUrl(jsp);
        rep.setisJson(true);
        return rep;
    
    }
    @Auth(authentification="admin")
    @Url(lien="Save_Emp")
    public ModelView save(){
    String cle ="Emp";
    String jsp="Empsave.jsp";
    ModelView v=new ModelView();
    v.addItem(cle, this);
    v.setUrl(jsp);
    v.setisJson(true);
    return v;
    }
    
    @Url(lien="Arg_Emp")
    public ModelView affect(int id,String nom,Date embauche,FileUploads fichier){
    String cle ="arguEmp";
    String jsp="Emparg.jsp";
    this.setid(id);
    this.setnom(nom);
    this.setembauche(embauche);
    this.setfichier(fichier);
    ModelView v=new ModelView();
    v.addItem(cle, this);
    v.setUrl(jsp);
    return v;
    }
    @Url(lien="Sessions")
    @Session
    public ModelView testsession(){
    ModelView m=new ModelView();
    m.setUrl("Session.jsp");
    return m;
    }
}
