package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.LocalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<LocalFile, Integer> {

  @Query("select f from LocalFile f where f.fileType in ?1 ")
  List<LocalFile> findByFileType(List<Integer> fileType);
}
