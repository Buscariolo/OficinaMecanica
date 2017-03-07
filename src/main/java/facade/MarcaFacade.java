package facade;

import entidades.Marca;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class MarcaFacade extends AbstractFacade<Marca> implements Serializable {

    @Inject
    private EntityManager em;

    public MarcaFacade() {
        super(Marca.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Marca> marcaAutoComplete(String nome) {
        Query q = em.createQuery("FROM Marca AS m WHERE LOWER(m.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY m.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Marca> listar() {
        return getEm().createQuery("FROM "+Marca.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
