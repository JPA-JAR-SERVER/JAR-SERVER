
function imageToEight(fileDTOS){
    let text = `
                                        <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:400px"src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 150px" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 250px;" src="/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[4].filePath}/${fileDTOS[4].fileUuid}_${fileDTOS[4].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:150px;" src="/file/display?fileName=${fileDTOS[5].filePath}/${fileDTOS[5].fileUuid}_${fileDTOS[5].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height:150px;" src="/file/display?fileName=${fileDTOS[6].filePath}/${fileDTOS[6].fileUuid}_${fileDTOS[6].fileOriginalName}">
                                                        </div>
                                                        <div  class="image">
                                                            <img style="height:250px" src="/file/display?fileName=${fileDTOS[7].filePath}/${fileDTOS[7].fileUuid}_${fileDTOS[7].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>

        `;
    $imageBox.append(text);
}

function imageToSeven(fileDTOS){
    let text = `
                                            <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:400px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 250px;" src="/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[4].filePath}/${fileDTOS[4].fileUuid}_${fileDTOS[4].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:150px;" src="/file/display?fileName=${fileDTOS[5].filePath}/${fileDTOS[5].fileUuid}_${fileDTOS[5].fileOriginalName}">
                                                        </div>
                                                        <div  class="image">
                                                            <img style="height:400px" src="/file/display?fileName=${fileDTOS[6].filePath}/${fileDTOS[6].fileUuid}_${fileDTOS[6].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>
        `;
    $imageBox.append(text);
}

function imageToSix(fileDTOS){
    let text = `
                                              <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:400px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 250px;" src="/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[4].filePath}/${fileDTOS[4].fileUuid}_${fileDTOS[4].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div  class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[5].filePath}/${fileDTOS[5].fileUuid}_${fileDTOS[5].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>

        `;
    $imageBox.append(text);
}

function imageToFive(fileDTOS){
    let text = `
                                                <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:400px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img  style="height: 150px" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:150px" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 400px;" src="/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div  class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[4].filePath}/${fileDTOS[4].fileUuid}_${fileDTOS[4].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>

        `;
    $imageBox.append(text);
}

function imageToFour(fileDTOS){
    let text = `
                                                <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:150px" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                        <div class="image">
                                                            <img style="height: 400px;" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div  class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[3].filePath}/${fileDTOS[3].fileUuid}_${fileDTOS[3].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>
        `;
    $imageBox.append(text);
}

function imageToFThree(fileDTOS){
    let text = `
                                                 <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:550px"src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height: 550px;"src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div  class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[2].filePath}/${fileDTOS[2].fileUuid}_${fileDTOS[2].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>
        `;
    $imageBox.append(text);
}

function imageToFTwo(fileDTOS){
    let text = `
                                                <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height: 550px;" src="/file/display?fileName=${fileDTOS[1].filePath}/${fileDTOS[1].fileUuid}_${fileDTOS[1].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>

        `;
    $imageBox.append(text);
}

function imageToOne(fileDTOS){
    let text =`
                                                <div class="image-box">
                                                    <div class="image-flex-column">
                                                        <div class="image">
                                                            <img style="height:550px" src="/file/display?fileName=${fileDTOS[0].filePath}/${fileDTOS[0].fileUuid}_${fileDTOS[0].fileOriginalName}">
                                                        </div>
                                                    </div>
                                                </div>
        `;
    $imageBox.append(text);
}

const fileSize = fileDTOS.length;

if(fileSize == 8){
    imageToEight(fileDTOS);
}else if(fileSize == 7){
    imageToSeven(fileDTOS);
}else if(fileSize == 6){
    imageToSix(fileDTOS);
}else if(fileSize == 5){
    imageToFive(fileDTOS);
}else if(fileSize == 4){
    imageToFour(fileDTOS);
}else if(fileSize == 3){
    imageToFThree(fileDTOS);
}else if(fileSize == 2){
    imageToFTwo(fileDTOS);
}else if(fileSize == 1){
    imageToOne(fileDTOS);
}else {

}

$(".image-box").click(() => {
    $imageModal.show();
});