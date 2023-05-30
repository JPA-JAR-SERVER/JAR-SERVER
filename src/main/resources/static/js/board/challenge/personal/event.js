let $selectButton = $(".select-button");
$selectButton.each((i, e) => {
    $(e).click(() => {
        $selectButton.removeClass("select-button-active");
        $(e).addClass("select-button-active");
    });
});