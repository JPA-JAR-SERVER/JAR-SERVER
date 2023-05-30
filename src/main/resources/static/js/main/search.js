const $listResults = $("#inquiryTable tbody");
const allSearch = {
    inquiryEmail: null,
    inquiryPhoneNumber: null,
    inquiryContent: null
};

function getList() {
    let url = "/main/search";
    // 검색 조건이 비어있을 때에만 allSearch 객체를 전송.
    if (allSearch.inquiryEmail || allSearch.inquiryPhoneNumber || allSearch.inquiryContent) {
        $.ajax({
            url: url,
            data: allSearch,
            success: function(data) {
                if (data.content.length > 0) {
                    showList(data);
                } else { //검색 결과가 없을 때
                    
                }
            }
        });
    } else {
        // 검색 조건이 없을 때
        $listResults.empty(); // 이전 검색 결과를 지우기 위해 빈 내용으로 초기화
        $.ajax({
            url: url,
            success: function(data) {
                showList(data);

                if (data.content.length === 0) {
                    
                }
            }
        });
    }

}


// 검색 기능 구현
$(".search__searchbox__button").on("click", function (e) {
    e.preventDefault();
    $listResults.empty();
    let $search = $("#searchbox").val(); //input 입력값
    allSearch.inquiryEmail = $search;
    allSearch.inquiryPhoneNumber = $search;
    allSearch.inquiryContent = $search;

    getList(); // 전체 목록 가져오기
});

getList();

