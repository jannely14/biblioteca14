package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modeloDTO.Venta;

public class VentaDAO {

    private Connection con = null; // Inicializa la conexión como null
    private final Conexion cn = new Conexion();
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public String generarSerie() {
        String numeroSerie = "";
        String sql = "SELECT MAX(NumeroSerie) FROM ventas";
        try {
            con = cn.getConexion(); // Usa el método corregido
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                numeroSerie = rs.getString(1) != null ? rs.getString(1) : "0"; // Maneja el caso donde no hay serie
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return numeroSerie;
    }

    public String idVentas() {
        String idVentas = "";
        String sql = "SELECT MAX(IdVentas) FROM ventas";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                idVentas = rs.getString(1) != null ? rs.getString(1) : "0"; // Maneja el caso donde no hay ID
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return idVentas;
    }

    public int guardarVenta(Venta ve) {
        String sql = "INSERT INTO ventas(IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getMonto());
            ps.setString(6, ve.getEstado());
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    public int guardarDetalleVentas(Venta venta) {
        String sql = "INSERT INTO detalle_ventas(IdVentas, IdProducto, Cantidad, PrecioVenta) VALUES (?, ?, ?, ?)";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getId());
            ps.setInt(2, venta.getIdproducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando recursos: " + e);
        }
    }

    public void agregar(Venta nuevaVenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
