package com.peace.myblog.service;

import com.peace.myblog.dto.ArchiveInfo;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-14 21:05
 */
public interface ArchiveService {

    /**
     * 获得归档信息
     * @return
     */
    List<ArchiveInfo> findArchiveNameAndArticleNum();

    /**
     * 添加归档日期
     * @param archiveName
     */
    void addArchiveName(String archiveName);
}
