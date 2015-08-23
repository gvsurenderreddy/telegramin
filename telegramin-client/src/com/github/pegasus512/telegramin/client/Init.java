package com.github.pegasus512.telegramin.client;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

/**
 * @author MagnusBane413
 * JFrame inicial do programa, que define o ip e porta a serem conectados na classe Main.
 * Tamb�m define o nome do usuario.
 * */
public class Init extends JFrame {
	
	ImageIcon icone = new ImageIcon(getClass().getResource("files/icon.png"));
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPorta;
	private JButton btnEntrar;
	private JTextField txtSeuNickEx;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Init frame = new Init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unused")
	public Init() {
		setIconImage(icone.getImage());
		/** Seta o LookAndFeel para Nimbus. */
		try {
        	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
        	}
        } catch (Exception e) {
        	  try {
				UIManager.setLookAndFeel(
				  UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Vish, deu erro D:");
			}
        }
		/** Faz as opera��es padr�es de JFrame */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		/** Seta o Layout para  MigLayout */
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][]"));
		
		/** Inicia o campo de texto do ip do servidor */
		txtIp = new JTextField();
		txtIp.setText("IP do servidor. Ex: 192.168.25.213");
		contentPane.add(txtIp, "cell 0 0,growx");
		txtIp.setColumns(10);
		
		/** Inicia o campo de texto da porta do servidor */
		txtPorta = new JTextField();
		txtPorta.setText("Porta do servidor. Ex: 8467");
		contentPane.add(txtPorta, "cell 0 1,growx");
		txtPorta.setColumns(10);
		
		/** Inicia o campo de texto do nick do usuario */
		txtSeuNickEx = new JTextField();
		txtSeuNickEx.setText("Seu nick. Ex: Luigi6543");
		contentPane.add(txtSeuNickEx, "cell 0 2,growx");
		txtSeuNickEx.setColumns(10);
		
		/** Inicia o bot�o entrar */
		btnEntrar = new JButton("Entrar");
		contentPane.add(btnEntrar, "flowx,cell 0 3");
		/** Adiciona um ActionListener para o bot�o entrar */
		btnEntrar.addActionListener(e -> {
			/** Tenta abrir o programa */
			try {
				Main main = new Main(txtSeuNickEx.getText(), txtIp.getText(), Integer.parseInt(txtPorta.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Vish, deu erro D:");
			}
		});
	}

}
