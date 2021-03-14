package com.adinfi.sevCaptura.resources;

import java.util.ResourceBundle;

public class BundleManager{
	
	private static ResourceBundle bundle = null;

	public static String getValue(String bundle, String key)
	{
		getPropertie("com/adinfi/sevCaptura/resources/bundles/" + bundle);
		//getPropertie(bundle);
		
		return BundleManager.bundle.getString(key);
	}
	
	private static boolean getPropertie(String bundle)
	{
		BundleManager.bundle = ResourceBundle.getBundle(bundle);
		return false;
	}
}