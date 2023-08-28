package Modelo;

import java.sql.*;

public class VentaDao {

	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int r;

	public int IdVenta() {
		int id = 0;
		String sql = "select max(id) from ventas";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return id;
	}

	public int RegistrarVenta(Venta v) {

		String sql = "insert into ventas (cliente, vendedor, total) values(?,?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, v.getCliente());
			ps.setString(2, v.getVendedor());
			ps.setDouble(3, v.getTotal());
			ps.execute();

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return r;
	}

	public int RegistrarDetalle(Detalle Dv) {
		String sql = "insert into detalle (cod_pro, cantidad, precio, id_venta) values" + "(?,?,?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, Dv.getCod_pro());
			ps.setInt(2, Dv.getCantidad());
			ps.setDouble(3, Dv.getPrecio());
			ps.setInt(4, Dv.getId());
			ps.execute();
		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return r;

	}

	public boolean ActualizarStock(int cant, String cod) {
		String sql = "update productos set stock = ? where codigo =?";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cant);
			ps.setString(2, cod);
			ps.execute();
		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return false;
	}
}
//https://www.youtube.com/watch?v=KyKzfmUuAxY&list=PLMPZIgg1n4JlSr_81Lhp8lem8Dtfe9qxV&index=29
//2:30 / 13:55
//video 29
