package localhost.erickdomingues.simplecrm.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import localhost.erickdomingues.simplecrm.model.Produto;
import localhost.erickdomingues.simplecrm.service.ReturnException;
import localhost.erickdomingues.simplecrm.util.jsf.FacesUtil;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = -7763906430266717723L;
	
	@Inject
	private EntityManager em;
	
	public Produto buscarPeloCodigo(Long codigo) {
		return em.find(Produto.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarTodos() {
		return em.createQuery("from produto").getResultList();
	}
	
	@Transactional
	public void salvar(Produto produto) {
		em.getTransaction().begin();
		em.merge(produto);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Transactional
	public void excluir(Produto produto) throws ReturnException {
		try {
			Produto produtoTemp = em.find(Produto.class, produto.getCodigo());
			em.getTransaction().begin();
			em.remove(produtoTemp);
			em.flush();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
}
