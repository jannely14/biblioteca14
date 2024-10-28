package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloDAO.ClienteDAO;
import modeloDAO.EmpleadoDAO;
import modeloDAO.ProductoDAO;
import modeloDAO.VentaDAO;
import modeloDTO.Cliente;
import modeloDTO.Empleado;
import modeloDTO.Producto;
import modeloDTO.Venta;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    private final EmpleadoDAO edao = new EmpleadoDAO();
    private final ClienteDAO cdao = new ClienteDAO();
    private final ProductoDAO pdao = new ProductoDAO();
    private final VentaDAO vdao = new VentaDAO();
    private final CorreoDAO correoDAO = new CorreoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        switch (menu) {
            case "Principal":
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
                break;
            case "Empleado":
                manejarEmpleado(accion, request, response);
                break;
            case "Cliente":
                manejarCliente(accion, request, response);
                break;
            case "Producto":
                manejarProducto(accion, request, response);
                break;
            case "NuevaVenta":
                manejarNuevaVenta(accion, request, response);
                break;
            case "EnviarCorreo":
                manejarEnviarCorreo(accion, request, response);
                break;
            default:
                throw new IllegalArgumentException("Menú no válido");
        }
    }

    private void manejarEmpleado(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (accion) {
            case "Listar":
                List<Empleado> listaEmpleados = edao.listar();
                request.setAttribute("empleados", listaEmpleados);
                break;
            case "Agregar":
                agregarEmpleado(request);
                break;
            case "Actualizar":
                actualizarEmpleado(request);
                break;
            case "Eliminar":
                eliminarEmpleado(request);
                break;
        }
        request.getRequestDispatcher("Empleado.jsp").forward(request, response);
    }

    private void agregarEmpleado(HttpServletRequest request) {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setDni(request.getParameter("txtDni"));
        nuevoEmpleado.setNom(request.getParameter("txtNombres"));
        nuevoEmpleado.setTel(request.getParameter("txtTel"));
        nuevoEmpleado.setEstado(request.getParameter("txtEstado"));
        nuevoEmpleado.setUser(request.getParameter("txtUser"));
        edao.agregar(nuevoEmpleado);
    }

    private void actualizarEmpleado(HttpServletRequest request) {
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setId(Integer.parseInt(request.getParameter("txtId")));
        empleadoActualizado.setDni(request.getParameter("txtDni"));
        empleadoActualizado.setNom(request.getParameter("txtNombres"));
        empleadoActualizado.setTel(request.getParameter("txtTel"));
        empleadoActualizado.setEstado(request.getParameter("txtEstado"));
        empleadoActualizado.setUser(request.getParameter("txtUser"));
        edao.actualizar(empleadoActualizado);
    }

    private void eliminarEmpleado(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("txtId"));
        edao.eliminar(id);
    }

    private void manejarCliente(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (accion) {
            case "Listar":
                List<Cliente> listaClientes = cdao.listar();
                request.setAttribute("clientes", listaClientes);
                break;
            case "Agregar":
                agregarCliente(request);
                break;
            case "Actualizar":
                actualizarCliente(request);
                break;
            case "Eliminar":
                eliminarCliente(request);
                break;
        }
        request.getRequestDispatcher("Cliente.jsp").forward(request, response);
    }

    private void agregarCliente(HttpServletRequest request) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setDni(request.getParameter("txtDni"));
        nuevoCliente.setNom(request.getParameter("txtNombres"));
        nuevoCliente.setTel(request.getParameter("txtTel"));
        cdao.agregar(nuevoCliente);
    }

    private void actualizarCliente(HttpServletRequest request) {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setId(Integer.parseInt(request.getParameter("txtId")));
        clienteActualizado.setDni(request.getParameter("txtDni"));
        clienteActualizado.setNom(request.getParameter("txtNombres"));
        clienteActualizado.setTel(request.getParameter("txtTel"));
        cdao.actualizar(clienteActualizado);
    }

    private void eliminarCliente(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("txtId"));
        cdao.eliminar(id);
    }

    private void manejarProducto(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (accion) {
            case "Listar":
                List<Producto> listaProductos = pdao.listar();
                request.setAttribute("productos", listaProductos);
                break;
            case "Agregar":
                agregarProducto(request);
                break;
            case "Actualizar":
                actualizarProducto(request);
                break;
            case "Eliminar":
                eliminarProducto(request);
                break;
        }
        request.getRequestDispatcher("Producto.jsp").forward(request, response);
    }

    private void agregarProducto(HttpServletRequest request) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(request.getParameter("txtNombre"));
        nuevoProducto.setAutor(request.getParameter("txtAutor"));
        
        nuevoProducto.setStock(Integer.parseInt(request.getParameter("txtStock")));
        pdao.agregar(nuevoProducto);
    }

    private void actualizarProducto(HttpServletRequest request) {
        Producto productoActualizado = new Producto();
        productoActualizado.setId(Integer.parseInt(request.getParameter("txtId")));
        productoActualizado.setNombre(request.getParameter("txtNombre"));
        productoActualizado.setAutor(request.getParameter("txtAutor"));
        
        productoActualizado.setStock(Integer.parseInt(request.getParameter("txtStock")));
        pdao.actualizar(productoActualizado);
    }

    private void eliminarProducto(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("txtId"));
        pdao.eliminar(id);
    }

    private void manejarNuevaVenta(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (accion) {
            case "Realizar":
                realizarVenta(request);
                break;
        }
        request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
    }

    private void realizarVenta(HttpServletRequest request) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdCliente(Integer.parseInt(request.getParameter("txtIdCliente")));
        nuevaVenta.setIdEmpleado(Integer.parseInt(request.getParameter("txtIdEmpleado")));
        List<Producto> productos = obtenerProductosDesdeRequest(request); // Método para obtener los productos de la venta
        nuevaVenta.setProductos(productos);
        double totalPagar = calcularTotalPagar(productos);
        nuevaVenta.setTotal(totalPagar);
        vdao.agregar(nuevaVenta);
        request.setAttribute("totalPagar", totalPagar);
    }

    private List<Producto> obtenerProductosDesdeRequest(HttpServletRequest request) {
        // Lógica para obtener los productos desde el request (por ejemplo, de un formulario)
        return new ArrayList<>(); // Devuelve la lista de productos seleccionados
    }

    private double calcularTotalPagar(List<Producto> productos) {
        double total = 0.0;
        for (Producto producto : productos) {
            total = producto.getPrecio(); // Asegúrate de calcular el subtotal si es necesario
        }
        return total;
    }

    private void manejarEnviarCorreo(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (accion) {
            case "Enviar":
                enviarCorreo(request);
                break;
        }
        request.getRequestDispatcher("EnviarCorreo.jsp").forward(request, response);
    }

    private void enviarCorreo(HttpServletRequest request) {
        String destinatario = request.getParameter("txtDestinatario");
        String asunto = request.getParameter("txtAsunto");
        String mensaje = request.getParameter("txtMensaje");
        correoDAO.enviarCorreo(destinatario, asunto, mensaje); // Lógica para enviar el correo
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private static class CorreoDAO {

        public CorreoDAO() {
        }

        private void enviarCorreo(String destinatario, String asunto, String mensaje) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
