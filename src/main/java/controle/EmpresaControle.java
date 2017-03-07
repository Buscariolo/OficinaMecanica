package controle;

import converter.ConverterGenerico;
import entidades.Empresa;
import facade.EmpresaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EmpresaControle implements Serializable {

    private Empresa empresa;
    @Inject
    private EmpresaFacade empresaFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(empresaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        empresa = new Empresa();
    }

    public String salvar() {
        empresaFacade.salvar(empresa);
        return "list?faces-redirect=true";
    }

    public String excluir(Empresa e) {
        empresaFacade.excluir(e);
        return "list?faces-redirect=true";
    }

    public List<Empresa> getListagem() {
        return empresaFacade.listar();
    }
    
    public int status() {
        if (empresaFacade.listar().isEmpty()) {
            return 1;
        }
        return 2;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
