
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
    })

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

