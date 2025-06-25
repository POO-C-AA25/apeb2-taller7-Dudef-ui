import java.util.*;

public class Problema5_EntradasTeatro {

    public static void main(String[] args) {
        // Crear zonas
        Zona principal = new Zona("Principal", 200, 25.0, 17.5, 0);
        Zona palcoB = new Zona("PalcoB", 40, 70.0, 40.0, 0);
        Zona central = new Zona("Central", 400, 20.0, 14.0, 0);
        Zona lateral = new Zona("Lateral", 100, 15.5, 10.0, 0);

        // Lista de entradas vendidas
        ArrayList<Entrada> entradasVendidas = new ArrayList<>();

        // Clientes
        Cliente c1 = new Cliente("Principal", "normal", null);
        Cliente c2 = new Cliente("PalcoB", "abonado", null);
        Cliente c3 = new Cliente("Central", "reducida", null);
        Cliente c4 = new Cliente("Lateral", "normal", null);

        // Realizar compras
        c1.comprarEntrda(c1, principal, entradasVendidas, 1, "Luis");
        c2.comprarEntrda(c2, palcoB, entradasVendidas, 2, "Ana");
        c3.comprarEntrda(c3, central, entradasVendidas, 3, "Carlos");
        c4.comprarEntrda(c4, lateral, entradasVendidas, 4, "Marta");

        // Consultar entradas
        c1.consultarEntrada(1, entradasVendidas);
        c2.consultarEntrada(2, entradasVendidas);
        c3.consultarEntrada(3, entradasVendidas);
        c4.consultarEntrada(4, entradasVendidas);
    }
}

class Zona {

    public String nombre;
    public int localidades;
    public double precioNormal;
    public double precioAbonado;
    public int localidadesOcupadas;

    //Constructores
    public Zona() {
    }

    public Zona(String nombre, int localidades, double precioNormal, double precioAbonado, int localidadesOcupadas) {
        this.nombre = nombre;
        this.localidades = localidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesOcupadas = localidadesOcupadas;
    }

    public boolean comprobarLocalidades() {
        return this.localidades > this.localidadesOcupadas;
    }

}

class Entrada{
    public int identificador;
    public Zona zona;
    public String nombreComprador;

    //Constructores
    public Entrada() {
    }

    public Entrada(int identificador, Zona zona, String nombreComprador) {
        this.identificador = identificador;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularPrecio() {
        return zona.precioNormal; // Entrada de precio normal
    }

}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.precioAbonado;
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override //Se sobreescribe el metodo
    public double calcularPrecio() {
        return zona.precioNormal * 0.85;
    }
}

class Cliente{
    public String zona;
    public String tipo;
    public Entrada entrada;

    //Constructores
    public Cliente() {
    }

    public Cliente(String zona, String tipo, Entrada entrada) {
        this.zona = zona;
        this.tipo = tipo;
        this.entrada = entrada;
    }

    public void comprarEntrda(Cliente cliente, Zona zonaSeleccionada, ArrayList<Entrada> listaEntradas, int id, String nombreComprador){
        if(zonaSeleccionada.comprobarLocalidades()){
            Entrada e;
            switch(cliente.tipo.toLowerCase()){
                case "abonado":
                    e = new EntradaAbonado(id, zonaSeleccionada, nombreComprador);
                    break;
                case "reducida":
                    e = new EntradaReducida(id, zonaSeleccionada, nombreComprador);
                    break;
                default:
                    e = new Entrada(id, zonaSeleccionada, nombreComprador);
                    break;
            }
            zonaSeleccionada.localidadesOcupadas++;
            cliente.entrada = e;
            listaEntradas.add(e);
            System.out.println("Entrada vendida: ID " + id + ", Precio: $" + e.calcularPrecio());
        } else {
            System.out.println("Zona completa. No se pudo vender entrada.");
        }
    }

    public void consultarEntrada(int identificador, ArrayList<Entrada> listaEntrdas){
        for(Entrada e : listaEntrdas){
            if(e.identificador == identificador){
                System.out.println("Entrada encontrada: ");
                System.out.println("ID: " + e.identificador);
                System.out.println("Comprador: " + e.nombreComprador);
                System.out.println("Zona: " + e.zona.nombre);
                System.out.println("Precio: $" + e.calcularPrecio());
                return;
            }
        }
        System.out.println("Entrada no encontrada.");
    }
}
 
