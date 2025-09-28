<%-- 
    Document   : index
    Created on : 28 sept 2025, 12:02:42
    Author     : josue
--%>
<%@ page import="modelo.Sangre" %>
<%@ page import="modelo.Estudiante" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estudiantes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <body>
        <h1>Formulario Estudiantes</h1>
        <button type="button" name="btn_nuevo" id="btn_nuevo" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_estudiante" onclick="limpiar()">Nuevo</button>
        
    <div class="container">
         
        <div class="modal fade" id="modal_estudiante" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
             <form action="sr_estudiantes" method="post" class="form-group">
               <label for="lbl_id" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0"  readonly> 
                <label for="lbl_carne" ><b>Carne</b></label>
                <input type="text" name="txt_carne" id="txt_carne" class="form-control" placeholder="Ejemplo: E001" required>
                <label for="lbl_nombres" ><b>Nombres</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Juan" required>
                <label for="lbl_apellidos" ><b>Apellidos</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Pérez" required>
                <label for="lbl_direccion" ><b>Direccion</b></label>
                <input type="text"  name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: Ciudad de Guatemala" required>
                <label for="lbl_telefono" ><b>Telefono</b></label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 5555555" required>
                <label for="lbl_correo" ><b>Correo Electrónico</b></label>
                <input type="email" name="txt_correo" id="txt_correo" class="form-control" placeholder="Ejemplo: juan.perez@email.com" required>
                <label for="lbl_fn" ><b>Nacimiento</b></label>
                <input type="date"  name="txt_fn" id="txt_fn" class="form-control" required>
                <label for="lbl_sangre" ><b>Tipo de Sangre</b></label>
                <label for="lbl_sangre" ><b>Tipo de Sangre</b></label>
                <select name="drop_tipo_sangre" id="drop_tipo_sangre" class="form-control">
              <%
                    Sangre sangre = new Sangre();
                    HashMap<String,String> drop = sangre.drop_sangre();
                for (String i : drop.keySet()){
                out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
             }
                %>
</select>
                
                <br>
                <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                <button type="button" class="btn btn-warning btn-lg" data-dismiss="modal">Cerrar</button>
            </form>
        </div>
      </div>
      
    </div>
  </div>
        
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Carne</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <th>Direccion</th>
        <th>Telefono</th>
        <th>Correo</th>
        <th>Nacimiento</th>
        <th>Tipo Sangre</th>
      </tr>
    </thead>
  <tbody id="tbl_estudiantes">
    <%
   Estudiante estudiante = new Estudiante();
DefaultTableModel tabla = estudiante.leer();
for (int t = 0; t < tabla.getRowCount(); t++) {
    out.println("<tr data-id='" + tabla.getValueAt(t,0) + "' data_id_s='" + tabla.getValueAt(t,9) + "'>");
    out.println("<td>" + tabla.getValueAt(t,1) + "</td>"); // carne
    out.println("<td>" + tabla.getValueAt(t,2) + "</td>"); // nombres
    out.println("<td>" + tabla.getValueAt(t,3) + "</td>"); // apellidos
    out.println("<td>" + tabla.getValueAt(t,4) + "</td>"); // direccion
    out.println("<td>" + tabla.getValueAt(t,5) + "</td>"); // telefono
    out.println("<td>" + tabla.getValueAt(t,6) + "</td>"); // correo
    out.println("<td>" + tabla.getValueAt(t,7) + "</td>"); // nacimiento
    out.println("<td>" + tabla.getValueAt(t,8) + "</td>"); // sangre
    out.println("</tr>");
}
    %>
</tbody>
  </table>
  </div>
      

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function limpiar(){
        $("#txt_id").val(0);
       $("#txt_carne").val('');
       $("#txt_nombres").val('');
       $("#txt_apellidos").val('');
       $("#txt_direccion").val('');
       $("#txt_telefono").val('');
       $("#txt_correo").val('');
       $("#txt_fn").val('');
       $("#drop_tipo_sangre").val(1);
    }
   
    $('#tbl_estudiantes').on('click','tr td',function(evt){
       var target,id,id_s,carne,nombres,apellidos,direccion,telefono,correo,nacimiento; 
       target = $(event.target);
       id = target.parent().data('id');
       id_s = target.parent().data('id_s'); 
       carne = target.parent("tr").find("td").eq(0).html();
       nombres= target.parent("tr").find("td").eq(1).html();
       apellidos = target.parent("tr").find("td").eq(2).html();
       direccion = target.parent("tr").find("td").eq(3).html();
       telefono = target.parent("tr").find("td").eq(4).html();
       correo = target.parent("tr").find("td").eq(5).html();
       nacimiento = target.parent("tr").find("td").eq(6).html();
       $("#txt_id").val(id);
       $("#txt_carne").val(carne);
       $("#txt_nombres").val(nombres);
       $("#txt_apellidos").val(apellidos);
       $("#txt_direccion").val(direccion);
       $("#txt_telefono").val(telefono);
       $("#txt_correo").val(correo);
       $("#txt_fn").val(nacimiento);
       $("#drop_tipo_sangre").val(id_s);
       $("#modal_estudiante").modal('show');
    });
    
</script>
    </body>
</html>
