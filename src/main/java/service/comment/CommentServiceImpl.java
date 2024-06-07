package service.comment;

import base.service.BaseServiceImpl;
import entity.Comment;
import org.hibernate.SessionFactory;
import repository.comment.CommentRepository;

public class CommentServiceImpl extends BaseServiceImpl<Comment,Long, CommentRepository>implements CommentService {
    private final CommentRepository commentRepository;
    private final SessionFactory sessionFactory;

    public CommentServiceImpl(CommentRepository commentRepository , SessionFactory sessionFactory){
        super(commentRepository, sessionFactory);
        this.commentRepository = commentRepository;
        this.sessionFactory = sessionFactory;
    }
}
