package com.adinfi.sevCaptura.resources;

import java.util.ResourceBundle;

public class ResourceBundleMassages{
		
	private static ResourceBundle bundle;
		
	public static String getValue(String key)
	{
		getPropertiesResource();
		String message = "";
		try {
			message = bundle.getString(key);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return message;
	}
	
	private static void getPropertiesResource(){
		if(bundle == null) {
			try {
				bundle = ResourceBundle.getBundle("com/adinfi/sevCaptura/resources/bundles/messages");
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}