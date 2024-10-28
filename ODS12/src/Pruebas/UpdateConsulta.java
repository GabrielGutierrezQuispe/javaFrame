package pruebas;

import ods12.ODS12;

public class UpdateConsulta {

    public static void main(String[] args) {
        ODS12 ods12 = new ODS12();
        
        int id = 6;
        String name = "Marcelo";
        String lastname = "Perez Gutierrez";
        String number = "789456123";
        String email = "mmarcelo@gmail.com";
        String request = "Puedo participar del proyecto?";
        String status = "A";
        
        boolean isUpdated = ods12.actualizarConsulta(id, name, lastname, number, email, request, status);
        
        if (isUpdated) {
            System.out.println("Consulta actualizada exitosamente.");
        } else {
            System.out.println("No se encontró una consulta con el ID proporcionado.");
        }
    }
}