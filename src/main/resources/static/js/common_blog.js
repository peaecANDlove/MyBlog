
// 目录生成插件初始化


// 打赏二维码，弹出
$('#payButton').popup({
    popup : $('.payQR'),
    on : 'click',
    position : 'top center'
});

// 弹出文章目录
$('.toc.button').popup({
    popup : $('.toc-container'),
    on : 'click',
    position : 'top center'
});

$('.wechat').popup({
    popup : $('.wechat-qr'),
    on : 'click',
    position:'left center'
});

// 回到顶部
$('#toTop').click( function(){
    $(window).scrollTo(0, 650);
});


    