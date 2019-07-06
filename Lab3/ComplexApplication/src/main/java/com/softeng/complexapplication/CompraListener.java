/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.complexapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author biar
 */
public class CompraListener implements FocusListener, ActionListener {
    
    private CompraFrame frame;
    private CompraJMSManager compraMan;

    public CompraListener(CompraFrame compraFrame) {
        this.frame = compraFrame;
        this.compraMan = new CompraJMSManager();
    }

    @Override
    public void focusGained(FocusEvent e) {
    
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            
            String quantityTxt = frame.getQuantity();
            String priceTxt = frame.getPrice();
            
            int quantity = Integer.parseInt(quantityTxt);
            float price = Float.parseFloat(priceTxt);
            
            String finale = String.format("%1$.2f", quantity * price);
            
            frame.setTotalAmount(finale);
            
        } catch(NumberFormatException ex) {
            frame.setTotalAmount("0.00");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean buonEsito = false;
        try {
            System.out.println("In actionPerf compraList");
            buonEsito = compraMan.compra(
                    frame.getName(),
                    Float.parseFloat(frame.getPrice()),
                    Integer.parseInt(frame.getQuantity())
            );
            if(!buonEsito) {
                frame.notifyError(
                    "La transazione non \u00e8 andata a buon fine."  
                );
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            else {
                JOptionPane.showMessageDialog(frame, "La transazione \u00e8 andata a buon fine.", "Effettua Acquisti", 0);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        } catch (NumberFormatException ex) {
            frame.notifyError(
                "Quantit\u00e0 e prezzo devono essere valori numerici, " +
                    "risp. intero e decimale"
            );
        }
    }
    
}
