package com.example.breno.blog.repositories;

import com.example.breno.blog.models.PostModel;
import com.example.breno.blog.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {

    public List<PostModel> findByAuthorOrderByCreationDateDesc(UserModel author);
}
