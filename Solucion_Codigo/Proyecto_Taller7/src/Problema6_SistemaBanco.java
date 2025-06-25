public class Problema6_SistemaBanco {
    public static void main(String[] args) {
        
        CuentaCheques cliente1 = new CuentaCheques("001", "Ana Pérez");
        
        CuentaAhorros cliente2 = new CuentaAhorros("002", "Luis Torres", 0.03);
        
        CuentaPlatino cliente3 = new CuentaPlatino("003", "Carla Ríos");

        // Operaciones sobre cuenta de cheques
        cliente1.depositar(500);
        cliente1.retirar(600); // Permitido aunque cause sobregiro

        // Operaciones sobre cuenta de ahorros
        cliente2.depositar(1000);
        cliente2.retirar(200);
        cliente2.calcularInteres(); // Aplicar interés

        // Operaciones sobre cuenta platino
        cliente3.depositar(2000);
        cliente3.retirar(500);
        cliente3.calcularInteres(); // Aplicar interés

        // Mostrar resultados
        System.out.println(cliente1);
        System.out.println("\n" + cliente2);
        System.out.println("\n" + cliente3);
    }
}


class Cuenta {
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    // Constructores

    public Cuenta(){
      
    }
    public Cuenta(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0; // Balance inicial en 0
    }

    // Método para depositar dinero a la cuenta
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
        }
    }

    // Método para retirar dinero 
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
        }
    }

    // Método para obtener el balance actual
    public double obtenerBalance() {
        return balance;
    }

    // Representación en texto del estado de la cuenta
    public String toString() {
        return "Cuenta: " + numeroCuenta 
              + "\nCliente: " + nombreCliente 
              + "\nBalance: $" + balance;
    }
}


class CuentaAhorros extends Cuenta {
    public double tasaInteres;

    // Constructores
    public CuentaAhorros(){
      
    }
    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    // Método para calcular el interés
    public void calcularInteres() {
        if (balance > 0) {
            double interes = balance * tasaInteres;
            depositar(interes);
        }
    }

    // Se reescribe el metodo
    @Override
    public void retirar(double cantidad) {
        if (cantidad <= balance) {
            balance -= cantidad;
        }
    }
}

// Clase CuentaCheques permite sobregiros
class CuentaCheques extends Cuenta {
    
    //Constructores

    public CuentaCheques(){
      
    }
    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    // Permite retirar aunque quede balance negativo
    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
        }
    }
}

// Clase CuentaPlatino similar a CuentaAhorros pero sin penalizaciones
class CuentaPlatino extends Cuenta {
    public double tasaInteres;

    //Constructores
    public CuentaPlatino(){
      
    }
    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = 0.10; // Tasa fija del 10%
    }

    // Aplica interés del 10%
    public void calcularInteres() {
        if (balance > 0) {
            double interes = balance * tasaInteres;
            depositar(interes);
        }
    }

    // Se sobreescribe el metodo 
    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            balance -= cantidad;
        }
    }
}
/***
 * run:
Cuenta: 001
Cliente: Ana Pérez
Balance: $-100.0

Cuenta: 002
Cliente: Luis Torres
Balance: $824.0

Cuenta: 003
Cliente: Carla Ríos
Balance: $1650.0
BUILD SUCCESSFUL (total time: 0 seconds)

 */