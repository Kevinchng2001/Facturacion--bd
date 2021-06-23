package com.example.demo.controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controlador.Persona.PersonaUI;
import com.example.demo.controlador.Producto.ProductoUI;
import com.example.demo.controlador.factura.FacturaUI;
import com.example.demo.infraestructura.servicios.FacturaReporteService;
import com.example.demo.infraestructura.servicios.PersonaReporteService;
import com.example.demo.infraestructura.servicios.ProductoReporteService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class Index extends JFrame {


	JDesktopPane desktopPanel;
	
	@Autowired
	PersonaUI pUI;
	

	@Autowired
	FacturaUI facturaUI;
	@Autowired
	PersonaReporteService personaReporteService;
	@Autowired
	FacturaReporteService facturaReporteService;
	@Autowired
	ProductoReporteService productoReporteService;
	@Autowired
	ProductoUI ProductoUI;
	

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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("ReportePersona");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					personaReporteService.generarReporte();
					try

					{

					Process pro = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:\\Users\\ASUS\\OneDrive\\Reporte_Personas.pdf");

					}catch (Exception evvv)

	{

	JOptionPane.showMessageDialog(null, "No se puede abrir el archivo de ayuda, probablemente fue borrado","ERROR",JOptionPane.ERROR_MESSAGE);

	 

	}
					
				} catch (FileNotFoundException | JRException e) {
					
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		
		JMenu menuPersona = new JMenu("Factura");
		menuBar.add(menuPersona);
		
		JMenuItem nuevopersona = new JMenuItem("Nueva Factura");
		nuevopersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facturaUI.setVisible(true);
				desktopPanel.add(facturaUI);
			}
		});
		menuPersona.add(nuevopersona);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("ReporteFactura");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					facturaReporteService.generarReporte();
				} catch (FileNotFoundException | JRException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
		});
		menuPersona.add(mntmNewMenuItem_1);
		
		JMenu menuProducto = new JMenu("Producto");
		menuBar.add(menuProducto);
		
		JMenuItem nuevoproducto = new JMenuItem("Nuevo");
		
		nuevoproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ProductoUI.setVisible(true);
				desktopPanel.add(ProductoUI);
			}
		});
		menuProducto.add(nuevoproducto);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("ReporteProducto");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					productoReporteService.generarReporte();
					
				} catch (FileNotFoundException | JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			
		});
		menuProducto.add(mntmNewMenuItem_2);
		//contentPane = new JPanel();
		//setContentPane(contentPane);
		
		
		
	}
}
