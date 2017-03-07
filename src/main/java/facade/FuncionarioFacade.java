package facade;

import entidades.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;

@Transacional
public class FuncionarioFacade extends AbstractFacade<Funcionario> implements Serializable {

    @Inject
    private EntityManager em;

    public FuncionarioFacade() {
        super(Funcionario.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public List<Funcionario> funcionarioAutoComplete(String nome) {
        Query q = em.createQuery("FROM Funcionario AS f WHERE LOWER(f.nome) LIKE('%" + nome.toLowerCase() + "%') ORDER BY f.nome");
        return q.getResultList();
    }
    
    @Override
    public List<Funcionario> listar() {
        return getEm().createQuery("FROM "+Funcionario.class.getSimpleName()+" ORDER BY nome ASC").getResultList();
    }
    
}
