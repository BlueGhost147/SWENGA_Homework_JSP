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
 * Servlet implementation class FillSongList
 */
@WebServlet("/fillSongList")
public class FillSongList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillSongList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		SongService songService = (SongService) session.getAttribute("songService");
		if (songService == null) {
			songService = new SongService();	
		}
		
		int idInc = 100;
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Under Pressure","Queen","Hot Space",new Date()));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Man On The Moon","R.E.M.","Automatic For The People",new Date()));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"King for a Day","Battle Beast","Bringer of Pain",new Date()));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"Hammer High","Hammerfall","Built To Last",new Date()));
		songService.remove(idInc);
		songService.addSong(new SongModel(idInc++,"F.T.L.","Keldian","Outbound",new Date()));
		
		session.setAttribute("songService", songService);
		
		RequestDispatcher rd = request.getRequestDispatcher("./listSongs");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
