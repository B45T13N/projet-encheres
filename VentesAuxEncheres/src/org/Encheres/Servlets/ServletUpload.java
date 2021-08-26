package org.Encheres.Servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ServletUpload
 */
@WebServlet("/Upload")
public class ServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * Chemin dans lequel les images seront sauvegardées.
	 */
	public static final String IMAGES_FOLDER = "/Images";

	public String uploadPath;

	/*
	 * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
	 */
	@Override
	public void init() throws ServletException {
		uploadPath = getServletContext().getRealPath(IMAGES_FOLDER);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
	}

	/*
	 * Récupération et sauvegarde du contenu de chaque image.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		for (Part part : request.getParts()) {
			String fileName = getFileName(part);
			String fullPath = uploadPath + File.separator + fileName;
			part.write(fullPath);
		}
	}

	/*
	 * Récupération du nom du fichier dans la requête.
	 */
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "Default.file";
	}

}
