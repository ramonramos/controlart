package com.controlart.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.controlart.dao.AcervoDao;
import com.controlart.dao.ClassificacaoDao;
import com.controlart.dao.RelatoriosDao;
import com.controlart.dao.TipoTransacaoDao;
import com.controlart.transfer.AcervoT;
import com.controlart.transfer.ClassificacaoT;
import com.controlart.transfer.TipoTransacaoT;

@ManagedBean(name = "relatoriosBean")
@ViewScoped
public class RelatoriosBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;
	private static final String PATCH_LOGO = "/resources/image/logo_ireport.gif";
	private FacesContext facesContext;
	private Date dataInicial;
	private Date dataFinal;
	private List<AcervoT> listAcervo;
	private AcervoT acervoT;
	private List<ClassificacaoT> listClassificacao;
	private ClassificacaoT classificacaoT;
	private List<TipoTransacaoT> listTipoTransacao;
	private List<TipoTransacaoT> listTipoTransacaoEntrada;
	private TipoTransacaoT tipoTransacaoT;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<AcervoT> getListAcervo() {
		if (listAcervo == null) {
			try {
				AcervoDao acervoDao = new AcervoDao();
				listAcervo = acervoDao.consultAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listAcervo;
	}

	public void setListAcervo(List<AcervoT> listAcervo) {
		this.listAcervo = listAcervo;
	}

	public AcervoT getAcervoT() {
		return acervoT;
	}

	public void setAcervoT(AcervoT acervoT) {
		this.acervoT = acervoT;
	}

	public List<ClassificacaoT> getListClassificacao() {
		if (listClassificacao == null) {
			try {
				ClassificacaoDao classificacaoDao = new ClassificacaoDao();
				listClassificacao = classificacaoDao.consultAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listClassificacao;
	}

	public void setListClassificacao(List<ClassificacaoT> listClassificacao) {
		this.listClassificacao = listClassificacao;
	}

	public ClassificacaoT getClassificacaoT() {
		return classificacaoT;
	}

	public void setClassificacaoT(ClassificacaoT classificacaoT) {
		this.classificacaoT = classificacaoT;
	}

	public List<TipoTransacaoT> getListTipoTransacao() {
		if (listTipoTransacao == null) {
			try {
				TipoTransacaoDao tipoTransacaoDao = new TipoTransacaoDao();
				listTipoTransacao = tipoTransacaoDao
						.consultTipoTransacaoComDevolucao();
			} catch (SQLException sql) {
				processSQLError(sql);
			}
		}
		return listTipoTransacao;
	}

	public void setListTipoTransacao(List<TipoTransacaoT> listTipoTransacao) {
		this.listTipoTransacao = listTipoTransacao;
	}

	public List<TipoTransacaoT> getListTipoTransacaoEntrada() {
		if (listTipoTransacaoEntrada == null) {
			try {
				TipoTransacaoDao tipoTransacaoDao = new TipoTransacaoDao();
				listTipoTransacaoEntrada = tipoTransacaoDao
						.consultTipoTransacaoEntrada();
			} catch (SQLException sql) {
				processSQLError(sql);
			}
		}
		return listTipoTransacaoEntrada;
	}

	public void setListTipoTransacaoEntrada(
			List<TipoTransacaoT> listTipoTransacaoEntrada) {
		this.listTipoTransacaoEntrada = listTipoTransacaoEntrada;
	}

	public TipoTransacaoT getTipoTransacaoT() {
		return tipoTransacaoT;
	}

	public void setTipoTransacaoT(TipoTransacaoT tipoTransacaoT) {
		this.tipoTransacaoT = tipoTransacaoT;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void gerarRelatorio(String arquivoJasper, HashMap parametros,
			Connection connection, JRResultSetDataSource jrsds, String titulo)
			throws JRException, IOException {

		JasperPrint jasperPrint = null;
		if (connection != null) {
			jasperPrint = JasperFillManager.fillReport(arquivoJasper,
					parametros, connection);
		} else if (jrsds != null) {
			jasperPrint = JasperFillManager.fillReport(arquivoJasper,
					parametros, jrsds);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();

		byte[] bytes = baos.toByteArray();

		if (bytes != null && bytes.length > 0) {
			HttpServletResponse response = (HttpServletResponse) facesContext
					.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=\""
					+ titulo + ".pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioEntradaPeca() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();
			Statement stmt = connection.createStatement();

			StringBuilder sb = new StringBuilder();
			sb.append("select tabela.nm_acervo, ");
			sb.append("       tabela.nm_peca, ");
			sb.append("       tabela.nm_tipo_transacao, ");
			sb.append("       case ");
			sb.append("       when nm_tipo_transacao ilike 'Dev%' then ");
			sb.append("          tabela.dt_devolucao ");
			sb.append("       else ");
			sb.append("          tabela.dt_transacao ");
			sb.append("       end as dt_transacao ");
			sb.append("from ( ");
			sb.append("       select a.nm_acervo, ");
			sb.append("              p.nm_peca, ");
			sb.append("              tt.nm_tipo_transacao, ");
			sb.append("              t.dt_transacao, ");
			sb.append("              t.dt_previsao, ");
			sb.append("              t.dt_devolucao ");
			sb.append("from tb_peca p inner join ");
			sb.append("tb_transacao t using(id_peca) inner join ");
			sb.append("tb_tipo_transacao tt using(id_tipo_transacao) inner join ");
			sb.append("tb_acervo a on (t.id_acervo_destino = a.id_acervo) ");
			sb.append("where tt.tp_operacao = 'E' ");
			sb.append("  and t.id_acervo_destino = " + acervoT.getId());
			sb.append(") as tabela ");
			if (getDataInicial() != null && getDataFinal() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				sb.append("  where dt_transacao between '"
						+ sdf.format(getDataInicial()) + "' and '"
						+ sdf.format(getDataFinal()) + "' ");
			}
			sb.append("order by dt_transacao desc ");

			ResultSet rs = stmt.executeQuery(sb.toString());
			JRResultSetDataSource jrsds = new JRResultSetDataSource(rs);

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_entrada_peca.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Entrada de Peças";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, null, jrsds, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioPecasAcervo() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_pecas_acervo.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Peças por Acervo";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("ID_ACERVO", acervoT.getId());
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, connection, null, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioPecasClassificacao() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_pecas_classificacao.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Peças por Classificação";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("ID_CLASSIFICACAO", classificacaoT.getId());
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, connection, null, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioPecasTransacao() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();
			Statement stmt = connection.createStatement();

			StringBuilder sb = new StringBuilder();
			sb.append("select tabela.nm_peca, ");
			sb.append("       tabela.nm_classificacao, ");
			sb.append("       case ");
			sb.append("       when nm_tipo_transacao ilike 'Dev%' then ");
			sb.append("          tabela.dt_devolucao ");
			sb.append("       else ");
			sb.append("          tabela.dt_transacao ");
			sb.append("       end as dt_transacao ");
			sb.append("from ( ");
			sb.append("       select p.nm_peca, ");
			sb.append("              tt.nm_tipo_transacao, ");
			sb.append("              t.dt_transacao, ");
			sb.append("              c.nm_classificacao, ");
			sb.append("              t.dt_devolucao ");
			sb.append("from tb_peca p inner join ");
			sb.append("tb_transacao t using(id_peca) inner join ");
			sb.append("tb_tipo_transacao tt using(id_tipo_transacao) inner join ");
			sb.append("tb_classificacao c using(id_classificacao) ");
			sb.append("where tt.id_tipo_transacao = " + tipoTransacaoT.getId());
			sb.append(") as tabela ");
			if (getDataInicial() != null && getDataFinal() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				sb.append("  where dt_transacao between '"
						+ sdf.format(getDataInicial()) + "' and '"
						+ sdf.format(getDataFinal()) + "' ");
			}
			sb.append("order by dt_transacao desc ");

			ResultSet rs = stmt.executeQuery(sb.toString());
			JRResultSetDataSource jrsds = new JRResultSetDataSource(rs);

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_pecas_transacao.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Peças por Transação";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, null, jrsds, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioPrazoDevolucao() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_prazo_devolucao.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Prazo para Devolução";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("ID_TIPO_TRANSACAO", tipoTransacaoT.getId());
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, connection, null, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioSaidaPeca() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();
			Statement stmt = connection.createStatement();

			StringBuilder sb = new StringBuilder();
			sb.append("select tabela.nm_acervo, ");
			sb.append("       tabela.nm_peca, ");
			sb.append("       tabela.nm_tipo_transacao, ");
			sb.append("       case ");
			sb.append("       when nm_tipo_transacao ilike 'Dev%' then ");
			sb.append("          tabela.dt_devolucao ");
			sb.append("       else ");
			sb.append("          tabela.dt_transacao ");
			sb.append("       end as dt_transacao ");
			sb.append("from ( ");
			sb.append("       select a.nm_acervo, ");
			sb.append("              p.nm_peca, ");
			sb.append("              tt.nm_tipo_transacao, ");
			sb.append("              t.dt_transacao, ");
			sb.append("              t.dt_previsao, ");
			sb.append("              t.dt_devolucao ");
			sb.append("from tb_peca p inner join ");
			sb.append("tb_transacao t using(id_peca) inner join ");
			sb.append("tb_tipo_transacao tt using(id_tipo_transacao) inner join ");
			sb.append("tb_acervo a on (t.id_acervo_origem = a.id_acervo) ");
			sb.append("where tt.tp_operacao = 'S' ");
			sb.append("  and t.id_acervo_origem = " + acervoT.getId());
			sb.append(") as tabela ");
			if (getDataInicial() != null && getDataFinal() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				sb.append("  where dt_transacao between '"
						+ sdf.format(getDataInicial()) + "' and '"
						+ sdf.format(getDataFinal()) + "' ");
			}
			sb.append("order by dt_transacao desc ");

			ResultSet rs = stmt.executeQuery(sb.toString());
			JRResultSetDataSource jrsds = new JRResultSetDataSource(rs);

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_saida_peca.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Saída de Peças";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, null, jrsds, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioTabelaPreco() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

			facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_tabela_preco.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Tabela de Preços";

			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("ID_ACERVO", acervoT.getId());
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, connection, null, titulo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
