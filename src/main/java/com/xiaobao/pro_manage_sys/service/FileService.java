package com.xiaobao.pro_manage_sys.service;

import com.xiaobao.pro_manage_sys.entity.LocalFile;

import java.util.List;

public interface FileService {

  LocalFile save(LocalFile uploadFile);

  List<LocalFile> findByFileType(List<Integer> fileType);

  Boolean deleteFiles(List<LocalFile> files);
}
