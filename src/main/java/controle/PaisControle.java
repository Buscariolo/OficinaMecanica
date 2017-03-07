package controle;

import converter.ConverterGenerico;
import entidades.Pais;
import facade.PaisFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PaisControle implements Serializable {

    private Pais pais;
    @Inject
    private PaisFacade paisFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(paisFacade);
        }
        return converterGenerico;
    }

    public String novo() {
        pais = new Pais();
        return "/app/pais/form2.xhtml";
    }

    public String salvar() {
        paisFacade.salvar(pais);
        return "list?faces-redirect=true";
    }

    public String excluir(Pais e) {
        paisFacade.excluir(e);
        return "list?faces-redirect=true";
    }

    public List<Pais> getListagem() {
        return paisFacade.listar();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    public List<Pais> paisAutoComplete(String nome) {
        return paisFacade.paisAutoComplete(nome);
    }

}
