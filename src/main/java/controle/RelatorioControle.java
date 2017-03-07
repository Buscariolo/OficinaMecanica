package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.primefaces.context.RequestContext;
import utils.ReportsUtil;

@Named
@RequestScoped
public class RelatorioControle implements Serializable {

    @Inject
    private EntityManager em;
    private Date dataInicio;
    private Date dataFim;
    private String nome;

    public void relatorioServicoVenda() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("inicio", dataInicio);
            m.put("fim", dataFim);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/ServicoVenda.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioContasReceber() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("inicio", dataInicio);
            m.put("fim", dataFim);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/ContasReceber.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioContasRecebidas() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("inicio", dataInicio);
            m.put("fim", dataFim);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/ContasRecebidas.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralCidade() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/cidades.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralFuncionario() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/funcionarios.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralPedido() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/pedidos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralCliente() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/clientes.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralCargo() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/cargos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralEmpresa() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/empresa.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralFornecedor() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/fornecedores.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralMarca() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/marca.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralEstado() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/estado.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    
    public void relatorioCadastralPais() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/pais.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralProduto() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/produtos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralUsuario() {
        try {
            ReportsUtil util = new ReportsUtil();
            Map m = new HashMap();
            m.put("parametros", nome);
            util.gerarRelatorioPDF(m, "WEB-INF/reports/usuarios.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public void relatorioCadastralClientesmaisgastaram() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/clientesmaisgastaram.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem", "Erro ao gerar relatório");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }

    public Connection getConnection() throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection connection = cp.getConnection();
        return connection;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
