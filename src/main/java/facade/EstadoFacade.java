package facade;

import entidades.Estado;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class EstadoFacade extends AbstractFacade<Estado> implements Serializable {

    @Inject
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Estado> estadoAutoComplete(String nome) {
        Query q = em.createQuery("FROM Estado AS c WHERE LOWER(c.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY c.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Estado> listar() {
        return getEm().createQuery("FROM "+Estado.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
