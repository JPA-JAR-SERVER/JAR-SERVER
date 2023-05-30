// 썸네일
$("input[name='file']").on("change", function (e) {
    e.preventDefault();
    const $file = $("input[name=file]")[0].files[0];
    profile = $file;
    let formData = new FormData();
    formData.append("file", $file);

    $.ajax({
        url: "/file/upload-file",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            if ($file.type.startsWith("image")) {
                $(".profile-image").attr("src", `/file/display?fileName=${toStringByFormatting(new Date())}/t_${uuid}_${$file.name}`);
            } else {
                alert("이미지 파일만 넣어주세요.");
            }
        }
    });
});

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