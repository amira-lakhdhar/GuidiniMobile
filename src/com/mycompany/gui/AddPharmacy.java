/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Pharmacy;
import com.mycompany.services.Service_Pharmacy;
import java.io.IOException;
import java.util.Date;
/**
 *
 * @author amira
 */
public class AddPharmacy extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddPharmacy(Form previous) throws IOException {
      super("Add Pharmacy", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("Add Pharmacy");

        this.add(AJOUT);
            

            
            
        TextField nom = new TextField("", "Nom ", 20, TextArea.TEXT_CURSOR);
         
        TextField location = new TextField("", "location", 20, TextArea.TEXT_CURSOR);
         TextField hourly = new TextField("", "hourly", 20, TextArea.TEXT_CURSOR);
             TextField phone = new TextField("", "phone", 20, TextArea.TEXT_CURSOR);
            TextField email = new TextField("", "email", 20, TextArea.TEXT_CURSOR);


                      
        Button save = new Button("Ajouter");
      
        
        
        
        this.add("Nom : ").add(nom);
        
        
        this.add("location : ").add(location);
        
                this.add("hourly : ").add(hourly);
        this.add("phone : ").add(phone);
        this.add("email : ").add(email);
       
 
        this.add(save);
        save.addActionListener(l
                                -> {

            
            
            
            // les controles de saisi
                            if (nom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Name  ", "OK", null);

                            }  else if (location.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Description de  location ", "OK", null);

                                
                            }  else if (hourly.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Description de hourly ", "OK", null);

                                
                            }  else if (phone.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Description phone ", "OK", null);

                                
                            }   else if (email.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Description email ", "OK", null);

                                
                            }  
               
               
                            
                            
                            else {
                           
                                try {
                                    
                                
                                    Pharmacy c = new Pharmacy();
                                    
                                    
                                
                                    c.setName(nom.getText());
                                    c.setLocation(location.getText());
                                      c.setHourly(hourly.getText());
                                      c.setPhone(phone.getText());
                                      c.setEmail(email.getText());

                                  
                         
                         
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Pharmacy().AddPharmacy(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter Pharmacie", "Ajouter Pharmacie aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Nom    "+c.getName()+"  ajoute avec succes");
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
                   new PharmacyForm(this).showBack();
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
