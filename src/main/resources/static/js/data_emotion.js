

fillEmotion();
//构建散言碎语
function makeEmotion(data) {

    var emotion = $('.data-emotion');
    emotion.empty();

    var emotionData = data['data'];
    $.each(emotionData, function (index, oneEmotion) {

        var emotionBody = '<div class="teal card wow rotateIn ">\n' +
            '              <div class="content">\n' +
            '                <div class="header">'+oneEmotion['createTime']+'</div>\n' +
            '                <div class="meta">'+oneEmotion['category']+'</div>\n' +
            '                <div class="description">'+oneEmotion['emotionWord']+'</div>\n' +
            '              </div>\n' +
            '            </div>';
        emotion.append(emotionBody);
    });
}

function fillEmotion() {

    $.ajax({
            type: 'get',
            url: '/user/listEmotion',
            dataType: 'json',
            success: function (data) {
                if (data['status'] === 'success') {

                    if (data['data'] === null){

                    }  else {
                        makeEmotion(data);
                    }

                } else {
                    alert('心情数据请求错误');
                }
            },
            error: function () {
                alert("心情错误");
            }
        });
}