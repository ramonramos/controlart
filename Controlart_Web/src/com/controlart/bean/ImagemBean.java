package com.controlart.bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.controlart.transfer.ImagemT;

public class ImagemBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	private static final String PATH_APLICACAO = FacesContext
			.getCurrentInstance().getExternalContext().getRealPath("/");
	private static final String PATH_IMAGENS = "Imagens" + File.separator;
	private static final String PATH_IMAGENS_TEMPORARIAS = PATH_IMAGENS
			+ "temp" + File.separator;

	private List<ImagemT> listImagens;

	public ImagemBean() {
		checkTempDir();

		listImagens = new ArrayList<ImagemT>(0);
	}

	private void checkTempDir() {
		File file = new File(PATH_APLICACAO + PATH_IMAGENS_TEMPORARIAS);

		if (file.exists()) {
			try {
				FileUtils.cleanDirectory(file);
			} catch (IOException io) {
				io.printStackTrace();
			}
		} else {
			file.mkdirs();
		}
	}

	public void listener(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();

		File file = new File(PATH_APLICACAO + PATH_IMAGENS_TEMPORARIAS
				+ uploadedFile.getFileName());

		try {
			InputStream is = new BufferedInputStream(
					uploadedFile.getInputstream());

			OutputStream fos = new FileOutputStream(file);

			while (is.available() != 0) {
				fos.write(is.read());
			}

			fos.close();
			is.close();

			ImagemT imagemT = new ImagemT();
			imagemT.setNome(uploadedFile.getFileName());

			listImagens.add(imagemT);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void deleteFromList() {
		ImagemT imagemT = getFacesObject("listaImagens");

		listImagens.remove(imagemT);

		File file = new File(PATH_APLICACAO + PATH_IMAGENS_TEMPORARIAS
				+ imagemT.getNome());
		file.delete();
	}

	public int getQtdImagens() {
		return listImagens.size();
	}

	public List<ImagemT> getListImagens() {
		return listImagens;
	}

	public void setListImagens(List<ImagemT> listImagens) {
		this.listImagens = listImagens;
	}

	public String getPathImagensTemporarias() {
		return PATH_IMAGENS_TEMPORARIAS;
	}
}
