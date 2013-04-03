package com.controlart.bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.controlart.dao.ImagemDao;
import com.controlart.transfer.ImagemT;

public class ImagemBean extends ControlArtBean {

	private static final long serialVersionUID = 1L;

	private String pathAplicacao;
	private static final String PATH_IMAGENS = "Imagens" + File.separator;
	private static final String PATH_IMAGENS_TEMPORARIAS = PATH_IMAGENS
			+ "temp" + File.separator;

	private int idPeca;
	private List<ImagemT> listImagens;

	public ImagemBean() {
		pathAplicacao = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/");

		checkTempDir();

		listImagens = new ArrayList<ImagemT>(0);
	}

	private void checkTempDir() {
		File file = new File(pathAplicacao + PATH_IMAGENS_TEMPORARIAS);

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

		File file = new File(pathAplicacao + PATH_IMAGENS_TEMPORARIAS
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

	public void insertAction() {
		if (listImagens.size() != 0) {
			for (ImagemT imagemT : listImagens) {
				imagemT.setPeca(idPeca);
			}

			try {
				ImagemDao imagemDao = new ImagemDao();
				imagemDao.insert(listImagens);

				File file = new File(pathAplicacao + PATH_IMAGENS + idPeca
						+ File.separator);

				if (!file.exists()) {
					file.mkdirs();
				} else {
					FileUtils.cleanDirectory(file);
				}

				FileUtils.copyDirectory(new File(pathAplicacao
						+ PATH_IMAGENS_TEMPORARIAS), file);
			} catch (SQLException sql) {
				sql.printStackTrace();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}

	public void updateAction() {

	}

	public void deleteFromList() {
		ImagemT imagemT = getFacesObject("listaImagens");

		listImagens.remove(imagemT);

		File file = new File(pathAplicacao + PATH_IMAGENS_TEMPORARIAS
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

	public int getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}
}
