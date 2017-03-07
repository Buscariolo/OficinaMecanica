package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "contasreceber")
public class ContasReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cr_id")
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "cr_dataconta", nullable = false)
    private Date dataConta;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "cr_datavencimento", nullable = false)
    private Date dataVencimento; 
    @Column(name = "cr_status", nullable = false)
    private String status; 
    @Column(name = "cr_valor", nullable = false)
    private Double valor;
    @Column(name = "cr_parcela", nullable = false)
    private Integer parcela;
    @ManyToOne
    @JoinColumn(name = "cr_venda", nullable = false)
    private Venda venda;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JoinColumn(name = "cr_databaixa")
    private Date dataBaixa;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConta() {
        return dataConta;
    }

    public void setDataConta(Date dataConta) {
        this.dataConta = dataConta;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.dataVencimento);
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
        final ContasReceber other = (ContasReceber) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
