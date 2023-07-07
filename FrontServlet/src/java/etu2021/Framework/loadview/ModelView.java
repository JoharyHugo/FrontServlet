/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.loadview;

import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author johary
 */
public class ModelView {
    String Url="View/";
    HashMap<String,Object> data=new HashMap<>();
    HashMap<String,Object>authenf=new HashMap<>();
    HashMap<String,Object> session=new HashMap<>();
    boolean isJson=false;
    boolean invalidateSession=false;
    Vector<String> removeSession=new Vector();

    public boolean getisInvalidateSession() {
        return invalidateSession;
    }

    public void setinvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public Vector<String> getRemoveSession() {
        return removeSession;
    }

    public void setRemoveSession(Vector<String> removeSession) {
        this.removeSession = removeSession;
    }
    
    public boolean getisJson() {
        return isJson;
    }

    public void setisJson(boolean isJson) {
        this.isJson = isJson;
    }
    
    
    
    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }
    
    
    
    public HashMap<String,Object> getData() {
        return this.data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
    
    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = this.Url+Url;
    }

    public HashMap<String, Object> getAuthenf() {
        return authenf;
    }

    public void setAuthenf(HashMap<String, Object> authenf) {
        this.authenf = authenf;
    }
    
    public void addItem(String keys,Object value){
        
        this.data.put(keys, value);
    }
    public void addAuthenf(String keys ,Object ob){
        this.authenf.put(keys, ob);
    }
    
    public void addSession(String keys ,Object ob){
         this.session.put(keys, ob);
    }
}
