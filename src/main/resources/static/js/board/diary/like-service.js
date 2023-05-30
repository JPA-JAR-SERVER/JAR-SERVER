let likeService = (function () {
    // 좋아요 했는지 여부 확인
    function heartCheck(heart, callback) {
        $.ajax({
            url: "/diary/heart/heart-check",
            type: "post",
            data: JSON.stringify(heart),
            contentType: "application/json;charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    // 좋아요 카운트 ++
    function heartUp(heart, callback) {
        $.ajax({
            url: "/diary/heart/heartUp",
            type: "post",
            data: JSON.stringify(heart),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }

            }
        });
    }

    // 좋아요 카운트 --
    function heartDown(heart, callback) {
        $.ajax({
            url: "/diary/heart/heartDown",
            type: "delete",
            data: JSON.stringify(heart),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    // 게시판의 좋아요 갯수
    function count(heart, callback) {
        $.ajax({
            url: "/diary/heart/heartCount",
            type: "get",
            data: heart,
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    return {
        heartCheck: heartCheck,
        heartUp: heartUp,
        heartDown: heartDown,
        count: count
    }
})();