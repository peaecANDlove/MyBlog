package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.BlogRecord;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.BlogRecordMapper;
import com.peace.myblog.service.BlogRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 11:52
 */
@Service
public class BlogRecordServiceImpl implements BlogRecordService {


    @Autowired
    private BlogRecordMapper blogRecordMapper;

    @Override
    public BlogRecord saveRecord(BlogRecord record) {
        record.setCreateTime(new Date());
        blogRecordMapper.saveRecord(record);
        return getRecord(record.getId());
    }

    @Override
    @Transactional
    public BlogRecord getRecord(Long recordId) {
        return blogRecordMapper.getRecord(recordId);
    }

    @Override
    @Transactional
    public BlogRecord updateRecord(BlogRecord record) {
        BlogRecord blogRecord = getRecord(record.getId());
        if (blogRecord == null) {
            throw new MeNotFoundException("记录不存在");
        }

        BeanUtils.copyProperties(record, blogRecord);
        blogRecordMapper.updateRecord(record);

        return getRecord(record.getId());
    }

    @Override
    @Transactional
    public void deleteRecord(Long recordId) {
        blogRecordMapper.deleteRecord(recordId);
    }

    @Override
    @Transactional
    public List<BlogRecord> getAllRecord() {
        return blogRecordMapper.getAllBlogRecord();
    }

    @Override
    @Transactional
    public BlogRecord getRecordByName(String name) {
        return blogRecordMapper.getBlogRecordByName(name);
    }
}
