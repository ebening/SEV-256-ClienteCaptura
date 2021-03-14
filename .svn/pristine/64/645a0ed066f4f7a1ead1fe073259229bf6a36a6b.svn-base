package com.adinfi.sevCaptura.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.adinfi.sevCaptura.connections.ConnectionManagerCM;
import com.adinfi.sevCaptura.connections.ConnectionManagerDB2;
import com.adinfi.sevCaptura.entities.Aplicativo;
import com.adinfi.sevCaptura.entities.Area;
import com.adinfi.sevCaptura.entities.Modulo;
import com.adinfi.sevCaptura.entities.Puesto;
import com.adinfi.sevCaptura.entities.Usuario;
import com.ibm.mm.sdk.common.DKException;


public class LoginDAO implements LoginDAOInterface {
	
			private ConnectionManagerCM connectionManager = null;
			//private DKDatastoreICM connCM = null;
			private ConnectionManagerDB2 connManagerDB2 = null;
			private Connection connDB2 = null;
			private PreparedStatement preparedStat = null;
			private ResultSet result = null;

	@Override
	public boolean establecerConexionDB2() throws SQLException,
			InstantiationException, IllegalAccessException,ClassNotFoundException {
			
				this.connManagerDB2 = new ConnectionManagerDB2();
				if(connManagerDB2.connectDB2()){
					this.connDB2 = connManagerDB2.getConnectionDB2();
					return true;
				}
				return false;
	}

	@Override
	public boolean validaConexionDB2() throws SQLException {
				boolean conexionAbierta = false;	
				conexionAbierta = this.connManagerDB2.validaConexionDB2();
				return conexionAbierta;
	}

	@Override
	public void cerrarConexionDB2() throws SQLException {
				connManagerDB2.disconectDB2();
	}

	@Override
	public Usuario validaUsuario(String idUsuario, String password) throws SQLException {
		
		String query=LoginDAOQuery.SELECT_USER_BY_USER_NAME;
		preparedStat= connDB2.prepareStatement(query);
		preparedStat.setString(1, idUsuario);
		preparedStat.setString(2, password);
		result = preparedStat.executeQuery();
		Usuario usuario = null;
		if(result != null){
			if(result.next()){
				usuario = new Usuario();
				usuario.setIdUsuario(result.getString("id_usuario"));
				usuario.setTipoUsuario(result.getInt("id_tipo_usuario"));
				usuario.setNombre(result.getString("nombres"));
				usuario.setApellido(result.getString("apellidos"));
				usuario.setPassword(result.getString("pass_empleado"));
				usuario.setCorreo(result.getString("correo"));
				
				Puesto puesto = new Puesto();
				puesto.setIdPuesto(result.getString("id_puesto"));
				puesto.setNombre(result.getString("nombre_puesto"));
				puesto.setMonto(result.getDouble("monto"));
				
				usuario.setPuesto(puesto);
				
				Area area = new Area();
				area.setId(result.getInt("id_area"));
				area.setNombre(result.getString("nombre_area"));
				
				usuario.setArea(area);
			}
		}
		
		this.closeQuery();
		return usuario;
	}

	private void closeQuery() throws SQLException{
		if(preparedStat != null){
			preparedStat.close();
			preparedStat = null;
		}
		
		if(result != null){
			result.close();
			result = null;
		}
	}


	@Override
	public boolean extraerListasAcceso(Usuario usuario) throws SQLException {
		ArrayList<Aplicativo> aplicaciones = new ArrayList<Aplicativo>();
		preparedStat = connDB2.prepareStatement(LoginDAOQuery.SELECT_PERMISOS_APLICATIVOS);
		preparedStat.setInt(1, usuario.getTipoUsuario());
		result = preparedStat.executeQuery();
		
		if(result != null){
			while(result.next()){
				Aplicativo app = new Aplicativo();
				app.setIdAplicativo(result.getInt("id_aplicativo"));
				app.setNombre(result.getString("nombre_aplicativo"));
				aplicaciones.add(app);
			}
		}
		
		usuario.setAccesoAplicativos(aplicaciones);
		this.closeQuery();
		
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		preparedStat = connDB2.prepareStatement(LoginDAOQuery.SELECT_PERMISOS_MODULOS);
		preparedStat.setInt(1, usuario.getTipoUsuario());
		result = preparedStat.executeQuery();
	
		if(result != null){
			while(result.next()){
				Modulo modulo = new Modulo();
				modulo.setIdModulo(result.getInt("id_modulo"));
				modulo.setNombreModulo(result.getString("nombre_modulo"));
				modulo.setIdAplicativo(result.getInt("id_aplicativo"));
				modulos.add(modulo);
			}
		}
		
		usuario.setAccesoModulos(modulos);
		this.closeQuery();
		
		return true;
	}

	@Override
	public boolean establecerConexionCM() throws DKException, Exception{
		connectionManager = new ConnectionManagerCM();
		
		if(connectionManager.connectCM()){
			//connCM = connectionManager.getConnectionCM();
			return true;
		}		
		return false;
	}
	
	@Override
	public void cerrarConexionCM() throws DKException, Exception{
			this.connectionManager.disconectCM();
	}
	
	@Override
	public boolean validaConexionCM() throws DKException, Exception{
		boolean conexionValida = false;
				
		conexionValida = this.connectionManager.validaConexionCM();
		
		return conexionValida;
	}


}
