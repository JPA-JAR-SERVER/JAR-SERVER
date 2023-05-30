const $ul = $(".challenge-wrapper-list");
let page = 0;
let boardStatus = "OPEN";

let groupChallengeService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/board/challenge/group/content-list',
            type: 'get',
            data: page,
            success: function (list) {
                if (callback) {
                    callback(list);
                }
            }
        });
    }

    return {
        list: list,
    }
})();
getList(boardStatus, page);

$(".currently-challenge").click(() => {
    $ul.empty();
    page = 0;
    boardStatus = "OPEN";
    getList(boardStatus, page);
    check =false;
});

$(".closing-challenge ").click(() => {
    page = 0;
    boardStatus = "PRIVATE";
    $ul.empty();
    check = true;
    getList(boardStatus, page);
});


$(".paging-layout").on("click", "a", function(e) {
    e.preventDefault();
    $ul.empty();
    const targetPage = $(this).text();

    if ($(this).hasClass("arrow")) {
        // 화살표 클릭 시 이전 페이지 또는 다음 페이지로 이동
        if ($(this).attr("id") === "prev") {
            page--;
        } else if ($(this).attr("id") === "next") {
            page++;
        }
    } else {
        // 페이지 번호 클릭 시 해당 페이지로 이동
        page = parseInt(targetPage) - 1;
    }

    getList(boardStatus, page);
});

function displayPagination(totalPages) {
    const $pagination = $(".paging-layout");
    $pagination.empty();

    if (page > 0) {
        $pagination.append("<a href='#' class='arrow' id='prev'><img class='prev'  src='/image/mypage/arrow.png'></a>");
    }

    for (let i = 1; i <= totalPages; i++) {
        if (i === page + 1) {
            // 현재 페이지를 텍스트로 표시
            $pagination.append(
                "<div class='paging-border-none paging-active'><span class='page'>" + i + "</span></div>"
            );
        } else {
            // 다른 페이지는 a 태그로 표시
            $pagination.append("<div class='paging-border-none'><a href='#' class='page'>" + i + "</a></div>");
        }
    }
    if (page < totalPages - 1) {
        $pagination.append("<a href='#' class='arrow' id='next'><img class='next' src='/image/mypage/arrow.png'></a>");
    }
}

function listText(list) {
    let groupChallengeDTOS = list.content;

    $(groupChallengeDTOS).each((i, groupChallengeDTO) => {
        let text = `
                                                <li smcolumn="2" lgcolumn="3" class="challenge-board">
                                                        <div class="challenge-board-box">
                                                            <a href="/board/challenge/group/detail/${groupChallengeDTO.id}"  class="challenge-board-box-a">
                                                                <div class="challenge-board-box-a-box">
                                                                    <div class="board-box">
                                                                        <span class="board-box-span-1 board-box-span-2">
                                                                            <picture class="board-box-picture" ratio="0.75">
                                                                            `;
        if(groupChallengeDTO.fileDTOS.length == 0){
            text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
        }else {
            for (let  j= 0; j < groupChallengeDTO.fileDTOS.length; j++){
                if(groupChallengeDTO.fileDTOS[j].fileType === "REPRESENTATIVE"){
                    text += `<img class="list-image"  src="/file/display?fileName=${groupChallengeDTO.fileDTOS[j].filePath}/${groupChallengeDTO.fileDTOS[j].fileUuid}_${groupChallengeDTO.fileDTOS[j].fileOriginalName}">`;
                }
            }
        }

                        text +=                                        `        </picture>
                                                                        </span>
                                                                    </div>
                                                                    <div class="">
                                                                        <div class="board-container-1">
                                                                            <p class="board-container-name">${groupChallengeDTO.boardTitle}</p>
                                                                        </div>
                                                                        <div class="board-container-2">${groupChallengeDTO.boardContent}</div>
                                                                        <div class="board-container-3">
                                                                            <div class="board-container-set">
                                                                                <div class="board-container-set-wrap" data-test-group="product-card-wishlited-count1">
                                                                                    <div class="board-container-set-box">
                                                                                        <img src="/image/board/human-icon.png" alt="" class="human-icon">
                                                                                       <span>${groupChallengeDTO.attendCount}</span>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="board-container-set-like" data-test-group="product-card-like-count">
                                                                                    <div class="board-container-set-box">
                                                                                        <img src="/image/board/reply-icon.png" alt="" class="reply-icon">
                                                                                        <span>${groupChallengeDTO.replyCount}</span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="board-box-line"></div>
                                                                    <div class="board-box-line-flex"></div>
                                                                </div>
                                                            </a>
                                                        </div>
                                                    </li>

        `;
        $ul.append(text);
    });

}

function getList(boardStatus, page){
    groupChallengeService.list({
        page: page,
        boardStatus : boardStatus
    }, function (list) {
        window.scrollTo(0, 0);
        listText(list);
        displayPagination(list.totalPages);
    })
}
