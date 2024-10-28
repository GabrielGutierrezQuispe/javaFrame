package pruebas;

import ods12.ODS12;

public class deleteConsulta {

    public static void main(String[] args) {
        ODS12 ods12 = new ODS12();
        
        int id = 3; // ID de la consulta a eliminar
        boolean isDeleted = ods12.eliminarConsulta(id);
        
        if (isDeleted) {
            System.out.println("Consulta eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una consulta con el ID proporcionado.");
        }
    }
}