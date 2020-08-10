/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author SOPORTE
 */
public class InicioFacade  extends Inicio {

    private static HashMap textos;
    
    public InicioFacade() {
        super();
    }
    
    private static void setTextos(){
       InicioFacade.textos = new HashMap();
       InicioFacade.textos.put("buscar_en_facebook", "Buscar en Facebook ");
    }
    
    private static void setCustomTheme(){
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception ex) {
           Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        InicioFacade.setCustomTheme();
        InicioFacade.setTextos();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
}
