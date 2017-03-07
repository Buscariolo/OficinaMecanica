package controle;

import converter.ConverterGenerico;
import entidades.Cliente;
import facade.ClienteFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ClienteControle implements Serializable {

    private Cliente cliente;
    @Inject
    private ClienteFacade clienteFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(clienteFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        cliente = new Cliente();
    }

    public String salvar() {
        clienteFacade.salvar(cliente);
        return "list?faces-redirect=true";
    }

    public String excluir(Cliente c) {
        clienteFacade.excluir(c);
        return "list?faces-redirect=true";
    }

    public List<Cliente> getListagem() {
        return clienteFacade.listar();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> clienteAutoComplete(String nome) {
        return clienteFacade.clienteAutoComplete(nome);
    }

}
