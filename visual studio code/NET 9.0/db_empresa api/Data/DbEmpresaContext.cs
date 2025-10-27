using Microsoft.EntityFrameworkCore;
using ApiEmpresa.Models;

namespace ApiEmpresa.Data
{
    public class DbEmpresaContext : DbContext
    {
        public DbEmpresaContext(DbContextOptions<DbEmpresaContext> options) : base(options)
        {
        }

        public DbSet<Producto> Productos { get; set; } = null!;
    }
}
