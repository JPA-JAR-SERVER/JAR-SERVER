
const $first_slide = $(".first-content-slide");
groupListText(groupChallengeDTOS);
function groupListText(list) {
    let groupChallengeDTOS = list;


    $(groupChallengeDTOS).each((i, groupChallengeDTO) => {
        let text =  '';
        text +=                                 `<div class="content-image-box first-content-box">
                                                     <a href="/board/challenge/group/detail/${groupChallengeDTO.id}">
                 
                                                        <div class="content-image">
                                                            <span class="content-image-span">
                                                                <picture class="picture-position">
                                                                `;
                                                                    if(groupChallengeDTO.fileDTOS == null || groupChallengeDTO.fileDTOS == undefined){
                                                                        text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
                                                                    }else {
                                                                        for (let  j= 0; j < groupChallengeDTO.fileDTOS.length; j++){
                                                                            if(groupChallengeDTO.fileDTOS[j].fileType === "REPRESENTATIVE"){
                                                                                text += `<img class="list-image"  src="/file/display?fileName=${groupChallengeDTO.fileDTOS[j].filePath}/${groupChallengeDTO.fileDTOS[j].fileUuid}_${groupChallengeDTO.fileDTOS[j].fileOriginalName}">`;
                                                                            }
                                                                        }
                                                                    }

        text +=                                                `</picture>
                                                            </span>
                                                        </div>
                                                        <!-- 제목 -->
                                                        <div class="content-title">${groupChallengeDTO.boardTitle}</div>
                                                        <!-- 내용 -->
                                                        <div class="content">${groupChallengeDTO.boardContent}</div>
                                                        <div style="display: flex; margin-top: 10px;">
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
                                                    </a>
                                                </div>

        `;
        $first_slide.append(text);
    });

}