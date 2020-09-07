package com.peace.myblog.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author YR#
 * @create 2020-08-25 16:50
 */
@Repository
public interface CommentMapper {

    @Select("select count(1) from t_comment")
    Long countComment();
}
