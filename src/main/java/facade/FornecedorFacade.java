package facade;

import entidades.Fornecedor;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class FornecedorFacade extends AbstractFacade<Fornecedor> implements Serializable {

    @Inject
    private EntityManager em;

    public FornecedorFacade() {
        super(Fornecedor.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Fornecedor> fornecedorAutoComplete(String nome) {
        Query q = em.createQuery("FROM Fornecedor AS f WHERE LOWER(f.nome_razao) LIKE('%" + nome.toLowerCase() + "%') ORDER BY f.nome_razao");
        return q.getResultList();
    }
    
    @Override
    public List<Fornecedor> listar() {
        return getEm().createQuery("FROM "+Fornecedor.class.getSimpleName()+" ORDER BY nome_razao ASC").getResultList();
    }

}
