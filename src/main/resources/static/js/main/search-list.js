if(groupChallengeDTO.length == 0){
    let text = `
            <div>
                        <span>
                            검색 결과가 없습니다.
                        </span>
            </div>
        `;
    $(".content-ul").css("margin", "0px");
    $(".group-challenge-ul").append(text);
}
groupChallengeDTO.forEach((groupChallengeDTO, i) => {
    let groupChallengeFileDTOS = groupChallengeDTO.fileDTOS;
    let text = ``;
    text += `
                                   <li class="content-li">
                                            <div class="content-flex">
                                                <a href="/board/challenge/group/detail/${groupChallengeDTO.id}">
                                                    <div class="content-box">
                                                        <div class="content-image-box">
                                                                <span class="content-image-layout">
                                                                    <picture class="groupChallenge_img">`;
    if (groupChallengeFileDTOS != null || groupChallengeFileDTOS != undefined) {
        groupChallengeFileDTOS.forEach((fileDTO, i) => {
            if (fileDTO.fileType === "REPRESENTATIVE") {
                text += `
                                                  <img style="object-fit: cover;" src="/file/display?fileName=${fileDTO.filePath}/${fileDTO.fileUuid}_${fileDTO.fileOriginalName}" class="inserted_img">
                                                `;
            }

        });
    }
    text +=                   ` </picture>
                                                                </span>
                                                        </div>
                                                        <div class="content-title">
                                                            ${groupChallengeDTO.boardTitle}
                                                        </div>
                                                        <div class="content2">
                                                            ${groupChallengeDTO.boardContent}
                                                        </div>
                                                        <div class="liked-box">
                                                            <div class="flex">
                                                                <div class="liked-layout">
                                                                    <div class="icon-box">
                                                                        <img src="/image/board/human-icon.png"
                                                                             class="human-icon">
                                                                    </div>
                                                                    <p class="attend-count">
                                                                        ${groupChallengeDTO.attendCount}
                                                                    </p>
                                                                </div>
                                                                <div class="liked-layout">
                                                                    <div class="icon-box">
                                                                        <img src="/image/board/reply-icon.png"
                                                                             class="reply-icon">
                                                                    </div>
                                                                    <p class="reply-count">
                                                                        ${groupChallengeDTO.replyCount}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </li>

        `;
    $(".group-challenge-ul").append(text);
});

if(suggestDTO.length == 0){
    let text = `<div>
                        <span>
                            검색 결과가 없습니다.
                        </span>
                    </div>
                    `;
    $(".content-ul").css("margin", "0px");
    $ul.append(text);
}else {
    suggestDTO.forEach((suggestDTO, i) => {
        let suggestFileDTOS = suggestDTO.fileDTOS;
        let text = `
                                <li class="content-li">
                                            <div class="content-flex">
                                                    <div class="content-box">
                                                        <div class="content-image-box">
                                                                <span class="content-image-layout">
                                                                <a href="/board/suggest/detail/${suggestDTO.id}">
                                                                    <picture class="suggest_img">
                                                                    `;
        if (suggestFileDTOS != null || suggestFileDTOS != undefined) {
            suggestFileDTOS.forEach((fileDTO, i) => {
                if (fileDTO.fileType === "REPRESENTATIVE") {
                    text += `
                               <img style="object-fit: cover;" src="/file/display?fileName=${fileDTO.filePath}/${fileDTO.fileUuid}_${fileDTO.fileOriginalName}" class="inserted_img">
                                                        `;
                }
            });
        }
        text+= `</picture>
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
                                                            ${suggestDTO.boardContent}
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
                                                                    <p class="like-count">
                                                                        ${suggestDTO.likeCount}
                                                                    </p>
                                                                </div>
                                                                <div class="liked-layout">
                                                                    <div class="icon-box">
                                                                        <img src="/image/board/reply-icon.png"
                                                                             class="reply-icon">
                                                                    </div>
                                                                    <p class="reply-count">
                                                                        ${suggestDTO.replyCount}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                            </div>
                                        </li>

        `;
        $ul.append(text);
    });
}