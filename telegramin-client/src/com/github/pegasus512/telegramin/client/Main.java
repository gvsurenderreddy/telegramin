/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.pegasus512.telegramin.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author MagnusBane413
 * 
 * 
 */


@SuppressWarnings("serial")
public class Main extends JFrame {
	
	ImageIcon icone = new ImageIcon(getClass().getResource("files/icon.png"));
	String nome;
    PrintWriter escritor;
    Socket socket;
    JTextField textoParaEnviar;
    JButton botao;
    JTextArea textoRecebido;
    Scanner leitor;
    
    
    public Main(String nome, String ip, int port) throws Exception {
    	super("Chat : " + nome);
    	setIconImage(icone.getImage());
    	setVisible(true);

        try {
        	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
        	}
        } catch (Exception e) {
        	  UIManager.setLookAndFeel(
        	  UIManager.getSystemLookAndFeelClassName());
        }
        this.nome = nome;        
        textoRecebido = new JTextArea();
        textoParaEnviar = new JTextField();
        botao = new JButton("Enviar");
        botao.addActionListener(new EnviarListener());
        
        configurarRede(ip, port);
        
        JScrollPane scroll = new JScrollPane(textoRecebido);
        
        getContentPane().add(BorderLayout.CENTER, scroll);
        Container envio = new JPanel();
        envio.setLayout(new BorderLayout());
        envio.add(BorderLayout.CENTER, textoParaEnviar);
        envio.add(BorderLayout.EAST, botao);
        getContentPane().add(BorderLayout.SOUTH, envio);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(518, 331);
        setVisible(true);
    }
    private void configurarRede(String ip, int port) throws Exception {
            try {
            socket = new Socket(ip, port);
            escritor =  new PrintWriter(socket.getOutputStream());
            leitor = new Scanner(socket.getInputStream());
            new Thread(new EscutaServidor()).start();
            } catch (Exception e){JOptionPane.showMessageDialog(null, "Vish, deu erro D:");}
                
            
            
    }
    private class EscutaServidor implements Runnable {
        
        @Override
        public void run() {
      try {
          String texto;
            while((texto = leitor.nextLine())  != null ) {
                textoRecebido.append(texto + "\n");
            } 
                    
                    } catch (Exception et) {
                        et.printStackTrace();
                        
        }
        }
        
    }
   private class EnviarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            escritor.println(nome + " : " + textoParaEnviar.getText());
            escritor.flush();
            textoParaEnviar.setText("");
            textoParaEnviar.requestFocus();
            
        }
   }
}