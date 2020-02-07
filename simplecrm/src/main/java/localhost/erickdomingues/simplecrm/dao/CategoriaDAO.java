package localhost.erickdomingues.simplecrm.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import localhost.erickdomingues.simplecrm.model.Categoria;
import localhost.erickdomingues.simplecrm.service.ReturnException;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = -7716865228706593597L;
	
	@Inject
	private EntityManager em;
		
	public Categoria buscarPeloCodigo(Long codigo) {
		return em.find(Categoria.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> buscarTodos() {
		return em.createQuery("from categoria").getResultList();
	}
	
	@Transactional
	public void salvar(Categoria categoria) {
		em.getTransaction().begin();
		em.merge(categoria);
		em.flush();
		em.getTransaction().commit();
	}
	
	@Transactional
	public void excluir(Categoria categoria) throws ReturnException {
		Categoria categoriaTemp = em.find(Categoria.class, categoria.getCodigo());
		em.getTransaction().begin();
		em.remove(categoriaTemp);
		em.flush();
		em.getTransaction().commit();
	}
}
