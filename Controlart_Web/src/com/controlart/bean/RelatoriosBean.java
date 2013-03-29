package com.controlart.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

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

import com.controlart.dao.RelatoriosDao;

@ManagedBean(name = "relatoriosBean")
@ViewScoped
public class RelatoriosBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;
	private static final String PATCH_LOGO = "/resources/image/logo_ireport.gif";
	private FacesContext facesContext;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void gerarRelatorio(String arquivoJasper, HashMap parametros, Connection connection, String titulo) throws JRException, IOException{
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper, parametros, connection);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();

		byte[] bytes = baos.toByteArray();

		if (bytes != null && bytes.length > 0) {
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","inline; filename=\""+titulo+".pdf\"");
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
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

			String arquivoJasper = scontext.getRealPath("/resources/jasper/rel_tabela_preco.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Tabela de Preços";
						
			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem );
			
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
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

			String arquivoJasper = scontext.getRealPath("/resources/jasper/rel_entrada_peca.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Entrada de Peças";
						
			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem );
			
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
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

			String arquivoJasper = scontext.getRealPath("/resources/jasper/rel_saida_peca.jasper");
			String arquivoLogo = scontext.getRealPath(PATCH_LOGO);
			String titulo = "Saída de Peças";
						
			BufferedImage imagem = ImageIO.read(new File(arquivoLogo));
			HashMap parametros = new HashMap();
			parametros.put("LOGO", imagem );
			
			gerarRelatorio(arquivoJasper, parametros, connection, titulo);
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
