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
import localhost.erickdomingues.simplecrm.model.Cliente;
import localhost.erickdomingues.simplecrm.service.CadastroClienteService;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Inject
	private CategoriaDAO categoriaDAO;

	private Cliente cliente;
	@SuppressWarnings("unused")
	private Categoria categoria;
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroClienteService.salvar(cliente);
			FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.limpar();
	}

	public void limpar() {
		cliente = new Cliente();
		categoria = new Categoria();
		cliente.setCategorias(new ArrayList<Categoria>());
		categorias = new ArrayList<Categoria>();
		categorias = categoriaDAO.buscarTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
