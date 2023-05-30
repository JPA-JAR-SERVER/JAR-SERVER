const $contentPrev = $("#content-prev");
const $contentNext = $("#content-next");
const $firstContent = $(".first-content-slide");
const $firstContentAll = $(".first-content-box");
let firstWidth = 400;
let checkCount = 1;

if(isMobileDevice()){
    firstWidth = 336;
}

if(groupChallengeDTOS.length == 1){
    $contentNext.hide();
}

$contentNext.click(() => {

    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${firstWidth * checkCount++}px)`);

    $contentPrev.show();
    if(checkCount == groupChallengeDTOS.length) {
        $contentNext.hide();
    }
});

$contentPrev.click(() => {

    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${firstWidth * --checkCount}px)`); 
    $contentNext.show();
    
    if(checkCount == 0){
        $contentPrev.hide();
    }
});

const $secondPrev = $("#second-prev");
const $secondNext = $("#second-next");
const $secondContent = $(".second-content-slide");
const $secondContentAll = $(".content-image-box2");
let secondWidth = 300;

if(isMobileDevice()){
    secondWidth = 164;
}

let checkCount2 = 1;

$secondNext.click(() => {

    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${300 * checkCount2++}px)`);

    $secondPrev.show();

    if(checkCount2 == globalThis.suggestLength - 3) {
        $secondNext.hide();
    }
});

$secondPrev.click(() => {

    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${300 * --checkCount2}px)`); 
    $secondNext.show();
    
    if(checkCount2 == 0){
        $secondPrev.hide();
    }
});


const $heartButton = $(".heart-layout");
const $noHeart = $(".no-heart");
const $heartUp = $(".heart-up");

$heartButton.each((i, e) => {

    $(e).click(() => {
        if($($noHeart[i]).hasClass("heart-active")){
            $($heartUp[i]).hide();
            $($noHeart[i]).show();
            $($noHeart[i]).removeClass("heart-active");
        }else {
            $($noHeart[i]).addClass("heart-active");
            $($heartUp[i]).show();
            $($noHeart[i]).hide();
        }
    });
});


// 첫번째 슬라이드 터치 이벤트 부여
let curPos1 = 0;
let position1 = 0;
let start_x1, end_x1;

$firstContent.on('touchstart', touchStart);
$firstContent.on('touchend', touchEnd);

function touchStart(e) {
  start_x1 = e.touches[0].pageX;
}

function touchEnd(e) {
  end_x1 = e.changedTouches[0].pageX;
  if (start_x1 > end_x1) {
    next();
  } else {
    prev();
  }
}

function next() {
  if (curPos1 < groupChallengeDTOS.length) {
    position1 += firstWidth;
    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${position1}px)`);
    curPos1++;
  }
}   

function prev() {
  if (curPos1 > 0) {
    position1 -= firstWidth;
    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${position1}px)`);
    curPos1--;
  }
}

// 두번째 슬라이드 
let curPos2 = 0;
let position2 = 0;
let start_x2, end_x2;

$secondContent.on('touchstart', touchStart2);
$secondContent.on('touchend', touchEnd2);

function touchStart2(e) {
  start_x2 = e.touches[0].pageX;
}

function touchEnd2(e) {
  end_x2 = e.changedTouches[0].pageX;
  if (start_x2 > end_x2) {
    next2();
  } else {
    prev2();
  }
}

function next2() {
  if (curPos2 < globalThis.suggestLength - 2) {
    position2 += secondWidth;
    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${position2}px)`);
    curPos2++;
  }
}   

function prev2() {
  if (curPos2 > 0) {
    position2 -= firstWidth;
    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${position2}px)`);
    curPos2--;
  }
}


// 모바일인지 확인하는 함수
function isMobileDevice() {
  return (typeof window.orientation !== "undefined") || (navigator.userAgent.indexOf('IEMobile') !== -1);
};