package com.mx.chaos;

import com.mx.chaos.config.I18N;
import com.mx.chaos.controlador.CtrlReporte;
import com.mx.chaos.dao.ReporteDAO;
import com.mx.chaos.model.Usuarios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.apache.log4j.Logger;
import java.awt.Window.Type;

/**
 * Desktop class.
 *
 * @author Llopezg (chaos.as.a.service@cai.com)
 */
public class Desktop extends JFrame {

	final static Logger log = Logger.getLogger(Desktop.class);
	
	 
	JLabel jLabelLogo;
	
	JDesktopPane jDesktopPane = new JDesktopPane();
	JLabel jLabelFooterState = new JLabel(I18N.lang("desktop.jLabelFooterState") + System.getProperty("os.name"));

	// internal frames :
	FrameAbout frameAbout = new FrameAbout();
	Frame1 frame1 = new Frame1();

	// menu :
	MenuBar menuBar = new MenuBar();
	private final JPanel panel_1 = new JPanel();
	
	FondoPanel fondo = new FondoPanel();

	/**
	 * Constructor.
	 */
	public Desktop() {
		log.debug("START constructor...");

		// init frame :
		setTitle(I18N.lang("desktop.title"));
		//es para obtener la resolucion e la pabtalla y pasarlo al Bounds
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);


		getContentPane().add(jDesktopPane, BorderLayout.CENTER);
		getContentPane().add(jLabelFooterState, BorderLayout.SOUTH);
		frameAbout.setLocation(209, 69);
		
		jDesktopPane.add(frameAbout);
		frame1.setLocation(401, 127);
		jDesktopPane.add(frame1);
		panel_1.setBounds(0, 0, screenSize.width, screenSize.height);
		
		
		//las siguientes lineas son para modificar la imagen principal
		ImageIcon welcomeImage = new ImageIcon(getClass().getClassLoader().getResource("images/CAI.jpg"));
		log.info("ZAZAZAZAZZA : "+getClass().getClassLoader().getResource("images/CAI.jpg"));
        Image image = welcomeImage.getImage();
        Image newImage = image.getScaledInstance(screenSize.width, screenSize.height, java.awt.Image.SCALE_AREA_AVERAGING);//The error appears on this line
        welcomeImage = new ImageIcon(newImage);
		
        JLabel picLabel = new JLabel(welcomeImage);
		panel_1.add(picLabel);
		jDesktopPane.add(panel_1);
		
		//jLabelLogo = new JLabel(icon);
	

		//JPanel panel = new JPanel();
		//panel_1.add(jLabelLogo);
		

		// add the menu bar :
		setJMenuBar(menuBar);

		// menu listeners :
		// jMenuItemQuit :
		menuBar.jMenuItemQuit.addActionListener((ActionEvent ev) -> {
			log.debug("ActionEvent on " + ev.getActionCommand());

			if (confirmBeforeExit()) {
				System.exit(0);
			}
		});

		// jMenuItemFrameAbout :
		menuBar.jMenuItemFrameAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				log.debug("ActionEvent on " + ev.getActionCommand());

				frameAbout.setVisible(true);
			}
		});

		// jMenuItemFrame1 :
		menuBar.jMenuItemFrame1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				log.debug("ActionEvent on " + ev.getActionCommand());
				
				 
		        Usuarios user=new Usuarios();
		             
		        ReporteDAO repD = new ReporteDAO();

		        //Frame1 frm1 =new Frame1();
		        
		        CtrlReporte ctrlRep = new CtrlReporte(user, frame1, repD);
		        
		        ctrlRep.initComboBox();
		        
		        frame1.setVisible(true);
			}
		});

		// window closing event :
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				log.debug("WindowEvent on " + ev.paramString());

				if (confirmBeforeExit()) {
					System.exit(0);
				}
			}
		});

		setVisible(true);

		log.debug("End of constructor.");
	}

	/**
	 * Show confirm dialog before closing the window.
	 *
	 * @return boolean true user answer Yes.
	 */
	public boolean confirmBeforeExit() {
		log.debug("Display confirm dialog...");

		if (JOptionPane.showConfirmDialog(this, I18N.lang("desktop.confirmbeforeexitdialog.text"),
				I18N.lang("desktop.confirmbeforeexitdialog.title"), JOptionPane.YES_NO_OPTION) == 0) {
			log.debug("User answer YES.");
			return true;
		}

		log.debug("User answer NO.");
		return false;
	}
	
	class FondoPanel extends JPanel
    {
        private Image imagen;
        
        @Override
        public void paint(Graphics g)
        {
            imagen = new ImageIcon(getClass().getResource("/imagenes/uno.jpg")).getImage();
            
            //g.drawImage(imagen,0, 0, getWidth(), getHeight(),this);
            
            setOpaque(false);
            
            super.paint(g);
        }
    }
}
