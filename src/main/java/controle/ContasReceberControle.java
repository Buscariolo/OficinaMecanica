package controle;

import converter.ConverterGenerico;
import entidades.ContasReceber;
import facade.ContasReceberFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ContasReceberControle implements Serializable {

    private ContasReceber contasReceber;    
    @Inject
    private ContasReceberFacade contasReceberFacade;
    private ConverterGenerico converterGenerico;
    
    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(contasReceberFacade);
        }
        return converterGenerico;
    }
    
    public void novo(){
        contasReceber = new ContasReceber();
    }
    
    public void salvar() {
        contasReceberFacade.salvar(contasReceber);
    }
    
    public void editar(ContasReceber e){
        contasReceber = e;
    }
    
    public void excluir(ContasReceber e){
        contasReceberFacade.excluir(e);
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }
    
    public List<ContasReceber> getListagem() {
        return contasReceberFacade.listar();
    }
    
    public List<ContasReceber> getListagem2() {
        return contasReceberFacade.listar2();
    }
    
    public void pagamento(ContasReceber cr) {
        cr.setStatus("PAGO");
        cr.setDataBaixa(new Date());
        contasReceberFacade.salvar(cr);
    }

}
