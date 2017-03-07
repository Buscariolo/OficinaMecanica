package controle;

import converter.ConverterGenerico;
import entidades.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EstadoControle implements Serializable {

    private Estado estado;
    @Inject
    private EstadoFacade estadoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(estadoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        estado = new Estado();
    }

    public String salvar() {
        estadoFacade.salvar(estado);
        return "list?faces-redirect=true";
    }

    public String excluir(Estado e) {
        estadoFacade.excluir(e);
        return "list?faces-redirect=true";
    }

    public List<Estado> getListagem() {
        return estadoFacade.listar();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public List<Estado> estadoAutoComplete(String nome) {
        return estadoFacade.estadoAutoComplete(nome);
    }

}
