#include "sucursal.h"
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

void Sucursal::crear(MYSQL* con) {
    string nombre, ubicacion, fecha;
    int trabajadores, activo;

    cout << "Nombre: ";
    getline(cin, nombre);
    cout << "Ubicación: ";
    getline(cin, ubicacion);
    cout << "Cantidad de trabajadores: ";
    cin >> trabajadores;
    cin.ignore();
    cout << "Fecha de inauguración (YYYY-MM-DD): ";
    getline(cin, fecha);
    cout << "¿Activo? (1 o 0): ";
    cin >> activo;
    cin.ignore();

    stringstream ss;
    ss << "INSERT INTO sucursal(nombre, ubicacion, cantidad_trabajadores, fecha_inaguracion, activo) "
        << "VALUES('" << nombre << "', '" << ubicacion << "', " << trabajadores << ", '" << fecha << "', " << activo << ")";

    if (mysql_query(con, ss.str().c_str()) == 0) {
        cout << "✅ Sucursal creada correctamente.\n";
    }
    else {
        cerr << "❌ Error al crear sucursal: " << mysql_error(con) << endl;
    }
}

void Sucursal::listar(MYSQL* con) {
    if (mysql_query(con, "SELECT * FROM sucursal") != 0) {
        cerr << "❌ Error al listar sucursales: " << mysql_error(con) << endl;
        return;
    }

    MYSQL_RES* res = mysql_store_result(con);
    if (!res) {
        cerr << "❌ Error al obtener resultados: " << mysql_error(con) << endl;
        return;
    }

    MYSQL_ROW row;
    cout << "\n--- Lista de Sucursales ---\n";
    while ((row = mysql_fetch_row(res)) != nullptr) {
        cout << "ID: " << row[0]
            << " | Nombre: " << row[1]
            << " | Ubicación: " << row[2]
            << " | Trabajadores: " << row[3]
            << " | Fecha: " << row[4]
            << " | Activo: " << (row[5][0] == '1' ? "Sí" : "No") << endl;
    }

    mysql_free_result(res);
}

void Sucursal::actualizar(MYSQL* con) {
    int id;
    string nuevoNombre;

    cout << "ID de sucursal a actualizar: ";
    cin >> id;
    cin.ignore();
    cout << "Nuevo nombre: ";
    getline(cin, nuevoNombre);

    stringstream ss;
    ss << "UPDATE sucursal SET nombre = '" << nuevoNombre << "' WHERE Id_sucursal = " << id;

    if (mysql_query(con, ss.str().c_str()) == 0) {
        cout << "✅ Sucursal actualizada correctamente.\n";
    }
    else {
        cerr << "❌ Error al actualizar sucursal: " << mysql_error(con) << endl;
    }
}

void Sucursal::eliminar(MYSQL* con) {
    int id;
    cout << "ID de sucursal a eliminar (lógicamente): ";
    cin >> id;
    cin.ignore();

    stringstream ss;
    ss << "UPDATE sucursal SET activo = 0 WHERE Id_sucursal = " << id;

    if (mysql_query(con, ss.str().c_str()) == 0) {
        cout << "✅ Sucursal eliminada lógicamente.\n";
    }
    else {
        cerr << "❌ Error al eliminar sucursal: " << mysql_error(con) << endl;
    }
}
