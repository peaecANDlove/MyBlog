
//网站开始时间
var BeginTime = '2020-08-19 20:00:00';

fillArticleInfo(1);

fillTagCloud();

fillRecommendBlog();

fillWebsiteInfo();


// 构建简略文章
function makeArticleInfo(data) {
    var articleInfo = $('.articleInfo');
    articleInfo.empty();
    var articleInfos =  data['data']['articleInfos'];
    $.each(articleInfos, function getData(index, articleInfo) {

        //获取底部标签列表
        var tags = articleInfo['tagNames'].split(',');
        var bottomTagLine = '';
        $.each(tags, function (index, tagName) {
            //todo
            bottomTagLine +='<a href="/tag/?tagName="'+tagName+'><i class="grey tag icon" ></i><span>'+tagName+'</span></a>'
        });

        //文章缩略主体
        var articleInfoBody = '<div class="ui raised segments article-content-segment" >\n' +
            '\n' +
            '                            <!-- 文章块部分 -->\n' +
            '                            <div class="ui raised segment">\n' +
            '                                <div class="ui middle aligned grid">\n' +
            '\n' +
            '                                    <!-- 每篇内容头部 -->\n' +
            '                                    <div class="eleven wide column">\n' +
            '\n' +
            '                                        <!-- 内容标题 -->\n' +
            '                                        <h4 class="m-text-middle  m-font-size-big m-margin-bt-mini">\n' +
            '                                            <a class="m-text-color" href="/user/getBlogDetail/'+articleInfo['id']+'" >'+articleInfo['title']+'</a>\n' +
            '                                        </h4>\n' +
            '\n' +
            '                                        <!-- 文章创作类型，作者，分类 -->\n' +
            '                                        <div class="ui horizontal link list ">\n' +
            '\n' +
            '                                            <!-- 创作类型 -->\n' +
            '                                            <div class="item" >\n' +
            '                                                <div class="m-margin-bt-small ">\n' +
            '                                                    <span class="ui  green label m-padd-mini type-bottom" >'+articleInfo['markFlag']+'</span>\n' +
            '                                                </div>\n' +
            '\n' +
            '                                            </div>\n' +
            '\n' +
            '                                            <!-- 创作时间 -->\n' +
            '                                            <div class="item" >\n' +
            '                                                <div class="create-time">\n' +
            '                                                    <i class="  calendar alternate icon m-i-remark m-create-time-color m-margin-zero-right m-padding-right-zero"></i>\n' +
            '                                                    <a href="/user/time/'+formatDate(articleInfo['createTime'],"YY-MM-DD")+'" class="m-font-size-mini m-a-remark m-time-color" >'+formatDate(articleInfo['createTime'],"YY-MM-DD")+'</a>\n' +
            '                                                </div>\n' +
            '                                            </div>\n' +
            '\n' +
            '                                            <!-- 作者 -->\n' +
            '                                            <div class="item ">\n' +
            '                                                <div class="author ">\n' +
            '                                                    <i class=" user icon m-i-remark m-padding-right-zero m-margin-zero-right" ></i>\n' +
            '                                                    <span >'+articleInfo['authorName']+'</span>\n' +
            '                                                </div>\n' +
            '\n' +
            '                                            </div>\n' +
            '\n' +
            '                                            <!-- 文件目录 -->\n' +
            '                                            <div class="item">\n' +
            '                                                <div class="contents">\n' +
            '                                                    <i class="folder icon m-i-remark m-padding-right-zero m-margin-zero-right "></i>\n' +
            '                                                    <a href="/user/categoryName/'+articleInfo['category']+'" class="m-font-size-mini m-a-remark m-time-color">'+articleInfo['category']+'</a>\n' +
            '                                                </div>\n' +
            '                                            </div>\n' +
            '\n' +
            '                                            <!-- 浏览次数 -->\n' +
            '                                            <div class="item">\n' +
            '                                                <div class="skim">\n' +
            '                                                    <i class="eye icon m-i-remark m-padding-right-zero m-margin-zero-right"></i>\n' +
            '                                                    <span>'+articleInfo['views']+'</span>\n' +
            '                                                </div>\n' +
            '                                            </div>\n' +
            '\n' +
            '                                        </div>\n' +
            '\n' +
            '\n' +
            '                                        <!-- 内容简介 -->\n' +
            '                                        <div class="ui container">\n' +
            '                                            <div class="summarized m-padd-foot-tiny">\n' +
            '                                                <p class="m-text-second-p ">'+articleInfo['description']+'</p>\n' +
            '                                            </div>\n' +
            '\n' +
            '                                        </div>\n' +
            '\n' +
            '                                        <!-- 阅读链接 -->\n' +
            '                                        <div class="left aligned five column">\n' +
            '                                            <div class="m-article-link">\n' +
                                                            //todo
            '                                                <a href="/user/getBlogDetail/'+articleInfo['id']+'" >\n' +
            '                                                    阅读全文\n' +
            '                                                </a>\n' +
            '                                                <i class="hand point left outline icon"></i>\n' +
            '                                            </div>\n' +
            '\n' +
            '                                        </div>\n' +
            '\n' +
            '                                    </div>\n' +
            '\n' +
            '                                    <!-- 内容插图 -->\n' +
            '                                    <div class="five wide column " >\n' +
            '                                        <a href="#" target="_blank" >\n' +
            '                                            <img class="ui  rounded image" src="https://picsum.photos/id/1/1000/550?image=1051" alt="" >\n' +
            '                                        </a>\n' +
            '                                    </div>\n' +
            '\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '\n' +
            '\n' +
            '\n' +
            '                            <!-- 底部分类标签 -->\n' +
            '                            <div class="ui raised segment article-footer-segment">\n' +
            '                                <div class="m-tag-list">\n' +
            '                                        '+bottomTagLine+' '+
            '                                </div>\n' +
            '\n' +
            '                            </div>\n' +
            '                        </div>';

        $('.articleInfo').append(articleInfoBody);


    });
    
}



