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
import com.mycomany.entities.Pharmacy;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amira
 */
public class Service_Pharmacy {
    
      public ArrayList<Pharmacy> Coachs;
    public static Service_Pharmacy instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Pharmacy() {
        req = new ConnectionRequest();
    }

     
    public static Service_Pharmacy getInstance() {
        if (instance == null) {
            instance = new Service_Pharmacy();
        }
        return instance;
    }
    
    
    
    
    public ArrayList<Pharmacy> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Pharmacy coach = new Pharmacy();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);
               
             
           
               
                
                
        
               coach.setName(obj.get("name").toString());
               
               
               coach.setLocation(obj.get("location").toString());
               
               
               
               coach.setHourly(obj.get("hourly").toString());

                 coach.setPhone(obj.get("phone").toString());

                 coach.setEmail(obj.get("email").toString());

     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Pharmacy> findAll() {
        String url = Statics.BASE_URL + "pharmacy_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
            public void AddPharmacy(Pharmacy c) {
        String url = Statics.BASE_URL + "newpharmacy_mobile/"+ c.getName()+ "/" + c.getLocation()+ "/" +c.getHourly()+ "/" + c.getPhone()+ "/" +c.getEmail(); //création de l'URL

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
            
            
            
            
            
            
            
            
            
            
            
        public boolean deletePharmacy(int id) {
        String url = Statics.BASE_URL + "SupprimerPharmacy?id=" + id;
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
    
    
    
    
    
        
        
            public boolean ModifierPharmacie( Pharmacy c) {
        
       String url = Statics.BASE_URL + "updatePharmacie?id=" + c.getId()+"&name=" + c.getName()+"&hourly=" + c.getHourly()+"&phone=" + c.getPhone()+"&email=" + c.getEmail();

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

    
    
}
