let page = 0;

replyService.list({
    page: page,
    boardId: boardId
}, function (replies) {
    if (replies.content.length < 1) {
        let text = `
                                                            <div class="none-reply-display">
                                                                <div class="none-reply-box">
                                                                    <div class="none-reply-layout">
                                                                        <p>현재 댓글이 없습니다.</p>
                                                                    </div>
                                                                </div>
                                                            </div>
            `;
        $replyBox.html(text);
        $(".the-bogi").hide();
        return false;
    }

    if (replies.last) {
        $(".the-bogi").hide();
    }

    $replyBox.html(repliesContent(replies));
});

/* 댓글 */
const $registerButton = $(".register-button");
let $replyContent = $(".reply-content");
let $replyBox = $(".reply-box-container");

// 댓글 등록 시
$registerButton.click(() => {
    let replyRequestDTO = new Object();

    replyRequestDTO.memberId = memberId;
    replyRequestDTO.replyContent = $replyContent.val();
    replyRequestDTO.boardId = boardId;

    if (!$replyContent.val()) {
        return false;
    } else {
        replyService.save(replyRequestDTO, function () {
            $(".the-bogi").show();
            page = 0;
            replyService.list({page: page, boardId: boardId}, function (replies) {
                $replyBox.html(repliesContent(replies));
                $replyContent.val("");

                if (replies.last) {
                    $(".the-bogi").hide();
                }
            });
        });
    }
});


// 댓글 수정 버튼
$replyBox.on("click", "button.modify-button", function (e) {
    let i = e.target.id.replaceAll("modify", "");
    let reply = $($(".reply-content-text")[i]).text();

    $($(".reply-modify-content")[i]).val(reply);
    $($(".reply-cancel-button")[i]).show();
    $($("button.modify-button")[i]).hide();

    $($(".real-reply-text-box")[i]).hide();
    $($(".modify-content-box")[i]).show();

});

// 취소 버튼
$replyBox.on("click", "button.reply-cancel-button", function (e) {
    let i = e.target.id.replaceAll("cancel", "");
    $($(".modify-button")[i]).show();
    $($(".reply-cancel-button")[i]).hide();
    $($(".real-reply-text-box")[i]).show();
    $($(".modify-content-box")[i]).hide();
});

// 수정 완료 버튼
$replyBox.on("click", "button.confirm-button", function (e) {
    let i = e.target.id.replaceAll("confirm", "");
    let replyContent = $($(".reply-modify-content")[i]).val();
    let replyId = $($(".reply-modify-content")[i]).prop("id").replaceAll("reply", "");
    replyService.modify({
        replyId: replyId,
        replyContent: replyContent
    }, function () {
        $($(".modify-button")[i]).show();
        $($(".reply-cancel-button")[i]).hide();
        $($(".real-reply-text-box")[i]).show();
        $($(".modify-content-box")[i]).hide();
        $($(".reply-content-text")[i]).text(replyContent);
    });

});

// 삭제 버튼
$replyBox.on("click", "button.delete-button", function (e) {
    let i = e.target.id.replaceAll("delete", "");
    let replyId = $($(".reply-modify-content")[i]).prop("id").replaceAll("reply", "");
    page = 0;
    replyService.deleteReply({
        replyId: replyId
    }, function () {
        replyService.list({
            page: page,
            boardId: boardId
        }, function (replies) {
            $replyBox.html(repliesContent(replies));
            if (replies.last) {
                $(".the-bogi").hide();
            }
        })
    });
});

// 더보기 버튼
$(".the-bogi").click(() => {
    page++;
    replyService.list({
        page: page,
        boardId: boardId
    }, function (replies) {
        $replyBox.append(repliesContent(replies));

        if (replies.last) {
            $(".the-bogi").hide();
        }
    });
});


