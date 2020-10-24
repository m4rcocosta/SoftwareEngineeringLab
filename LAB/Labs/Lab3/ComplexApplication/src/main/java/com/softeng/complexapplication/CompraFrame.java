/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.complexapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author biar
 */
public class CompraFrame extends JFrame implements Observer {
    
    private static final int LABEL_WIDTH = 120;
    
    // Labels
    private JLabel userLbl = new JLabel("Utente:");
    private JLabel nameLbl = new JLabel("Nome:");
    private JLabel priceLbl = new JLabel("Prezzo:");
    private JLabel quantityLbl = new JLabel("Quantit\u00e0:");
    private JLabel totalAmountLbl = new JLabel("Importo:");
    private JLabel euroPriceLbl = new JLabel("\u20ac");
    private JLabel euroAmountLbl = new JLabel("\u20ac");
    
    private JLabel userTxtLbl = new JLabel();
    private JLabel totalAmountValueLbl = new JLabel("0.00");
    
    // Panels
    private JPanel userPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pricePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel quantityPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel totalAmountPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    private JPanel centerPnl = new JPanel();
    private JPanel southPnl = new JPanel();
    
    // Input widgets
    private JTextField nameTxt = new JTextField(20);
    private JTextField priceTxt = new JTextField(10);
    private JTextField quantityTxt = new JTextField(5);
    
    // Buttons
    private JButton ordinaBtn = new JButton("Ordina");
    
    // Listener
    private CompraListener listener = new CompraListener(this);

    public CompraFrame() {
        super("Effettua Acquisti");
        
        userTxtLbl.setText(Utente.getInstance().getUtente());
        
        impostaCaratteristicheGraficheEtichette();
        
        userPnl.add(userLbl);
        userPnl.add(userTxtLbl);
        namePnl.add(nameLbl);
        namePnl.add(nameTxt);
        pricePnl.add(priceLbl);
        pricePnl.add(priceTxt);
        pricePnl.add(euroPriceLbl);
        quantityPnl.add(quantityLbl);
        quantityPnl.add(quantityTxt);
        
        priceTxt.addFocusListener(listener);
        quantityTxt.addFocusListener(listener);
        ordinaBtn.addActionListener(listener);
        
        totalAmountPnl.add(totalAmountLbl);
        totalAmountPnl.add(totalAmountValueLbl);
        totalAmountPnl.add(euroAmountLbl);
        
        centerPnl.setLayout(new BoxLayout(centerPnl, BoxLayout.Y_AXIS));
        
        centerPnl.add(userPnl);
        centerPnl.add(namePnl);
        centerPnl.add(pricePnl);
        centerPnl.add(quantityPnl);
        centerPnl.add(totalAmountPnl);
        
        southPnl.add(ordinaBtn);
        
        this.getContentPane().add(centerPnl, BorderLayout.CENTER);
        this.getContentPane().add(southPnl, BorderLayout.SOUTH);
        
        this.pack();
        
    }
    
    public String getQuantity() {
        return quantityTxt.getText();
    }
    
    public String getPrice() {
        return priceTxt.getText();
    }
    
    public String getName() {
        return nameTxt.getText();
    }
    
    public void setName(String name) {
        nameTxt.setText(name);
    }
    
    public void setTotalAmount(String totalAmount) {
        totalAmountValueLbl.setText(totalAmount);
    }
    
    public void notifyError(String error) {
		JOptionPane.showMessageDialog(
				this,
				error,
				this.getTitle(),
				JOptionPane.ERROR_MESSAGE);
	}

    @Override
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(
                this,
                arg.toString(),
                this.getTitle(),
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void impostaCaratteristicheGraficheEtichette() {
		// usato solo in quanto la dimensione di default per le etichette prevede
		// l'inserimento sia della larghezza, che ci interessa, sia dell'altezza
		int labelHeight = this.userLbl.getPreferredSize().height;

		this.userLbl.setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
		this.userLbl.setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
		this.priceLbl.setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
		this.quantityLbl.setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
		this.totalAmountLbl.setPreferredSize(new Dimension(LABEL_WIDTH, labelHeight));
		
		this.userLbl.setHorizontalAlignment(JLabel.RIGHT);
		this.nameLbl.setHorizontalAlignment(JLabel.RIGHT);
		this.priceLbl.setHorizontalAlignment(JLabel.RIGHT);
		this.quantityLbl.setHorizontalAlignment(JLabel.RIGHT);
		this.totalAmountLbl.setHorizontalAlignment(JLabel.RIGHT);
	}
    
}
