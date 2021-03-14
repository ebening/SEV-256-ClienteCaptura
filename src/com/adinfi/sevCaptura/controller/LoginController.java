package com.adinfi.sevCaptura.controller;


import com.adinfi.sevCaptura.model.LoginModel;
import com.adinfi.sevCaptura.model.MenuModel;
import com.adinfi.sevCaptura.view.LoginView;
import com.adinfi.sevCaptura.view.MenuView;

public class LoginController {
	private LoginModel model = null;
	private LoginView view = null;
	
	public LoginController(LoginModel model,LoginView view){
		this.model=model;
		this.view=view;
		this.view.addLoginListener(new LoginListener(this));
	}
	
	public void closeLoginWindow() {
		view.setVisible(false);
		view.dispose();
		System.exit(0);
	}
	public void validateLogin() {	
		
		if( model.validaLogin()){
			MenuModel model = new MenuModel();
			MenuView view = new MenuView(model);
			MenuController controller = new MenuController(model, view);
			controller.setUsuario(this.model.getUsuario());
			this.view.dispose();
			controller.lotesPendientes();
			
		}
		else{
			this.view.showMessage(this.model.getMensaje());
		
		}
	}
}