// 构建标签云数据获取
function fillTagCloud() {

    $.ajax({
        type: 'get',
        url: '/user/tagCloud',
        dataType: 'json',
        success: function (data) {
            if (data['status'] === 'success') {
                $('.tagCloud').empty();

                $.each(data['data'],function (index, tag) {
                    var item = '<a href="/tag?tag='+tag['tagName']+'" style="font-size:'+tag['tagSize']+'px">'+tag['tagName']+'</a>';
                    $(".tagCloud").append(item)
                });

            } else {
                alert("标签云数据请求错误");
            }

        },


        error: function () {
            alert("标签云出现错误")
        }

    });

}


// 简略文章数据获取
function fillArticleInfo(currentPage) {

    $.ajax({
        type: 'post',
        url : '/user/articleInfos',
        dataType: 'json',
        data: {
            size:"3",
            pageNum:currentPage
        },
        success: function (data) {
            var single = data['status'];
            if (single === "success") {

                scrollTo(0,0);
                makeArticleInfo(data);

                var pageInfo = data['data']['myPageInfo'];

                //分页显示
                $("#pagination").paging({
                    rows: pageInfo['pageSize'],//每页显示条数
                    pageNum: pageInfo['pageNum'],//当前所在页码
                    pages: pageInfo['pages'],//总页数
                    total: pageInfo['total'],//总记录数
                    callback:function(currentPage){
                        fillArticleInfo(currentPage);
                    }
                });


            } else {
                alert("请求有误");
            }
        },
        error: function () {
            alert("文章缩略发生错误");
        }
    });
    
}

//构建推荐文章块
function makeRecommendBlog(data) {
    var recommend = $('.recommend-blog');
    recommend.empty();
    var recommendBlogs = data['data'];
    var recommendHeader = '<div class="ui segment m-padd-foot-small">\n' +
        '                            <div class=" m-padd-mini">\n' +
        '                                <i class="yellow edit icon"></i>\n' +
        '                                <span>推荐博客</span>\n' +
        '                            </div>\n' +
        '                        </div>';

    recommend.append(recommendHeader);
    $.each(recommendBlogs, function (index, blog) {

        var recomendBody = '<div class="m-padd-rf-large m-padd-foot-small " style="background:#fffef9;">\n' +
            //   todo
            '   <a href="/user/getBlogDetail/'+blog['id']+'" class="m-color ">'+blog['title']+'</a>\n' +
            '    </div>';
        recommend.append(recomendBody);

    });
}

