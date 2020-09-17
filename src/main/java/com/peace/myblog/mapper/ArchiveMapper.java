package com.peace.myblog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-14 21:02
 */
@Repository
public interface ArchiveMapper {

    @Select("select archive_name from t_archive order by id desc")
    List<String> findArchives();

    @Insert("insert into t_archive(archive_name) values(#{archiveName})")
    void save(@Param("archiveName") String archiveName);

    @Select("select IFNULL(max(id),0) from t_archive where archive_name=#{archiveName}")
    int findArchiveNameByArchiveName(@Param("archiveName") String archiveName);
}
