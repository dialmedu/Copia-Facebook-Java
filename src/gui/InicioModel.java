/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.media.jfxmedia.logging.Logger;
import gui.componets.PanelBuscar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author SOPORTE
 */
public class InicioModel extends javax.swing.JFrame{
    
    Color active = new Color(24, 119, 242);
    Color disabled = new Color(100,103,107);
    Color hover = new Color(228,230,232);
    Color background = new Color(240,242,245);
    
    public void setToogleMenuOption(javax.swing.JButton button, javax.swing.JPanel panel){        
        if(button.getBackground().equals(active)){
           setDisabledMenuOption(button, panel);
        }else {
           setActiveMenuOption(button, panel);
        }
        setDisabledFriendsOptions(button , panel);
    }
    
    private void setDisabledMenuOption(JButton button, JPanel panel){
        if(button != null){
            button.setBackground(disabled);
        }
        if(panel != null){
            panel.setBackground(Color.white);
        }
    }
    
    private void setActiveMenuOption(JButton button, JPanel panel){
        button.setBackground(active);
        panel.setBackground(active);
    }
    
    private void setDisabledFriendsOptions(javax.swing.JButton button,  javax.swing.JPanel panel){
        JPanel padre =  (JPanel) button.getParent();
        int childCount =  padre.getComponentCount();
        for(int i =0; i < childCount; i++){
           Component hijo = padre.getComponent(i);
           String accesibleName = hijo.getAccessibleContext().getAccessibleName();
           System.out.println(accesibleName);
           if(hijo instanceof javax.swing.JButton){
               if (!accesibleName.equals(button.getAccessibleContext().getAccessibleName())){
                    setDisabledMenuOption((JButton) hijo, null);
               }
           }
           if(hijo instanceof javax.swing.JPanel){
               if (!accesibleName.equals(panel.getAccessibleContext().getAccessibleName())){
                   setDisabledMenuOption(null, (JPanel) hijo);
               }
           }
        }
    }
    
    
    protected void setEfectHoverItem(JPanel padre){
        String AccesibleDescriptionLauncher = "hover";
        int childCount =  padre.getComponentCount();
        for(int i =0; i < childCount; i++){
           Component hijo = padre.getComponent(i);
           String descripcion = hijo.getAccessibleContext().getAccessibleDescription();
            if(descripcion != null && descripcion.equalsIgnoreCase(AccesibleDescriptionLauncher)){
                hijo.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                     hijo.setBackground(hover);
                    }
                    
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                     hijo.setBackground(background);
                    }
                });
            }
        }
    }
   
    
    public void setShowBuscar(){
       PanelBuscar buscar = new PanelBuscar();
       buscar.setLocation(this.getLocation().x, this.getLocation().y);
       buscar.setVisible(true);
    }
    
    public void setVisibleScrollBar(javax.swing.JScrollPane panel,  Boolean visible){
        if(visible == false){
           new Thread(new HideScrollPane(panel)).start();
        } else {
            panel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
    }
    
}


class HideScrollPane implements Runnable {
    javax.swing.JScrollPane panel;

    HideScrollPane(JScrollPane panel) {
         this.panel = panel;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(InicioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        Point p =  panel.getMousePosition(true);
        if(p == null){
            panel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        }
    } 
}
