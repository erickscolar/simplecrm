package localhost.erickdomingues.simplecrm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.CategoriaDAO;
import localhost.erickdomingues.simplecrm.model.Categoria;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter<Object> {

	@Inject
	private CategoriaDAO categoriaDAO;
	
	private Categoria categoria = new Categoria();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.categoria = categoriaDAO.buscarPeloCodigo(Long.parseLong(value));
		}
		
		return this.categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Categoria) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return null;
	}

}