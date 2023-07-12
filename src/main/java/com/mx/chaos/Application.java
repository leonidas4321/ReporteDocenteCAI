/*
 * The MIT License
 *
 * Copyright 2023 Llopezg (chaos.as.a.service@cai.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mx.chaos;

import org.apache.log4j.Logger;

import com.mx.chaos.conexion.DBConection;
import com.mx.chaos.config.I18N;
import com.mx.chaos.config.PROP;
import com.mx.chaos.dao.ReporteDAO;

/**
 * Application class.
 *
 * @author Llopezg (chaos.as.a.service@cai.com)
 */
public class Application
{
    final static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args)
    {
        log.info("Initializing the application...");
        
        ReporteDAO getEventRecords=new ReporteDAO();
//		// TODO Auto-generated method stub
//		 // t.start();
		//System.out.println(getEventRecords.getEventRecordList()); 

        PROP.init();
        I18N.init();
        macosConfig();

        log.info("Starting " + PROP.getProperty("app.finalName") + " Application...");

        // display the desktop frame :
        new Desktop();

        log.info("Application " + PROP.getProperty("app.finalName") + " started.");
    }

    /**
     * Special settting for macOS.
     */
    public static void macosConfig()
    {
        if (System.getProperty("os.name").contains("Mac"))
        {
            log.debug("Special settings for macOS users...");

            // take the menu bar off the jframe :
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }
    
    
    
    
    
    
}
