package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Producto;

public class ProductoDAO {

    private final Conexion cn = new Conexion();
    private Connection con = null; // Inicializa la conexión como null
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Producto buscar(int id) {
        Producto p = null; // Cambia a null para evitar crear un objeto vacío
        String sql = "SELECT * FROM producto WHERE idproducto=?";
        try {
            con = cn.getConexion(); // Usa el método corregido
            ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Usa parámetros en la consulta
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                p = new Producto(); // Crea el objeto solo si hay resultados
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.set.Autor(rs.getString(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return p; // Devuelve el objeto encontrado o null
    }

    public int actualizarstock(int id, int stock) {
        String sql = "UPDATE producto SET Stock=? WHERE idproducto=?";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    //*******Operaciones CRUD***************//    
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto em = new Producto();
                em.setId(rs.getInt(1));
                em.setNom(rs.getString(2));
                em.setAutor(rs.getString(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return lista; // Devuelve la lista de productos
    }

    public int agregar(Producto p) {
        String sql = "INSERT INTO producto(Nombres, Precio, Stock, Estado) VALUES (?, ?, ?, ?)";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getAutor());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getEstado());
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    public Producto listarId(int id) {
        Producto pr = null; // Cambia a null para evitar crear un objeto vacío
        String sql = "SELECT * FROM producto WHERE IdProducto=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Usa parámetros en la consulta
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                pr = new Producto(); // Crea el objeto solo si hay resultados
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setAutor(rs.getString(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return pr; // Devuelve el objeto encontrado o null
    }

    public int actualizar(Producto em) {
        String sql = "UPDATE producto SET Nombres=?, Precio=?, Stock=?, Estado=? WHERE IdProducto=?";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setString(2, em.getAutor());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.setInt(5, em.getId());
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    public void delete(int id) {
        String sql = "DELETE FROM producto WHERE IdProducto=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Usa parámetros en la consulta
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
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

    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
