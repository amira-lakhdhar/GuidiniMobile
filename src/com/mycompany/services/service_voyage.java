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
import com.mycomany.entities.Voyage;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amira
 */
public class service_voyage {
      
      public ArrayList<Voyage> Voyages;
    public static service_voyage instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;
    public service_voyage() {
        req = new ConnectionRequest();
    }

     
    public static service_voyage getInstance() {
        if (instance == null) {
            instance = new service_voyage();
        }
        return instance;
    }  
    
    
    public ArrayList<Voyage> parseVoyage(String jsonText) {
        try {
            
            Voyages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
               
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
    
               
          
               



                Voyages.add(new Voyage((int) id, obj.get("description").toString(),Float.parseFloat(obj.get("Prix").toString()) ));
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Voyages;
    }

    
        
    public ArrayList<Voyage> findAll() {
        String url = Statics.BASE_URL + "voyage_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Voyages = parseVoyage(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Voyages;
    }
    
    
    
    
    
    
    
        
    
            public void AddVoyage(Voyage c) {
        String url = Statics.BASE_URL + "newvoyage_mobile/"+ c.getDescription()+ "/" + c.getPrix(); //création de l'URL

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
            
            
            
            
            
            
            
            
            
                        
            
        public boolean deletevoyage(int id) {
        String url = Statics.BASE_URL + "delete_voyage_mobile?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
        
        
        
        
        
        
        
        
        
                
       public boolean ModifierVoyage( Voyage c) {
        
       String url = Statics.BASE_URL + "updateVoyage?id=" + c.getId()+"&description=" + c.getDescription()+"&prix=" + c.getPrix();

       
       
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }

       
       
       
       
       
       
       
       
       
     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    
       
    
    
}
