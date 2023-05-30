const $ul =$(".list-box")
let page = 0;
// page = $('.paging-active div').text();

suggestService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/mypage/propsal-list',
            type: 'get',
            data: page,
            success: function(list){
                if(callback){
                    callback(list);
                }
            }
        });
    }

    return {
        list: list,
    }
})();

getList(page, memberId);


$(".paging-layout").on("click", "a", function (e) {
    e.preventDefault();
    $ul.empty();
    const targetPage = $(this).text();

    if ($(this).hasClass("arrow")) {
        // 화살표 클릭 시 이전 페이지 또는 다음 페이지로 이동
        if ($(this).attr("id") === "prev") {
            page--;
        } else if ($(this).attr("id") === "next") {
            page++;
        }
    } else {
        // 페이지 번호 클릭 시 해당 페이지로 이동
        page = parseInt(targetPage) - 1;
    }

    getList(page, memberId);
});

function displayPagination(totalPages) {
    const $pagination = $(".paging-layout");
    $pagination.empty();

    if (page > 0) {
        $pagination.append("<a href='#' class='arrow' id='prev'><img class='prev'  src='/image/mypage/arrow.png'></a>");
    }

    for (let i = 1; i <= totalPages; i++) {
        if (i === page + 1) {
            // 현재 페이지를 텍스트로 표시
            $pagination.append(
                "<div class='paging-border-none paging-active'><span class='page'>" + i + "</span></div>"
            );
        } else {
            // 다른 페이지는 a 태그로 표시
            $pagination.append("<div class='paging-border-none'><a href='#' class='page'>" + i + "</a></div>");
        }
    }
    if (page < totalPages - 1) {
        $pagination.append("<a href='#' class='arrow' id='next'><img class='next' src='/image/mypage/arrow.png'></a>");
    }
}


function listText(list) {
    let suggestDTOS = list.content;
    let text = '';

    $(suggestDTOS).each((i, suggestDTO) => {
    var createDate = getDate(suggestDTO.createdDate);
        text += `
                                        <div class="list-layout">
                                            <div class="flex-between">
                                                <p class="date">${createDate}</p>
                                                <div>
                                                    <button type="button" class="btn modify-btn" onclick="location.href='/board/suggest/modify/${suggestDTO.id}'">수정</button>
                                                    <button type="button" class="btn delete-btn" onclick="deleteSuggest(${suggestDTO.id})">삭제</button>
                                                </div>
                                            </div>
                                            <a href="/board/suggest/detail/${suggestDTO.id}">
                                                <div class="list-content-title-box">
                                                    <h3>${suggestDTO.boardTitle}</h3>
                                                </div>
                                            </a>
                                        </div>              
    `;


    });
    return text;

}






function getList(page, memberId) {
    suggestService.list({
        page: page,
        memberId : memberId
    }, function (list) {
        $ul.append(listText(list));
        displayPagination(list.totalPages);


    });
}

function getNewList(page, memberId) {
    suggestService.list({
        page: page,
        memberId : memberId
    }, function (list) {
        $ul.html(listText(list));
        displayPagination(list.totalPages);

    });
}

function getDate(register){
    const newDate = new Date(register);
    const year = newDate.getFullYear();
    const month = newDate.getMonth() + 1;
    const date = newDate.getDate();

    return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
    // 2021-01-01
}


function deleteSuggest(boardId) {
    $.ajax({
        url: `/mypage/delete-suggest/${boardId}`,
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify(boardId),
        traditional: true,
        success: function() {
            suggestService;
            getNewList(page, memberId);
        }
    });
}