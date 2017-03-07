package facade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> implements Serializable {

    private final Class<T> classe;

    public AbstractFacade(Class<T> classe) {
        this.classe = classe;
    }

    protected abstract EntityManager getEm();

    public T salvar(T entidade) {
        entidade = getEm().merge(entidade);
        return entidade;
    }

    public void excluir(T entidade) {
        getEm().remove(getEm().merge(entidade));
    }

    public T pesquisar(Object id) {
        return getEm().find(classe, id);
    }

    public List<T> listar() {
        return getEm().createQuery("FROM " + classe.getSimpleName()).getResultList();
    }

}
