package com.app.projectjar.entity.file.diary;

import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.Files;
import com.app.projectjar.type.FileType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = {"diary"})
@Table(name = "TBL_DIARY_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryFile extends Files {

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public DiaryFile(Long id, String fileOriginalName, String fileUuid, String filePath, FileType fileType, Diary diary) {
        super(id, fileOriginalName, fileUuid, filePath, fileType);
        this.diary = diary;
    }
}
