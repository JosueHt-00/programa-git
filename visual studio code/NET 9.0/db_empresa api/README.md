# ApiEmpresa (ASP.NET Core Web API)
Proyecto generado para administrar la tabla `productos` en MySQL (db_empresa).

## Características
- .NET 8 (C#)
- Entity Framework Core (Pomelo MySQL provider)
- CRUD para `Producto`
- Swagger (OpenAPI)
- Pensado para Visual Studio Code

## Conexión a la base de datos
Revisa `appsettings.json`. Conexión actual:
```
server=localhost;database=db_empresa;user=root;password=User200@
```

## Cómo ejecutar
1. Asegúrate de tener .NET 8 SDK instalado.
2. Abre el proyecto en VS Code.
3. Restaurar paquetes:
   ```
   dotnet restore
   ```
4. Crear la base de datos `db_empresa` en MySQL si no existe.
5. Ejecutar migraciones (opcional):
   ```
   dotnet tool install --global dotnet-ef --version 8.0.0
   dotnet ef migrations add InitialCreate
   dotnet ef database update
   ```
   (Si prefieres crear manualmente la tabla, el modelo está en `Models/Producto.cs`)
6. Ejecutar:
   ```
   dotnet run
   ```
7. Abrir Swagger en `https://localhost:5001/swagger` (o el puerto que muestre la consola).

## Estructura principal
- Controllers/ProductosController.cs
- Models/Producto.cs
- Data/DbEmpresaContext.cs
