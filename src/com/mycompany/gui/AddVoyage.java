/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycomany.entities.Voyage;
import com.mycompany.services.service_voyage;
import java.io.IOException;

/**
 *
 * @author amira
 */
public class AddVoyage extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddVoyage(Form previous) throws IOException {
      super("Add Voyage", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("Add Voyage");

        this.add(AJOUT);
            

            
        TextField description = new TextField("", "description ", 20, TextArea.TEXT_CURSOR);
         
        TextField Prix = new TextField("", "Prix", 20, TextArea.TEXT_CURSOR);
      

                      
        Button save = new Button("Ajouter");
      
          Validator val_description = new Validator();

            val_description.addConstraint(description, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_description.addConstraint(description, new RegexConstraint(text_saisir_des_caracteres, ""));
        
        
        this.add("description : ").add(description);
        
        
        this.add("Prix : ").add(Prix);
      
       
 
        this.add(save);
        save.addActionListener(l
                                -> {

                            if (description.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ description vide  ", "OK", null);

                            }  
                            
                             else if (val_description.isValid()) {
                                     com.codename1.ui.Dialog.show("Erreur Description !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                            
                            else if (Prix.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Prix vide ", "OK", null);

                                
                            }   
               
               
                            
                            
                            else {
                           
                                try {
                                    
                                
                                    Voyage c = new Voyage();
                                    
                                    
                                
                                    c.setDescription(description.getText());
               c.setPrix(Float.valueOf(Prix.getText()));

                                  
                         
          new service_voyage().AddVoyage(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter Voyage", "Ajouter Voyage aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Voyage    "+c.getDescription()+"  ajoute avec succes");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////
                                        
                                } catch (Exception ex) {
                                       System.out.println("hekllllo");
                                }

                            }

                        }
                        );
        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
        
        
        
        
        
        
        
        
           
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new VoyageForm(this).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        
        
           
           
           
           
           






           
        
 this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        this.show();

             
     
     
                            

      
     
     }
     
     
     
     private void addButton(Image img, Resources res) {
         
         
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);  
        
		}
     
     
     
     
     private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");


    }  
}
