$("input[type=radio]").each((i, e) => {

    if($(e).val() == suggestDTO.boardType) {
        $("input[type=radio]").prop("checked", false);
        $challengeButton.removeClass("radio-active");
        $(e).prop("checked", true);
        $($challengeButton[i]).addClass("radio-active");
    }
});

$(".modify-button").click(() => {
    let text = "";

    let boardTitle = $("input[name='boardTitle']").val();
    let boardContent = $(".proposal_content").val();

    if (files.length < 4) {
        alertModal(alertMsg[0]);
        return false;
    } else if (!boardTitle) {
        alertModal(alertMsg[1]);
        return false;
    } else if (!boardContent) {
        alertModal(alertMsg[2]);
        return false;
    }


    FileList.prototype.forEach = Array.prototype.forEach;
    let count = 0;
    files.forEach((file, i) => {
        if(file.fileOriginalName == undefined && file.fileOriginalName == null){
            text +=
                `
                        <input type="hidden" name="fileDTOS[${i}].fileOriginalName" value="${file.name}">
                        <input type="hidden" name="fileDTOS[${i}].fileUuid" value="${globalThis.uuids[count]}">
                        <input type="hidden" name="fileDTOS[${i}].filePath" value="${toStringByFormatting(new Date())}">
                        `;
            count++;
        }else {
            text +=
                `
                <input type="hidden" name="fileDTOS[${i}].fileOriginalName" value="${file.fileOriginalName}">
                <input type="hidden" name="fileDTOS[${i}].fileUuid" value="${file.fileUuid}">
                <input type="hidden" name="fileDTOS[${i}].filePath" value="${file.filePath}">
                `;
        }

    });
    $("form[name=form]").append(text);
    $("form[name=form]").submit();
});

imageText(suggestDTO.fileDTOS);
function imageText(files){
    files.forEach((file, i) => {
        let text = `
                        <li class="img_list" id="li${i}">
                            <div class="img_box_wrapper">
                                <header class="delete_button_wrapper">
                                    <label class="close-button">
                                        <button icon-position="0" color="white" fill="false" type="button"
                                            class="pasing-button-1 pasing-no-select" id="button${i}">
                                            <span class="pasing-button-span">
                                                <svg xmlns="http://www.w3.org/2000/svg"
                                                    width="24" height="24" fill="none"
                                                    viewBox="0 0 24 24">
                                                    <path
                                                        d="M18.5 4L12 10.5 5.5 4 4 5.5l6.5 6.5L4 18.5 5.5 20l6.5-6.5 6.5 6.5 1.5-1.5-6.5-6.5L20 5.5 18.5 4z"
                                                        fill="#cacaca"></path>
                                                </svg>
                                            </span>
                                        </button>
                                    </label>
                                </header>
                                <article class="img_wrapper">
                                    <div class="img_div">
                                        <img src="/file/display?fileName=${file.filePath}/${file.fileUuid}_${file.fileOriginalName}" class="inserted_img">
                                    </div>
                                </article>
                            </div>
                        </li>
                `;
        $ul.append(text);
    })
}