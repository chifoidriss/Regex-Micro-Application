
package com.view;

import com.automate.Afn_En_Afd;
import com.automate.AutomateD;
import com.automate.Reconnaissance;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HomePane extends javax.swing.JPanel {
    
    private String alphabet="";
    private String expr="";

    private ArrayList<Character> symbols; //symbols table

    private ArrayList<Integer> Netats_final;
    private BufferedImage image;
    private BufferedImage image2;

    JLabel afn = new JLabel("");
    JLabel afd = new JLabel("");

    int tab[][];
    Afn_En_Afd deter;
    ArrayList<Integer>[][] tran;

    public HomePane() {
      initComponents();
      automate_container.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        verifierInput = new javax.swing.JTextField();
        header = new java.awt.Label();
        validerBtn = new javax.swing.JButton();
        exprInput = new javax.swing.JTextField();
        verifierBtn = new javax.swing.JButton();
        wordCheckInput = new javax.swing.JTextField();
        wordCheck = new javax.swing.JButton();
        result = new javax.swing.JLabel();
        automate_container = new javax.swing.JTabbedPane();
        afn_Container = new javax.swing.JPanel();
        afd_Container = new javax.swing.JPanel();
        resultat_container = new javax.swing.JPanel();
        response_title = new javax.swing.JLabel();
        response = new javax.swing.JLabel();
        responseLabel = new javax.swing.JLabel();

        verifierInput.setFont(new java.awt.Font("Lucida Sans", 3, 14)); // NOI18N
        verifierInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.lightGray), "Entrer votre alphabet: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 2, 14))); // NOI18N

        header.setAlignment(java.awt.Label.CENTER);
        header.setBackground(new java.awt.Color(0, 0, 204));
        header.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        header.setFont(new java.awt.Font("Lucida Sans", 1, 25)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setText("Annalyse et Vérification des Expréssions Régulières");

        validerBtn.setBackground(new java.awt.Color(0, 0, 204));
        validerBtn.setFont(new java.awt.Font("Lucida Sans", 3, 14)); // NOI18N
        validerBtn.setForeground(new java.awt.Color(255, 255, 255));
        validerBtn.setText("COMPILER");
        validerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        validerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerBtnActionPerformed(evt);
            }
        });

        exprInput.setFont(new java.awt.Font("Lucida Sans Unicode", 3, 14)); // NOI18N
        exprInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.lightGray), "Entrer une expression", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 2, 14))); // NOI18N
        exprInput.setEnabled(false);

        verifierBtn.setBackground(new java.awt.Color(0, 0, 204));
        verifierBtn.setFont(new java.awt.Font("Lucida Sans", 3, 14)); // NOI18N
        verifierBtn.setForeground(new java.awt.Color(255, 255, 255));
        verifierBtn.setText("VALIDER");
        verifierBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verifierBtn.setEnabled(false);
        verifierBtn.setMaximumSize(new java.awt.Dimension(78, 23));
        verifierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifierBtnActionPerformed(evt);
            }
        });

        wordCheckInput.setFont(new java.awt.Font("Lucida Sans", 3, 14)); // NOI18N
        wordCheckInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.lightGray), "Entrer un mot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Batang", 3, 14))); // NOI18N
        wordCheckInput.setEnabled(false);

        wordCheck.setBackground(new java.awt.Color(0, 0, 204));
        wordCheck.setFont(new java.awt.Font("Lucida Sans", 3, 14)); // NOI18N
        wordCheck.setForeground(new java.awt.Color(255, 255, 255));
        wordCheck.setText("VERIFIER MOT");
        wordCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        wordCheck.setEnabled(false);
        wordCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordCheckActionPerformed(evt);
            }
        });

        result.setBackground(new java.awt.Color(204, 0, 204));
        result.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        result.setText(" Informations");
        result.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.blue));

        automate_container.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, java.awt.Color.white));
        automate_container.setAutoscrolls(true);

        afn_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, java.awt.Color.white));
        afn_Container.setAutoscrolls(true);

        javax.swing.GroupLayout afn_ContainerLayout = new javax.swing.GroupLayout(afn_Container);
        afn_Container.setLayout(afn_ContainerLayout);
        afn_ContainerLayout.setHorizontalGroup(
            afn_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        afn_ContainerLayout.setVerticalGroup(
            afn_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        automate_container.addTab("A.F.N", afn_Container);

        afd_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, java.awt.Color.white));
        afd_Container.setAutoscrolls(true);

        javax.swing.GroupLayout afd_ContainerLayout = new javax.swing.GroupLayout(afd_Container);
        afd_Container.setLayout(afd_ContainerLayout);
        afd_ContainerLayout.setHorizontalGroup(
            afd_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        afd_ContainerLayout.setVerticalGroup(
            afd_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        automate_container.addTab("A.F.D", afd_Container);

        response_title.setText(" STATISTIQUES");

        javax.swing.GroupLayout resultat_containerLayout = new javax.swing.GroupLayout(resultat_container);
        resultat_container.setLayout(resultat_containerLayout);
        resultat_containerLayout.setHorizontalGroup(
            resultat_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(response_title, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addComponent(response, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(responseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        resultat_containerLayout.setVerticalGroup(
            resultat_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultat_containerLayout.createSequentialGroup()
                .addComponent(response_title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(response, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(verifierInput)
                                    .addComponent(result, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(exprInput)
                                    .addComponent(validerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(verifierBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(wordCheckInput)
                                    .addComponent(wordCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(resultat_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(automate_container)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verifierInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(validerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(exprInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verifierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(wordCheckInput, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wordCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultat_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(automate_container))
                .addContainerGap())
        );

        automate_container.getAccessibleContext().setAccessibleName(" A.F.N ");
    }// </editor-fold>//GEN-END:initComponents

    private void validerBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
      alphabet = verifierInput.getText();
      if(isCorrect_alphabet(alphabet)){
        isWordofLanguage();
        result.setText(" Entrer votre expression!!!");
        validerBtn.setBackground(Color.GREEN);
        exprInput.setEnabled(true);
        verifierBtn.setEnabled(true);
      }else{
          
          JOptionPane.showMessageDialog(null,"Erreur: veuillez verifier l'alphabet");
        //verif(" Erreur: veuillez verifier l'alphabet!!!");
        validerBtn.setBackground(Color.RED);
      }
    }

    private void verifierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifierBtnActionPerformed
      if (exprInput.getText().trim().length()>0) {

        expr = exprInput.getText();

        if(isCorrect_expression(alphabet, expr)) {

          isWordofLanguage();
          result.setText(" Bon!!!");
          AutomateD auto = new AutomateD(expr);

          symbols = auto.getAlphabet();
          Netats_final = auto.getF_etat();

          tran = new ArrayList[auto.getEtats().size()][auto.getAlphabet().size()];
          tran = auto.copy();

          deter = new Afn_En_Afd(tran, Netats_final, symbols);
          deter.afn_En_Afd();

          tab = deter.getDtran_simplifier();

          DrawAutomate da = new DrawAutomate(tran, symbols, Netats_final);
          image = da.painAFn();

            afn_Container.removeAll();
            afn_Container.validate();
            afn_Container.revalidate();
            afn = new JLabel(new ImageIcon(image));
          
            drawAutomate(afn_Container,afn);

            DrawAutomate da2 = new DrawAutomate(deter.getDtran_simplifier(), symbols, deter.getEtats_final_mini());
            image2 = da2.paintAFd();

            afd_Container.removeAll();
            afd_Container.validate();
            afd_Container.revalidate();
            afd = new JLabel(new ImageIcon(image2));
          
            drawAutomate(afd_Container,afd);
          
            isWordofLanguage();

            result.setText(" Analyse terminée faites vos vérifications.");
            
            automate_container.setVisible(true);
            
            verifierBtn.setBackground(Color.GREEN);
            wordCheckInput.setEnabled(true);
            wordCheck.setEnabled(true);
        }
      } else {
        verif();
        verifierBtn.setBackground(Color.RED);
        result.setText(" Vérifiez votre expression régulière!!!");
     }
    }//GEN-LAST:event_verifierBtnActionPerformed

    private void wordCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordCheckActionPerformed
        if(wordCheckInput.getText().trim().length()>1) {
          wordCheck.setBackground(Color.GREEN);

          result.setText(" Bon!!!");

          Reconnaissance reco = new Reconnaissance(tab, symbols, deter.getEtats_final_mini());
          String mot = wordCheckInput.getText();
          boolean res = reco.reconnaissance_Avc_execution(mot);

          resultat_container.setVisible(true);
          if (res == true) {
            result.setText(" Le mot entré appartient au langage définit !!");
            isWordofLanguage();

            resultat_container.setLayout(new FlowLayout());

            resultat_container.removeAll();
            resultat_container.validate();

            responseLabel.setText(" Taille du mot: "+mot.length());
            JLabel esP = new JLabel("");

            int table_of_symbols_occ[] = reco.table_of_symbols_occ;

            for (int x=0;x<table_of_symbols_occ.length;x++){
              JLabel nwl  = new JLabel(" *[ "+symbols.get(x)+" ]=[ "+table_of_symbols_occ[x]+" ]");
              nwl.setForeground(new Color(20, 143, 30));
              resultat_container.add(esP);
              resultat_container.add(nwl);
            }
          } else if(res == false) {
          verif();
          response.setText(" Le mot entré n'appartient pas au langage définit !!");
          isnotWordofLanguage();

          resultat_container.setLayout(new FlowLayout());
          resultat_container.removeAll();
          resultat_container.validate();
        }
      }else{
         verif();
         response.setText(" Vérifiez votre mot!!!");
         wordCheck.setBackground(Color.RED);
      }
    }//GEN-LAST:event_wordCheckActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel afd_Container;
    private javax.swing.JPanel afn_Container;
    private javax.swing.JTabbedPane automate_container;
    private javax.swing.JTextField exprInput;
    private java.awt.Label header;
    private javax.swing.JLabel response;
    private javax.swing.JLabel responseLabel;
    private javax.swing.JLabel response_title;
    private javax.swing.JLabel result;
    private javax.swing.JPanel resultat_container;
    private javax.swing.JButton validerBtn;
    private javax.swing.JButton verifierBtn;
    private javax.swing.JTextField verifierInput;
    private javax.swing.JButton wordCheck;
    private javax.swing.JTextField wordCheckInput;
    // End of variables declaration//GEN-END:variables


    
    private void drawAutomate(JPanel pa, JLabel lab){
        javax.swing.GroupLayout afn_ContainerLayout = new javax.swing.GroupLayout(pa);
            pa.setLayout(afn_ContainerLayout);
            afn_ContainerLayout.setHorizontalGroup(
                afn_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(afn_ContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lab, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .addContainerGap())
            );
            afn_ContainerLayout.setVerticalGroup(
                afn_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(afn_ContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lab, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addContainerGap())
            );
    }
    
    private void verif(){
      this.result.setText("Vérifier vos champs !!");
      this.result.setForeground(new Color(186,0,0));
    }

    private void isnotWordofLanguage(){
        
    }

    private void isWordofLanguage(){
      result.setForeground(new Color(20, 143, 30));        
    }

    private boolean isCorrect_alphabet(String st) {
        int i=0;
        
        if(st.trim().length()<1){
            return false;
        }
        
        for(i=0;i<st.length();i++){
            if(i<st.length()-1){
              if(st.charAt(i)==',' && st.charAt(i+1)==',')
                  return false;
              else if((st.charAt(i)==',' || (".*+|()").indexOf(st.charAt(i))!=-1) && i==0)
                  return false;
              else if(st.charAt(i)!=',' && st.charAt(i+1)!=',' && st.charAt(i)!='{')
                  return false;
              else if(st.charAt(i)!=',' && st.charAt(i+1)!=',' && st.charAt(i+1)!='}')
                  return false;
              else if((".+*|)(").indexOf(st.charAt(i))!=-1)
                  return false;
              else if(st.charAt(i)=='{' && st.charAt(i+1)==',' ) {
                  return false;
              }
            } else {
                if( st.charAt(i)==',' || (".*+|()").indexOf(st.charAt(i))!=-1)
                    return false;
            }
        }
        return true;
    }

    private boolean isCorrect_syntax(String s) {
        int parent = 0;
        int o=0,f=0;
        String op="+|.";

        for(int i=0;i<s.length();i++) {
            if(i<s.length()-1) {
                if(op.indexOf(s.charAt(i))!=-1 &&op.indexOf(s.charAt(i+1))!=-1)
                    return false;
                else if(op.indexOf(s.charAt(i))!=-1 && i==0 )
                    return false;
                else if(s.charAt(i)=='(' && s.charAt(i+1)==')')
                    return false;
                else if(s.charAt(i)=='(')
                    o=o+1;
                else if(s.charAt(i)==')')
                    f=f+1;
            } else {
                    if(s.charAt(i)=='(')
                        o=o+1;
                    else if(s.charAt(i)==')')
                        f=f+1;
                    else if((".|+").indexOf(s.charAt(i))!=-1)
                        return  false;
                }
        }
        parent=o-f;
        if(parent==0)
            return true;
        return false;
    }

    private boolean verify(String s, String alphab) {

      for(int i=0; i < s.length() ; i++) {
        if(alphab.indexOf(s.charAt(i))==-1 && (("*.+|()").indexOf(s.charAt(i))==-1))
          return false;
        else if((".+|").indexOf(s.charAt(i))!=-1 && i== s.length()-1)
          return false;
      }
      return true;
    }

    private boolean isCorrect_expression(String alphabet,String regex) {
        if(isCorrect_syntax(regex)) {
            if(verify(regex,alphabet)) {
              return true;
            } else {
              verif(" Votre expression ne cottespond pas a l'alphabet entré :\""+alphabet+"\"");
              return false;
            }
        } else {
          verif(" Erreur de syntaxe vuillez verifier votre expression ");
          return false;
        }
    }

    private void verif(String s){
      this.result.setText(s+" !!");
      this.result.setForeground(new Color(186,0,0));
    }

    public  void save(){

        Path path = Paths.get("files");
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        path = Paths.get("files/save");

        if(!Files.exists(path)) {

            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        path = Paths.get("files/save/data.txt");

        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        String rex_file_title = "############################  Sauvegarde de l'automate de l'application Rex  #############################\n";
        String vide = "\n\n\n";
        String ligne = " ";
        String head_table = " |\t\tEtats\t|";
        String afn_title = "############################# TABLEAU DE TRANSISTION DE L'AFN ###################################\n\n";
        String souligner="_______________________________________________________________________________________________________________\n";
        String exp = "##Expression réguliere ::  [ "+expr+" ]\n\n";

        try {
            Files.write(path,rex_file_title.getBytes(Charset.defaultCharset()), StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,("\n\n\n \t\t#INFO::  les crochets vides et -1 ( [] , -1) représentent des états null\n\n\n").getBytes(Charset.defaultCharset()), StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,vide.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,exp.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,afn_title.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);

            for(int i=0;i<symbols.size();i++){
                if(i==0)
                    head_table+="\t\t"+symbols.get(i)+"\t\t|\t\t";
                else
                    head_table+=symbols.get(i)+"\t\t|\t\t";
            }

            head_table+="\n";

            Files.write(path,head_table.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,souligner.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);


            for (int i=0;i<tran.length;i++){
                    ligne+="|\t\t"+i+"\t\t|\t\t";
                for (int j=0;j<symbols.size();j++){
                    ligne+=tran[i][j]+"\t\t|\t\t";
                }
                ligne+="\n";
                Files.write(path,ligne.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
                ligne=" ";
            }

            Files.write(path,("\n\n##Etats Initial ::  [ 0 ]\n\n").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,("\n##Etats Finaux   ::  [ "+Netats_final+" ]").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,("\n\n\n################################## TABlEAU DE TRANSITION AFD ####################################\n\n").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);


            head_table = " |\t\tEtats\t|";

            for(int i=0;i<symbols.size()-1;i++){
                if(i==0)
                    head_table+="\t\t"+symbols.get(i)+"\t\t|\t\t";
                else
                    head_table+=symbols.get(i)+"\t\t|\t\t";
            }

            head_table+="\n";

            Files.write(path,head_table.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,souligner.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);

            for (int i = 0; i < tab.length; i++) {
                ligne+="|\t\t"+i+"\t\t|\t\t";
                for (int j = 0; j < symbols.size()-1 ; j++) {
                    ligne+=tab[i][j]+"\t\t|\t\t";
                }
                ligne+="\n";
                Files.write(path,ligne.getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
                ligne=" ";

            }

            Files.write(path,("\n\n##Etats Initial ::  [ 0 ]\n\n").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,("\n##Etats Finaux   ::  [ "+deter.getEtats_final_mini()+" ]").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);
            Files.write(path,("\n\n\n################################## FIN MERCI POUR VOTRE PATIENCE , A PLUS  ;) ####################################\n\n").getBytes(Charset.defaultCharset()),StandardOpenOption.WRITE,StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init(){
        result.setText(" Informations");
        
        automate_container.setVisible(false);
        
        afn_Container.removeAll();
        afn_Container.validate();
        afn_Container.revalidate();
        
        afd_Container.removeAll();
        afd_Container.validate();
        afd_Container.revalidate();
        
        validerBtn.setBackground(new Color(0,0,204));
        verifierBtn.setBackground(new Color(0,0,204));
        wordCheck.setBackground(new Color(0,0,204));
        
        exprInput.setEnabled(false);
        verifierBtn.setEnabled(false);
        wordCheckInput.setEnabled(false);
        wordCheck.setEnabled(false);
        
        this.validate();
        this.revalidate();
    }
}
