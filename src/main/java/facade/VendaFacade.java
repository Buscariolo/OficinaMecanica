package facade;

import entidades.Venda;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

@Transacional
public class VendaFacade extends AbstractFacade<Venda> implements Serializable {

    @Inject
    private EntityManager em;

    public VendaFacade() {
        super(Venda.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    @Override
    public List<Venda> listar() {
        return getEm().createQuery("FROM "+Venda.class.getSimpleName()+" ORDER BY data DESC").getResultList();
    }

}
