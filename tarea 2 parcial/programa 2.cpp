#include <iostream>
#include <string>
using namespace std;

struct Estudiante {
    int id;
    string nombres;
    string apellidos;
    float nota1, nota2, nota3, nota4;
    float promedio;
};

// Función para calcular el promedio
void calcularPromedio(Estudiante* est) {
    est->promedio = (est->nota1 + est->nota2 + est->nota3 + est->nota4) / 4.0;
}

void mostrarEstado(Estudiante* est) {
    cout << "\nID: " << est->id << endl;
    cout << "Nombre completo: " << est->nombres << " " << est->apellidos << endl;
    cout << "Promedio: " << est->promedio << endl;
    if (est->promedio >= 60) {
        cout << "Estado: Aprobado" << endl;
    } else {
        cout << "Estado: Reprobado" << endl;
    }
}

int main() {
    int cantidad;
    cout << "Ingrese la cantidad de estudiantes: ";
    cin >> cantidad;

    // Crear arreglo dinámico de estructuras
    Estudiante* estudiantes = new Estudiante[cantidad];

    for (int i = 0; i < cantidad; i++) {
        cout << "\n--- Ingresar datos del estudiante #" << i + 1 << " ---\n";
        cout << "ID: ";
        cin >> estudiantes[i].id;
        cin.ignore(); // limpiar buffer
        cout << "Nombres: ";
        getline(cin, estudiantes[i].nombres);
        cout << "Apellidos: ";
        getline(cin, estudiantes[i].apellidos);
        cout << "Nota 1: ";
        cin >> estudiantes[i].nota1;
        cout << "Nota 2: ";
        cin >> estudiantes[i].nota2;
        cout << "Nota 3: ";
        cin >> estudiantes[i].nota3;
        cout << "Nota 4: ";
        cin >> estudiantes[i].nota4;

        // Calcular promedio usando puntero
        calcularPromedio(&estudiantes[i]);
    }

    cout << "\n===== Resultados =====\n";
    for (int i = 0; i < cantidad; i++) {
        mostrarEstado(&estudiantes[i]);
    }

    delete[] estudiantes;

    return 0;
}

