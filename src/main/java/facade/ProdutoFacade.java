package facade;

import entidades.Produto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class ProdutoFacade extends AbstractFacade<Produto> implements Serializable {

    @Inject
    private EntityManager em;

    public ProdutoFacade() {
        super(Produto.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public List<Produto> produtoAutoComplete(String codigo) {
        Query q = em.createQuery("FROM Produto AS p WHERE LOWER(p.codigo) LIKE('%" + codigo.toLowerCase() + "%') ORDER BY p.codigo");
        return q.getResultList();
    }
    
    @Override
    public List<Produto> listar() {
        return getEm().createQuery("FROM "+Produto.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
