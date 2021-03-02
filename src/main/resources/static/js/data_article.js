var articleId;
var user_id = 0;
var pid = 0;
var floor = 0;
var comment_num = 0;

function isLogin() {
    $.ajax({
        type: 'get',
        url: '/user/isLogin',
        dataType: 'json',
        success: function (data) {
            if (data['status'] === 'success') {
                if (data['data']['state'] == 1) {

                    document.getElementById('loginMessage').innerHTML = "已登录";
                } else {
                    document.getElementById('loginMessage').innerHTML = "登录/注册";
                }
            } else {
                alert('数据请求错误');
            }
        },
        error: function () {
            alert("错误");
        }
    });
}

isLogin();

fillBlogDetail();




//需要完善的地方
//把博客页面重新编辑一下使得markdown正确展示
function makeBlogDetail(data){




    var articleTop = $('.article-top');
    articleTop.empty();

    var articleBottom = $('.article-bottom');
    articleBottom .html('');
    var blogDetailData = data['data'];



    var tagLine ='';
    $.each(blogDetailData['tags'], function (index, tag) {
        tagLine+='<a href="#" class="ui teal tag  label">'+tag['tagName']+'</a>';
    });



    //头部
    var articleHeadBody = '<div class="article-top">\n' +
        '                            <!-- 文章标题 -->\n' +
        '                            <div id="article-header">\n' +
        '                                <div class="ui center aligned container">\n' +
        '                                    <h1 class="m-text-middle"  >'+blogDetailData['title']+'</h1>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <!-- 分类，时间，作者，文件夹 -->\n' +
        '                            <div id="article-sort " class="m-margin-bt-large">\n' +
        '                                <div class="ui center aligned container">\n' +
        '                                    <div class="ui horizontal link list ">\n' +
        '\n' +
        '                                        <!-- 创作类型 -->\n' +
        '                                        <div class="item" >\n' +
        '                                            <div class="m-margin-bt-small ">\n' +
        '                                                <span class="ui  green label m-padd-mini" style="margin-bottom: 1px">'+blogDetailData['markFlag']+'</span>\n' +
        '                                            </div>\n' +
        '\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <!-- 创作时间 -->\n' +
        '                                        <div class="item" >\n' +
        '                                            <div class="create-time">\n' +
        '                                                <i class="  calendar alternate icon m-i-remark m-create-time-color m-margin-zero-right m-padding-right-zero"></i>\n' +
        '                                                <a href="#" class="m-font-size-mini m-a-remark m-time-color" >'+formatDate(blogDetailData['createTime'], 'YY/MM/DD')+'</a>\n' +
        '                                            </div>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <!-- 作者 -->\n' +
        '                                        <div class="item ">\n' +
        '                                            <div class="author ">\n' +
        '                                                <i class=" user icon m-i-remark m-padding-right-zero m-margin-zero-right" ></i>\n' +
        '                                                <span >'+blogDetailData['authorName']+'</span>\n' +
        '                                            </div>\n' +
        '\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <!-- 文件目录 -->\n' +
        '                                        <div class="item">\n' +
        '                                            <div class="contents">\n' +
        '                                                <i class="folder icon m-i-remark m-padding-right-zero m-margin-zero-right "></i>\n' +
        '                                                <span>'+blogDetailData['category']['name']+'</span>\n' +
        '                                            </div>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <!-- 浏览次数 -->\n' +
        '                                        <div class="item">\n' +
        '                                            <div class="skim">\n' +
        '                                                <i class="eye icon m-i-remark m-padding-right-zero m-margin-zero-right"></i>\n' +
        '                                                <span>'+blogDetailData['views']+'</span>\n' +
        '                                            </div>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '\n' +
        '                            </div>\n' +
        '\n' +
        '                            <!-- 文章开篇图片 -->\n' +
        '                            <div id="article-first-photo">\n' +
        '                                <img src="'+blogDetailData['firstPicture']+'"  alt=""  class = "ui centered  rounded  image" style="height: 500px; width: 700px;">\n' +
        '                            </div>\n' +
        '                        </div>';
    articleTop.append(articleHeadBody);


    $("#mdText").text(blogDetailData['content']);
    var  testEditormdView;
    testEditormdView = editormd.markdownToHTML("test-editormd-view", {
        htmlDecode: "true", // you can filter tags decode
        emoji: true,
        taskList: true,
        previewTheme : "dark",
        tex: true,
        flowChart: true,
        sequenceDiagram: true
    });

    // $(".prettyprint linenums prettyprinted").addClass("editormd-preview-theme-dark");

    var articeBottomBody = '<!--文章底部-->\n' +
        '                        <div class="article-bottom">\n' +
        '\n' +
        '                            <!-- ending 图标 -->\n' +
        '                            <div id="article-ending" class="m-margin-bt-large">\n' +
        '                                <div class="ui center aligned container">\n' +
        '                                    <img src="/images/ending.png"  alt="" class="ui small centered image">\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '                            <div class="ui divider"></div>\n' +
        '\n' +
        '                            <!-- 赞赏 -->\n' +
        '                            <div id="article-admired" class="m-margin-bt">\n' +
        '\n' +
        '                                <!-- 文字说明 -->\n' +
        '                                <div class="ui center aligned container m-padding-top" id="admired-word">\n' +
        '                                    <span class="m-inlie-block">“觉得文章很棒，就鼓励一下作者吧！”</span>\n' +
        '                                </div>\n' +
        '\n' +
        '                                <!-- 赞赏图标 -->\n' +
        '                                <div class="ui center aligned basic segment m-padd-foot-mini" id="admire-icon">\n' +
        '                                    <button id="payButton" class="ui large orange basic circular button">\n' +
        '                                        <i class="thumbs up icon"></i>赞赏\n' +
        '                                    </button>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '\n' +
        '                            <!-- 打赏二维码 -->\n' +
        '                            <div class="ui payQR flowing popup  transition hidden">\n' +
        '                                <div class="ui orange basic label" id="admired-method">\n' +
        '                                    <div class="ui images" style="font-size: inherit !important;">\n' +
        '                                        <div class="image">\n' +
        '                                            <img src="/images/weChat.png"  alt="" class="ui rounded bordered image">\n' +
        '                                            <p>支付宝打赏</p>\n' +
        '                                        </div>\n' +
        '                                        <div class="image">\n' +
        '                                            <img src="/images/weChat.png"  alt="" class="ui rounded bordered image">\n' +
        '                                            <p>微信打赏</p>\n' +
        '                                        </div>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '                            <!-- 公众号同步 -->\n' +
        '                            <div id="weChat-public" class="m-margin-bt-big" >\n' +
        '                                <div class="m-container-mini ">\n' +
        '                                    <div class="ui center secondary segment">\n' +
        '\n' +
        '                                        <img src="/images/weChat-public.jpg" alt="" class="ui small centered rounded image ">\n' +
        '\n' +
        '                                        <div class="ui center aligned container">\n' +
        '                                            <h3 >关注我的公众号：生活要有诗意，可同步查阅文章</h3>\n' +
        '                                        </div>\n' +
        '\n' +
        '\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '\n' +
        '                            </div>\n' +
        '\n' +
        '\n' +
        '                            <!-- 博客信息介绍 -->\n' +
        '                            <div id="article-info" class="m-margin-bt-tiny">\n' +
        '\n' +
        '                                <ul class="line m-margin-top-zero">\n' +
        '                                    <li>\n' +
        '                                        <span>文章作者：</span>\n' +
        '                                        <span> '+blogDetailData['authorName']+'</span>\n' +
        '                                    </li>\n' +
        '                                    <li>\n' +
        '                                        <span>原文链接：</span>\n' +
        '                                        <span> 余睿哲</span>\n' +
        '                                    </li>\n' +
        '                                    <li>\n' +
        '                                        <span>版权声明：</span>\n' +
        '                                        <span> 余睿哲</span>\n' +
        '                                    </li>\n' +
        '                                </ul>\n' +
        '\n' +
        '\n' +
        '                            </div>\n' +
        '\n' +
        '                            <!-- 文章标签  -->\n' +
        '                            <div id="article-tag">\n' +
        '                                '+tagLine+
        '                            </div>\n' +
        '\n' +
        '                            <div class="ui divider"></div>\n' +
        '\n' +
        '                            <!-- 底部换页区域 -->\n' +
        '                            <div id="next-pre-page">\n' +
        '\n' +
        '\n' +
        '                                <div class=" m-margin-rf">\n' +
        '\n' +
        '                                    <div class="ui grid">\n' +
        '\n' +
        '                                        <!-- 上一页 -->\n' +
        '                                        <div class="left floated left aligned six wide column">\n' +
        '                                            <div class="left floated column pre" style="display: inline-block !important;">\n' +
        '                                                <a href="">上一篇文章名</a>\n' +
        '                                                <i class="hand point left outline icon"></i>\n' +
        '                                            </div>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <div class="right floated right aligned six wide column">\n' +
        '                                            <div class="right floated column next " style="display: inline-block;">\n' +
        '                                                <i class="hand point right outline icon"></i>\n' +
        '                                                <a href="">下一篇文章名</a>\n' +
        '                                            </div>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                    </div>\n' +
        '\n' +
        '\n' +
        '\n' +
        '                                </div>\n' +
        '\n' +
        '\n' +
        '                            </div>\n' +
        '                        </div>';
    articleBottom.append(articeBottomBody);

    $.ajax({
        type:"get",
        url:"/user/getCommentsOfArticle",
        dataType:"json",
        data:{
            articleId:articleId
        },

        success:function (data) {


            if(data['result']==undefined){
                var item = '<div><p class="no-message">无人留下足迹，可怜</p></div>'
                $(".comment-main-top").append(item);
            }

            else {
                comment_num = data['commentNum']
                comment_plus();
                floor = data['result'].length;
                var temp_floor = floor;
                $.each(data['result'],function (index, obj) {
                    var temp="";
                    var isLike = "am-icon-thumbs-o-up";

                    if(obj['isLike']==1){
                        isLike = "am-icon-thumbs-up";
                    }

                    $.each(obj['replies'],function (index, obj) {
                        var text = "";
                        if(obj['responsorId']!=0){
                            var text = '回复<a>@'+obj['responsorName']+'</a>：'+obj['content']+'';
                        }

                        else {
                            var text = obj['content'];
                        }

                        var reply_item = '<div class="reply-item">\n' +
                            '                                                <div class="reply-user-img">\n' +
                            '                                                    <a href="">\n' +
                            '                                                        <img src="'+obj['avatarImgUrl']+'">\n' +
                            '                                                    </a>\n' +
                            '                                                </div>\n' +
                            '                                                <div class="reply-item-content">\n' +
                            '                                                    <div class="user">\n' +
                            '                                                        <span class="user-name">'+obj['userName']+'</span>\n' +
                            '                                                        <span class="user-id" style="display: none">'+obj['remarkerId']+'</span>\n' +
                            '                                                        <span class="text">'+text+'</span>\n' +
                            '                                                    </div>\n' +
                            '\n' +
                            '\n' +
                            '                                                    <div class="info">\n' +
                            '                                                        <span class="time">'+obj['date']+'</span>\n' +
                            '                                                        <span class="reply inner-reply"><a>回复</a></span>\n' +
                            '                                                    </div>\n' +
                            '                                                </div>\n' +
                            '                                            </div>'

                        temp+=reply_item;
                    })


                    var list_item = '<div class="list-item">\n' +
                        '                                    <div class="user-img">\n' +
                        '                                        <a href="">\n' +
                        '                                            <img src="'+obj['avatarImgUrl']+'">\n' +
                        '                                        </a>\n' +
                        '                                    </div>\n' +
                        '                                    <div class="list-item-content">\n' +
                        '                                        <div class="user">\n' +
                        '                                            <span class="user-name">'+obj['userName']+'</span>\n' +
                        '                                            <span class="pid" style="display: none">'+obj['commentId']+'</span>\n' +
                        '                                        </div>\n' +
                        '                                        <p class="text">'+obj['content']+'</p>\n' +
                        '                                        <div class="info">\n' +
                        '                                            <span class="floor">#'+(temp_floor--)+'</span>\n' +
                        '                                            <span class="time">'+obj['date']+'</span>\n' +
                        '                                            <span class="like">\n' +
                        '                                                <a>\n' +
                        '                                                    <i class="'+isLike+'">&nbsp;&nbsp;'+obj['likes']+'</i>\n' +
                        '                                                </a>\n' +
                        '                                            </span>\n' +
                        '\n' +
                        '                                            <span class="reply top-reply"><a>回复</a></span>\n' +
                        '\n' +
                        '                                        </div>\n' +
                        '\n' +
                        '                                        <div class="reply-box">'+temp+'</div>\n' +
                        '                                    </div>\n' +
                        '\n' +
                        '                                    <div class="comment-reply am-animation-slide-top" style="display: none">\n' +
                        '                                        <div class="user-img">\n' +
                        '                                            <img src="">\n' +
                        '                                        </div>\n' +
                        '\n' +
                        '                                        <div class="comment-reply-textarea">\n' +
                        '                                            <textarea placeholder="" id="comment-reply-textarea"></textarea>\n' +
                        '                                            <button class="comment-reply-textarea-btn am-text-break">发表评论</button>\n' +
                        '                                        </div>\n' +
                        '                                    </div>\n' +
                        '                                </div>'


                    $(".comment-main-list").append(list_item);
                })
            }


        }

    });

    $('#payButton').popup({
        popup : $('.payQR'),
        on : 'click',
        position : 'top center'
    });

    //todo
    // 弹出文章目录
    $('.toc.button').popup({
        popup : $('.toc-container'),
        on : 'click',
        position : 'top center'
    });

    // 生成网页二维码
    var qrcode = new QRCode("qrcode", {
        text: "http://jindo.dev.naver.com/collie",
        width: 128,
        height: 128,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
    });

    var waypoint = new Waypoint({
        element: document.getElementById('waypoint'),
        handler: function(direction) {
            if(direction == 'down') {
                $('#toolbar').show(500);

            } else {
                $('#toolbar').hide(500);
            }
        }
    });

}

