/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.user;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amira
 */
public class user_service {
        public ArrayList<user> users;
    public static user_service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public user_service() {
        req = new ConnectionRequest();
    }

     
    public static user_service getInstance() {
        if (instance == null) {
            instance = new user_service();
        }
        return instance;
    }  
    
    
    public ArrayList<user> parseUser(String jsonText) {
        try {
            
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                
                
                

               int id = (int)Float.parseFloat(obj.get("id").toString());
          
               
             
           
              String nom =obj.get("nom").toString();
                String prenom =obj.get("prenom").toString();
                 String adresse =obj.get("adresse").toString();
             String email =obj.get("email").toString();
           String password =obj.get("password").toString();
                String Photo =obj.get("Photo").toString();    
                Boolean role = Boolean.valueOf(obj.get("role").toString());
        
               
               
     


                users.add(new user(id, nom, prenom, adresse, email, password, Photo, role));
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return users;
    }

     public user  get_User(int id)
      {
          for( user u :getInstance().findAll())
          {
          
              if (u.getId()==id)
              {
               
                  return u;
              }
          }
          return null;
  
      }
        
    public ArrayList<user> findAll() {
        String url = Statics.BASE_URL + "usr/user_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

        public void deleteUser(int id) {
        String url = Statics.BASE_URL + "usr/delete_user_mobile?id=" + id;
     req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
        
           public void Adduser(user c) {
        String url = Statics.BASE_URL + "usr/newuser_mobile/"+c.getNom()+"/"+c.getPrenom()+"/"+c.getAdresse()+"/"+c.getEmail()+"/"+c.getPassword()+"/"+c.getPhoto()+"/"+c.getRole();

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
              public void edituser(user c) {
        String url = Statics.BASE_URL + "usr/edituser_mobile/"+c.getId()+"/"+c.getNom()+"/"+c.getPrenom()+"/"+c.getAdresse()+"/"+c.getEmail()+"/"+c.getPassword()+"/"+c.getPhoto()+"/"+c.getRole();

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
        
            /*    
       public boolean ModifierOffre( Offre c) {
        
       String url = Statics.BASE_URL + "updateOffre?id=" + c.getId()+"&Nom=" + c.getNom()+"&Pourcentage=" + c.getPourcentage();

       
       
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }

       
   */    
       
       
       
}
