package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class AnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	
	private AnswerDao answerDao = new AnswerDao();
	private Answer answer;
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		answer = new Answer(
				request.getParameter("writer"),
				request.getParameter("contents"),
				Integer.parseInt(request.getParameter("questionId")));
		
		answerDao.insert(answer);
		
		ModelAndView mav = jstlView("redirect:/");
		return mav;
	}
}
