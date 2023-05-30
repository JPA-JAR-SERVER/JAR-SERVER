// 좋아요 버튼
$("div.second-content-slide ").on("click","button.heart-layout",function(e){
    let boardId = e.currentTarget.id.replaceAll("heart","");

    let ul = $(e.currentTarget).parent().parent().parent().parent().parent();
    let li = $(e.currentTarget).parent().parent().parent().parent();
    let i = ul.find("div.content-image-box2").index(li);
    let likeDTO = new Object();
    likeDTO.memberId = memberId;
    likeDTO.boardId = boardId;

    likeService.heartCheck(likeDTO, function(result){
        if(result){
            // 좋아요 ++
            likeService.heartUp(likeDTO, function(){
                $($(".no-heart")[i]).addClass("heart-active");
                $($(".heart-up")[i]).show();
                $($(".no-heart")[i]).hide();
                likeService.count({boardId: boardId}, function (result) {
                    $($(".like-count")[i]).text(result);
                });
            });
        } else {
            // 좋아요 --
            likeService.heartDown(likeDTO, function(){
                $($(".heart-up")[i]).hide();
                $($(".no-heart")[i]).show();
                $($(".no-heart")[i]).removeClass("heart-active");
                likeService.count({boardId: boardId}, function (result) {
                    $($(".like-count")[i]).text(result);
                })
            });
        }
    });
});
