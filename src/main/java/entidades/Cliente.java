package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cli_nome_razao", nullable = false)
    private String nome_razao;
    @Column(name = "cli_tipo_pessoa", nullable = false)
    private String tipo_pessoa = "PF";
    @Column(name = "cli_data_cadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data_cadastro = new Date();

    public String getNome_razao() {
        return nome_razao;
    }

    public void setNome_razao(String nome_razao) {
        this.nome_razao = nome_razao;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

}
