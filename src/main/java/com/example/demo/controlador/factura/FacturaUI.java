package com.example.demo.controlador.factura;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.infraestructura.repositorio.FacturaCabeceraRepository;
import com.example.demo.infraestructura.repositorio.PersonaRepositorio;
import com.example.demo.infraestructura.repositorio.ProductoRepositorio;
import com.example.demo.modelo.DetalleFactura;
import com.example.demo.modelo.FacturaCabecera;
import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Producto;
import java.awt.Color;

@Controller
public class FacturaUI extends JInternalFrame {

	private JTextField txtNumFactuta;
	private JTextField txtCedula;
	private JLabel lblFecha;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCedula ;
	private Persona p;


	@Autowired
	PersonaRepositorio personaRepositorio;
	
	@Autowired
	FacturaCabeceraRepository fCR;
	
	@Autowired
	ProductoRepositorio productoRepository;
	
	List<DetalleFactura> detallesFacturas; 

	FacturaCabecera fc;
	
	Producto producto;
	
	
	private JButton btnGuardar;
	private JTable tableDetalleFactura;
	DetalleFacturaItemModel modelo;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblNombreProducto;
	private JLabel lblNewLabel_4;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTable table;

	public FacturaUI() {
		getContentPane().setBackground(new Color(230, 230, 250));
		detallesFacturas=new ArrayList<>();
		producto=new Producto();
		
		modelo=new DetalleFacturaItemModel(detallesFacturas);
		fc=new FacturaCabecera();
		setBounds(100, 100, 624, 543);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Facturación Integral");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(56, 11, 353, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Número Factura");
		lblNewLabel_1.setBounds(494, 75, 85, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNumFactuta = new JTextField();
		txtNumFactuta.setBounds(493, 51, 86, 20);
		getContentPane().add(txtNumFactuta);
		txtNumFactuta.setColumns(10);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(421, 11, 181, 14);
		getContentPane().add(lblFecha);
		lblFecha.setText(new Date().toString());
		
		txtCedula = new JTextField();
		txtCedula.setBounds(145, 164, 86, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula=txtCedula.getText();
				p = personaRepositorio.findByCedula(cedula); 
				
				lblCedula.setText(p.getCedula());
				lblNombre.setText(p.getNombre());
				lblApellido.setText(p.getApellido());
				
				
			}
		});
		btnBuscar.setBounds(257, 163, 85, 20);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Cédula");
		lblNewLabel_2.setBounds(56, 166, 64, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(180, 265, 112, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("");
		lblApellido.setBounds(302, 265, 100, 14);
		getContentPane().add(lblApellido);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setPersona(p);
				fc.setNumeroFactura(Integer.parseInt(txtNumFactuta.getText()));
				fc.setFechaEmision(new Date());
				fc.setDetallesFacturas(detallesFacturas);
				fc.setDetallesFacturas(detallesFacturas);
				
				fCR.save(fc);
			}
		});
		btnGuardar.setBounds(257, 481, 89, 23);
		getContentPane().add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 300, 495, 171);
		getContentPane().add(scrollPane);
		
		tableDetalleFactura = new JTable();
		scrollPane.setViewportView(tableDetalleFactura);
		tableDetalleFactura.setModel(modelo);
		
		JLabel lblNewLabel_3 = new JLabel("Producto");
		lblNewLabel_3.setBounds(56, 66, 79, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(145, 64, 86, 20);
		getContentPane().add(txtProducto);
		txtProducto.setColumns(10);
		
		btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				producto=productoRepository.findById(Integer.parseInt(txtProducto.getText())).get();
				lblNombreProducto.setText(producto.getNombre());
			}
		});
		btnBuscarProducto.setBounds(257, 66, 89, 23);
		getContentPane().add(btnBuscarProducto);
		
		lblNombreProducto = new JLabel("");
		lblNombreProducto.setBounds(305, 132, 178, 14);
		getContentPane().add(lblNombreProducto);
		
		lblNewLabel_4 = new JLabel("Cantidad");
		lblNewLabel_4.setBounds(56, 108, 79, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(145, 106, 86, 20);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cantidad= Integer.parseInt(txtCantidad.getText());
				DetalleFactura dF = new DetalleFactura(cantidad, producto, fc);
				detallesFacturas.add(dF);
				generarTabla();
			}
		});
		btnAgregar.setBounds(257, 99, 89, 23);
		getContentPane().add(btnAgregar);
		
		lblCedula = new JLabel("");
		lblCedula.setBounds(76, 265, 119, 14);
		getContentPane().add(lblCedula);
		
		lblNewLabel_5 = new JLabel("ID");
		lblNewLabel_5.setBounds(84, 209, 45, 13);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("NOMBRE");
		lblNewLabel_6.setBounds(179, 209, 79, 13);
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("APELLIDO");
		lblNewLabel_7.setBounds(302, 209, 60, 13);
		getContentPane().add(lblNewLabel_7);
		
		table = new JTable();
		table.setBounds(56, 208, 353, 82);
		getContentPane().add(table);

		
		
	}
	
	public void generarTabla() {
		
		modelo = new DetalleFacturaItemModel(detallesFacturas);
		
		tableDetalleFactura.setModel(modelo);

	}
}
