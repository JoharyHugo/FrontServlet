/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2021.Framework.loadview;

import java.util.HashMap;

/**
 *
 * @author johary
 */
public class ModelView {
    String Url="View/";
    HashMap<String,Object> data=new HashMap<>();

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
    
    public void addItem(String keys,Object value){
        
        this.data.put(keys, value);
    }
}
