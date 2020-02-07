package localhost.erickdomingues.simplecrm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.ClienteDAO;
import localhost.erickdomingues.simplecrm.model.Cliente;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter<Object> {

	@Inject
	private ClienteDAO clienteDAO;
	
	private Cliente cliente = new Cliente();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.cliente = clienteDAO.buscarPeloCodigo(Long.parseLong(value));
		}
		
		return this.cliente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Cliente) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return null;
	}

}