package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;


import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.interfaces.DistribuidoraModelInterface;

import java.util.ArrayList;
import java.util.List;

public class Distribuidora implements DistribuidoraModelInterface {

private static Distribuidora instancia;
    ArrayList<Cliente> listaClientesDistribuidora = new ArrayList<Cliente>();
    List<Empleado> listaEmpleadosDistribuidora = new ArrayList<Empleado>();
    List<Producto> listaProductosDistribuidora = new ArrayList<Producto>();
    List<Venta> listaVentasDistribuidora = new ArrayList<Venta>();

    public Distribuidora(){}

    public static Distribuidora getInstancia(){
        if(instancia == null){
            instancia = new Distribuidora();
        }
        return instancia;
    }

    public boolean crearEmpleado(String nombre, String cedula, String email, String celular, int edad,String tipo){
        Empleado empleadoEncontrado = obtenerEmpleado(cedula);
        if(empleadoEncontrado == null){
            Empleado empleado = getBuildEmpleado(nombre, cedula, email, celular, edad, tipo);
            getListaEmpleadosDistribuidora().add(empleado);
            return true;
        }else{
            return  false;
        }
    }

    public boolean crearEmpleado(Empleado nuevoEmpleado){
        Empleado empleadoEncontrado = obtenerEmpleado(nuevoEmpleado.getCedula());
        if(empleadoEncontrado == null){
            getListaEmpleadosDistribuidora().add(nuevoEmpleado);
            return true;
        }else{
            return  false;
        }
    }

    private Empleado getBuildEmpleado(String nombre, String cedula, String email, String celular, int edad, String tipo) {
        return Empleado.builder()
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .celular(celular)
                .edad(edad)
                .tipo(tipo)
                .build();
    }

    public boolean crearCliente(String nombre, String cedula, String email, String celular, int edad){
        Cliente clienteEncontrado = obtenerCliente(cedula);
        if(clienteEncontrado == null){
            Cliente cliente = getBuildCliente(nombre, cedula, email, celular, edad);
            getListaClientesDistribuidora().add(cliente);
            return true;
        }else{
            return  false;
        }
    }
    public boolean crearCliente(Cliente nuevoCliente){
        Cliente clienteEncontrado = obtenerCliente(nuevoCliente.getCedula());
        if(clienteEncontrado == null){
            getListaClientesDistribuidora().add(nuevoCliente);
            return true;
        }
        else{
            return  false;
        }
    }

    private Cliente getBuildCliente(String nombre, String cedula, String email, String celular, int edad) {
        return Cliente.builder()
                .nombre(nombre)
                .cedula(cedula)
                .email(email)
                .celular(celular)
                .edad(edad).build();
    }


    public ArrayList<Cliente> getListaClientesDistribuidora() {
        return listaClientesDistribuidora;
    }

    public void setListaClientesDistribuidora(ArrayList<Cliente> listaClientesDistribuidora) {
        this.listaClientesDistribuidora = listaClientesDistribuidora;
    }

    public List<Empleado> getListaEmpleadosDistribuidora() {
        return listaEmpleadosDistribuidora;
    }

    public void setListaEmpleadosDistribuidora(ArrayList<Empleado> listaEmpleadosDistribuidora) {
        this.listaEmpleadosDistribuidora = listaEmpleadosDistribuidora;
    }

    @Override
    public Empleado agregarEmpleado(Empleado nuevoEmpleado) {
        listaEmpleadosDistribuidora.add(nuevoEmpleado);
        return nuevoEmpleado;
    }

    @Override
    public Empleado obtenerEmpleado(String cedula) {
        Empleado empleado = null;
        for (Empleado empleado1: getListaEmpleadosDistribuidora()) {
            if(empleado1.getCedula().equalsIgnoreCase(cedula)){
                empleado = empleado1;
                break;
            }
        }
        return empleado;
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleadoActualizado) {
        for (int i = 0; i < listaEmpleadosDistribuidora.size(); i++){
            if(listaEmpleadosDistribuidora.get(i).getCedula().equals(empleadoActualizado.getCedula())){
                listaEmpleadosDistribuidora.set(i, empleadoActualizado);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        for (int i = 0; i < listaEmpleadosDistribuidora.size(); i++){
            if(listaEmpleadosDistribuidora.get(i).getCedula().equals(cedula)){
                listaEmpleadosDistribuidora.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cliente agregarCliente(Cliente nuevoCliente) {
        listaClientesDistribuidora.add(nuevoCliente);
        return nuevoCliente;
    }

    @Override
    public Cliente obtenerCliente(String cedula) {
        Cliente cliente = null;
        for (Cliente cliente1: getListaClientesDistribuidora()) {
            if(cliente1.getCedula().equalsIgnoreCase(cedula)){
                cliente = cliente1;
                break;
            }
        }
        return cliente;
    }

    @Override
    public boolean actualizarCliente(Cliente clienteActualizado) {
        for (int i = 0; i < listaClientesDistribuidora.size(); i++){
            if(listaClientesDistribuidora.get(i).getCedula().equals(clienteActualizado.getCedula())){
                listaClientesDistribuidora.set(i, clienteActualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(String cedula) {
        for (int i = 0; i < listaClientesDistribuidora.size(); i++){
            if(listaClientesDistribuidora.get(i).getCedula().equals(cedula)){
                listaClientesDistribuidora.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<String> listaTipoMotos() {
        List<Moto.TipoMoto> listaTipoMotos = List.of(Moto.TipoMoto.values());
        List<String> listaTipoMotoString = new ArrayList<>();
        for(Moto.TipoMoto tipo: listaTipoMotos)
        {
            listaTipoMotoString.add(tipo.name());
        }
        return listaTipoMotoString;
    }

    public List<Producto> getListaProductosDistribuidora() {
        return listaProductosDistribuidora;
    }
    public boolean crearProducto(Producto producto) {
        Producto productoEncontrado = obtenerProducto(producto.getNombre());
        if(productoEncontrado == null){
            getListaProductosDistribuidora().add(producto);
            return true;
        }
        else{
            return  false;
        }

    }

    private Producto obtenerProducto(String nombre) {
        Producto producto = null;
        for (Producto producto1: getListaProductosDistribuidora()) {
            if(producto1.getNombre().equalsIgnoreCase(nombre)){
                producto = producto1;
                break;
            }
        }
        return producto;
    }

    public boolean actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < listaProductosDistribuidora.size(); i++){
            if(listaProductosDistribuidora.get(i).getNombre().equals(productoActualizado.getNombre())){
                listaProductosDistribuidora.set(i, productoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(String nombre) {
        for (int i = 0; i < listaProductosDistribuidora.size(); i++){
            if(listaProductosDistribuidora.get(i).getNombre().equals(nombre)){
                listaProductosDistribuidora.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Venta> getListaVentasDistribuidora() {
        return listaVentasDistribuidora;
    }
}
