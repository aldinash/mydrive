package com.aldinash.mydrive.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFile {
    private int fileid;
    private String filename;
    private String dataURL;
}
