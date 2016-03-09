package com.examen.portlet;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import com.examen.entidad.Persona;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class PortletA
 */
public class PortletA extends GenericPortlet {

    public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
    
    @ProcessAction(name="enviarEventoB")
    public void enviarEventoB(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    	
    	//La logica propia de este process action y ademas, en este caso
    	//enviamos el evento
    	
    	String nombre = actionRequest.getParameter("nombre");
    	String direccion = actionRequest.getParameter("direccion");
    	String telefono = actionRequest.getParameter("telefono");
    	
    	Persona persona = new Persona (nombre, direccion, telefono);
    	
    	QName qname = new QName("http://examen.portlet", "mensajeB", "x");
		actionResponse.setEvent(qname, persona);
		
		
		
    }
    
    @ProcessAction(name="enviarEventoC")
 public void enviarEventoC(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException {
    	
    	//La logica propia de este process action y ademas, en este caso
    	//enviamos el evento
    	
    	String nombre = actionRequest.getParameter("nombre");
    	String direccion = actionRequest.getParameter("direccion");
    	String telefono = actionRequest.getParameter("telefono");
    	
    	Persona persona = new Persona (nombre, direccion, telefono);
    	
    	QName qname = new QName("http://examen.portlet", "mensajeC", "x");
		actionResponse.setEvent(qname, persona);
		

    }
    
    @ProcessEvent(qname="{http://examen.portlet}mensajeB")
    public void procesarMiEventoB(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	Event evento = arg0.getEvent();
    	
    	Serializable persona = evento.getValue();
    	
    	//Esto es asi, porque aunque el valor del Evento, en este caso es un String de forma generica es un Serializable, y la unica forma de psar un serializable
    	//es con atributos
    	arg0.setAttribute("persona", persona);
    }
 
    
    @ProcessEvent(qname="{http://examen.portlet}mensajeC")
    public void procesarMiEventoC(EventRequest arg0, EventResponse arg1) throws PortletException, IOException {
    	Event evento = arg0.getEvent();
    	
    	Serializable persona = evento.getValue();
    	
    	//Esto es asi, porque aunque el valor del Evento, en este caso es un String de forma generica es un Serializable, y la unica forma de psar un serializable
    	//es con atributos
    	arg0.setAttribute("persona", persona);
    }
    
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletA.class);

}
