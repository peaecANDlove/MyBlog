
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

fillCategoryList();

fillCategoryBlogMerge(1);

//构建分类列表块
function makeCategoryList(data) {
    var categoryList = $('.make-categoryList');
    categoryList.empty();
    var categoryListData = data['data'];
    var categoryListHeader = '<div class="item">\n' +
        '                      <div class="content">\n' +
        '                       <div class="ui left aligned container">\n' +
        '                           <div class="header head-name">类目</div> \n' +
        '                       </div>\n' +
        '                       </div>\n' +
        '                     </div>';
    categoryList.append(categoryListHeader);
    $.each(categoryListData, function (index, categoryListInfo) {
        var categoryListBody = '<div class="item">\n' +
            '                       <div class="content">\n' +
            '                           <div class="ui left aligned container">\n' +
            //todo
            '                               <a href="#" class="m-font" >'+categoryListInfo['categoryName']+'</a> \n' +
            '                               <span class="m-span-color">('+categoryListInfo['blogs']+')</span>\n' +
            '                           </div>\n' +
            '                       </div>\n' +
            '                   </div>';
        categoryList.append(categoryListBody);

    });

}

//填充分类列表块数据
function fillCategoryList() {


    $.ajax({
        type: 'get',
        url: '/user/categoryList',
        dataType: 'json',
        success: function (data) {
            if (data['status'] === 'success') {
                makeCategoryList(data);
            } else {
                alert('分类列表数据请求错误');
            }
        },
        error: function () {
            alert("分类列表错误");
        }
    });

}

//构建分类博客信息合并项
function makeCategoryBlogMerge(data) {

    var categoryInfo = $('.blog-category-merge');
    categoryInfo.empty();

    var categoryInfoDatas = data['data']['blogCategoryInfos'];
    $.each(categoryInfoDatas, function (index, categoryInfoData) {

        //解析底部标签列表
        var tags = categoryInfoData['tagNames'].split(',');
        var bottomTagLine = '';
        $.each(tags, function (index, tagName) {
            //todo
            bottomTagLine +='<a class="m-color" href="/tag/?tagName="'+tagName+'>'+tagName+'</a>&nbsp; '
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
        categoryInfo.append(categoryInfoBody);
    });
}

//填充分类博客信息合并项
function fillCategoryBlogMerge(currentPage) {

    var url = location.href;
    var id = url.split("/");
    var categoryName = id[5];
    $.ajax({
        type: 'post',
        url: '/user/listCategoryBlogMergeByName/'+categoryName,
        dataType: 'json',
        data: {
            size:"3",
            pageNum:currentPage
        },
        success: function (data) {
            if (data['status'] === 'success') {
                scrollTo(0,0);
                makeCategoryBlogMerge(data);

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