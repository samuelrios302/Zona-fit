package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                clientes.add(cliente);
            }
        }
        catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally {
            try {
                conexion.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorID(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var conexion = Conexion.getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar el cliente por id: " + e.getMessage());
        }
        finally {
            try {
                conexion.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        var sql = "INSERT INTO cliente(nombre, apellido, membresia) VALUES (?,?,?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.execute();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
        finally {
            try {
            conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        var sql = "UPDATE cliente SET nombre = ?, apellido = ? WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3, cliente.getId());
            ps.execute();

            return true;
        }
        catch (Exception e) {
            System.out.println("Error al actualizar un cliente: "+ e.getMessage()) ;
        }
        finally {
            try{
                conexion.close();
            }
            catch (Exception e) {
                System.out.println("Error al actualizar");
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        var sql = "DELETE FROM cliente WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        }
        catch (Exception e) {
            System.out.println("No se pudo eliminar el cliente: " + e.getMessage());
        }
        finally {
            try {
                conexion.close();
            }
            catch (Exception e) {
                System.out.println("No se pudo cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var clienteDao = new ClienteDAO();

        // Listar Clientes
        //System.out.println("***  Listar clientes ***");
        //var clientes = clienteDao.listarCliente();
        //clientes.forEach(System.out::println);

        // Buscar por id
//        var cliente2 = new Cliente(2);
//        System.out.println("Cliente antes de la búsqueda: " + cliente2);
//        var encontrado = clienteDao.buscarClientePorID(cliente2);
//        if (encontrado) {
//            System.out.println("Cliente encontrado: " + cliente2);
//        } else {
//            System.out.println("Cliente no encontrado: " + cliente2.getId());
//        }

        // Agregar cliente
//        Cliente cliente = new Cliente("Camilo", "Sanchez", 400);
//        var agregado = clienteDao.agregarCliente(cliente);
//        if (agregado) {
//            System.out.println("Agregado con Exito!");
//        }
//        else {
//            System.out.println("No se agregó");
//        }

        // Modificar Cliente
//        Cliente cliente = new Cliente(1, "Samuel","Ríos",100);
//        var modificado = clienteDao.modificarCliente(cliente);
//        if (modificado) {
//            System.out.println("Cliente Modificado!");
//        }
//        else {
//            System.out.println("Error al modificar");
//        }

        // Eliminar cliente
//        Cliente cliente = new Cliente(1);
//        var eliminado = clienteDao.eliminarCliente(cliente);
//        if (eliminado) {
//            System.out.println("Cliente eliminado: ");
//        } else {
//            System.out.println("No se pudo eliminar: ");
//        }
    }
}
