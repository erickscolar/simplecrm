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
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO categoriaDAO;
	
	private Categoria categoria;

	private List<Categoria> categorias = new ArrayList<>();
	
	////GETTERS & SETTERS
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	////GETTERS & SETTERS
	
	@PostConstruct
	public void init()  {
		this.categorias = this.categoriaDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			this.categoriaDAO.excluir(categoria);
			this.categorias.remove(categoria);
			FacesUtil.addSuccessMessage("A categoria " + categoria.getNome() + " foi excluído permanentemente!");
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
}
