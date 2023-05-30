package com.app.projectjar.entity.file;


import com.app.projectjar.type.FileType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public abstract class Files {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'NORMAL'")
    private FileType fileType;

    public Files(String fileOriginalName, String fileUuid, String filePath, FileType fileType) {
        this.fileOriginalName = fileOriginalName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileType = fileType;
    }
}
