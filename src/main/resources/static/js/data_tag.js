
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


fillCategoryBlogMerge(1);


function makeCategoryBlogMerge(data, tagName) {

    var tag = $('.tag-data');
    tag.empty();

    var headBody = '<header class="m-header">#'+decode(tagName)+'</header>';
    tag.append(headBody);

    var body = '<div class="time-line time-pre blog-category-merge" style="margin-left: 0px;">\n' +
        '                  </div>';
    tag.append(body);

    var categoryBody = $('.blog-category-merge');

    var categoryInfoDatas = data['data']['blogCategoryInfos'];
    $.each(categoryInfoDatas, function (index, categoryInfoData) {

        //解析底部标签列表
        var tags = categoryInfoData['tagNames'].split(',');
        var bottomTagLine = '';
        $.each(tags, function (index, tagName) {
            //todo
            bottomTagLine +='<a class="m-color" href="/user/tag/'+tagName+'">'+tagName+'</a>&nbsp; '
        });

        var categoryInfoBody = '<div class="block m-magin-bottom">\n' +
            '\n' +
            '                          <!-- 圆形标头 -->\n' +
            '                          <span class="circle animation-left-c"></span>\n' +
            '                          <!-- 分类明细块 -->\n' +
            '                          <div class="ui raised segments m-inline-block m-margin-left animation-right" >\n' +
            '\n' +
            '                            <!-- 头部 -->\n' +
            '                            <div class="ui secondary segment">\n' +
            '                                  <div class="content con-head">\n' +
            '                                    <a class="header-style " href="/user/getBlogDetail/'+categoryInfoData['id']+'">'+categoryInfoData['title']+'</a>\n' +
            '                                  </div>\n' +
            '                            </div>\n' +
            '                              \n' +
            '                            <div class="ui yellow secondary segment segment-width">\n' +
            '                              <div class="ui horizontal list list-style">\n' +
            '\n' +
            '                                    <!-- 时间标签 -->\n' +
            '                                    <div class="item">\n' +
            '                                      <i class="blue calendar minus outline icon"></i>\n' +
            '                                      <div class="content m-padding-zero-left" >\n' +
            '                                        <a class=" m-color" href="/user/time/'+formatDate(categoryInfoData['createTime'],'YY-MM-DD')+'">'+formatDate(categoryInfoData['createTime'],'YY-MM-DD')+'</a>\n' +
            '                                      </div>\n' +
            '                                    </div>\n' +
            '    \n' +
            '                                    <!-- 查看人数 -->\n' +
            '                                    <div class="item">\n' +
            '                                      <i class="red eye icon"></i>\n' +
            '                                      <div class="content m-padding-zero-left">\n' +
            '                                        <a class=" m-color" href="">'+categoryInfoData['views']+'</a>\n' +
            '                                      </div>\n' +
            '                                    </div>\n' +
            '    \n' +
            '                                    <!-- 分类文件夹 -->\n' +
            '                                    <div class="item">\n' +
            '                                      <i class="yellow folder open icon"></i>\n' +
            '                                      <div class=" content m-padding-zero-left" >\n' +
            '                                        <a class=" m-color" href="#">'+categoryInfoData['categoryName']+'</a>\n' +
            '                                      </div>\n' +
            '                                    </div>\n' +
            '                                  \n' +
            '                                    <!-- 分类标签 -->\n' +
            '                                    <div class="item">\n' +
            '                                      <i class="green tag icon"></i>\n' +
            '                                      <div class="content m-padding-zero-left">\n' +
            '                                        '+bottomTagLine+'\n' +
            '                                      </div>\n' +
            '                                    </div>  \n' +
            '    \n' +
            '                                  </div>\n' +
            '                                </div>\n' +
            '                          </div>\n' +
            '                      </div>';
        categoryBody.append(categoryInfoBody);
    });

}

//填充分类博客信息合并项
function fillCategoryBlogMerge(currentPage) {

    var url = location.href;
    var id = url.split("/");
    var tagName = id[5];
    $.ajax({
        type: 'post',
        url: '/user/listCategoryBlogMergeByTag/'+tagName,
        dataType: 'json',
        data: {
            size:"3",
            pageNum:currentPage
        },
        success: function (data) {
            if (data['status'] === 'success') {
                scrollTo(0,0);
                makeCategoryBlogMerge(data, tagName);

                var pageInfo = data['data']['myPageInfo'];

                //分页显示
                $("#pagination").paging({
                    rows: pageInfo['pageSize'],//每页显示条数
                    pageNum: pageInfo['pageNum'],//当前所在页码
                    pages: pageInfo['pages'],//总页数
                    total: pageInfo['total'],//总记录数
                    callback:function(currentPage){
                        fillCategoryBlogMerge(currentPage);
                    }
                });

            } else {
                alert('分类博客信息合并项数据请求错误');
            }
        },
        error: function () {
            alert("分类博客信息合并项错误");
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

function decode(inputStr){
    var resultArr =[];
    for(var i=0;i<inputStr.length;i++){
        var chr = inputStr.charAt(i);
        if(chr == "+"){
            resultArr[resultArr.length]=" ";
        }else if(chr=="%"){
            var asc = inputStr.substring(i+1,i+3);
            if(parseInt("0x"+asc)>0x7f){
                resultArr[resultArr.length]= decodeURI(inputStr.substring(i,i+9));
                i+=8;
            }else{
                resultArr[resultArr.length]=String.fromCharCode(parseInt("0x"+asc));
                i+=2;
            }
        }else{
            resultArr[resultArr.length]= chr;
        }
    }
    return resultArr.join("");
}