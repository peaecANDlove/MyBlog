// 导航
$('.menu.toggle').click(function() {
    $('.m-item').toggleClass('m-mobile-hide');
});

$('.ui.dropdown').dropdown();

// 添加分类
$('.category-add').click(function(){
    $('.ui.modal.category').modal('show');
});





$('.message .close')
    .on('click', function() {
        $(this)
            .closest('.message')
            .transition('fade')
        ;
    })
;



$('.ui.form.category').form({
    fields : {
        title : {
            identifier : 'name',
            rules : [{
                type : 'empty',
                prompt : '请输入分类名！'
            }]
        }
    }
});

$('.ui.form.blogRecord').form({
    fields : {
        title : {
            identifier : 'recordWord',
            rules : [{
                type : 'empty',
                prompt : '请输入记录！'
            }]
        }
    }
});


$('.ui.form.emotion').form({
    fields : {
        title : {
            identifier : 'emotionWord',
            rules : [{
                type : 'empty',
                prompt : '请输入心情！'
            }]
        }
    }
});

function page(obj) {
    $("[name='page']").val($(obj).data("page"));
    loaddata();

}


$("#search-btn").click(function () {
    $("[name='page']").val(0);
    loaddata();
});

function loaddata() {
    $("#table-container").load(/*[[@{/admin/blogs/search}]]*/"/admin/blogs/search",{
        title : $("[name='title']").val(),
        categoryId : $("[name='categoryId']").val(),
        recommend : $("[name='recommend']").prop('checked'),
        page : $("[name='page']").val()
    });
}



//保存
$('#save-btn').click(function () {
    $('[name="published"]').val(false);
    $('#blog-form').submit();
});

//发布
$('#publish-btn').click(function () {
    $('[name="published"]').val(true);
    $('#blog-form').submit();
});

//消息框关闭
$('.message.close')
    .on('click', function () {
        $(this)
            .closest('.message')
            .transition('fade');
});


$('#clear-btn')
    .on('click', function() {
        $('.ui.category.dropdown')
            .dropdown('clear')
        ;
});


$('.ui.tag.dropdown')
    .dropdown({
        allowAdditions: true
 });