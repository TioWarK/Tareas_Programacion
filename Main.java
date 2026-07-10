public class Main {// Creación de clase Main

    static class Tinaco{ // Creación de clase Tinaco
    String marca; // Atributos del objeto Tinaco, Marca, Capacidad y Nivel de Agua
    int cap_Maxima;
    int nivel_Agua;

    public Tinaco(String marca, int cap_Maxima){ // Implementación de Clase
        this.marca = marca;
        this.cap_Maxima = cap_Maxima;
        this.nivel_Agua = 0;
    }

    public void rellenar(int litros){ // Metodo de uso Rellenar, uso del Tinaco, agrega agua
        if (nivel_Agua + litros <= cap_Maxima){ // Condicional de "si se cumple" rellenar
            nivel_Agua += litros;
            System.out.println("Se agregaron: " + litros + " Quedan L. " + this.nivel_Agua);
    }
        else // Condicional "en caso contrario o si no se comple"
        {
            nivel_Agua = cap_Maxima;
            System.out.println("El tinaco " + marca + "no puede retener más agua, se está desbordando. Tinaco en su capacidad máxima " + cap_Maxima + "L.");
        }
    }
        public void retirar(int litros){ // Metodo de uso Retirar, uso del Tinaco, retirar agua
            if (nivel_Agua >= litros){
            nivel_Agua -= litros;
            System.out.println("Se retiran: " + litros + " y quedan L. : " + nivel_Agua); //
        }else {
            System.out.println("No hay suficiente agua para retirar, quedan " +nivel_Agua + "L."); // Llamada al metodo de la clase que nos permitira ver en pantalla el mensaje colocado entre ("")
        }
    }
}

static class Tinaco_Termico extends Main.Tinaco{ // Herencia "extends" apartir de la clase Tinaco a una nueva sub Clase
        int temperatura_Agua; // atributo propio de la nueva sub Clase

        public Tinaco_Termico(String marca, int cap_Maxima){
            super(marca, cap_Maxima); // "super" llama al constructor de la clase padre para inicializar atributos
            this.temperatura_Agua = 29;
        }

    public void conservar (int litros, int temperatura_Entrada){
        super.rellenar(litros);
        this.temperatura_Agua = temperatura_Entrada;

        System.out.println("El Tinaco Térmico conserva el agua a: " + this.temperatura_Agua + "°C."); // Muestra la temperatura del agua
    }

}
    public static void main(String[] ags){ // Punto de entrada para ejecutar la clase Main y comienze a leerla o ejecutarla

        Tinaco miTinaco= new Tinaco("Rotoplax",1500); // Nombre de la marca y capacidad del objeto
        //Simulación  de rellenado y retiro de agua con y su conservación de la temperatura
        miTinaco.rellenar(300);
        miTinaco.rellenar(200);
        miTinaco.retirar(350);
        System.out.println(); // Salto de linea, deja un espacio en blanco en pantalla para separar en este caso el texto del primer Tinaco y el segundo Tinaco termico de la Herencia nueva sub Clase

        Tinaco_Termico miTinaco_Termico = new Tinaco_Termico("Rotoplax Plus Forte Max", 1100);
        miTinaco_Termico.conservar(600, 29);
    }
}