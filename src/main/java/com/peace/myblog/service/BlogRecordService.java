package com.peace.myblog.service;

import com.peace.myblog.daoObject.BlogRecord;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 11:45
 */
public interface BlogRecordService {

    /**
     * 保存一个记录
     * @param record
     * @return
     */
    BlogRecord saveRecord(BlogRecord record);


    /**
     * 根据 id 获取一个记录
     * @param recordId
     * @return
     */
    BlogRecord getRecord(Long recordId);

    /**
     * 根据 id 更新一个记录
     * @param record
     * @return
     */
    BlogRecord updateRecord(BlogRecord record);

    /**
     * 根据 id 删除一个记录
     * @param recordId
     */
    void deleteRecord(Long recordId);

    /**
     * 获取所有记录，用于分页展示
     * @return
     */
    List<BlogRecord> getAllRecord();

    /**
     * 根据名称获取记录
     * @param name
     * @return
     */
    BlogRecord getRecordByName(String name);


}
