

fillBlogRecord();

//构建博客记录方法体
function makeBlogRecord(data) {

    var blogRecord = $('.blogRecord-data');
    blogRecord.empty();

    var blogRecordData = data['data'];
    $.each(blogRecordData, function (index, record) {
        var blogRecordBody = '<div class="item m-header-bottom">\n' +
            '                  <div class="header">\n' +
            '                    <h4 class="record-content-header">'+timeTransfer(record['createTime'])+'</h4>\n' +
            '                  </div>\n' +
            '                  <div class="content">'+record['recordWord']+'</div>\n' +
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
        '              <p style="color: #74787c;">你可以不漂亮，也可以不爱打扮，甚至可以很胖，你可以不优秀，可以不上进，甚至可以不聪明。但，我不可以。</p>\n' +
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













// 时间格式转换
function timeTransfer(time) {
    var str = '';

    for (var i=0; i<=9; i++) {
        if (i == 4) continue;
        if (i == 7) continue;
       str+=time[i];
       if (i== 3) str+='年';
       if (i==6) str+='月';
       if (i==9) str+='日';
    }



    return str;

}