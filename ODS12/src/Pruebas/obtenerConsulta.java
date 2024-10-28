package pruebas;

import ods12.ODS12;
import java.util.List;

public class obtenerConsulta {
    
    public static void main(String[] args) {
        ODS12 ods12 = new ODS12();
        List<String> consultas = ods12.obtenerConsulta();
        
        if (consultas.isEmpty()) {
            System.out.println("No se encontraron consultas.");
        } else {
            System.out.println("Consultas encontradas:");
            for (String consulta : consultas) {
                System.out.println(consulta);
            }
        }
    }
}