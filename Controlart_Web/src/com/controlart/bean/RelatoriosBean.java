package com.controlart.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.controlart.dao.AcervoDao;
import com.controlart.dao.ClassificacaoDao;
import com.controlart.dao.RelatoriosDao;
import com.controlart.transfer.AcervoT;
import com.controlart.transfer.ClassificacaoT;

@ManagedBean(name = "relatoriosBean")
@ViewScoped
public class RelatoriosBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;
	private static final String PATCH_LOGO = "/resources/image/logo_ireport.gif";
	private FacesContext facesContext;
	private List<AcervoT> listAcervo;
	private AcervoT acervoT;
	private List<ClassificacaoT> listClassificacao;
	private ClassificacaoT classificacaoT;

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void gerarRelatorio(String arquivoJasper, HashMap parametros,
			Connection connection, String titulo) throws JRException,
			IOException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper,
				parametros, connection);

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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioEntradaPeca() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioPecasTransacao() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
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
			parametros.put("LOGO", imagem);

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void gerarRelatorioSaidaPeca() {
		try {

			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

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

			gerarRelatorio(arquivoJasper, parametros, connection, titulo);

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