$("#comment-btn").on("click",function () {


    var tag = 0;//判断是否登录

    $.ajax({
        type:"get",
        url:"/user/isLogin",
        async:false,
        dataType:"json",
        success:function (data) {
            if(data['data']['state']==0){
                window.location.href="/user/login";
            }

            else {
                tag = 1;
                headImg = data['headImg'];
                id = data['id'];
            }

        }
    })

    if(tag!=0){
        var commentContent = $("#comment-content").val();
        if(commentContent==""){
            layer.alert('信息不能为空',{
                icon:2,
                skin: 'layer-ext-moon'
            })

            return;
        }
        $.ajax({
            type:"get",
            url:"/user/addComment",
            dataType:"json",
            data:{
                articleId:articleId,
                commentContent:commentContent
            },

            success:function (data) {
                comment_num++;
                comment_plus();
                var comment=' <div class="list-item">\n' +
                    '                                    <div class="user-img">\n' +
                    '                                        <a href="">\n' +
                    '                                            <img src="'+headImg+'">\n' +
                    '                                        </a>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="list-item-content">\n' +
                    '                                        <div class="user">\n' +
                    '                                            <span class="user-name">'+data['remarkerName']+'</span>\n' +
                    '                                            <span class="pid" style="display: none">'+data['pid']+'</span>\n' +
                    '                                        </div>\n' +
                    '                                        <p class="text">'+commentContent+'</p>\n' +
                    '                                        <div class="info">\n' +
                    '                                            <span class="floor">#'+(++floor)+'</span>\n' +
                    '                                            <span class="time">'+data['date']+'</span>\n' +
                    '                                            <span class="like">\n' +
                    '                                                <a>\n' +
                    '                                                    <i class="am-icon-thumbs-o-up">&nbsp;&nbsp;0</i>\n' +
                    '                                                </a>\n' +
                    '                                            </span>\n' +
                    '\n' +
                    '                                            <span class="reply top-reply"><a>回复</a></span>\n' +
                    '\n' +
                    '                                        </div>\n' +
                    '\n' +
                    '                                        <div class="reply-box"></div>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="comment-reply am-animation-slide-top" style="display: none">\n' +
                    '                                        <div class="user-img">\n' +
                    '                                            <img src="'+headImg+'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="comment-reply-textarea">\n' +
                    '                                            <textarea placeholder="" id="comment-reply-textarea"></textarea>\n' +
                    '                                            <button class="comment-reply-textarea-btn am-text-break">发表评论</button>\n' +
                    '                                        </div>\n' +
                    '                                    </div>' +
                    '                                </div>'


                $(".comment-main-list").prepend(comment);
                $("#comment-content").val("")
            }
        })
    }





})

