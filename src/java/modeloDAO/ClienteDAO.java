package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Cliente;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con = null; // Inicializa la conexión como null
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Cliente buscar(String dni) {
        Cliente c = null; // Cambia a null para evitar crear un objeto vacío
        String sql = "SELECT * FROM cliente WHERE Dni=?";
        try {
            con = cn.getConexion(); // Usa el método corregido
            ps = con.prepareStatement(sql);
            ps.setString(1, dni); // Usa parámetros en la consulta
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                c = new Cliente(); // Crea el objeto solo si hay resultados
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEs(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return c;
    }

    // Operaciones CRUD
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEs(rs.getString(5));
                lista.add(cl);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public int agregar(Cliente cl) {
        String sql = "INSERT INTO cliente(Dni, Nombres, Direccion, Estado) VALUES (?, ?, ?, ?)";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEs());
            r = ps.executeUpdate(); // Guarda el resultado
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return r; // Devuelve el resultado
    }

    public Cliente listarId(int id) {
        Cliente cli = null; // Cambia a null para evitar crear un objeto vacío
        String sql = "SELECT * FROM cliente WHERE IdCliente=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Usa parámetros en la consulta
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                cli = new Cliente(); // Crea el objeto solo si hay resultados
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEs(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return cli;
    }

    public int actualizar(Cliente em) {
        String sql = "UPDATE cliente SET Dni=?, Nombres=?, Direccion=?, Estado=? WHERE IdCliente=?";
        int r = 0; // Inicializa el resultado
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEs());
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
        String sql = "DELETE FROM cliente WHERE IdCliente=?";
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
