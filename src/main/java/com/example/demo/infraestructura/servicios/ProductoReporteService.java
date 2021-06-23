package com.example.demo.infraestructura.servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import com.example.demo.infraestructura.repositorio.ProductoRepositorio;
import com.example.demo.modelo.Producto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ProductoReporteService {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	public String generarReporte() throws FileNotFoundException, JRException{
		
		List<Producto> productos= productoRepositorio.findAll();
		
		File file= ResourceUtils.getFile("classpath:reportes\\Reporte_Productos.jrxml"); 
		
		JRBeanCollectionDataSource datasource= new JRBeanCollectionDataSource(productos); 
		
		Map<String,Object>parametros = new HashedMap();
		parametros.put("creado por", "Kevin Chango");
		
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath()); 
		

		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parametros,datasource);

		JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\ASUS\\OneDrive\\Reporte_Productos.html");
		JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\ASUS\\OneDrive\\Reporte_Productos.pdf");
		
	return null;
	
	
	
	}
}
