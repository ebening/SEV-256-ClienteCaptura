package com.adinfi.sevCaptura.main;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.PropertyConfigurator;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;

import com.adinfi.sevCaptura.controller.LoginController;
import com.adinfi.sevCaptura.model.LoginModel;
import com.adinfi.sevCaptura.view.LoginView;


public class Principal {

	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator+"logConfig.properties");		
		try{
			
			UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
			
			
		}catch(Exception e){
			System.out.println("Login - DEBUG003");
			}		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {			        
				try {
					LoginModel loginModel = new LoginModel();
					LoginView loginView = new LoginView(loginModel);
					LoginController loginController=new LoginController(loginModel,loginView);
					loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		});
	}

}
