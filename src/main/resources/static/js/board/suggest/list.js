const $ul = $(".content-ul");
let page = 0;
let boardType = "personal";


let suggestService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/board/suggest/list-content',
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

getList(boardType, page);


$(".personal").click(() => {
    boardType = "personal"
    page = 0;
    getList(boardType, page);
});

$(".group").click(() => {
    boardType = "group"
    page =0;
    getList(boardType, page);
});



function listText(list) {

    let text = '';
    let suggestDTOS = list.content;

    suggestDTOS.forEach((suggestDTO, i) => {
        text += `
                                        <li class="content-li">
                                                <div class="content-flex">

                                                        <div class="content-box">
                                                            <div class="content-image-box">
                                                                <span class="content-image-layout">
                                                                    <a href="/board/suggest/detail/${suggestDTO.id}">
                                                                    <picture>
                                         `
        if (suggestDTO.fileDTOS.length == 0) {
            text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
        } else {
            for (let j = 0; j < suggestDTO.fileDTOS.length; j++) {
                if (suggestDTO.fileDTOS[j].fileType === "REPRESENTATIVE") {
                    text += `<img src="/file/display?fileName=${suggestDTO.fileDTOS[j].filePath}/${suggestDTO.fileDTOS[j].fileUuid}_${suggestDTO.fileDTOS[j].fileOriginalName}">`;
                }
            }
        }
        text += `                            </picture>
                                                            </a>
                                                                    <div class="heart-box">
                                                                        <button class="heart-layout" id="heart${suggestDTO.id}">
                                                                            <span class="auto-flex no-heart">
                                                                                <svg xmlns="http://www.w3.org/2000/svg"
                                                                                    width="24" height="24" fill="none"
                                                                                    viewBox="0 0 24 24">
                                                                                    <path fill-rule="evenodd"
                                                                                        d="M20.5 9c0-2-1.5-3.9-3.7-3.9-2.3 0-3.8 1.63-4.8 3.33-1-1.7-2.5-3.33-4.8-3.33C5 5.1 3.5 6.867 3.5 9c0 4.62 4.949 7.667 8.5 9.623 3.551-1.956 8.5-5.003 8.5-9.623zm-19-.176C1.5 5.607 3.962 3 7 3c2.7 0 4 1 5 2.2C13 4 14.3 3 17 3c3.038 0 5.5 2.607 5.5 5.824C22.5 14.827 16.684 18.52 12 21 7.316 18.52 1.5 14.827 1.5 8.824z"
                                                                                        fill="#FFF"></path>
                                                                                </svg>
                                                                            </span>
                                                                            <span class="auto-flex heart-up" style="display:none">
                                                                                <svg xmlns="http://www.w3.org/2000/svg"
                                                                                    width="24" height="24" fill="none"
                                                                                    viewBox="0 0 24 24">
                                                                                    <path fill-rule="evenodd"
                                                                                        d="M1.5 8.824C1.5 5.607 3.962 3 7 3c2.5 0 4 1.5 5 3 1-1.5 2.5-3 5-3 3.038 0 5.5 2.607 5.5 5.824C22.5 14.827 16.684 18.52 12 21 7.316 18.52 1.5 14.827 1.5 8.824z"
                                                                                        fill="#fd3049"></path>
                                                                                </svg>
                                                                            </span>
                                                                        </button>
                                                                    </div>
                                                                </span>
                                                            </div>
                                                            <div class="content-title">
                                                                ${suggestDTO.boardTitle}
                                                            </div>
                                                            <div class="content2">
                                                            `;
                                                text +=       contentPrint(suggestDTO.boardContent);
                                                text +=            `
                                                             </div>
                                                                <div class="liked-box">
                                                                <div class="flex">
                                                                    <div class="liked-layout">
                                                                        <div class="icon-box">
                                                                            <svg xmlns="http://www.w3.org/2000/svg"
                                                                                width="12" height="12" fill="none"
                                                                                viewBox="0 0 24 24">
                                                                                <path fill-rule="evenodd"
                                                                                    d="M1.5 8.824C1.5 5.607 3.962 3 7 3c2.5 0 4 1.5 5 3 1-1.5 2.5-3 5-3 3.038 0 5.5 2.607 5.5 5.824C22.5 14.827 16.684 18.52 12 21 7.316 18.52 1.5 14.827 1.5 8.824z"
                                                                                    fill="#d7d7d7"></path>
                                                                            </svg>
                                                                        </div>
                                                                        <p class="like-count">${suggestDTO.likeCount}</p>
                                                                    </div>
                                                                    <div class="liked-layout">
                                                                        <div class="icon-box">
                                                                            <img src="/image/board/reply-icon.png" class="reply-icon">
                                                                        </div>
                                                                        <p class="reply-count">${suggestDTO.replyCount}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                </div>
                                            </li>
    `;
    });
    return text;
}

function contentPrint(content) {
    let test = content.replaceAll("\n", "<br/>").split("<br/>");
    let text = "";

    test.forEach((e, i) => {

        text += `
                        <p>${e}</p>
            `;
    });
    return text;
}

function getList(boardType, page){
    suggestService.list({
        boardType : boardType,
        page : page
    },function (list) {
        window.scrollTo(0, 0);
        $ul.html(listText(list));
        displayPagination(list.totalPages);
        heartCheck(list);
    })
}

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

    getList(boardType, page);
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


function heartCheck(list) {

    let diaryDTOS = list.content;

    diaryDTOS.forEach((diaryDTO, i) => {
        let likeDTO = new Object();
        likeDTO.memberId = memberId;
        likeDTO.boardId = diaryDTO.id;

        likeService.heartCheck(likeDTO,function(result){
            if(result){
                $($(".heart-up")[i]).hide();
                $($(".no-heart")[i]).show();
                $($(".no-heart")[i]).removeClass("heart-active");
            }else {
                $($(".no-heart")[i]).addClass("heart-active");
                $($(".heart-up")[i]).show();
                $($(".no-heart")[i]).hide();
            }
        });
    })
}