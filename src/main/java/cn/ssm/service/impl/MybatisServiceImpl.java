package cn.ssm.service.impl;

import cn.ssm.dao.MybatisDao;
import cn.ssm.entity.Student;
import cn.ssm.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mybatisService")
public class MybatisServiceImpl implements MybatisService {
    @Autowired
    private MybatisDao mybatisDao;

    public void add(Student student){
        mybatisDao.add(student);
    }
    public void delete(Student student){
        mybatisDao.delete(student);
    }
    public void update(Student student){
        mybatisDao.update(student);
    }
    public List<Student> findAll(){
        return mybatisDao.findAll();
    }
    public Student findById(String id){ return mybatisDao.findById(id);};
    public Student findByStudent(Student student){ return mybatisDao.findByStudent(student);}

    @Override
    public Integer findByStates(String str) {
        return mybatisDao.findByStates(str);
    }

    @Override
    public Integer findByType(String str) {
        return mybatisDao.findByType(str);
    }

    ;

}