var topOld = "";
var innerOld = "";
var lastReply = "";

function comment_plus() {
    var item = '<h3>Comments |\n' +
        '                                <span>'+comment_num+'条评论</span>\n' +
        '                            </h3>'
    $(".comment-main-top").empty();
    $(".comment-main-top").append(item);
}

$(document).on("click",".top-reply",function () {
    var temp = $(this).parent().parent().parent();
    var tag = 0;//判断是否登录

    $.ajax({
        type:"get",
        url:"/user/isLogin",
        async:false,
        dataType:"json",
        success:function (data) {
            if(data['data']['state']==0){
                window.location.href="/user/login";
            }

            else {
                tag = 1;
                headImg = data['headImg'];
                id = data['id'];
            }

        }
    })

    if(tag!=0){
        //如果同源，或者第一次
        if($(this).is(lastReply)==true||lastReply==""){
            //如果上次点击过，则消失
            if(temp.is(topOld)==true){
                topOld = "";
                temp.children(".comment-reply").css("display","none");
                return;
            }

            //否则则显示
            else {
                user_id = 0;

                temp.children(".comment-reply").children(".comment-reply-textarea").children("textarea").attr("placeholder","写下你的伟论...");
                temp.children(".comment-reply").children(".user-img").children("img").attr("src",headImg);
                temp.children(".comment-reply").css("display","block");
            }
        }

        //如果不同源，则先清除存在的输入框，再重新
        else {
            $(".comment-reply").css("display","none");
            user_id = 0;
            temp.children(".comment-reply").children(".comment-reply-textarea").children("textarea").attr("placeholder","写下你的伟论...");
            temp.children(".comment-reply").children(".user-img").children("img").attr("src",headImg);
            temp.children(".comment-reply").css("display","block");
        }

        topOld = temp;
        lastReply = $(this);
    }

});


