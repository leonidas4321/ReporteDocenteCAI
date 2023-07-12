package com.mx.chaos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mx.chaos.conexion.DBConection;
import com.mx.chaos.model.Asistencia;
import com.mx.chaos.model.Usuarios;

public class ReporteDAO extends DBConection {

	public List<String> getEventRecordList() {
		Statement ps = null;
		ResultSet result = null;
		Connection con = getConexion();

		List<String> eventRecordList = new ArrayList<>();

		LocalDate date = LocalDate.now();
		// LocalDate prevDate = date.minusDays(1);
		LocalDate prevDate2 = date.minusDays(2);

		System.out.println(date);
		System.out.println(prevDate2);

		String sql = "SELECT * FROM checkinout,userinfo where userinfo.userid=checkinout.userid and DateValue([checktime])<='"
				+ date + "'";
		HashMap<String, String> eventRecord = new HashMap<>();
		try {
			ps = con.createStatement();

			result = ps.executeQuery(sql);

			if (result.next()) {
				Time ariseTime = result.getTime("checktime");
				Date ariseDate = result.getDate("checktime");

				eventRecord.put("ariseTime", ariseTime.toString());
				eventRecord.put("ariseDate", ariseDate.toString());
				eventRecord.put("userId", result.getString("userid"));
				eventRecord.put("card", result.getString("Badgenumber"));
				eventRecord.put("nombre", result.getString("Name"));

				eventRecordList.add(new Gson().toJson(eventRecord));

			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return eventRecordList;
	}

	public List<Asistencia> buscaAsistencias(JsonObject val) {

		
		JsonObject gsonObj = val.getAsJsonObject();
		int id = gsonObj.get("idUser").getAsInt();
		
		
		List<Asistencia> usuariosList = new ArrayList<Asistencia>();

		Connection con = getConexion();

		Statement ps;
		ResultSet result;
		
		StringBuffer sb = new StringBuffer();
		  sb.append("SELECT punches.emp_id  AS userId,punches.punch_time AS punchTime, punches.terminal_id  as terminalId ");
		  sb.append(" FROM att_punches punches WHERE punches.emp_id = "+id);
		if (!gsonObj.get("dateFrmt1").isJsonNull() && !gsonObj.get("dateFrmt2").isJsonNull()) {
			String date1 = gsonObj.get("dateFrmt1").getAsString();
			String date2 = gsonObj.get("dateFrmt2").getAsString();
			sb.append(" and punches.punch_time  BETWEEN '"+date1+"'").append(" and '"+date2+"'");
		}
		System.out.println("consulta contruida : "+sb.toString());
		try {
			ps = con.createStatement();
			result = ps.executeQuery(sb.toString());

			while (result.next()) {
				Asistencia userZK = new Asistencia();
				userZK.setUserId(result.getInt("userId"));
				userZK.setPunch_time(result.getString("punchTime"));
				userZK.setTerminalId(result.getInt("terminalId"));

				usuariosList.add(userZK);
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

		return usuariosList;
	}

	public ArrayList<Usuarios> obtenerListaUsuarios() {

		String sql = "SELECT employee.id AS userId,employee.emp_pin AS empNo, employee.emp_lastname || ' ' || employee.emp_firstname as fullName "
				+ "FROM [hr_employee] employee,[hr_department] department "
				+ "WHERE department.id = employee.emp_dept  "
				+ "AND employee.emp_active = 1 AND employee.emp_privilege not in('3') order by fullName ";

		ArrayList<Usuarios> mListaPuestos = new ArrayList<Usuarios>();

		Connection con = getConexion();

		Statement ps;
		ResultSet result;
		try {
			ps = con.createStatement();
			result = ps.executeQuery(sql);

			while (result.next()) {
				Usuarios userZK = new Usuarios();
				userZK.setUserId(result.getInt("userId"));
				userZK.setEmpNo(result.getInt("empNo"));
				userZK.setFullName(result.getString("fullName"));
				mListaPuestos.add(userZK);
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

		return mListaPuestos;
	}

}