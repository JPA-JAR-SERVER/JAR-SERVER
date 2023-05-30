$(".btn").each((i, e) => {
    $(e).click(() => {
        if(!$(e).hasClass("active")){
            $(".btn").removeClass("active");
            $(e).addClass("active");
        }else {
            $(e).removeClass("active");
        }
    });
});

const $page = $(".paging-border-none");

$page.each((i, e) => {
    $(e).click(() => {
        $page.removeClass("paging-active");
        $(e).addClass("paging-active");
    });
});
