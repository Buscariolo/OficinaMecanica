package controle;

import converter.ConverterGenerico;
import entidades.Produto;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProdutoControle implements Serializable {

    private Produto produto;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(produtoFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        produto = new Produto();
    }

    public String salvar() {
        produtoFacade.salvar(produto);
        return "list?faces-redirect=true";
    }

    public String excluir(Produto p) {
        produtoFacade.excluir(p);
        return "list?faces-redirect=true";
    }

    public List<Produto> getListagem() {
        return produtoFacade.listar();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> produtoAutoComplete(String codigo) {
        return produtoFacade.produtoAutoComplete(codigo);
    }

}
