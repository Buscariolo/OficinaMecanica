package controle;

import converter.ConverterGenerico;
import entidades.Marca;
import facade.MarcaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MarcaControle implements Serializable {

    private Marca marca;
    @Inject
    private MarcaFacade marcaFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(marcaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        marca = new Marca();
    }

    public String salvar() {
        marcaFacade.salvar(marca);
        return "list?faces-redirect=true";
    }

    public String excluir(Marca m) {
        marcaFacade.excluir(m);
        return "list?faces-redirect=true";
    }

    public List<Marca> getListagem() {
        return marcaFacade.listar();
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public List<Marca> marcaAutoComplete(String nome) {
        return marcaFacade.marcaAutoComplete(nome);
    }

}
