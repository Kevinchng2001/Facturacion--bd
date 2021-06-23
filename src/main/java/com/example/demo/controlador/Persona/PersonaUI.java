package com.example.demo.controlador.Persona;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.infraestructura.repositorio.PersonaRepositorio;
import com.example.demo.modelo.Persona;
import java.awt.Color;
import javax.swing.JTextArea;

@Controller
public class PersonaUI extends JInternalFrame {
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextField txtTelefono;

	JButton btnEliminar;
	JButton btnActualizar;

	Persona personaActualizar;

	boolean banderaActualizar = false;

	PersonaItemModel personaModel;

	@Autowired
	PersonaRepositorio personaRepositorio;
	
	private JTable tablePersona;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscarApellido;
	private JLabel lblNewLabel_4;
	private JTextField txtApellido_1;
	private JTextField txtId;
	private JTextField textField;
	private JTextField txtId_1;

	public PersonaUI() {

		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 517);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setBounds(130, 11, 74, 24);
		contentPane.add(lblNewLabel_3);

		txtNombre = new JTextField();
		txtNombre.setBounds(234, 11, 122, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(130, 46, 81, 24);
		contentPane.add(lblNewLabel_1);

		txtApellido = new JTextField();
		txtApellido.setBounds(234, 46, 122, 24);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel = new JLabel("Cédula");
		lblNewLabel.setBounds(130, 86, 96, 14);
		contentPane.add(lblNewLabel);

		txtCedula = new JTextField();
		txtCedula.setBounds(234, 81, 122, 24);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(0, 0, 0));
		btnGuardar.setBackground(new Color(192, 192, 192));
		btnGuardar.setBounds(407, 65, 121, 35);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona p;
				if (banderaActualizar == true) {
					p = personaActualizar;
					p.setApellido(txtApellido.getText());
					p.setNombre(txtNombre.getText());
					p.setTelefono(txtTelefono.getText());
					p.setCedula(txtCedula.getText());
					banderaActualizar = false;

				} else {
					p = new Persona(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(),txtCedula.getText());
				}
				personaRepositorio.save(p);
				limpiarInterfaz();
				generarTabla(personaRepositorio.findAll());
			}
		});
		contentPane.add(btnGuardar);

		tablePersona = new JTable();
		tablePersona.setBackground(new Color(173, 216, 230));
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				//System.out.println(tablePersona.getSelectedRow());
				//System.out.println(personaModel.getPersonaAt(tablePersona.getSelectedRow()));
			}
		});

		tablePersona.setBounds(36, 238, 546, 163);
		contentPane.add(tablePersona);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona pEliminar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				personaRepositorio.delete(pEliminar);
				generarTabla(personaRepositorio.findAll());

			}
		});
		btnEliminar.setBounds(185, 424, 89, 23);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personaActualizar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				txtCedula.setText(personaActualizar.getCedula());
				txtNombre.setText(personaActualizar.getNombre());
				txtApellido.setText(personaActualizar.getApellido());
				txtTelefono.setText(personaActualizar.getTelefono());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(320, 424, 112, 23);
		contentPane.add(btnActualizar);
		
		lblNewLabel_2 = new JLabel("Teléfono");
		lblNewLabel_2.setBounds(130, 126, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBackground(new Color(224, 255, 255));
		txtTelefono.setBounds(234, 124, 122, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtBuscarApellido = new JTextField();
		txtBuscarApellido.setBackground(new Color(224, 255, 255));
		txtBuscarApellido.setBounds(130, 183, 259, 20);
		contentPane.add(txtBuscarApellido);
		txtBuscarApellido.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Buscar por apellido");
		lblNewLabel_4.setBounds(10, 189, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnBuscar = new JButton("Buscar..");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String apellido= txtBuscarApellido.getText();
				List<Persona> personasFiltro= personaRepositorio.buscarPorApellido(apellido);
				generarTabla(personasFiltro);
			}
		});
		btnBuscar.setBounds(407, 182, 89, 23);
		contentPane.add(btnBuscar);
		
		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setBackground(new Color(175, 238, 238));
		txtrNombre.setText("NOMBRE");
		txtrNombre.setBounds(36, 213, 133, 22);
		contentPane.add(txtrNombre);
		
		txtApellido_1 = new JTextField();
		txtApellido_1.setBackground(new Color(175, 238, 238));
		txtApellido_1.setText("APELLIDO");
		txtApellido_1.setBounds(173, 213, 133, 22);
		contentPane.add(txtApellido_1);
		txtApellido_1.setColumns(10);
		
		txtId = new JTextField();
		txtId.setBackground(new Color(175, 238, 238));
		txtId.setText("TELÉFONO");
		txtId.setBounds(309, 213, 133, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(575, 216, -103, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtId_1 = new JTextField();
		txtId_1.setBackground(new Color(175, 238, 238));
		txtId_1.setText("ID");
		txtId_1.setBounds(449, 210, 133, 24);
		contentPane.add(txtId_1);
		txtId_1.setColumns(10);

	}

	public void limpiarInterfaz() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
	}

	public void generarTabla(List<Persona> personas) {

		
		personaModel = new PersonaItemModel(personas);
		
		tablePersona.setModel(personaModel);
		
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		// personaRepositorio.findAll();
	}
}