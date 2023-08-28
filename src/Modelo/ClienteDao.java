package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {

	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public boolean RegistrarCliente(Cliente cl) {
		String sql = "insert into clientes (dni, nombre, telefono, direccion, razon) values (?,?,?,?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cl.getDni());
			ps.setString(2, cl.getNombre());
			ps.setInt(3, cl.getTelefono());
			ps.setString(4, cl.getDireccion());
			ps.setString(5, cl.getRazon());
			ps.execute();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public List ListarCliente() {
		List<Cliente> ListaCl = new ArrayList<>();
		String sql = "select * from clientes";

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cl = new Cliente();
				cl.setId(rs.getInt("id"));
				cl.setDni(rs.getInt("dni"));
				cl.setNombre(rs.getString("nombre"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setDireccion(rs.getString("direccion"));
				cl.setRazon(rs.getString("razon"));
				ListaCl.add(cl);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return ListaCl;
	}

	public boolean EliminarCliente(int id) {
		String sql = "delete from clientes where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	public boolean ModificarCliente(Cliente cl) {
		String sql = "update clientes set dni=?, nombre=?, telefono=?, direccion=?, razon=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cl.getDni());
			ps.setString(2, cl.getNombre());
			ps.setInt(3, cl.getTelefono());
			ps.setString(4, cl.getDireccion());
			ps.setString(5, cl.getRazon());
			ps.setInt(6, cl.getId());
			ps.execute();
			return true;

		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public Cliente BuscarCliente(int dni) {
		Cliente cl = new Cliente();
		String sql = "select * from clientes where dni = ?";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dni);
			rs = ps.executeQuery();
			if (rs.next()) {
				cl.setNombre(rs.getString("nombre"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setDireccion(rs.getString("direccion"));
				cl.setRazon(rs.getString("razon"));
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return cl;
	}

}

//  https://www.youtube.com/watch?v=HXZag7Sn3qc&list=PLMPZIgg1n4JlSr_81Lhp8lem8Dtfe9qxV&index=11
//3:01 / 13:06
