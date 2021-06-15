package com.example.demo.controlador.Producto;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

import com.example.demo.infraestructura.repositorio.ProductoRepositorio;
import com.example.demo.modelo.Producto;


@SuppressWarnings("serial")
@Controller
public class ProductoUI extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTable tableProducto;

	JButton btnEliminar;
	JButton btnActualizar;
	JButton btnCargar;
	JButton btnBuscar;

	Producto productoActualizar;

	boolean banderaActualizar = false;

	ProductoItemModel prodModel;

	@Autowired
	ProductoRepositorio productoRepository;
	private JTextField txtBuscarNombre;
	protected CrudRepository<Producto, Integer> productoRepositorio;

	public ProductoUI() {

		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable
		setBounds(0, 0, 1534, 770);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("INSERTAR   PRODUCTO  ");
		lblNewLabel_6.setFont(new Font("Papyrus", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(147, 86, 282, 24);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setBounds(115, 153, 74, 24);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(115, 188, 81, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblPrecio = new JLabel("Precio:   $");
		lblPrecio.setBounds(115, 228, 96, 14);
		getContentPane().add(lblPrecio);

		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(115, 262, 62, 14);
		getContentPane().add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(219, 154, 189, 24);
		getContentPane().add(txtNombre);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(219, 189, 189, 24);
		getContentPane().add(txtDescripcion);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(219, 223, 189, 25);
		getContentPane().add(txtPrecio);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(219, 258, 189, 24);
		getContentPane().add(txtCantidad);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto prod;
				if (banderaActualizar == true) {
					prod = productoActualizar;
					prod.setNombre(txtNombre.getText().toUpperCase());
					prod.setDescripcion(txtDescripcion.getText().toUpperCase());
					prod.setPrecio(txtPrecio.getText());
					prod.setStock(txtCantidad.getText());
					banderaActualizar = false;

				} else {
					prod = new Producto(txtNombre.getText().toUpperCase(), txtDescripcion.getText().toUpperCase(),
							txtPrecio.getText(), txtCantidad.getText());
				}
				productoRepositorio.save(prod);
				limpiarInterfaz();
				generarTabla((List<Producto>) productoRepositorio.findAll());

			}
		});
		btnGuardar.setBounds(431, 195, 121, 35);
		getContentPane().add(btnGuardar);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(10, 343, 608, 14);
		getContentPane().add(horizontalStrut);

		JLabel lblNewLabel_7 = new JLabel("BUSCAR   PRODUCTO  ");
		lblNewLabel_7.setFont(new Font("Papyrus", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(164, 393, 265, 24);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_5 = new JLabel("Buscar por nombre  ");
		lblNewLabel_5.setBounds(68, 483, 143, 13);
		getContentPane().add(lblNewLabel_5);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(606, 10, 35, 721);
		getContentPane().add(verticalStrut);

		btnCargar = new JButton("Cargar base de datos");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Producto> productoFiltro = productoRepository.findAll();
				generarTabla(productoFiltro);

			}
		});
		btnCargar.setBounds(960, 29, 249, 35);
		getContentPane().add(btnCargar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto prodEliminar = prodModel.getProductoAt(tableProducto.getSelectedRow());
				productoRepositorio.delete(prodEliminar);
				generarTabla((List<Producto>) productoRepositorio.findAll());
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(719, 672, 110, 35);
		getContentPane().add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productoActualizar = prodModel.getProductoAt(tableProducto.getSelectedRow());
				txtNombre.setText(productoActualizar.getNombre().toUpperCase());
				txtDescripcion.setText(productoActualizar.getDescripcion().toUpperCase());
				txtPrecio.setText(productoActualizar.getPrecio());
				txtCantidad.setText(productoActualizar.getStock());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(1299, 672, 110, 35);
		getContentPane().add(btnActualizar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(651, 74, 825, 588);
		getContentPane().add(scrollPane);

		tableProducto = new JTable();
		scrollPane.setViewportView(tableProducto);
		
		txtBuscarNombre = new JTextField();
		txtBuscarNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnBuscar.setEnabled(true);	
			}
		});
		txtBuscarNombre.setBounds(187, 480, 181, 19);
		getContentPane().add(txtBuscarNombre);
		txtBuscarNombre.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtBuscarNombre.getText().toUpperCase();
				List<Producto> productoFiltro= ((ProductoRepositorio) productoRepositorio).buscarPorNombreLike(nombre);
				generarTabla(productoFiltro);
				apagarBotones();
			}
		});
		btnBuscar.setBounds(398, 479, 85, 21);
		getContentPane().add(btnBuscar);

		tableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
			}
		});
	}

	public void limpiarInterfaz() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
	}

	public void generarTabla(List<Producto> productos) {
		prodModel = new ProductoItemModel(productos);
		tableProducto.setModel((TableModel) prodModel);

		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
	}

	public void apagarBotones() {
		txtBuscarNombre.setText("");
		btnBuscar.setEnabled(false);
	}
}