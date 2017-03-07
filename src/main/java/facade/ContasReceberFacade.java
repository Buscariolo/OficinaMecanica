package facade;

import entidades.ContasReceber;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

@Transacional
public class ContasReceberFacade extends AbstractFacade<ContasReceber> implements Serializable {

    @Inject
    private EntityManager em;
 
    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
    
    @Override
    protected EntityManager getEm() {
        return em;
    }
    
    @Override
    public List<ContasReceber> listar() {
        return getEm().createQuery("FROM "+ContasReceber.class.getSimpleName()+" WHERE status = 'PENDENTE' ORDER BY dataVencimento ASC").getResultList();
    }
    
    public List<ContasReceber> listar2() {
        return getEm().createQuery("FROM "+ContasReceber.class.getSimpleName()+" WHERE status = 'PAGO' ORDER BY dataBaixa DESC").getResultList();
    }
    
}
