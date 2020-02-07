package localhost.erickdomingues.simplecrm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import localhost.erickdomingues.simplecrm.dao.ClienteDAO;
import localhost.erickdomingues.simplecrm.model.Cliente;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;
	
	private Cliente cliente;

	private List<Cliente> clientes = new ArrayList<>();
	
	@PostConstruct
	public void init()  {
		clientes = clienteDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			clienteDAO.excluir(cliente);
			clientes.remove(cliente);
			FacesUtil.addSuccessMessage("O cliente " + cliente.getNome() + " foi excluído permanentemente!");
		} catch (ReturnException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void buscarProdutosCliente() {
		cliente = clienteDAO.buscarProdutosCliente(cliente.getCodigo());
	}
	
	//GETTERS & SETTERS
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	//GETTERS & SETTERS
}
