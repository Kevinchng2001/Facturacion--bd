package com.example.demo.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controlador.Persona.PersonaUI;

@SuppressWarnings("serial")
@Controller
public class Index extends JFrame {

	//private JPanel contentPane;
	
	JDesktopPane desktopPanel;
	
	@Autowired
	PersonaUI pUI;
	
	@Autowired
	PersonaModificarUI pModificarUI;

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		desktopPanel= new JDesktopPane();
		getContentPane().add(desktopPanel);
		JMenu mnNewMenu = new JMenu("Persona");
		menuBar.add(mnNewMenu);
		
		JMenuItem miPNuevo = new JMenuItem("Nueva");
		miPNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pUI.setVisible(true);
				desktopPanel.add(pUI);

			}
		});
		mnNewMenu.add(miPNuevo);
		
		JMenuItem miPModificar = new JMenuItem("Modificar");
		miPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pModificarUI.setVisible(true);
				desktopPanel.add(pModificarUI);
			}
		});
		mnNewMenu.add(miPModificar);
		
		JMenuItem miPEliminar = new JMenuItem("Eliminar");
		mnNewMenu.add(miPEliminar);
		
		JMenuItem miPListar = new JMenuItem("Listar");
		mnNewMenu.add(miPListar);
		
		
		
	}

}