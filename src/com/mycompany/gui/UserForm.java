/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Session;
import com.mycomany.entities.user;
import com.mycompany.services.user_service;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author amira
 */
public class UserForm extends BaseForm{
  
    
    
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public UserForm(Form previous)
    {
     
     
           super("Users",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                   
             for (user c : new user_service().findAll()) {
            this.add(addItem_User(c));
        }
               this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
               
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
     
   
           

    }
    
 
    
     
      String Image="";
   public MultiButton  addItem_User(user c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des users");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               
   String url = "http://127.0.0.1:8000/Uploads/image/" + Session.get().getPhoto();

                    Image imge;
                    EncodedImage enc;

                    enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    imge = URLImage.createToStorage(enc, url, url);
                


      
       
       m.setTextLine1(c.getNom());
       m.setTextLine2(c.getEmail());
          
        m.setEmblem(theme.getImage("round.png"));
      
           // m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail User",BoxLayout.y());
            
            
            
               String urlab = "http://localhost/mobile/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                String data = "Nom : " + c.getNom() + " prenom : " + c.getPrenom()+ " Adresse :" + c.getAdresse()+ " Email : " +c.getEmail() + " Merci pour votre confiance &#128525;";

                                cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                     Image = new String(cnreq.getResponseData());
                          

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
            
            
            
            
            
            
              String url2 = "http://localhost/mobile/" + Image;

                    Image imge2;
                    EncodedImage enc2;

                    enc2 = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    imge2 = URLImage.createToStorage(enc2, url2, url2); 
            
            
            
              Button supp = new Button("supprimer");
               supp.addActionListener(ev
                                -> {
                            new user_service().deleteUser(c.getId());
                             new UserForm(this).showBack();
                        }
                        );
              f2.add("nom : ").add(c.getNom()).add("Prenom : ").add(c.getPrenom()).add("Email : ").add(c.getEmail()).add("Adresse : ").add(c.getAdresse()).add("QR : ").add(imge2).add(imge).add(supp);

             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new UserForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
           
}
