package localhost.erickdomingues.simplecrm.service;

import java.io.Serializable;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.ProdutoDAO;
import localhost.erickdomingues.simplecrm.model.Produto;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = -5853509778678635857L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Transactional
	public void salvar(Produto produto) throws ReturnException {
		if(
			(StringUtils.isEmpty(produto.getNome()) || StringUtils.isEmpty(produto.getDescricao())
			|| produto.getCategoria() == null)
		){
			throw new ReturnException("Todos os campos são obrigatórios");
		}
		this.produtoDAO.salvar(produto);
	}
}
