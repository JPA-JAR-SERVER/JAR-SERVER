// 이메일
const $emailInput = $("input[name=memberEmail]");
const $emailError = $(".error");

const $confirmBtn = $('.link_btn');
const $mainDiv = $('.center_box');

// 이메일 정규식 이벤트 사용 및 함수타
$emailInput.on("blur", function () {
    // 이메일 정규식
    let emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    /* 이메일 중복 확인 */

    $.ajax({
        url: '/member/check-email', //Controller에서 요청 받을 주소
        method: 'POST', //POST 방식으로 전달
        data: {"memberEmail": $emailInput.val()},
        success: function (result) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (result == 1) { // cnt가 1일 경우 -> 이미 존재하는 이메일
                $emailError.css("display", "block");
                $emailError.css('color', '#2bb673');
                $emailError.text(`Jar에 등록되어 있는 계정입니다.`);
                $confirmBtn.prop('disabled', false);
                $confirmBtn.css("background-color", "rgb(255, 93, 0)").css('cursor','pointer');
            } else if (!emailPattern.test($emailInput.val())) {
                $emailError.text("이메일 형식이 올바르지 않습니다.");
                $emailError.css("display", "block");
                $emailError.css('color', 'red');
                $confirmBtn.prop('disabled', true);1
                $confirmBtn.css('background-color', 'gray').css('cursor','default');
            } else {
                $emailError.text("Jar에 등록되어 있지 않은 계정입니다.");
                $emailError.css("display", "block");
                $emailError.css('color', 'red');
                $confirmBtn.prop('disabled', true);
                $confirmBtn.css('background-color', 'gray').css('cursor','default');
            }

        },
        error: function () {
            alert("에러 입니다");
        }
    });
});


$confirmBtn.on("click", function () {
    let text = '';
    $.ajax({
        url: '/member/sendEmail',
        method: 'POST',
        data: {"memberEmail": $emailInput.val()},
        success: function (result) {
            text = `<div class="login">
                            <div class="password_div">
                                <h3 class="password_div_h3">비밀번호 변경 이메일 발송 완료</h3>
                                <div class="pw_gap"></div>
                                <p class="pw_to_email">이메일을 확인하고 비밀번호를 변경 해주세요.</p>
                            </div>`
            $mainDiv.html(text);
        },
        error: function () {
            text = `<div class="login">
                            <div class="password_div">
                                <h3 class="password_div_h3">비밀번호 변경이 이메일 발송 실패</h3>
                                <div class="pw_gap"></div>
                                <p class="pw_to_email">이메일을 다시 한번 확인해 주세요.</p>
                            </div>`
            $mainDiv.html(text);
        }
    });
});

