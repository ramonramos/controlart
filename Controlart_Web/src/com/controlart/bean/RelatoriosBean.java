package com.controlart.bean;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.controlart.dao.RelatoriosDao;

@ManagedBean(name = "relatoriosBean")
@ViewScoped
public class RelatoriosBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	public void gerarRelatorio() {
		try {
			
			RelatoriosDao relatoriosDao = new RelatoriosDao();
			Connection connection = relatoriosDao.getConnection();

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scontext = (ServletContext) facesContext
					.getExternalContext().getContext();

			String arquivoJasper = scontext
					.getRealPath("/resources/jasper/rel_tabela_preco.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					arquivoJasper, null, connection);

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
				response.setHeader("Content-disposition",
						"inline; filename=\"relatorioTabelaPreco.pdf\"");
				response.setContentLength(bytes.length);
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
