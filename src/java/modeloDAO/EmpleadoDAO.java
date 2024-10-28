package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Empleado;

public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection con = null; // Inicializa la conexión como null
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Empleado validar(String user, String dni) {
        Empleado em = null; // Cambia a null para evitar crear un objeto vacío
        String sql = "SELECT * FROM empleado WHERE User=? AND Dni=?";
        try {
            con = cn.getConexion(); // Usa el método corregido
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs = ps.executeQuery();
            if (rs.next()) { // Usa if en lugar de while
                em = new Empleado(); // Crea el objeto solo si hay resultados
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            // Cierra los recursos en el bloque finally
            cerrarRecursos();
        }
        return em;
    }

    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt("IdEmpleado")); // Usar el nombre de la columna
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
                em.setTel(rs.getString("Telefono"));
                em.setEstado(rs.getString("Estado"));
                em.setUser(rs.getString("User"));
                lista.add(em);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // Otras operaciones CRUD (agregar, listarId, actualizar, delete) siguen el mismo patrón...

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error cerrando recursos: " + e);
        }
    }

    public void agregar(Empleado nuevoEmpleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void actualizar(Empleado empleadoActualizado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
