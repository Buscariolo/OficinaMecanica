package facade;

import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Transacional;
import utils.Criptografia;

@Transacional
public class UsuarioFacade extends AbstractFacade<Usuario> implements Serializable {

    @Inject
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public Usuario pesquisaUsuario(String login, String senha) {
        Query query = em.createQuery("FROM Usuario AS u WHERE u.login='" + login + "' AND u.senha='" + Criptografia.md5(senha) + "'");
        if (query.getResultList().size() == 1) {
            return (Usuario) query.getResultList().get(0);
        }
        return null;
    }
    
    @Override
    public List<Usuario> listar() {
        return getEm().createQuery("FROM "+Usuario.class.getSimpleName()+" ORDER BY identificacao ASC").getResultList();
    }

}
