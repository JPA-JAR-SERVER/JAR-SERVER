files = fileDTOS;

$(".modify-button").click(() => {
    const $files = fileDTOS;
    let text = "";
    let boardTitle = $("input[name='boardTitle']").val();
    let boardContent = $(".diary-content").val();

    if ($files.length < 1) {
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
        if (file.fileOriginalName == undefined && file.fileOriginalName == null) {
            text +=
                `
                        <input type="hidden" name="fileDTOS[${i}].fileOriginalName" value="${file.name}">
                        <input type="hidden" name="fileDTOS[${i}].fileUuid" value="${globalThis.uuids[count]}">
                        <input type="hidden" name="fileDTOS[${i}].filePath" value="${toStringByFormatting(new Date())}">
                        `;
            count++;
        } else {
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

imageText(fileDTOS);
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

//    =======================================
//input 창
let $diaryTitle = $(".diary-title");
let $diaryContent = $(".diary-content");
let $diaryButton = $(".insert-button");

if (!$diaryTitle.val() || !$diaryContent.val()) {
    $diaryButton.removeClass("save-active");
} else {
    $diaryButton.addClass("save-active");
}

$diaryTitle.keyup(() => {
    inputCheck();
});

$diaryContent.keyup(() => {
    inputCheck();
});

// 공개 비공개
const $check = $("input[type=checkbox]");
const $checkbox = $(".private-check-box");
const $checkText = $(".private-check-layout p");

$checkbox.click(() => {
    if (!$check.is(":checked")) {
        $checkbox.addClass("check-active");
        $checkText.text("공개");
        $check.val("OPEN");
    } else {
        $checkbox.removeClass("check-active");
        $checkText.text("비공개");
        $check.val("PRIVATE");
    }
});

function alertModal(errorMsg) {

    $(".alert").text(errorMsg);
    $alertModal.fadeIn();
    setTimeout(function () {
        $alertModal.fadeOut();
    }, 2000);

}

function inputCheck() {
    if (!$diaryTitle.val() || !$diaryContent.val()) {
        $diaryButton.removeClass("save-active");
    } else {
        $diaryButton.addClass("save-active");
    }
}