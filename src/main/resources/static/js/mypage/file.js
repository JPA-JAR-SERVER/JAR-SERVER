globalThis.uuids = [];

diaryService = (function(){

    function diary(id, callback) {
        $.ajax({
            url :'/mypage/diary-detail',
            type: 'get',
            data : id,
            success : function(result) {
                if(callback) {
                    callback(result);
                }
            }
        });
    }

    return {diary : diary}
})();



FileList.prototype.forEach = Array.prototype.forEach;
const $alertModal = $(".alert-modal-display");
const alertMsg = ['사진은 최소 1개이상 넣어 주세요.', '제목을 입력해주세요.', '내용을 입력해주세요.'];
// 이미지 파일
let files = [];
let $ul =$(".sumnail-box");
$("input[type=file]").on("change", function () {
    const $files = $("input[type=file]")[0].files;
    let formData = new FormData();

    if($files.length > 8) {
        alertModal("이미지는 8장까지 넣을 수 있습니다.");
        return false;
    }

    $($files).each((i, file) => {
        files.push(file);
    })

    console.log(files);

    $files.forEach((file, e) => {
        formData.append("file", file);
    })


    $.ajax({
        url: "/file/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            $files.forEach((file, i) => {
                if (file.type.startsWith("image")) {
                    let text = `
                            <li class="img_list" id="li${i}">
                            <div class="img_box_wrapper">
                                <header class="delete_button_wrapper">
                                    <label class="close-button" id="button${i}">
                                        <button icon-position="0" color="white" fill="false" type="button"
                                            class="pasing-button-1 pasing-no-select">
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
                                        <img src="/file/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.name}" class="inserted_img">
                                    </div>
                                </article>
                            </div>
                        </li>
                           `;
                    $ul.append(text);
                }
            });
        }
    });
});


// 이미지 지울 때
// 이미지 지울 때
$ul.on("click",".close-button", function(e){
    const dataTransfer = new DataTransfer();

    let target = $(e.currentTarget).parent().parent().parent();
    let fileArray = Array.from(files);
    let ul = target.parent();
    let i = ul.find("li").index(target);
    files = [];
    globalThis.uuids.splice(i, 1);
    fileArray.splice(i, 1);
    fileArray.forEach(file => {
        if(file.fileOriginalName == null && file.fileOriginalName == undefined){
            dataTransfer.items.add(file);
        }else {
            files.push(file);
        }
    })

    target.remove();
    dataTransfer.files.forEach((file, i) =>{
        files.push(file);
    });
});


$(".save-button").click(() => {
    const $files = $("input[type=file]")[0].files;
    let text = "";

    let boardTitle = $("input[name='boardTitle']").val();
    let boardContent = $(".diary-content").val();

    if (files.length < 1) {
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
})
/*****************************************************/
function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

function toStringByFormatting(source, delimiter = '/') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
}

// 이미지 제거
$(".attachment-layout").on("click", ".image-cancel-box", (e) => {
    const dataTransfer = new DataTransfer();

    let target = $(e.currentTarget).parent().parent().parent();
    console.log(target);
    let fileArray = Array.from(files);
    let ul = target.parent();
    let i = ul.find("li").index(target);
    files = [];

    fileArray.splice(i, 1);
    fileArray.forEach(file => {
        if(file.fileOriginalName == null && file.fileOriginalName == undefined){
            dataTransfer.items.add(file);
        }else {
            files.push(file);
        }
    })

    target.remove();
    dataTransfer.files.forEach((file, i) =>{
        files.push(file);
    });

});

function alertModal(errorMsg) {

    $(".alert").text(errorMsg);
    $alertModal.fadeIn();
    setTimeout(function () {
        $alertModal.fadeOut();
    }, 2000);

}