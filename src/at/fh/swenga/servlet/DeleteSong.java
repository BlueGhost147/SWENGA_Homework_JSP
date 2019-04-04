package at.fh.swenga.servlet;

import java.io.IOException;
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
 * Servlet implementation class DeleteSong
 */
@WebServlet("/deleteSong")
public class DeleteSong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSong() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		SongService songService = (SongService) session.getAttribute("songService");

		if (songService == null) {
			songService = new SongService();
			session.setAttribute("songService", songService);
			
			request.setAttribute("errorMessage", "Error - Session timed out!");
		}
		else
		{
			request.setAttribute("songs", songService.getAllSongs());
			String idString = request.getParameter("id");
			
			try 
			{
				int id = Integer.parseInt(idString);
				songService.remove(id);
				request.setAttribute("warningMessage", "Delete song "+id);
			}
			/*
			catch(ClassCastException cce)
			{
				request.setAttribute("errorMessage", "Error - Could not delete song");
			}
			*/
			catch(Exception e)
			{
				request.setAttribute("errorMessage", "Something went wrong!");
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("listSongs");
		rd.forward(request, response);
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
