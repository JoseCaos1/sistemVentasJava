package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ProductosDao {

	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;

	public boolean RegistrarProdutos(Productos productos) {
		String sql = "insert into productos (codigo, nombre, proveedor, stock, precio) values(?,?,?,?,?)";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productos.getCodigo());
			ps.setString(2, productos.getNombre());
			ps.setString(3, productos.getProveedor());
			ps.setInt(4, productos.getStock());
			ps.setDouble(5, productos.getPrecio());
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

	public void ConsultarProveedor(JComboBox proveedor) {
		String sql = "select nombre from proveedor";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				proveedor.addItem(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public List ListarProductos() {
		List<Productos> ListaProd = new ArrayList<>();
		String sql = "select * from productos";

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Productos prod = new Productos();
				prod.setId(rs.getInt("id"));
				prod.setCodigo(rs.getString("codigo"));
				prod.setNombre(rs.getString("nombre"));
				prod.setProveedor(rs.getString("proveedor"));
				prod.setStock(rs.getInt("stock"));
				prod.setPrecio(rs.getDouble("precio"));
				ListaProd.add(prod);
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return ListaProd;
	}

	public boolean EliminarProducto(int id) {
		String sql = "delete from productos where id = ?";
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

	public boolean ModificarProductos(Productos prod) {
		String sql = "update productos set codigo=?, nombre=?, proveedor=?, stock=?, precio=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, prod.getCodigo());
			ps.setString(2, prod.getNombre());
			ps.setString(3, prod.getProveedor());
			ps.setInt(4, prod.getStock());
			ps.setDouble(5, prod.getPrecio());
			ps.setInt(6, prod.getId());
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

	public Productos BuscarProductos(String cod) {
		Productos producto = new Productos();
		String sql = "select * from productos where codigo = ?";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, cod);
			rs = ps.executeQuery();
			if (rs.next()) {
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock"));
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return producto;
	}

}
