package controle;

import converter.ConverterGenerico;
import entidades.Fornecedor;
import facade.FornecedorFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FornecedorControle implements Serializable {

    private Fornecedor fornecedor;
    @Inject
    private FornecedorFacade fornecedorFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(fornecedorFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        fornecedor = new Fornecedor();
    }

    public String salvar() {
        fornecedorFacade.salvar(fornecedor);
        return "list?faces-redirect=true";
    }

    public String excluir(Fornecedor f) {
        fornecedorFacade.excluir(f);
        return "list?faces-redirect=true";
    }

    public List<Fornecedor> getListagem() {
        return fornecedorFacade.listar();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public List<Fornecedor> fornecedorAutoComplete(String nome) {
        return fornecedorFacade.fornecedorAutoComplete(nome);
    }

}
