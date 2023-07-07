/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2021.Framework.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import etu2021.Framework.Mapp.Mapping;
import etu2021.Framework.annotation.APIrest;
import etu2021.Framework.annotation.Auth;
import etu2021.Framework.annotation.Scope;
import etu2021.Framework.annotation.Session;
import etu2021.Framework.annotation.Url;
import etu2021.Framework.loadview.ModelView;
import etu2021.Framework.upload.FileUploads;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

/**
 *
 * @author johary
 */
 @MultipartConfig(
    // Chemin où les fichiers téléchargés seront temporairement stockés
    
)
public class FrontServlet extends HttpServlet {
    
      
    HashMap<String,Mapping> MappingUrls=new HashMap <String,Mapping> ();
    HashMap<Class,Object> singleton=new HashMap<Class,Object>();
    String packages;
    String connected;
    String profil;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param config
     * @throws ServletException if a servlet-specific error occurs
     */
    
   
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
       packages= getServletConfig().getInitParameter("modelPackage");
       this.connected=getServletConfig().getInitParameter("connected");
       this.profil=getServletConfig().getInitParameter("profil");    
        try{
            this.scanget(packages);
            this.getSingleton();
        }catch(URISyntaxException e){
        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, URISyntaxException, ClassNotFoundException {
          String url = request.getRequestURL().toString();
           String requete=request.getQueryString();
            String uri = request.getRequestURI();
            Map <String,String[]>parameterValue=request.getParameterMap();
            String page = uri.substring(uri.lastIndexOf("/") + 1);
             PrintWriter outs = response.getWriter(); 
            
        if(requete!=null || page!=null){
            url=url+"?"+requete;
         
           // String [] sp=requete.split("=");
             for(Map.Entry<String,Mapping> entry:this.MappingUrls.entrySet()){
                String keys=entry.getKey();
                Mapping mas=entry.getValue();
                  
                     
               if(keys.compareTo(page)==0){
                   try{
                      Class<?> clazz = Class.forName(packages+"."+mas.getClassName());  
                      Object ob=this.checkSingleton(clazz);
                      Method meth=this.checkfonction(ob, mas.getMethods());
                      this.checkAuthenf(request, response, meth);
                      if(meth.getReturnType()==ModelView.class){
                     if(!parameterValue.isEmpty()){
                       //  System.out.println("Nadalo Condition");
                      // System.out.println(ob.getClass().getMethod("getnom").invoke(ob));
                      // Method meth=ob.getClass().getMethod(mas.getMethods());
                      ModelView mod=(ModelView)this.prepareFonction(parameterValue, ob, meth,request,response);
                       //outs.println("WEB-INF"+mod.getUrl());
                       this.preapareSession(request, response, mod);
                       this.prepareview(request, response, mod, meth);
                       /*for(Map.Entry<String,Object> e: mod.getData().entrySet()){
                            String k=e.getKey();
                            Object o=e.getValue();
                            request.setAttribute(k, o);
                       }
                       String chemin=   mod.getUrl();
                       outs.println(chemin);
                       RequestDispatcher dispat =request.getRequestDispatcher(chemin);
                       dispat.forward(request,response);*/
                       this.prepareDispatch(request, response, mod);
                     
                     
                     }else{
                      // Method meth=ob.getClass().getMethod(mas.getMethods());
                      //System.out.println("Nom fonction"+meth.getName());   
                      ModelView mod=(ModelView) meth.invoke(ob);
                       //outs.println("WEB-INF"+mod.getUrl());
                        this.preapareSession(request, response, mod);
                        this.prepareview(request, response, mod, meth);
                       /*for(Map.Entry<String,Object> e: mod.getData().entrySet()){
                            String k=e.getKey();
                            Object o=e.getValue();
                            request.setAttribute(k, o);
                       }
                       String chemin=   mod.getUrl();
                       outs.println(chemin);
                       RequestDispatcher dispat =request.getRequestDispatcher(chemin);
                       dispat.forward(request,response);*/
                       this.prepareDispatch(request, response, mod);
                     }
                      }else{
                       this.checkVerifFonction(request, response, parameterValue, meth, ob);
                      }         
                      
                   }catch (ClassNotFoundException e) {
                       outs.println(e.getMessage());
                        e.printStackTrace(outs);
                      //e.printStackTrace();
        }           catch (IllegalArgumentException | InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException ex) {
                        Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
                        outs.println(ex.getMessage());
                        ex.printStackTrace(outs);
                         //ex.printStackTrace();
                    } catch (Exception ex) {
                        Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
                           ex.printStackTrace(outs);
                            //ex.printStackTrace();
                             outs.println(ex.getMessage());
                    }
               }
               
            }
        }
           PrintWriter out = response.getWriter();
         //  out.println("<h1>"+requete+"</h1>"+"<br>");
          out.println("<h1>"+url+"</h1>");
            for (Map.Entry<String, String[]> entry : parameterValue.entrySet()) {
                       //  outs.print(entry.getKey());
                         outs.print(entry.getValue()[0]);
                     }
            for(Map.Entry<String,Mapping> entry:this.MappingUrls.entrySet()){
                String key=entry.getKey();
                Mapping ma=entry.getValue();
                out.println("Url :"+ key+"  /"+"className:"+ma.getClassName()+"   "+"  /"+"Methode:"+ma.getMethods()+"<br>"+"<br>");
               
            }
         }
    /* Fonction scan package*/
    public void scanget(String packagename) throws URISyntaxException{
    String path=packagename.replaceAll("[.]", "/");
    URL packageURL=Thread.currentThread().getContextClassLoader().getResource(path);
    File packageDirectory=new File(packageURL.toURI());
    File [] inside=packageDirectory.listFiles();
    for (int i = 0; i < inside.length; i++) {
                String [] n=inside[i].getName().split("[.]");
                
             try{
              Class<?> clazz = Class.forName(packagename+"."+n[0]);
              for(int j=0;j<clazz.getMethods().length;j++){
              if(clazz.getMethods()[j].isAnnotationPresent(Url.class)){
                Url u=clazz.getMethods()[j].getAnnotation(Url.class);
                String cle=u.lien();
                String classN=clazz.getSimpleName();
                String fonction=clazz.getMethods()[j].getName();
                Mapping m=new Mapping(classN,fonction);
                this.MappingUrls.put(cle, m);
              }
              }
              
              
              
             }catch (ClassNotFoundException e) {
                 
        }
            
       }
    }
    /*Maka Ny class Izay manana annotation Scope  */
    public void getSingleton() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    for(Map.Entry<String,Mapping> entry:this.MappingUrls.entrySet()){
        Mapping m=entry.getValue();
        Class clazz=Class.forName(packages+"."+m.getClassName());
        if(clazz.isAnnotationPresent(Scope.class)){
            Scope scopeAnnotation =  (Scope) clazz.getAnnotation(Scope.class);
            boolean isSingletons = scopeAnnotation.isSingleton();
            
            if(isSingletons==true){
                Object ob=clazz.newInstance();
                this.singleton.put(clazz, ob);
                
            }
        
        }
    }
    }
    
