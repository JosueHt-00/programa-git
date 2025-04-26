#include <iostream>
#include <string>
using namespace std;

int main() {
    int cantidad;

    cout << "Ingrese la cantidad de estudiantes: ";
    cin >> cantidad;

    int id[cantidad];
    string nombres[cantidad];
    string apellidos[cantidad];
    float nota1[cantidad], nota2[cantidad], nota3[cantidad], nota4[cantidad];
    float promedio[cantidad];
    
    for (int i = 0; i < cantidad; i++) {
        cout << "\nEstudiante #" << i + 1 << endl;
        cout << "ID: ";
        cin >> id[i];
        cin.ignore(); 
        cout << "Nombres: ";
        getline(cin, nombres[i]);
        cout << "Apellidos: ";
        getline(cin, apellidos[i]);
        cout << "Nota 1: ";
        cin >> nota1[i];
        cout << "Nota 2: ";
        cin >> nota2[i];
        cout << "Nota 3: ";
        cin >> nota3[i];
        cout << "Nota 4: ";
        cin >> nota4[i];

        promedio[i] = (nota1[i] + nota2[i] + nota3[i] + nota4[i]) / 4;
    }

    cout << "\n--- Resultados ---\n";
    for (int i = 0; i < cantidad; i++) {
        cout << "\nEstudiante #" << i + 1 << endl;
        cout << "ID: " << id[i] << endl;
        cout << "Nombre completo: " << nombres[i] << " " << apellidos[i] << endl;
        cout << "Promedio: " << promedio[i] << endl;
        if (promedio[i] >= 60) {
            cout << "Estado: Aprobado\n";
        } else {
            cout << "Estado: Reprobado\n";
        }
    }

    return 0;
}

