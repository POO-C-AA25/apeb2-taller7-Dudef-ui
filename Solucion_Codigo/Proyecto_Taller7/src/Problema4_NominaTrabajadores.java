
import java.util.*;

public class Problema4_NominaTrabajadores {

    public static void main(String[] args) {
        ArrayList<Trabajador> empleados = new ArrayList<>();

        // Crear un jefe
        Jefe jefe1 = new Jefe("Laura Fernández", "Av. Central 123", 1001, 0, true, "Jefe");

        // Contratar empleados de distintos tipos
        TrabajadoresMensuales empMensual = new TrabajadoresMensuales(
                "Pedro Gómez", "Calle Norte 55", 2001, 0, jefe1.nombreCompleto, true, "Mensual");

        Comisionistas empComision = new Comisionistas(
                15000, "Ana Torres", "Calle Sur 88", 2002, 0, jefe1.nombreCompleto, true, "Comisionista");

        TrabajadorHoras empHoras = new TrabajadorHoras(
                45, "Luis Pérez", "Av. Libertad 300", 2003, 0, jefe1.nombreCompleto, true, "Horas");

        // Contratar a todos
        Jefe.contratarEmpleados(empleados, jefe1);
        Jefe.contratarEmpleados(empleados, empMensual);
        Jefe.contratarEmpleados(empleados, empComision);
        Jefe.contratarEmpleados(empleados, empHoras);

        // Mostrar nómina completa
        System.out.println("Nómina antes del despido:");
        Jefe.imprimirNomina(empleados);

        // Despedir a un empleado
        System.out.println("\nDespidiendo al empleado con DNI 2002 (Ana Torres - comisionista):");
        Jefe.despedirEmpleados(empleados, 2002);

        // Mostrar nómina actualizada
        System.out.println("\nNómina después del despido:");
        Jefe.imprimirNomina(empleados);
    }

}

class Trabajador {

    public String nombreCompleto;
    public String direccion;
    public int DNI;
    public double sueldo;
    public String jefeAsignado;
    public String tipoEmpleado;
    public boolean contratado;

    //Constructores
    public Trabajador() {

    }

    //Constructor especifico para la clase Jefe (Sin jefe asignado)
    public Trabajador(String nombreCompleto, String direccion, int DNI, double sueldo, boolean contratado, String tipoEmpleado) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.DNI = DNI;
        this.sueldo = sueldo;
        this.contratado = contratado;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Trabajador(String nombreCompleto, String direccion, int DNI, double sueldo, String jefeAsignado, boolean contratado, String tipoEmpleado) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.DNI = DNI;
        this.sueldo = sueldo;
        this.jefeAsignado = jefeAsignado;
        this.contratado = contratado;
        this.tipoEmpleado = tipoEmpleado;
    }

    //toString
    @Override

    public String toString() {
        return "\n[Tipo: " + tipoEmpleado
                + "]\nNombre: " + nombreCompleto
                + "\nDirección: " + direccion
                + "\nDNI: " + DNI
                + "\nJefe: " + (jefeAsignado != null ? jefeAsignado : "Sin jefe")
                + "\nSueldo: $" + sueldo
                + "\nContratado: " + (contratado ? "Sí" : "No");
    }

}

class Jefe extends Trabajador {

    //Constructores
    public Jefe() {
    }

    public Jefe(String nombreCompleto, String direccion, int DNI, double sueldo, boolean contratado, String tipoEmpleado) {
        super(nombreCompleto, direccion, DNI, sueldo, contratado, tipoEmpleado);
    }

    //Metodos solicitados
    public static void despedirEmpleados(ArrayList<Trabajador> listaEmpleados, int DNI) {
        for (Trabajador listaEmpleado : listaEmpleados) {

            if (DNI == listaEmpleado.DNI) {
                System.out.println("Se despedira al siguiente empleado: ");
                listaEmpleado.toString();

                //Se elimina el objeto cuando se cumpla la condicion
                listaEmpleados.remove(listaEmpleado);

                System.out.println("Empleado despedido. ");
            }
        }
    }

    public static void contratarEmpleados(ArrayList<Trabajador> listaEmpleados, Trabajador empleado) {
        //Se agrega a la lista de empleados
        listaEmpleados.add(empleado);
    }