//    ===================================================== function
function repliesContent(replies) {
    let text = '';
    let replyDTO = replies.content;

    if (replyDTO.length < 1) {
        text = `
                                                            <div class="none-reply-display">
                                                                <div class="none-reply-box">
                                                                    <div class="none-reply-layout">
                                                                        <p>현재 댓글이 없습니다.</p>
                                                                    </div>
                                                                </div>
                                                            </div>
            `;
        return text;
    }

    $(replyDTO).each((i, reply) => {
        text += `
                                                                <div class="reply-text-box">
                                                                    <div class="reply-text-box-user">
                                                                        <section class="reply-user-box">
                                                                            <div class="reply-user-box-image" size="40">
                                                                                <span
                                                                                    class="reply-user-box-image-sapn-1  reply-user-box-image-sapn-2">
                                                                                    <picture class="reply-user-picture">
                                                                                    `;

        if (reply.memberDTO.fileDTO == null || reply.memberDTO.fileDTO == undefined) {
            text += `<img src="/image/board/default-user.png" sizes="100vw" alt="">`;
        } else {
            text += `<img src="/file/display?fileName=${reply.memberDTO.fileDTO.filePath}/${reply.memberDTO.fileDTO.fileUuid}_${reply.memberDTO.fileDTO.fileOriginalName}" sizes="100vw" alt="">`;
        }


        text += `</picture>
                                                                                </span>
                                                                            </div>
                                                                            <div class="reply-user-box-info">
                                                                                <strong
                                                                                    class="reply-user-box-info-name">${reply.memberDTO.memberNickname}</strong>
                                                                                <div class="reply-user-box-info-date">
                                                                                    ${date(reply.registerDate)}
                                                                                 </div>
                                                                            </div>
                                                                            <div class="trophy-box">
                                                                          `;
        if (reply.memberDTO.badgeType == 'THREE') {
            text += `<img src="/image/mypage/trophy_3.png">`;
        } else if (reply.memberDTO.badgeType == 'TWO') {
            text += `<img src="/image/mypage/trophy_2.png">`;
        } else if (reply.memberDTO.badgeType == 'ONE') {
            text += `<img src="/image/mypage/trophy_1.png">`;
        } else {
            text += `<img src="/image/mypage/trophy_1_5.png">`;
        }

        text += `</div>
                                                                        </section>
                                         `;
        if (reply.memberDTO.id == memberId && memberId != undefined) {
            text += `<div class="modify-button-box">
                                                        <button type="button" class="btn reply-cancel-button" id="cancel${i}">취소</button>
                                                        <button type="button" class="btn modify-button" id="modify${i}">수정</button>
                                                        <button type="button" class="btn delete-button" id="delete${i}">삭제</button>
                                                    </div>`;
        }

        text += `                                       </div>
                                                                    <div class="reply-text-box-text">
                                                                        <div class="reply-text-container" top="16"
                                                                            right="0" bottom="8" left="0">
                                                                            <div class="reply-text-container-box-1 reply-text-container-box"
                                                                                md="Body2">
                                                                                <div
                                                                                    class="reply-text-container-box-layout">
                                                                                    <div class="reply-text-container-box-layout-text"
                                                                                        size="200">
                                                                                        <div
                                                                                            class="real-reply-text-box">
                                                                                            <p class="reply-content-text">${reply.replyContent}</p>
                                                                                        </div>
                                                                                        <div class="modify-content-box">
                                                                                            <div
                                                                                                class="modify-content-layout">
                                                                                                <input type="text" id="reply${reply.id}"
                                                                                                    placeholder="댓글을 입력해주세요."
                                                                                                    class="reply-modify-content">
                                                                                                <button type="button"
                                                                                                    class="confirm-button" id="confirm${i}">완료</button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
            `;
    });
    return text;
}

function date(date) {
    let registerDate = new Date(date);
    return registerDate.getFullYear() + ". " + (numberFormat(registerDate.getMonth() + 1)) + ". " + numberFormat(registerDate.getDate());
}