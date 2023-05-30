const $ul = $(".content-ul");
let page = 0;
let sort = "recent";
list(page, sort);

// 기존 리스트 불러오기
function list(page, sort) {

    $.ajax({
        url: '/board/diary/list-content',
        type: 'get',
        data: {page: page, sort: sort},
        success: function (list) {
            $ul.append(listText(list));
            heartCheck(list);
        }
    });

}

// 인기순 최신순 눌럿을때 모든 요소 사라지고 나와야 하기때문에
// .html
function newList(page, sort) {
    $.ajax({
        url: '/board/diary/list-content',
        type: 'get',
        data: {page: page, sort: sort},
        success: function (list) {
            $ul.html(listText(list));
            heartCheck(list);
        }
    });
}

//    --------------
// 리스트 갯수만큼 dom
function listText(list) {

    let diaryDTOS = list.content;
    let text = ``;
    diaryDTOS.forEach((diaryDTO, i) => {
        text += `
                                             <li class="content-li" id="${i}">
                                                <div class="content-flex">
                                                        <div class="content-box">
                                                            <div class="content-image-box">
                                                                <span class="content-image-layout">
                                                                    <picture>
                                                                    `;
        if (diaryDTO.fileDTOS.length == 0) {
            text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
        } else {
            for (let j = 0; j < diaryDTO.fileDTOS.length; j++) {
                if (diaryDTO.fileDTOS[j].fileType === "REPRESENTATIVE") {
                    text += `<img class="list-image"  src="/file/display?fileName=${diaryDTO.fileDTOS[j].filePath}/${diaryDTO.fileDTOS[j].fileUuid}_${diaryDTO.fileDTOS[j].fileOriginalName}">`;
                }
            }
        }
        text += `</picture>
                                                                </span>
                                                            </div>
                                                            <div class="content-hover">
                                                                <a href="/board/diary/detail/${diaryDTO.id}">
                                                                 <div style="position: absolute; width: 100%; height: 100%;"></div>
                                                                  </a>
                                                                 <div class="heart-box">
                                                                        <button type="button" class="heart-layout" id="heart${diaryDTO.id}">
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
                                                                <div class="hover-box">
                                                                    <div class="content-title-box">${diaryDTO.boardTitle}</div>
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
                                                                                <p class="like-count">${diaryDTO.likeCount}</p>
                                                                            </div>
                                                                            <div class="liked-layout">
                                                                                <div class="icon-box">
                                                                                    <img src="/image/board/reply-icon.png" class="reply-icon">
                                                                                </div>
                                                                                <p class="reply-count">${diaryDTO.replyCount}</p>
                                                                            </div>
                                                                        </div>
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