    public static void imprimirNomina(ArrayList<Trabajador> listaEmpleados) {
        for (Trabajador empleado : listaEmpleados) {

            // Comisionista
            if (empleado instanceof Comisionistas) { //instanceof sirve para verificar si un objeto es instancia o pertenece a una clase en especifico
                //para asi usar comportamientos especificos
                Comisionistas com = (Comisionistas) empleado; //Convertimos de la clase general trabajador a comisionista
                com.sueldo = com.ventasRealizadas * 0.30;
            } // Por horas
            else if (empleado instanceof TrabajadorHoras) {
                int horas;
                TrabajadorHoras hora = (TrabajadorHoras) empleado;
                horas = hora.horasTrabajadas;
                if (horas <= 40) {
                    hora.sueldo = horas * 5;
                } else {
                    hora.sueldo = 40 * 5 + (horas - 40) * 8;
                }
            } // Jefe (sueldo fijo)
            else if (empleado.tipoEmpleado.equalsIgnoreCase("Jefe")) {
                empleado.sueldo = 2500;
            } //Trabajador mensual (sueldo fijo)
            else if (empleado.tipoEmpleado.equalsIgnoreCase("Mensual")) {
                empleado.sueldo = 475;
            }
            // Mostrar datos
            System.out.println(empleado);
        }
    }

    //toString
    @Override
    public String toString() {
        return super.toString() + "\n[Sueldo fijo como jefe]";
    }

}

class TrabajadoresMensuales extends Trabajador {

    //Constructores
    public TrabajadoresMensuales() {
    }

    public TrabajadoresMensuales(String nombreCompleto, String direccion, int DNI, double sueldo, String jefeAsignado, boolean contratado, String tipoEmpleado) {
        super(nombreCompleto, direccion, DNI, sueldo, jefeAsignado, contratado, tipoEmpleado);
    }

    //toString
    @Override
    public String toString() {
        return super.toString() + "\n[Empleado mensual]";
    }

}

class Comisionistas extends Trabajador {

    public double ventasRealizadas;

    //Constructores
    public Comisionistas() {
    }

    public Comisionistas(double ventasRealizadas, String nombreCompleto, String direccion, int DNI, double sueldo, String jefeAsignado, boolean contratado, String tipoEmpleado) {
        super(nombreCompleto, direccion, DNI, sueldo, jefeAsignado, contratado, tipoEmpleado);
        this.ventasRealizadas = ventasRealizadas;
    }

    //toString
    @Override
    public String toString() {
        return super.toString()
                + "\nVentas realizadas: $" + ventasRealizadas;
    }

}

class TrabajadorHoras extends Trabajador {

    public int horasTrabajadas;

    //Constructores
    public TrabajadorHoras() {
    }

    public TrabajadorHoras(int horasTrabajadas, String nombreCompleto, String direccion, int DNI, double sueldo, String jefeAsignado, boolean contratado, String tipoEmpleado) {
        super(nombreCompleto, direccion, DNI, sueldo, jefeAsignado, contratado, tipoEmpleado);
        this.horasTrabajadas = horasTrabajadas;
    }

    //toString
    @Override
    public String toString() {
        return super.toString()
                + "\nHoras trabajadas: " + horasTrabajadas;
    }
}

/***
 * run:
Nómina antes del despido:

[Tipo: Jefe]
Nombre: Laura Fernández
Dirección: Av. Central 123
DNI: 1001
Jefe: Sin jefe
Sueldo: $2500.0
Contratado: Sí
[Sueldo fijo como jefe]

[Tipo: Mensual]
Nombre: Pedro Gómez
Dirección: Calle Norte 55
DNI: 2001
Jefe: Laura Fernández
Sueldo: $475.0
Contratado: Sí
[Empleado mensual]

[Tipo: Comisionista]
Nombre: Ana Torres
Dirección: Calle Sur 88
DNI: 2002
Jefe: Laura Fernández
Sueldo: $4500.0
Contratado: Sí
Ventas realizadas: $15000.0

[Tipo: Horas]
Nombre: Luis Pérez
Dirección: Av. Libertad 300
DNI: 2003
Jefe: Laura Fernández
Sueldo: $240.0
Contratado: Sí
Horas trabajadas: 45

Despidiendo al empleado con DNI 2002 (Ana Torres - comisionista):
Se despedira al siguiente empleado: 
Empleado despedido. 

Nómina después del despido:

[Tipo: Jefe]
Nombre: Laura Fernández
Dirección: Av. Central 123
DNI: 1001
Jefe: Sin jefe
Sueldo: $2500.0
Contratado: Sí
[Sueldo fijo como jefe]

[Tipo: Mensual]
Nombre: Pedro Gómez
Dirección: Calle Norte 55
DNI: 2001
Jefe: Laura Fernández
Sueldo: $475.0
Contratado: Sí
[Empleado mensual]

[Tipo: Horas]
Nombre: Luis Pérez
Dirección: Av. Libertad 300
DNI: 2003
Jefe: Laura Fernández
Sueldo: $240.0
Contratado: Sí
Horas trabajadas: 45
BUILD SUCCESSFUL (total time: 0 seconds)
 */
