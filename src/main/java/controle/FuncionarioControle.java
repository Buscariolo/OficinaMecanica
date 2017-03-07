package controle;

import converter.ConverterGenerico;
import entidades.Funcionario;
import facade.FuncionarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FuncionarioControle implements Serializable {

    private Funcionario funcionario;
    @Inject
    private FuncionarioFacade funcionarioFacade;
    private ConverterGenerico converterGenerico;

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(funcionarioFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        funcionario = new Funcionario();
    }

    public String salvar() {
        funcionarioFacade.salvar(funcionario);
        return "list?faces-redirect=true";
    }

    public String excluir(Funcionario c) {
        funcionarioFacade.excluir(c);
        return "list?faces-redirect=true";
    }

    public List<Funcionario> getListagem() {
        return funcionarioFacade.listar();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public List<Funcionario> funcionarioAutoComplete(String nome) {
        return funcionarioFacade.funcionarioAutoComplete(nome);
    }

}
