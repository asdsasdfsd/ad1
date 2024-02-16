// 显示模态框
$('#addToCollection').click(function() {
    var userId = $('#userId').val(); 

    if (!userId) {
        window.location.href = '/user/login'; 
    } else {
        $('#collectionModal').show();
    }
});

// 关闭模态框函数
function closeModal() {
    $('#collectionModal').hide();
}

// 处理保存按钮点击事件
$('#saveToCollection').click(function() {
    var animeId = $('#animeId').val();
    var userId = $('#userId').val();
    var name = $('#animeName').val();
    var rating = parseFloat($('#animeRating').val());
    var description = $('#animeDescription').val();

    $.ajax({
        url: '/create-collection', 
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            regularUserId: userId,
            animeId: animeId,
            rating: rating,
            name: name,
            time: new Date().toISOString(),
            type: 'Collection', 
            description: description
        }),
        success: function(response) {
            closeModal(); 
        },
        error: function(xhr, status, error) {
            alert('保存到收藏失败');
        }
    });
});