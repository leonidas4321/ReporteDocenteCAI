/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.chaos;

import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;
import com.mx.chaos.config.I18N;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

/**
 *
 * @author llopezg
 */
public class Frame1 extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	final static Logger log = Logger.getLogger(Frame1.class);


	public Frame1() {
		log.debug("START constructor...");

		setTitle(I18N.lang("frame1.title"));
		setLocation(new Random().nextInt(120) + 10, new Random().nextInt(120) + 10);
		setSize(550, 350);

		setClosable(true);

		setIconifiable(true);
		setMaximizable(false);
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		initComponents();

		log.debug("End of constructor.");

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jboxUsuarios = new javax.swing.JComboBox();
		btnGuardar = new javax.swing.JButton();
		jDateChooser1 = new com.toedter.calendar.JDateChooser();
		jDateChooser1.setBorder(new TitledBorder(null, "Fecha Inicial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jDateChooser2 = new com.toedter.calendar.JDateChooser();
		jDateChooser2.setBorder(new TitledBorder(null, "Fecha Final", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jDateChooser2.setToolTipText("");
		
		
		btnReporte = new javax.swing.JButton();
		btnReporte.setIcon(new ImageIcon(Frame1.class.getResource("/images/icons-imprimir.png")));
		//log.info("QQQQQQQQQQ : "+getClass().getClassLoader().getResource("/images/icons-imprimir.png"));
		
		
		btnBuscar = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tablaDatos = new javax.swing.JTable();


		btnGuardar.setText("guardar");


		btnReporte.setText("Genera Reporte");

		btnBuscar.setText("buscar");

		jLabel1.setText("Usuarios");

		tablaDatos.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {

		}));
		jScrollPane1.setViewportView(tablaDatos);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(jboxUsuarios, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscar))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnReporte, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup()
							.addGap(29)
							.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGap(124)
							.addComponent(jDateChooser1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(126)
							.addComponent(jDateChooser2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGap(39)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel1)
						.addComponent(jboxUsuarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(248)
					.addComponent(btnGuardar)
					.addContainerGap(397, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(106, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jDateChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jDateChooser2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnReporte)
					.addGap(119))
		);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>



	// Variables declaration - do not modify
	public javax.swing.JButton btnBuscar;
	public com.toedter.calendar.JDateChooser jDateChooser1;
	public javax.swing.JButton btnGuardar;
	public javax.swing.JButton btnReporte;
	public com.toedter.calendar.JDateChooser jDateChooser2;
	public javax.swing.JLabel jLabel1;
	public javax.swing.JScrollPane jScrollPane1;
	public javax.swing.JTable tablaDatos;
	public javax.swing.JComboBox jboxUsuarios;
	// End of variables declaration
}