$(document).on("click",".inner-reply",function () {
    var temp = $(this).parent().parent().parent().parent().parent().parent();
    var person = "回复 @"+$(this).parent().prev(".user").children(".user-name").text()+" :";

    var tag = 0;//判断是否登录

    $.ajax({
        type:"get",
        url:"/user/isLogin",
        async:false,
        dataType:"json",
        success:function (data) {
            if(data['data']['state']==0){
                window.location.href="/user/login";
            }

            else {
                tag = 1;
                headImg = data['headImg'];
                id = data['id'];
            }

        }
    })

    if(tag!=0){
        //如果同源或第一次，则显示
        if($(this).is(lastReply)||lastReply==""){

            if($(this).is(innerOld)){
                innerOld = "";
                temp.children(".comment-reply").css("display","none");
                return;
            }

            else {
                user_id = $(this).parent().prev(".user").children(".user-id").text();
                temp.children(".comment-reply").children(".comment-reply-textarea").children("textarea").attr("placeholder",person);
                temp.children(".comment-reply").children(".user-img").children("img").attr("src",headImg);
                temp.children(".comment-reply").css("display","block");
            }

        }

        //如果不是，则清除，显示
        else {
            $(".comment-reply").css("display","none");
            user_id = $(this).parent().prev(".user").children(".user-id").text();
            temp.children(".comment-reply").children(".comment-reply-textarea").children("textarea").attr("placeholder",person);
            temp.children(".comment-reply").children(".user-img").children("img").attr("src",headImg);
            temp.children(".comment-reply").css("display","block");
        }

        lastReply = $(this);
        innerOld = $(this);
    }


});

