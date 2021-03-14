package com.adinfi.sevCaptura.controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;



public class FocusListenerCaptura implements FocusListener {

	@Override
	public void focusGained(FocusEvent arg0) {
		arg0.getComponent().setBackground(Color.ORANGE);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		arg0.getComponent().setBackground(Color.WHITE);

	}

}
