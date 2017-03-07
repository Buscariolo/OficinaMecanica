package facade;

import entidades.Cidade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class CidadeFacade extends AbstractFacade<Cidade> implements Serializable {

    @Inject
    private EntityManager em;

    public CidadeFacade() {
        super(Cidade.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Cidade> cidadeAutoComplete(String nome) {
        Query q = em.createQuery("FROM Cidade AS c WHERE LOWER(c.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY c.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Cidade> listar() {
        return getEm().createQuery("FROM "+Cidade.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
