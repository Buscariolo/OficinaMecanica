package controle;

import com.sun.faces.facelets.tag.jsf.core.ResetValuesHandler;
import converter.ConverterGenerico;
import entidades.Funcionario;
import entidades.NivelAcesso;
import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UsuarioControle implements Serializable {

    private Usuario usuario;
    @Inject
    private UsuarioFacade usuarioFacade;
    private ConverterGenerico converterGenerico;
    private boolean flag;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ConverterGenerico converter() {
        if (converterGenerico == null) {
            converterGenerico = new ConverterGenerico(usuarioFacade);
        }
        return converterGenerico;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public String salvar() {
        try {
            usuarioFacade.salvar(usuario);
            return "list?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Dados de usuário repetidos");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return null;
    }

    public String excluir(Usuario u) {
        usuarioFacade.excluir(u);
        return "list?faces-redirect=true";
    }
    
    public String voltar() {
        usuario = new Usuario();
        return "list?faces-redirect=true";
    }

    public List<Usuario> getListagem() {
        return usuarioFacade.listar();
    }

    public NivelAcesso[] getNiveisAcesso() {
        return NivelAcesso.values();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
