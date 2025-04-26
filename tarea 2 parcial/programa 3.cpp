#include <iostream>
#include <fstream>
#include <string>
#include <cstring>  
using namespace std;

struct Estudiante {
    int id;
    char nombres[50];
    char apellidos[50];
    float nota1, nota2, nota3, nota4;
    float promedio;
    char resultado[10]; 
};

void calcular(Estudiante &est) {
    est.promedio = (est.nota1 + est.nota2 + est.nota3 + est.nota4) / 4;
    if (est.promedio >= 60)
        strcpy(est.resultado, "Aprobado");
    else
        strcpy(est.resultado, "Reprobado");
}

void crear() {
    int cantidad;
    cout << "\nIngrese cantidad de estudiantes: ";
    cin >> cantidad;
    Estudiante est;

    ofstream archivo("notas.dat", ios::binary | ios::app);

    for (int i = 0; i < cantidad; i++) {
        cout << "\n--- Estudiante #" << i + 1 << " ---\n";
        cout << "ID: ";
        cin >> est.id;
        cin.ignore();
        cout << "Nombres: ";
        cin.getline(est.nombres, 50);
        cout << "Apellidos: ";
        cin.getline(est.apellidos, 50);
        cout << "Nota 1: "; cin >> est.nota1;
        cout << "Nota 2: "; cin >> est.nota2;
        cout << "Nota 3: "; cin >> est.nota3;
        cout << "Nota 4: "; cin >> est.nota4;

        calcular(est);

        archivo.write(reinterpret_cast<char*>(&est), sizeof(Estudiante));
    }
    archivo.close();
    cout << "\nDatos guardados exitosamente en notas.dat\n";
}

void leer() {
    Estudiante est;
    ifstream archivo("notas.dat", ios::binary);
    cout << "\n=== Lista de estudiantes ===\n";

    while (archivo.read(reinterpret_cast<char*>(&est), sizeof(Estudiante))) {
        cout << "\nID: " << est.id
             << "\nNombre: " << est.nombres << " " << est.apellidos
             << "\nNotas: " << est.nota1 << ", " << est.nota2 << ", " << est.nota3 << ", " << est.nota4
             << "\nPromedio: " << est.promedio
             << "\nResultado: " << est.resultado << "\n";
    }
    archivo.close();
}

void actualizar() {
    int idBuscar;
    cout << "\nIngrese el ID del estudiante a actualizar: ";
    cin >> idBuscar;

    fstream archivo("notas.dat", ios::in | ios::out | ios::binary);
    Estudiante est;
    bool encontrado = false;

    while (archivo.read(reinterpret_cast<char*>(&est), sizeof(Estudiante))) {
        if (est.id == idBuscar) {
            cout << "\n--- Datos actuales ---\n";
            cout << "Nombre: " << est.nombres << " " << est.apellidos << "\n";

            cout << "\n--- Ingrese nuevos datos ---\n";
            cin.ignore();
            cout << "Nombres: "; cin.getline(est.nombres, 50);
            cout << "Apellidos: "; cin.getline(est.apellidos, 50);
            cout << "Nota 1: "; cin >> est.nota1;
            cout << "Nota 2: "; cin >> est.nota2;
            cout << "Nota 3: "; cin >> est.nota3;
            cout << "Nota 4: "; cin >> est.nota4;

            calcular(est);

            int pos = -1 * static_cast<int>(sizeof(Estudiante));
            archivo.seekp(pos, ios::cur); // retroceder
            archivo.write(reinterpret_cast<char*>(&est), sizeof(Estudiante));
            encontrado = true;
            cout << "\nEstudiante actualizado.\n";
            break;
        }
    }

    if (!encontrado)
        cout << "\nEstudiante no encontrado.\n";

    archivo.close();
}

void eliminar() {
    int idEliminar;
    cout << "\nIngrese el ID del estudiante a eliminar: ";
    cin >> idEliminar;

    ifstream archivoIn("notas.dat", ios::binary);
    ofstream archivoTemp("temp.dat", ios::binary);
    Estudiante est;
    bool eliminado = false;

    while (archivoIn.read(reinterpret_cast<char*>(&est), sizeof(Estudiante))) {
        if (est.id != idEliminar) {
            archivoTemp.write(reinterpret_cast<char*>(&est), sizeof(Estudiante));
        } else {
            eliminado = true;
        }
    }

    archivoIn.close();
    archivoTemp.close();

    remove("notas.dat");
    rename("temp.dat", "notas.dat");

    if (eliminado)
        cout << "\nEstudiante eliminado exitosamente.\n";
    else
        cout << "\nEstudiante no encontrado.\n";
}

int main() {
    int opcion;

    do {
        cout << "\n======= MENU =======\n";
        cout << "1. Crear registros\n";
        cout << "2. Leer registros\n";
        cout << "3. Actualizar estudiante\n";
        cout << "4. Eliminar estudiante\n";
        cout << "5. Salir\n";
        cout << "Seleccione una opción: ";
        cin >> opcion;

        switch (opcion) {
            case 1: crear(); break;
            case 2: leer(); break;
            case 3: actualizar(); break;
            case 4: eliminar(); break;
            case 5: cout << "\nSaliendo...\n"; break;
            default: cout << "Opción inválida.\n"; break;
        }
    } while (opcion != 5);

    return 0;
}

