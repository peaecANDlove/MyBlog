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

var articleId = 9999;
var user_id = 0;
var pid = 0;
var floor = 0;
var comment_num = 0;


fillFriendLink();

//无友链情况
function noFriendLink() {

    var friendLink = $('.data-friendLink');
    friendLink.empty();

    var state = '<h2 class="friendLink-h3">暂无友链添加，欢迎你的 Link</h2>';
    friendLink.append(state);

}

//构建友链卡片
function makeFriendLind(data) {

    var friendLink = $('.data-friendLink');
    friendLink.empty();

    var friendLinkData = data['data'];

    $.each(friendLinkData, function (index, friend) {

        //href todo
        var friendLinkBody = '<a class="ui teal card " href=" '+friend['blogAddress']+' ">\n' +
            '          <div class="content">\n' +
            '            <div class="header">'+friend['blogName']+'</div>\n' +
            '            <div class="meta">\n' +
            '              <span class="category">'+friend['blogType']+'</span>\n' +
            '            </div>\n' +
            '            <div class="description">\n' +
            '              <p class="frindeLink-desc">'+friend['blogInfo']+'</p>\n' +
            '            </div>\n' +
            '          </div>\n' +
            '          <div class="extra content">\n' +
            '            <div class="right floated author">\n' +
            '              <img class="ui avatar image" src=" '+friend['avatarAddress']+' "> Matt\n' +
            '            </div>\n' +
            '          </div>\n' +
            '        </a>';

        friendLink.append(friendLinkBody);
    });

    $.ajax({
        type:"get",
        url:"/user/getCommentsOfArticle",
        dataType:"json",
        data:{
            articleId:articleId
        },

        success:function (data) {


            if(data['result']==undefined){
                var item = '<div><p class="no-message">无人留下足迹，可怜</p></div>';
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

}


function fillFriendLink() {

    $.ajax({
            type: 'get',
            url: '/user/listFriendLink',
            dataType: 'json',
            success: function (data) {
                if (data['status'] === 'success') {
                    if (data['data'] === null) {
                        noFriendLink();
                    } else {
                        makeFriendLind(data);
                    }

                } else {
                    alert('友链数据请求错误');
                }
            },
            error: function () {
                alert("友链错误");
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