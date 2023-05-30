const $mainImage = $(".main-picture");
$mainImage.attr("src", `/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}`)

const $subImage1 = $(".sub-image1");
$subImage1.attr("src", `/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}`)
const $subImage2 = $(".sub-image2");
$subImage2.attr("src", `/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}`)
const $subImage3 = $(".sub-image3");
$subImage3.attr("src", `/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}`)
if(fileDTOS.length - 4 != 0){
    $(".remaining-image-count").text("+ " + (fileDTOS.length - 4) +"개");
}else {
    $(".sub-image-container-plus").hide();
}