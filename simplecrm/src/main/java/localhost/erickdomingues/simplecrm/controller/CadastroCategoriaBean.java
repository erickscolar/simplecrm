package localhost.erickdomingues.simplecrm.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import localhost.erickdomingues.simplecrm.model.Categoria;
import localhost.erickdomingues.simplecrm.service.CadastroCategoriaService;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = -4520813976552972100L;
	
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;
	
	private Categoria categoria;
	
	//GETTERS & SETTERS
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	//GETTERS & SETTERS
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroCategoriaService.salvar(categoria);
			FacesUtil.addSuccessMessage("Categoria salva com sucesso!");
			this.limpar();
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.categoria = new Categoria();
	}
}
