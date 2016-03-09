<%@page import="com.examen.entidad.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<h1>PORTLET A</h1>



<% 
String nombre ="";
String direccion="";
String telefono="";


Persona persona = (Persona)renderRequest.getAttribute("persona");
 


if(persona !=null){
	nombre=persona.getNombre();
	direccion=persona.getDireccion();
	telefono=persona.getTelefono();
}
%>

<portlet:actionURL var="urlPortletB" name="enviarEventoB" />
<portlet:actionURL var="urlPortletC" name="enviarEventoC" />


<form action="<%=urlPortletB%>" method="post">
	<input type="submit"  value="Portlet B" />
	<input type="submit" formaction="<%=urlPortletC%>" value="Portlet C" /><br />
	

Nombre: <input type="text" name="nombre" value="<%=nombre%>" /><br />
Direccion: <input type="text" name="direccion" value="<%=direccion%>" /><br />
Telefono: <input type="text" name="telefono" value="<%=telefono%>" /><br />


</form>	