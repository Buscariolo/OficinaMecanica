package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prod_id")
    private Long id;
    @Column(name = "prod_nome", nullable = false)
    private String nome;
    @Column(name = "prod_codigo", nullable = false)
    private String codigo;
    @Column(name = "prod_estoque", nullable = false)
    private BigDecimal estoque;
    @Column(name = "prod_preco", nullable = false)
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "prod_marca", nullable = false)
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "prod_fornecedor", nullable = false)
    private Fornecedor fornecedor;
    @Column(name = "prod_detalhes")
    private String detalhes;
    @Column(name = "prod_veiculos", nullable = false)
    private String veiculos;

    public String getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(String veiculos) {
        this.veiculos = veiculos;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void baixarEstoque(BigDecimal quantidade) throws Exception {
        if (estoque.compareTo(quantidade) >= 0) {
            estoque = estoque.subtract(quantidade);
        } else {
            throw new Exception();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
