package controle;

import converter.ConverterGenerico;
import entidades.Cargo;
import facade.CargoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CargoControle implements Serializable {

    private Cargo cargo;
    @Inject
    private CargoFacade cargoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(cargoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        cargo = new Cargo();
    }

    public String salvar() {
        cargoFacade.salvar(cargo);
        return "list?faces-redirect=true";
    }

    public String excluir(Cargo c) {
        cargoFacade.excluir(c);
        return "list?faces-redirect=true";
    }

    public List<Cargo> getListagem() {
        return cargoFacade.listar();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public List<Cargo> cargoAutoComplete(String nome) {
        return cargoFacade.cargoAutoComplete(nome);
    }

}
