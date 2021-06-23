package com.example.demo.controlador.Producto;

import javax.swing.table.AbstractTableModel;

import com.example.demo.modelo.Producto;

import java.util.List;
public class ProductoItemModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private List<Producto> productos;
	private static final String[] COLUMN_NAMES = { "Nombre", "Descripcion", "Precio", "Stock" };

	public ProductoItemModel(List<Producto> productos) {

		this.productos = productos;

	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Producto producto = productos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = producto.getNombre();
			break;
		case 1:
			value = producto.getDescripcion();
			break;
		case 2:
			value = producto.getPrecio();
			break;

		case 3:
			value = producto.getStock();
			break;
		}

		return value;

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Producto.class;
	}


	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}


	public Producto getPersonaAt(int row) {
		return productos.get(row);
	}

}
