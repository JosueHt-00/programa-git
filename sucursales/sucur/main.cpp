#include <iostream>
#include "conexionBD.h"
#include "sucursal.h"

using namespace std;

int main() {
    conexionBD db;
    db.abrir_conexion();
    MYSQL* con = db.getConector();

    if (!con) {
        cout << "❌ No se pudo conectar a la base de datos.\n";
        return 1;
    }

    Sucursal sucursal;
    int opcion;

    do {
        cout << "\n--- MENU ---\n";
        cout << "1. Crear sucursal\n";
        cout << "2. Listar sucursales\n";
        cout << "3. Actualizar sucursal\n";
        cout << "4. Eliminar sucursal \n";
        cout << "5. Salir\n";
        cout << "opcion: ";
        cin >> opcion;
        cin.ignore();  // Muy importante para getline()

        switch (opcion) {
        case 1:
            sucursal.crear(con);
            break;
        case 2:
            sucursal.listar(con);
            break;
        case 3:
            sucursal.actualizar(con);
            break;
        case 4:
            sucursal.eliminar(con);
            break;
        case 5:
            cout << "👋 Saliendo del programa...\n";
            break;
        default:
            cout << "❌ Opción inválida.\n";
        }
    } while (opcion != 5);

    db.cerrar_conexion();
    return 0;
}
