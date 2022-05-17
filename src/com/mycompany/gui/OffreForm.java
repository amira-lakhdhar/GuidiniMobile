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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Offre;
import com.mycompany.services.Service_Offre;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author amira
 */
public class OffreForm extends BaseForm{
       
    
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public OffreForm(Form previous)
    {
     
     
           super("Offre",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                   
             for (Offre c : new Service_Offre().findAll()) {
            this.add(addItem_Coach(c));
        }
               this.revalidate();
            });
        });
        
        //search

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
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
     
        
              
           this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
                   new AddOffre(this).show();
               } catch (Exception ex) {
            
               }
               
               
               
        });
           

    }
    
 
    
     public Container addItem_Cotch_detail(Offre c) {
       
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        
        
        
                      Label Pourcentage = new Label("Pourcentage  : "+c.getPourcentage());       
  
                        

        
                     Label Nom = new Label("Nom  : "+c.getNom());
        
            

       
       
        Button screen = new Button("Screen");

                Button Stat = new Button("Show Stat");

        
        
        cn2.add(Nom).add(Pourcentage).add(screen).add(Stat);
        cn1.add(BorderLayout.WEST, cn2);
        
        
        
        
               
    Stat.addActionListener((x) -> {                        
        new StatOffre().createPieChartForm("Produits", new Service_Offre().getStat(), theme);       
        });
        
        
    //api screen
        screen.addActionListener(e -> {
            
             Form form = Display.getInstance().getCurrent();
        if (form != null) {
            
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
form.revalidate();
form.setVisible(true);
form.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
        }  
            
            });

        return cn1;

    }
     
     
   public MultiButton  addItem_Coach(Offre c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
//api toast
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des offres");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               

            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                // image_coach = new ImageViewer(imge);
                
                
        
        Label nom = new Label("Nom  : "+c.getNom());
       
      
       
       m.setTextLine1(c.getNom());
          
        m.setEmblem(theme.getImage("round.png"));
      
           // m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            f2.add(addItem_Cotch_detail(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new OffreForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
}
