/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycomany.entities.Session;
import com.mycomany.entities.user;
import com.mycompany.services.user_service;
import java.io.IOException;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    String file ;
    public ProfileForm(Resources res) {
      
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
         String url = "http://127.0.0.1:8000/Uploads/image/" + Session.get().getPhoto();

                    Image imge;
                    EncodedImage enc;

                    enc = EncodedImage.createFromImage(res.getImage("profile-pic.jpg"), false);
                    imge = URLImage.createToStorage(enc, url, url);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(imge, "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        
        // modifier user tdkhl l page profile
      

         TextField Nom = new TextField("", "Nom", 20, TextField.ANY);
           Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);
        TextField Prenom = new TextField("", "Prenom", 20, TextField.ANY);
        Prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", Prenom);
         TextField Adresse = new TextField("", "Adresse", 20, TextField.ANY);
         Adresse.setUIID("TextFieldBlack");
        addStringValue("Prenom", Adresse);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
         email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("password", password);
     Button upload = new Button("Upload Image");
      Button submit = new Button("submit");
           addStringValue("upload", upload);
             addStringValue("submit", submit);
               submit.addActionListener(e ->{
                   // Validator control de saisi 
         Validator val_Nom = new Validator();
                            val_Nom.addConstraint(Nom, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_Nom.addConstraint(Nom, new RegexConstraint(text_saisir_des_caracteres, ""));
                              Validator val_Prenom = new Validator();
                            val_Prenom.addConstraint(Prenom, new LengthConstraint(8));
                           
                            val_Prenom.addConstraint(Prenom, new RegexConstraint(text_saisir_des_caracteres, ""));
                                      Validator val_Adresse = new Validator();
                            val_Adresse.addConstraint(Adresse, new LengthConstraint(8));
                           
                            val_Adresse.addConstraint(Adresse, new RegexConstraint(text_saisir_des_caracteres, ""));
                            
                                Validator val_email = new Validator();
                                   String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                         
                            val_email.addConstraint(Adresse, new LengthConstraint(8));
                           
                            val_email.addConstraint(Adresse, new RegexConstraint(text_mail, ""));
                   
                                if (Nom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Nom ", "OK", null);

                            } else if (val_Nom.isValid()) {
                                com.codename1.ui.Dialog.show("Erreur Nom !", "il faut saisir des caracteres  !", "OK", null);

                            } else if (Prenom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Prenom ", "OK", null);

                            }else if (val_Prenom.isValid()) {
                                com.codename1.ui.Dialog.show("Erreur Prenom !", "il faut saisir des caracteres  !", "OK", null);

                            } 
                                else if (Adresse.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Adresse ", "OK", null);

                            }else if (val_Adresse.isValid()) {
                                com.codename1.ui.Dialog.show("Erreur Adresse !", "il faut saisir des caracteres  !", "OK", null);

                            } 
                             else if (email.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                            }else if (val_email.isValid()) {
                                com.codename1.ui.Dialog.show("Erreur email !", "il faut saisir des caracteres  !", "OK", null);

                            } 
                                 else if (password.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de password ", "OK", null);

                            }
                                 else {
                                     
                                     user u = new user(Session.get().getId(), Nom.getText(), Prenom.getText(), Adresse.getText(), email.getText(), password.getText(), file, true);
                                     
                                     new user_service().edituser(u);
                                      com.codename1.ui.Dialog.show("Modifier", "Modifier", "OK", null);
                                    new NewsfeedForm(res).show();
                                 }
                            
                            
        
        
        });
      upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/mobile/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    com.codename1.ui.Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
