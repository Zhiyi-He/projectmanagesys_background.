package com.xiaobao.pro_manage_sys.service.impl;

import com.xiaobao.pro_manage_sys.entity.LocalFile;
import com.xiaobao.pro_manage_sys.repository.FileRepository;
import com.xiaobao.pro_manage_sys.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

  @Resource FileRepository fileRepository;

  @Override
  public Boolean deleteFiles(List<LocalFile> files) {
    fileRepository.deleteInBatch(files);
    return true;
  }

  @Override
  public List<LocalFile> findByFileType(List<Integer> fileType) {
    return fileRepository.findByFileType(fileType);
  }

  @Override
  public LocalFile save(LocalFile uploadFile) {
    return fileRepository.save(uploadFile);
  }
}
