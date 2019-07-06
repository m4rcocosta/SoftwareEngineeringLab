/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.complexapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author biar
 */
public class AzioniListener implements ActionListener {
    
    private AzioniFrame frameAzioni;
    public static final String COMPRA_CMD = "A";
    public static final String ACCEDI_CMD = "B";
    
    private AzioniJMSListener jsmListener;

    public AzioniListener(AzioniFrame frameAzioni) {
        this.frameAzioni = frameAzioni;
        this.jsmListener = new AzioniJMSListener(
                new Observer[] {
                    frameAzioni,
                    frameAzioni.getQuotazioniTableModel()
                }
        );
        jsmListener.addObserver(frameAzioni);
        jsmListener.addObserver(frameAzioni.getQuotazioniTableModel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd == ACCEDI_CMD)
            autentica();
        else if(cmd == COMPRA_CMD)
            compra();
    }
    
    public void autentica(){
        String utente = Utente.getInstance().getUtente();
        
        do {
            utente = (String) JOptionPane
                .showInputDialog(
                    this.frameAzioni,
                    "Specificare il nome del mittente",
                    this.frameAzioni.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    utente
            );
            utente.trim();
        } while (utente != null && utente.equals(""));
        if(utente != null) {
            System.out.println("utente not null");
            this.frameAzioni.setBuyBtn(true);
            //this.jsmListener.start();
        }
        else {
            this.jsmListener.stop();
        }
        Utente.getInstance().setUtente(utente);
    }
    
    public void compra() {
        CompraFrame frameBuy = new CompraFrame();
        String nomeAzione = frameAzioni.getNomeAzioneSelezionata();
        if(nomeAzione != null) {
            frameBuy.setName(nomeAzione);
        }
        frameBuy.setVisible(true);
    }
    
}
