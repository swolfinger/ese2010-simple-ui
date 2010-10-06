package controllers;

import java.util.List;

import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

import models.Question;
import models.User;

public class Application extends Controller {

	@Before
	static void addDefaults() {
		renderArgs.put("blogTitle", Play.configuration
				.getProperty("blog.title"));
		renderArgs.put("blogBaseline", Play.configuration
				.getProperty("blog.baseline"));
	}

	public static void index() {
		Question frontPost = Question.find("order by timestamp desc").first();
		List<Question> olderPosts = Question.find("order by timestamp desc")
				.from(1).fetch(10);
		render(frontPost, olderPosts);
	}
	
	public static void show(Long id) {
	    Question post = Question.findById(id);
	    render(post);
	}

	
	public static void postComment(Long postId, String author, String content) {
	    Question post = Question.findById(postId);
	    User user = User.find("byFullname", author).first();
	    show(postId);
	}


}