package integration;

import java.io.IOException;

import integration.entities.ImageClientResource;
import integration.entities.ImageListClientResource;

import org.restlet.representation.Representation;

import business.representations.ImageListTO;

public class ImagesDAO {
	public Representation find(String nome) throws DAOException {
		ImageClientResource images = new ImageClientResource(nome);
		Representation image = null;

		try {
			image = images.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return image;
	}

	public ImageListTO findAll() throws DAOException {
		ImageListClientResource images = new ImageListClientResource();
		ImageListTO imageList = null;

		try {
			imageList = images.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageList;
	}

	public void update(Representation nome) throws DAOException, IOException {
		ImageClientResource images = new ImageClientResource(nome.getText());
		@SuppressWarnings("unused")
		Representation imageTO = null;

		try {
			imageTO = images.store(nome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(String nome) throws DAOException {
		ImageClientResource images = new ImageClientResource(nome);

		try {
			images.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
