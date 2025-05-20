#pragma once
#include <mysql.h>

class Sucursal {
public:
    void crear(MYSQL* con);
    void listar(MYSQL* con);
    void actualizar(MYSQL* con);
    void eliminar(MYSQL* con); // Eliminación lógica
};
