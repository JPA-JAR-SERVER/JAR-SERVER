// 최신순 인기순 클릭시 class 추가 삭제
$(".btn").each((i, e) => {

    $(e).click(() => {
        if (!$(e).hasClass("active")) {
            $(".btn").removeClass("active");
            $(e).addClass("active");
        } else {
            $(e).removeClass("active");
        }
    });
});

// 해당 li에 마우스 올리면 일어나는 이벤트
$ul.on("mouseover", "li.content-li", function (e) {
    let ul = $(e.currentTarget).parent();
    let i = ul.find("li").index(e.currentTarget);

    $($(".content-hover")[i]).css("bottom", "1px");
    $($(".hover-box")[i]).css("transition", "all 0.2s ease-in-out 0s");
    $($(".hover-box")[i]).css("transform", "translateY(260px)");
    $($(".heart-layout")[i]).show();
});

// 해당 li에 마우스 떼면 일어나는 이벤트
$ul.on("mouseout", "li.content-li", function (e) {
    let ul = $(e.currentTarget).parent();
    let i = ul.find("li").index(e.currentTarget);

    $($(".content-hover")[i]).css("bottom", "-260px");
    $($(".hover-box")[i]).css("transition", "all 0.2s ease-in-out 0s");
    $($(".hover-box")[i]).css("transform", "translateY(0px)");
    $($(".heart-layout")[i]).hide();
});

// 최신순 버튼
$(".recent").click(() => {
    page = 0;
    sort = "recent";
    newList(page, sort);
});

// 인기순 버튼
$(".popular").click(() => {
    page = 0;
    sort = "popular";
    newList(page, sort);
});

// 스크롤 이벤트(무한 스크롤)
$(window).scroll(
    function() {
        if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
            page++;
            list(page, sort);
        }
    }
);


// 좋아요 버튼
$ul.on("click","button.heart-layout",function(e){
    let boardId = e.currentTarget.id.replaceAll("heart","");

    let ul = $(e.currentTarget).parent().parent().parent().parent().parent().parent();
    let li = $(e.currentTarget).parent().parent().parent().parent().parent();
    let i = ul.find("li").index(li);

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
