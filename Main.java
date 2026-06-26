// Creación de Clase
class Tinaco {
    String marca;
    int cap_max; // Capacidad de litros L de agua 
    int nivel_agua;     // Nivel de litros de agua 

    // Implementación de clase
    public Tinaco(String marca, int cap_max) {
        this.marca = marca;
        this.cap_max = cap_max;
        this.nivel_agua = 0; // Tinaco vacío, contador 
    }

    // Método para rellenar agua
    public void rellenar(int litros) {
        if (nivel_agua + litros <= cap_max) {
            nivel_agua += litros;
            System.out.println("Se agregaron: " + litros + " litros al tinaco " + marca + ". Nivel actual: " + nivel_agua + "L.");
        } else {
            nivel_agua = cap_max;
            System.out.println("¡El tinaco " + marca + " se ha desbordado! Ahora está en su capacidad máxima de " + cap_max + "L.");
        }
    }

    // Método para sacar agua 
    public void sacar(int litros) {
        if (nivel_agua >= litros) {
            nivel_agua -= litros;
            System.out.println("Se uso " + litros + " litros y quedan " + nivel_agua + "L");
        } else {
            System.out.println("¡No hay suficiente agua!. Quedan: " + nivel_agua + "L");
        }
    }
}

public class Main {
    public static void main(String[] args) { 
        
        // Instanciación del objetos a partir de la clase (Creamos un tinaco de 1000 litros)
        Tinaco mi_Tinaco = new Tinaco("Rotoplox", 1000); // Nombre de la variable u objeto (Tinaco)

        // Uso de métodos (Simulación de uso)
        mi_Tinaco.rellenar(500);    // Metemos 500 litros
        mi_Tinaco.sacar(100);   // Sacamos 100 litros para lavar 
        mi_Tinaco.sacar(500);   // Intentamos sacar 500 litros (marcará que no alcanza)
        mi_Tinaco.rellenar(700);    // Lo intentamos llenar nuevamente para probar el límite del Tinaco
    }
}