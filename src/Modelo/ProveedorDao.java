package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;

	public boolean RegistrarProveedor(Proveedor proveedor) {
		String sql = "insert into proveedor (ruc, nombre, telefono, direccion, razon) values(?,?,?,?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, proveedor.getRuc());
			ps.setString(2, proveedor.getNombre());
			ps.setInt(3, proveedor.getTelefono());
			ps.setString(4, proveedor.getDireccion());
			ps.setString(5, proveedor.getRazon());
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

	public List ListarProveedor() {
		List<Proveedor> listaProveedor = new ArrayList<>();
		String sql = "select * from proveedor";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proveedor prov = new Proveedor();
				prov.setId(rs.getInt("id"));
				prov.setRuc(rs.getInt("ruc"));
				prov.setNombre(rs.getString("nombre"));
				prov.setTelefono(rs.getInt("telefono"));
				prov.setDireccion(rs.getString("direccion"));
				prov.setRazon(rs.getString("razon"));
				listaProveedor.add(prov);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return listaProveedor;

	}

	public boolean EliminarProveedor(int id) {
		String sql = "delete from proveedor where id = ?";
		try {
			con = cn.getConnection();
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
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}

	}

	public boolean ModificarProveedor(Proveedor prov) {
		String sql = "update proveedor set ruc=?, nombre=?, telefono=?, direccion=?, razon =? where id=?";

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, prov.getRuc());
			ps.setString(2, prov.getNombre());
			ps.setInt(3, prov.getTelefono());
			ps.setString(4, prov.getDireccion());
			ps.setString(5, prov.getRazon());
			ps.setInt(6, prov.getId());
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

}
