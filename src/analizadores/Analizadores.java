package analizadores;

public class Analizadores {

	public static void main(String[] args) {
		 generarCompilador();
    }
	
    private static void generarCompilador(){
        try {
            String ruta = "src/analizadores/";
            String opcFlex[] = {ruta + "Lexico.flex", "-d", ruta};
            JFlex.Main.generate(opcFlex);
            
            
            String opcCUP[] = {"-destdir", ruta, "-parser", "parser", ruta + "Sintactico.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

}
