<%@page import="com.examen.entidad.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<h1>PORTLET C</h1>



<% 
String nombre ="";
String direccion="";
String telefono="";

Persona persona = (Persona)renderRequest.getAttribute ("persona");
 


if(persona !=null){
	nombre=persona.getNombre();
	direccion=persona.getDireccion();
	telefono=persona.getTelefono();
}
%>

<portlet:actionURL var="urlPortletC" />

<form action=<%=urlPortletC %> method="post">
	

Nombre: <input type="text" name="nombre" value="<%=nombre %>"/><br/>
Direccion: <input type="text" name="direccion" value="<%=direccion %>"/><br/>
Telefono: <input type="text" name="telefono" value="<%=telefono %>"/><br/>


</form>	