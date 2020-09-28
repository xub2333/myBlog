package com.example.demo.service;

import com.example.demo.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xubin
 * @date 2020/8/2 15:21
 */
public interface TypeService {

    Type saveType(Type type);
    Type getType(Long id);
    Type updateType(Long id,Type type);
    Page<Type> listType(Pageable pageable);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);
    void deleteType(Long id);
    Type getTypeByName(String name);

}
