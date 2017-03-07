package facade;

import entidades.Cargo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class CargoFacade extends AbstractFacade<Cargo> implements Serializable {

    @Inject
    private EntityManager em;

    public CargoFacade() {
        super(Cargo.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    public List<Cargo> cargoAutoComplete(String nome) {
        Query q = em.createQuery("FROM Cargo AS c WHERE LOWER(c.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY c.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Cargo> listar() {
        return getEm().createQuery("FROM "+Cargo.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }

}
