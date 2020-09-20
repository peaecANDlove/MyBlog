

fillBlogDetail();

//构建博客体
function makeBlogDetail(data) {

    var blogDetail = $('.data-blog');
    blogDetail.empty();

    var blogDetailData = data['data'];

    var tagLine ='';
    $.each(blogDetailData['tags'], function (index, tag) {
       tagLine+='<a href="#" class="ui teal tag  label">'+tag['tagName']+'</a>';
    });

    var blogDetailBody = '<div class="data-blog">\n' +
        '    <!-- 文章标题 -->\n' +
        '    <div id="article-header">\n' +
        '        <div class="ui center aligned container">\n' +
        '            <h1 class="m-text-middle"  >'+blogDetailData['title']+'</h1>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- 分类，时间，作者，文件夹 -->\n' +
        '    <div id="article-sort " class="m-margin-bt-large">\n' +
        '        <div class="ui center aligned container">\n' +
        '            <div class="ui horizontal link list ">\n' +
        '\n' +
        '                <!-- 创作类型 -->\n' +
        '                <div class="item" >\n' +
        '                    <div class="m-margin-bt-small ">\n' +
        '                        <span class="ui  green label m-padd-mini" style="margin-bottom: 1px">'+blogDetailData['markFlag']+'</span>\n' +
        '                    </div>\n' +
        '\n' +
        '                </div>\n' +
        '\n' +
        '                <!-- 创作时间 -->\n' +
        '                <div class="item" >\n' +
        '                    <div class="create-time">\n' +
        '                        <i class="  calendar alternate icon m-i-remark m-create-time-color m-margin-zero-right m-padding-right-zero"></i>\n' +
        '                        <a href="#" class="m-font-size-mini m-a-remark m-time-color" >'+formatDate(blogDetailData['createTime'], 'YY/MM/DD')+'</a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '                <!-- 作者 -->\n' +
        '                <div class="item ">\n' +
        '                    <div class="author ">\n' +
        '                        <i class=" user icon m-i-remark m-padding-right-zero m-margin-zero-right" ></i>\n' +
        '                        <span >'+blogDetailData['authorName']+'</span>\n' +
        '                    </div>\n' +
        '\n' +
        '                </div>\n' +
        '\n' +
        '                <!-- 文件目录 -->\n' +
        '                <div class="item">\n' +
        '                    <div class="contents">\n' +
        '                        <i class="folder icon m-i-remark m-padding-right-zero m-margin-zero-right "></i>\n' +
        '                        <span>'+blogDetailData['category']['name']+'</span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '                <!-- 浏览次数 -->\n' +
        '                <div class="item">\n' +
        '                    <div class="skim">\n' +
        '                        <i class="eye icon m-i-remark m-padding-right-zero m-margin-zero-right"></i>\n' +
        '                        <span>'+blogDetailData['views']+'</span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '            </div>\n' +
        '        </div>\n' +
        '\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- 文章开篇图片 -->\n' +
        '    <div id="article-first-photo">\n' +
        '        <img src="'+blogDetailData['firstPicture']+' "  alt="" class="ui centered  rounded  image" style="height: 500px; width: 700px;">\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- 文章内容 -->\n' +
        '    <div id="acticle-content" class="m-padd-rf-small-reponsive m-margin-bt-large m-padd-foot typo typo-selection js-toc-content">\n' +
        '\n' +
        '\n' +
        '       '+blogDetailData['content']+'\n' +
        '\n' +
        '\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- ending 图标 -->\n' +
        '    <div id="article-ending" class="m-margin-bt-large">\n' +
        '        <div class="ui center aligned container">\n' +
        '            <img src="/images/ending.png"  alt="" class="ui small centered image">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '    <div class="ui divider"></div>\n' +
        '\n' +
        '    <!-- 赞赏 -->\n' +
        '    <div id="article-admired" class="m-margin-bt">\n' +
        '\n' +
        '        <!-- 文字说明 -->\n' +
        '        <div class="ui center aligned container m-padding-top" id="admired-word">\n' +
        '            <span class="m-inlie-block">“觉得文章很棒，就鼓励一下作者吧！”</span>\n' +
        '        </div>\n' +
        '\n' +
        '        <!-- 赞赏图标 -->\n' +
        '        <div class="ui center aligned basic segment m-padd-foot-mini" id="admire-icon">\n' +
        '            <button id="payButton" class="ui large orange basic circular button">\n' +
        '                <i class="thumbs up icon"></i>赞赏\n' +
        '            </button>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- 打赏二维码 -->\n' +
        '    <div class="ui payQR flowing popup  transition hidden">\n' +
        '        <div class="ui orange basic label" id="admired-method">\n' +
        '            <div class="ui images" style="font-size: inherit !important;">\n' +
        '                <div class="image">\n' +
        '                    <img src="/images/weChat.png"  alt="" class="ui rounded bordered image">\n' +
        '                    <p>支付宝打赏</p>\n' +
        '                </div>\n' +
        '                <div class="image">\n' +
        '                    <img src="/images/weChat.png"  alt="" class="ui rounded bordered image">\n' +
        '                    <p>微信打赏</p>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '    <!-- 公众号同步 -->\n' +
        '    <div id="weChat-public" class="m-margin-bt-big" >\n' +
        '        <div class="m-container-mini ">\n' +
        '            <div class="ui center secondary segment">\n' +
        '\n' +
        '                <img src="/images/weChat-public.jpg"  alt="" class="ui small centered rounded image ">\n' +
        '\n' +
        '                <div class="ui center aligned container">\n' +
        '                    <h3 >关注我的公众号：生活要有诗意，可同步查阅文章</h3>\n' +
        '                </div>\n' +
        '\n' +
        '\n' +
        '            </div>\n' +
        '        </div>\n' +
        '\n' +
        '    </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '\n' +
        '    <!-- 博客信息介绍 -->\n' +
        '    <div id="article-info" class="m-margin-bt-tiny">\n' +
        '\n' +
        '        <ul class="line m-margin-top-zero">\n' +
        '            <li>\n' +
        '                <span>文章作者：</span>\n' +
        '                <span>'+blogDetailData['authorName']+'</span>\n' +
        '            </li>\n' +
        '            <li>\n' +
        '                <span>原文链接：</span>\n' +
        '                <span> 余睿哲</span>\n' +
        '            </li>\n' +
        '            <li>\n' +
        '                <span>版权声明：</span>\n' +
        '                <span> 余睿哲</span>\n' +
        '            </li>\n' +
        '        </ul>\n' +
        '\n' +
        '\n' +
        '    </div>\n' +
        '\n' +
        '    <!-- 文章标签  -->\n' +
        '    <div id="article-tag">\n' +
        '        '+tagLine+
        '    </div>\n' +
        '\n' +
        '    <div class="ui divider"></div>\n' +
        '\n' +
        '    <!-- 底部换页区域 -->\n' +
        '    <div id="next-pre-page">\n' +
        '\n' +
        '\n' +
        '        <div class=" m-margin-rf">\n' +
        '\n' +
        '            <div class="ui grid">\n' +
        '\n' +
        '                <!-- 上一页 -->\n' +
        '                <div class="left floated left aligned six wide column">\n' +
        '                    <div class="left floated column pre" style="display: inline-block !important;">\n' +
        '                        <a href="#">上一篇文章名</a>\n' +
        '                        <i class="hand point left outline icon"></i>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '                <div class="right floated right aligned six wide column">\n' +
        '                    <div class="right floated column next " style="display: inline-block;">\n' +
        '                        <i class="hand point right outline icon"></i>\n' +
        '                        <a href="#">下一篇文章名</a>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '            </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '        </div>\n' +
        '\n' +
        '\n' +
        '    </div>\n' +
        '</div>';
    blogDetail.append(blogDetailBody);
}


function fillBlogDetail() {

    var url = location.href;
    var id = url.split("/");
    var articleId = id[5];
    console.log(location.href);

    $.ajax({
            type: 'post',
            url: '/user/getBlogDetail/'+articleId,
            dataType: 'json',
            success: function (data) {
                if (data['status'] === 'success') {
                    makeBlogDetail(data);
                } else {
                    alert('博客详情数据请求错误');
                }
            },
            error: function () {
                alert("博客详情错误");
            }
        });
}

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
