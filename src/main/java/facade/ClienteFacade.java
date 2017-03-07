package facade;

import entidades.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class ClienteFacade extends AbstractFacade<Cliente> implements Serializable {

    @Inject
    private EntityManager em;

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public List<Cliente> clienteAutoComplete(String nome) {
        Query q = em.createQuery("FROM Cliente AS c WHERE LOWER(c.nome_razao) LIKE('%" + nome.toLowerCase() + "%') ORDER BY c.nome_razao");
        return q.getResultList();
    }
    
    @Override
    public List<Cliente> listar() {
        return getEm().createQuery("FROM "+Cliente.class.getSimpleName()+" ORDER BY nome_razao ASC").getResultList();
    }

}
