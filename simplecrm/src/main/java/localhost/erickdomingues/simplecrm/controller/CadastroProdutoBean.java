package localhost.erickdomingues.simplecrm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import localhost.erickdomingues.simplecrm.dao.CategoriaDAO;
import localhost.erickdomingues.simplecrm.model.Categoria;
import localhost.erickdomingues.simplecrm.model.Produto;
import localhost.erickdomingues.simplecrm.service.CadastroProdutoService;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	@Inject
	private CategoriaDAO categoriaDAO;

	private Produto produto;
	private Categoria categoria;
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroProdutoService.salvar(produto);
			FacesUtil.addSuccessMessage("Produto salvo com sucesso!");
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.limpar();
	}
	
	public void limpar() {
		produto = new Produto();
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
		categorias = categoriaDAO.buscarTodos();
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
}
