package localhost.erickdomingues.simplecrm.service;

import java.io.Serializable;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import localhost.erickdomingues.simplecrm.dao.ClienteDAO;
import localhost.erickdomingues.simplecrm.model.Cliente;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = -5853509778678635857L;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@SuppressWarnings("deprecation")
	@Transactional
	public void salvar(Cliente cliente) throws ReturnException {
		if(
			(StringUtils.isEmpty(cliente.getNome()))
			|| (StringUtils.isEmpty(cliente.getCpf()))
			|| (StringUtils.isEmpty(cliente.getRg()))
			|| (StringUtils.isEmpty(cliente.getEndereco()))
			|| (StringUtils.isEmpty(cliente.getTelefone()))
			|| (cliente.getDataNascimento() == null || cliente.getDataNascimento().getYear() > 2020)
			|| cliente.getCategorias() == null
		){
			throw new ReturnException("Todos os campos são obrigatórios");
		}
		this.clienteDAO.salvar(cliente);
	}
}
