attendService = (function () {

    //  참여 버튼
    function attend(data, callback) {
        $.ajax({
            url: '/challenge/attend/insert',
            type: 'post',
            data: data,
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    // 참여 여부
    function attendCheck(data, callback) {
        $.ajax({
            url: '/challenge/attend/check',
            type: 'get',
            data: data,
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        })
    }

    // 참여 취소
    function attendCancel(data, callback) {
        $.ajax({
            url: '/challenge/attend/delete',
            type: "delete",
            data: data,
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    // 참여 완료
    function challengeSuccess(data, callback) {
        $.ajax({
            url: '/challenge/attend/update',
            type: 'patch',
            data: data,
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    // 참여 완료햇엇는지
    function successCheck(data, callback) {
        $.ajax({
            url: '/challenge/attend/success-check',
            type: 'get',
            data: data,
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        })
    }

    return {
        attend: attend,
        attendCheck: attendCheck,
        attendCancel: attendCancel,
        challengeSuccess: challengeSuccess,
        successCheck: successCheck
    }
})();

// 참여 완료햇엇는지, 참여 하기만 눌럿는지 확인
attendService.attendCheck({
    boardId: boardId,
    memberId: memberId
}, function (result) {
    if (result) {
        $(".attend-button").show();
        $(".challenge-button-box").hide();
    } else {
        attendService.successCheck({
            boardId: boardId,
            memberId: memberId
        }, function (check) {
            if (check) {
                $(".attend-button").hide();
                $(".challenge-button-box").hide();
                $(".challenge-complete").show();
            } else {
                $(".attend-button").hide();
                $(".challenge-button-box").show();
            }
        });
    }
});


// 참여 버튼
$(".attend-button").click(() => {
    attendService.attend({
        boardId: boardId,
        memberId: memberId
    }, function () {
        $(".attend-button").hide();
        $(".challenge-button-box").show();
    });
});


// 참여 취소 버튼
$(".attend-cancel").click(() => {
    attendService.attendCancel({
        boardId: boardId,
        memberId: memberId
    }, function () {
        $(".attend-button").show();
        $(".challenge-button-box").hide();
    });
});

// 참여 완료 버튼
$(".complete-button").click(() => {
    attendService.challengeSuccess({
        boardId: boardId,
        memberId: memberId
    }, function () {
        $(".challenge-complete").show();
        $(".attend-button").hide();
        $(".challenge-button-box").hide();
    });
});