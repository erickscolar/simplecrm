package localhost.erickdomingues.simplecrm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import localhost.erickdomingues.simplecrm.dao.ProdutoDAO;
import localhost.erickdomingues.simplecrm.model.Produto;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;
	
	private Produto produto;

	private List<Produto> produtos = new ArrayList<>();
	
	////GETTERS & SETTERS
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	////GETTERS & SETTERS
	
	@PostConstruct
	public void init()  {
		this.produtos = this.produtoDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			this.produtoDAO.excluir(produto);
			this.produtos.remove(produto);
			FacesUtil.addSuccessMessage("O produto " + produto.getNome() + " foi excluído permanentemente!");
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
}
