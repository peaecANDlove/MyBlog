
fillCategoryList();

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
}

//填充分类博客信息合并项

function fillCategoryBlogMerge() {

    $.ajax({
            type: 'get',
            url: '/user/categoryBlogMergeList',
            dataType: 'json',
            success: function (data) {
                if (data['status'] === 'success') {
                    makeCategoryBlogMerge(data);
                } else {
                    alert('分类博客信息合并项数据请求错误');
                }
            },
            error: function () {
                alert("分类博客信息合并项错误");
            }
        });
}