package localhost.erickdomingues.simplecrm.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.CategoriaDAO;
import localhost.erickdomingues.simplecrm.model.Categoria;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = -4283530817844760834L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Transactional
	public void salvar(Categoria categoria) throws ReturnException {
		if (StringUtils.isEmpty(categoria.getNome()) || StringUtils.isEmpty(categoria.getDescricao())) {
			throw new ReturnException("Todos os campos são obrigatórios!");
		}
		this.categoriaDAO.salvar(categoria);
	}
}
