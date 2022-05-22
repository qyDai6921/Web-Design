import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.StudentBean;
import logic.StudentDAO;

@WebServlet(name = "ProcessServlet", urlPatterns = { "/ProcessServlet" })
/**
 * Servlet implementation class ProcessServlet
 */
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<String> parts;
	private static List<String> idParts;
	private static List<String> studentParts;
	private static List<List<String>> masterList;
	private static HttpSession session;
	private Logger logger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessServlet() {
		super();
		// TODO Auto-generated constructor stub
		parts = new ArrayList<String>();
		idParts = new ArrayList<String>();
		session = null;
		logger = Logger.getLogger(ProcessServlet.class.getName());
		masterList = new ArrayList<List<String>>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// set response content type
		response.setContentType("text/html");
		session = request.getSession(false);

		parts.add(request.getParameter("user_choice"));
		masterList = StudentDAO.getData(request.getParameter("user_choice"));
				
		if(masterList.isEmpty())
		{
			logger.severe("Error: MasterList is empty, DB exception returned!");
			studentParts = new ArrayList<String>();
			idParts = new ArrayList<String>();
			logger.info("No student record returned due to error!");
			logger.info("No student IDs returned due to error!");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
		else
		{
			studentParts = masterList.get(0);
			if(!studentParts.isEmpty())
			{
				logger.info("Found student record!");
				StudentBean beanStudent = new StudentBean();
				beanStudent.setStudentID(studentParts.get(0));
				beanStudent.setName(studentParts.get(1));
				beanStudent.setAddress(studentParts.get(2));
				beanStudent.setCity(studentParts.get(3));
				beanStudent.setState(studentParts.get(4));
				beanStudent.setZipcode(studentParts.get(5));
				beanStudent.setTelephone(studentParts.get(6));
				beanStudent.setEmail(studentParts.get(7));
				beanStudent.setURL(studentParts.get(8));
				beanStudent.setInterestedArea(studentParts.get(9));
				beanStudent.sethighestDiff(studentParts.get(10));
				beanStudent.setDOS(studentParts.get(11));
//				beanStudent.setDOS(studentParts.get(9));
				session.setAttribute("studentbean", beanStudent);
				
				request.getRequestDispatcher("Student.jsp").forward(request, response);
			}
			else
			{
				logger.info("No student record!");
				request.getRequestDispatcher("NoSuchStudent.jsp").forward(request, response);
			}
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// set response content type
		response.setContentType("text/html");
		session = request.getSession();

		parts.add(request.getParameter("student_id"));
		parts.add(request.getParameter("name"));
		parts.add(request.getParameter("street_address"));
		parts.add(request.getParameter("city"));
		parts.add(request.getParameter("state"));
		parts.add(request.getParameter("zip"));
		parts.add(request.getParameter("telephone_number"));
		parts.add(request.getParameter("e_mail"));
		parts.add(request.getParameter("url"));
		parts.add(request.getParameter("InterestedArea"));
		parts.add(request.getParameter("highestDiff"));
		parts.add(request.getParameter("date_of_suvery"));

		masterList = StudentDAO.getData(request.getParameter("student_id"));
		
		if(masterList.isEmpty())
		{
			logger.severe("Error: MasterList is empty, DB exception returned!");
			studentParts = new ArrayList<String>();
			idParts = new ArrayList<String>();
			logger.info("No student record returned due to error!");
			logger.info("No student IDs returned due to error!");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
		else
		{
			studentParts = masterList.get(0);
			if(!studentParts.isEmpty())
			{
				logger.info("Student record already exist!");
				logger.info(studentParts.get(0).toString());
				idParts = masterList.get(1);
				if(idParts.isEmpty()) logger.severe("ID List is empty!");
				session.setAttribute("studentids", idParts);
			}
			else
			{
				logger.info("No student record!");
				idParts = StudentDAO.saveData(parts);
				session.setAttribute("studentids", idParts);
			}
		}
		
		

		
		logger.info("Dispatching!");
		request.getRequestDispatcher("SimpleAcknowledgement.jsp").forward(request, response);
	}

}
