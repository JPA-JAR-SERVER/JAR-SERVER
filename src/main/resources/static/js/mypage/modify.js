globalThis.code;

$(".toggle-box").click(() => {
    if ($(".accept").is(':checked')) {
        $(".toggle-box").removeClass("toggle-active");
    } else {
        $(".toggle-box").addClass("toggle-active");
    }
});

//  이름 검사
const $nameInput = $("input[name=memberName]");
let nameCheck = false;

$nameInput.blur(() => {
    if (!$nameInput.val()) {
        nameCheck = false;
        $(".name-error").show();
        $(".name-error").text("이름을 입력해주세요.");
    } else {
        $(".name-error").hide();
        nameCheck = true;
    }
    check();
});

// 닉네임 검사
let nickNameCheck = false;
let nicknameInput = $("input[name=memberNickname]")
let nicknameErrorMsg = $(".nick-name-error");

nicknameInput.on("blur", function () {
    $.ajax({
        url: "/member/check-nickname",
        type: "post",
        data: {"memberNickname": nicknameInput.val()},
        success: function (result) {
            if (result != 0) {
                nicknameErrorMsg.text("중복된 닉네임입니다.").show();
                nicknameInput.addClass("error-input");
                nicknameErrorMsg.css('color', 'rgb(243, 33, 59)');
                nickNameCheck = false;
                $(".next_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
                $(".next_btn").prop("disabled", true);
            } else if (nicknameInput.length > 20 || nicknameInput.val().length < 2) {
                nicknameErrorMsg.text("닉네임은 최소 2자 입니다.").show();
                nicknameInput.addClass("error-input");
                nicknameErrorMsg.css('color', 'rgb(243, 33, 59)');
                nickNameCheck = false;
                $(".next_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
                $(".next_btn").prop("disabled", true);
            } else {
                nicknameErrorMsg.text("").show();
                nicknameErrorMsg.css('color', '#2bb673');
                nicknameInput.removeClass("error-input");
                nickNameCheck = true;
            }
            check();
        }
    });
})

// 핸드폰 모달 관련 js
$(".phone-number-modify-button").click(() => {
    $(".phone-modal-display").show();
    let phoneNumber = $(".phone_number").val();
    $("input[name='phoneNumber']").val(phoneNumber);
    $phoneErrorMsg.text("");
    $(".auth-msg").text("");
});

$(".exit").each((i, e) => {
    $(e).click(() => {
        $(".phone-modal-display").hide();
        $sendAuthButton.attr("disabled", true);
        $phoneNumberInput.attr("disabled", false);
        $phoneErrorMsg.text("");
        $phoneNumberInput.val("");
        $(".auth-box").hide();
        $("auth-msg").text("");
        $codeInput.val("");
    });
});

// 핸드폰 검사
const $phoneErrorMsg = $(".phone-number-error");
const regPhone = /^010([0|1|6|7|8|9])?([0-9]{4})?([0-9]{4})$/;
const $phoneNumberInput = $("input[name='phoneNumber']");
const $sendAuthButton = $(".send-auth-button");

$phoneNumberInput.blur(() => {

    $.ajax({
        url: "/member/check-phone",
        type: "post",
        data: {"memberPhoneNumber": $phoneNumberInput.val()},
        success: function (result) {
            if (result == 1) {
                $sendAuthButton.attr("disabled", true);
                $phoneErrorMsg.text("중복된 핸드폰 번호입니다.");
            } else if (!$phoneNumberInput.val()) {
                $sendAuthButton.attr("disabled", true);
                $phoneErrorMsg.text("핸드폰 번호를 입력해주세요.");

            } else if (!regPhone.test($phoneNumberInput.val())) {
                $sendAuthButton.attr("disabled", true);
                $phoneErrorMsg.text("형식에 맞게 입력해주세요.");

            } else {
                $sendAuthButton.attr("disabled", false);
                $phoneErrorMsg.text("");
            }
        }
    });
});

$sendAuthButton.click(() => {
    $.ajax({
        type: "POST",
        url: "/member/sendCode",
        data: {memberPhone: $phoneNumberInput.val()},
        success: function (data) {
            console.log(data);
            globalThis.code = data;
            $(".auth-box").show();
            $sendAuthButton.attr("disabled", true);
            $phoneNumberInput.attr("disabled", true);
            $(".auth-msg").text("인증번호가 발송되었습니다.");
        }
    });
});

// 인증번호 검사
const $codeInput = $("input[name=code]");
const $codeCheckButton = $(".auth-check-button");
4

$codeInput.blur(() => {

    if (!$codeInput.val()) {
        $codeCheckButton.attr("disabled", true);
        $(".auth-msg").css("color", "red");
        $(".auth-msg").text("인증번호를 입력해주세요.");
    } else {
        $(".auth-msg").text("");
        $(".auth-msg").css("color", "#36f");
        $codeInput.attr("disabled", false);
        $codeCheckButton.attr("disabled", false);
    }
});

// 인증번호 검사 버튼
$codeCheckButton.click(() => {
    if ($codeInput.val() == globalThis.code) {
        $(".phone-number-change-button").attr("disabled", false);
        $(".auth-msg").css("color", "#36f");
        $(".auth-msg").text("인증번호가 일치합니다.");
    } else {
        $(".phone-number-change-button").attr("disabled", true);
        $(".auth-msg").css("color", "red");
        $(".auth-msg").text("인증번호가 일치하지 않습니다.");
    }
});

// 핸드폰 번호 변경
let $phoneConfirmButton = $(".phone-number-change-button");

$phoneConfirmButton.click(() => {
    let phoneNumber = $("input[name='phoneNumber']").val();
    $(".phone_number").val(phoneNumber);
    $(".phone-modal-display").hide();
});

function check() {
    if (nameCheck && nickNameCheck) {
        $(".modify-button").attr("disabled", false);
    } else {
        $(".modify-button").attr("disabled", true);
    }
}


// 회원탈퇴 모달
const $withdrawModal = $(".withdraw-open");
$withdrawModal.click(() => {
    $(".withdraw-display").show();
});

$(".exit-withdraw").each((i, e) => {
    $(e).click(() => {
        $(".withdraw-display").hide();
        $(".reson-textarea").val("");
    });
});


$(".modify-accept").click(() => {
    let phoneNumber = $(".phone_number").val();
    let text = '';
    if (profile != null || profile != undefined) {


        if (profile.fileOriginalName == undefined && profile.fileOriginalName == null) {
            text =
                `
                        <input type="hidden" name="fileDTO.fileOriginalName" value="${profile.name}">
                        <input type="hidden" name="fileDTO.fileUuid" value="${globalThis.uuid}">
                        <input type="hidden" name="fileDTO.filePath" value="${toStringByFormatting(new Date())}">
                        `;
        } else {
            text =
                `
                <input type="hidden" name="fileDTO.fileOriginalName" value="${profile.fileOriginalName}">
                <input type="hidden" name="fileDTO.fileUuid" value="${profile.fileUuid}">
                <input type="hidden" name="fileDTO.filePath" value="${profile.filePath}">
                `;
        }
    }
    text += `<input type="hidden" name="memberPhoneNumber" value="${phoneNumber}">`;
    $("form[name=form]").append(text);
    $("form[name=form]").submit();
});