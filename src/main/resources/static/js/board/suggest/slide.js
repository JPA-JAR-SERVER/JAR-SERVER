//  이미지 받아서 이미지 갯수 + 2개 만큼 태그 만들기

const $imageModal = $(".image-expansion-display");
const $image = $(".image");
const $modalExitButtton = $(".cancel-button");
const $imageWrap = $(".swiper-wrapper");
const width = 896;

// 이미지 정보 Array객체로 받기

let totalWidth = width * (fileDTOS.length + 2);
// 슬라이드 요소 감싸고잇는 div의 width 설정
$imageWrap.css("width", totalWidth);
$(".all-count").text(numberFormat(fileDTOS.length));


createImageSlideDiv(fileDTOS);

function createImageSlideDiv(fileDTOS) {
    let length = fileDTOS.length;
    let text = "";

    fileDTOS.forEach((file, i) => {
        text += `
                                <div class="swiper-slide swiper-slide-active" style="width: 896px;">
                                    <span class="swiper-slide-span" ratio="0.75">
                                        <picture ratio="0.75" class="swiper-slide-picture image-container-picture">
                                            <img sizes=" 100vw" src="/file/display?fileName=${file.filePath}/${file.fileUuid}_${file.fileOriginalName}">
                                        </picture>
                                    </span>
                                </div>
           `;
    });
    $imageWrap.html(text);
    createAppendAndPrependImageDiv();
}


function createAppendAndPrependImageDiv() {
    let firstImage = fileDTOS[0];
    let lastImage = fileDTOS[fileDTOS.length - 1];

    let text1 = `
                                <div class="swiper-slide swiper-slide-active" style="width: 896px;">
                                    <span class="swiper-slide-span" ratio="0.75">
                                        <picture ratio="0.75" class="swiper-slide-picture image-container-picture">
                                            <img sizes=" 100vw" src="/file/display?fileName=${firstImage.filePath}/${firstImage.fileUuid}_${firstImage.fileOriginalName}">
                                        </picture>
                                    </span>
                                </div>
           `;

    let text2 = `
                                <div class="swiper-slide swiper-slide-active" style="width: 896px;">
                                    <span class="swiper-slide-span" ratio="0.75">
                                        <picture ratio="0.75" class="swiper-slide-picture image-container-picture">
                                            <img sizes=" 100vw" src="/file/display?fileName=${lastImage.filePath}/${lastImage.fileUuid}_${lastImage.fileOriginalName}">
                                        </picture>
                                    </span>
                                </div>
           `;

    $imageWrap.prepend(text2);
    $imageWrap.append(text1);
}

function numberFormat(number) {
    if(number < 10){
        return "0" + number;
    }
    return number;
}
// =========================================================================================================
//  슬라이드 이벤트
let checkArrow = false;
let count = 1;
const $prev = $(".image-expansion-wrapper-prev");
const $next = $(".image-expansion-wrapper-next");
const $imageNumber = $(".count-first");

$imageWrap.css("transform",`translate(-${width}px)`);

$prev.click(function(){
    if(checkArrow){return;}
    checkArrow = true;
    $imageWrap.css("transition","transform 0.3s");
    $imageWrap.css("transform",`translate(${-width * --count}px)`);

    if(count == 0) {
        count = fileDTOS.length;
        setTimeout(function(){
            $imageWrap.css("transition","transform 0s");
            $imageWrap.css("transform",`translate(${-width * (fileDTOS.length)}px)`);
        }, 300);
    }
    $imageNumber.text(numberFormat(count));
    setTimeout(()=>{checkArrow = false}, 300);
});

$next.click(function(){
    if(checkArrow){return;}
    checkArrow = true;

    $imageWrap.css("transition","transform 0.3s");
    $imageWrap.css("transform",`translate(${-width * ++count}px)`);

    if(count == fileDTOS.length + 1) {
        count = 1;
        setTimeout(function(){
            $imageWrap.css("transition","transform 0s");
            $imageWrap.css("transform",`translate(-${width}px)`);
        }, 300);
    }
    $imageNumber.text(numberFormat(count));

    setTimeout(()=>{checkArrow = false}, 300);
});



// ==========================================================================================================
//  모달
$image.each((i, e) => {
    $(e).click(() => {
        $imageModal.show();
    });
});

$modalExitButtton.click(() => {
    $imageModal.hide();
});
