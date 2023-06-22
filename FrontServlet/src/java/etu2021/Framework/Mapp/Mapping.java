/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.Mapp;

/**
 *
 * @author johary
 */
public class Mapping {
    String className;
    String method;
    
     public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethods() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public Mapping(){}
    
    public Mapping(String classN,String fonction){
        this.setClassName(classN);
         this.setMethod(fonction);
     }
}
