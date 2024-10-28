package pruebas;

import ods12.ODS12;

public class Insertar {
    
    public static void main(String[] args) {
        ODS12 ods12 = new ODS12();
        
        String name = "gabriel";
        String lastname = "gutierrez";
        String number = "987645321";
        String email = "gabriel@vallegrande.edu.pe";
        String request = "Quisiera más información";
        String status = "A";
        
        boolean isInserted = ods12.insertarConsulta(name, lastname, number, email, request,status);
        
        if (isInserted) {
            System.out.println("Consulta insertada exitosamente.");
        } else {
            System.out.println("Error al insertar la consulta.");
        }
    }
}