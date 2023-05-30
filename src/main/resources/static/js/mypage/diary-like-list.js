const $ul = $(".list-box-ul");
let page = 0;

diaryService = (function () {
    function list(page, callback){
        $.ajax({
            url: '/mypage/diary-like-list-content',
            type: 'get',
            data: page,
            success: function (list) {
                if(callback) {
                    callback(list);
                }
            }
        });
    }

    return {
        list: list,
    }
})();

getList(page, memberId);



$(".paging-layout").on("click", "a", function (e) {
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

    getList(page, memberId);
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
    let text = '';
    let diaryLikeDTOS = list.content;
    $(diaryLikeDTOS).each((i, diaryLikeDTO) => {
        text += `
                                            <li class="content-li">
                                                    <div class="content-image">
                                                        <span class="content-image-span">
                                                        <a href="/board/diary/detail/${diaryLikeDTO.diaryDTO.id}">
                                                            <picture class="picture-postion">
                                                            `;
                                                            <!-- 사진 -->
                                                            if (diaryLikeDTO.diaryDTO.fileDTOS.length == 0 ||diaryLikeDTO.diaryDTO.fileDTOS == null || diaryLikeDTO.diaryDTO.fileDTOS == undefined) {
                                                                text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
                                                            } else {
                                                                for (let j = 0; j < diaryLikeDTO.diaryDTO.fileDTOS.length; j++) {
                                                                    if (diaryLikeDTO.diaryDTO.fileDTOS[j].fileType === "REPRESENTATIVE") {
                                                                        text += `<img src="/file/display?fileName=${diaryLikeDTO.diaryDTO.fileDTOS[j].filePath}/${diaryLikeDTO.diaryDTO.fileDTOS[j].fileUuid}_${diaryLikeDTO.diaryDTO.fileDTOS[j].fileOriginalName}">`;
                                                                    }
                                                                }
                                                            }
                                                text +=           `</picture>
                                                            </a>
                                                            <div class="heart-box">
                                                                <button class="heart-layout" id="heart${diaryLikeDTO.diaryDTO.id}">
                                                                      <span class="auto-flex no-heart" style="display:none;">
                                                                        <svg xmlns="http://www.w3.org/2000/svg"
                                                                            width="24" height="24" fill="none"
                                                                            viewBox="0 0 24 24">
                                                                            <path fill-rule="evenodd"
                                                                                d="M20.5 9c0-2-1.5-3.9-3.7-3.9-2.3 0-3.8 1.63-4.8 3.33-1-1.7-2.5-3.33-4.8-3.33C5 5.1 3.5 6.867 3.5 9c0 4.62 4.949 7.667 8.5 9.623 3.551-1.956 8.5-5.003 8.5-9.623zm-19-.176C1.5 5.607 3.962 3 7 3c2.7 0 4 1 5 2.2C13 4 14.3 3 17 3c3.038 0 5.5 2.607 5.5 5.824C22.5 14.827 16.684 18.52 12 21 7.316 18.52 1.5 14.827 1.5 8.824z"
                                                                                fill="#FFF"></path>
                                                                        </svg>
                                                                    </span>
                                                                    <span class="auto-flex heart-up">
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
                                                        ${diaryLikeDTO.diaryDTO.boardTitle}
                                                    </div>
<!--                                                    <div class="content2">-->
<!--                                                        상시 이벤트-->
<!--                                                    </div>-->
                                                    <div class="liked-box">
                                                        <div class="flex">
                                                            <div class="liked-layout" id="heart${diaryLikeDTO.diaryDTO.id}">
                                                                <div class="icon-box">
                                                                    <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="none" viewBox="0 0 24 24"><path fill-rule="evenodd" d="M1.5 8.824C1.5 5.607 3.962 3 7 3c2.5 0 4 1.5 5 3 1-1.5 2.5-3 5-3 3.038 0 5.5 2.607 5.5 5.824C22.5 14.827 16.684 18.52 12 21 7.316 18.52 1.5 14.827 1.5 8.824z" fill="#d7d7d7"></path></svg>
                                                                </div>
                                                                <p class="like-count">${diaryLikeDTO.diaryDTO.likeCount}</p>
                                                            </div>
                                                            <div class="liked-layout">
                                                                <div class="icon-box">
                                                                    <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="none" viewBox="0 0 24 24"><path fill-rule="evenodd" d="M2 10.68c0-.691.503-1.251 1.135-1.251h2.273V22H3.135C2.508 22 2 21.427 2 20.749V10.68zm11.361-2.508h6.366c1.255 0 2.272 1.126 2.272 2.514 0 .151-.012.301-.037.45l-1.616 9.832C20.25 21.566 19.779 22 19.23 22H7.681V9.429L11.089 0c1.704 0 2.84 1.257 2.84 3.143 0 1.257-.189 2.933-.568 5.029z" fill="#d7d7d7"></path></svg>
                                                                </div>
                                                                <p class="reply-count">${diaryLikeDTO.diaryDTO.replyCount}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                            </li>
        
                                                `;

    });
    return text;
}

function getList(page, memberId) {
    diaryService.list({
        page: page,
        memberId : memberId
    }, function (list){
        $ul.append(listText(list));
        displayPagination(list.totalPages);
    });
}

function  getNewList(page, memberId) {
    diaryService.list({
        page: page,
        memberId : memberId
    }, function (list) {
        $ul.html(listText(list));
        displayPagination(list.totalPages);
    })
}