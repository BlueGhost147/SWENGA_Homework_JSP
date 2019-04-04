package at.fh.swenga.servlet;

import java.io.IOException;

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
 * Servlet implementation class EditSong
 */
@WebServlet("/editSong")
public class EditSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSong() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");

		int id = Integer.parseInt(idString);

		HttpSession session = request.getSession(true);

		SongService songService = (SongService) session.getAttribute("songService");

		if (songService == null) {
			songService = new SongService();
			session.setAttribute("songService", songService);
			
			request.setAttribute("errorMessage", "Error session timed out!");
			RequestDispatcher rd = request.getRequestDispatcher("./listSongs");
			rd.forward(request, response);
			return;
		}
		else {
			SongModel songModel = songService.getSongById(id);
			if (songModel != null) {
				request.setAttribute("song", songModel);
				RequestDispatcher rd = request.getRequestDispatcher("./editSong.jsp");
				rd.forward(request, response);
				return;
			} else {
				request.setAttribute("errorMessage", "No song with this id " + id);
				RequestDispatcher rd = request.getRequestDispatcher("./listSongs");
				rd.forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