//推荐文章
function fillRecommendBlog() {

    $.ajax({
         type: 'get',
         url: '/user/recommendBlog',
         dataType: 'json',
         success: function (data) {
            if (data['status'] === 'success'){
                makeRecommendBlog(data);
            } else {
                alert("推荐文章数据请求错误");
            }
         },
         error: function () {
             alert("推荐文章错误");
         }
    });

}

//构建网站信息块
function makeWebsiteInfo(data) {
    var website = $('.website-info');
    website.empty();
    var websiteInfo = data['data'];

    var websiteBody = '<div class="ui segment m-padd-foot-small">\n' +
        '                            <div class=" m-padd-mini">\n' +
        '                               <i class="green info icon"></i>\n' +
        '                                <span>网站信息</span>\n'+
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <!-- 各个条目 -->\n' +
        '                        <div style="background:#fafff8; margin-left: 10px; padding-top: 10px; padding-bottom: 10px" >\n' +
        '                            <div class="articles m-padd-mini">\n' +
        '                                <i class="file alternate icon"></i>\n' +
        '                                <span>文章总数：</span>\n' +
        '                                <span>'+websiteInfo['totalBlog']+'篇</span>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <div class="tag-counts m-padd-mini">\n' +
        '                                <i class="tag icon"></i>\n' +
        '                                <span>标签总数：</span>\n' +
        '                                <span>'+websiteInfo['totalTag']+'个</span>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <div class="category-counts m-padd-mini">\n' +
        '                                <i class="folder outline icon"></i>\n' +
        '                                <span>分类总数：</span>\n' +
        '                                <span>'+websiteInfo['totalCategory']+'条</span>\n' +
        '                            </div>\n' +
        '                            <div class="discuss m-padd-mini">\n' +
        '                                <i class=" comment outline icon"></i>\n' +
        '                                <span>评论总数：</span>\n' +
        '                                <span>'+websiteInfo['totalComment']+'条</span>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <div class="least-update-time m-padd-mini">\n' +
        '                                <i class=" edit icon"></i>\n' +
        '                                <span>网站最近更新时间：</span>\n' +
        '                                <span>'+formatDate(websiteInfo['websiteUpdateTime'], "YY/MM/DD")+'</span>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <div class="working-time m-padd-mini">\n' +
        '                                <i class=" cloud icon"></i>\n' +
        '                                <span>网站运行时间：</span>\n' +
        '                                <span class="siteRunningTime"></span>\n' +
        '                            </div>\n' +
        '                        </div>';
    website.append(websiteBody);

}

//网站信息
function fillWebsiteInfo() {

    $.ajax({
        type: 'get',
        url: '/user/websiteInfo',
        dataType: 'json',
        success: function (data) {
            if (data['status'] === 'success') {
                makeWebsiteInfo(data);
            }  else {
                alert("网站信息获取有误");
            }

        },
        error: function () {
            alert("网站信息错误");
        }
    });
}

// 时间格式转换
function formatDate(time,format){
    var date = new Date(time);

    var year = date.getFullYear(),
        month = date.getMonth()+1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var preArr = Array.apply(null,Array(10)).map(function(elem, index) {
        return '0'+index;
    });

    var newTime = format.replace(/YY/g,year)
        .replace(/MM/g,preArr[month]||month)
        .replace(/DD/g,preArr[day]||day)
        .replace(/hh/g,preArr[hour]||hour)
        .replace(/mm/g,preArr[min]||min)
        .replace(/ss/g,preArr[sec]||sec);

    return newTime;
}

// 网站运行时间
function siteRunningTime(time) {
    var theTime;
    var strTime = "";
    if (time >= 86400){
        theTime = parseInt(time/86400);
        strTime += theTime + "天";
        time -= theTime*86400;
    }
    if (time >= 3600){
        theTime = parseInt(time/3600);
        strTime += theTime + "时";
        time -= theTime*3600;
    }
    if (time >= 60){
        theTime = parseInt(time/60);
        strTime += theTime + "分";
        time -= theTime*60;
    }
    strTime += time + "秒";

    $('.siteRunningTime').html(strTime);
}

var nowDate = new Date().getTime();
//网站开始运行日期
var oldDate = new Date(BeginTime.replace(/-/g,'/'));
var time = oldDate.getTime();
var theTime = parseInt((nowDate-time)/1000);
setInterval(function () {
    siteRunningTime(theTime);
    theTime++;
},1000);