$(document).on("click",".comment-reply-textarea-btn",function () {


    var reply_btn = $(this).parent().children("textarea");
    var reply = reply_btn.val();
    var box = $(this).parent().parent().prev(".list-item-content").children(".reply-box");
    var pid = $(this).parent().parent().prev(".list-item-content").children(".user").children(".pid").text();
    var reply_constant=""

    if($(this).parent().children("textarea").attr("placeholder").indexOf("@")!=-1){

        //var index = $(this).parent().children("textarea").attr("placeholder").indexOf(":");
        //person = $(this).parent().children("textarea").attr("placeholder").substring(3,index);
        reply_constant = "回复 ";
        if(reply==""){
            layer.alert('信息不能为空',{
                icon:2,
                skin: 'layer-ext-moon'
            })
            return;
        }

        $.ajax({
            type:"get",
            url:"/user/addComment",
            dataType:"json",
            data:{
                articleId:articleId,
                commentContent:reply,
                responsorId:user_id,
                pId:pid

            },

            success:function (data) {
                comment_num++;
                comment_plus();
                var reply_item = '<div class="reply-item">\n' +
                    '                                                <div class="reply-user-img">\n' +
                    '                                                    <a href="">\n' +
                    '                                                        <img src="'+headImg+'">\n' +
                    '                                                    </a>\n' +
                    '                                                </div>\n' +
                    '                                                <div class="reply-item-content">\n' +
                    '                                                    <div class="user">\n' +
                    '                                                        <span class="user-name">'+data['remarkerName']+'</span>\n' +
                    '                                                        <span class="user-id" style="display: none">'+id+'</span>\n' +
                    '                                                        <span class="text">'+reply_constant+'<a>@'+data['responsorName']+'</a>：'+reply+'</span>\n' +
                    '                                                    </div>\n' +
                    '\n' +
                    '\n' +
                    '                                                    <div class="info">\n' +
                    '                                                        <span class="time">'+data['date']+'</span>\n' +
                    '                                                        <span class="reply inner-reply"><a>回复</a></span>\n' +
                    '                                                    </div>\n' +
                    '                                                </div>\n' +
                    '                                            </div>'

                box.append(reply_item);
                reply_btn.val("");
            }
        })

    }

    else {
        $.ajax({
            type:"get",
            url:"/user/addComment",
            dataType:"json",
            data:{
                articleId:articleId,
                commentContent:reply,
                pId:pid
            },

            success:function (data) {
                comment_num++;
                comment_plus();
                var reply_item = '<div class="reply-item">\n' +
                    '                                                <div class="reply-user-img">\n' +
                    '                                                    <a href="">\n' +
                    '                                                        <img src="'+headImg+'">\n' +
                    '                                                    </a>\n' +
                    '                                                </div>\n' +
                    '                                                <div class="reply-item-content">\n' +
                    '                                                    <div class="user">\n' +
                    '                                                        <span class="user-name">'+data['remarkerName']+'</span>\n' +
                    '                                                        <span class="user-id" style="display: none">'+id+'</span>\n' +
                    '                                                        <span class="text">'+reply+'</span>\n' +
                    '                                                    </div>\n' +
                    '\n' +
                    '\n' +
                    '                                                    <div class="info">\n' +
                    '                                                        <span class="time">'+data['date']+'</span>\n' +
                    '                                                        <span class="reply inner-reply"><a>回复</a></span>\n' +
                    '                                                    </div>\n' +
                    '                                                </div>\n' +
                    '                                            </div>'

                box.append(reply_item);
                reply_btn.val("");
            }
        })
    }



});

