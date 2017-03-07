package facade;

import entidades.Pedido;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

@Transacional
public class PedidoFacade extends AbstractFacade<Pedido> implements Serializable {

    @Inject
    private EntityManager em;

    public PedidoFacade() {
        super(Pedido.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    @Override
    public List<Pedido> listar() {
        return getEm().createQuery("FROM "+Pedido.class.getSimpleName()+" ORDER BY data ASC").getResultList();
    }

}
