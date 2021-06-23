package com.example.demo.controlador.Producto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.demo.infraestructura.repositorio.ProductoRepositorio;
import com.example.demo.modelo.Persona;
import com.example.demo.modelo.Producto;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.persistence.PostLoad;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;

@Controller
public class ProductoUI extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtStock;

	JButton btnEliminar;
	JButton btnActualizar;

	Producto productoActualizar;

	boolean banderaActualizar = false;

	ProductoItemModel productoModel;

	@Autowired
	ProductoRepositorio productoRepositorio;

	private JTable tablePersona;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscarNombre;
	private JLabel lblNewLabel_4;

	public ProductoUI() {
		setTitle("Panel Producto");
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setResizable(true);

		setBounds(100, 100, 846, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("NOMBRE");
		lblNewLabel_3.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(22, 11, 96, 24);
		contentPane.add(lblNewLabel_3);

		txtNombre = new JTextField();
		txtNombre.setBounds(115, 10, 122, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("PRECIO");
		lblNewLabel_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(22, 81, 122, 24);
		contentPane.add(lblNewLabel_1);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(115, 80, 122, 24);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		JLabel lblNewLabel = new JLabel("DESCRIPCIÓN");
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
		lblNewLabel.setBounds(22, 46, 96, 14);
		contentPane.add(lblNewLabel);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(115, 47, 122, 24);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(22, 161, 121, 35);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro;
				if (banderaActualizar == true) {
					pro = productoActualizar;
					pro.setNombre(txtNombre.getText());
					pro.setDescripcion(txtDescripcion.getText());
					pro.setPrecio(Double.parseDouble(txtPrecio.getText()));
					pro.setStock(txtStock.getText());
					banderaActualizar = false;

				} else {
					pro = new Producto(txtNombre.getText(), txtDescripcion.getText(),
							(Double.parseDouble(txtPrecio.getText())), txtStock.getText());
				}
				productoRepositorio.save(pro);
				limpiarInterfaz();
				generarTabla(productoRepositorio.findAll());
			}
		});
		contentPane.add(btnGuardar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pEliminar = productoModel.getPersonaAt(tablePersona.getSelectedRow());
				productoRepositorio.delete(pEliminar);
				generarTabla(productoRepositorio.findAll());

			}
		});
		btnEliminar.setBounds(384, 352, 89, 23);
		contentPane.add(btnEliminar);

		

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productoActualizar = productoModel.getPersonaAt(tablePersona.getSelectedRow());
				txtNombre.setText(productoActualizar.getNombre());
				txtDescripcion.setText(productoActualizar.getDescripcion());
				//txtPrecio.setText(productoActualizar.getPrecio());
				txtStock.setText(productoActualizar.getStock());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(612, 352, 112, 23);
		contentPane.add(btnActualizar);

		lblNewLabel_2 = new JLabel("STOCK");
		lblNewLabel_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(22, 119, 89, 14);
		contentPane.add(lblNewLabel_2);

		txtStock = new JTextField();
		txtStock.setBounds(115, 115, 122, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		txtBuscarNombre = new JTextField();
		txtBuscarNombre.setBounds(22, 303, 220, 20);
		contentPane.add(txtBuscarNombre);
		txtBuscarNombre.setColumns(10);

		lblNewLabel_4 = new JLabel("BUSQUEDA RECOMENDADA POR NOMBRE");
		lblNewLabel_4.setFont(new Font("Showcard Gothic", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(22, 279, 220, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnBuscarApellido = new JButton("BUSCAR POR NOMBRE");
		btnBuscarApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtBuscarNombre.getText();
				List<Producto> personasFiltro = productoRepositorio.buscarPorNombreLike(nombre);
				generarTabla(personasFiltro);
			}
		});

		btnBuscarApellido.setBounds(58, 333, 141, 23);
		contentPane.add(btnBuscarApellido);

		JLabel lblNewLabel_5 = new JLabel("TABLA DE PRODUCTOS");
		lblNewLabel_5.setFont(new Font("Showcard Gothic", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(477, 11, 169, 24);
		contentPane.add(lblNewLabel_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(336, 50, 484, 278);
		contentPane.add(scrollPane);

		tablePersona = new JTable();
		scrollPane.setViewportView(tablePersona);
		tablePersona.setColumnSelectionAllowed(true);
		tablePersona.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		JButton btnCargar = new JButton("CARGAR");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Producto> productofil = productoRepositorio.findAll();
				generarTabla(productofil);
			}
		});
		btnCargar.setBounds(191, 161, 112, 35);
		contentPane.add(btnCargar);
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
			}
		});

	}

	public void limpiarInterfaz() {
		txtNombre.setText("");
		txtPrecio.setText("");
		txtDescripcion.setText("");
		txtStock.setText("");
	}

	public void generarTabla(List<Producto> productos) {

		productoModel = new ProductoItemModel(productos);

		tablePersona.setModel(productoModel);

		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
	}
}
