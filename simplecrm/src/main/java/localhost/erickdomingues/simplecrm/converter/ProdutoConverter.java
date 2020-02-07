package localhost.erickdomingues.simplecrm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.ProdutoDAO;
import localhost.erickdomingues.simplecrm.model.Produto;

@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter<Object> {

	@Inject
	private ProdutoDAO produtoDAO;
	
	private Produto produto;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		this.produto = new Produto();
		if (StringUtils.isNotEmpty(value)) {
			this.produto = produtoDAO.buscarPeloCodigo(Long.parseLong(value));
		}
		
		return this.produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Produto) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return null;
	}

}