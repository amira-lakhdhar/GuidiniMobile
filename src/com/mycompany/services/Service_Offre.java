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
import com.mycomany.entities.Offre;
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
public class Service_Offre {
      
      public ArrayList<Offre> Offres;
    public static Service_Offre instance = null;
    public boolean resultOK=true;
    private ConnectionRequest req;
    public Service_Offre() {
        req = new ConnectionRequest();
    }

     
    public static Service_Offre getInstance() {
        if (instance == null) {
            instance = new Service_Offre();
        }
        return instance;
    }  
    
    
    public ArrayList<Offre> parseOffre(String jsonText) {
        try {
            
            Offres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Offre coach = new Offre();
                
                
                

               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);
               
             
           
               
                    float Pourcentage = Float.parseFloat(obj.get("Pourcentage").toString());
               coach.setPourcentage((int) Pourcentage);
               
             
           
                   
                
        
               coach.setNom(obj.get("Nom").toString());
               
               
     


                Offres.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Offres;
    }

    
        
    public ArrayList<Offre> findAll() {
        String url = Statics.BASE_URL + "offre_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Offres = parseOffre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Offres;
    }
    
    
    
    
    
    
    
        
    
            public void AddOffre(Offre c) {
        String url = Statics.BASE_URL + "newoffre_mobile/"+ c.getNom()+ "/" + c.getPourcentage(); //création de l'URL

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
            
            
            
            
            
            
            
            
            
                        
            
        public boolean deleteOffre(int id) {
        String url = Statics.BASE_URL + "SupprimerOffre?id=" + id;
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
        
        
        
        
        
        
        
        
        
                
       public boolean ModifierOffre( Offre c) {
        
       String url = Statics.BASE_URL + "updateOffre?id=" + c.getId()+"&Nom=" + c.getNom()+"&Pourcentage=" + c.getPourcentage();

       
       
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

       
       
       
       
       
       
       
       
       
       
       
       
       
    public ArrayList<Offre> getStat() {
        String url = Statics.BASE_URL + "offre_mobile_stat";

 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Offres = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return Offres;
    }

    
    
                  
    
    
    
    public ArrayList<Offre> parseStat(String jsonText) {
        
        try {
            Offres = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("Nom").toString();         
            int  quantite =  (int) Float.parseFloat(obj.get("Pourcentage").toString());
           
                
            
     
            
            Offre ab = new Offre();
            ab.setPourcentage((int)quantite);
            ab.setNom(LibC);
            Offres.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Offres;
    }
    
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    
       
    
    
}
