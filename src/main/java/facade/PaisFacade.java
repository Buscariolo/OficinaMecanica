package facade;

import entidades.Pais;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class PaisFacade extends AbstractFacade<Pais> implements Serializable {

    @Inject
    private EntityManager em;

    public PaisFacade() {
        super(Pais.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Pais> paisAutoComplete(String nome) {
        Query q = em.createQuery("FROM Pais AS c WHERE LOWER(c.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY c.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Pais> listar() {
        return getEm().createQuery("FROM "+Pais.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
