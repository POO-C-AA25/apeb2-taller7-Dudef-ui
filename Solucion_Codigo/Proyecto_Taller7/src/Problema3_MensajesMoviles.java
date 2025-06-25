
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problema3_MensajesMoviles {

    public static void main(String[] args) {
        ArrayList<Mensajes> listaMensajes = new ArrayList<>();

        Scanner teclado = new Scanner(System.in);

        System.out.println("¿Qué tipo de mensaje desea enviar? (1 = SMS, 2 = MMS): ");
        int tipo = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingrese el número del remitente: ");
        int remitente = teclado.nextInt();
        System.out.print("Ingrese el número del destinatario: ");
        int destinatario = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Ingrese el nombre del destinatario: ");
        String nombreDest = teclado.nextLine();

        if (tipo == 1) {
            System.out.print("Ingrese el mensaje de texto: ");
            String texto = teclado.nextLine();

            SMS sms = new SMS(texto, remitente, destinatario, nombreDest);
            Mensajes.acumularMensajes(listaMensajes, sms);

        } else if (tipo == 2) {
            System.out.print("Ingrese el nombre del fichero multimedia: ");
            String fichero = teclado.nextLine();

            MMS mms = new MMS(fichero, remitente, destinatario, nombreDest);
            Mensajes.acumularMensajes(listaMensajes, mms);

        } else {
            System.out.println("Opción no válida.");
        }

        // Mostrar los mensajes enviados
        Mensajes.mostrarMensajes(listaMensajes);

    }

}

class Mensajes {

    //Atributos
    public int numRemitente;
    public int numDestinatario;
    public String nombreDestinatario;

    //Constructores
    public Mensajes() {
    }

    public Mensajes(int numRemitente, int numDestinatario, String nombreDestinatario) {
        this.numRemitente = numRemitente;
        this.numDestinatario = numDestinatario;
        this.nombreDestinatario = nombreDestinatario;
    }

    //Metodos
    public static void enviarMensaje(ArrayList<Mensajes> listaMensajes, Mensajes mensaje) {
        System.out.println("Enviando mensaje...");
        System.out.println(mensaje); // Muestra el mensaje enviado
        acumularMensajes(listaMensajes, mensaje);
        System.out.println("Mensaje enviado con éxito.");
    }

    public static void acumularMensajes(ArrayList<Mensajes> listaMensajes, Mensajes mensaje) {
        listaMensajes.add(mensaje);
    }

    public static void mostrarMensajes(ArrayList<Mensajes> listaMensajes) {
        System.out.println("\n--- Mensajes enviados ---");
        for (Mensajes mensaje : listaMensajes) {
            System.out.println(mensaje); // se invoca toString() según el tipo real (SMS o MMS)
        }
    }

}

class SMS extends Mensajes {

    //Atributos
    public String mensajeTexto;

    //Constructores
    public SMS() {
    }

    public SMS(String mensajeTexto, int numRemitente, int numDestinatario, String nombreDestinatario) {
        super(numRemitente, numDestinatario, nombreDestinatario);
        this.mensajeTexto = mensajeTexto;
    }

    //toString
    @Override
    public String toString() {
        return "\nSMS:"
                + "\nMensaje de texto: " + mensajeTexto
                + "\nRemitente: " + numRemitente
                + "\nDestinatario: " + numDestinatario
                + "\nNombre destinatario: " + nombreDestinatario;
    }

}

class MMS extends Mensajes {

    //Atributos
    public String nombreFichero;

    //Constructores
    public MMS() {
    }

    public MMS(String nombreFichero, int numRemitente, int numDestinatario, String nombreDestinatario) {
        super(numRemitente, numDestinatario, nombreDestinatario);
        this.nombreFichero = nombreFichero;
    }

    //toString
    @Override
    public String toString() {
        return "\nMMS:"
                + "\nNombre fichero: " + nombreFichero
                + "\nRemitente: " + numRemitente
                + "\nDestinatario: " + numDestinatario
                + "\nNombre destinatario: " + nombreDestinatario;
    }

}
/***
 * run:
¿Qué tipo de mensaje desea enviar? (1 = SMS, 2 = MMS): 
1
Ingrese el número del remitente: 1234
Ingrese el número del destinatario: 4321
Ingrese el nombre del destinatario: Pedro
Ingrese el mensaje de texto: Hola, ¿Comó estas?

--- Mensajes enviados ---

SMS:
Mensaje de texto: Hola, ¿Comó estas?
Remitente: 1234
Destinatario: 4321
Nombre destinatario: Pedro
BUILD SUCCESSFUL (total time: 29 seconds)

 */