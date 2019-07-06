/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.complexapplication;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author biar
 */
public class AzioniFrame extends JFrame implements Observer {
    
    private QuotazioniTableModel quotazioniTabModel = new QuotazioniTableModel();
    private JTable table = new JTable(quotazioniTabModel);
    
    // Buttons
    private JButton compraBtn = new JButton("Compra");
    private JButton authBtn = new JButton("Autenticati");
    
    // Panels
    private JScrollPane jSPane = new JScrollPane(table);
    private JPanel centralPnl = new JPanel();
    private JPanel southPnl = new JPanel();
    
    private AzioniListener listener = new AzioniListener(this);

    public AzioniFrame() {
        super("Sistema Azionario");
        
        setBuyBtn(false);
        
        this.compraBtn.setActionCommand(listener.COMPRA_CMD);
        this.compraBtn.addActionListener(listener);
        this.authBtn.setActionCommand(listener.ACCEDI_CMD);
        this.authBtn.addActionListener(listener);
        
        this.centralPnl.add(jSPane);
        this.southPnl.add(compraBtn);
        this.southPnl.add(authBtn);
        
        this.getContentPane().add(centralPnl, BorderLayout.CENTER);
        this.getContentPane().add(southPnl, BorderLayout.SOUTH);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    public void setBuyBtn(boolean flag) {
        this.compraBtn.setEnabled(flag);
    }
    
    public String getNomeAzioneSelezionata() {
        int row = this.table.getSelectedRow();
        String nomeAzioneSelezionata = null;
        if(row >= 0) {
            nomeAzioneSelezionata = (String) this.table.getModel().getValueAt(row, 0);
        }
        return nomeAzioneSelezionata;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.table.updateUI();
    }
    
    public QuotazioniTableModel getQuotazioniTableModel() {
        return this.quotazioniTabModel;
    }
    
}   
