package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AnswerController;
import next.controller.DeleteAnswerController;
import next.controller.DeleteQuestionController;
import next.controller.EditQuestionController;
import next.controller.ListController;
import next.controller.MobileController;
import next.controller.MobileDeleteQuestionController;
import next.controller.SaveQuestionController;
import next.controller.ShowController;
import next.controller.UpdateController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/save.next", new SaveQuestionController());
		mappings.put("/api/addanswer.next", new AnswerController());
		mappings.put("/deleteanswer.next", new DeleteAnswerController()); 
		mappings.put("/api/list.next", new MobileController());
		mappings.put("/updateForm.next", new UpdateController());
		mappings.put("/editQuestion.next", new EditQuestionController());
		mappings.put("/delete.next", new DeleteQuestionController());
		mappings.put("/api/delete.next", new MobileDeleteQuestionController());
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
