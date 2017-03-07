package controle;

import converter.ConverterGenerico;
import entidades.Pedido;
import facade.PedidoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PedidoControle implements Serializable {

    private Pedido pedido;
    @Inject
    private PedidoFacade pedidoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(pedidoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        pedido = new Pedido();
    }

    public String salvar() {
        pedidoFacade.salvar(pedido);
        return "list?faces-redirect=true";
    }

    public String excluir(Pedido p) {
        pedidoFacade.excluir(p);
        return "list?faces-redirect=true";
    }

    public List<Pedido> getListagem() {
        return pedidoFacade.listar();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
