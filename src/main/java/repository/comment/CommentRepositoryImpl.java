package repository.comment;

import base.repository.BaseRepositoryImpl;
import entity.Comment;
import org.hibernate.SessionFactory;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long>
        implements CommentRepository{
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public String getEntity() {
        return "Comment";
    }
}
