public class Main {// Contenedor general del programa

    static class Tinaco{ // Declaración de clase Tinaco
    String marca; // Atributos del objeto Tinaco, Marca, Capacidad y Nivel de Agua
    int cap_Maxima;
    int nivel_Agua;

    public Tinaco(String marca, int cap_Maxima){ // Constructor de la Clase
        this.marca = marca;
        this.cap_Maxima = cap_Maxima;
        this.nivel_Agua = 0; // Inicializa el tinaco vacío
    }

    public void rellenar(int litros){ // Metodo de uso Rellenar o agregar agua al Tinaco
        if (nivel_Agua + litros <= cap_Maxima){ // Estructura condicional de "si se cumplela condición" entonces rellenar
            nivel_Agua += litros;
            System.out.println("Se agregaron: " + litros + " Quedan L. " + this.nivel_Agua);
    }
        else // Estructura condicional "en caso contrario"
        {
            nivel_Agua = cap_Maxima;
            System.out.println("El tinaco " + marca + "no puede retener más agua, se está desbordando. Tinaco en su capacidad máxima " + cap_Maxima + "L.");
        }
    }
        public void retirar(int litros){ // Metodo de uso Retirar o sacar agua del Tinaco
            if (nivel_Agua >= litros){
            nivel_Agua -= litros;
            System.out.println("Se retiran: " + litros + " y quedan L. : " + nivel_Agua);
        }else {
            System.out.println("No hay suficiente agua para retirar, quedan " +nivel_Agua + "L."); // Llamada a la cadena de texto String de la clase que nos permitira ver en pantalla el mensaje colocado entre ("")
        }
    }
}

static class Tinaco_Termico extends Main.Tinaco{ // Herencia "extends" apartir de la Clase Tinaco a una nueva Subclase
        int temperatura_Agua; // atributo propio de la nueva Subclase

        public Tinaco_Termico(String marca, int cap_Maxima){
            super(marca, cap_Maxima); // "super" llama al constructor de la Clase padre para inicializar atributos
            this.temperatura_Agua = 29;
        }

    public void conservar (int litros, int temperatura_Entrada){
        super.rellenar(litros); // Reutiliza y llama el metodo rellenar heredado por la Clase "Tinaco"
        this.temperatura_Agua = temperatura_Entrada; // Actualizara el atriburo de la Subclase con el valor recibido de la temperatura

        System.out.println("El Tinaco Térmico conserva el agua a: " + this.temperatura_Agua + "°C."); // Muestra la temperatura del agua
    }

}
    public static void main(String[] ags){ // Punto de entrada para ejecutar la clase Main y comience a ejecutar el programa

        Tinaco miTinaco= new Tinaco("Rotoplax",1500); // Nombre de la marca y capacidad del objeto
        //Simulación  de rellenado y retiro de agua con y su conservación de la temperatura
        miTinaco.rellenar(300);
        miTinaco.rellenar(200);
        miTinaco.retirar(350);
        System.out.println(); // Salto de linea, deja un espacio en blanco en pantalla para separar en este caso el texto del primer Tinaco y el segundo Tinaco térmico de la Herencia nueva Subclase

        Tinaco_Termico miTinaco_Termico = new Tinaco_Termico("Rotoplax Plus Forte Max", 1100);
        miTinaco_Termico.conservar(600, 29);
    }
}