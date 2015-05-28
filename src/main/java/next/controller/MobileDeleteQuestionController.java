package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;

public class MobileDeleteQuestionController extends AbstractController {
private static final Logger logger = LoggerFactory.getLogger(DeleteQuestionController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		Question question;
		AnswerDao answerDao = new AnswerDao();
		List<Answer> answers;

		long questionId = Integer.parseInt(request.getParameter("questionId"));
		question = questionDao.findById(questionId);
		String questionWriter = question.getWriter();
		
		answers = answerDao.findAllByQuestionId(questionId);
		
		ModelAndView mav = jsonView();

		
		if (answers.size() != 0) {
			for (int i = 0; i < answers.size(); i++) {
				if (answers.get(i).getWriter() != questionWriter) {
					mav.addObject("result", Result.fail("답변이 있어서 삭제할 수 없어요 :("));
					return mav;
				}
			}
		}

		questionDao.delete(questionId);
		
		mav.addObject("result", Result.ok());
		return mav;
	}
}
