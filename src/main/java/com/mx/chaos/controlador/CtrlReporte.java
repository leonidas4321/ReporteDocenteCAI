package com.mx.chaos.controlador;

import com.google.gson.JsonObject;
import com.mx.chaos.Frame1;
import com.mx.chaos.dao.ReporteDAO;
import com.mx.chaos.model.Asistencia;
import com.mx.chaos.model.Usuarios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

/**
 *
 * @author llopezg
 */
public class CtrlReporte implements ActionListener {

	Logger log = Logger.getLogger(CtrlReporte.class);

	private ReporteDAO ctrlB;
	private Usuarios user;
	private Frame1 frm1;
	ArrayList<Usuarios> fakultases;

	public CtrlReporte(Usuarios user, Frame1 frm1, ReporteDAO ctrlB) {
		this.user = user;
		this.frm1 = frm1;
		this.ctrlB = ctrlB;
		this.frm1.btnBuscar.addActionListener(this);
		this.frm1.jDateChooser1 = frm1.jDateChooser1;
		this.frm1.jDateChooser2 = frm1.jDateChooser2;
		this.frm1.btnReporte.addActionListener(this);

	}

	public void initComboBox() {

		ctrlB = new ReporteDAO();
		fakultases = ctrlB.obtenerListaUsuarios();

		DefaultComboBoxModel model = new DefaultComboBoxModel();
		log.info("tamaño de la lista usuarios : " + fakultases.size());
		frm1.jboxUsuarios.setModel(model);
		Iterator iterador = fakultases.iterator();

		while (iterador.hasNext()) {
			Usuarios mPuesto = (Usuarios) iterador.next();
			frm1.jboxUsuarios.addItem(mPuesto);
		}

	}

	@Override
	public void actionPerformed(ActionEvent et) {

		// obtienes el valor del usuario seleccionado
		Usuarios user = (Usuarios) frm1.jboxUsuarios.getSelectedItem();

		if (et.getSource() == frm1.btnBuscar) {

			JsonObject obj = new JsonObject();
			obj.addProperty("idUser", user.getUserId());
			log.info("BUSCAR dentro");

			String dateFrmt1 = null;
			String dateFrmt2 = null;

			Date date1 = null;
			Date date2 = null;

			date1 = frm1.jDateChooser1.getDate();
			date2 = frm1.jDateChooser2.getDate();
			if (date1 == null && date2 == null) {
				obj.addProperty("dateFrmt1", dateFrmt1);
				obj.addProperty("dateFrmt2", dateFrmt2);
				mostrarTabla(obj);

			} else if (date1 != null && date2 != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dateFrmt1 = formatter.format(date1);
				dateFrmt2 = formatter.format(date2);
				obj.addProperty("dateFrmt1", dateFrmt1);
				obj.addProperty("dateFrmt2", dateFrmt2);
				mostrarTabla(obj);

			} else {
				JOptionPane.showMessageDialog(null, "falta seleccionar UNA fecha");
			}

		}

		if (et.getSource() == frm1.btnReporte) {
			log.info("Imprimir reporte");
			try {
				buildReportRun();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private InputStream getFileFromResourceAsStream(String fileName) {
		
		// Realiza la conversion de InputStream para poder compilar el archivo jrxml
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}

	}

	private void buildReportRun() throws IOException {
		log.info("buildReportRun : ingresa al metodo : ok ");
		
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		
		// "C:/Users/llopezg_developer/workspace_CAI_Excel/ReporteDocenteCAI/report01.jasper";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("param", "22");

		String fileName = "jasper/seiem.jrxml";

		InputStream is = getFileFromResourceAsStream(fileName);
		
		try {
			jasperReport = JasperCompileManager.compileReport(is);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, ctrlB.getConexion());
			JasperViewer.viewReport(jasperPrint, false);

		} catch (JRException ex) {
			log.error("Error:\n" + ex.getLocalizedMessage());
		}
	}

	public void mostrarTabla(JsonObject obj) {

		DefaultTableModel modelo = new DefaultTableModel();

		frm1.tablaDatos.setModel(modelo);

		modelo.addColumn("id");
		modelo.addColumn("empNo");
		modelo.addColumn("empLastName");

		ctrlB = new ReporteDAO();
		List<Asistencia> fakultases = ctrlB.buscaAsistencias(obj);
		if (!fakultases.isEmpty()) {
			for (Asistencia fak : fakultases) {
				Object[] datos = new Object[6];
				datos[0] = fak.getUserId();
				datos[1] = fak.getPunch_time();
				datos[2] = fak.getTerminalId();

				modelo.addRow(datos);

			}

		} else {
			JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
		}
	}

}
