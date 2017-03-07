package facade;

import entidades.Empresa;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import persistencia.Transacional;

@Transacional
public class EmpresaFacade extends AbstractFacade<Empresa> implements Serializable {

    @Inject
    private EntityManager em;

    public EmpresaFacade() {
        super(Empresa.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

}
