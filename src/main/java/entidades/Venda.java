package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.primefaces.context.RequestContext;

@Entity
@Table(name = "venda")
public class Venda implements Serializable, MovimentaEstoque, Validador {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ven_id")
    private Long id;

    @Column(name = "ven_data", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data = new Date();

    @Column(name = "ven_totalfinal", nullable = false)
    private BigDecimal totalfinal = BigDecimal.ZERO;

    @Column(name = "ven_desconto")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "ven_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private VendaTipo vendaTipo = VendaTipo.ORCAMENTO;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "venda",
            orphanRemoval = true)
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "venda",
            orphanRemoval = true)
    private List<MaoDeObra> maoDeObra = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "venda",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<ContasReceber> listaContasReceber = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ven_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ven_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(name = "ven_quantidadeparcelas")
    private Integer quantidadeParcelas;

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public List<ContasReceber> getListaContasReceber() {
        return listaContasReceber;
    }

    public void setListaContasReceber(List<ContasReceber> listaContasReceber) {
        this.listaContasReceber = listaContasReceber;
    }

    public void addItem(ItemVenda item) throws Exception {
        item.setVenda(this);
        if (!itensVenda.contains(item)) {
            item.setPreco(item.getProduto().getPreco());
            itensVenda.add(item);
            calculaTotal();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "O produto " + item.getProduto().getNome() + " já está adicionado à venda");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void addItem2(MaoDeObra obra) throws Exception {
        obra.setVenda(this);
        maoDeObra.add(obra);
        calculaTotal();
    }

    @Override
    public void movimenta() throws Exception {
        if (vendaTipo.equals(VendaTipo.VENDA)) {
            for (ItemVenda item : itensVenda) {
                item.getProduto().baixarEstoque(item.getQuantidade());
            }
        }
    }

    @Override
    public void validar() throws Exception {
        if (itensVenda.isEmpty() && maoDeObra.isEmpty()) {
            throw new Exception();
        } else if (vendaTipo.equals(VendaTipo.VENDA) && listaContasReceber.isEmpty() && getQuantidadeParcelas() >= 1 && getQuantidadeParcelas() <= 12) {
            throw new Exception();
        }
    }

    public void removeItem(ItemVenda item) {
        itensVenda.remove(item);
        calculaTotal();
    }

    public void removeItem2(MaoDeObra obra) {
        maoDeObra.remove(obra);
        calculaTotal();
    }

    public void calculaTotal() {
        totalfinal = BigDecimal.ZERO;
        for (ItemVenda iv : itensVenda) {
            totalfinal = totalfinal.add(iv.getPreco().multiply(iv.getQuantidade()));
        }
        for (MaoDeObra mo : maoDeObra) {
            totalfinal = totalfinal.add(mo.getValor());
        }
        if (desconto == null || desconto.compareTo(totalfinal) > 0 || desconto.equals(totalfinal)) {
            desconto = BigDecimal.ZERO;
        }
        totalfinal = totalfinal.subtract(desconto);
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotalfinal() {
        return totalfinal;
    }

    public void setTotalfinal(BigDecimal totalfinal) {
        this.totalfinal = totalfinal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public List<MaoDeObra> getMaoDeObra() {
        return maoDeObra;
    }

    public void setMaoDeObra(List<MaoDeObra> maoDeObra) {
        this.maoDeObra = maoDeObra;
    }

    public VendaTipo getVendaTipo() {
        return vendaTipo;
    }

    public void setVendaTipo(VendaTipo vendaTipo) {
        this.vendaTipo = vendaTipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.itensVenda);
        hash = 97 * hash + Objects.hashCode(this.maoDeObra);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.itensVenda, other.itensVenda)) {
            return false;
        }
        if (!Objects.equals(this.maoDeObra, other.maoDeObra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
