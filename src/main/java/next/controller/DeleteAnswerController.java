package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteAnswerController.class);


	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("good");
		AnswerDao answerdao = new AnswerDao();

		long questionId = Integer.parseInt(request.getParameter("questionId"));
		long answerId = Integer.parseInt(request.getParameter("answerId"));
		
		answerdao.delete(answerId, questionId);
		
		ModelAndView mav = jstlView("redirect:/");

		return mav;
	}

}
