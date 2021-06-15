package com.example.demo.controlador.Producto;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.demo.modelo.Producto;



public class ProductoItemModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Producto> productos;
	private static final String[] COLUMN_NAMES = {"Nombre", "Descripcion", "Precio", "Cantidad"};

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

	public Class<?> getColumnClass(int columnIndex) {
        return Producto.class;
    }
	
    //the column header
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

	/*
	 * Override this if you want the values to be editable...
	 * 
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { //.... }
	 */

	/**
	 * This will return the user at the specified row...
	 * 
	 * @param row
	 * @return
	 */
	public Producto getProductoAt(int row) {
		return productos.get(row);
	}
}


