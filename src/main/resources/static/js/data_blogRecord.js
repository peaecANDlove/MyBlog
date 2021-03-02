
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

fillBlogRecord();

//构建博客记录方法体
function makeBlogRecord(data) {

    var blogRecord = $('.blogRecord-data');
    blogRecord.empty();

    var blogRecordData = data['data'];
    $.each(blogRecordData, function (index, record) {
        var blogRecordBody = '<div class="item m-header-bottom">\n' +
            '                  <div class="header">\n' +
            '                    <h4 class="record-content-header">'+(record['createTime'])+'</h4>\n' +
            '                  </div>\n' +
            '                  <div class="content recordWord ">'+record['recordWord']+'</div>\n' +
            '                  <div class="ui center aligned container icon-top">\n' +
            '                    <i class=" arrow down icon"></i>\n' +
            '                  </div>\n' +
            '            </div>';
        blogRecord.append(blogRecordBody);
    });

    var blogRecordBottom = '<div class="item ">\n' +
        '          <div class="ui center aligned container">\n' +
        '            <div class="header">\n' +
        '              <p class="record-ending">未完待续.......</p>\n' +
        '            </div>\n' +
        '            <div class="description">\n' +
        '              <p style="color: #74787c;">前程似锦 未来可期 寻得良人 共赴白头 祝你也祝我</p>\n' +
        '            </div>\n' +
        '            \n' +
        '          </div>\n' +
        '        </div>';
    blogRecord.append(blogRecordBottom);

}

//填充博客记录
function fillBlogRecord() {

    $.ajax({
            type: 'get',
            url: '/user/listBlogRecord',
            dataType: 'json',
            success: function (data) {
                if (data['status'] === 'success') {
                    makeBlogRecord(data);
                } else {
                    alert('博客记录数据请求错误');
                }
            },
            error: function () {
                alert("博客记录错误");
            }
        });
}













