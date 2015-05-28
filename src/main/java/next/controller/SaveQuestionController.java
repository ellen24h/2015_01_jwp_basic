package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class SaveQuestionController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(SaveQuestionController.class);
		
	private QuestionDao questionDao = new QuestionDao();
	private Question question;
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		question = new Question(
				request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("contents"));
		
		questionDao.insert(question);
		
		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}
}
