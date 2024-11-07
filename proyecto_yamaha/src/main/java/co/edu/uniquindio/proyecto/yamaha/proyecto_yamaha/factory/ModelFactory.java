package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.*;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Dto.UsuarioDto;


import java.time.LocalDate;
import java.util.List;

public class ModelFactory  {

    private static ModelFactory modelFactory;

    private Distribuidora distribuidora;
    private ModelFactory(){
        distribuidora = new Distribuidora();
        inicializarDatos();
    }

    public static ModelFactory getInstancia(){
        if(modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private void inicializarDatos() {
        inicializarDatosEmpleado();
        inicializarDatosCliente();
        inicializarProductos();
        inializarVentas();
    }

    private void inializarVentas() {

        Empleado empleado1 = Empleado.builder()
                .nombre("Santiago")
                .cedula("1090274004")
                .email("santiagoml222@gmail.com")
                .celular("3147904473")
                .edad(18)
                .tipo("ADMIN")
                .build();

        Cliente cliente1 = Cliente.builder()
                .nombre("Sebastian")
                .cedula("1090178904")
                .email("sebasoml222@gmail.com")
                .celular("3143334473")
                .edad(21)
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Juan")
                .cedula("1095208100")
                .email("juanda526@gmail.com")
                .celular("3014025436")
                .edad(18)
                .build();

        Venta venta1 = new Venta(LocalDate.now(), empleado1, cliente1, "TARJETA");
        Venta venta2 = new Venta(LocalDate.now(), empleado1, cliente2, "EFECTIVO");

        distribuidora.getListaVentasDistribuidora().add(venta1);
        distribuidora.getListaVentasDistribuidora().add(venta2);
    }

    private void inicializarDatosEmpleado(){

        Empleado empleado1 = Empleado.builder()
                .nombre("Santiago")
                .cedula("1090274004")
                .email("santiagoml222@gmail.com")
                .celular("3147904473")
                .edad(18)
                .tipo("ADMIN")
                .build();

        Empleado empleado2 = Empleado.builder()
                .nombre("Nicole")
                .cedula("112233")
                .email("ngjx__@gmail.com")
                .celular("3331212121")
                .edad(20)
                .tipo("ADMIN")
                .build();

        Empleado empleado3 = Empleado.builder()
                .nombre("Samuel")
                .cedula("334567321")
                .email("samuel9865@gmail.com")
                .celular("9953679035")
                .edad(33)
                .tipo("ADMIN")
                .build();

        Empleado empleado4 = Empleado.builder()
                .nombre("kevin")
                .cedula("108765")
                .email("kcr02@gmail.com")
                .celular("7786542091")
                .edad(28)
                .tipo("ADMIN")
                .build();

        Empleado empleado5 = Empleado.builder()
                .nombre("Paula")
                .cedula("075462")
                .email("22ps22@gmail.com")
                .celular("2265438717")
                .edad(22)
                .tipo("VENDEDOR")
                .build();

        distribuidora.getListaEmpleadosDistribuidora().add(empleado1);
        distribuidora.getListaEmpleadosDistribuidora().add(empleado2);
        distribuidora.getListaEmpleadosDistribuidora().add(empleado3);
        distribuidora.getListaEmpleadosDistribuidora().add(empleado4);
        distribuidora.getListaEmpleadosDistribuidora().add(empleado5);
    }

    private void inicializarDatosCliente(){

        Cliente cliente1 = Cliente.builder()
                .nombre("Sebastian")
                .cedula("1090178904")
                .email("sebasoml222@gmail.com")
                .celular("3143334473")
                .edad(21)
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("ana")
                .cedula("2190237804")
                .email("ana@gmail.com")
                .celular("3257904893")
                .edad(18)
                .build();

        distribuidora.getListaClientesDistribuidora().add(cliente1);
        distribuidora.getListaClientesDistribuidora().add(cliente2);
    }

    private void inicializarProductos()
    {
        Moto moto1 = new Moto("MT09", 57500000, 3, "Moto MT09 890 cc ", "DEPORTIVAS", "890 cc");
        Moto moto2 = new Moto("N MAX", 1600000, 5, "Moto N MAX 155 cc ", "AUTOMATICA", "155 cc");

        Repuesto repuesto1 = new Repuesto("Farola MT09 V4", 947000, 10,  "Farola completa MT09 version 4");

        distribuidora.getListaProductosDistribuidora().add(moto1);
        distribuidora.getListaProductosDistribuidora().add(moto2);
        distribuidora.getListaProductosDistribuidora().add(repuesto1);

    }


    public boolean crearEmpleado(String nombre, String cedula, String email, String celular, int edad,String tipo){
        return distribuidora.crearEmpleado(nombre, cedula, email, celular, edad, tipo);
    }

    public boolean crearEmpleado(Empleado empleado){
        return distribuidora.crearEmpleado(empleado);
    }


    public List<Empleado> obtenerEmpleados() {
        return distribuidora.getListaEmpleadosDistribuidora();
    }

    public boolean actualizarEmpleado(Empleado empleadoActualizado) {
        return distribuidora.actualizarEmpleado(empleadoActualizado);
    }

    public boolean eliminarEmpleado(Empleado empleadoSeleccionado) {
        return distribuidora.eliminarEmpleado(empleadoSeleccionado.getCedula());
    }

    public boolean crearCliente(Cliente cliente){
        return distribuidora.crearCliente(cliente);
    }


    public List<Cliente> obtenerClientes() {
        return distribuidora.getListaClientesDistribuidora();
    }

    public boolean actualizarCliente(Cliente clienteActualizado) {
        return distribuidora.actualizarCliente(clienteActualizado);
    }

    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        return distribuidora.eliminarCliente(clienteSeleccionado.getCedula());
    }

    public Empleado autenticarUsuario(UsuarioDto usuarioDto) {
        Empleado empleadoAutenticado = distribuidora.obtenerEmpleado(usuarioDto.usuario());
        if ((empleadoAutenticado == null)){
            return null;
        }
        if (empleadoAutenticado.getCedula().equals(usuarioDto.contrasena())){
            return empleadoAutenticado;
        }
        return null;
    }

    public List<Producto> obtenerProductos() {
        return distribuidora.getListaProductosDistribuidora();
    }

    public List<String> listaTipoMotos() {
        return distribuidora.listaTipoMotos();
    }

    public boolean crearProducto(Producto producto) {
        return  distribuidora.crearProducto(producto);
    }

    public boolean actualizarProducto(Producto productoActualizado) {
        return distribuidora.actualizarProducto(productoActualizado);
    }

    public boolean eliminarProducto(Producto productoSeleccionado) {
        return distribuidora.eliminarProducto(productoSeleccionado.getNombre());
    }

    public List<Venta> obtenerVentas() {
        return distribuidora.getListaVentasDistribuidora();
    }
}
