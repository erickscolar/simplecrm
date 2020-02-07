package localhost.erickdomingues.simplecrm.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import localhost.erickdomingues.simplecrm.model.Cliente;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = -7763906430266717723L;
	
	@Inject
	private EntityManager em;
	
	public Cliente buscarPeloCodigo(Long codigo) {
		return em.find(Cliente.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		return em.createQuery("from cliente").getResultList();
	}
	
	@Transactional
	public void salvar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Transactional
	public void excluir(Cliente cliente) throws ReturnException {
		try {
			Cliente clienteTemp = em.find(Cliente.class, cliente.getCodigo());
			em.getTransaction().begin();
			em.remove(clienteTemp);
			em.flush();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Cliente buscarProdutosCliente(Long codigo) {
		return (Cliente) em.createQuery("SELECT nome FROM produto p"
								+" WHERE codigo"
									+" IN (select p.codigo from cliente_categoria cc "
											+" INNER JOIN produto p"
											+" ON cc.codigo_categoria = p.codigo_categoria"
											+" INNER JOIN cliente cl"
											+" WHERE cc.codigo_cliente = ?1)").setParameter(1, codigo).getSingleResult();
	}
}
