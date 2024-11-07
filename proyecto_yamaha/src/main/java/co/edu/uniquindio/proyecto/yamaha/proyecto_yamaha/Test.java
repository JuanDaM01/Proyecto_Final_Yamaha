package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Moto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.EmpleadoBuilder;

public class Test {
    public static void main(String[] args) {

        Moto moto1 = new Moto("N MAX", 15000000, 2, "CUALQUIER COSA","AUTOMATICA", "155 CC") ;
        System.out.print(moto1);


    }
}
