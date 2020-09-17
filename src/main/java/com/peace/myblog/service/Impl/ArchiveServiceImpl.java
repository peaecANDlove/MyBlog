package com.peace.myblog.service.Impl;

import com.peace.myblog.dto.ArchiveInfo;
import com.peace.myblog.mapper.ArchiveMapper;
import com.peace.myblog.service.ArchiveService;
import com.peace.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YR#
 * @create 2020-09-14 21:06
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveMapper archiveMapper;

    @Autowired
    private BlogService blogService;


    @Override
    public List<ArchiveInfo> findArchiveNameAndArticleNum() {
       List<String> archive = archiveMapper.findArchives();
       List<ArchiveInfo> archiveInfos = new ArrayList<>();

       if (archive == null) return null;

       for (String publishDate: archive) {
           ArchiveInfo archiveInfo = new ArchiveInfo();
           archiveInfo.setArchiveName(publishDate);
           Integer numberOfBlogs = blogService.getBlogsByArchive(publishDate);
           if (numberOfBlogs != 0) {
               archiveInfo.setNumberOfBlog(numberOfBlogs);
               archiveInfos.add(archiveInfo);
           }

       }

       return archiveInfos;

    }

    @Override
    public void addArchiveName(String archiveName) {
        int archiveNameIsExist = archiveMapper.findArchiveNameByArchiveName(archiveName);
        if(archiveNameIsExist == 0){
            archiveMapper.save(archiveName);
        }
    }
}