$(document).on("click",".like",function () {
    var tag = 0;//判断是否登录

    $.ajax({
        type:"get",
        url:"/user/isLogin",
        async:false,
        dataType:"json",
        success:function (data) {
            if(data['data']['state']==0){
                window.location.href="/user/login";
            }

            else {
                tag = 1;
                headImg = data['headImg'];
                id = data['id'];
            }

        }
    })


    if(tag!=0){
        var temp = $(this).children("a").children("i");
        var commentId = $(this).parent().parent().children(".user").children(".pid").text();
        $.ajax({
            type:"get",
            url:"/user/commentLikeAction",
            dataType:"json",
            data:{
                commentId:commentId,
                articleId:articleId
            },
            success:function (data) {
                if(data==1){
                    var number = $(temp).text();
                    number++;
                    $(temp).html("&nbsp;&nbsp;"+number);
                    $(temp).removeClass("am-icon-thumbs-o-up");
                    $(temp).addClass("am-icon-thumbs-up")
                }

                else {
                    var number = $(temp).text();
                    number--
                    $(temp).html("&nbsp;&nbsp;"+number);
                    $(temp).removeClass("am-icon-thumbs-up");
                    $(temp).addClass("am-icon-thumbs-o-up");
                }
            }
        })



    }
});


function fillBlogDetail() {


    var url = location.href;
    var id = url.split("/");
    articleId = id[5];

    $.ajax({
        type: 'post',
        url: '/user/getBlogDetail/'+articleId,
        dataType: 'json',
        success: function (data) {
            if (data['status'] === 'success') {
                scrollTo(0,0);
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