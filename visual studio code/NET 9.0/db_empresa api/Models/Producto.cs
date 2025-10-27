using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiEmpresa.Models
{
    [Table("productos")]
    public class Producto
    {
        [Key]
        [Column("id_producto")]
        public int Id_Producto { get; set; }

        [Required]
        [Column("codigo")]
        [MaxLength(10)]
        public string Codigo { get; set; } = string.Empty;

        [Required]
        [Column("producto")]
        [MaxLength(50)]
        public string Nombre { get; set; } = string.Empty;

        [Column("descripcion")]
        [MaxLength(300)]
        public string? Descripcion { get; set; }

        [Column("presentacion")]
        [MaxLength(50)]
        public string? Presentacion { get; set; }

        [Column("precio_costo", TypeName = "decimal(8,2)")]
        public decimal Precio_Costo { get; set; }

        [Column("fecha_vencimiento", TypeName = "date")]
        public DateTime? Fecha_Vencimiento { get; set; }
    }
}
