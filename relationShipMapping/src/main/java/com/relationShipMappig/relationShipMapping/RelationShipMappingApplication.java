package com.relationShipMappig.relationShipMapping;

import com.relationShipMappig.relationShipMapping.model.Comment;
import com.relationShipMappig.relationShipMapping.model.Post;
import com.relationShipMappig.relationShipMapping.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RelationShipMappingApplication {
    //implements CommandLineRunner
    public static void main(String[] args) {
        SpringApplication.run(RelationShipMappingApplication.class, args);
    }


    @Autowired
    private PostRepository postRepository;
/*
	@Override
	public void run(String...args)throws Exception{
		Post post = new Post();

		Comment comment1 = new Comment("very god !");
		Comment comment2 = new Comment("Not very god !");
		Comment comment3 = new Comment("So Much very god !");
		Comment comment4 = new Comment("very god !");
		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);
		post.getComments().add(comment4);
		this.postRepository.save(post);
	}*/

}
