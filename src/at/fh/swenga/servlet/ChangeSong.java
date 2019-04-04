package at.fh.swenga.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.SongModel;
import at.fh.swenga.model.SongService;

/**
 * Servlet implementation class ChangeSong
 */
@WebServlet("/changeSong")
public class ChangeSong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeSong() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		String songName = request.getParameter("songName");
		String artist = request.getParameter("artist");
		String album = request.getParameter("album");
		String releaseDateString = request.getParameter("releaseDate");

		String errorMessage = "";
		boolean errorOccurred = false;

		int id = 0;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			errorMessage += "Id invalid<br>";
			errorOccurred = true;
		}

		Date releaseDate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			releaseDate = sdf.parse(releaseDateString);
		} catch (Exception e) {
			errorMessage += "Release date invalid<br>";
			errorOccurred = true;
		}
		
		if (!errorOccurred) {
			 
			HttpSession session = request.getSession(true);
 
			SongService songService = (SongService) session.getAttribute("songService");
			if (songService==null) {
				songService=new SongService();
				session.setAttribute("songService", songService);
			}
 
			boolean songExisted = songService.remove(id);
 
			if (songExisted) {
				songService.addSong(new SongModel(id, songName, artist, album, releaseDate));
			} else {
				errorMessage += "Song didn´t exist!<br>";
				errorOccurred = true;
			}
		}
		
		if (!errorOccurred) {
			request.setAttribute("message", "Changed song " + id + ".");
		}
		else {
			request.setAttribute("errorMessage", errorMessage);
 
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./listSongs");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