    public Object checkSingleton(Class clazz) throws InstantiationException, IllegalAccessException{
        for(Map.Entry<Class,Object> entry:this.singleton.entrySet()){
            Class cle=entry.getKey();
            Object ob=entry.getValue();
            if(clazz.equals(cle)){
                System.out.println("Singleton");
               resetToDefault(ob);
               return ob;
            }
        }
        return clazz.newInstance();
    
    }
    /*Rendre Par defaut tous les attribut de l'objet*/
    public static  void resetToDefault(Object obj) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    Object defaultValue = getDefaultValue(fieldType);
                    field.set(obj, defaultValue);
                }
            
               
            
        }
    }
    public static  Object getDefaultValue(Class<?> type) {
        if (type.equals(int.class)|| type.equals(Integer.class)) {
            return 0;
        } else if (type.equals(boolean.class)||type.equals(Boolean.class) ) {
            return false;
        } else if (type.equals(byte.class)|| type.equals(Byte.class)) {
            return (byte) 0;
        
        } else if (type.equals(double.class) ||type.equals(Double.class) ) {
            return 0.0;
        } else {
            return null;
        }
    }
    
    public Method checkfonction(Object ob,String name){
        Method[] fonctions=ob.getClass().getDeclaredMethods();
        for(int i=0;i<fonctions.length;i++){
            if(fonctions[i].getName().compareTo(name)==0){
                return fonctions[i];
            }
        }
        return null;
    }
    public  void caster(Class typeEnter,String value,String cle,Object ob,Method fonction,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("Type: "+typeEnter.getSimpleName());
    if(typeEnter==Integer.class){
      // Integer.valueOf(value);
       Object[] argument = new Object[1];
       argument[0] = Integer.valueOf(value);
       fonction.invoke(ob,  argument);
    }else if(typeEnter==int.class){
      //  Integer.valueOf(value);
      Object[] argument = new Object[1];
       argument[0] = Integer.valueOf(value);
       fonction.invoke(ob,  argument);
    }else if(typeEnter==Double.class){
        // Double.valueOf(value);
          fonction.invoke(ob, Double.valueOf(value) );
    }else if(typeEnter==double.class){
       //  Double.valueOf(value);
       fonction.invoke(ob, Double.valueOf(value) );
    }else if(typeEnter==Date.class){
        SimpleDateFormat format=new SimpleDateFormat ("yyyy-MM-dd");
      fonction.invoke(ob,  format.parse(value));
    }
    else if(typeEnter==String.class){
     fonction.invoke(ob, value);
    }
    
    }
    
    
    public  Object cast(Class typeEnter,String value,String cle,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("Type: "+typeEnter.getSimpleName());
    if(typeEnter==Integer.class){
      // Integer.valueOf(value);
     
       return Integer.valueOf(value);
      
    }else if(typeEnter==int.class){
      //  Integer.valueOf(value);
    
       return Integer.valueOf(value);
      
    }else if(typeEnter==Double.class){
        return Double.valueOf(value);
          
    }else if(typeEnter==double.class){
       return  Double.valueOf(value);
      
    }else if(typeEnter==Date.class){
        SimpleDateFormat format=new SimpleDateFormat ("yyyy-MM-dd");
        return format.parse(value);
    }else {
        return value;
    }
    
    }
    /*Cherche tous les attributs de l'objet et le compare s'il possede le meme nom que les inputs*/
    public  void affectationvaleur(Map<String,String[]> valeur,Object ob,HttpServletRequest request, HttpServletResponse response) throws Exception{
       Field[] attribut=ob.getClass().getDeclaredFields();
       Method[] fonction=ob.getClass().getDeclaredMethods();
       for (Map.Entry<String, String[]> entry : valeur.entrySet()) {
       String nomImput=entry.getKey();
       String[] valeurInput=entry.getValue();
        System.out.println( "NomInput:"+nomImput);
       for(int i=0;i<attribut.length;i++){
           if(attribut[i].getName().compareTo(nomImput)==0){
              System.out.println( "Attribut: "+attribut[i].getName());
               for(int j=0;j<fonction.length;j++){
                     //System.out.println(fonction[j].getName());
                   if(fonction[j].getName().compareTo("set"+attribut[i].getName())==0){
                     //  fonction[j].invoke(ob, valiny);
                      System.out.println( "val:"+valeurInput[0]);
                       System.out.println( "fonction: "+fonction[j].getName());
                       this.caster(attribut[i].getType(),valeurInput[0],nomImput,ob,fonction[j],request,response);
                        //System.out.println("ok");
                       ///System.out.println(fonction[j].getName());
                   }
               }
           }
       }
       }
       this.checkFileUpload(ob, request, response);
    }
    public Object prepareFonction(Map<String,String[]> valeur,Object ob,Method fonction,HttpServletRequest request, HttpServletResponse response) throws Exception{
        Parameter[] parameters=fonction.getParameters();
        Object[] argument=new Object[parameters.length];
        if(parameters.length!=0){
            for(int i=0;i<parameters.length;i++ ){
                 if(parameters[i].getType()!=FileUploads.class){
                    for (Map.Entry<String, String[]> entry : valeur.entrySet()) {
                    String nomInput=entry.getKey();
                    String[] val=entry.getValue();
                        if(parameters[i].getName().compareTo(nomInput)==0){

                             argument[i]=this.cast(parameters[i].getType(), val[0],nomInput,request,response);
                         }
                    } 
                }else if(parameters[i].getType()==FileUploads.class){
                    argument[i]=this.preparefile(parameters[i].getName(), request, response);
                }
            }
            return fonction.invoke(ob,argument);
        }else{
            this.affectationvaleur(valeur, ob,request,response);
           // ModelView v=(ModelView)fonction.invoke(ob);
            return fonction.invoke(ob);
        }
        
    }
    public void checkFileUpload(Object ob,HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException, Exception{
    Field[] attribut=ob.getClass().getDeclaredFields();
    Method[] fonction=ob.getClass().getDeclaredMethods();
    for(int i=0;i<attribut.length;i++){
        if(attribut[i].getType()==FileUploads.class){
            for(int j=0;j<fonction.length;j++){
                if(fonction[j].getName().compareTo("set"+attribut[i].getName())==0){
                   FileUploads f= this.preparefile( attribut[i].getName(), request, response);
                    fonction[j].invoke(ob,f);
                }
            }
        }
        
    }
    }
    public FileUploads preparefile(String nom,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception{
        FileUploads fichier=new FileUploads();
        Part filePart = request.getPart(nom);
        if (filePart != null) {
              String submittedFileName = filePart.getSubmittedFileName();
              System.out.println(nom);
              fichier.setnom(submittedFileName );
              InputStream fileInputStream = filePart.getInputStream();
              ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

              byte[] buffer = new byte[4096];
              int bytesRead;
               while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    byteOutputStream.write(buffer, 0, bytesRead);
                }
    
            byte[] fileBytes = byteOutputStream.toByteArray();

            Byte[] fileBytesWrapper = new Byte[fileBytes.length];
            for (int i = 0; i < fileBytes.length; i++) {
                fileBytesWrapper[i] = Byte.valueOf(fileBytes[i]);
            }
            fichier.setbit(fileBytesWrapper);
            fileInputStream.close();
            byteOutputStream.close();
            return fichier;
        }else{
            throw new Exception("le fichier est vide");
        }
    }
    
    public void preapareSession(HttpServletRequest request, HttpServletResponse response,ModelView v){
        HttpSession session = request.getSession(true);
        if(v.getAuthenf().size()!=0){
             for (Map.Entry<String, Object> entry : v.getAuthenf().entrySet()) {
                 session.setAttribute(entry.getKey(), entry.getValue());
             }
        }
    
    
    }
    public void checkAuthenf(HttpServletRequest request, HttpServletResponse response,Method m) throws Exception{
        if(m.isAnnotationPresent(Auth.class)){
         this.verifConnected(request, response);
          Auth authenf= (Auth) m.getAnnotation(Auth.class);
          String authen=authenf.authentification();
          if(authen.compareTo("")!=0){
               HttpSession session = request.getSession(false);
               String profil=(String) session.getAttribute(this.profil);
               if(profil.compareTo(authen)!=0){
                   throw new Exception("Accès Refusé");
               }
          
          }
        }
    }
    
    public void verifConnected(HttpServletRequest request, HttpServletResponse response) throws Exception{
      HttpSession session = request.getSession(false);
      if(session.getAttribute(this.connected)==null){
          throw new Exception("Vous etes pas Connecte");
      }
    
    }
    
    
    public void prepareview(HttpServletRequest request, HttpServletResponse response,ModelView v,Method m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Annotation[] annotations = m.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        if(m.isAnnotationPresent(Session.class)){
           System.out.println("Nandalo fa session");
         
          HttpSession session = request.getSession();
         Enumeration<String> sessionNames = session.getAttributeNames();
        
         while (sessionNames.hasMoreElements()) {
            String sessionName = sessionNames.nextElement();
            System.out.println("Nom Session: "+sessionName);
            v.addSession(sessionName, session.getAttribute(sessionName ));
        }
         v.addItem("session", v.getSession());/* Reuperation du HashMap Session qui contient tous les session */
         //return v ;
       }
    
    }
    
    public void prepareDispatch(HttpServletRequest request, HttpServletResponse response,ModelView v) throws ServletException, IOException{
        if(v.getisJson()==false){
            for(Map.Entry<String,Object> e: v.getData().entrySet()){
                            String k=e.getKey();
                            Object o=e.getValue();
                            request.setAttribute(k, o);
            }
            String chemin=   v.getUrl();
            System.out.println(chemin);
            RequestDispatcher dispat =request.getRequestDispatcher(chemin);
            dispat.forward(request,response);
        }else{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(v.getData());
        PrintWriter out = response.getWriter();
        out.println(json);
        }
    }
    
    public void checkVerifFonction(HttpServletRequest request, HttpServletResponse response,Map<String,String[]> valeur,Method fonction,Object ob) throws Exception{
        
        if(fonction.isAnnotationPresent(APIrest.class)&& !fonction.getReturnType().equals(Void.TYPE)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if(!valeur.isEmpty()){
                 Object obj=this.prepareFonction(valeur, ob, fonction, request, response);
                  String json = gson.toJson(obj);
                  PrintWriter out = response.getWriter();
                  out.println(json);
            }else{
                Object obj=fonction.invoke(ob);
                String json = gson.toJson(obj);
                PrintWriter out = response.getWriter();
                out.println(json);
            }
        }else{
          throw new Exception("La fonction retourne un Void ou ne Possede pas l'annotation APIrest");
        }
    
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException | ClassNotFoundException  ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException | ClassNotFoundException  ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
