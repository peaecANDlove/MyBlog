package com.peace.myblog.dto;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-08-22 16:24
 */
@Data
public class MyPageInfo {

    private int pageNum;      //当前页的页码
    private int pageSize;      //每页的数量
    private int size;            //当前页的数量

    private int startRow; //当前页面第一个元素在数据库中的行号
    private int endRow;//当前页面最后一个元素在数据库中的行号

    private long total;     //总记录数
    private int pages;       //总页数

    private int prePage;    //上一页
    private int nextPage;   //下一页

    private boolean isFirstPage;   //是否为第一页
    private boolean isLastPage;   //是否为最后一页
    private boolean hasPreviousPage;       //是否有前一页
    private boolean hasNextPage;          //是否有下一页
}
