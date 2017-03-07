package controle;

import converter.ConverterGenerico;
import entidades.ContasReceber;
import entidades.ItemVenda;
import entidades.MaoDeObra;
import entidades.Venda;
import entidades.VendaTipo;
import facade.VendaFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class VendaControle implements Serializable {

    private Venda venda;
    @Inject
    private VendaFacade vendaFacade;
    private ConverterGenerico converterGenerico;
    private ItemVenda itemVenda = new ItemVenda();
    private MaoDeObra maoDeObra = new MaoDeObra();
    private ContasReceber contasReceber = new ContasReceber();
    private Date dataPrimeiraParcela = new Date();
    private boolean flag;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Date getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public void geraParcelas() throws Exception {

        if (validaParcelas() && venda.getQuantidadeParcelas() >= 1 && venda.getQuantidadeParcelas() <= 12) {

            venda.setListaContasReceber(new ArrayList<ContasReceber>());
            Date dataParcela = dataPrimeiraParcela;

            for (int i = 1; i <= venda.getQuantidadeParcelas(); i++) {
                ContasReceber cr = new ContasReceber();
                cr.setDataConta(venda.getData());
                cr.setDataVencimento(dataParcela);
                cr.setStatus("PENDENTE");
                cr.setValor(venda.getTotalfinal().doubleValue() / venda.getQuantidadeParcelas());
                cr.setParcela(i);
                cr.setVenda(venda);
                venda.getListaContasReceber().add(cr);

                Calendar cal = Calendar.getInstance();
                cal.setTime(dataParcela);
                cal.add(Calendar.MONTH, 1);
                dataParcela = cal.getTime();
            }
        } else {
            if (!validaParcelas()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "A data da primeira parcela deve ser maior ou igual a data da venda");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                throw new Exception();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "As parcelas devem estar entre 1 e 12");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                throw new Exception();
            }
        }
    }

    private boolean validaParcelas() {
        Date d = dataPrimeiraParcela;

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.HOUR, 23);
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.SECOND, 59);
        d = cal.getTime();

        if (d.before(venda.getData())) {
            return false;
        }
        return true;
    }

    public void addItem() throws Exception {
        BigDecimal q = itemVenda.getQuantidade();
        BigDecimal e = itemVenda.getProduto().getEstoque();
        if (e.compareTo(q) >= 0) {
            venda.addItem(itemVenda);
            venda.setDesconto(BigDecimal.ZERO);
            venda.calculaTotal();
            itemVenda = new ItemVenda();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Quantidade Insuficiente em Estoque");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void addItem2() throws Exception {
        venda.addItem2(maoDeObra);
        venda.setDesconto(BigDecimal.ZERO);
        venda.calculaTotal();
        maoDeObra = new MaoDeObra();
    }

    public void removeItem(ItemVenda i) {
        venda.removeItem(i);
    }

    public void removeItem2(MaoDeObra m) {
        venda.removeItem2(m);
    }

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(vendaFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        venda = new Venda();
        itemVenda = new ItemVenda();
        maoDeObra = new MaoDeObra();
        contasReceber = new ContasReceber();
    }

    public String salvar() {
        if (venda.getVendaTipo().equals(VendaTipo.VENDA)) {
            try {
                venda.calculaTotal();
                geraParcelas();
                venda.movimenta();
                venda.validar();
                venda.setData(new Date());
                vendaFacade.salvar(venda);
                return "list?faces-redirect=true";
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Confira se mão de obra e produto estão em brancos "
                        + "ou se há modo de pagamento correto "
                        + "e se a quantidade de produtos na lista há no estoque");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            return null;
        } else {
            try {
                venda.calculaTotal();
                venda.validar();
                venda.setListaContasReceber(new ArrayList<ContasReceber>());
                venda.setQuantidadeParcelas(null);
                contasReceber = new ContasReceber();
                vendaFacade.salvar(venda);
                return "list?faces-redirect=true";
            } catch (Exception ex) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao salvar, confira se mão de obra e produto estão em brancos");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
            return null;
        }

    }

    public String excluir(Venda v) {
        vendaFacade.excluir(v);
        return "list?faces-redirect=true";
    }

    public List<Venda> getListagem() {
        return vendaFacade.listar();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public MaoDeObra getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(MaoDeObra maoDeObra) {
        this.maoDeObra = maoDeObra;
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public String voltar() {
        return "list?faces-redirect=true";
    }

}
