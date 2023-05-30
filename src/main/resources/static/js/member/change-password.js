
let userPassword = $("input[name=memberPassword]");
let userConfirmPassword = $("input[name=confirmPassword]");

// 비밀번호 눈표시 JS
$(".password_span").click(function(){
    if(userPassword.attr("type") == "password"){
        $("#eye_close").css('display', 'none');
        $("#eye_open").css('display', 'block');
        userPassword.attr("type","text");
    }else if(userPassword.attr("type") == "text"){
        $("#eye_close").css('display', 'block');
        $("#eye_open").css('display', 'none');
        userPassword.attr("type","password");
    }
});

$(".confirm_span").click(function(){
    if(userConfirmPassword.attr("type") == "password"){
        $("#eye_close_confirm").css('display', 'none');
        $("#eye_open_confirm").css('display', 'block');
        userConfirmPassword.attr("type","text");
    }else if(userConfirmPassword.attr("type") == "text"){
        $("#eye_close_confirm").css('display', 'block');
        $("#eye_open_confirm").css('display', 'none');
        userConfirmPassword.attr("type","password");
    }
});



let passwordInput = $("input[name=memberPassword]");
let passwordErrorMsg = $("#red_password");
let passwordFlag = false;

passwordInput.on("blur", function() {
    let password = passwordInput.val().trim();
    let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{8,32}$/;
    let passwordContinuity = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{8,32}$/

    if (password === "") {
        passwordErrorMsg.text("비밀번호를 입력해주세요.").show();
        passwordInput.addClass("error-input");
        passwordFlag = false;
        $(".next_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
        $(".next_btn").prop("disabled",true);
    } else if (!passwordRegex.test(password)) {
        passwordErrorMsg.text("비밀번호는 영문 대소문자, 숫자, 특수문자 중 2가지 이상 조합된 8자 ~ 32자만 가능합니다.").show();
        passwordInput.addClass("error-input");
        passwordFlag = false;
        $(".next_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
        $(".next_btn").prop("disabled",true);
    } else if (!passwordContinuity.test(password)) {
        passwordErrorMsg.text("3회 이상 연속되거나 동일한 숫자, 문자 등 타인이 쉽게 알아낼 수 있는 비밀번호를 사용할 수 없습니다.").show();
        passwordInput.addClass("error-input");
        passwordFlag = false;
        $(".next_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
        $(".next_btn").prop("disabled",true);
    } else {
        passwordErrorMsg.hide();
        passwordInput.removeClass("error-input");
        $("#red_password").css('display', 'none');
        $("input[name='memberPassword']").css('border-color', 'rgba(0, 0, 0, 0.15)');
        passwordFlag = true;
        if (passwordFlag && confirmPasswordFlag) {
            $(".link_btn").css("background-color", "rgb(255, 93, 0)");
            $(".link_btn").prop("disabled",false);
        }else{
            $(".link_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
            $(".link_btn").prop("disabled",true);
        }
    }
});

let confirmPasswordInput = $("input[name=confirmPassword]");
let confirmPasswordErrorMsg = $("#red_confirm_password");
let confirmPasswordFlag = false;

confirmPasswordInput.on("input", function() {
    let password = passwordInput.val().trim();
    let confirmPassword = confirmPasswordInput.val().trim();
    if (passwordInput.val() === confirmPasswordInput.val()) {
        confirmPasswordErrorMsg.hide();
        confirmPasswordInput.removeClass("error-input");
        confirmPasswordFlag = true;
        if (passwordFlag && confirmPasswordFlag) {
            $(".link_btn").prop("disabled",false);
            $(".link_btn").css("background-color", "rgb(255, 93, 0)");
        }else{
            $(".link_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
            $(".link_btn").prop("disabled",true);
        }
    } else {
        confirmPasswordErrorMsg.text("비밀번호가 일치하지 않습니다.").show();
        confirmPasswordInput.addClass("error-input");
        confirmPasswordFlag = false;
        $(".link_btn").css("background-color", "rgba(0, 0, 0, 0.1)");
        $(".link_btn").prop("disabled",true);
    }
});






/* 모달  */
function showModal(){
    $('.modal').css('display', 'block');
}

function closeModal(){
    $('.modal').css('display', 'none');
    location.href="/member/login"
}

let $confirmBtn = $(".link_btn");

$confirmBtn.on("click", function(){
    if(passwordFlag && confirmPasswordFlag){
        $.ajax({
            url:"/member/change-password",
            method:'post',
            data: {"memberEmail": memberEmail,
                    "memberPassword" : passwordInput.val()},
            success: function (result) {
                showModal();

            }
        });
    }else {
        $confirmBtn.prop('disabled', true);
        $confirmBtn.css('background-color', 'gray').css('cursor','default');
    }
})