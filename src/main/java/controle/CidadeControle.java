package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import facade.CidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CidadeControle implements Serializable {

    private Cidade cidade;
    @Inject
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(cidadeFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        cidade = new Cidade();
    }

    public String salvar() {
        cidadeFacade.salvar(cidade);
        return "list?faces-redirect=true";
    }

    public String excluir(Cidade c) {
        cidadeFacade.excluir(c);
        return "list?faces-redirect=true";
    }

    public List<Cidade> getListagem() {
        return cidadeFacade.listar();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public List<Cidade> cidadeAutoComplete(String nome) {
        return cidadeFacade.cidadeAutoComplete(nome);
    }

}
