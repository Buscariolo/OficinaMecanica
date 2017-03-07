package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Funcionario extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "fun_nome", nullable = false)
    private String nome;
    @Column(name = "fun_data_admissao", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data = new Date();
    @Column(name = "fun_salario", nullable = false)
    private BigDecimal salario;
    @ManyToOne
    @JoinColumn(name = "fun_cargo", nullable = false)
    private Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

}
