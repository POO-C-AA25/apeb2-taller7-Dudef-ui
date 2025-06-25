
/** *
 * Un videoclub dispone de una serie de películas que pueden estar en DVD (con capacidad en Gb.)
 * o en VHS (una sola cinta por película y con cierto tipo de cinta magnetica). De las películas
 * interesa guardar el título, el autor, el año de edición y el idioma (o los idiomas, en caso de DVD).
 * El precio de alquiler de las películas varía en función del tipo de película.
 * Las DVD siempre son 10% mas caras que las de VHS.
 *
 * Note:
 * Analice los tipos de relación de las siguientes posibles clases: Pelicula, Dvd, Vhs, Soporte, etc, y justifique su diseño.
 * Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
 * Los escenarios de prueba pueden darse para el alquiler de una o varias peliculas según la preferencia del usuario.
 *
 * @author David Gonzalez/Dudef-ui
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Problema2_AlquilerPeliculas {

    public static void main(String[] args) {
        // Crear películas
        Pelicula p1 = new Pelicula("Inception", "Christopher Nolan", 2010);
        Pelicula p2 = new Pelicula("Interstellar", "Christopher Nolan", 2014);
        Pelicula p3 = new Pelicula("Matrix", "Wachowski", 1999);
        Pelicula p4 = new Pelicula("Gladiator", "Ridley Scott", 2000);
        Pelicula p5 = new Pelicula("Titanic", "James Cameron", 1997);

        // Listas para DVD y VHS
        ArrayList<DVD> listaDVD = new ArrayList<>();
        ArrayList<VHS> listaVHS = new ArrayList<>();

        // Crear y agregar 2 DVDs con 2 idiomas y 2 películas cada uno
        String[] idiomasDVD1 = {"Inglés", "Español"};
        Pelicula[] peliculasDVD1 = {p1, p2};
        SoportePelicula.agregarDVD(listaDVD, idiomasDVD1, peliculasDVD1);

        String[] idiomasDVD2 = {"Francés", "Alemán"};
        Pelicula[] peliculasDVD2 = {p3, p4};
        SoportePelicula.agregarDVD(listaDVD, idiomasDVD2, peliculasDVD2);

        // Crear y agregar 2 VHS con una película cada uno
        SoportePelicula.agregarVHS(listaVHS, "Español", p5);
        SoportePelicula.agregarVHS(listaVHS, "Inglés", p4);

        // Simular alquiler de 1 DVD (ej: "Inception") y 1 VHS (ej: "Titanic")
        SoportePelicula.alquilarPelicula(listaDVD, listaVHS, "Inception", "DVD");
        SoportePelicula.alquilarPelicula(listaDVD, listaVHS, "Titanic", "VHS");
    }

}

class SoportePelicula {

    protected double precioAlquiler = 5;

    //Constructores
    public SoportePelicula() {
    }

    public SoportePelicula(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    //Metodos de agregacion de DVD y VHS
    public static void agregarDVD(ArrayList<DVD> listaDVD, String[] idiomas, Pelicula[] peliculas) {
        listaDVD.add(new DVD(idiomas, peliculas));
    }

    public static void agregarVHS(ArrayList<VHS> listaVHS, String idioma, Pelicula pelicula) {
        listaVHS.add(new VHS(idioma, pelicula));
    }

    //Metodos de manejo de alquiler
    public static void alquilarPelicula(ArrayList<DVD> listaDVD, ArrayList<VHS> listaVHS, String nombre, String tipoAlquiler) {
        if (tipoAlquiler.equalsIgnoreCase("DVD")) {

            //Recorremos la lista de peliculas en DVD 
            for (DVD dvd : listaDVD) {

                //Recorremos las peliculas disponibles en cada DVD
                for (Pelicula nombrePelicula : dvd.pelicula) {
                    if (nombrePelicula.titulo.equalsIgnoreCase(nombre)) {
                        System.out.println("Pelicula encontrada, en DVD.");

                        //Se le aumenta un 10% al costo ya que es DVD
                        dvd.precioAlquiler = dvd.precioAlquiler * 1.1;

                        //Se imprimen los datos de la pelicula en DVD
                        System.out.println(dvd.toString());
                        return;
                    }
                }
            }

        } else if (tipoAlquiler.equalsIgnoreCase("VHS")) {
            for (VHS vhs : listaVHS) {
                if (vhs.pelicula.titulo.equalsIgnoreCase(nombre)) {
                    System.out.println("Pelicula encontrada, en VHS.");
                    System.out.println(vhs.toString());
                }
            }
        } else {
            System.out.println("Tipo de alquiler no disponible. ");
            return;
        }
    }

}

class DVD extends SoportePelicula {

    public String[] idiomas;
    public Pelicula[] pelicula;

    //Constructores
    public DVD() {
    }

    public DVD(String[] idiomas, Pelicula[] pelicula) {
        this.idiomas = idiomas;
        this.pelicula = pelicula;
    }

    public DVD(String[] idiomas, Pelicula[] pelicula, double precioAlquiler) {
        super(precioAlquiler);
        this.idiomas = idiomas;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "\nDVD:"
                + "\nIdiomas: " + Arrays.toString(idiomas)
                + "\nPeliculas: " + Arrays.toString(pelicula)
                + "\nPrecio: " + precioAlquiler;
    }

}

class VHS extends SoportePelicula {

    public String idioma;
    public Pelicula pelicula;

    //Constructores
    public VHS() {
    }

    public VHS(String idioma, Pelicula pelicula) {
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    public VHS(String idioma, Pelicula pelicula, double precioAlquiler) {
        super(precioAlquiler);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "\nVHS:"
                + "\nIdioma: " + idioma
                + "\nPelicula: " + pelicula;
    }

}

class Pelicula {

    public String titulo;
    public String autor;
    public int añoEdicion;

    //Constructores
    public Pelicula() {
    }

    public Pelicula(String titulo, String autor, int añoEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoEdicion = añoEdicion;
    }

    @Override
    public String toString() {
        return "\nPelicula:"
                + "\nTitulo: " + titulo
                + "\nAutor:" + autor
                + "\nAño edicion: " + añoEdicion;
    }
}

/***
run:
Pelicula encontrada, en DVD.

DVD:
Idiomas: [Inglés, Español]
Peliculas: [
Pelicula:
Titulo: Inception
Autor:Christopher Nolan
Año edicion: 2010, 
Pelicula:
Titulo: Interstellar
Autor:Christopher Nolan
Año edicion: 2014]
Precio: 5.5
Pelicula encontrada, en VHS.

VHS:
Idioma: Español
Pelicula: 
Pelicula:
Titulo: Titanic
Autor:James Cameron
Año edicion: 1997
BUILD SUCCESSFUL (total time: 0 seconds)

 */